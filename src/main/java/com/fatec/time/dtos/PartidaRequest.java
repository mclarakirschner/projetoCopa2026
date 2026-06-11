package com.fatec.time.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PartidaRequest(

    @NotNull(message = "Time da casa é obrigatório")
    Long timeCasaId,

    @NotNull(message = "Time visitante é obrigatório")
    Long timeVisitanteId,

    @Min(value = 0, message = "Os gols da casa devem ser maiores ou iguais a 0")
    Integer golsCasa,

    @Min(value = 0, message = "Os gols do visitante devem ser maiores ou iguais a 0")
    Integer golsVisitante

) {}
