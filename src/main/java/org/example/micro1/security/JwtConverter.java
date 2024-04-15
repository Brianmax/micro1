package org.example.micro1.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementPermission;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Component
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Value("${jwt.auth.converter.resource-id}")
    private String resourceId;
    @Value("${jwt.auth.converter.principal.attribute}")
    private String principalAttribute;
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    @Override
    public AbstractAuthenticationToken convert(Jwt token) {
        Collection<GrantedAuthority> authorities = Stream
                .concat(jwtGrantedAuthoritiesConverter.convert(token).stream(), extractResourceRoles(token).stream())
                .toList();

        return new JwtAuthenticationToken(
                token,
                authorities,
                extractPrincipalName(token)
        );
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt)
    {
        Map<String, Object> resourcesAcces;
        Map<String, Object> resource;
        Collection<String> resourceRoles;

        if(jwt.getClaims()==null)
        {
            return Set.of();
        }
        resourcesAcces = (Map<String, Object>) jwt.getClaims().get("resource_access");
        if(resourcesAcces==null)
        {
            return Set.of();
        }
        resource = (Map<String, Object>) resourcesAcces.get(resourceId);
        resourceRoles = (Collection<String>) resource.get("roles");
        return resourceRoles
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role)))
                .collect(Collectors.toSet());
    }
    private String extractPrincipalName(Jwt jwt)
    {
        String claimName = JwtClaimNames.SUB;
        if(principalAttribute!=null)
        {
            claimName = principalAttribute;
        }
        return jwt.getClaimAsString(claimName);
    }
}