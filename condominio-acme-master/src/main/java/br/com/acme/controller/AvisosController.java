package br.com.acme.controller;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.acme.aviso.Aviso;
import br.com.acme.condominio.Condominio;
import br.com.acme.exception.NotFoundException;
import br.com.acme.service.AvisoService;
import br.com.acme.service.CondominioService;
import br.com.acme.service.UnidadeService;


@RestController
@RequestMapping("/api")
public class AvisosController {

	@Autowired
	private AvisoService avisoService;

	@Autowired
	private CondominioService condominioService;
	
	@Autowired
	private UnidadeService unidadeService;

	@GetMapping("/condominios/{id}/avisos")
	public Set<Aviso> getAvisosByCondominioId(@PathVariable(value = "id") Long id) {
		if (!condominioService.existsById(id)) {
			throw new NotFoundException("Condominio nao encontrado");
		}
		Optional<Condominio> condominioOpicional = this.condominioService.getCondominioById(id);
		Condominio condominio = condominioOpicional.get();
		return condominio.getAvisos();
	}
//	@PostMapping("/condominios/{condominioId}/avisos")
//	public Aviso addAvisos(@PathVariable Long condominioId, @Valid @RequestBody Aviso aviso) {
//		return this.condominioService.getCondominioById(condominioId).map(condominio -> {
//			aviso.setCondominoAvisos(condominio);
//			aviso.setId(condominioId);
//			return avisoService.saveAvisos(aviso);
//		}).orElseThrow(() -> new NotFoundException("Student not found!"));
//	}
	
	@PostMapping("/condominios/{cid}/unidades/{uid}/avisos")
	public Aviso addAvisoUnidade(@PathVariable Long cid, @PathVariable Long uid,@Valid @RequestBody Aviso aviso) {
		if (!condominioService.existsById(cid)) {
			throw new NotFoundException("Condominio nao encontrado");
		}
		return this.condominioService.getCondominioById(cid).map(condominio -> {
			aviso.setCondominoAvisos(condominio);
				return this.unidadeService.getUnidadeById(uid).map(unidade -> {
			aviso.setAvisoUnidade(unidade);
				return this.avisoService.saveAvisos(aviso);
			}).orElseThrow(() -> new NotFoundException("Unidade nao encontrado"));
		}).orElseThrow(() -> new NotFoundException("Erro ao salvar"));
	}
	
	@PutMapping("/condominios/{condominioId}/avisos/{aid}")
	public Aviso updateAviso(@PathVariable Long condominioId, @PathVariable Long aid,
			@Valid @RequestBody Aviso avisosUpdated) {
		Optional<Aviso> avisoOptional = this.avisoService.findAvisosById(aid);
		Aviso a = avisoOptional.get();
		if (!condominioService.existsById(condominioId)) {
			throw new NotFoundException("Condominio nao encontrado");
		}
		return this.avisoService.findAvisosById(a.getId()).map(aviso -> {
			aviso.setDescricaoAviso(avisosUpdated.getDescricaoAviso());
			return avisoService.saveAvisos(aviso);
		}).orElseThrow(() -> new NotFoundException("Erro ao atualizar"));
	}

	@DeleteMapping("/condominios/{condominioId}/avisos/{aid}")
	public String deleteAviso(@PathVariable Long condominioId, @PathVariable Long aid) {
		Optional<Aviso> avisoOptional = this.avisoService.findAvisosById(aid);
		Aviso a = avisoOptional.get();
		if (!condominioService.existsById(condominioId)) {
			throw new NotFoundException("Condominio nao encontrado");
		}
		return this.avisoService.findAvisosById(a.getId()).map(aviso -> {
			avisoService.deleteAviso(aviso);
			return "Deletado com sucesso!";
		}).orElseThrow(() -> new NotFoundException("Erro ao deletar"));
	}
	
}
