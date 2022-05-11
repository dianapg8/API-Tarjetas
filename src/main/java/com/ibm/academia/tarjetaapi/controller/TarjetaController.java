package com.ibm.academia.tarjetaapi.controller;

import com.ibm.academia.tarjetaapi.mapper.TarjetaMapper;
import com.ibm.academia.tarjetaapi.services.TarjetaService;
import com.ibm.academia.tarjetaapi.models.entities.Tarjeta;
import com.ibm.academia.tarjetaapi.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ibm.academia.tarjetaapi.Validaciones.Validar.*;
import static java.util.stream.Collectors.toList;

/*
Puerto: 8081
* TODO: Eliminar endpoint: /listar ya que este no es requerido
*/


@RestController
@RequestMapping("api/v1/tarjeta")
public class TarjetaController {


    private final TarjetaMapper tarjetaMapper;
    private final TarjetaService tarjetaService;

    @Autowired
    public TarjetaController(TarjetaMapper tarjetaMapper, TarjetaService tarjetaService) {
        this.tarjetaMapper = tarjetaMapper;
        this.tarjetaService = tarjetaService;
    }


    @GetMapping("/listar")
    public ResponseEntity<?> listarTodasLasRuletas(){
        List<Tarjeta> tarjetas = tarjetaService.listarTodasLasTarjetas();
        if(tarjetas.isEmpty()){
            throw new TarjetaNoEncontrada("No hay tarjetas registradas");
        }
        return new ResponseEntity<>(tarjetas, HttpStatus.OK);
    }

    /*TODO: ControllerAdvice
    *  TODO: Creacion entidad de respuesta en el propio controlador
    *   */

    @GetMapping("/recomendar")
    public ResponseEntity<?> recomendarTarjetas(@RequestParam(required = true) String preferencia,@RequestParam(required = true) Integer edad, @RequestParam(required = true) Double salario){
        /*TODO: Validaciones de paramaetros*/
        if(!validarPreferencia(preferencia)){
            throw new PreferenciaNoValida("La preferencia " + preferencia + " no existe");

        }
        if(validarEdad(edad)){
            throw new EdadNoValida("El usuario no cumple con la edad permitida");

        }
        if(validarSalario(salario)){
            throw new SalarioNoValido("El usuario no cumple con el mimimo salario parametrizado");
        }
        List<Tarjeta> tarjetas = tarjetaService.recomendarTarjetas(preferencia, edad, salario);
        if(tarjetas.isEmpty()){
            throw new TarjetaNoEncontrada("El usuario no es apto para una tarjeta");
        }
        return new ResponseEntity<>(
                tarjetas.stream()
                        .map(tarjetaMapper::mapTarjeta)
                        .collect(toList()),
                HttpStatus.OK);
    }
}
