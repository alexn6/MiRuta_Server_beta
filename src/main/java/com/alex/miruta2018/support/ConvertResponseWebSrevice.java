/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author alextc6
 */
public class ConvertResponseWebSrevice {
        
    public static List<String> stringToCoordenate(String coord){
            String aux1 = coord.replace("[","");
            String aux2 = aux1.replace("]","");
            System.out.println("Entro a la clase de conversion: "+aux2);
            
            List<String> coord_final = new ArrayList<>(Arrays.asList(aux2.split(",")));
            
            return coord_final;
    }
}
