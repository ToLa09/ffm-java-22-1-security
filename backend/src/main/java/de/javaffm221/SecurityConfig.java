package de.javaffm221;

import de.javaffm221.appuser.AppUser;
import de.javaffm221.appuser.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AppUserService appUserService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/pets", "/api/trucks", "/api/login","/api/logout").authenticated()
                .antMatchers(HttpMethod.POST, "/api/pets", "/api/trucks").hasRole("ADMIN")
                .anyRequest().denyAll()
                .and().build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsManager userDetailsService() {
        return new UserDetailsManager() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                AppUser userFromDb = appUserService.findByUsername(username);
                if(userFromDb == null){
                    throw new UsernameNotFoundException("Username \"" + username + "\" not found!");
                } else {
                    return User.builder()
                            .username(username)
                            .password(userFromDb.password())
                            .roles(userFromDb.role().toString())
                            .build();
                }
            }

            @Override
            public void createUser(UserDetails user) {
                throw new UnsupportedOperationException("You cannot use the custom UserDetailsManager for this action");
            }

            @Override
            public void updateUser(UserDetails user) {
                throw new UnsupportedOperationException("You cannot use the custom UserDetailsManager for this action");
            }

            @Override
            public void deleteUser(String username) {
                throw new UnsupportedOperationException("You cannot use the custom UserDetailsManager for this action");
            }

            @Override
            public void changePassword(String oldPassword, String newPassword) {
                throw new UnsupportedOperationException("You cannot use the custom UserDetailsManager for this action");
            }

            @Override
            public boolean userExists(String username) {
                throw new UnsupportedOperationException("You cannot use the custom UserDetailsManager for this action");
            }
        };
    }
}
