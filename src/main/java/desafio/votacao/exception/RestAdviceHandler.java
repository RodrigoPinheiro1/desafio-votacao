package desafio.votacao.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class RestAdviceHandler {



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageGlobalException CamposInvalidos(MethodArgumentNotValidException exception) {
        var erros = exception.getFieldErrors();

        List<ErrorValidation> errorValidations = erros.stream().map(ErrorValidation::new).toList();

        return new MessageGlobalException(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), "error fields", errorValidations);
    }




    @ExceptionHandler(EntityNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {

        return "Recurso nao existe";
    }

}
