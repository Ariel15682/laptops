package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:application-dev.properties")
public class HelloControllers {

    @Value("${app.message}")
    private String mensaje;

    @GetMapping("/saludo")
    public String saludo(){ return "Hola, buenos dias desde: " + mensaje;}
}
