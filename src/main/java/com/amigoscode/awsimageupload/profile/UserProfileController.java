package com.amigoscode.awsimageupload.profile;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user-profiles")
public class UserProfileController {

		private final UserProfileService userProfileService;

		@GetMapping
		public List<UserProfile> getUserProfiles() {
				return userProfileService.getUserProfiles();
		}

		@PostMapping(path = "/{userProfileId}/image/upload",
				consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public void uploadUserProfileImage(@PathVariable("userProfileId") final UUID userProfileId,
		                                   @RequestParam("file") final MultipartFile file) {
				userProfileService.uploadUserProfileImage(userProfileId, file);
		}
}
