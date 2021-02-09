package br.com.zup.proposta.propostas;

import br.com.zup.proposta.validadores.CPFouCNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostasRequest {

   @CPFouCNPJ
   private String documento;

   @Email
   @NotBlank
   private String email;

   @NotBlank
   private String nome;

   @NotBlank
   private String endereco;

   @NotNull
   @Positive
   private BigDecimal salario;

   public PropostasRequest(String documento, @Email @NotBlank String email, @NotBlank String nome, @NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
      this.documento = documento;
      this.email = email;
      this.nome = nome;
      this.endereco = endereco;
      this.salario = salario;
   }

   public Proposta toModel() {
      return new Proposta(documento, email, nome, endereco, salario);
   }
}
