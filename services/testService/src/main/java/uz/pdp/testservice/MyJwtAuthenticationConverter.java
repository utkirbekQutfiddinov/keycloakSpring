package uz.pdp.testservice;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.Map;

public class MyJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Map<String, Object> claims = jwt.getClaims();

        LinkedTreeMap<String,Object> resource_access= (LinkedTreeMap<String, Object>) claims.get("resource_access");

        LinkedTreeMap<String,Object> testResource= (LinkedTreeMap<String, Object>) resource_access.get("test");

        List<String> roles= (List<String>) testResource.get("roles");

        List<SimpleGrantedAuthority> grantedAuthorities = roles.stream().map(role -> new SimpleGrantedAuthority(role)).toList();

        return new JwtAuthenticationToken(jwt,grantedAuthorities);
    }
}
