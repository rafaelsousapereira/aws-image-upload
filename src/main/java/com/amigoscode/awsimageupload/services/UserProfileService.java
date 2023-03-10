package com.amigoscode.awsimageupload.services;

import com.amigoscode.awsimageupload.bucket.BucketName;
import com.amigoscode.awsimageupload.filestore.FileStore;
import com.amigoscode.awsimageupload.profile.UserProfile;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@AllArgsConstructor
@Service
public class UserProfileService {

		final Logger log = LoggerFactory.getLogger(UserProfileService.class);
		private final UserProfileDataAccessService userProfileDataAccessService;
		private final FileStore fileStore;

		public List<UserProfile> getUserProfiles() {
				return userProfileDataAccessService.getUserProfiles();
		}

		public void uploadUserProfileImage(final UUID userProfileId, final MultipartFile file) {

				isFileEmpty(file);
				log.info("Checking if image is not empty. {}", file.getOriginalFilename());

				isImage(file);
				log.info("Checking if file is an image. {}", file.getContentType());

				UserProfile user = getUserProfileOrThrow(userProfileId);
				log.info("Checking the user exists in database. {}", userProfileId);

				Map<String, String> metadata = extractMetadata(file);
				log.info("Grabbing some metadata from file if any. {}", metadata);

				//
				String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
				String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

				try {
						fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
						user.setUserProfileImageLink(filename);
						log.info("Storing the image in s3 and update database with s3 image link. {}", path);
				} catch (IOException e) {
						throw new IllegalStateException(e);
				}
		}

		private static Map<String, String> extractMetadata(MultipartFile file) {
				Map<String, String> metadata = new HashMap<>();
				metadata.put("Content-Type", file.getContentType());
				metadata.put("Content-Length", String.valueOf(file.getSize()));
				return metadata;
		}

		private UserProfile getUserProfileOrThrow(UUID userProfileId) {
				return userProfileDataAccessService
						.getUserProfiles()
						.stream()
						.filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
						.findFirst()
						.orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
		}

		private static void isImage(MultipartFile file) {
				if (Arrays.asList(IMAGE_JPEG, IMAGE_PNG, IMAGE_GIF).contains(file.getContentType())) {
						throw new IllegalStateException("File must be an image [ " + file.getContentType() + " ]");
				}
		}

		private static void isFileEmpty(MultipartFile file) {
				if (file.isEmpty()) {
						throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + " ]");
				}
		}
}
