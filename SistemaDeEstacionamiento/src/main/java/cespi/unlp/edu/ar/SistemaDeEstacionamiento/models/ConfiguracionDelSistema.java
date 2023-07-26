package cespi.unlp.edu.ar.SistemaDeEstacionamiento.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ConfiguracionDelSistema {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_configuracion_del_sistema")
    private Long id;
	
	private Double precioPorHora;

	public ConfiguracionDelSistema() {
		
	}

	public ConfiguracionDelSistema(Double valor) {
		this.precioPorHora=valor;
	}
	
	public void setId(Long id) {
		this.id=id;
	}

	public Double getPrecioPorHora() {
		return precioPorHora;
	}

	public void setPrecioPorHora(Double precioPorHora) {
		this.precioPorHora = precioPorHora;
	}
}
