package br.com.alisson.financeira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class FinanceiraApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceiraApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/cliente/*").allowedOrigins("http://localhost:8080").allowedMethods("*");
                registry.addMapping("/emprestimo/*").allowedOrigins("http://localhost:8080").allowedMethods("*");
            }
        };
    }
}
