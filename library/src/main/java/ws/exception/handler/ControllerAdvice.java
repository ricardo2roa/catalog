package ws.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ws.error.modelo.ErrorDTO;
import ws.error.modelo.ErrorDetalleDTO;
import ws.exception.modelo.RequestException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorDTO> requestExceptionHandler(RequestException ex){
        ErrorDetalleDTO error = ErrorDetalleDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(ErrorDTO.builder().error(error).build(), HttpStatus.BAD_REQUEST);
    }
}
