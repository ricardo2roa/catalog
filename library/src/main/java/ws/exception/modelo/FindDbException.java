package ws.exception.modelo;

public class FindDbException extends RuntimeException{
    private String code;
    private String message;

    public FindDbException(String code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}
