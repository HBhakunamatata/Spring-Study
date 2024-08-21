package cloud.popples.securingweb.spittr.service;

import cloud.popples.securingweb.spittr.exceptionhandler.SpitterNotFoundException;
import cloud.popples.securingweb.spittr.pojo.Spitter;
import cloud.popples.securingweb.spittr.repository.SpitterRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class SpitterUserService implements UserDetailsService {

    private final SpitterRepository spitterRepository;

    public SpitterUserService (SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Spitter spitter = spitterRepository.findByUsername(username);
        if (spitter != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_SPITTER"));
            return new User(spitter.getUsername(), spitter.getPassword(), authorities);
        }
        throw new SpitterNotFoundException();
    }
}
