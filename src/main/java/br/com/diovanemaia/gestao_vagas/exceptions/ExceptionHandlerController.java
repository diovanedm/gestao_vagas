package br.com.diovanemaia.gestao_vagas.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Classe que intercepta exceções lançadas pelos controllers
@ControllerAdvice
public class ExceptionHandlerController {

  private MessageSource messageSource;

  // Injeção do MessageSource (usado pra pegar mensagens traduzidas)
  public ExceptionHandlerController(MessageSource message) {
    this.messageSource = message;
  }

  // Esse método trata exceções do tipo MethodArgumentNotValidException,
  // que acontecem quando os dados recebidos na requisição são inválidos
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    // Lista que vai guardar os erros de validação formatados
    List<ErrorMessageDTO> dto = new ArrayList<>();

    // Percorre todos os erros de campos inválidos
    e.getBindingResult().getFieldErrors().forEach(err -> {
      // Pega a mensagem de erro traduzida de acordo com o idioma atual
      String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());

      // Cria um DTO com a mensagem e o nome do campo que deu erro
      ErrorMessageDTO erro = new ErrorMessageDTO(message, err.getField());

      // Adiciona o erro na lista
      dto.add(erro);
    });

    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  }
}
