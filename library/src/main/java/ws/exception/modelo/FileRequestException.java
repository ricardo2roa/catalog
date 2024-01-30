package ws.exception.modelo;

public class FileRequestException extends RuntimeException{
    public FileRequestException(String mensaje){
        super(mensaje);
    }
}
