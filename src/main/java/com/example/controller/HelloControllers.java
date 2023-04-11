package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControllers {

    @GetMapping("/saludo")
    public String saludo(){ return "Hola, buenos dias desde Spring!";}
}
