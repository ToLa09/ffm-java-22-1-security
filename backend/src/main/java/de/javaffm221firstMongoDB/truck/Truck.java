package de.javaffm221firstMongoDB.truck;

public record Truck(
        String id,
        String brand,
        String model,
        int power,
        int weight
) {
}
