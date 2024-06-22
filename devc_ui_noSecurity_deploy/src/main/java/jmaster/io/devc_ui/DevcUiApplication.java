package jmaster.io.devc_ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableFeignClients


public class DevcUiApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(DevcUiApplication.class, args);
	}

}
