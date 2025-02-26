package br.com.covadetiriri.covarituaiscoletivos.service;

import br.com.covadetiriri.covarituaiscoletivos.dto.AuthenticationRequest;
import br.com.covadetiriri.covarituaiscoletivos.dto.AuthenticationResponse;
import br.com.covadetiriri.covarituaiscoletivos.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public LoginService(TokenService tokenService,
                        AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.email(), authenticationRequest.password()
        );
        var auth = authenticationManager.authenticate(usernamePasswordToken);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new AuthenticationResponse(token);
    }
}
