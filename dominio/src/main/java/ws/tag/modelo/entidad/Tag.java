package ws.tag.modelo.entidad;

import ws.brand.modelo.entidad.Brand;

import java.util.Date;

public class Tag {
    private int code;
    private String name;
    private Boolean locked;
    private Boolean disabled;
    private Date dateCreated;

    private Tag(int code, String name, Boolean locked, Boolean disabled){
        this.code = code;
        this.name = name;
        this.locked = locked;
        this.disabled = disabled;
        this.dateCreated = new Date();
    }

    public static Tag crear(int code, String name, Boolean locked, Boolean disabled){
        return new Tag(code,name,locked,disabled);
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
