package com.exemplo.livros.controller;

import com.exemplo.livros.dto.LivroDTO;
import com.exemplo.livros.model.Livro;
import com.exemplo.livros.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public List<Livro> getAll() {
        return livroRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getById(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        return livro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Livro create(@Valid @RequestBody LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setAnoPublicacao(livroDTO.getAnoPublicacao());
        livro.setGenero(livroDTO.getGenero());
        return livroRepository.save(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable Long id, @Valid @RequestBody LivroDTO livroDTO) {
        Optional<Livro> livroOptional = livroRepository.findById(id);

        if (livroOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Livro livro = livroOptional.get();
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setAnoPublicacao(livroDTO.getAnoPublicacao());
        livro.setGenero(livroDTO.getGenero());
        livroRepository.save(livro);

        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Livro> livroOptional = livroRepository.findById(id);

        if (livroOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        livroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/genero/{genero}")
    public List<Livro> buscarLivrosPorGenero(@PathVariable String genero) {
        return livroRepository.findByGenero(genero);
    }
}
