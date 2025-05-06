package com.example.demo.Controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.ComidaEnum;
import com.example.demo.Models.ComidaModel;
import com.example.demo.Models.ContadorModel;
import com.example.demo.Repositories.ComidaRepository;
import com.example.demo.Repositories.ContRepository;
import com.example.demo.Utils.UtilsService;




@RestController
@CrossOrigin("*")
@RequestMapping("/sequesabe")
public class SeQueSabeController {

    private final ComidaRepository comidaRepository;

    private final ContRepository contRepository;

    public SeQueSabeController(ComidaRepository comidaRepository, ContRepository contRepository) {
        this.comidaRepository = comidaRepository;
        this.contRepository = contRepository;    }

    @PostMapping("/tudo")
    public ResponseEntity<Map<String, String>> pegarTudo(@RequestBody(required = false) List<String> listaItens,
                                                        @RequestParam(value = "tipo", required = false) String tipo){

        List<ComidaModel> lista = new ArrayList<>();
        ContadorModel cont = contRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("contador não encontrado"));
        if(tipo == null || tipo.isEmpty()){
            lista = comidaRepository.findAll();
        }else if(tipo.equalsIgnoreCase("casa")){
            lista = comidaRepository.findByTipo(ComidaEnum.RECEITA);
        }else if(tipo.equalsIgnoreCase("fora")){
            lista = comidaRepository.findByTipo(ComidaEnum.CULINARIA);
        }
        ComidaModel comida = null;
        if(listaItens == null || listaItens.isEmpty()){ 
            comida = UtilsService.trazerComidaAleatorio(lista);
        }else{
            List<ComidaModel> listaFiltrada =  UtilsService.filtrarComida(lista, listaItens);
            comida = UtilsService.trazerComidaAleatorio(listaFiltrada);
        }
        cont.incrementar();
        contRepository.save(cont);
       return ResponseEntity.ok(Map.of("resultado", comida.getNome(),
                                        "contador", cont.getCont().toString()
       ));
    }

    @PostMapping("/criarContador")
    public ResponseEntity<String> criarContador(@RequestBody ContadorModel contadorModel){
        contRepository.save(contadorModel);
        return ResponseEntity.ok("Contador adicionado com sucesso");
    }

}
