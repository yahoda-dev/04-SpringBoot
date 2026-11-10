package com.yahodadev.studyboard.common.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageResponseDTO<T> {

    private List<T> items;
    private int page;
    private int size;
    private long totalElements;

    // Page의 pageable, sort 정보 등도 포함하고 있기에 불필요한 정보는 제공하지 않도록 커스텀하여 리턴함
    public static <T> PageResponseDTO<T> from(Page<T> page) {
        return new PageResponseDTO<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }
}
