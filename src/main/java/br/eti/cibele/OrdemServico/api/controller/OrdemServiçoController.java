/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.cibele.OrdemServico.api.controller;

import br.eti.cibele.OrdemServico.Repository.domain.service.OrdemServicoService;
import br.eti.cibele.OrdemServico.domain.dto.AtualizaStatusDTO;
import br.eti.cibele.OrdemServico.domain.model.OrdemServico;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */
@RestController
@RequestMapping("/ordem-servico")
public class OrdemServiçoController {
    @Autowired
    private OrdemServicoService ordemServicoService;
    
    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    
    
    public OrdemServico criar (@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }
@PutMapping("/atualiza-status/{ordemServicoID}")
public ResponseEntity<OrdemServico> atualizaStatus(
        @PathVariable Long ordemServicoID,
        @Valid @RequestBody AtualizaStatusDTO statusDTO) {
    Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(
            ordemServicoID,
            statusDTO.status());

    if (optOS.isPresent()){
        return ResponseEntity.ok(optOS.get());
    }else{
        return ResponseEntity.notFound().build();
    }



}



}
