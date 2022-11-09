package de.javaffm221.truck;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trucks")
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping
    List<Truck> getAllTrucks() {
        return truckService.getAllTrucks();
    }

    @PostMapping
    Truck addTruck(@RequestBody Truck truck){
        return truckService.addTruck(truck);
    }
}
