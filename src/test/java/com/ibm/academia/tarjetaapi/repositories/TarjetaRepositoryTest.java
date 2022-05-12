package com.ibm.academia.tarjetaapi.repositories;


import com.ibm.academia.tarjetaapi.models.entities.Tarjeta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TarjetaRepositoryTest {
    @Autowired
    private TarjetaRepository repository;


    @Test
    @DisplayName("Si preferencia no es valida")
    void preferenciaInvalidad(){

        List<Tarjeta> expected = repository.recomendarTarjetas("Education",19,7000d);
        assertThat(expected.size()).isEqualTo(0);


    }

    @Test
    @DisplayName("Si la edad no es valida")
    void edadInvalidad(){
        Integer edad = 17;
        //Integer edad = 76;
        List<Tarjeta> expected = repository.recomendarTarjetas("shopping",edad,7000d);
        assertThat(expected.size()).isEqualTo(0);

    }

    @Test
    @DisplayName("Si el salario no es valido")
    void salarioInvalido(){
        Double salario = 1700d;
        List<Tarjeta> expected = repository.recomendarTarjetas("shopping",19,salario);
        assertThat(expected.size()).isEqualTo(0);

    }

    @Test
    @DisplayName("Si los datos ingresados son validos")
    void datosValidos(){
        List<Tarjeta> expected = repository.recomendarTarjetas("shopping",19,8000d);
        assertThat(expected.size()).isGreaterThan(0);
    }

}
