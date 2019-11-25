package ru.itpark.service;

import ru.itpark.model.House;
import ru.itpark.repository.HouseRepository;

import java.util.*;
import java.util.stream.Collectors;


public class HouseService {
    private final HouseRepository repository;

    public HouseService(HouseRepository repository) {
        this.repository = repository;
    }

    public House register(House house) {
        if (house.getId() != 0) {
            throw new IllegalArgumentException("Для регистрации id должен быть равным 0");
        }
        return repository.save(house);
    }

    public List <House> getByDistrict(String district) {
        return getByDistrict(district).stream().filter(o -> o.getDistrict().equals(district)).collect(Collectors.toList());
        //FIXME: как выкинуть DistrictNotFoundException?
    }

    public List<House> sortByPriceASC() {
        return repository.sortByPriceASC().stream().collect(Collectors.toList());
    }

    public List<House> sortByPriceDESC() {
        return repository.sortByPriceDESC().stream().collect(Collectors.toList());
    }

}
