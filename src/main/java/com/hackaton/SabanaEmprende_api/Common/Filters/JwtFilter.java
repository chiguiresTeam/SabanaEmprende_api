package com.hackaton.SabanaEmprende_api.Common.Filters;

import com.hackaton.SabanaEmprende_api.Common.Components.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    private static final List<String> PUBLIC_PATHS = List.of(
            "/error",
            "/usuarios",
            "/people"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        // Permitir solicitudes OPTIONS para CORS
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return true;
        }

        // Verificar rutas públicas
        for (String publicPath : PUBLIC_PATHS) {

            if (path.startsWith(publicPath)) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws IOException, jakarta.servlet.ServletException {

        // Buscar cookie con nombre "access_token"
        String token = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("access_token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null && jwtUtil.isTokenValid(token)) {
            Claims claims = jwtUtil.getClaims(token);
            String email = claims.getSubject();
            String role = claims.get("role", String.class);

            var authToken = new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + role))
            );

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

}