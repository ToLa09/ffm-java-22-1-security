package de.javaffm221.appuser;

import lombok.NonNull;
import lombok.With;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@With
public record AppUser(
        String id,
        String username,
        @NonNull
        @NotEmpty(message = "Password must not be empty")
        @Length(min = 6, max = 30, message="Password must have at least 6, max. 30 characters")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\\\S+$)", message = "Password must have minimum eight characters, at least one letter and one number")
        String password,
        AppUserRole role
) {
}
