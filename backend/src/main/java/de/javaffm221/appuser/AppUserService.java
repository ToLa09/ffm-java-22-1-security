package de.javaffm221.appuser;

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
