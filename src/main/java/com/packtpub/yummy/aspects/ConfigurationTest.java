package com.packtpub.yummy.aspects;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationTest {
    @Bean
    public int foo(){
        return 0;
    }
    @Bean
    public int bar(){
        return foo()+1;}
}
