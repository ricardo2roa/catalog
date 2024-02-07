package ws.product.modelo.entidad;

import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import ws.brand.modelo.entidad.Brand;
import ws.category.modelo.entidad.Category;
import ws.exception.validador.ValidarDatos;
import ws.information.modelo.entidad.Information;
import ws.reference.modelo.dto.ReferenceDTO;
import ws.reference.modelo.entidad.Reference;
import ws.tag.modelo.entidad.Tag;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Log
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
    private List<String> references;
    @Transient
    private List<Reference> fullreferences;

    private Product(String id, int code, Tag fullTag, Category fullCategory, Brand fullBrand,
                   String name, Information information, List<Reference> references) {
        this.id = id;
        this.code = code;
        this.fullTag = fullTag;
        this.fullCategory = fullCategory;
        this.fullBrand = fullBrand;
        this.name = name;
        this.information = information;
        this.fullreferences = references;
    }

    private Product(int code, int tag, int category, int brand, String name, Information information,
                    List<String> references) {
        this.code = code;
        this.tag = tag;
        this.category = category;
        this.brand = brand;
        this.name = name;
        this.information = information;
        this.references = references;
    }
    public static Product crear(SolicitudProducto solicitudProducto, int code, List<String> references){
        //Validadores
        ValidarDatos.siEsMayoraCero("codigo de producto",code);
        Product.validarCampos(solicitudProducto);
        references.spliterator().forEachRemaining(id->ValidarDatos.
                siEsVacioONull("id de referencia",id));

        return new Product(code,
                solicitudProducto.getTag(),solicitudProducto.getCategory(),
                solicitudProducto.getBrand(),solicitudProducto.getName(),
                Information.crear(solicitudProducto.getInformation()), references
        );
    }

    public static Product recrear(String id, int code, Tag tag, Category category, Brand brand, String name, Information information,
                                  List<Reference> references) {
        //Validadores
        return new Product(id,code, tag, category, brand, name, information, references);
    }

    public static void validarCampos(SolicitudProducto solicitudProducto){
        ValidarDatos.siEsMayoraCero("codigo de etiqueta", solicitudProducto.getTag());
        ValidarDatos.siEsMayoraCero("codigo de categoria", solicitudProducto.getCategory());
        ValidarDatos.siEsMayoraCero("codigo de marca", solicitudProducto.getBrand());
        ValidarDatos.siEsVacioONull("nombre de producto", solicitudProducto.getName());
        ValidarDatos.siEsVacioONull("beneficios de producto",
                solicitudProducto.getInformation().getBenefits());
        ValidarDatos.siEsVacioONull("característica de producto",
                solicitudProducto.getInformation().getFeature());
        ValidarDatos.siEsVacioONull("descripción de producto",
                solicitudProducto.getInformation().getDescription());
    }

    public static void validarReferencias(List<SolicitudReferencia> references){
        ValidarDatos.siListaEsNull("references",references);
        int[] cont = {1};
        references.spliterator()
                .forEachRemaining(field-> {
                    ValidarDatos.siEsMayoraCero(("peso referencia "+cont[0]), field.getPeso());
                    ValidarDatos.siEsMayoraCero(("precio referencia "+cont[0]), field.getPrecio());
                    ValidarDatos.siEsMayoraCero(("stock referencia "+cont[0]), field.getStock());
                    cont[0]++;
                });
    }

    public static void validarFullReferencias(List<Reference> references){
        ValidarDatos.siListaEsNull("references",references);
        int[] cont = {1};
        references.spliterator()
                .forEachRemaining(field-> {
                    ValidarDatos.siEsMayoraCero(("peso referencia "+cont[0]), field.getPeso());
                    ValidarDatos.siEsMayoraCero(("precio referencia "+cont[0]), field.getPrecio());
                    ValidarDatos.siEsMayoraCero(("stock referencia "+cont[0]), field.getStock());
                    cont[0]++;
                });
    }

    public static List<Reference> agregarSKUaReferencias(List<SolicitudReferencia> referenciaInput, String nameBrand,
                       String nameProduct, int code){

        ValidarDatos.siEsVacioONull("nombre de producto",nameProduct);
        ValidarDatos.siEsMayoraCero("codigo de producto 1",code);
        ValidarDatos.siEsVacioONull("nombre de marca",nameProduct);

        return referenciaInput.stream().map(reference->{
            ValidarDatos.siEsNull("referencia de producto",reference);
            ValidarDatos.siEsMayoraCero("peso de producto",reference.getPeso());
            ValidarDatos.siEsMayoraCero("precio de producto",reference.getPrecio());
            ValidarDatos.siEsMayoraCero("stock de producto",reference.getStock());

            return Reference.crear(reference.getPeso(),reference.getPrecio(),
                Product.generarSKU(nameBrand, nameProduct,code,reference),reference.getStock(), reference.getCodeImg());
        }).toList();
    }


    private static String generarSKU(String brand, String name,
                                     int code, SolicitudReferencia reference){
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

    public List<String> getReferences() {
        return references;
    }

    public List<Reference> getFullReferences() {
        return fullreferences;
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
