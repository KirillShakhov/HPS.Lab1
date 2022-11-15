package ru.itmo.hps.lab1.core.exeptions;

public class StandardRoleNotFoundException extends RuntimeException {

    public StandardRoleNotFoundException(String message) {
        super(message);
    }
}
