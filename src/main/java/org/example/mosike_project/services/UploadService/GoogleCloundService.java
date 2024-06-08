package org.example.mosike_project.services.UploadService;

import com.google.api.client.util.Value;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GoogleCloundService {
    @Value("${your-gcp-project-id}")
    private String projectId;

    public String uploadFile(MultipartFile file, String bucketName, String folderName, String fileName) {
        try {
            // Load the Google Cloud credentials from the JSON file
            Resource credentialsResource = new ClassPathResource("credentials.json");
            Credentials googleCredentials = GoogleCredentials.fromStream(credentialsResource.getInputStream());

            // Create a Storage client
            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(googleCredentials)
                    .setProjectId(projectId)
                    .build()
                    .getService();

            // Construct the full file path within the bucket
            String filePath = folderName + "/" + fileName;

            // Create the BlobInfo object with the file path
            BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, filePath))
                    .setContentType(file.getContentType())
                    .build();

            // Upload the file to Google Cloud Storage
            Blob blob = storage.create(blobInfo, file.getBytes());
            // Set public access for the uploaded file for 24 hours
            blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
            // Return the public URL of the uploaded file
            String publicUrl = String.format("https://storage.googleapis.com/%s/%s", bucketName, filePath);
            return publicUrl;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to Google Cloud Storage", e);
        }
    }
    public void deleteFile(String bucketName, String folderName, String fileName) {
        try {
            // Load the Google Cloud credentials from the JSON file
            Resource credentialsResource = new ClassPathResource("credentials.json");
            Credentials googleCredentials = GoogleCredentials.fromStream(credentialsResource.getInputStream());

            // Create a Storage client
            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(googleCredentials)
                    .setProjectId(projectId)
                    .build()
                    .getService();

            // Construct the full file path within the bucket
            String filePath = folderName + "/" + fileName;

            // Delete the file from Google Cloud Storage
            storage.delete(BlobId.of(bucketName, filePath));
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file from Google Cloud Storage", e);
        }
    }
}
