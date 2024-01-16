package ws.brand.modelo.entidad;

import java.util.Date;

public class Brand {
    private int code;
    private String name;
    private Boolean locked;
    private Boolean disabled;
    private Date dateCreated;

    private Brand(int code, String name, Boolean locked, Boolean disabled){
        this.code = code;
        this.name = name;
        this.locked = locked;
        this.disabled = disabled;
        this.dateCreated = new Date();
    }

    public static Brand crear(int code, String name, Boolean locked, Boolean disabled){
        return new Brand(code,name,locked,disabled);
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Boolean getLocked() {
        return locked;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
}