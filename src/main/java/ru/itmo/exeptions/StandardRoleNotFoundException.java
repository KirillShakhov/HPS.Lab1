package ru.itmo.exeptions;

public class StandardRoleNotFoundException extends RuntimeException {

    public StandardRoleNotFoundException(String message) {
        super(message);
    }
}
