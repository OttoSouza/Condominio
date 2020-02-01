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

import br.com.acme.condominio.Condominio;
import br.com.acme.exception.NotFoundException;
import br.com.acme.multas.Multa;
import br.com.acme.service.CondominioService;
import br.com.acme.service.MultaService;
import br.com.acme.service.UnidadeService;

@RestController
@RequestMapping("/api")
public class MultaController {
	@Autowired
	private MultaService multaService;
	@Autowired
	private CondominioService condominioService;
	@Autowired
	private UnidadeService unidadeService;

	@GetMapping("/condominios/{id}/multas")
	public Set<Multa> getMultaByCondominioId(@PathVariable(value = "id") Long id) {

		if (!condominioService.existsById(id)) {
			throw new NotFoundException("Condominio nao esta prensente");
		}
		Optional<Condominio> condominioOpicional = this.condominioService.getCondominioById(id);
		Condominio condominio = condominioOpicional.get();
		return condominio.getMultasAplicadas();
	}

//	@PostMapping("/condominios/{id}/multas")
//	public Multa addMultaCondominio(@PathVariable Long id, @Valid @RequestBody Multa multa) {
//		return this.condominioService.getCondominioById(id).map(condominio -> {
//			multa.setCondominoMulta(condominio);			
//			return this.multaService.saveMulta(multa);
//		}).orElseThrow(() -> new NotFoundException("Condominio nao encontrado"));
//	}

	@PostMapping("/condominios/{condominioId}/unidades/{unidadeId}/multas")
	public Multa addMultaUnidade(@PathVariable Long condominioId, @PathVariable Long unidadeId,
			@Valid @RequestBody Multa multa) {
		if (!condominioService.existsById(condominioId)) {
			throw new NotFoundException("Condominio nao esta presente");
		}
		
		return this.condominioService.getCondominioById(condominioId).map(condominio -> {
			multa.setCondominoMulta(condominio);
			return this.unidadeService.getUnidadeById(unidadeId).map(unidade -> {
				multa.setUnidadeMulta(unidade);
				return this.multaService.saveMulta(multa);
			}).orElseThrow(() -> new NotFoundException("Unidade nao encontrado"));

		}).orElseThrow(() -> new NotFoundException("Condominio nao encontrado"));
	}

	@PutMapping("/condominios/{condominioId}/multas/{multaId}")
	public Multa updateMulta(@PathVariable Long condominioId, @PathVariable Long multaId,
			@Valid @RequestBody Multa multaUpdated) {
		Optional<Multa> multaOptional = this.multaService.findMultaById(multaId);
		Multa m = multaOptional.get();
		if (!condominioService.existsById(condominioId)) {
			throw new NotFoundException("Condominio nao esta prensente");
		}
		return this.multaService.findMultaById(m.getId()).map(multa -> {
			multa.setDescricaoMulta(multaUpdated.getDescricaoMulta());
			multa.setDataMulta(multaUpdated.getDataMulta());
			multa.setValorMulta(multaUpdated.getValorMulta());
			return multaService.saveMulta(multa);
		}).orElseThrow(() -> new NotFoundException("Multa not found!"));
	}

	@DeleteMapping("/condominios/{condominioId}/multas/{multaId}")
	public String deleteMultas(@PathVariable Long condominioId, @PathVariable Long multaId) {
		Optional<Multa> multaOptional = this.multaService.findMultaById(multaId);
		Multa m = multaOptional.get();
		if (!condominioService.existsById(condominioId)) {
			throw new NotFoundException("Condominio not found!");
		}
		return this.multaService.findMultaById(m.getId()).map(multa -> {
			multaService.deleteMulta(multa);
			return "Deleted Successfully!";
		}).orElseThrow(() -> new NotFoundException("Contact not found!"));
	}
}
