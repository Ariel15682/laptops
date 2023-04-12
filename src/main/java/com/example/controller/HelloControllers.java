package com.example.controller;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloControllers {

    @Value("${app.message}")
    private String mensaje;

    @GetMapping("/saludo")
    public String saludo(){
        System.out.println("CONSOLA: Hola desde: " + mensaje);
        return "Hola, buenos dias";}
}
