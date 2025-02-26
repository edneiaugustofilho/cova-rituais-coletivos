package br.com.covadetiriri.covarituaiscoletivos.service;

import br.com.covadetiriri.covarituaiscoletivos.entity.Person;
import br.com.covadetiriri.covarituaiscoletivos.repository.PersonRepository;
import br.com.covadetiriri.covarituaiscoletivos.util.AgeValidator;
import br.com.covadetiriri.covarituaiscoletivos.util.ContextUtil;
import br.com.covadetiriri.covarituaiscoletivos.util.EmailAddressValidator;
import br.com.covadetiriri.covarituaiscoletivos.util.NameValidator;
import ch.qos.logback.core.joran.action.PreconditionValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public boolean existsByEmail(String email) {
        return personRepository.existsByEmail(email);
    }

    public Person getByEmail(String email) {
        return personRepository.getByEmail(email);
    }

    public UUID getIdByEmail(String email) {
        return personRepository.getIdByEmail(email);
    }

    private void validate(Person person) {
        if (!NameValidator.isValidFullName(person.getFullName())) {
            throw new IllegalArgumentException("Nome completo deve ser nome e sobrenome.");
        }

        if (!EmailAddressValidator.isValid(person.getEmail())) {
            throw new IllegalArgumentException("E-mail deve ser válido.");
        }

        if (person.getBirthDate() == null) {
            throw new IllegalArgumentException("Data de nascimento é obrigatória.");
        }

        if (!AgeValidator.isMajorOf18(person.getBirthDate())) {
            throw new IllegalArgumentException("Inscrito deve ter pelo menos 18 anos.");
        }

        if (person.getWhatsapp() == null || person.getWhatsapp().isEmpty()) {
            throw new IllegalArgumentException("WhatsApp é obrigatório.");
        }

        if (person.getFullAddress() == null || person.getFullAddress().isEmpty()) {
            throw new IllegalArgumentException("Endereço completo é obrigatório.");
        }

        if (person.getFullAddress().length() < 30) {
            throw new IllegalArgumentException("Endereço completo deve ter pelo menos 30 caracteres.");
        }

        if (!NameValidator.isValidFullName(person.getMotherName())) {
            throw new IllegalArgumentException("Nome da mãe deve ter nome e sobrenome.");
        }
    }

    public void validadeIfNotExists(String email) {
        if (personRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }
    }

    public void validadeIfNotExists(String email, UUID id) {
        if (personRepository.existsAnotherWithSameEmail(id, email)) {
            throw new IllegalArgumentException("Email já cadastrado para outra pessoa.");
        }
    }

    public Person create(Person person) {
        validate(person);
        validadeIfNotExists(person.getEmail());

        person.setCreatedAt(LocalDateTime.now());
        person.setCreatedBy(ContextUtil.getCurrentUsername());

        return personRepository.save(person);
    }

    public void update(Person person) {
        validate(person);
        validadeIfNotExists(person.getEmail(), person.getId());

        person.setUpdatedAt(LocalDateTime.now());
        person.setUpdatedBy(ContextUtil.getCurrentUsername());

        personRepository.save(person);
    }
}
