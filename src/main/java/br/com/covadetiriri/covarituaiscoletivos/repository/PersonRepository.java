package br.com.covadetiriri.covarituaiscoletivos.repository;

import br.com.covadetiriri.covarituaiscoletivos.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person getByEmail(String email);

    boolean existsByEmail(String email);

    @Query(value = "select p.id from tb_person p where p.email = :email", nativeQuery = true)
    UUID getIdByEmail(String email);

    @Query(value = "select " +
            "case when count(p.id) > 0 then true else false end " +
            "from tb_person p " +
            "where p.email = :email " +
            "and p.id != :id", nativeQuery = true)
    boolean existsAnotherWithSameEmail(UUID id, String email);
}
