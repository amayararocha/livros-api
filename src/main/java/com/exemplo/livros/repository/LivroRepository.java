package com.exemplo.livros.repository;

import com.exemplo.livros.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByGenero(String genero);
}
