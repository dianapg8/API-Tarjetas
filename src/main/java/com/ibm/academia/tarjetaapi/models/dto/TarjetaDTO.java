package com.ibm.academia.tarjetaapi.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
public class TarjetaDTO {
    private Long id;
    private String nombre;
    /*
    private String usuarioCreacion;
    private Date fechaCreacion;
    */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TarjetaDTO(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
}
