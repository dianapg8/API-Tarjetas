package com.ibm.academia.tarjetaapi.repositories;

import com.ibm.academia.tarjetaapi.entities.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta,Long> {

    @Query("SELECT a FROM Tarjeta a WHERE a.preferencia = ?1 AND ?2 BETWEEN a.edadMinima and a.edadMaxima AND a.salarioMinimo <= ?3 and (a.salarioMaximo >= ?3 or a.salarioMaximo is NULL)")
    List<Tarjeta> recomendarTarjetas(String preferencia,Integer edad, Double salario);
}
