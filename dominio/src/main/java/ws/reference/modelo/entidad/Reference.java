package ws.reference.modelo.entidad;

import ws.reference.modelo.dto.ReferenceDTO;

public class Reference {
    private long peso;
    private long precio;
    private String sku;
    private int stock;

    private Reference(long peso, long precio, String sku, int stock){
        this.peso = peso;
        this.precio = precio;
        this.sku = sku;
        this.stock = stock;
    }

    public static Reference crear(long peso, long precio, String sku, int stock){
        return new Reference(peso,precio,sku,stock);
    }

    public static Reference crear(ReferenceDTO referenceDTO){
        return new Reference(referenceDTO.getPeso(),referenceDTO.getPrecio(),
                referenceDTO.getSku(),referenceDTO.getStock());
    }

    public long getPeso() {
        return peso;
    }

    public float getPrecio() {
        return precio;
    }

    public String getSku() {
        return sku;
    }

    public int getStock() {
        return stock;
    }
}
