package ru.itpark.repository;

import ru.itpark.model.House;
import java.util.*;
import java.util.stream.Collectors;

public class HouseRepositoryInMemoryImpl implements HouseRepository {
    private final Collection<House> houses = new ArrayList<>();
    private int nextId = 1;

    @Override
    public House save(House house) {
        if (house.getId() == 0) {
            house.setId(nextId++);
            houses.add(house);
            return house;
        }
        boolean removed = houses.removeIf(o -> o.getId() == house.getId());
        if (!removed) {
            throw new IllegalArgumentException("Id not exists: " + house.getId());
        }
        houses.add(house);
        return house;
    }

    @Override
    public List<House> getByDistrict(String district) {
        return houses.stream().filter(house -> house.getDistrict() == district).collect(Collectors.toList());

    }

    @Override
    public List<House> sortByPriceASC() {
        return houses.stream().sorted((o1, o2) -> o1.getPrice() - o2.getPrice()).collect(Collectors.toList());
    }

    @Override
    public List<House> sortByPriceDESC() {
        return houses.stream().sorted((o1, o2) -> -(o1.getPrice() - o2.getPrice())).collect(Collectors.toList());
    }
}
