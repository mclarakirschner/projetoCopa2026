package com.fatec.time.dtos;

public record TimeResponse( // envia dados para frontend quando é utilizado métodos http

                Long id,
                String nome,
                String grupo,
                int pontos

) {

}
