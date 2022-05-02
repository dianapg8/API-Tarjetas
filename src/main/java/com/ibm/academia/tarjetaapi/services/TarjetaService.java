package com.ibm.academia.tarjetaapi.services;

import com.ibm.academia.tarjetaapi.entities.Tarjeta;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TarjetaService {
    ResponseEntity<?> listarTodasLasTarjetas();

    ResponseEntity<?> recomendarTarjetas(String preferencia, Integer edad, Double salario);
}
