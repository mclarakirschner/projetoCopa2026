package com.fatec.time.dtos;

public record PartidaResponse(
        Long id,
        String timeCasa,
        String timeVisitante,
        Integer golsCasa,
        Integer golsVisitante

) {
}
