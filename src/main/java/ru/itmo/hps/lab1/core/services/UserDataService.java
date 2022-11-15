package ru.itmo.hps.lab1.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.hps.lab1.core.entity.User;
import ru.itmo.hps.lab1.core.exeptions.AlreadyExistsException;
import ru.itmo.hps.lab1.core.exeptions.NotFoundException;
import ru.itmo.hps.lab1.core.exeptions.StandardRoleNotFoundException;
import ru.itmo.hps.lab1.core.repository.CustomizedRoleCrudRepository;
import ru.itmo.hps.lab1.core.repository.CustomizedUserCrudRepository;
import ru.itmo.hps.lab1.core.services.JwtTokenProvider;

import javax.management.relation.RoleNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserDataService {
    private final CustomizedUserCrudRepository customizedUserCrudRepository;
    private final CustomizedRoleCrudRepository customizedRoleCrudRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;


    public String loginUser(String username, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        return tokenProvider.generateToken(authentication);
    }

    public void registerUser(User user) {
        var role = customizedRoleCrudRepository.findByName("ROLE_USER");
        if (role.isEmpty()) throw new StandardRoleNotFoundException("ROLE_USER role not found");
        log.info("registering user {}", user.getUsername());
        if(customizedUserCrudRepository.existsByUsername(user.getUsername())) {
            log.warn("username {} already exists.", user.getUsername());
            throw new NotFoundException(
                    String.format("username %s already exists", user.getUsername()));
        }
        if(customizedUserCrudRepository.existsByEmail(user.getEmail())) {
            log.warn("email {} already exists.", user.getEmail());

            throw new AlreadyExistsException(
                    String.format("email %s already exists", user.getEmail()));
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(role.get()));
        customizedUserCrudRepository.save(user);
    }

    public List<User> findAll() {
        return (List<User>) customizedUserCrudRepository.findAll();
    }

    public Optional<User> getByUsername(String username) {
        return customizedUserCrudRepository.findByUsername(username);
    }

    public void addRole(String username, String roleName) throws RoleNotFoundException {
        var user = customizedUserCrudRepository.findByUsername(username);
        if (user.isEmpty()) throw new UsernameNotFoundException(String.format("username %s not found", username));
        var role = customizedRoleCrudRepository.findByName(roleName);
        if (role.isEmpty()) throw new RoleNotFoundException(String.format("role %s not found", roleName));
        if (user.get().getRoles().contains(role.get())) throw new RoleNotFoundException(String.format("role %s already have", roleName));
        user.get().getRoles().add(role.get());
        customizedUserCrudRepository.save(user.get());
    }
}

