package com.example.jwt_chuan1.filtes;

import com.example.jwt_chuan1.models.AccountCredentials;
import com.example.jwt_chuan1.services.TokenAuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by nhs3108 on 29/03/2017.
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        AccountCredentials credentials = new AccountCredentials(request.getParameter("username"), request.getParameter("password"));
//        if(credentials!=null){
//            throw new RuntimeException("username"+credentials.getPassword());
//        }
//        Collection<? extends GrantedAuthority> y;
//        List<GrantedAuthority> auth=new ArrayList<>();
//        auth.add(new SimpleGrantedAuthority("admin"));
//
//        y=auth;
//
//        List<String> x=new ArrayList<>();
//        x.add("admin");
//        UsernamePasswordAuthenticationToken k=new UsernamePasswordAuthenticationToken(credentials.getUsername(),credentials.getPassword(),Collections.emptyList());
//                if(credentials!=null){
//            throw new RuntimeException("username"+k.getAuthorities());
//        }
        return
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword(),
                        Collections.emptyList()
                );

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        TokenAuthenticationService.addAuthentication(response, authResult.getName());
    }
}