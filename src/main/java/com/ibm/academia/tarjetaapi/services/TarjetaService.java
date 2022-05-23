package com.ibm.academia.tarjetaapi.services;

import com.ibm.academia.tarjetaapi.models.entities.Tarjeta;

import java.util.List;

public interface TarjetaService {
    List<Tarjeta> listarTodasLasTarjetas();

    List<Tarjeta> recomendarTarjetas(String preferencia, Integer edad, Double salario);
}
