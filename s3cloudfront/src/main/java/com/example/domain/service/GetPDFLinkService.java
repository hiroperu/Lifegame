package com.example.domain.service;

import com.example.domain.model.GetPDFLink;

public interface GetPDFLinkService {
    // // MOTAS側API呼び出しのやつ
    // GetPDFLink getMOTASData(GetPDFLink getpdfLink);

    // // PDF作るやつのイメージ
    // File createPDF(GetPDFLink getpdfLink);

    // // S3に保存するやつ
    // void putPDFtoS3(File pdf);

    // CloudFrontのURL取得するやつ
    String createCloudFrontURL(GetPDFLink getpdfLink);

}