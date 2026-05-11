/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.eti.cibele.OrdemServico.domain.dto;

import br.eti.cibele.OrdemServico.domain.model.StatusOrdemServico;
import jakarta.validation.constraints.NotNull;
    

/**
 *
 * @author digma
 */
public record AtualizaStatusDTO (
@NotNull(message ="Status é obrigatório")
        StatusOrdemServico status
        
){}
