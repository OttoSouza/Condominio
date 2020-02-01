/**
 * 
 */
package br.com.acme.condominio;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import br.com.acme.aviso.Aviso;
import br.com.acme.multas.Multa;
import br.com.acme.unidade.Unidade;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 * @author carlosfilho
 *
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "tb_condominio")
public class Condominio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;

	private String nome;

	private String email;

	private String telefone;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "condominoMulta", fetch = FetchType.LAZY)
	private Set<Multa> multasAplicadas;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "condominoAvisos", fetch = FetchType.LAZY)
	private Set<Aviso> avisos;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "condominoUnidades", fetch = FetchType.LAZY)
    private Set<Unidade> unidades;
    
	public Condominio(String nome, String email, String telefone) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}
	
	public Condominio() {
		super();
	}

}
