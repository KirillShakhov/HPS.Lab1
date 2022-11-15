package ru.itmo.entity;

public class View {
    public interface User { }
    public interface Category { }
    public interface Order { }
    public interface Dispute { }
    public interface Message { }
    public interface Attachment { }
    public interface Chat { }
    public interface Payment { }
    public interface Product extends User, Category { }
    public interface PageEntity extends Attachment, Product, Category { }
}
