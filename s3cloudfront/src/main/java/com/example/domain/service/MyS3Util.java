package com.example.domain.service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import com.amazonaws.services.cloudfront.*;
import com.amazonaws.services.cloudfront.util.SignerUtils.Protocol;

import java.security.spec.InvalidKeySpecException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;

@Component
public class MyS3Util {

    private static Region region = Region.AP_NORTHEAST_1;
    private static S3Client s3 = S3Client.builder()
                .region(region)
                .build();

    private static String bucketName = "hiroki-bucket1605252517504";
    private static String distributionDomain = "d31bha0n011jr.cloudfront.net";
    // private static String privateKeyFilePath = "/tmp/rsa-private-key.der";
    // private static String cloudFrontKeyPairId = "K1ES8V3753BTY7";
    
    // CloudFrontの事前署名付きURLを発行する
    // 前提バケットがCloudFrontのディストリビューションに設定されている
    public static String getPreSignedCloudFrontURL(String fileName, File cloudFrontPrivateKeyFile) {
        // ServletContext context = this.getServletContext();
        // String privateKeyFilePath = context.getRealPath("/WEB-INF/rsa-private-key.der");
        // the DNS name of your CloudFront distribution, or a registered alias
        String distributionDomainName = distributionDomain;
        // the private key you created in the AWS Management Console
        // String privateKeyFilePath = "WEB-INF/rsa-private-key.der";
        // File cloudFrontPrivateKeyFile = Paths.get(privateKeyFilePath).toFile();
        // if (cloudFrontPrivateKeyFile.exists()){
        //     System.out.println("exists");
        // } else {
        //     System.out.println("NOT FOUND");
        // }
        // the unique ID assigned to your CloudFront key pair in the console    
        String cloudFrontKeyPairId = "K1ES8V3753BTY7";   
        Date expirationDate = new Date(System.currentTimeMillis() + 60 * 1000 * 5);
        String resourceFileName  = fileName;

        // String policy = buildCustomPolicyForSignedUrl(
        //     resourcePath,
        //     expirationDate,
        //     null);
        String signedUrl = "";
        try {
        signedUrl = CloudFrontUrlSigner.getSignedURLWithCannedPolicy(
            Protocol.https, 
            distributionDomainName, 
            cloudFrontPrivateKeyFile,   
            resourceFileName, // the resource path to our content
            cloudFrontKeyPairId, 
            expirationDate);
        System.out.println(signedUrl);

        } catch (InvalidKeySpecException e){
            System.err.println(e.getMessage());
        } catch (IOException e){
            System.err.println(e.getMessage());
        }

        return signedUrl;        

    }

    // 引数のファイルを所定のS3バケットに保存する
    public static String putPDFtoS3(File pdffile) {
        try {
            PutObjectResponse response = s3.putObject(PutObjectRequest.builder()
                             .bucket(bucketName)
                             .key(pdffile.getName())
                             .build(),
                             RequestBody.fromBytes(getObjectFile(pdffile)));
                    //  RequestBody.fromBytes(getObjectFile(objectPath)));
            return response.eTag();
 
         } catch (S3Exception e) {
             System.err.println(e.getMessage());
             System.exit(1);
         }
         return "";
    }

    // Return a byte array
    private static byte[] getObjectFile(File file) {

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {
            // File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytesArray;
    }



}