package com.sbicolending.utils;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.sbicolending.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;


@Service
public class S3DocumentLinks {

    @Autowired
    private AmazonS3 client;

    @Value("${aws.bucketName}")
    private String bucketName;

    @Value("${aws.accessKey}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsScretKey;

    @Value("${aws.region}")
    private String region;

    public String preSignedUrl(String fileName){

        try {

            Date expirationDate = new Date();
            long time = expirationDate.getTime();
            int mins=1;
            time = time+ mins*60*1000;
            expirationDate.setTime(time);

            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName,fileName)
                    .withMethod(HttpMethod.GET)
                    .withExpiration(expirationDate);

            URL url = client.generatePresignedUrl(generatePresignedUrlRequest);

            return  url.toString();
        }catch (Exception e){
            throw new SystemException("1110","S3 "+fileName+" genrate link exception.");
        }

    }

    public List<String> allFiles(String folderName){

        AmazonS3 amazonS3 = null;
        List<String> finalFileName = new ArrayList<>();
        try {

        amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey,awsScretKey)))
                .withRegion(region)
                .build();

        ListObjectsV2Request request = new ListObjectsV2Request()
                .withBucketName(bucketName).withPrefix(folderName);

        ListObjectsV2Result result;
        do {
            result = amazonS3.listObjectsV2(request);

            List<S3ObjectSummary> objects = result.getObjectSummaries();
            for (S3ObjectSummary object : objects) {

                //System.out.println("getBucketName : "+object.getBucketName());
                //System.out.println("getKey : "+object.getKey());

                String fileName = object.getKey().substring(object.getKey().lastIndexOf("/"));
                if(fileName.startsWith("/")) {
                    fileName = fileName.substring(1);
                }
                if (StringUtils.isNoneBlank(fileName)){
                    finalFileName.add(preSignedUrl(folderName+fileName));
                }
            }
            System.out.println("file : "+finalFileName.toString());
            String token = result.getNextContinuationToken();
            request.setContinuationToken(token);
        } while (result.isTruncated());


        }catch (Exception e){
            throw new SystemException("1110","aws s3 server exception");
        }finally {
            amazonS3.shutdown();
        }

        return finalFileName;
    }










    public List<Long> getBusinessPhoneNumberNia() {

            List<Long> businessPhoneNumberNia = new ArrayList<>();
            businessPhoneNumberNia.add(9876543210L);
            businessPhoneNumberNia.add(9876543210L);
            return businessPhoneNumberNia;
    }

    public List<String> getBusinessEmailIdNia() {

            List<String> businessEmailIdNia = new ArrayList<>();
            businessEmailIdNia.add("holmes@credavenue.com");
            businessEmailIdNia.add("holmes@credavenue.com");

            return businessEmailIdNia;
    }



}
