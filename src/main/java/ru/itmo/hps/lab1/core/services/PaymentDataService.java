package ru.itmo.hps.lab1.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.hps.lab1.core.entity.Payment;
import ru.itmo.hps.lab1.core.repository.CustomizedPaymentCrudRepository;

import java.util.List;
import java.util.Optional;


@Service
public class PaymentDataService {
    private final CustomizedPaymentCrudRepository customizedPaymentCrudRepository;

    @Autowired
    public PaymentDataService(CustomizedPaymentCrudRepository customizedPaymentCrudRepository) {
        this.customizedPaymentCrudRepository = customizedPaymentCrudRepository;
    }

    public List<Payment> findAll() {
        return customizedPaymentCrudRepository.findAll();
    }

    public void save(Payment payment) {
        customizedPaymentCrudRepository.save(payment);
    }

    public void removeById(Long id) {
        customizedPaymentCrudRepository.deleteById(id);
    }

    public Optional<Payment> getById(Long id) {
        return customizedPaymentCrudRepository.findById(id);
    }
}

