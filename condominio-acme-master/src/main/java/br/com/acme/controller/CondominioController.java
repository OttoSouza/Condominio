package br.com.acme.controller;

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
import br.com.acme.condominio.Condominio;
import br.com.acme.exception.NotFoundException;
import br.com.acme.service.CondominioService;

@RestController
@RequestMapping("/api")
public class CondominioController {

	@Autowired
	private CondominioService serviceCondominio;

	@GetMapping("/condominios")
	public List<Condominio> getAllCondominio() {
		return this.serviceCondominio.listCondominio();
	}

	@GetMapping("condominios/{id}")
	public Condominio get(@PathVariable Long id) {
		Optional<Condominio> optionalCondominio = this.serviceCondominio.getCondominioById(id);
		if (optionalCondominio.isPresent()) {
			return optionalCondominio.get();
		} else {
			throw new NotFoundException("Condominio nao existe");
		}
	}

	@PostMapping("/condominios")
	public Condominio createCondominio(@Valid @RequestBody Condominio condominio) {
		return this.serviceCondominio.saveCondominio(condominio);
	}

	@PutMapping("/condominios/{condominioId}")
	public Condominio updateCondominio(@PathVariable Long condominioId,
			@Valid @RequestBody Condominio condominioUpdate) {

		Optional<Condominio> optionalCondominio = this.serviceCondominio.getCondominioById(condominioId);
		Condominio c = optionalCondominio.get();

		if (!serviceCondominio.existsById(condominioId)) {
			throw new NotFoundException("Condominio nao esta prensente");
		}
		c.setNome(condominioUpdate.getNome());
		c.setEmail(condominioUpdate.getEmail());
		c.setTelefone(condominioUpdate.getTelefone());
		return this.serviceCondominio.saveCondominio(c);
	}

	@DeleteMapping("/condominios/{condominioId}")
	public String deleteMultas(@PathVariable Long condominioId) {
		if (!serviceCondominio.existsById(condominioId)) {
			throw new NotFoundException("Condominio não encontrado");
		}
		serviceCondominio.deleteCondominioById(condominioId);
		return "Deletado com sucesso!";
	}

}
