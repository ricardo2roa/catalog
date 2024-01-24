package ws.category.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;
import ws.category.modelo.dto.CategoryDTO;
import ws.config.graphql.context.dataloader.DataLoaderRegistryFactory;
import ws.product.modelo.entidad.Product;

import java.util.concurrent.CompletableFuture;

@Component
public class CategoryResolver implements GraphQLResolver<Product>{
    /*private final RepositorioCategory repositorioCategory;

    public CategoryResolver(RepositorioCategory repositorioCategory) {
        this.repositorioCategory = repositorioCategory;
    }*/

    public CompletableFuture<CategoryDTO> category(Product product, DataFetchingEnvironment enviroment){
        DataLoader<Integer, CategoryDTO> dataLoader = enviroment
                .getDataLoader(DataLoaderRegistryFactory.CATEGORY_DATA_LOADER);
        return dataLoader.load(product.getCategory());
    }
}
