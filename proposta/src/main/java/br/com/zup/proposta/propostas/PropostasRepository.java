package br.com.zup.proposta.propostas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropostasRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByDocumento(String documento);
}
