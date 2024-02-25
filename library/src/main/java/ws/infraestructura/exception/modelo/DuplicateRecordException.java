package ws.infraestructura.exception.modelo;

public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException(String mensaje){
        super(mensaje);
    }
}
