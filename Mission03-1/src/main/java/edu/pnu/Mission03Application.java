package edu.pnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "edu.pnu", "com.ruby" }) // 컴포넌트를 스캔할때 명시된 패키지 모두 탐색
public class Mission03Application {

	public static void main(String[] args) {
		SpringApplication.run(Mission03Application.class, args);
	}

}
