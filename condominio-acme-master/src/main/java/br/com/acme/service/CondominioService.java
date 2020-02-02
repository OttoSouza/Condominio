package br.com.acme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.condominio.Condominio;
import br.com.acme.repository.CondominioRepository;

@Service
public class CondominioService {

	@Autowired
	private CondominioRepository repository;
	
	public List<Condominio> listCondominio(){
		return this.repository.findAll();
	}
	
	public Condominio saveCondominio(Condominio condominio) {
		return this.repository.save(condominio);
	}
	
	public Optional<Condominio> getCondominioById(Long id){
		return this.repository.findById(id);
	}
	
	public void deleteCondominioById(Long id) {
		this.repository.deleteById(id);
	}

	public boolean existsById(Long id) {
		Optional<Condominio> condominio = this.repository.findById(id);		
		if(condominio.isPresent()) {
			return true;
		}else {
			return false;
		}
		
	}

	
	
}
