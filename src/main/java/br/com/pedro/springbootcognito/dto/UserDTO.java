package br.com.pedro.springbootcognito.dto;

import java.util.List;

public record UserDTO(String username, List<String> roles) {
}
