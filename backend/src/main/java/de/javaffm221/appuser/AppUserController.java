package de.javaffm221.appuser;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

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
        service.addUser(newAppUser);
    }
}
