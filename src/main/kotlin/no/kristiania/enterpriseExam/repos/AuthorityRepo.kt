package no.kristiania.enterpriseExam.repos

import no.kristiania.enterpriseExam.models.AuthorityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepo: JpaRepository<AuthorityEntity, Long> {

    fun findByName(authority: String?): AuthorityEntity
}