package br.com.easyprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication
public class EasypriceApplication {

	public static void main(String[] args) {

		SpringApplication.run(EasypriceApplication.class, args);

		// Criação do diretório 'uploads'
		String uploadDirectoryPath = "uploads";
		File uploadDirectory = new File(uploadDirectoryPath);
		if (!uploadDirectory.exists()) {
			uploadDirectory.mkdir();
		}
	}
}

