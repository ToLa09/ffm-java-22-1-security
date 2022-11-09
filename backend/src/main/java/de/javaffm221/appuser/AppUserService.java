package de.javaffm221.appuser;

import de.javaffm221.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository repository;

    public AppUser findByUsername(String username){
        return repository.findByUsername(username);
    }

    public void addUser(AppUser newAppUser) {
        String encodedPassword = SecurityConfig.passwordEncoder.encode(newAppUser.password());
        repository.save(newAppUser.withPassword(encodedPassword));
    }
}
