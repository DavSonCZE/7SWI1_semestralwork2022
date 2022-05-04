package cz.davson.reviewgameapp;

import cz.davson.reviewgameapp.entities.*;
import cz.davson.reviewgameapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@SpringBootApplication
public class ReviewGameAppApplication implements CommandLineRunner {
    @Autowired UserRepository ob;
    public static void main(String[] args) {

        SpringApplication.run(ReviewGameAppApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User("David","Slonka","david@seznam.cz","DavSonCZE");
        User u2 = new User("Daniel","Milián","milián@seznam.cz","Mikilian");

        ob.save(u1);
        ob.save(u2);
    }
}
