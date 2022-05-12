package com.ibm.academia.tarjetaapi.services;

import com.ibm.academia.tarjetaapi.exceptions.EdadNoValida;
import com.ibm.academia.tarjetaapi.exceptions.PreferenciaNoValida;
import com.ibm.academia.tarjetaapi.exceptions.SalarioNoValido;
import com.ibm.academia.tarjetaapi.exceptions.TarjetaNoEncontrada;
import com.ibm.academia.tarjetaapi.models.entities.Tarjeta;
import com.ibm.academia.tarjetaapi.repositories.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ibm.academia.tarjetaapi.Validaciones.Validar.*;


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
        if(!validarPreferencia(preferencia)){
            throw new PreferenciaNoValida("La preferencia " + preferencia + " no existe");

        }
        if(validarEdad(edad)){
            throw new EdadNoValida("El usuario no cumple con la edad permitida");

        }
        if(validarSalario(salario)){
            throw new SalarioNoValido("El usuario no cumple con el mimimo salario parametrizado");
        }
        List<Tarjeta> tarjetas = tarjetaRepository.recomendarTarjetas(preferencia.toLowerCase(), edad, salario);
        if(tarjetas.isEmpty()){
            throw new TarjetaNoEncontrada("El usuario no es apto para una tarjeta");
        }

        return tarjetas;

        /*String[] omitirCampos = {"idTarjeta","edadMinima","edadMaxima","salarioMinimo","salarioMaximo","preferencia"};
        MappingJacksonValue jacksonValue = FiltrarBeanService.filterBean(omitirCampos, "tarjetaFiltro", recomendacionTarjetas);
        return new ResponseEntity<>(jacksonValue, HttpStatus.OK);*/
    }
}
