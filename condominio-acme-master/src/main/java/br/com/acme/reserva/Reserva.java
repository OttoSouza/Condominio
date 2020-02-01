/**
 * 
 */
package br.com.acme.reserva;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "tb_reserva")
public class Reserva implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private LocalDate dataReserva;
	
	private LocalDateTime inicioReserva;
	
	private LocalDateTime fimReserva;
	
//	@ManyToOne
//	@JoinColumn(name = "id_responsavel")
//	private Responsavel responsavelReserva;
//	
//	@ManyToOne
//	@JoinColumn(name = "id_area_comum")
//	private AreaComum areaComumReservada;
}
