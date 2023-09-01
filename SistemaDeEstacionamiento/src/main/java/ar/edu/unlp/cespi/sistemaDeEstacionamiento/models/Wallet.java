package ar.edu.unlp.cespi.sistemaDeEstacionamiento.models;

import java.math.BigInteger;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Wallet {
	

	@Id
	@Column(name = "id_wallet")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable= false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private Double balance;

   /* @OneToOne
    @JoinColumn(name = "id_automovilista")
    private Automovilista automovilista;
 */

    // Constructor, getters y setters
    public Wallet(Double saldo) {
    	this.balance=saldo;
    	this.accountNumber= String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));

    }

    public Wallet() {
    	
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

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double saldo) {
		this.balance = saldo;
	}
	
	
	public void subtractBalance(Double amountToSubtract) {
		this.balance-= amountToSubtract;
	}
	
	public Boolean balanceIsLesserThan(Double amount) {
		return amount > this.balance;
	}
}
