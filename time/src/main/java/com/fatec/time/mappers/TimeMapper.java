package com.fatec.time.mappers;

import com.fatec.time.dtos.TimeResponse;
import com.fatec.time.entities.Time;

public class TimeMapper {

    public static Time toEntity(TimeRequest request) {

        Time t = new Time();

        t.setNome(request.nome());
        t.setGrupo(request.grupo());
        t.setPontos(request.pontos());

        return t;
    }

    public static TimeResponse toDTO(Time time) {
        return new TimeResponse(
                time.getId(),
                time.getNome(),
                time.getGrupo(),
                time.getPontos());
    }
}
