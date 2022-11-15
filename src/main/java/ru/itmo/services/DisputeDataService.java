package ru.itmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.entity.Dispute;
import ru.itmo.repository.CustomizedDisputeCrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class DisputeDataService {
    private final CustomizedDisputeCrudRepository customizedDisputeCrudRepository;

    @Autowired
    public DisputeDataService(CustomizedDisputeCrudRepository customizedDisputeCrudRepository) {
        this.customizedDisputeCrudRepository = customizedDisputeCrudRepository;
    }

    @Transactional
    public List<Dispute> findAll() {
        return (List<Dispute>) customizedDisputeCrudRepository.findAll();
    }

    @Transactional
    public void save(Dispute dispute) {
        customizedDisputeCrudRepository.save(dispute);
    }

    @Transactional
    public void removeById(Long id) {
        customizedDisputeCrudRepository.deleteById(id);
    }

    @Transactional
    public Optional<Dispute> getById(Long id) {
        return customizedDisputeCrudRepository.findById(id);
    }
}

