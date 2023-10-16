package fr.intellcap.artproject;

import fr.intellcap.artproject.entities.*;
import fr.intellcap.artproject.repositories.ArtistRepo;
import fr.intellcap.artproject.repositories.ClientRepo;
import fr.intellcap.artproject.repositories.CommandRepo;
import fr.intellcap.artproject.repositories.PaintRepo;
import fr.intellcap.artproject.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//
//@Component
@EnableWebMvc
@SpringBootApplication
@EnableSwagger2
@CrossOrigin("*")
public class ArtprojectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ArtprojectApplication.class, args);
    }

    @Autowired
    ClientRepo clientRepo;
    @Autowired
    ArtistRepo artistRepo;
    @Autowired
    CommandRepo commandRepo;
    @Autowired
    PaintRepo paintRepo;
    @Autowired
    ClientService clientService;


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Override
    public void run(String... args) throws Exception {
//        Client client = new Client();
////        client.setUserId(2L);
//        client.setFirstName("SOUFIANE");
//        client.setLastName("AIT TALB");
//        client.setEmail("soufiane.aittalb@gmail.com");
//        client.setAddress("Sidi moumen Casablanca");
//        client.setPassword("soufiane");
//        client.setAge(23L);
//        clientRepo.save(client);
//
//        Artist artist = new Artist();
//        artist.setFirstName("da");
//        artist.setLastName("vinchi");
//        artistRepo.save(artist);
//
//        Paint paint = new Paint();
//        paint.setDescPaint("wonderful");
//        paint.setArtist(artist);
//
//        paintRepo.save(paint);
//
//        Command command=new Command();
//        command.setPaint(paint);
//        command.setClient(client);
//        commandRepo.save(command);

    }
}
