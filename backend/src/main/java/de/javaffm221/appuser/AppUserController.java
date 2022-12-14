package de.javaffm221.appuser;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService service;

    @GetMapping("/login")
    public void login() {
    }
    @GetMapping("/logout")
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }
    @GetMapping("/me")
    public String getUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    @GetMapping("/role")
    public String getRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addUser(@RequestBody @Valid AppUser newAppUser){
        try {
            service.addUser(newAppUser);
        } catch(UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
