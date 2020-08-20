package com.zhangzhihao.FileUpload.Java.minIoTools;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.apache.commons.io.IOUtils;
import org.springframework.util.DigestUtils;

import java.io.*;
import java.util.Arrays;

/**
 * @author liangtao
 * @Date 2020/8/19
 **/
public class S3Sample {
    private static String bucketName = "dmcenter";
    private static String keyName = "hosts";
    //    static final String accessKey = "admin";
//    static final String secretKey = "1234567890";
//    static final String endpoint = "http://172.168.10.223:9007";
    static final String accessKey = "minio";
    static final String secretKey = "miniopwd";
    static final String endpoint = "http://121.199.44.208:9001";

    static String outputFile = "C:\\Users\\liangtao\\Desktop\\test.7z";
    static String outPath = "C:\\Users\\liangtao\\Desktop\\" + System.currentTimeMillis() + ".7z";
    //1M
    static Long partSize = 1024 * 1024L;
    static File file = new File(outputFile);
    static String md5;
    static AmazonS3 s3Client;

    static {
        try {
            md5 = DigestUtils.md5DigestAsHex(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        initialS3();
        String uploadId = s3Client.initiateMultipartUpload(new InitiateMultipartUploadRequest(bucketName, md5)).getUploadId();
        try {
            System.out.println("将新对象从文件上传到S3\n");
            // 上传文件
            uploadParts(uploadId);

            // 下载文件
            GetObjectRequest rangeObjectRequest = new GetObjectRequest(bucketName, md5);
            S3Object objectPortion = s3Client.getObject(rangeObjectRequest);
            System.out.println("Printing bytes retrieved:");
            IOUtils.copy(objectPortion.getObjectContent(), new FileOutputStream(new File(outPath)));
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " + "means your request made it "
                    + "to Amazon S3, but was rejected with an error response" + " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());

        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " + "means the client encountered " + "an internal error while trying to "
                    + "communicate with S3, " + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());

        }

    }

    private static void initialS3() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        ClientConfiguration clientConfiguration = new ClientConfiguration();
//        clientConfiguration.addHeader("Content-Length",);
//        clientConfiguration.setSignerOverride("AWSS3V4SignerType");
        clientConfiguration.setConnectionTimeout(1000);
        s3Client = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, Regions.US_EAST_1.name()))
                .withPathStyleAccessEnabled(true)
                .withClientConfiguration(clientConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    private static void uploadParts(String uploadId) throws AmazonClientException, IOException {
        int filePosition = 0;
        int bufferPosition = 0;
        byte[] buffer = new byte[Math.toIntExact(partSize)];
        File file = new File(outputFile);
        FileInputStream fileToUpload = new FileInputStream(file);
//        List<byte[]> allList = new ArrayList<>();
        int read = 0;

        while (filePosition < file.length()) {
            read = fileToUpload.read(buffer);
            if (read == -1) {
                break;
            }
            byte[] bytesRead = Arrays.copyOf(buffer, read);
            //Upload part.
            UploadPartRequest request = new UploadPartRequest()
                    .withBucketName(bucketName)
                    .withKey(md5)
                    .withFileOffset(filePosition)
                    .withInputStream(new ByteArrayInputStream(bytesRead))
                    .withPartSize(bytesRead.length).withPartNumber(nextInt())
                    .withLastPart((filePosition+read)==file.length())
                    .withUploadId(uploadId);
            UploadPartResult uploadPartResult = s3Client.uploadPart(request);
            filePosition += read;
        }
        fileToUpload.close();
    }

    static int i = 0;

    private static int nextInt() {
        return ++i;
    }
}



