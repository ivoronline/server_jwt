package com.ivoronline.server_jwt.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  //=========================================================================
  // HELLO
  //=========================================================================
  @Secured("ROLE_USER")
  @RequestMapping("Hello")
  String hello() {
    return "Hello from Controller";
  }

  //==============================================================
  // GET TOKEN
  //==============================================================
  @RequestMapping("GetToken")
  String getToken() {
    return "MyToken";
  }

  //==============================================================
  // SEND TOKEN
  //==============================================================
  @RequestMapping("SendToken")
  String sendToken(@RequestHeader String authorization) {
    return "RECEIVED: " + authorization;
  }

}

