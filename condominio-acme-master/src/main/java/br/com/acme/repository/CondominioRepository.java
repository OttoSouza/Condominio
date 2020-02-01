package br.com.acme.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acme.condominio.Condominio;

public interface CondominioRepository extends Serializable,JpaRepository<Condominio, Long> {

}
