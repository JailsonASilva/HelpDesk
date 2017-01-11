package br.com.projeto.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "evento_material")
public class EventoMaterial extends GenericDomain {
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Evento evento;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Material material;

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}
