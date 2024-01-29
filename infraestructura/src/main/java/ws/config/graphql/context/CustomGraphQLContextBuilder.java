package ws.config.graphql.context;

import graphql.kickstart.execution.context.GraphQLKickstartContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.websocket.Session;
import jakarta.websocket.server.HandshakeRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import ws.config.graphql.context.dataloader.DataLoaderRegistryFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {
    private final DataLoaderRegistryFactory dataLoaderRegistryFactory;

    public CustomGraphQLContextBuilder(DataLoaderRegistryFactory dataLoaderRegistryFactory) {
        this.dataLoaderRegistryFactory = dataLoaderRegistryFactory;
    }

    @Override
    public GraphQLKickstartContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        Map<Object,Object> context = new HashMap<>();
        context.put("HttpServletRequest",httpServletRequest);
        context.put("HttpServletResponse",httpServletResponse);
        context.put("token",token);

        /*try {
            Collection<Part> prueba = httpServletRequest.getParts();
            context.put("parts",prueba);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }*/

        return GraphQLKickstartContext.of(this.dataLoaderRegistryFactory.create(),context);
        //return GraphQLKickstartContext.of(context);
    }

    @Override
    public GraphQLKickstartContext build(Session session, HandshakeRequest handshakeRequest) {
        throw new IllegalStateException("Unsupported");
    }

    @Override
    public GraphQLKickstartContext build() {
         throw new IllegalStateException("Unsupported");
    }
}
