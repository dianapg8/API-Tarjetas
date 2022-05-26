package com.ibm.academia.tarjetaapi.controller;

import com.ibm.academia.tarjetaapi.exceptions.TarjetaNoEncontrada;
import com.ibm.academia.tarjetaapi.mapper.TarjetaMapper;
import com.ibm.academia.tarjetaapi.models.dto.TarjetaDTO;
import com.ibm.academia.tarjetaapi.models.entities.Tarjeta;
import com.ibm.academia.tarjetaapi.services.TarjetaService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

/*
Puerto: 8081
* TODO: Eliminar endpoint: /listar ya que este no es requerido
*/


@RestController
public class TarjetaController {

    Logger logger = LoggerFactory.getLogger(TarjetaController.class);


    @Autowired
    private CircuitBreakerFactory cbFactory;

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

    @Operation(summary = "Buscar tarjeta adecuada",description = "Devolverá una lista de tarjetas " +
            "preferibles para un perfil de usuario en especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Tarjetas encontradas.",content = {@Content(mediaType = "application/json",schema = @Schema(implementation = TarjetaDTO.class))}),
            @ApiResponse(responseCode = "404",description = "Perfil no valido para obtener una tarjeta.",content = @Content),
            @ApiResponse(responseCode = "400",description = "Petición no valida.",content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "preferencia",description = "Preferencia del usuario",required = true),
            @Parameter(name = "edad",description = "Edad del usuario",required = true),
            @Parameter(name = "salario",description = "Salario del usuario",required = true)
    })

    //@CircuitBreaker(name ="recomendarTarjeta",fallbackMethod = "metodoAlternativo")
    @GetMapping("/recomendar")
    public ResponseEntity<?> recomendarTarjetas(@RequestParam(required = true) String preferencia,
                                                @RequestParam(required = true) Integer edad,
                                                @RequestParam(required = true) Double salario){

        return cbFactory.create("recomendar").run(()->new ResponseEntity<>(
                tarjetaService.recomendarTarjetas(preferencia,edad,salario).stream()
                        .map(tarjetaMapper::mapTarjeta)
                        .collect(toList()),
                HttpStatus.OK),e->metodoAlternativo());
    }

    
    @GetMapping("/alternativo")
    public ResponseEntity<?> metodoAlternativo(){
        return new ResponseEntity<>("Estamos trabajando en crear " +
                "la mejor tarjeta para usted, esperela pronto",HttpStatus.OK);
    }


}
