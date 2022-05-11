package com.ibm.academia.tarjetaapi.mapper;

import com.ibm.academia.tarjetaapi.models.dto.TarjetaDTO;
import com.ibm.academia.tarjetaapi.models.entities.Tarjeta;
import org.springframework.stereotype.Component;

@Component
public class TarjetaMapper {
    public TarjetaDTO mapTarjeta(Tarjeta tarjeta){
        return new TarjetaDTO(
                tarjeta.getIdTarjeta(),
                tarjeta.getNombreTarjeta()
        );
    }
}
