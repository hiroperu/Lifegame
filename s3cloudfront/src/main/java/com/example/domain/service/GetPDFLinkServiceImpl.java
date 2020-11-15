package com.example.domain.service;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.example.domain.model.GetPDFLink;


@Service
@Transactional
public class GetPDFLinkServiceImpl implements GetPDFLinkService {
    // // MOTAS側API呼び出しのやつ
    // GetPDFLink getMOTASData(GetPDFLink getpdfLink);

    // // PDF作るやつのイメージ
    // File createPDF(GetPDFLink getpdfLink);

    // // S3に保存するやつ
    @Autowired
    MyS3Util myS3Util;

    @Autowired 
    ServletContext context;
    // void putPDFtoS3(File pdf);


    // CloudFrontのURL取得するやつ
    public String createCloudFrontURL(GetPDFLink getpdfLink) {
        String putresult = "";
        // MOTAS側API呼び出しの処理
        
        // PDF作る処理
        // File file = new File("/tmp/test-" + System.currentTimeMillis() + ".txt");
        // String fileNmae = file.getName();
        // S3に保存する処理
        // putresult = myS3Util.putPDFtoS3(file);

        // S3に保存したPDFの事前署名付きURLを取得する処理
        String fileName = "index.html";
        // String privateKeyFilePath = "WEB-INF/rsa-private-key.der";
        // File cloudFrontPrivateKeyFile = Paths.get(privateKeyFilePath).toFile();
        // ServletContext context = this.getServletContext();
        String privateKeyFilePath = context.getRealPath("WEB-INF/rsa-private-key.der");
        File cloudFrontPrivateKeyFile = Paths.get(privateKeyFilePath).toFile();
        // System.out.println("##########START###########");
        // if (cloudFrontPrivateKeyFile.exists()){
        //     System.out.println("exists");
        // } else {
        //     System.out.println("NOT FOUND(privateKeyFilePath):" + privateKeyFilePath);
        // }
        // System.out.println("##########END###########");
        // String privateKeyFilePath = Paths.getRealPath("WEB-INF/rsa-private-key.der");

        putresult = myS3Util.getPreSignedCloudFrontURL(fileName, cloudFrontPrivateKeyFile);
        return putresult;

    }

}