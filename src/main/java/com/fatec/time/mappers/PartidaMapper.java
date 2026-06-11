package com.fatec.time.mappers;

import com.fatec.time.dtos.PartidaResponse;
import com.fatec.time.dtos.PartidaRequest;
import com.fatec.time.entities.Partida;
import com.fatec.time.entities.Time;

public class PartidaMapper {

    public static Partida toEntity(PartidaRequest request, Time casa, Time visitante) {

        Partida p = new Partida();

        p.setTimeCasa(casa);
        p.setTimeVisitante(visitante);
        p.setGolsCasa(request.golsCasa());
        p.setGolsVisitante(request.golsVisitante());

        return p;
    }

    public static PartidaResponse toDTO(Partida p) {

        return new PartidaResponse(
                p.getId(),
                p.getTimeCasa().getId(),
                p.getTimeCasa().getNome(),
                p.getTimeVisitante().getId(),
                p.getTimeVisitante().getNome(),
                p.getGolsCasa(),
                p.getGolsVisitante()
        );
    }
}
