package no.kristiania.enterpriseExam.repos

import no.kristiania.enterpriseExam.models.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo: JpaRepository<UserEntity, Long> {

    fun findByEmail(email: String?): UserEntity
}