package de.javaffm221.appuser;

public record AppUser(
        String id,
        String username,
        String password,
        AppUserRole role
) {
}
