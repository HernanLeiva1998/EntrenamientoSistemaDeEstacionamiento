package ar.edu.unlp.cespi.sistemaDeEstacionamiento.models;

import java.math.BigInteger;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CuentaCorriente {
	

	@Id
	@Column(name = "id_cuenta_corriente")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable= false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private Double saldo;

   /* @OneToOne
    @JoinColumn(name = "id_automovilista")
    private Automovilista automovilista;
 */

    // Constructor, getters y setters
    public CuentaCorriente(Double saldo) {
    	this.saldo=saldo;
    	this.accountNumber= String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));

    }

    public CuentaCorriente() {
    	
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String cbu) {
		this.accountNumber = cbu;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	
	public void restarSaldo(Double costoDeEstacionamientoFinalizado) {
		this.saldo-= costoDeEstacionamientoFinalizado;
	}
	
	public Boolean saldoEsInferiorA(Double costo) {
		return costo > this.saldo;
	}
}
