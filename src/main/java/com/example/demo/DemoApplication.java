// Genere une DemoApplication boo de Spring Boot
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.provider.HibernateUtils;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class DemoApplication {


    // Fonction main qui lance l'application
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        // Créer 3 objet cat et les afficher
        Cat cat1 = new Cat("Mimi", 2, "black");
        Cat cat2 = new Cat("Mina", 3, "white");
        Cat cat3 = new Cat("Momo", 1, "brown");

        System.out.println("Cat1: " + cat1.getName() + " " + cat1.getAge() + " " + cat1.getColor());
        System.out.println("Cat2: " + cat2.getName() + " " + cat2.getAge() + " " + cat2.getColor());
        System.out.println("Cat3: " + cat3.getName() + " " + cat3.getAge() + " " + cat3.getColor());


        // Créer une collectiond e chat et les afficher
        List<Cat> cats = new ArrayList<>();
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);

        for (Cat cat : cats) {
            System.out.println("Cat: " + cat.getName() + " " + cat.getAge() + " " + cat.getColor());
        }

        // genere 10 chats de couleurs différentes entre black, white et brown puis filtre que les white
        List<Cat> cats2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String color = i % 3 == 0 ? "black" : i % 3 == 1 ? "white" : "brown";
            cats2.add(new Cat("Cat" + i, i, color));
        }
        // affiche les chats blancs
        cats2.stream().filter(cat -> cat.getColor().equals("white")).forEach(cat -> System.out.println("Cat: " + cat.getName() + " " + cat.getAge() + " " + cat.getColor()));

        // ecris  avec la libJakarta et SQLite  pour enregistrer mes cats en abse de données






    }

}
