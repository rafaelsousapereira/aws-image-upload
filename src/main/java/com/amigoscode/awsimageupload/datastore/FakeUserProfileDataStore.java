package com.amigoscode.awsimageupload.datastore;

import com.amigoscode.awsimageupload.profile.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

		static final Logger log = LoggerFactory.getLogger(FakeUserProfileDataStore.class);
		private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

		static {
				USER_PROFILES.add(new UserProfile(UUID.fromString("965f6de5-6625-4a85-ae69-a0dac0b236bc"), "janetjones", null));
				USER_PROFILES.add(new UserProfile(UUID.fromString("63ea366d-acf3-458d-917e-e18840ec0ed6"), "antoniojunior", null));
		}

		public List<UserProfile> getUserProfiles() {
				log.info("Getting fake user profiles in the data store. {}", USER_PROFILES);
				return USER_PROFILES;
		}
}
