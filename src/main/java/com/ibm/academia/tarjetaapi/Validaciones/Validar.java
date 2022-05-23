package com.ibm.academia.tarjetaapi.Validaciones;



public class Validar {
    public static boolean validarEdad(Integer edad){
        return edad<18 || edad > 75;

    }

    public static boolean validarPreferencia(String preferencia){
      return (preferencia.equalsIgnoreCase("shopping") ||
              preferencia.equalsIgnoreCase("mystyle")||
              preferencia.equalsIgnoreCase("mybusiness") ||
              preferencia.equalsIgnoreCase("travels")) ||
              preferencia.equalsIgnoreCase("sports") ||
              preferencia.equalsIgnoreCase("help");

    }
    public static boolean validarSalario(Double salario){
        return salario<7000;
    }
}
