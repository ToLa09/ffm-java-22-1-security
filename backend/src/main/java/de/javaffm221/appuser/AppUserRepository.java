package de.javaffm221.appuser;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppUserRepository extends MongoRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
