package com.amigoscode.awsimageupload.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

		@Value("${AWS_ACCESS_KEY_ID}")
		private String accessKey;
		@Value("${AWS_SECRET_ACCESS_KEY}")
		private String secretKey;

		@Bean
		public AmazonS3 s3() {
				final BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

				return AmazonS3ClientBuilder
						.standard()
						.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
						.build();
		}
}

