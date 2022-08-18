package com.ceibachallengue.book.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {
    private String id;
    private String author;
    private String title;
}
