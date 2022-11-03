package no.kristiania.enterpriseExam.repos

import no.kristiania.enterpriseExam.models.AnimalEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnimalRepo: JpaRepository<AnimalEntity, Long>