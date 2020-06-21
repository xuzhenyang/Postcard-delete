package co.lilpilot.postcard.authcontext.gateway.security;

import co.lilpilot.postcard.authcontext.domain.User;
import co.lilpilot.postcard.authcontext.domain.UserRepository;
import co.lilpilot.postcard.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        userRepository.findByAccount(username);
        User user = new User(1L, "test", new BCryptPasswordEncoder().encode("test"));
        return UserPrincipal.create(user);
    }
}
