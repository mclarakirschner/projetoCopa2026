package com.fatec.time.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PartidaRequest(

                @NotBlank(message = "Time da casa é obrigatório") @Size(min = 3, max = 50, message = "O nome do time da casa deve ter entre 3 e 50 caracteres") String timeCasa,

                @NotBlank(message = "Time visitante é obrigatório") @Size(min = 3, max = 50, message = "O nome do time visitante deve ter entre 3 e 50 caracteres") String timeVisitante,

                @Min(value = 0, message = "Os gols da casa devem ser maiores ou iguais a 0") Integer golsCasa,

                @Min(value = 0, message = "Os gols do visitante devem ser maiores ou iguais a 0") Integer golsVisitante) {

}
