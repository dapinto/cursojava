package co.edu.usbcali.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BankClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankClienteApplication.class, args);
	}

}
