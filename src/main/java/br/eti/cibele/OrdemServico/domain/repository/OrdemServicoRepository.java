/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.eti.cibele.OrdemServico.domain.repository;

import br.eti.cibele.OrdemServico.domain.model.OrdemServico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author digma
 */
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    List <OrdemServico> findByClienteId (Long clienteID);
}
