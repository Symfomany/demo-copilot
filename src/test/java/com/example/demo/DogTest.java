package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    @Test
    void testDogCreation() {
        Dog dog = new Dog(1L, "Rex", "Berger Allemand", 5);

        assertEquals(1L, dog.getId());
        assertEquals("Rex", dog.getName());
        assertEquals("Berger Allemand", dog.getBreed());
        assertEquals(5, dog.getAge());
    }

    @Test
    void testNoArgsConstructor() {
        Dog dog = new Dog();

        assertNull(dog.getId());
        assertNull(dog.getName());
        assertNull(dog.getBreed());
        assertEquals(0, dog.getAge());
    }

    @Test
    void testSetters() {
        Dog dog = new Dog();

        dog.setId(2L);
        dog.setName("Max");
        dog.setBreed("Labrador");
        dog.setAge(3);

        assertEquals(2L, dog.getId());
        assertEquals("Max", dog.getName());
        assertEquals("Labrador", dog.getBreed());
        assertEquals(3, dog.getAge());
    }

    @Test
    void testEqualsAndHashCode() {
        Dog dog1 = new Dog(1L, "Rex", "Berger Allemand", 5);
        Dog dog2 = new Dog(1L, "Rex", "Berger Allemand", 5);
        Dog dog3 = new Dog(2L, "Max", "Labrador", 3);

        assertEquals(dog1, dog2);
        assertNotEquals(dog1, dog3);
        assertEquals(dog1.hashCode(), dog2.hashCode());
    }

    @Test
    void testToString() {
        Dog dog = new Dog(1L, "Rex", "Berger Allemand", 5);
        String toString = dog.toString();

        assertTrue(toString.contains("Rex"));
        assertTrue(toString.contains("Berger Allemand"));
        assertTrue(toString.contains("5"));
    }
}
