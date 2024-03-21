package br.com.pedro.springbootcognito.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.List;
import java.util.stream.Collectors;

public class CognitoJwtAuthenticationConverter extends JwtAuthenticationConverter {

    public CognitoJwtAuthenticationConverter() {
        setPrincipalClaimName("username");

        setJwtGrantedAuthoritiesConverter(jwt -> {
            List<String> groups = jwt.getClaimAsStringList("cognito:groups");
            return groups != null ? groups.stream()
                    .map(group -> new SimpleGrantedAuthority("ROLE_" + group.toUpperCase()))
                    .collect(Collectors.toList()) : List.of();
        });
    }
}
