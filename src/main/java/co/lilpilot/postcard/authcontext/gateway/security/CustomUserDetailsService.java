package co.lilpilot.postcard.authcontext.gateway.security;

import co.lilpilot.postcard.authcontext.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        userRepository.findByAccount(username);
        org.springframework.security.core.userdetails.User.UserBuilder users = User.withDefaultPasswordEncoder();
        return users
                .username("test")
                .password("test")
                .roles("USER")
                .build();
    }
}
