package com.ibm.academia.tarjetaapi.exceptions;

public class SalarioNoValido extends RuntimeException{
    public SalarioNoValido(String message) {
        super(message);
    }
}
