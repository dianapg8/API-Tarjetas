package com.ibm.academia.tarjetaapi.controller;

import com.ibm.academia.tarjetaapi.exceptions.SalarioNoValido;
import com.ibm.academia.tarjetaapi.mapper.TarjetaMapper;
import com.ibm.academia.tarjetaapi.models.entities.Tarjeta;
import com.ibm.academia.tarjetaapi.services.TarjetaService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TarjetaControllerTest {

    private TarjetaController controller;

    private TarjetaService service;
    private TarjetaMapper mapper = new TarjetaMapper();
    @Autowired
    private MockMvc mvc;


   @BeforeEach
    public void setUp(){
        service = Mockito.mock(TarjetaService.class);
        controller = new TarjetaController(mapper,service);

    }


    @Test
    @DisplayName("Si un perfil es valido")
    void recomendarTarjeta() {
        //Given
        Tarjeta tarjetaUno = new Tarjeta(1L,"bsmart","shopping",18,32,7000d,14999d);
        Tarjeta tarjetaDos = new Tarjeta(2L,"Afinity Card","shopping",18,32,7000d,14999d);
        List<Tarjeta> tarjetas = new ArrayList<>();
        tarjetas.add(tarjetaUno);
        tarjetas.add(tarjetaDos);
        //when
        when(service.recomendarTarjetas("shopping",22,8000d)).thenReturn(tarjetas);
        ResponseEntity<?> httpResponse = controller.recomendarTarjetas("shopping",22,8000d);

        //Then

        assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);


    }
    @Test
    @DisplayName("Si un perfil es invalido")
    void invalidProfileShouldReturnNotFound() throws Exception {
        when(service.recomendarTarjetas("travels", 74,10500d))
                .thenReturn(new ArrayList<>());

        mvc.perform(get("/recomendar")
                        .queryParam("preferencia", "travels")
                        .queryParam("edad", "74")
                        .queryParam("salario", "10500"))
                .andExpect(status().isNotFound());
    }


}