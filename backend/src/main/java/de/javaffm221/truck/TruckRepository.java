package de.javaffm221.truck;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TruckRepository extends MongoRepository<Truck, String> {
}
