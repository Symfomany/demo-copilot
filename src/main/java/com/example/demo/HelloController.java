
package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
      return "Hello, World!";
    }

    // faire une fonction qui retourn "Coucou"
    @GetMapping("/coucou")
    public String coucou() {
      return "Coucou";
    }



}
