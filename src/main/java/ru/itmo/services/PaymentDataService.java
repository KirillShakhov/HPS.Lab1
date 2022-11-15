package ru.itmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.entity.Payment;
import ru.itmo.repository.CustomizedPaymentCrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class PaymentDataService {
    private final CustomizedPaymentCrudRepository customizedPaymentCrudRepository;

    @Autowired
    public PaymentDataService(CustomizedPaymentCrudRepository customizedPaymentCrudRepository) {
        this.customizedPaymentCrudRepository = customizedPaymentCrudRepository;
    }

    @Transactional
    public List<Payment> findAll() {
        return (List<Payment>) customizedPaymentCrudRepository.findAll();
    }

    @Transactional
    public void save(Payment payment) {
        customizedPaymentCrudRepository.save(payment);
    }

    @Transactional
    public void removeById(Long id) {
        customizedPaymentCrudRepository.deleteById(id);
    }

    @Transactional
    public Optional<Payment> getById(Long id) {
        return customizedPaymentCrudRepository.findById(id);
    }
}

