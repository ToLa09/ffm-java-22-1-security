package de.javaffm221.appuser;

import de.javaffm221.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository repository;

    public AppUser findByUsername(String username){
        return repository.findByUsername(username);
    }

    public void addUser(AppUser newAppUser){
        if(newAppUser.role() == null){
            newAppUser.withRole(AppUserRole.BASIC);
        }
        if(findByUsername(newAppUser.username()) != null){
            throw new UserAlreadyExistsException("Username already exists");
        }
        String encodedPassword = SecurityConfig.passwordEncoder.encode(newAppUser.password());
        repository.save(newAppUser.withPassword(encodedPassword));
    }
}
