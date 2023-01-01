package com.hyena.springpageable.base.domain;

import lombok.Getter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
public class PageCustomResponse<T> {

    private List<T> stock;

    private PageableCustom pageableCustom;

    public PageCustomResponse(List<T> stock, Pageable pageable, long totalElement) {
        this.stock = stock;
        this.pageableCustom = new PageableCustom(new PageImpl<>(stock, pageable, totalElement));
    }
}
