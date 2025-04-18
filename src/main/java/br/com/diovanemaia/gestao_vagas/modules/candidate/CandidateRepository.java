package br.com.diovanemaia.gestao_vagas.modules.candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
  /**
   * Método que busca um candidato pelo username ou email.
   *
   * @param username o username do candidato
   * @param email    o email do candidato
   * @return um Optional contendo o candidato encontrado, ou vazio se não
   *         encontrado
   */
  Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
}
