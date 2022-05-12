package com.ibm.academia.tarjetaapi.services;

import com.ibm.academia.tarjetaapi.exceptions.EdadNoValida;
import com.ibm.academia.tarjetaapi.exceptions.PreferenciaNoValida;
import com.ibm.academia.tarjetaapi.exceptions.SalarioNoValido;
import com.ibm.academia.tarjetaapi.exceptions.TarjetaNoEncontrada;
import com.ibm.academia.tarjetaapi.models.entities.Tarjeta;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TarjetaServiceTest {

    @Autowired
    private TarjetaService service;

    @Test
    @DisplayName("Edad invalidad")
    void edadInvalidad(){

        assertThrows(EdadNoValida.class, () -> {
           service.recomendarTarjetas("shopping",17,7000d);
        });
    }
    @Test
    @DisplayName("Preferencia invalida")
    void preferenciaInvalidad(){

        assertThrows(PreferenciaNoValida.class, () -> {
            service.recomendarTarjetas("education",19,7000d);
        });
    }
    @Test
    @DisplayName("Salario invalido")
    void salarioInvalido(){

        assertThrows(SalarioNoValido.class, () -> {
            service.recomendarTarjetas("shopping",19,1000d);
        });
    }

    @Test
    @DisplayName("Tarjeta no encontrada")
    void tarjetaNoEncontrada(){

        assertThrows(TarjetaNoEncontrada.class, () -> {
            service.recomendarTarjetas("travels",74,10400d);
        });
    }


}
