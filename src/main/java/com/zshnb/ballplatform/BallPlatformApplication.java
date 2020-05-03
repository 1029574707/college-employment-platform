package com.zshnb.ballplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.zshnb.ballplatform.mapper")
@EnableTransactionManagement
public class BallPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(BallPlatformApplication.class, args);
	}
}
