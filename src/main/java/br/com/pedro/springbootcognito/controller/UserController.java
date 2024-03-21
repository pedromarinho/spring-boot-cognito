package br.com.pedro.springbootcognito.controller;

import br.com.pedro.springbootcognito.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<UserDTO> me() {
        var authUser = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(
                new UserDTO(authUser.getName(), authUser.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())), HttpStatus.OK);
    }
}
