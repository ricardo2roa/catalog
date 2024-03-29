package ws.product.modelo.dto;

import ws.information.modelo.dto.InformationDTO;

import java.util.List;

public abstract class ProductDTO {
    public String getId() {return null;}
    public int getCode() {return 0;}
    public int getTag(){return 0;}
    public int getCategory(){return 0;}
    public int getBrand(){return 0;}
    public String getName(){return null;}
    public InformationDTO getInformation(){return null;}
    public List<String> getReferences(){return null;}
}
