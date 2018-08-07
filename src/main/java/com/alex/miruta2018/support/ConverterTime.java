/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.support;

import java.sql.Time;
import java.time.LocalTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author alextc6
 */
@Converter(autoApply = true)
public class ConverterTime implements AttributeConverter<LocalTime, Time>{
    
    @Override
    public Time convertToDatabaseColumn(LocalTime localTime) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(localTime == null){
            return null;
        }
//        System.out.println("Entro a convert LOCAL-TIME");
        
        return Time.valueOf(localTime);
    }

    @Override
    public LocalTime convertToEntityAttribute(Time time) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(time == null){
            return null;
        }
        
//        System.out.println("Entro a convert TIME-SQL");
        
        return time.toLocalTime();
    }
}
