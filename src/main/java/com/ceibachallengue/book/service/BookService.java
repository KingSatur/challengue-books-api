package com.ceibachallengue.book.service;

import com.ceibachallengue.book.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAll();

    BookDto findOne(String id);
    boolean deleteOne(String id);
    BookDto createBook(BookDto bookDto);
    BookDto updateOne(String id, BookDto bookDto) throws Exception;

}
