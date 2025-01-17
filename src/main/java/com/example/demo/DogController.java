package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing dogs.
 */
@RestController
@RequestMapping("/api/dogs")
public class DogController {

    private final DogRepository dogRepository;

    /**
     * Constructor for DogController.
     *
     * @param dogRepository the repository for managing dog entities
     */
    @Autowired
    public DogController(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    /**
     * GET /api/dogs : Get all dogs.
     *
     * @return the list of all dogs
     */
    @GetMapping
    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    /**
     * GET /api/dogs/{id} : Get a dog by ID.
     *
     * @param id the ID of the dog to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dog, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable Long id) {
        Optional<Dog> dog = dogRepository.findById(id);
        return dog.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /api/dogs : Create a new dog.
     *
     * @param dog the dog to create
     * @return the created dog
     */
    @PostMapping
    public Dog createDog(@RequestBody Dog dog) {
        return dogRepository.save(dog);
    }

    /**
     * PUT /api/dogs/{id} : Update an existing dog.
     *
     * @param id the ID of the dog to update
     * @param updatedDog the updated dog data
     * @return the ResponseEntity with status 200 (OK) and with body the updated dog, or with status 404 (Not Found)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Dog> updateDog(@PathVariable Long id, @RequestBody Dog updatedDog) {
        return dogRepository.findById(id)
                .map(dog -> {
                    dog.setName(updatedDog.getName());
                    dog.setBreed(updatedDog.getBreed());
                    dog.setAge(updatedDog.getAge());
                    dogRepository.save(dog);
                    return ResponseEntity.ok(dog);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * DELETE /api/dogs/{id} : Delete a dog by ID.
     *
     * @param id the ID of the dog to delete
     * @return the ResponseEntity with status 204 (No Content) if the dog was deleted, or with status 404 (Not Found)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDog(@PathVariable Long id) {
        if (dogRepository.existsById(id)) {
            dogRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}