package ru.itmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.entity.Order;
import ru.itmo.repository.CustomizedOrderCrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class OrderDataService {
    private final CustomizedOrderCrudRepository customizedOrderCrudRepository;

    @Autowired
    public OrderDataService(CustomizedOrderCrudRepository customizedOrderCrudRepository) {
        this.customizedOrderCrudRepository = customizedOrderCrudRepository;
    }

    @Transactional
    public List<Order> findAll() {
        return (List<Order>) customizedOrderCrudRepository.findAll();
    }

    @Transactional
    public void save(Order order) {
        customizedOrderCrudRepository.save(order);
    }

    @Transactional
    public void removeById(Long id) {
        customizedOrderCrudRepository.deleteById(id);
    }

    @Transactional
    public Optional<Order> getById(Long id) {
        return customizedOrderCrudRepository.findById(id);
    }
}

