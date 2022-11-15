package ru.itmo.hps.lab1.core.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder(toBuilder = true)
public class PageEntity {
    @Singular
    @JsonView(View.PageEntity.class)
    List<Object> items;
    @JsonView(View.PageEntity.class)
    Boolean hasMore;
}
