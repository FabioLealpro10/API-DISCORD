package com.fabio.pratica.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


class ResponderConfiguraCaoBean {


    public String msg;

    ResponderConfiguraCaoBean(String nome) {
        this.msg = "Hello, " + nome + "!";
        
    }

    public String getMsg() {
        return msg;
    }

}


@Configuration 
public class ResponderConfiguraCao {

    @Bean 
    public ResponderConfiguraCaoBean ResponderConfiguraCao(){
        return new ResponderConfiguraCaoBean("Teste");
    }
    public String getMsg() {
        return ResponderConfiguraCao().getMsg();
    }

}
