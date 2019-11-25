package ru.itpark.repository;

import ru.itpark.model.House;

import java.util.List;
import java.util.Optional;


public interface HouseRepository {
    House save(House house);
    List <House> getByDistrict(String district);
    List<House> sortByPriceASC();
    List<House> sortByPriceDESC();
}
