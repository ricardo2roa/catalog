package ws.config.graphql.context;


import graphql.GraphQLContext;
import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLKickstartContext;
import graphql.kickstart.servlet.context.GraphQLServletContext;
import graphql.kickstart.spring.GraphQLSpringContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.Getter;
import lombok.NonNull;
import org.dataloader.DataLoaderRegistry;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.Map;

@Getter
public class CustomGraphQLContext implements GraphQLKickstartContext {

    private final String token;
    private final Map<Object,Object> context;

    public CustomGraphQLContext(String token, Map<Object,Object> context) {
        this.token = token;
        this.context = context;
    }


    @Override
    public @NonNull DataLoaderRegistry getDataLoaderRegistry() {
        return null;
    }

    @Override
    public Map<Object, Object> getMapOfContext() {
        return null;
    }
}
