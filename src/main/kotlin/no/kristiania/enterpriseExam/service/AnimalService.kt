package no.kristiania.enterpriseExam.service

import no.kristiania.enterpriseExam.models.AnimalEntity
import no.kristiania.enterpriseExam.repos.AnimalRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class AnimalService(@Autowired private val animalRepo: AnimalRepo) {

    fun getAnimals(): List<AnimalEntity> {
        return animalRepo.findAll()
    }

    fun addAnimal(animal: AnimalEntity): AnimalEntity {
        return animalRepo.save(animal)
    }

    fun createAnimal(animal: RegisterAnimalDTO): AnimalEntity {
        val newAnimal = AnimalEntity(animalType = animal.animalType, animal_name = animal.animalName, vaccinated = animal.vaccinated, birthDate = animal.birthDate, cageNr = animal.cageNr)
        return animalRepo.save(newAnimal)
    }

    fun getAnimalById(animalId: Long): Optional<AnimalEntity> {
        return animalRepo.findById(animalId)
    }

    fun updateAnimalById(id: Long, animal: AnimalEntity): AnimalEntity {
        return animalRepo.save(
            AnimalEntity(
                animalId = id,
                animalType = animal.animalType,
                animal_name = animal.animal_name,
                vaccinated = animal.vaccinated,
                birthDate = animal.birthDate,
                registered = animal.registered,
                cageNr = animal.cageNr
            )
        )
    }

    fun deleteAnimalById(id: Long): ResponseEntity<String> {
        return if (animalRepo.existsById(id)) {
            val response = animalRepo.deleteById(id)
            ResponseEntity.ok("Deleted")
        } else {
            ResponseEntity.notFound().build()
        }
    }
}

data class RegisterAnimalDTO(val animalType: String, val animalName: String, val vaccinated: Boolean, val birthDate: LocalDate, val cageNr: Int)