package com.fatec.time.mappers;

import com.fatec.time.entities.Partida;

public class PartidaMapper {

    public static Partida toEntity(PartidaRequest request) {

        Partida p = new Partida();

        p.setTimeCasa(request.timeCasa());
        p.setTimeVisitante(request.timeVisitante());
        p.setGolsCasa(request.golsCasa());
        p.setGolsVisitante(request.golsVisitante());

        return p;
    }

    public static PartidaResponse toDTO(Partida partida) {

        return new PartidaResponse(
                partida.getId(),
                partida.getTimeCasa(),
                partida.getTimeVisitante(),
                partida.getGolsCasa(),
                partida.getGolsVisitante());
    }
}
