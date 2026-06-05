package com.fatec.time.dtos;

public record PartidaRequest(
        String timeCasa,
        String timeVisitante,
        Integer golsCasa,
        Integer golsVisitante

) {

}
