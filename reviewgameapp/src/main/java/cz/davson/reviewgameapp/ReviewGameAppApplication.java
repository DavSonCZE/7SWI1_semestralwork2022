package cz.davson.reviewgameapp;

import cz.davson.reviewgameapp.entities.*;
import cz.davson.reviewgameapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@SpringBootApplication
public class ReviewGameAppApplication{
    public static void main(String[] args) {

        SpringApplication.run(ReviewGameAppApplication.class, args);

    }
}
