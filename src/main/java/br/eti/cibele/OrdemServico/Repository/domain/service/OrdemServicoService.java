/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.cibele.OrdemServico.Repository.domain.service;

import br.eti.cibele.OrdemServico.Repository.domain.exception.DomainException;
import br.eti.cibele.OrdemServico.domain.model.OrdemServico;
import br.eti.cibele.OrdemServico.domain.model.StatusOrdemServico;
import br.eti.cibele.OrdemServico.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */
@Service
public class OrdemServicoService {
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    public OrdemServico criar (OrdemServico ordemServico) {
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());
        
        return ordemServicoRepository.save(ordemServico);
    }

public Optional<OrdemServico> atualizaStatus (Long ordemServicoID, StatusOrdemServico status) {
    
    Optional<OrdemServico> optOrdemServico = ordemServicoRepository.findById(ordemServicoID);


   if (optOrdemServico.isPresent()) { 
        OrdemServico ordemServico = optOrdemServico.get();
       
        if (ordemServico.getStatus()== StatusOrdemServico.ABERTA && status != StatusOrdemServico.ABERTA){
           
           ordemServico.setStatus(status);
           ordemServico.setDataAbertura(LocalDateTime.now());
           ordemServicoRepository.save(ordemServico);
           return Optional.of(ordemServico);
           
        } else{ 

            return Optional.empty();
        }
     }else{
        throw new DomainException("Não existe OS com o id" + ordemServicoID);
    } 
}

}
