package com.exemplo.livros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LivroDTO {
    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "O autor é obrigatório")
    private String autor;

    @NotNull(message = "O ano de publicação é obrigatório")
    private Integer anoPublicacao;

    private String genero;
}
