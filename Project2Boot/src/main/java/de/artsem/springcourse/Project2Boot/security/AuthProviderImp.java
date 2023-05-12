package de.artsem.springcourse.Project2Boot.security;

import de.artsem.springcourse.Project2Boot.services.UserAccountDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImp implements AuthenticationProvider {

    private final UserAccountDetailsService userAccountDetailsService;

    public AuthProviderImp(UserAccountDetailsService userAccountDetailsService) {
        this.userAccountDetailsService = userAccountDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        UserDetails userAccountDetails = userAccountDetailsService.loadUserByUsername(username);

        String password = authentication.getCredentials().toString();


       if (!password.equals(userAccountDetails.getPassword())){
           throw new BadCredentialsException("Invalid password");
       }
        return new UsernamePasswordAuthenticationToken(userAccountDetails, password,
                Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
