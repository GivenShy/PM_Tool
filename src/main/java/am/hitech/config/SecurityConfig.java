package am.hitech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity( jsr250Enabled = true/*, prePostEnabled = truesecuredEnabled = true*/)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private PasswordEncoder passwordEncoder;
    private MyUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, MyUserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }



    public void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()//for basic authentication
                .csrf().disable()//some error cases
                .cors().disable()//for cors errors, ask for access to make request to other server(http method - OPTION)
                .headers().frameOptions().disable()//close frames from other websites
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//to save(STATELESS) session or not(ALWAYS)
                .and().authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                //.antMatchers(HttpMethod.POST, "/user/**").permitAll()//not secure urls
                .anyRequest().authenticated();
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
