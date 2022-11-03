package no.kristiania.enterpriseExam.models

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "animals")
class AnimalEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id", nullable = false)
    val animalId: Long? = 0,

    @Column(name = "animal_type")
    val animalType: String?,

    @Column(name = "animal_name")
    val animal_name: String?,

    @Column(name = "vaccinated")
    val vaccinated: Boolean?,

    @Column(name = "date_of_birth")
    val birthDate: LocalDate?,

    @Column(name = "registered")
    val registered: LocalDate? = LocalDate.now(),

    @Column(name = "cage_nr")
    val cageNr: Int?


) {

    override fun toString(): String {
        return "AnimalEntity(animalId=$animalId, animalType=$animalType, animal_name=$animal_name, vaccinated=$vaccinated, birthDate=$birthDate, registered=$registered, cageNr=$cageNr)"
    }
}