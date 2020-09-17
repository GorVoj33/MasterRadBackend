package fon.elab.prodavnica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class ProdavnicaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProdavnicaApplication.class, args);
		System.out.println("Pokrenuta app");
		
		
	}

}
