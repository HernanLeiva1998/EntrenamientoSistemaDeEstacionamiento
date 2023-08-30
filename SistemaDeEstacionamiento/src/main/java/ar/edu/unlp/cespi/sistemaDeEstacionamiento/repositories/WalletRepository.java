package ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Wallet;

public interface WalletRepository extends CrudRepository<Wallet, Long> {

	Optional<Wallet> findByAccountNumber(String accountNumber);

}
