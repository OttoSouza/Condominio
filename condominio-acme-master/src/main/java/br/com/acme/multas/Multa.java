/**
 * 
 */
package br.com.acme.multas;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.acme.condominio.Condominio;
import br.com.acme.unidade.Unidade;

import lombok.Getter;
import lombok.Setter;

/**
 * @author carlosfilho
 *
 */
@Entity
@Getter
@Setter
@Table(name = "tb_multas")
public class Multa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String descricaoMulta;

	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dataMulta;

	private Double valorMulta;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_unidade", nullable = false)
	@JsonIgnore
	private Unidade unidadeMulta;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_condominio", nullable = false)
	@JsonIgnore
	private Condominio condominoMulta;

	public Multa() {
		super();
	}

	public Multa(String descricaoMulta, LocalDate dataMulta, Double valorMulta) {
		super();
		this.descricaoMulta = descricaoMulta;
		this.dataMulta = dataMulta;
		this.valorMulta = valorMulta;
	}
 

}
