package com.fabio.pratica.controller;


import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabio.pratica.configuration.ResponderConfiguraCao;
import com.fabio.pratica.dominio.User;
import com.fabio.pratica.service.ResponderService;

import org.springframework.web.bind.annotation.RequestParam;

@RestController 
@RequestMapping("/responder")
public class Responder {

    private ResponderService responderService;

    private final ResponderConfiguraCao responderConfiguraCaoBean;

    Responder(ResponderService responderService, ResponderConfiguraCao responderConfiguraCaoBean) {
        this.responderService = responderService;
        this.responderConfiguraCaoBean = responderConfiguraCaoBean;
    }

    @GetMapping("/get")            
    public String hello() {
        return responderService.hello("Fabio");
    }

    //@PostMapping("/post")
    @GetMapping("/get2")
    public String helloPost() {

        
        return responderService.hello("Fabio") + ""+responderConfiguraCaoBean.getMsg();
    }


    @PostMapping("/post/{id}")
    public ResponseEntity<Map<String, String>>  helloPost(
        @RequestBody User usuario, 
        @PathVariable("id") String id,
        @RequestParam("filtro") String name) {
        return ResponseEntity.ok(Map.of(
            "mensagem", "Tudo certo " + usuario.getName(),
            "id", id,
            "filtro", name
        ));
    }

    @GetMapping("/perfil")
public Map<String, String> perfil(
        @org.springframework.security.core.annotation.AuthenticationPrincipal
        org.springframework.security.oauth2.jwt.Jwt jwt) {

    return Map.of(
        "mensagem", "Acesso autorizado!",
        "usuario", jwt.getSubject()
    );
}
}
