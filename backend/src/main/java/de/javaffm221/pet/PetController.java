package de.javaffm221.pet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @Value("${app.message:Default message}")
    private String message;

    @GetMapping("/hello")
    public String getHello() {
        return message;
    }

    @GetMapping("/api/pets")
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @PostMapping("/api/pets")
    public Pet savePet(@RequestBody Pet pet) {
        return petService.savePet(pet);
    }
}
