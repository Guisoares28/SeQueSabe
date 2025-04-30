package com.example.demo.Controller;


import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.ComidaModel;
import com.example.demo.Repositories.ComidaRepository;
import com.example.demo.Utils.UtilsService;


@RestController
@CrossOrigin("*")
@RequestMapping("/sequesabe")
public class SeQueSabeController {

    private final ComidaRepository comidaRepository;

    public SeQueSabeController(ComidaRepository comidaRepository) {
        this.comidaRepository = comidaRepository;
    }

    @PostMapping("/tudo")
    public ResponseEntity<Map<String, String>> pegarTudo(@RequestBody(required = false) List<String> listaItens){
        List<ComidaModel> lista = comidaRepository.findAll();
        ComidaModel comida = null;
        if(listaItens == null || listaItens.isEmpty()){ 
            comida = UtilsService.trazerComidaAleatorio(lista);
        }else{
            List<ComidaModel> listaFiltrada =  UtilsService.filtrarComida(lista, listaItens);
            comida = UtilsService.trazerComidaAleatorio(listaFiltrada);
        }
       return ResponseEntity.ok(Map.of("resultado", comida.getNome()));
    }

    
}
