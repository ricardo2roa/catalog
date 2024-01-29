package ws.productos.adaptador.resolver.errorHTTP.exception;

public class ErrorHTTPException extends RuntimeException{
    public ErrorHTTPException(String code, String detalle){
        super(("code:"+code+","+"detalle:"+detalle));
    }
}
