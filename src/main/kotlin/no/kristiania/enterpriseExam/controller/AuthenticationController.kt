package no.kristiania.enterpriseExam.controller

import no.kristiania.enterpriseExam.models.AuthorityEntity
import no.kristiania.enterpriseExam.models.UserEntity
import no.kristiania.enterpriseExam.service.RegisterUserDTO
import no.kristiania.enterpriseExam.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api")
class AuthenticationController(@Autowired private val userService: UserService) {

    @GetMapping("/user/all")
    fun getUsers(): ResponseEntity<List<UserEntity>> {
        return ResponseEntity.ok().body(userService.getUsers())
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody newUser: RegisterUserDTO): ResponseEntity<UserEntity> {
        val user = userService.registerUser(newUser)
        val uri =
            URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/register/user").toUriString())
        return ResponseEntity.created(uri).body(user)
    }

    @PostMapping("/authority/create")
    fun registerAuthority(@RequestBody authority: AuthorityEntity): ResponseEntity<AuthorityEntity> {
        val authorityEntity = userService.createAuthority(authority)
        val uri = URI.create(
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/register/authority").toUriString()
        )
        return ResponseEntity.created(uri).body(authorityEntity)
    }

    @GetMapping("/authority/all")
    fun getAuthorities(): ResponseEntity<List<String?>> {
        return ResponseEntity.ok().body(userService.getAuthorities())
    }

    @PostMapping("/authority/addToUser")
    fun addAuthorityToUser(@RequestBody authorityToUser: AuthorityToUser): ResponseEntity<Any> {
        userService.grantAuthorityToUser(authorityToUser.email, authorityToUser.authority)
        return ResponseEntity.ok().build()
    }

}

data class AuthorityToUser(val email: String?, val authority: String?)

