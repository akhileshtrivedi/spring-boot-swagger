package com.springboot.swagger.controller;

import com.springboot.swagger.model.Author;
import com.springboot.swagger.service.AuthorService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/authors")
@Api(tags = {"Authors Details"}, value = "API to search Author from a Author Repository by different search parameters",
        description = "This API provides the capability to search Author from a Author's Repository", produces = "application/json")
public class AuthorController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthorService authorService;

    @ApiOperation(value = "Get All Author", produces = "application/json")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllAuthors() {
        logger.debug("Getting All Authors ...");
        List<Author> Author = null;
        try {
            Author = authorService.getAll();
            logger.debug("Getting All Authors ... ::");
        } catch (Exception ex) {
            logger.error("Error occurred in searchAuthorById >>", ex, ex.getMessage());
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(Author, HttpStatus.OK);
    }

    @ApiOperation(value = "Search Author by authorId", produces = "application/json")
    @RequestMapping(value = "/{authorId}", method = RequestMethod.GET)
    public ResponseEntity<Object> searchAuthorById(
            @ApiParam(name = "authorId",
                    value = "The Id of the Author to be viewed",
                    required = true)
            @PathVariable Integer authorId) {
        logger.debug("Searching for Author with authorId ::" + authorId);
        Author Author = null;
        try {
            Author = authorService.getAuthorById(authorId);
            logger.debug("Author found with authorId ::" + authorId);
        } catch (Exception ex) {
            logger.error("Error occurred in searchAuthorById >>", ex, ex.getMessage());
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(Author, HttpStatus.OK);
    }

    @ApiOperation(value = "Search for all Authors whose age is greater than input age", produces = "application/json")
    @RequestMapping(value = "/greaterThanAge/{age}", method = RequestMethod.GET)
    public ResponseEntity<Object> filterAuthorsByAge(
            @ApiParam(name = "age",
                    value = "filtering age",
                    required = true) @PathVariable Integer age) {
        List<Author> authorList = null;
        try {
            authorList = authorService.filterByAge(age);
        } catch (Exception ex) {
            logger.error("Error occurred in filterAuthorsByAge >>", ex, ex.getMessage());
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(authorList, HttpStatus.OK);
    }

    @ApiOperation(value = "Search for all Authors who are from input city", produces = "application/json")
    @RequestMapping(value = "/fromCity/{cityName}", method = RequestMethod.GET)
    public ResponseEntity<Object> filterAuthorsByCity(
            @ApiParam(name = "cityName", value = "filtering city name", required = true)
            @PathVariable String cityName) {
        List<Author> authorList = null;
        try {
            authorList = authorService.filterByCity(cityName);
        } catch (Exception ex) {
            logger.error("Error occurred in filterAuthorsByCity >>", ex, ex.getMessage());
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(authorList, HttpStatus.OK);
    }

    @ApiOperation(value = "Search for all Authors who are from given city and "
            + "whose age are greater than input age", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "schoolId", value = "School Id", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "age", value = "Age of Author", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "cityName", value = "City of Author", required = true, dataType = "String", paramType = "query") })
    @RequestMapping(value = "/filterByAgeAndCity", method = RequestMethod.GET)
    public ResponseEntity<Object> filterAuthorsByAgeAndCity(@RequestHeader(name = "schoolId") String userId,
                                                             @RequestParam Integer age,@RequestParam String cityName) {

        List<Author> authorList = null;
        try {
            authorList = authorService.filterByAgeAndCity(age, cityName);
        } catch (Exception ex) {
            logger.error("Error occurred in filterAuthorsByAgeAndCity >>", ex, ex.getMessage());
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(authorList, HttpStatus.OK);
    }
}
