# Usar una imagen base de Gradle para construir la aplicación
FROM gradle:8.5.0-jdk17-focal AS build

# Copiar el código fuente al contenedor
COPY --chown=gradle:gradle . /home/gradle/src

# Establecer el directorio de trabajo
WORKDIR /home/gradle/src

# Construir la aplicación
RUN gradle clean

RUN gradle build

# Mover el archivo jar construido a un directorio más accesible
RUN mv build/libs/catalog-0.0.1-SNAPSHOT.jar /home/gradle/src/catalog-0.0.1-SNAPSHOT.jar

FROM openjdk:17-oracle

# Copiar el archivo jar de la etapa de construcción
COPY --from=build /home/gradle/src/catalog-0.0.1-SNAPSHOT.jar /app/catalog-0.0.1-SNAPSHOT.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 7000

# Comando para iniciar la aplicación
ENTRYPOINT ["java","-jar","/app/catalog-0.0.1-SNAPSHOT.jar"]