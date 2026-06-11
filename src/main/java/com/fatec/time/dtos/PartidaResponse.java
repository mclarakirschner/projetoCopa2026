package com.fatec.time.dtos;

public record PartidaResponse(

        Long id,

        Long timeCasaId,
        String timeCasaNome,

        Long timeVisitanteId,
        String timeVisitanteNome,

        Integer golsCasa,
        Integer golsVisitante
) {}
