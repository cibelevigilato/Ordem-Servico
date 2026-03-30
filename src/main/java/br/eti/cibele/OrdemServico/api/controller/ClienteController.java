/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.cibele.OrdemServico.api.controller;

import br.eti.cibele.OrdemServico.domain.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */
@RestController
public class ClienteController {
 
    @PersistenceContext
    private EntityManager manager;
    
    
    
  public List<Cliente> listaClientes;
  
  
  @GetMapping("/clientes")    
  public List<Cliente>listas(){
     
   return manager.createQuery("from  Cliente", Cliente.class).getResultList();
   
    
    }

}