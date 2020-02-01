package br.com.acme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.repository.UnidadeRepository;
import br.com.acme.unidade.Unidade;

@Service
public class UnidadeService {

	@Autowired
	private UnidadeRepository unidadeRepository;
	
	public List<Unidade> listUnidade(){
		return this.unidadeRepository.findAll();
	}
	
	public Unidade saveUnidade(Unidade unidade) {
		return this.unidadeRepository.save(unidade);
	}
	
	public Optional<Unidade> getUnidadeById(Long id){
		return this.unidadeRepository.findById(id);
	}
	
	public void deleteUnidadeById(Long id) {
		this.unidadeRepository.deleteById(id);
	}
	public void deleteUnidade (Unidade unidade) {
		this.unidadeRepository.delete(unidade);
	}

	public boolean existsById(Long id) {
		Optional<Unidade> unidade = this.unidadeRepository.findById(id);		
		if(unidade.isPresent()) {
			return true;
		}else {
			return false;
		}
		
	}
}
