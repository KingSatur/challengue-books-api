package com.ceibachallengue.book.controller;


import com.ceibachallengue.book.dto.BookDto;
import com.ceibachallengue.book.service.BookServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BooksController {

    private final BookServiceImpl bookService;

    public BooksController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getBooks(){
        return this.bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookDto getOneBook(@PathVariable("id") String id){
        return this.bookService.findOne(id);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@RequestBody BookDto bookDto, @PathVariable("id") String id) throws Exception {
        return this.bookService.updateOne(id, bookDto);
    }

    @PostMapping
    public BookDto createBook(@RequestBody BookDto bookDto){
        return this.bookService.createBook(bookDto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteBook(@PathVariable("id") String id){
        return this.bookService.deleteOne(id);
    }

}
