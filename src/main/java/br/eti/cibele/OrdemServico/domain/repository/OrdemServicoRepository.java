/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.eti.cibele.OrdemServico.domain.repository;

import br.eti.cibele.OrdemServico.domain.model.OrdemServico;
import br.eti.cibele.OrdemServico.domain.model.StatusOrdemServico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    // 1. Busca todas por cliente ID
    List<OrdemServico> findByClienteIdAndStatus(Long clienteId, StatusOrdemServico status);

    List<OrdemServico> findByComentariosIsNotEmpty();

    List<OrdemServico> findByComentariosIsEmpty();

    List<OrdemServico> findByStatusAndComentariosIsNotEmpty(StatusOrdemServico status);

    List<OrdemServico> findByStatusAndComentariosIsEmpty(StatusOrdemServico status);

  
    
// O spring le o nome e monta a query automaticamente funcionando como um tradutor para o banco de dados
}
  

