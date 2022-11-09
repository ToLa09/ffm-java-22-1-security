package de.javaffm221firstMongoDB.truck;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TruckService {

    private final TruckRepository repository;

    public TruckService(TruckRepository repository) {
        this.repository = repository;
    }

    public List<Truck> getAllTrucks() {
        return repository.findAll();
    }

    public Truck addTruck(Truck truck) {
        return repository.save(truck);
    }
}
