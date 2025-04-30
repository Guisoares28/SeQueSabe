package com.example.demo.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.demo.Models.ComidaModel;

public class UtilsService {

    public static List<ComidaModel> filtrarComida(List<ComidaModel> listaComida , List<String> listaFiltros){
        List<ComidaModel> listaFiltrada = new ArrayList<>();
        for(ComidaModel comida : listaComida){
            for(String filtro : listaFiltros){
                if(!comida.getNome().contains(filtro)){
                    listaFiltrada.add(comida);
                }
            }
        }
        return listaFiltrada;
    }

    public static ComidaModel trazerComidaAleatorio(List<ComidaModel> listaComida){
        int index = new Random().nextInt(listaComida.size());
        return listaComida.get(index);
    }


}
