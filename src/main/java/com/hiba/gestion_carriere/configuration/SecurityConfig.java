package com.hiba.gestion_carriere.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public").permitAll() // Accès public
                        .requestMatchers("/rh").hasRole("VIEW-PROFILE")
                        .requestMatchers("/admin").hasRole("MANAGE-ACCOUNT")
                        .anyRequest().authenticated() // Toutes les autres requêtes nécessitent une authentification
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()) // Convertir les rôles du JWT
                        )
                );

        return http.build();
    }

    // Configurer la conversion des rôles du JWT
   /* private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_"); // Préfixe des rôles
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles"); // Nom de la claim contenant les rôles

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
*/

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
            grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_"); // Ajout du préfixe ROLE_
            grantedAuthoritiesConverter.setAuthoritiesClaimName("realm_access.roles"); // Vérification des rôles dans realm_access

            var authorities = new HashSet<>(grantedAuthoritiesConverter.convert(jwt));

            // Extraction des rôles depuis resource_access.account.roles
            Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
            if (resourceAccess != null && resourceAccess.containsKey("account")) {
                Map<String, Object> accountRoles = (Map<String, Object>) resourceAccess.get("account");
                if (accountRoles != null && accountRoles.containsKey("roles")) {
                    List<String> roles = (List<String>) accountRoles.get("roles");
                    roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())));
                }
            }

            return authorities;
        });

        return jwtAuthenticationConverter;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("http://localhost:8080/realms/gestion-carriere/protocol/openid-connect/certs").build();
    }
}