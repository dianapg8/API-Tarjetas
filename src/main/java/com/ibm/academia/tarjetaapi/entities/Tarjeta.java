package com.ibm.academia.tarjetaapi.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

/*Entidad tarjeta
* TODO: campos fechas ? */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("tarjetaFiltro")
@Builder
@Entity
@Table(name = "tarjetas", schema = "tarjetas_schema")
public class Tarjeta implements Serializable {
    @Id
    @SequenceGenerator(
            name = "ruleta_sequence",
            sequenceName = "ruleta_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "ruleta_sequence"
    )
    @Column(name = "id_tarjeta", updatable = false)
    private Long idTarjeta;

    @Column(name = "nombre_tarjeta", nullable = false)
    private String nombreTarjeta;

    @Column(name = "preferencia", nullable = false)
    private String preferencia;

    @Column(name = "edad_min", nullable = false)
    private Integer edadMinima;

    @Column(name = "edad_max", nullable = false)
    private Integer edadMaxima;

    @Column(name = "salario_min", nullable = false)
    private Double salarioMinimo;

    @Column(name = "salario_max")
    private Double salarioMaximo;
/*
    @Column(name = "fecha_alta", nullable = false)
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;
 */
    private static final long serialVersionUID = 2536383062378803095L;
}
