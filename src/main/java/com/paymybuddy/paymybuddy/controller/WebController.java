package com.paymybuddy.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
   @GetMapping("/index")
   public String home() {
      return "index";
   }
   
   @GetMapping("transfer")
   public String page1() {
      return "transfer";
   }
}