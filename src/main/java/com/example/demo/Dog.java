package com.example.demo;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.*;


/**
 * Entity class representing a Dog.
 */
@Setter
@Getter
@Entity
@Table(name = "dogs")
@Data
@NoArgsConstructor
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String breed;

    @Column
    private int age;


    /**
     * Constructs a new Dog with the specified details.
     *
     * @param id the unique identifier for the dog
     * @param name the name of the dog
     * @param breed the breed of the dog
     * @param age the age of the dog
     */
    public Dog(Long id, String name, String breed, int age) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
    }

    // Getters et Setters

}
