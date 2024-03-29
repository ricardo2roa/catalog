package ws.brand.modelo.entidad;

import ws.brand.modelo.dto.BrandRead;

import java.util.Date;

public class Brand {
    private String id;
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

    private Brand(int code, String name, Boolean locked, Boolean disabled, Date dateCreated){
        this.code = code;
        this.name = name;
        this.locked = locked;
        this.disabled = disabled;
        this.dateCreated = dateCreated;
    }

    private Brand(String id, int code, String name, Boolean locked, Boolean disabled, Date dateCreated) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.locked = locked;
        this.disabled = disabled;
        this.dateCreated = dateCreated;
    }

    public static Brand crear(int code, String name, Boolean locked, Boolean disabled){
        return new Brand(code,name,locked,disabled);
    }
    public static Brand recrear(int code, String name, Boolean locked, Boolean disabled,Date dateCreated){
        return new Brand(code,name,locked,disabled,dateCreated);
    }

    public static Brand recrear(BrandRead dto){
        return new Brand(dto.getId(),dto.getCode(),dto.getName(),dto.getLocked(),dto.getDisabled(),dto.getDateCreated());
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

    public String getId() {
        return id;
    }
}
