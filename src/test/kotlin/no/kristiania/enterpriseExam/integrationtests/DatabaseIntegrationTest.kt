package no.kristiania.enterpriseExam.integrationtests

import no.kristiania.enterpriseExam.models.AnimalEntity
import no.kristiania.enterpriseExam.repos.AnimalRepo
import no.kristiania.enterpriseExam.service.AnimalService
import no.kristiania.enterpriseExam.service.RegisterUserDTO
import no.kristiania.enterpriseExam.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate
import java.time.Month

@DataJpaTest
@ActiveProfiles("test")
@Import(UserService::class)
class DatabaseIntegrationTest {

    @Autowired
    private lateinit var animalService: AnimalService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var animalRepo: AnimalRepo

    @Test
    fun createAndFindUserTest(){
        userService.registerUser(RegisterUserDTO("jim@jimbob.com", "pirate"))
        val createdUser = userService.loadUserByUsername("jim@jimbob.com")
        assert(createdUser.username == "jim@jimbob.com")
        assert(createdUser.authorities.first().authority == "USER")
    }

    @Test
    fun saveAndRetrieveAnimal() {
        val animal = animalRepo.save(AnimalEntity(1, "cat", "twix", true, LocalDate.of(2022, Month.JANUARY, 1), LocalDate.now(), 678))
        val foundAnimal = animalRepo.getById(animal.animalId!!)

        assert(animal.animalType == foundAnimal.animalType)
    }

}