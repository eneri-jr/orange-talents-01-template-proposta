package br.com.zup.proposta.propostas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropostasRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByDocumento(String documento);

    @Query(value = "SELECT p FROM Proposta p WHERE p.status= :status AND p.cartao is null")
    List<Proposta> findByCartoes(StatusProposta status);
}
