package ws.UploadFiles.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecursoImagen {
    private String nombre;
    private byte[] file;
}
