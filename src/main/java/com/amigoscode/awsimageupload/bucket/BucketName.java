package com.amigoscode.awsimageupload.bucket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BucketName {

		PROFILE_IMAGE("amigoscode-images-upload-123");

		private final String bucketName;
}
