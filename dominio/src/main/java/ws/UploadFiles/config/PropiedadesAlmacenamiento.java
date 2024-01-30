package ws.UploadFiles.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("storage")
public class PropiedadesAlmacenamiento {
    private String location;
}
