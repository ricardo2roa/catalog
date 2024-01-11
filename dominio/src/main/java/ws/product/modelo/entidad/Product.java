package ws.product.modelo.entidad;

import ws.brand.modelo.dto.BrandDTO;
import ws.information.modelo.dto.InformationDTO;
import ws.reference.modelo.dto.ReferenceDTO;
import ws.reference.modelo.entidad.Reference;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String id;
    private int code;
    private int tag;
    private int category;
    private BrandDTO brand;
    private String name;
    private InformationDTO information;
    private List<Reference> references;

    private Product(int code, int tag, int category, BrandDTO brand, String name, InformationDTO information,
                    List<Reference> references) {
        this.code = code;
        this.tag = tag;
        this.category = category;
        this.brand = brand;
        this.name = name;
        this.information = information;
        this.references = references;
    }

    public static Product crear(SolicitudCrearProducto solicitudCrearProducto, int code){
        //Validadores
        List<Reference> references = new ArrayList<>();
        solicitudCrearProducto.getReferences().forEach((ReferenceDTO reference)->{
            String sku = Product.generarSKU(solicitudCrearProducto.getBrand().getName(), solicitudCrearProducto.getName(),
                    code,reference);
            references.add(Reference.crear(
               reference.getPeso(), reference.getPrice(),
               sku, reference.getStock()
            ));
        });

        return new Product(code,
                solicitudCrearProducto.getTag(),solicitudCrearProducto.getCategory(),
                solicitudCrearProducto.getBrand(),solicitudCrearProducto.getName(),
                solicitudCrearProducto.getInformation(), references
        );
    }

    private static String generarSKU(String brand, String name,
                                     int code, ReferenceDTO reference){
        StringBuilder skuBuilder = new StringBuilder();
        skuBuilder.append(name, 0, 4).append("-").append(brand,0,4).append("-");
        skuBuilder.append(Long.toString(reference.getPeso()),0,4);
        skuBuilder.append("-").append(code);
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

    public List<Reference> getReferences() {
        return references;
    }
}
