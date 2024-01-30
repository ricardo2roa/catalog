package ws.exception.modelo;

public class StorageException extends RuntimeException{
    public StorageException(String mensaje){
        super(mensaje);
    }

    public StorageException(String mensaje, Throwable cause){
        super(mensaje,cause);
    }
}
