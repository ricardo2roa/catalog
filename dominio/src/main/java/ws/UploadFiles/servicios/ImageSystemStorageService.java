package ws.UploadFiles.servicios;

import lombok.extern.java.Log;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;
import ws.UploadFiles.config.PropiedadesAlmacenamiento;
import ws.domain.exception.modelo.FileRequestException;
import ws.domain.exception.modelo.FileStorageException;
import ws.domain.exception.modelo.RequestException;
import ws.domain.exception.modelo.StorageException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
@Log
public class ImageSystemStorageService {
    public static final Set<String> LIST_EXT = Set.of(".jpg", ".png", ".jpeg");
    private final Path directorioRaiz;

    public ImageSystemStorageService(PropiedadesAlmacenamiento propiedadesAlmacenamiento) {
        if(propiedadesAlmacenamiento.getLocation().trim().length() == 0){
            throw new StorageException("Error el directorio para subir los archivos esta vacio");
        }
        this.directorioRaiz = Paths.get(propiedadesAlmacenamiento.getLocation());
    }

    public String store(MultipartFile file, String rename){
        try{
            if(file.isEmpty()){
                throw new FileRequestException("Error el archivo que intenta guardar se encuentra vacio");
            }
            String nameFile = cambiarNombre(file.getOriginalFilename(),rename);
            Path destinoArchivo = this.directorioRaiz.resolve(
                    Paths.get(nameFile))
                    .normalize().toAbsolutePath();

            log.info("directorio "+destinoArchivo);
            log.info("Path directorio "+destinoArchivo.toAbsolutePath());

            if(!destinoArchivo.getParent().equals(this.directorioRaiz.toAbsolutePath())){
                throw new StorageException("No se puede guardar por que esta fuera del directorio");
            }
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream,destinoArchivo, StandardCopyOption.REPLACE_EXISTING);
            }
            return nameFile;
        }catch (IOException e) {
            throw new StorageException("Error al guardar el archivo",e);
        }
    }

    public Path load(String filename){
        return directorioRaiz.resolve(filename);
    }

    public Resource loadAsResource(String filename){
        try{
            Path file = load(filename);
            Resource resource  = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new FileStorageException(("No se puede leer el archivo: "+filename));
            }
        } catch (MalformedURLException e) {
            throw new FileStorageException(("No se puede leer el archivo: "+filename),e);
        }
    }
    private static String cambiarNombre(String nameFile, String rename){
        var ext = nameFile.substring(nameFile.lastIndexOf("."));
        //Comprobación de extension
        if(!LIST_EXT.contains(ext)) throw new RequestException("400",
                ("La extension "+ext+" del archivo no esta permitida, solo se permite [.jpg, .png, .jpeg]"));
        return rename.concat(ext);
    }
}
