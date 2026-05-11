/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.cibele.OrdemServico.Repository.domain.service;

import br.eti.cibele.OrdemServico.Repository.ClienteRepository;
import br.eti.cibele.OrdemServico.Repository.domain.exception.DomainException;
import br.eti.cibele.OrdemServico.domain.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienterepository;
    
    public  Cliente salvar(Cliente cliente){
        Cliente clienteExistente = clienterepository.findByEmail(cliente.getEmail());
        
        
        if (clienteExistente!= null && !clienteExistente.equals(cliente)) { 
            
            throw new DomainException("Já existe um cliente cadastrado com esse email");
        }
        return clienterepository.save(cliente);
    }
    
    public void excluir (Long clienteId){
        clienterepository.deleteById(clienteId);
    }
}   
