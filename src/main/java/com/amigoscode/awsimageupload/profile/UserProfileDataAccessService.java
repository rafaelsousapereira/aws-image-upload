package com.amigoscode.awsimageupload.profile;

import com.amigoscode.awsimageupload.datastore.FakeUserProfileDataStore;
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
