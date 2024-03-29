package ws.product.servicios;

import ws.product.modelo.entidad.SolicitudUpdateProduct;
import ws.product.puerto.repositorio.RepositorioProduct;

public class ServiciosUpdateProduct {
    private final RepositorioProduct repositorioProduct;
    public ServiciosUpdateProduct(RepositorioProduct repositorioProduct) {
        this.repositorioProduct = repositorioProduct;
    }

    public String ejecutar(SolicitudUpdateProduct solicitudUpdateProduct, String id){
        return this.repositorioProduct.updateProduct(solicitudUpdateProduct,id);
    }
}
