# Backend AppMóvil - Bienestar Emocional

Este repositorio contiene el código fuente del servicio backend para una aplicación móvil de bienestar emocional. El backend está construido con Spring Boot y proporciona una API REST para gestionar usuarios, registrar emociones y administrar recursos de apoyo.

## ✨ Características

*   **Gestión de Usuarios**: Registro, actualización, listado y eliminación de usuarios.
*   **Seguimiento de Emociones**: Permite a los usuarios registrar su estado emocional a lo largo del tiempo.
*   **Recursos de Apoyo**: Administración de consejos, actividades y otros recursos para el bienestar del usuario.
*   **Documentación de API**: Documentación automática y interactiva con Swagger (OpenAPI).

## 🚀 Requisitos Previos

Para poder ejecutar este proyecto localmente, necesitarás tener instalado:

*   [Java JDK](https://www.oracle.com/java/technologies/downloads/) (versión 17 o superior)
*   [Apache Maven](https://maven.apache.org/download.cgi) (para la gestión de dependencias y construcción del proyecto)
*   Una base de datos (por ejemplo, MySQL, PostgreSQL, o H2).

## ⚙️ Instalación y Ejecución

1.  **Clona el repositorio:**
    ```bash
    git clone <URL-DEL-REPOSITORIO>
    cd backend-appmovil
    ```

2.  **Configura la base de datos:**
    Abre el archivo `src/main/resources/application.properties` y configura las propiedades de conexión a tu base de datos:
    ```properties
    # Ejemplo para H2 (base de datos en memoria)
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=password
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    spring.h2.console.enabled=true

    # Ejemplo para MySQL
    # spring.datasource.url=jdbc:mysql://localhost:3306/nombre_bd
    # spring.datasource.username=tu_usuario
    # spring.datasource.password=tu_contraseña
    # spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Ejecuta la aplicación:**
    Utiliza Maven para iniciar el servidor de Spring Boot.
    ```bash
    mvn spring-boot:run
    ```
    La aplicación se iniciará por defecto en el puerto `8080`.

## 📖 Documentación de la API

Este proyecto utiliza Swagger para generar documentación interactiva de la API. Una vez que la aplicación esté en ejecución, puedes acceder a la interfaz de Swagger UI en la siguiente URL:

http://localhost:8080/swagger-ui.html

Desde allí, podrás ver todos los endpoints disponibles, sus parámetros y probarlos directamente.

## Endpoints Principales

A continuación se listan los endpoints principales agrupados por recurso.

### Usuarios (`/api/users`)
*   `POST /`: Registrar un nuevo usuario.
*   `GET /`: Obtener la lista de todos los usuarios.
*   `PUT /{id}`: Actualizar un usuario existente.
*   `DELETE /{id}`: Eliminar un usuario.

### Emociones (`/api/emotions`)
*   `POST /`: Registrar una nueva emoción para un usuario.
*   `GET /`: Obtener todas las emociones registradas.
*   `PUT /{id}`: Actualizar una emoción existente.
*   `DELETE /{id}`: Eliminar una emoción.

### Recursos (`/api/resources`)
*   `POST /`: Crear un nuevo recurso (consejo, actividad, etc.).
*   `GET /`: Obtener todos los recursos disponibles.
*   `PUT /{id}`: Actualizar un recurso existente.
*   `DELETE /{id}`: Eliminar un recurso.

