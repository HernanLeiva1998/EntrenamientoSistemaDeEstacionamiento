package cespi.unlp.edu.ar.SistemaDeEstacionamiento.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CuentaCorriente {
	@Id
	@Column(name = "id_cuenta_corriente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable= false, unique = true)
    private String cbu;

    @Column(nullable = false)
    private Double saldo;

    @ManyToOne
    @JoinColumn(name = "id_automovilista")
    private Automovilista automovilista;

    // Constructor, getters y setters
    public CuentaCorriente() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Automovilista getAutomovilista() {
		return automovilista;
	}

	public void setAutomovilista(Automovilista automovilista) {
		this.automovilista = automovilista;
	}
}
