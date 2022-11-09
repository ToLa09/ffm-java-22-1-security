package de.javaffm221firstMongoDB.appUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository repository;

    public AppUser findByUsername(String username){
        return repository.findByUsername(username);
    }
}
