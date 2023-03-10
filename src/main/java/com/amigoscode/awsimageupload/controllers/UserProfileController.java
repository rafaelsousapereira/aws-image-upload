package com.amigoscode.awsimageupload.controllers;

import com.amigoscode.awsimageupload.profile.UserProfile;
import com.amigoscode.awsimageupload.services.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user-profile")
@CrossOrigin("*")
public class UserProfileController {

		private final UserProfileService userProfileService;

		@GetMapping
		public ResponseEntity<List<UserProfile>> getUserProfiles() {
				return ResponseEntity.ok(userProfileService.getUserProfiles());
		}

		@PostMapping(path = "/{userProfileId}/image/upload",
				consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public void uploadUserProfileImage(@PathVariable("userProfileId") final UUID userProfileId,
		                                   @RequestParam("file") final MultipartFile file) {
				userProfileService.uploadUserProfileImage(userProfileId, file);
		}
}
