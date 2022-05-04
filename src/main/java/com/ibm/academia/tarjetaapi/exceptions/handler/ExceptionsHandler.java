package com.ibm.academia.tarjetaapi.exceptions.handler;

import com.ibm.academia.tarjetaapi.exceptions.EdadNoValida;
import com.ibm.academia.tarjetaapi.exceptions.PreferenciaNoValida;
import com.ibm.academia.tarjetaapi.exceptions.SalarioNoValido;
import com.ibm.academia.tarjetaapi.exceptions.TarjetaNoEncontrada;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;



import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = TarjetaNoEncontrada.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> tarjetaNoEncontrada(TarjetaNoEncontrada ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;

    }
    @ExceptionHandler(value = PreferenciaNoValida.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> tarjetaNoEncontrada(PreferenciaNoValida ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;

    }
    @ExceptionHandler(value = EdadNoValida.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> tarjetaNoEncontrada(EdadNoValida ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;

    }
    @ExceptionHandler(value = SalarioNoValido.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> tarjetaNoEncontrada(SalarioNoValido ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;

    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> missingRequestParameters(MissingServletRequestParameterException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", "Olvidaste agregar alguno de los siguientes parámetros a la petición");
        response.put("param", ex.getParameterName());
        response.put("paramType", ex.getParameterType());
        return response;
    }


    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> paramTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", "El tipo de dato que enviaste no coincide con el esperado");
        response.put("param", ex.getName());
        response.put("paramTypeExpected", ex.getParameter().getParameterType());
        response.put("paramTypeGot", ex.getValue().getClass().getName());
        return response;
    }
}
