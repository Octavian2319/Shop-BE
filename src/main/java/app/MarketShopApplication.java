package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"app.Controller", "app.Service", "app.Entity", "app.Repository"})
public class MarketShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarketShopApplication.class, args);
	}
}
