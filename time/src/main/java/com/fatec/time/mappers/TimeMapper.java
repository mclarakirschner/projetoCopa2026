package com.fatec.time.mappers;

import com.fatec.time.dtos.TimeResponse;
import com.fatec.time.entities.Time;

public class TimeMapper {

    public static TimeResponse toDTo(Time time) {
        return new TimeResponse(
                time.getId(),
                time.getNome(),
                time.getGrupo(),
                time.getPontos());
    }
}
