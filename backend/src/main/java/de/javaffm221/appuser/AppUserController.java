package de.javaffm221.appuser;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;

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
    public User getUser(){
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    @PostMapping
    public void addUser(@RequestBody AppUser newAppUser){
        try {
            service.addUser(newAppUser);
        } catch(IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch(UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
