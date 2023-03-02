package com.amigoscode.awsimageupload.profile;

import com.amigoscode.awsimageupload.bucket.BucketName;
import com.amigoscode.awsimageupload.filestore.FileStore;
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

		/**
			* TODO:
			* 1. Check if image is not empty.
			* 2. If file is an image.
			* 3. The user exists in our database.
			* 4. Grab some metadata from file if any.
			* 5. Store the image in s3 and update database (userProfileImageLink) with s3 image link.
			* @param userProfileId id of user profifle
			* @param file file type image
			*/
		public void uploadUserProfileImage(final UUID userProfileId, final MultipartFile file) {
				validationFile(file);
				UserProfile user = getUserProfile(userProfileId);
				Map<String, String> metadata = getMetadata(file);

				String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
				String filename = String.format("%s-%s", file.getName(), UUID.randomUUID());

				try {
						fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
				} catch (IOException e) {
						throw new IllegalStateException(e);
				}
		}

		private static Map<String, String> getMetadata(MultipartFile file) {
				Map<String, String> metadata = new HashMap<>();
				metadata.put("Content-Type", file.getContentType());
				metadata.put("Content-Length", String.valueOf(file.getSize()));
				return metadata;
		}

		private UserProfile getUserProfile(UUID userProfileId) {
				return userProfileDataAccessService
						.getUserProfiles()
						.stream()
						.filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
						.findFirst()
						.orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
		}

		private static void validationFile(MultipartFile file) {
				if (file.isEmpty()) {
						throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + " ]");
				}

				if (Arrays.asList(IMAGE_JPEG, IMAGE_PNG, IMAGE_GIF).contains(file.getContentType())) {
						throw new IllegalStateException("File must be an image");
				}
		}
}
