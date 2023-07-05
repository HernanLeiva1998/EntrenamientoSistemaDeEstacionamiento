/*package cespi.unlp.edu.ar.SistemaDeEstacionamiento.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
//todavia no se usa
@Entity
public class Contraseña {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contraseña;
    
    @OneToOne
    @JoinColumn(name = "automovilista_id")
    private Automovilista automovilista;
	

	public Contraseña() {
	}
}*/