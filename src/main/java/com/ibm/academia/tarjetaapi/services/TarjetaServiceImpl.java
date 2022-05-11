package com.ibm.academia.tarjetaapi.services;

import com.ibm.academia.tarjetaapi.models.entities.Tarjeta;
import com.ibm.academia.tarjetaapi.repositories.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/*
* TODO: listarTodasLasTarjetas() no es requerido
* TODO: Validación de parámetros
* TODO: Testing*/
@Service
public class TarjetaServiceImpl implements TarjetaService{

    private final TarjetaRepository tarjetaRepository;


    @Autowired
    public TarjetaServiceImpl(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tarjeta> listarTodasLasTarjetas() {
        return tarjetaRepository.findAll();
        /*
        String[] omitirCampos = {};
        MappingJacksonValue jacksonValue = FiltrarBeanService.filterBean(omitirCampos, "tarjetaFiltro", listaDeTarjetas);
        return new ResponseEntity<>(jacksonValue, HttpStatus.OK);*/
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tarjeta> recomendarTarjetas(String preferencia,Integer edad, Double salario) {
        return tarjetaRepository.recomendarTarjetas(preferencia, edad, salario);

        /*String[] omitirCampos = {"idTarjeta","edadMinima","edadMaxima","salarioMinimo","salarioMaximo","preferencia"};
        MappingJacksonValue jacksonValue = FiltrarBeanService.filterBean(omitirCampos, "tarjetaFiltro", recomendacionTarjetas);
        return new ResponseEntity<>(jacksonValue, HttpStatus.OK);*/
    }
}
