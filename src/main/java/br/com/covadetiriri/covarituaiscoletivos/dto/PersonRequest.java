package br.com.covadetiriri.covarituaiscoletivos.dto;

import br.com.covadetiriri.covarituaiscoletivos.entity.Person;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record PersonRequest(
        @NotNull
        @Size(min = 5, message = "O nome deve conter no minimo 5 caracteres")
        String fullName,
        @NotNull
        @Email
        String email,
        @Past
        LocalDate birthDate,
        @NotNull
        @Size(min = 8, message = "O telefone deve conter no minimo 8 caracteres")
        String whatsapp,
        @NotNull
        @Size(min = 30, message = "O endereço deve conter no minimo 30 caracteres")
        String fullAddress,
        @NotNull
        @Size(min = 30, message = "O nome da mae deve conter no minimo 30 caracteres")
        String motherName
) {
    public static Person asEntity(@NotNull PersonRequest person) {
        return Person.builder().
                fullName(person.fullName()).
                email(person.email()).
                birthDate(person.birthDate()).
                whatsapp(person.whatsapp()).
                fullAddress(person.fullAddress()).
                motherName(person.motherName()).
                build();
    }
}
