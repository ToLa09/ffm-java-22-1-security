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
        @Pattern(regexp = "^(?=[^A-Z]*+[A-Z])(?=[^a-z]*+[a-z])(?=\\D*+\\d)(?=[^#?!@$ %^&*-]*+[#?!@$ %^&*-]).{6,30}$", message = "Password must have minimum eight characters, at least one letter and one number")
        String password,
        AppUserRole role
) {
}
