package br.com.acme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.aviso.Aviso;
import br.com.acme.repository.AvisoRepository;

@Service
public class AvisoService {
	@Autowired
	private AvisoRepository avisoRepository;
	
	public List<Aviso> listAllAvisos(){
		return this.avisoRepository.findAll();
	}
	
	public Aviso saveAvisos(Aviso aviso) {
		return this.avisoRepository.save(aviso);
	}
	
	public void deleteAvisosById(Long id) {
		this.avisoRepository.deleteById(id);
	}
	
	public Optional<Aviso> findAvisosById(Long id){
		return this.avisoRepository.findById(id);
	}
	
	public void deleteAviso(Aviso aviso) {
		this.avisoRepository.delete(aviso);
	}
}
