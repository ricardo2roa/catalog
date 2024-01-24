package ws.tag.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;
import ws.config.graphql.context.dataloader.DataLoaderRegistryFactory;
import ws.product.modelo.entidad.Product;
import ws.tag.modelo.dto.TagDTO;
import ws.tag.modelo.entidad.Tag;
import ws.tag.puerto.repositorio.RepositorioTag;

import java.util.concurrent.CompletableFuture;

@Component
public class TagResolver implements GraphQLResolver<Product>{
    /*private final RepositorioTag repositorioTag;

    public TagResolver(RepositorioTag repositorioTag) {
        this.repositorioTag = repositorioTag;
    }*/
    public CompletableFuture<TagDTO> tag(Product product, DataFetchingEnvironment environment){
        DataLoader<Integer, TagDTO> dataLoader = environment
                .getDataLoader(DataLoaderRegistryFactory.TAG_DATA_LOADER);
        return dataLoader.load(product.getTag());
    }
}
