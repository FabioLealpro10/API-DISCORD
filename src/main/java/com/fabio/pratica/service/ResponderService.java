package com.fabio.pratica.service;

import org.springframework.stereotype.Service;

@Service 
public class ResponderService {

    

    public String hello(String name) {
        return "Hello, " + name + "!";
    }

}
