package ru.itpark;

import ru.itpark.model.House;
import ru.itpark.repository.HouseRepositoryJdbcImpl;
import ru.itpark.service.HouseService;


public class Main {
    public static void main(String[] args) {
        final HouseService service = new HouseService(new HouseRepositoryJdbcImpl("jdbc:sqlite:db.sqlite"));
        final House houseA = service.register(new House(0, 3800000, "Приволжский", "Проспект Победы"));
        final House houseB = service.register(new House(0, 6700000, "Советский", "Аметьево"));
        System.out.println(houseA);
        System.out.println(houseB);
        System.out.println(service.getByDistrict("Приволжский"));
        System.out.println(service.sortByPriceASC());
        System.out.println(service.sortByPriceDESC());
    }
}
