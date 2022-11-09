package de.javaffm221.appuser;

import lombok.With;

@With
public record AppUser(
        String id,
        String username,
        String password,
        AppUserRole role
) {
}
