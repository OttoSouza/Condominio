package br.com.acme.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import br.com.acme.exception.NotFoundException;
import br.com.acme.service.CondominioService;
import br.com.acme.service.UnidadeService;
import br.com.acme.unidade.Unidade;

@RestController
@RequestMapping("/api")
public class UnidadeController {

	@Autowired
	private UnidadeService unidadeService;

	@Autowired
	private CondominioService condominioService;

	@GetMapping("/unidades")
	public List<Unidade> getAllUnidades() {
		return this.unidadeService.listUnidade();
	}

	@GetMapping("unidades/{id}")
	public Unidade getUnidadeId(@PathVariable Long id) {
		Optional<Unidade> optionalUnidade = this.unidadeService.getUnidadeById(id);
		if (optionalUnidade.isPresent()) {
			return optionalUnidade.get();
		} else {
			throw new NotFoundException("Unidade nao existe");
		}
	}

	@GetMapping("/unidadades/semultas")
	public List<Unidade> getUnidadeSemMultas() {
		List<Unidade> listUnidade = this.unidadeService.listUnidade();
		List<Unidade> unidadesSemMultas = new ArrayList<>();
		for (Unidade u : listUnidade) {
			if (u.getMultasUnidade().isEmpty()) {
				unidadesSemMultas.add(u);
			}
		}
		return unidadesSemMultas;
	}

	@GetMapping("/unidadades/commultas")
	public List<Unidade> getUnidadeComMultas() {
		List<Unidade> listUnidade = this.unidadeService.listUnidade();
		List<Unidade> unidadesComMultas = new ArrayList<>();
		for (Unidade u : listUnidade) {
			if (!u.getMultasUnidade().isEmpty()) {
				unidadesComMultas.add(u);
			}
		}
		return unidadesComMultas;
	}

	@PostMapping("/condominios/{condominioId}/unidades")
	public Unidade addUnidadeCondominio(@PathVariable Long condominioId, @Valid @RequestBody Unidade unidade) {
		return this.condominioService.getCondominioById(condominioId).map(condominio -> {
			unidade.setCondominoUnidades(condominio);
			unidade.setId(condominioId);
			return unidadeService.saveUnidade(unidade);
		}).orElseThrow(() -> new NotFoundException("Erro ao salvar Unidade"));
	}

	@PutMapping("/condominios/{condominioId}/unidades/{unidadeId}")
	public Unidade updateUnidade(@PathVariable Long condominioId, @PathVariable Long unidadeId,
			@Valid @RequestBody Unidade unidadeUpdated) {
		Optional<Unidade> unidadeOptional = this.unidadeService.getUnidadeById(unidadeId);
		Unidade u = unidadeOptional.get();
		if (!condominioService.existsById(condominioId)) {
			throw new NotFoundException("Condominio nao esta presente");
		}
		return this.unidadeService.getUnidadeById(u.getId()).map(unidade -> {
			unidade.setBlocoUnidade(unidadeUpdated.getBlocoUnidade());
			unidade.setNumeroUnidade(unidadeUpdated.getNumeroUnidade());
			return unidadeService.saveUnidade(unidade);
		}).orElseThrow(() -> new NotFoundException("Erro ao atualizar"));
	}

	@DeleteMapping("/condominios/{condominioId}/unidades/{unidadeId}")
	public String deleteUnidades(@PathVariable Long condominioId, @PathVariable Long unidadeId) {
		Optional<Unidade> unidadeOptional = this.unidadeService.getUnidadeById(unidadeId);
		Unidade u = unidadeOptional.get();
		if (!condominioService.existsById(condominioId)) {
			throw new NotFoundException("Condominio not found!");
		}
		return this.unidadeService.getUnidadeById(u.getId()).map(unidade -> {
			unidadeService.deleteUnidade(unidade);
			return "Deleted Successfully!";
		}).orElseThrow(() -> new NotFoundException("Erro ao deletar"));
	}

}
