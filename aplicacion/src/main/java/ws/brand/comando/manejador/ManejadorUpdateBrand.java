package ws.brand.comando.manejador;

import org.springframework.stereotype.Component;
import ws.brand.comando.ComandoUpdateBrand;
import ws.brand.comando.fabrica.FabricaUpdateBrand;
import ws.brand.servicios.ServicioUpdateBrand;

import java.util.List;

@Component
public class ManejadorUpdateBrand {
    private final ServicioUpdateBrand servicioUpdateBrand;
    private final FabricaUpdateBrand fabricaUpdateBrand;
    public ManejadorUpdateBrand(ServicioUpdateBrand servicioUpdateBrand, FabricaUpdateBrand fabricaUpdateBrand) {
        this.servicioUpdateBrand = servicioUpdateBrand;
        this.fabricaUpdateBrand = fabricaUpdateBrand;
    }
    public void ejecutar(ComandoUpdateBrand comandoBrand, String id){
        this.servicioUpdateBrand.ejecutar(this.fabricaUpdateBrand.crear(comandoBrand),id);
    }
}
