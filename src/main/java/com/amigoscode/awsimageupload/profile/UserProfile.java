package com.amigoscode.awsimageupload.profile;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Optional;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class UserProfile {

		private final UUID userProfileId;
		private final String userName;
		private String userProfileImageLink; // S3 key

		public Optional<String> getUserProfileImageLink() {
				return Optional.ofNullable(userProfileImageLink);
		}

		public void setUserProfileImageLink(String userProfileImageLink) {
				this.userProfileImageLink = userProfileImageLink;
		}
}
