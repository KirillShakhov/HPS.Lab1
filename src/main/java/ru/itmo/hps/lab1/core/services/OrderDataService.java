package ru.itmo.hps.lab1.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.hps.lab1.core.entity.Order;
import ru.itmo.hps.lab1.core.repository.CustomizedOrderCrudRepository;

import java.util.List;
import java.util.Optional;


@Service
public class OrderDataService {
    private final CustomizedOrderCrudRepository customizedOrderCrudRepository;

    @Autowired
    public OrderDataService(CustomizedOrderCrudRepository customizedOrderCrudRepository) {
        this.customizedOrderCrudRepository = customizedOrderCrudRepository;
    }

    public List<Order> findAll() {
        return (List<Order>) customizedOrderCrudRepository.findAll();
    }

    public void save(Order order) {
        customizedOrderCrudRepository.save(order);
    }

    public void removeById(Long id) {
        customizedOrderCrudRepository.deleteById(id);
    }

    public Optional<Order> getById(Long id) {
        return customizedOrderCrudRepository.findById(id);
    }
}

