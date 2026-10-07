package com.workintech.fswebs17d1.controller;
import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerFullname;

    private Map<Integer, Animal> animals;

    @PostConstruct
    public void init() {
        animals = new HashMap<>();
    }

    // Değerlerin okunduğunu test etmek için opsiyonel endpoint
    @GetMapping("/info")
    public String getInfo() {
        return "Course: " + courseName + " | Developer: " + developerFullname;
    }

    // [GET] /workintech/animal => Tüm animal map'inin value değerlerini List olarak döner
    @GetMapping
    public List<Animal> findAll() {
        return new ArrayList<>(animals.values());
    }

    // [GET] /workintech/animal/{id} => İlgili id'deki animal map'te varsa value değerini döner
    @GetMapping("/{id}")
    public Animal findById(@PathVariable("id") int id) {
        return animals.get(id);
    }

    // [POST] /workintech/animal => Integer id ve String name değerlerini alır ve animals map'ine ekler
    @PostMapping
    public Animal save(@RequestBody Animal animal) {
        animals.put(animal.getId(), animal);
        return animal;
    }

    // [PUT] /workintech/animal/{id} => İlgili id'deki map değerini günceller
    @PutMapping("/{id}")
    public Animal update(@PathVariable("id") int id, @RequestBody Animal animal) {
        animals.put(id, new Animal(id, animal.getName()));
        return animals.get(id);
    }

    // [DELETE] /workintech/animal/{id} => İlgili id değerini map'ten siler
    @DeleteMapping("/{id}")
    public Animal delete(@PathVariable("id") int id) {
        return animals.remove(id);
    }
}