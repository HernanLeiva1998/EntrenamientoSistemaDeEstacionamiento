package cespi.unlp.edu.ar.SistemaDeEstacionamiento.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ConfiguracionDelSistema {
	@Id
	@Column(name = "id_configuracion_del_sistema")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Double precioPorHora;

	public ConfiguracionDelSistema(Double valor) {
		this.precioPorHora=valor;
	}

	public Double getPrecioPorHora() {
		return precioPorHora;
	}

	public void setPrecioPorHora(Double precioPorHora) {
		this.precioPorHora = precioPorHora;
	}
}
