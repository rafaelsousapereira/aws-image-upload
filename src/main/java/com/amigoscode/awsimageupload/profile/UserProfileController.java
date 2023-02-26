package com.amigoscode.awsimageupload.profile;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user-profiles")
public class UserProfileController {

		private final UserProfileService userProfileService;

		@GetMapping
		public List<UserProfile> getUserProfiles() {
				return userProfileService.getUserProfiles();
		}
}
