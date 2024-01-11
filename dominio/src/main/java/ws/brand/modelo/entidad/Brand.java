package ws.brand.modelo.entidad;

public class Brand {
    private int code;
    private String name;

    private Brand(int code, String name){
        this.code = code;
        this.name = name;
    }

    public static Brand crear(int code, String name){
        return new Brand(code,name);
    }
}
