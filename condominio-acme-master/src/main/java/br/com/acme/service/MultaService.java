package br.com.acme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.multas.Multa;
import br.com.acme.repository.MultaRepository;

@Service
public class MultaService {
	@Autowired
	private MultaRepository multaRepository;
	
	public List<Multa> listAllMultas(){
		return this.multaRepository.findAll();
	}
	
	public Multa saveMulta(Multa multa) {
		return this.multaRepository.save(multa);
	}
	
	public void deleteMultaById(Long id) {
		this.multaRepository.deleteById(id);
	}
	
	public Optional<Multa> findMultaById(Long id){
		return this.multaRepository.findById(id);
	}
	
	public void deleteMulta(Multa multa) {
		this.multaRepository.delete(multa);
	}
}
