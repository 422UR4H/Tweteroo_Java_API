package com.tweteroo.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*")
@RestController
public class HealthController {
  @GetMapping("health")
  public String getHealth() {
    return "I'm Ok!";
  }

}
