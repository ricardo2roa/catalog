package ws.config.graphql.context.dataloader;

import org.dataloader.DataLoader;
import org.dataloader.DataLoaderFactory;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;
import ws.brand.modelo.dto.BrandDTO;
import ws.brand.servicios.ServicioBuscarBrand;
import ws.category.modelo.dto.CategoryDTO;
import ws.category.servicios.ServicioConsultarCategory;
import ws.tag.modelo.dto.TagDTO;
import ws.tag.servicios.ServicioConsultarTag;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class DataLoaderRegistryFactory {
    public static final String BRAND_DATA_LOADER = "BRAND_DATA_LOADER";

    public static final String TAG_DATA_LOADER = "TAG_DATA_LOADER";
    public static final String CATEGORY_DATA_LOADER = "CATEGORY_DATA_LOADER";
    private final ServicioBuscarBrand servicioBuscarBrand;

    private final ServicioConsultarCategory servicioConsultarCategory;

    private final ServicioConsultarTag servicioConsultarTag;
    private static final Executor brandThreadPool = Executors
            .newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final Executor CategoryThreadPool = Executors
            .newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static final Executor TagThreadPool = Executors
            .newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public DataLoaderRegistryFactory(ServicioBuscarBrand servicioBuscarBrand, ServicioConsultarCategory servicioConsultarCategory, ServicioConsultarTag servicioConsultarTag) {
        this.servicioBuscarBrand = servicioBuscarBrand;
        this.servicioConsultarCategory = servicioConsultarCategory;
        this.servicioConsultarTag = servicioConsultarTag;
    }

    public DataLoaderRegistry create(){
        DataLoaderRegistry registry = new DataLoaderRegistry();
        registry.register(BRAND_DATA_LOADER,createBrandDataLoader());
        registry.register(CATEGORY_DATA_LOADER,createCategoryLoader());
        registry.register(TAG_DATA_LOADER,createTagLoader());
        return registry;
    }

    private DataLoader<Integer, BrandDTO> createBrandDataLoader(){
       return DataLoaderFactory.newMappedDataLoader((Set<Integer>brandcodes) ->
               CompletableFuture.supplyAsync(() -> this.servicioBuscarBrand.getBrandFor(brandcodes), brandThreadPool));
    }

    private DataLoader<Integer, CategoryDTO> createCategoryLoader(){
        return DataLoaderFactory.newMappedDataLoader((Set<Integer>categoryCodes) ->
                CompletableFuture.supplyAsync(() -> this.servicioConsultarCategory.getCategorysFor(categoryCodes),CategoryThreadPool));
    }

    private DataLoader<Integer, TagDTO> createTagLoader(){
        return DataLoaderFactory.newMappedDataLoader((Set<Integer>tagCodes) ->
                CompletableFuture.supplyAsync(() -> this.servicioConsultarTag.getTagsFor(tagCodes),TagThreadPool));
    }
}
