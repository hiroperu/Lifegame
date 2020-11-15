package com.example.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import com.github.dozermapper.core.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.service.GetPDFLinkService;
import com.example.api.GetPDFLinkResponse;
import com.example.domain.model.GetPDFLink;

@RestController
@RequestMapping("getPDFlink")
public class GetPDFLinkRestController {
    @Autowired
    GetPDFLinkService service;
    
    @Autowired
    Mapper beanMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GetPDFLinkResponse getLink() {
        // System.out.println("#####################");
        // System.out.println(System.getProperty("user.name"));
        // System.out.println("#####################");
        GetPDFLink getpdfLink = new GetPDFLink();
        GetPDFLinkResponse response = new GetPDFLinkResponse();
        response.setResponsStr(service.createCloudFrontURL(getpdfLink));
        //GetPDFLinkResponse response = beanMapper.map(res, GetPDFLinkResponse.class);
        return response;
    }

}