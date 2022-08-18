package com.ceibachallengue.book.service;


import com.ceibachallengue.book.config.BookServiceConfigurationProps;
import com.ceibachallengue.book.dto.BookDto;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService{

    private List<BookDto> bookDtoStream;
    private final Faker faker;
    private final BookServiceConfigurationProps configurationProperties;

    public BookServiceImpl(Faker faker,
                           BookServiceConfigurationProps configurationProperties){
        this.faker = faker;
        this.configurationProperties = configurationProperties;
        this.bookDtoStream = Stream.generate(() -> BookDto.builder()
                    .id(UUID.randomUUID().toString())
                    .author(faker.book().author())
                    .title(faker.book().title())
                    .build())
                .limit(this.configurationProperties.getBooksQuantity())
                .collect(Collectors.toList());
    }

    public List<BookDto> getAll(){
        return this.bookDtoStream;
    }

    public BookDto findOne(String id){
        return this.bookDtoStream.stream().filter(book -> book.getId().equals(id)).findFirst().get();
    }

    public boolean deleteOne(String id){
        return this.bookDtoStream.removeIf(book -> book.getId().equals(id));
    }

    public BookDto createBook(BookDto bookDto){
        BookDto bookDto1 = BookDto.builder()
                .id(UUID.randomUUID().toString())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .build();
        this.bookDtoStream.add(bookDto1);
        return bookDto1;
    }


    public BookDto updateOne(String id, BookDto bookDto) throws Exception {
        Optional<BookDto> optional = this.bookDtoStream.stream()
                .filter(bookDto1 -> bookDto.getId().equals(id))
                .findFirst();
        if(!optional.isPresent()){
            throw new Exception("");
        }
        optional.ifPresent(dto -> {
            bookDto.setTitle(bookDto.getTitle());
            bookDto.setAuthor(bookDto.getAuthor());
        });
        return optional.get();
    }

}
