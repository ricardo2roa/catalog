package ws.infraestructura.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ws.domain.exception.modelo.*;
import ws.infraestructura.error.modelo.ErrorDTO;
import ws.infraestructura.error.modelo.ErrorDetalleDTO;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorDTO> requestExceptionHandler(RequestException ex){
        ErrorDetalleDTO error = ErrorDetalleDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(ErrorDTO.builder().error(error).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FindDbException.class)
    public ResponseEntity<ErrorDTO> findDbExceptionHandler(FindDbException ex){
        ErrorDetalleDTO error = ErrorDetalleDTO.builder().code("500").message(ex.getMessage()).build();
        return new ResponseEntity<>(ErrorDTO.builder().error(error).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = StorageException.class)
    public ResponseEntity<ErrorDTO> StorageExceptionHandler(StorageException ex){
        ErrorDetalleDTO error = ErrorDetalleDTO.builder().code("500").message(ex.getMessage()).build();
        return new ResponseEntity<>(ErrorDTO.builder().error(error).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = FileRequestException.class)
    public ResponseEntity<ErrorDTO> fileRequestExceptionHandler(FileRequestException ex){
        ErrorDetalleDTO error = ErrorDetalleDTO.builder().code("400").message(ex.getMessage()).build();
        return new ResponseEntity<>(ErrorDTO.builder().error(error).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FileStorageException.class)
    public ResponseEntity<ErrorDTO> fileStorageExceptionHandler(FileStorageException ex){
        ErrorDetalleDTO error = ErrorDetalleDTO.builder().code("500").message(ex.getMessage()).build();
        return new ResponseEntity<>(ErrorDTO.builder().error(error).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
