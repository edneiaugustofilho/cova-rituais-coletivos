package br.com.covadetiriri.covarituaiscoletivos.dto;

import br.com.covadetiriri.covarituaiscoletivos.entity.User;
import br.com.covadetiriri.covarituaiscoletivos.entity.UserRole;

public record UserRequest(String name, String email, String password, UserRole role) {

    public User asEntitty() {
        return new User(name, email, password, role);
    }

}
