package com.test.basic.auth.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    @GetMapping("/public")
    public String getPublicEndpoint(){
        return "Este es un Endpoint publico";
    }

    @GetMapping("/admin")
    public String getPrivateEndpoint(){
        return "Este es un Endpoint de Administrador";
    }

    @GetMapping("/manager")
    public String getManagerEndpoint(){
        return "Este es un Endpoint de Manager";
    }
}
