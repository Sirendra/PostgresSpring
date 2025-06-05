package com.db.Postgres.controllers;

import com.db.Postgres.domain.dto.AuthorDto;
import com.db.Postgres.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    // post
    @PostMapping("/")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto){
        AuthorDto savedAuthorDto= authorService.createAuthor(authorDto);
        return new ResponseEntity<>(savedAuthorDto, HttpStatus.CREATED);
    }
    // put/:id
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id,@RequestBody AuthorDto authorDto){
        AuthorDto updatedAuthor= authorService.put(id, authorDto);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }
    // patch/:id
    @PatchMapping("/{id}")
    public ResponseEntity<AuthorDto> patchAuthor(@PathVariable Long id,@RequestBody AuthorDto authorDto){
        AuthorDto patchedAuthor= authorService.patch(id, authorDto);
        return new ResponseEntity<>(patchedAuthor, HttpStatus.OK);
    }
    // get/:id
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id){
        AuthorDto authorDto= authorService.findById(id);
        return new ResponseEntity<>(authorDto, HttpStatus.OK);
    }
    // get
    @GetMapping("/")
    public ResponseEntity<List<AuthorDto>> getAuthors(){
        List<AuthorDto> authors= authorService.findAll();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
    // delete/:id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id){
        authorService.delete(id);
        return ResponseEntity.ok("Successfully deleted author");
    }
}
