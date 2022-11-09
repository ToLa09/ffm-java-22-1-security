package de.javaffm221firstMongoDB.appUser;

public record AppUser(
        String id,
        String username,
        String password,
        AppUserRole role
) {
}
