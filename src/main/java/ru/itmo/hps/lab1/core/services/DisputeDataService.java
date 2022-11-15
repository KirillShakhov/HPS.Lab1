package ru.itmo.hps.lab1.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.hps.lab1.core.entity.Dispute;
import ru.itmo.hps.lab1.core.repository.CustomizedDisputeCrudRepository;

import java.util.List;
import java.util.Optional;


@Service
public class DisputeDataService {
    private final CustomizedDisputeCrudRepository customizedDisputeCrudRepository;

    @Autowired
    public DisputeDataService(CustomizedDisputeCrudRepository customizedDisputeCrudRepository) {
        this.customizedDisputeCrudRepository = customizedDisputeCrudRepository;
    }

    public List<Dispute> findAll() {
        return (List<Dispute>) customizedDisputeCrudRepository.findAll();
    }

    public void save(Dispute dispute) {
        customizedDisputeCrudRepository.save(dispute);
    }

    public void removeById(Long id) {
        customizedDisputeCrudRepository.deleteById(id);
    }

    public Optional<Dispute> getById(Long id) {
        return customizedDisputeCrudRepository.findById(id);
    }
}

