package no.kristiania.enterpriseExam.unittests

import io.mockk.every
import io.mockk.mockk
import no.kristiania.enterpriseExam.models.AuthorityEntity
import no.kristiania.enterpriseExam.models.UserEntity
import no.kristiania.enterpriseExam.repos.AuthorityRepo
import no.kristiania.enterpriseExam.repos.UserRepo
import no.kristiania.enterpriseExam.service.RegisterUserDTO
import no.kristiania.enterpriseExam.service.UserService
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UserServiceUnitTest {

    private val userRepo = mockk<UserRepo>()
    private val authorityRepo = mockk<AuthorityRepo>()
    private val userService = UserService(userRepo, authorityRepo)

    @Test
    fun getUsers(){
        val userOne = UserEntity(1, "jim@bob.com", password = "pirate")
        val userTwo = UserEntity(2,  email = "joe@bob.com", password = "pirate")

        every { userRepo.findAll() } returns listOf(userOne, userTwo)

        val userList = userService.getUsers()
        assert(userList.size == 2)
        assert(userList[0].userId?.equals(1L) == true)
        assert(userList[1].email.equals("joe@bob.com"))
    }

    @Test
    fun registerUserTest(){
        every { userRepo.save(any()) } answers {
            firstArg()
        }
        val createdUser = userService.registerUser(RegisterUserDTO("jim@bob.com", "pirate"))
        assert(createdUser.email.equals("jim@bob.com"))
    }

    @Test
    fun createAuthority(){
        every { authorityRepo.save(any()) } answers {
            firstArg()
        }
        val createdAuthority = userService.createAuthority(AuthorityEntity(name = "ADMIN"))
        assert(createdAuthority.name.equals("ADMIN"))
    }

    @Test
    fun grantAuthorityToUserTest(){

        every { authorityRepo.findByName(any()) } returns AuthorityEntity(1, "ADMIN")
        every { userRepo.findByEmail(any()) } answers {
            val user = UserEntity(1, "jim@bob.com", LocalDateTime.now(), "pirate", true )
            user.authorities.add(AuthorityEntity(1, "ADMIN"))
            user
        }

        every { userRepo.save(any()) } answers {
            val user = UserEntity(1, "jim@bob.com", LocalDateTime.now(), "pirate", true)
            user.authorities.add(AuthorityEntity(1, "ADMIN"))
            user
        }

        val createdUser = userService.registerUser(RegisterUserDTO("jim@bob.com", "pirate"))
        userService.grantAuthorityToUser(createdUser.email, "ADMIN")
        assert(userRepo.findByEmail("jim@bob.com").authorities[0].name == "ADMIN")

    }
}