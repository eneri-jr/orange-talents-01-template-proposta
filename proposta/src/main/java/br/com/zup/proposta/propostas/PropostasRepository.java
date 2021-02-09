package br.com.zup.proposta.propostas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostasRepository extends JpaRepository<Proposta, Long> {
}
