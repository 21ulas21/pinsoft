package com.egrikulas.pinsoft.authentication.auth;

import com.egrikulas.pinsoft.authentication.user.impl.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService service;
    private final UserRepository repository;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
        final String authheader = request.getHeader("Authorization");
        final String token;
        final String username;
        if (authheader == null || !authheader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        token = authheader.substring(7);
        username = jwtService.extractUsername(token);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = service.loadUserByUsername(username);

            if(jwtService.isTokenValid(token, userDetails)){
                UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authtoken);
            }
        }
        filterChain.doFilter(request,response);

    }
}
