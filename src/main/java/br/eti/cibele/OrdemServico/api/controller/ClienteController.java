/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.cibele.OrdemServico.api.controller;

import br.eti.cibele.OrdemServico.Repository.ClienteRepository;
import br.eti.cibele.OrdemServico.Repository.domain.service.ClienteService;
import br.eti.cibele.OrdemServico.domain.model.Cliente;
import br.eti.cibele.OrdemServico.domain.model.OrdemServico;
import br.eti.cibele.OrdemServico.domain.repository.OrdemServicoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

// inicio -------------------------------->
@RestController
@RequestMapping("/ordem-servico")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    ClienteService clienteService;
        
        
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    //ignora isso prof ------------------------------------->
    //@GetMapping("/clientes")
    //public List<Cliente> listas() {
    // return clienteRepository.findByNome("KGe");
    //}
    
    
    
    // isso aqui procura por id ----------------------------->
   
    @GetMapping("/clientes/{clienteID}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteID) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteID);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    // esse aqui add ------------------------------>
   
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    
    //esse aqui atualiza -------------------------------->
    
    @PutMapping("/clientes/{clienteID}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteID, @RequestBody Cliente cliente) {

        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteID);
        cliente = clienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    
    // esse aqui exclui --------------------------------------->
    
    @DeleteMapping("/clientes/{clienteID}")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteID) {
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        clienteService.excluir(clienteID);
        return ResponseEntity.noContent().build();
    }

    // esse aqui lista todos os clientes ----------------------------->
   
    @GetMapping("/clientes")
    public List<Cliente> listas() {
        return clienteRepository.findAll();

    }
@GetMapping("/clientes/{clienteId}/ordens")
public ResponseEntity<List<OrdemServico>> ListarordensPorcliente(@PathVariable Long clienteId){
        if (!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        List<OrdemServico>ordens = ordemServicoRepository.findByClienteId(clienteId);
        return ResponseEntity.ok(ordens);
}
}   //------------------------------------------------------------->