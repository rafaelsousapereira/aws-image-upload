package com.amigoscode.awsimageupload.profile;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserProfileService {

		private final UserProfileDataAccessService userProfileDataAccessService;

		public List<UserProfile> getUserProfiles() {
				return userProfileDataAccessService.getUserProfiles();
		}

}
