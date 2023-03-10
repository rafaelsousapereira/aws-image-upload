package com.amigoscode.awsimageupload.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FileStore {
		final Logger log = LoggerFactory.getLogger(FileStore.class);
		private final AmazonS3 s3;
		public void save(final String path,
		                 final String fileName,
		                 final Optional<Map<String, String>> optionalMetadata,
		                 final InputStream inputStream) {

				ObjectMetadata metadata = new ObjectMetadata();
				optionalMetadata.ifPresent(map -> {
						if (!map.isEmpty()) {
								map.forEach(metadata::addUserMetadata);
						}
				});

				try {
						log.info("Saving image at bucket in. {}", fileName);
						s3.putObject(path, fileName, inputStream, metadata);
				}  catch (AmazonServiceException e) {
						log.error("Failed to store file to S3. {}", e.getMessage());
				}
		}

		public byte[] download(String path, String key) {
				try {
						S3Object object = s3.getObject(path, key);
						log.info("Download successfully {}", path);
						return IOUtils.toByteArray(object.getObjectContent());
				} catch (AmazonServiceException | IOException e) {
						throw new IllegalStateException(e);
				}
		}
}
