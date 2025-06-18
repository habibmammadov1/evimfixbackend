package com.evimfix.evimfix.core.utilities.results;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageData <T> {
    private int totalPages;
    private long totalElements;
    private int size;
    private int page;
    private List<T> content;
}
