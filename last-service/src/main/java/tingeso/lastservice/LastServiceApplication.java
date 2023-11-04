package tingeso.lastservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // This annotation is used to register the service in the Eureka server
public class LastServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LastServiceApplication.class, args);
	}

}
