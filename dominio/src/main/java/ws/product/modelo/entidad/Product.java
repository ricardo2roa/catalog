package ws.product.modelo.entidad;

import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import ws.brand.modelo.entidad.Brand;
import ws.category.modelo.entidad.Category;
import ws.information.modelo.entidad.Information;
import ws.reference.modelo.dto.ReferenceDTO;
import ws.reference.modelo.entidad.Reference;
import ws.tag.modelo.entidad.Tag;

import java.util.List;
@NoArgsConstructor
public class Product {
    private static final int FACTOR_NORMALIZAR_PESO = 100;
    public static final int MAXIMO_TAMANO_SECCION_SKU = 4;
    private String id;
    private int code;

    private int tag;
    private int category;
    private int brand;
    @Transient
    private Tag fullTag;
    @Transient
    private Category fullCategory;
    @Transient
    private Brand fullBrand;
    private String name;
    private Information information;
    private List<Reference> references;

    private Product(String id, int code, Tag fullTag, Category fullCategory, Brand fullBrand,
                   String name, Information information, List<Reference> references) {
        this.id = id;
        this.code = code;
        this.fullTag = fullTag;
        this.fullCategory = fullCategory;
        this.fullBrand = fullBrand;
        this.name = name;
        this.information = information;
        this.references = references;
    }

    private Product(int code, int tag, int category, int brand, String name, Information information,
                    List<Reference> references) {
        this.code = code;
        this.tag = tag;
        this.category = category;
        this.brand = brand;
        this.name = name;
        this.information = information;
        this.references = references;
    }
    public static Product crear(SolicitudCrearProducto solicitudCrearProducto, int code, List<Reference> references){
        //Validadores
        /*List<ReferenceDTO> references = agregarSKUaReferencias(solicitudCrearProducto.getReferences(),
                solicitudCrearProducto.getBrand(), solicitudCrearProducto.getName(), code);*/
        return new Product(code,
                solicitudCrearProducto.getTag(),solicitudCrearProducto.getCategory(),
                solicitudCrearProducto.getBrand(),solicitudCrearProducto.getName(),
                Information.crear(solicitudCrearProducto.getInformation()), references
        );
    }

    public static Product recrear(String id, int code, Tag tag, Category category, Brand brand, String name, Information information,
                                  List<Reference> references) {
        //Validadores
        return new Product(id,code, tag, category, brand, name, information, references);
    }
    public static List<Reference> agregarSKUaReferencias(List<ReferenceDTO> referenciaInput, String nameBrand,
                       String nameProduct, int code){
        /*List<ReferenceDTO> references = new ArrayList<>();
        referenciaInput.forEach((ReferenceDTO reference)->{
            String sku = Product.generarSKU(brand, nameProduct,code,reference);
            //log.info("Precio de referencia "+reference.getPrecio());
            references.add(new ReferenceDTO(
                    reference.getPeso(), reference.getPrecio(),
                    sku, reference.getStock()
            ));
        });*/
        return referenciaInput.stream().map(reference->Reference.crear(reference.getPeso(),reference.getPrecio(),
                Product.generarSKU(nameBrand, nameProduct,code,reference),reference.getStock())).toList();
    }


    private static String generarSKU(String brand, String name,
                                     int code, ReferenceDTO reference){
        String pesoNormalizado = Long.toString((reference.getPeso()/FACTOR_NORMALIZAR_PESO)).replaceAll("\\s", "").toUpperCase();
        String codeString = Integer.toString(code).replaceAll("\\s", "").toUpperCase();
        StringBuilder skuBuilder = new StringBuilder();
        skuBuilder.append(name.replaceAll("\\s", "").toUpperCase(), 0, Math.min(name.length(), MAXIMO_TAMANO_SECCION_SKU)).append("-")
                .append(brand.replaceAll("\\s", "").toUpperCase(),0,Math.min(brand.length(),MAXIMO_TAMANO_SECCION_SKU)).append("-");
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

    public int getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public Information getInformation() {
        return information;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public Tag getFullTag() {
        return fullTag;
    }

    public Category getFullCategory() {
        return fullCategory;
    }

    public Brand getFullBrand() {
        return fullBrand;
    }
}
