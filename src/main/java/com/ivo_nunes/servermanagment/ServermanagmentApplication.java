package com.ivo_nunes.servermanagment;

import com.ivo_nunes.servermanagment.enumeration.Status;
import com.ivo_nunes.servermanagment.model.Server;
import com.ivo_nunes.servermanagment.repo.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ServermanagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServermanagmentApplication.class, args);
	}

  @Bean
  CommandLineRunner run(ServerRepository serverRepository){
    return args -> {
      // Just a comment to erase this after finish ok!
      serverRepository.save(new Server(null,"192.168.1.160", "Ubuntu Linux", "16GB", "Personal PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
      serverRepository.save(new Server(null,"192.168.1.161", "Fedora Linux", "16GB", "Personal SERVER", "http://localhost:8080/server/image/server1.png", Status.SERVER_DOWN));
      serverRepository.save(new Server(null,"192.168.1.162", "Ubuntu Linux", "16GB", "Professional PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
      serverRepository.save(new Server(null,"192.168.1.163", "Test Linux", "16GB", "Professional PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_DOWN));
    };
  }
}
