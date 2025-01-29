# URL Shortening API

Este proyecto es una API REST desarrollada con **Spring Boot** que permite acortar URLs y realizar operaciones CRUD sobre estas URLs. También proporciona estadísticas de acceso para cada URL acortada.

## Características

- Crear una URL acortada.
- Redirigir a la URL original mediante su versión acortada.
- Recuperar metadatos de una URL acortada.
- Obtener estadísticas de acceso de una URL acortada.
- Actualizar una URL acortada.
- Eliminar una URL acortada.

## Requisitos previos

- **Java 21**
- **Maven 3.8+**
- **PostgreSQL 14+**

## Instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/SoyElGary1/url_shortening_api
   ```
2. Ve al directorio del proyecto:
   ```bash
   cd url_shortening
   ```
3. Configura la base de datos PostgreSQL:
    - Crea una base de datos llamada `url_shortening_db`.
    - Actualiza las credenciales de la base de datos en `application.properties` si es necesario:
      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/url_shortening_db
      spring.datasource.username=postgres
      spring.datasource.password=1234
      ```

4. Compila y ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

## Endpoints

### 1. Crear una URL acortada
- **URL:** `/api/v1/shorten`
- **Método:** `POST`
- **Cuerpo:**
  ```json
  {
    "url": "https://example.com"
  }
  ```
- **Respuesta:**
  ```json
  {
    "id": 1,
    "url": "https://example.com",
    "shortUrl": "47fe3af5",
    "createdAt": "2025-01-26T12:00:00",
    "updatedAt": "2025-01-26T12:00:00"
  }
  ```

### 2. Redirigir a la URL original
- **URL:** `/redirect/{shortUrl}`
- **Método:** `GET`
- **Descripción:** Redirige automáticamente a la URL original asociada con la URL acortada.

### 3. Recuperar una URL original
- **URL:** `/shorten/{shortUrl}`
- **Método:** `GET`
- **Respuesta:**
  ```json
  {
    "id": 1,
    "url": "https://example.com",
    "shortUrl": "47fe3af5",
    "createdAt": "2025-01-26T12:00:00",
    "updatedAt": "2025-01-26T12:00:00"
  }
  ```

### 4. Obtener estadísticas de una URL acortada
- **URL:** `/shorten/{shortUrl}/stats`
- **Método:** `GET`
- **Respuesta:**
  ```json
  {
    "id": 1,
    "url": "https://example.com",
    "shortUrl": "47fe3af5",
    "createdAt": "2025-01-26T12:00:00",
    "updatedAt": "2025-01-26T12:00:00",
    "accessCount": 42
  }
  ```

### 5. Actualizar una URL acortada
- **URL:** `/shorten/{shortUrl}`
- **Método:** `PUT`
- **Cuerpo:**
  ```json
  {
    "url": "https://updated-example.com"
  }
  ```
- **Respuesta:**
  ```json
  {
    "id": 1,
    "url": "https://updated-example.com",
    "shortUrl": "47fe3af5",
    "createdAt": "2025-01-26T12:00:00",
    "updatedAt": "2025-01-26T13:00:00"
  }
  ```

### 6. Eliminar una URL acortada
- **URL:** `/shorten/{shortUrl}`
- **Método:** `DELETE`
- **Respuesta:**
    - **204 No Content** si la URL fue eliminada correctamente.

## Dependencias principales

- **Spring Boot Starter Web**: Para crear la API REST.
- **Spring Boot Starter Data JPA**: Para interactuar con la base de datos.
- **PostgreSQL Driver**: Para conectar con PostgreSQL.
- **Spring Boot Starter Validation**: Para validar datos de entrada.

## Configuración de la base de datos

La configuración de la base de datos se encuentra en el archivo `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/url_shortening_db
spring.datasource.username=postgres
spring.datasource.password=1234
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## Autor

Desarrollado por [SoyElGary1].

