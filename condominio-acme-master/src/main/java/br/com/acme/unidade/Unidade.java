/**
 * 
 */
package br.com.acme.unidade;



import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.acme.aviso.Aviso;
import br.com.acme.condominio.Condominio;
import br.com.acme.multas.Multa;
import br.com.acme.responsavel.Responsavel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author carlosfilho
 *
 */
@Entity
@Getter
@Setter
@Table(name = "tb_unidade")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Unidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_responsavel")
	private Responsavel responsavelUnidade;
	
	private String numeroUnidade;
	
	private String blocoUnidade;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadeMulta", fetch = FetchType.LAZY)
	private Set<Multa> multasUnidade;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "avisoUnidade", fetch = FetchType.LAZY)
	private Set<Aviso> avisosUnidade;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_condominio", nullable = false)
	@JsonIgnore
	private Condominio condominoUnidades;

	public Unidade(String numeroUnidade, String blocoUnidade) {
		super();
		this.numeroUnidade = numeroUnidade;
		this.blocoUnidade = blocoUnidade;
	}

	public Unidade() {
		super();
	}
	
}
