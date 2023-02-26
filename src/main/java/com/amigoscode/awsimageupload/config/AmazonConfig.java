package com.amigoscode.awsimageupload.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.amazonaws.auth.profile.internal.ProfileKeyConstants.AWS_ACCESS_KEY_ID;
import static com.amazonaws.auth.profile.internal.ProfileKeyConstants.AWS_SECRET_ACCESS_KEY;

@Configuration
public class AmazonConfig {
		@Bean
		public AmazonS3 s3() {
				BasicAWSCredentials awsCredentials = new BasicAWSCredentials(
						AWS_ACCESS_KEY_ID,
						AWS_SECRET_ACCESS_KEY
				);

				return AmazonS3ClientBuilder
						.standard()
						.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
						.build();
		}
}

