package de.javaffm221firstMongoDB.truck;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TruckRepository extends MongoRepository<Truck, String> {
}
