package no.kristiania.enterpriseExam.unittests

import io.mockk.every
import io.mockk.mockk
import no.kristiania.enterpriseExam.models.AnimalEntity
import no.kristiania.enterpriseExam.service.AnimalService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import java.time.LocalDate
import java.time.Month
import java.util.*


@ExtendWith(SpringExtension::class)
@WebMvcTest
@ComponentScan("module-service")
@AutoConfigureMockMvc(addFilters = false)
class AnimalControllerUnitTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var animalService: AnimalService


    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun animalService() = mockk<AnimalService>()
    }



    @Test
    fun testShelterAddPetEndpoint() {
        val animalTwix = AnimalEntity(1, "cat", "twix", true, LocalDate.of(2022, Month.JANUARY, 1), LocalDate.now(), 678)
        every { animalService.addAnimal(animalTwix) } answers {
            val animal = AnimalEntity(animalId = 1, animalType = "cat", animal_name = "Fluffy", vaccinated = true, birthDate = LocalDate.of(2022, Month.JANUARY, 1), registered = LocalDate.now(), cageNr = 678)
            animal
        }

        mockMvc.post("/api/shelter/addPet"){
            contentType = MediaType.APPLICATION_JSON
            content = "{\n" +
                    "    \"animalType\": \"cat\",\n" +
                    "    \"animal_name\": \"Fluffy\",\n" +
                    "    \"vaccinated\": true,\n" +
                    "    \"birthDate\": \"2022-01-01\",\n" +
                    "    \"cageNr\": 678\n" +
                    "}"
        }
            .andExpect { status { isCreated() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
    }

    @Test
    fun testShelterAllEndpoint() {
        val animalTwix = AnimalEntity(1, "cat", "twix", true, LocalDate.of(2022, Month.JANUARY, 1), LocalDate.now(), 678)
        val animalPuppy = AnimalEntity(2, "dog", "puppy", true, LocalDate.of(2021, Month.DECEMBER, 25), LocalDate.now(), 888)

        every { animalService.getAnimals() }  answers {
            listOf(animalTwix, animalPuppy)
        }

        mockMvc.get("/api/shelter/all") {
        }

            .andExpect { status { isOk() } }
            .andExpect {
                jsonPath("$") { isArray()}
            }
    }

    @Test
    fun testGetShelterIDEndpoint() {
        val animalTwix = AnimalEntity(1, "cat", "Fluffy", true, LocalDate.of(2022, Month.JANUARY, 1), LocalDate.now(), 678)

        every { animalService.getAnimalById(animalTwix.animalId!!) } returns Optional.of(animalTwix)

        mockMvc.get("/api/shelter/${animalTwix.animalId}"){
        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
    }

    @Test
    fun testPutShelterIdEndpoint() {
        val animalTwix = AnimalEntity(1, "cat", "Fluffy", true, LocalDate.of(2022, Month.JANUARY, 1), LocalDate.now(), 678)
        val animalPuppy = AnimalEntity(2, "dog", "puppy", true, LocalDate.of(2021, Month.DECEMBER, 25), LocalDate.now(), 888)
        every { animalService.updateAnimalById(animalTwix.animalId!!, animalPuppy) } answers {
                       animalPuppy
        }
        mockMvc.put("/api/shelter/${animalTwix.animalId}") {
            contentType = MediaType.APPLICATION_JSON
            content = "{\n" +
                    "    \"animalType\": \"cat\",\n" +
                    "    \"animal_name\": \"puppy\",\n" +
                    "    \"vaccinated\": true,\n" +
                    "    \"birthDate\": \"2021-12-25\",\n" +
                    "    \"cageNr\": 888\n" +
                    "}"
        }
            .andExpect { status { isCreated() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
    }
}