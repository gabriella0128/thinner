package com.nns.thinner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.tmobi.evcharge.common.logging")
@EnableJpaAuditing
@EnableAsync
@EnableAspectJAutoProxy
public class ThinnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThinnerApplication.class, args);
	}

}
