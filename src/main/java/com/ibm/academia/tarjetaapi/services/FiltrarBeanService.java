package com.ibm.academia.tarjetaapi.services;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

/*
* filterBean() -> Filtra los campos no deseados de una entidad de respuesta */
public class FiltrarBeanService {
    // fields is an array of field names you wish not to send in your response
    // beanFilterName is value you give when you annotate your bean class
    // dataSet is the data you want to filter
    public static MappingJacksonValue filterBean(String[] fields, String beanFilterName, Object dataSet ) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept(fields);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter(beanFilterName, filter);
        MappingJacksonValue jacksonValue = new MappingJacksonValue(dataSet);
        jacksonValue.setFilters(filterProvider);
        return jacksonValue;
    }
}
