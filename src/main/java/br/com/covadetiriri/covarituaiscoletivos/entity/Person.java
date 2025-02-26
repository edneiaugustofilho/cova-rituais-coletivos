package br.com.covadetiriri.covarituaiscoletivos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Person")
@Table(schema = "public", name = "tb_person")
public class Person extends AuditableEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "whatsapp")
    private String whatsapp;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "mother_name")
    private String motherName;

}
