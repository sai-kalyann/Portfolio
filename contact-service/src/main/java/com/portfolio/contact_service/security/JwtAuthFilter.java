package com.portfolio.contact_service.security;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final byte[] secret;

    public JwtAuthFilter() {
        String s = System.getenv().getOrDefault("JWT_SECRET", "dev-secret-change-me");
        this.secret = s.getBytes();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                SignedJWT jwt = SignedJWT.parse(token);
                if (jwt.getHeader().getAlgorithm().equals(JWSAlgorithm.HS256) && jwt.verify(new MACVerifier(secret))) {
                    String subject = jwt.getJWTClaimsSet().getSubject();
                    List<String> roles = (List<String>) jwt.getJWTClaimsSet().getClaim("roles");
                    List<SimpleGrantedAuthority> authorities = roles == null ? List.of() : roles.stream()
                            .filter(Objects::nonNull)
                            .map(r -> new SimpleGrantedAuthority("ROLE_" + r.toUpperCase()))
                            .toList();
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subject, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (ParseException | com.nimbusds.jose.JOSEException e) {
                // ignore invalid token
            }
        }
        filterChain.doFilter(request, response);
    }
}



