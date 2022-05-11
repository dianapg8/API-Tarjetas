package com.ibm.academia.tarjetaapi.exceptions;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String mensaje){
        super(mensaje);
    }

    private static final long serialVersionUID = -3315028917411144150L;
}
