package com.springboot.swagger.service;

import com.springboot.swagger.model.Author;
import org.jfairy.Fairy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class AuthorService {
    private static Map<Integer, Author> authorDB;
    private Fairy fairy = Fairy.create();

    @PostConstruct
    public void init() throws Exception {
        authorDB = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            Author author = new Author(i, fairy.person());
            authorDB.put(new Integer(i), author);
        }
    }

    public List<Author> getAll(){
        List<Author> authorList = authorDB.entrySet().stream()
                .map(Map.Entry::getValue).collect(Collectors.toList());
        return authorList;
    }

    public Author getAuthorById(Integer authorId) {
        return authorDB.get(authorId);
    }

    public List<Author> filterByAge(Integer age) {
        List<Author> authorList = authorDB.entrySet().stream().filter(e -> e.getValue().getAge() > age)
                .map(Map.Entry::getValue).collect(Collectors.toList());
        return authorList;
    }

    public List<Author> filterByCity(String cityName) {
        List<Author> authorList = authorDB.entrySet().stream()
                .filter(e -> e.getValue().getAddress().getCity().equals(cityName)).map(Map.Entry::getValue)
                .collect(Collectors.toList());
        return authorList;
    }

    public List<Author> filterByAgeAndCity(Integer age, String cityName) {
        List<Author> authorList = authorDB.entrySet().stream()
                .filter(e -> e.getValue().getAddress().getCity().equals(cityName) && e.getValue().getAge() > age)
                .map(Map.Entry::getValue).collect(Collectors.toList());
        return authorList;
    }
}
