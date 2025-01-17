package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DogRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DogRepository dogRepository;

    @Test
    void testSaveDog() {
        Dog dog = new Dog(null, "Rex", "Berger Allemand", 5);
        Dog savedDog = dogRepository.save(dog);

        assertNotNull(savedDog.getId());
        assertEquals("Rex", savedDog.getName());
    }

    @Test
    void testFindById() {
        Dog dog = new Dog(null, "Max", "Labrador", 3);
        dog = entityManager.persist(dog);

        Optional<Dog> found = dogRepository.findById(dog.getId());

        assertTrue(found.isPresent());
        assertEquals("Max", found.get().getName());
    }

    @Test
    void testFindAll() {
        Dog dog1 = new Dog(null, "Rex", "Berger Allemand", 5);
        Dog dog2 = new Dog(null, "Max", "Labrador", 3);

        entityManager.persist(dog1);
        entityManager.persist(dog2);

        List<Dog> dogs = dogRepository.findAll();

        assertEquals(2, dogs.size());
    }

    @Test
    void testDeleteById() {
        Dog dog = new Dog(null, "Rex", "Berger Allemand", 5);
        dog = entityManager.persist(dog);

        dogRepository.deleteById(dog.getId());

        Optional<Dog> found = dogRepository.findById(dog.getId());
        assertFalse(found.isPresent());
    }
}
