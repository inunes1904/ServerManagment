package com.ivo_nunes.servermanagment;

import com.ivo_nunes.servermanagment.enumeration.Status;
import com.ivo_nunes.servermanagment.model.Server;
import com.ivo_nunes.servermanagment.repo.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


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
//STUDY TIME NOW
  @Bean
  public CorsFilter corsFilter(){
    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200")) ;
    corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
      "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
      "Access-Control-Request-Method", "Access-Control-Request-Headers"));
    corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
      "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    urlBasedCorsConfigurationSource.registerCorsConfiguration( "/**", corsConfiguration);
    return new CorsFilter(urlBasedCorsConfigurationSource);
}
}
