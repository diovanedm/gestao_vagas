package br.com.diovanemaia.gestao_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

// DTO que representa um erro de validação
@Data // Gera getters, setters, toString, equals, etc. automaticamente (Lombok)
@AllArgsConstructor // Gera um construtor com todos os argumentos (Lombok)
public class ErrorMessageDTO {

  private String message; // Mensagem de erro (ex: "O campo nome é obrigatório")
  private String field; // Nome do campo que gerou o erro (ex: "nome")
}
