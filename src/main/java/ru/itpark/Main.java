package ru.itpark;

import ru.itpark.model.House;
import ru.itpark.repository.HouseRepositoryJdbcImpl;
import ru.itpark.service.HouseService;


public class Main {
    public static void main(String[] args) {
        final HouseService service = new HouseService(new HouseRepositoryJdbcImpl("jdbc:sqlite:db.sqlite"));
        final House newHouse = service.register(new House(0, 3800000, "Приволжский", "Проспект Победы"));
        final House newHouse2 = service.register(new House(0, 6700000, "Советский", "Аметьево"));
        System.out.println(newHouse);
        System.out.println(newHouse2);
        System.out.println(service.getByDistrict("Приволжский"));
        System.out.println(service.sortByPriceASC());
        System.out.println(service.sortByPriceDESC());
    }
}
