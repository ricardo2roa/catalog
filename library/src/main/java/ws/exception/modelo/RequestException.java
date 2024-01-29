package ws.exception.modelo;

import lombok.Getter;

@Getter
public class RequestException extends RuntimeException{
    private String code;
    private String message;

    public RequestException(String code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}
