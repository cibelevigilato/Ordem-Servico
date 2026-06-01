/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.cibele.OrdemServico.api.controller;

import br.eti.cibele.OrdemServico.Repository.domain.service.OrdemServicoService;
import br.eti.cibele.OrdemServico.domain.dto.AtualizaStatusDTO;
import br.eti.cibele.OrdemServico.domain.model.OrdemServico;
import br.eti.cibele.OrdemServico.domain.model.StatusOrdemServico;
import br.eti.cibele.OrdemServico.domain.repository.OrdemServicoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private OrdemServicoRepository ordemserviceRepository;
    
   
    //----------------------------------------------------------------------------------------
    //- SWAGGER ------------------------------------------------------------------------------
    @Operation(
            summary = "Obter uma OS por ID",
            description = "Retorna os detalhes de uma Ordem de Serviço com base no ID fornecido."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ordem de Serviço encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Ordem de Serviço não encontrada")
    })
    @GetMapping("/os/{id}")
    public ResponseEntity<OrdemServico> getOS(
            @PathVariable("id")
            @Parameter(description = "ID da Ordem de Serviço a ser buscada", example = "1", required = true) Long id
    ) {
        // Nota: Substitua o findAll() pela lógica real de busca por ID
        return ordemserviceRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Schema(description = "ID exclusivo da Ordem de Serviço", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "Nome ou descrição da Ordem de Serviço", example = "Manutenção de Servidor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    @Schema(description = "Preço ou valor estimado do serviço", example = "100.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private String price;
    
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }

    @PutMapping("/atualiza-status/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizaStatus(
            @PathVariable Long ordemServicoID,
            @Valid @RequestBody AtualizaStatusDTO statusDTO) {
        Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(
                ordemServicoID,
                statusDTO.status());

        if (optOS.isPresent()) {
            return ResponseEntity.ok(optOS.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/cliente/{clienteId}/abertas")

    public List<OrdemServico> listarAbertasPorCliente(@PathVariable Long clienteId) {

        return ordemServicoService.listarAbertasPorCliente(clienteId);

    }

    @GetMapping("/cliente/{clienteId}/finalizadas")

    public List<OrdemServico> listarFinalizadasPorCliente(@PathVariable Long clienteId) {

        return ordemServicoService.listarFinalizadasPorCliente(clienteId);

    }

    @GetMapping("/com-comentarios")

    public List<OrdemServico> listarComComentarios() {

        return ordemServicoService.listarComComentarios();

    }

    @GetMapping("/abertas/sem-comentarios")

    public List<OrdemServico> listarAbertasSemComentarios() {

        return ordemServicoService.listarAbertasSemComentarios();

    }

    @GetMapping("/finalizadas/com-comentarios")

    public List<OrdemServico> listarFinalizadasComComentarios() {

        return ordemServicoService.listarFinalizadasComComentarios();

    }

    @GetMapping("/finalizadas/sem-comentarios")

    public List<OrdemServico> listarFinalizadasSemComentarios() {

        return ordemServicoService.listarFinalizadasSemComentarios();

    }

}
