package ws.product.servicios;

import ws.brand.modelo.entidad.Brand;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.product.modelo.entidad.Product;
import ws.product.modelo.entidad.SolicitudCrearProducto;
import ws.product.puerto.repositorio.RepositorioProduct;
import ws.reference.modelo.dto.ReferenceDTO;
import ws.reference.modelo.entidad.Reference;

import java.util.List;

public class ServicioCrearProducto {
    private final RepositorioProduct repositorioProduct;
    private final RepositorioBrand repositorioBrand;
    public ServicioCrearProducto(RepositorioProduct repositorioProduct, RepositorioBrand repositorioBrand) {
        this.repositorioProduct = repositorioProduct;
        this.repositorioBrand = repositorioBrand;
    }

    public String ejecutar(SolicitudCrearProducto solicitudCrearProducto){
        int code = this.repositorioProduct.calcularCode();
        Brand brand = this.repositorioBrand.obtenerByCode(solicitudCrearProducto.getBrand());
        List<Reference> references = Product.agregarSKUaReferencias(solicitudCrearProducto.getReferences(),brand.getName(),
                solicitudCrearProducto.getName(),code);
        Product product = Product.crear(solicitudCrearProducto,code,references);
        return this.repositorioProduct.guardar(product);
    }
}
