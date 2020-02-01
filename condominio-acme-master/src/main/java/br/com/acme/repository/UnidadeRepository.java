package br.com.acme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acme.unidade.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

}
