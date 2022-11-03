package no.kristiania.enterpriseExam.controller

import no.kristiania.enterpriseExam.models.AnimalEntity
import no.kristiania.enterpriseExam.service.AnimalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
class AnimalController(@Autowired(required=true) private val animalService: AnimalService) {

    @GetMapping("/shelter/all")
    fun getAnimals(): ResponseEntity<List<AnimalEntity>> {
        val animals = animalService.getAnimals()
        return ResponseEntity.ok().body(animals)
    }

    @PostMapping("/shelter/new")
    fun addAnimal(@RequestBody animal: AnimalEntity): AnimalEntity {
        return animalService.addAnimal(animal)
    }

    @GetMapping("/shelter/{id}")
    fun getAnimalById(@PathVariable("id") animalId: Long): ResponseEntity<Optional<AnimalEntity>> {
        val animal = animalService.getAnimalById(animalId)
        return ResponseEntity.ok().body(animal)
    }

    @PutMapping("/shelter/{id}")
    fun updateAnimalById(@PathVariable("id") animalId: Long, @RequestBody animal: AnimalEntity): AnimalEntity {
        return animalService.updateAnimalById(animalId, animal)
    }

    @DeleteMapping("/shelter/{id}")
    fun deleteAnimalById(@PathVariable("id") animalId: Long): Unit {
        animalService.deleteAnimalById(animalId)
    }


}