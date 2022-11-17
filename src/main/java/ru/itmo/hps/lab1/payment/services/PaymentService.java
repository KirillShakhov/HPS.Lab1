package ru.itmo.hps.lab1.payment.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.hps.lab1.payment.entity.Payment;
import ru.itmo.hps.lab1.payment.repository.PaymentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public void save(Payment person){
        paymentRepository.save(person);
    }

    public Payment getById(Long id){
        return paymentRepository.getById(id);
    }

    public void update(Payment person){
        paymentRepository.update(person);
    }

    public void removeById(Long id){
        paymentRepository.getById(id);
    }

    public void deleteAll(){
        paymentRepository.deleteAll();
    }

    public List<Payment> findAll(){
        return paymentRepository.findAll();
    }
}
