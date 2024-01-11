package ws.reference.modelo.entidad;

public class Reference {
    private long peso;
    private float price;
    private String sku;
    private int stock;

    private Reference(long peso, float price, String sku, int stock){
        this.peso = peso;
        this.price = price;
        this.sku = sku;
        this.stock = stock;
    }

    public static Reference crear(long peso, float price, String sku, int stock){
        return new Reference(peso,price,sku,stock);
    }

    public long getPeso() {
        return peso;
    }

    public float getPrice() {
        return price;
    }

    public String getSku() {
        return sku;
    }

    public int getStock() {
        return stock;
    }
}
