package ws.infraestructura.exception.modelo;

public class ErrorParserStringToDateException extends RuntimeException{
    public ErrorParserStringToDateException(String mensaje){
        super(mensaje);
    }
}
