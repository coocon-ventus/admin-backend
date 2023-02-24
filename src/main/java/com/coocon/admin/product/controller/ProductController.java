package com.coocon.admin.product.controller;


import com.coocon.admin.common.error.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class ProductController {

    @PostMapping("/200")
    public ResponseEntity<ErrorResponse> res200(){
        log.info("Request result is http.OK");
        return new ResponseEntity<>(new ErrorResponse("200","success"), HttpStatus.OK);
    }

    @PostMapping("/400")
    public ResponseEntity<ErrorResponse> res400(){
        log.info("Request result is http.BAD_REQUEST");
        return new ResponseEntity<>(new ErrorResponse("400","success"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/500")
    public ResponseEntity<ErrorResponse> res500(){
        log.info("Request result is http.INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(new ErrorResponse("400","success"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/timeout")
    public ResponseEntity<ErrorResponse> resTimeout(){
        log.info("Request result is http.INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(new ErrorResponse("400","success"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
