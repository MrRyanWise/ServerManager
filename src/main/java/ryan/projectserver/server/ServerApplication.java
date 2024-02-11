package ryan.projectserver.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import ryan.projectserver.server.model.Server;
import ryan.projectserver.server.repo.ServerRepo;
import java.util.Arrays;

import static ryan.projectserver.server.enumeration.Status.SERVER_DOWN;
import static ryan.projectserver.server.enumeration.Status.SERVER_UP;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return args ->{
			serverRepo.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Personal PC",
					"http://localhost:8080/server/images/server1.png", SERVER_UP));
			serverRepo.save(new Server(null, "54.167.117.14", "Fedora Linux", "32 GB", "Dell Tower",
					"http://localhost:8080/server/images/server2.png", SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.63", "AWS Instance", "64 GB", "Web Server",
					"http://localhost:8080/server/images/server3.png", SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.64", "M5 2008", "128 GB", "Web Server",
					"http://localhost:8080/server/images/server4.png", SERVER_DOWN));
		};
	}

	@Bean
	public CorsFilter corsFilter(){
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin","Access-Control-Allow-Origin","Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
	 	return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
