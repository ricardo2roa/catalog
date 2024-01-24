package ws.brand.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;
import ws.brand.modelo.dto.BrandDTO;
import ws.brand.modelo.entidad.Brand;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.config.graphql.context.dataloader.DataLoaderRegistryFactory;
import ws.product.modelo.entidad.Product;

import java.util.concurrent.CompletableFuture;

@Component
public class BrandResolver implements GraphQLResolver<Product>{
    /*private final RepositorioBrand repositorioBrand;

    public BrandResolver(RepositorioBrand repositorioBrand) {
        this.repositorioBrand = repositorioBrand;
    }*/

    public CompletableFuture<BrandDTO> brand(Product product, DataFetchingEnvironment enviroment){
        DataLoader<Integer,BrandDTO> dataLoader = enviroment.
                getDataLoader(DataLoaderRegistryFactory.BRAND_DATA_LOADER);
        return dataLoader.load(product.getBrand());
    }

}
