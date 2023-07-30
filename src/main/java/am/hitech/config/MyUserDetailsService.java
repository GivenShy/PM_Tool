package am.hitech.config;

import am.hitech.model.User;
import am.hitech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


@Component
public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;


    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userService.getUserByUsername(username);
        if(u==null){
            throw new UsernameNotFoundException("Username not found");
        }

        List<GrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority(u.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(u.getEmail(),u.getPassword(),roles);
    }


}
