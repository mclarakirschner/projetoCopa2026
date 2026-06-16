package com.fatec.time.dtos;

public record PartidaResponse( // envia dados para frontend quando é utilizado métodos http

                Long id,

                Long timeCasaId,
                String timeCasaNome,

                Long timeVisitanteId,
                String timeVisitanteNome,

                Integer golsCasa,
                Integer golsVisitante) {
}
