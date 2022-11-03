package no.kristiania.enterpriseExam.unittests

import io.mockk.every
import io.mockk.mockk
import no.kristiania.enterpriseExam.models.AnimalEntity
import no.kristiania.enterpriseExam.repos.AnimalRepo
import no.kristiania.enterpriseExam.service.AnimalService
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity
import java.time.LocalDate
import java.time.Month
import java.util.*

class AnimalServiceUnitTest {

    private val animalRepo = mockk<AnimalRepo>()
    private val animalService = AnimalService(animalRepo)

    @Test
    fun getAllAnimals(){
        val animalTwix = AnimalEntity(1, "cat", "twix", true, LocalDate.of(2022, Month.JANUARY, 1), LocalDate.now(), 678)
        val animalPuppy = AnimalEntity(2, "dog", "puppy", true, LocalDate.of(2021, Month.DECEMBER, 25), LocalDate.now(), 888)

        every { animalRepo.findAll() } returns listOf(animalTwix, animalPuppy)

        val animalList = animalService.getAnimals()
        assert(animalList.size == 2)
        assert(animalList[0].animalId?.equals(1L) == true)
        assert(animalList[0].vaccinated == true)
        assert(animalList[0].animal_name.equals("twix"))
        assert(animalList[1].animalType.equals("dog"))
        assert(animalList[1].cageNr?.equals(888) == true)
    }

    @Test
    fun addAnimal() {
        every { animalRepo.save(any()) } answers {
            firstArg()
        }
        val animalTwix = AnimalEntity(1, "cat", "twix", true, LocalDate.of(2022, Month.JANUARY, 1), LocalDate.now(), 678)
        assert(animalTwix.animal_name.equals("twix"))
    }

    @Test
    fun updateShelter() {
        val prevAnimal = AnimalEntity(1, "cat", "twix", true, LocalDate.of(2022, Month.JANUARY, 1), LocalDate.now(), 678)
        val newAnimal = AnimalEntity(1, "cat", "Fluffy", true, LocalDate.of(2022, Month.JANUARY, 1), LocalDate.now(), 777)

        every { animalRepo.findAll() } returns listOf(prevAnimal)

        val animalList = animalService.getAnimals()
        every { animalService.updateAnimalById(animalList[0].animalId!!, newAnimal) } answers {
            firstArg()
        }
        assert(newAnimal.animal_name.equals("Fluffy"))
    }

    @Test
    fun deleteAnimal() {
        val animalTwix = AnimalEntity(1, "cat", "twix", true, LocalDate.of(2022, Month.JANUARY, 1), LocalDate.now(), 678)
        val animalPuppy = AnimalEntity(2, "dog", "puppy", true, LocalDate.of(2021, Month.DECEMBER, 25), LocalDate.now(), 888)
        every { animalRepo.findAll() } returns listOf(animalPuppy, animalTwix)

        val animalList = animalService.getAnimals()

        every { animalService.deleteAnimalById(animalList[0].animalId!!) } answers {
            ResponseEntity.ok("Deleted")
        }

        assert(animalList[0].animal_name.equals("puppy"))



    }



}