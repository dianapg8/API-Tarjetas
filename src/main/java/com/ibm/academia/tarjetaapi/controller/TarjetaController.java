package com.ibm.academia.tarjetaapi.controller;

import com.ibm.academia.tarjetaapi.services.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
Puerto: 8081
* TODO: Eliminar endpoint: /listar ya que este no es requerido
*/
@RestController
@RequestMapping("api/v1/tarjeta")
public class TarjetaController {

    private final TarjetaService tarjetaService;

    @Autowired
    public TarjetaController(TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarTodasLasRuletas(){
        return tarjetaService.listarTodasLasTarjetas();
    }

    @GetMapping("/recomendar")
    public ResponseEntity<?> recomendarTarjetas(@RequestParam String preferencia,@RequestParam Integer edad, @RequestParam Double salario){
        /*TODO: Validaciones de paramaetros*/
        return tarjetaService.recomendarTarjetas(preferencia, edad, salario);
    }
}
