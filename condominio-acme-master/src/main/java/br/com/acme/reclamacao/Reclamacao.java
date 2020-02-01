/**
 * 
 */
package br.com.acme.reclamacao;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.acme.responsavel.Responsavel;
import lombok.Builder;
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
@Builder
@EqualsAndHashCode
@Table(name = "tb_reclamacao")
public class Reclamacao  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String descricaoReclamacao;
	
	private LocalDate dataReclamacao;
	
//	@ManyToOne
//	@JoinColumn(name = "id_responsavel")
//	private Responsavel responsavelReclamacao;
}
