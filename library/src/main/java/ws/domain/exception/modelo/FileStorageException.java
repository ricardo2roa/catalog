package ws.domain.exception.modelo;

public class FileStorageException extends StorageException{

    public FileStorageException(String mensaje) {
        super(mensaje);
    }

    public FileStorageException(String mensaje, Throwable cause) {
        super(mensaje, cause);
    }
}
