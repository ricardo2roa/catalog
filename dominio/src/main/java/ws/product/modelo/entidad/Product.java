package ws.product.modelo.entidad;

import lombok.extern.java.Log;
import ws.brand.modelo.dto.BrandDTO;
import ws.information.modelo.dto.InformationDTO;
import ws.reference.modelo.dto.ReferenceDTO;

import java.util.ArrayList;
import java.util.List;
@Log
public class Product {
    private static final int FACTOR_NORMALIZAR_PESO = 100;
    public static final int MAXIMO_TAMANO_SECCION_SKU = 4;
    private String id;
    private int code;
    private int tag;
    private int category;
    private BrandDTO brand;
    private String name;
    private InformationDTO information;
    private List<ReferenceDTO> references;

    private Product(int code, int tag, int category, BrandDTO brand, String name, InformationDTO information,
                    List<ReferenceDTO> references) {
        this.code = code;
        this.tag = tag;
        this.category = category;
        this.brand = brand;
        this.name = name;
        this.information = information;
        this.references = references;
    }

    private Product(){}

    public static Product crear(SolicitudCrearProducto solicitudCrearProducto, int code){
        //Validadores
        List<ReferenceDTO> references = agregarSKUaReferencias(solicitudCrearProducto.getReferences(),
                solicitudCrearProducto.getBrand().getName(), solicitudCrearProducto.getName(), code);
        return new Product(code,
                solicitudCrearProducto.getTag(),solicitudCrearProducto.getCategory(),
                solicitudCrearProducto.getBrand(),solicitudCrearProducto.getName(),
                solicitudCrearProducto.getInformation(), references
        );
    }

    public static Product reconstruir(){
        return new Product();
    }
    private static List<ReferenceDTO> agregarSKUaReferencias(List<ReferenceDTO> referenciaInput, String brandName,
                       String nameProduct, int code){
        List<ReferenceDTO> references = new ArrayList<>();
        referenciaInput.forEach((ReferenceDTO reference)->{
            String sku = Product.generarSKU(brandName, nameProduct,code,reference);
            //log.info("Precio de referencia "+reference.getPrecio());
            references.add(new ReferenceDTO(
                    reference.getPeso(), reference.getPrecio(),
                    sku, reference.getStock()
            ));
        });
        return references;
    }


    private static String generarSKU(String brand, String name,
                                     int code, ReferenceDTO reference){
        String pesoNormalizado = Long.toString((reference.getPeso()/FACTOR_NORMALIZAR_PESO)).toUpperCase();
        String codeString = Integer.toString(code).toUpperCase();
        StringBuilder skuBuilder = new StringBuilder();
        skuBuilder.append(name.toUpperCase(), 0, Math.min(name.length(), MAXIMO_TAMANO_SECCION_SKU)).append("-")
                .append(brand.toUpperCase(),0,Math.min(brand.length(),MAXIMO_TAMANO_SECCION_SKU)).append("-");
        skuBuilder.append(pesoNormalizado,0,Math.min(pesoNormalizado.length(),MAXIMO_TAMANO_SECCION_SKU));
        skuBuilder.append("-").append(codeString, 0, Math.min(codeString.length(),MAXIMO_TAMANO_SECCION_SKU));
        return skuBuilder.toString();
    }

    public String getId() {
        return id;
    }

    public int getCode() {
        return code;
    }

    public int getTag() {
        return tag;
    }

    public int getCategory() {
        return category;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public InformationDTO getInformation() {
        return information;
    }

    public List<ReferenceDTO> getReferences() {
        return references;
    }
}
