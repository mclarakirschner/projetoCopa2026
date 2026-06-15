package com.fatec.time.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TimeRequest(

        @NotBlank(message = "Nome é obrigatório") @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres") String nome,

        @NotBlank(message = "Grupo é obrigatório") @Size(min = 1, max = 1, message = "O grupo deve ter apenas 1 letra") String grupo

) {

}
