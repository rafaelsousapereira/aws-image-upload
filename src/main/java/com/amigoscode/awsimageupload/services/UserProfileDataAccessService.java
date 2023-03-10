package com.amigoscode.awsimageupload.services;

import com.amigoscode.awsimageupload.datastore.FakeUserProfileDataStore;
import com.amigoscode.awsimageupload.profile.UserProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class UserProfileDataAccessService {

		private final FakeUserProfileDataStore fakeUserProfileDataStore;

		public List<UserProfile> getUserProfiles() {
				return fakeUserProfileDataStore.getUserProfiles();
		}

}
