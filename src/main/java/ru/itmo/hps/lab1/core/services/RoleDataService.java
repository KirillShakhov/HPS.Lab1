package ru.itmo.hps.lab1.core.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.hps.lab1.core.entity.Role;
import ru.itmo.hps.lab1.core.repository.CustomizedRoleCrudRepository;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class RoleDataService {
    private final CustomizedRoleCrudRepository customizedRoleCrudRepository;

    @Autowired
    public RoleDataService(CustomizedRoleCrudRepository customizedRoleCrudRepository) {
        this.customizedRoleCrudRepository = customizedRoleCrudRepository;
    }

    public List<Role> findAll() {
        return (List<Role>) customizedRoleCrudRepository.findAll();
    }

    public void save(Role role) {
        customizedRoleCrudRepository.save(role);
    }

    public void removeById(Long id) {
        customizedRoleCrudRepository.deleteById(id);
    }

    public Optional<Role> getById(Long id) {
        return customizedRoleCrudRepository.findById(id);
    }
}

