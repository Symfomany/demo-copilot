package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class DogControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DogRepository dogRepository;

    @InjectMocks
    private DogController dogController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(dogController).build();
    }

    @Test
    void getAllDogs() throws Exception {
        Dog dog1 = new Dog(1L, "Rex", "Berger Allemand", 5);
        Dog dog2 = new Dog(2L, "Max", "Labrador", 3);
        when(dogRepository.findAll()).thenReturn(Arrays.asList(dog1, dog2));

        mockMvc.perform(get("/api/dogs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Rex"))
                .andExpect(jsonPath("$[1].name").value("Max"));
    }

    @Test
    void getDogById() throws Exception {
        Dog dog = new Dog(1L, "Rex", "Berger Allemand", 5);
        when(dogRepository.findById(1L)).thenReturn(Optional.of(dog));

        mockMvc.perform(get("/api/dogs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rex"));
    }

    @Test
    void createDog() throws Exception {
        Dog dog = new Dog(1L, "Rex", "Berger Allemand", 5);
        when(dogRepository.save(any(Dog.class))).thenReturn(dog);

        mockMvc.perform(post("/api/dogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Rex\",\"breed\":\"Berger Allemand\",\"age\":5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rex"));
    }

    @Test
    void updateDog() throws Exception {
        Dog existingDog = new Dog(1L, "Rex", "Berger Allemand", 5);
        Dog updatedDog = new Dog(1L, "Rex", "Berger Allemand", 6);
        when(dogRepository.findById(1L)).thenReturn(Optional.of(existingDog));
        when(dogRepository.save(any(Dog.class))).thenReturn(updatedDog);

        mockMvc.perform(put("/api/dogs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Rex\",\"breed\":\"Berger Allemand\",\"age\":6}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(6));
    }

    @Test
    void deleteDog() throws Exception {
        when(dogRepository.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/dogs/1"))
                .andExpect(status().isNoContent());

        verify(dogRepository, times(1)).deleteById(1L);
    }
}