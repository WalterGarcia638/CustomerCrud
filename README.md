# banreservas-api

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/banreservas-api-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Estructura del Proyecto y Decisiones de Diseño

### 1. Domain.Entities
- **Propósito:** Define las entidades de negocio utilizando JPA. Estas clases representan las tablas de la base de datos y sus relaciones.
- **Decisiones de Diseño:**
  - **Uso de JPA Annotations:** Facilita el mapeo objeto-relacional (ORM), permitiendo una integración fluida entre objetos Java y tablas de base de datos.
  - **Encapsulamiento:** Cada entidad encapsula sus atributos y expone operaciones a través de métodos públicos para mantener la integridad de los datos.

### 2. Repository
- **Propósito:** Implementa el acceso a datos utilizando PanacheRepository para simplificar las operaciones CRUD y consultas a la base de datos.
- **Decisiones de Diseño:**
  - **Patrón Repositorio:** Abstrae la lógica de acceso a datos del resto de la aplicación, facilitando cambios en la implementación de la base de datos sin afectar la lógica de negocio.
  - **PanacheRepository:** Reduce la cantidad de código boilerplate necesario para implementar operaciones de base de datos comunes.

### 3. Service
- **Propósito:** Contiene la lógica de negocio central de la aplicación. Estos servicios procesan los datos obtenidos a través de los repositorios y ejecutan operaciones de negocio.
- **Decisiones de Diseño:**
  - **Separación de Preocupaciones:** Aísla la lógica de negocio de las capas de acceso a datos y presentación, siguiendo el principio de responsabilidad única.
  - **Inyección de Dependencias:** Utiliza Quarkus para inyectar dependencias como repositorios, simplificando la gestión de dependencias y promoviendo un acoplamiento débil.

### 4. Test
- **Propósito:** Alberga pruebas unitarias y de integración para asegurar la calidad del código y la correcta funcionalidad de la aplicación.
- **Decisiones de Diseño:**
  - **Cobertura de Pruebas:** Incluye pruebas para servicios y repositorios para cubrir la lógica de negocio y acceso a datos, asegurando la robustez del sistema.
  - **Mockito para Simulación:** Utiliza Mockito para simular dependencias en las pruebas, permitiendo probar componentes de manera aislada.

### 5. Quarkus (Framework Base)
- **Propósito:** Proporciona el entorno de ejecución para la aplicación, facilitando la configuración, el manejo de solicitudes HTTP, y otras capacidades.
- **Decisiones de Diseño:**
  - **Selección de Quarkus:** Por su eficiencia en tiempo de ejecución y desarrollo, y su integración nativa con contenedores, lo que es ideal para microservicios y aplicaciones en la nube.
  - **Configuración y Extensibilidad:** Aprovecha las capacidades de Quarkus para configurar la aplicación y extenderla con funcionalidades adicionales según sea necesario.

### 6. Cliente/Consumidor
- **Propósito:** Interactúa con la aplicación a través de interfaces RESTful, utilizando los servicios expuestos por los controladores.
- **Decisiones de Diseño:**
  - **Interfaces RESTful:** Se eligió por su amplia adopción, facilidad de uso e integración con diferentes clientes (web, móvil, sistemas externos).
  - **Documentación API:** Uso de OpenAPI para documentar los endpoints, facilitando la integración y el consumo de los servicios por parte de los clientes.
 
## Diagrama de arquitectura
![Diagrama de arquitectura](https://github.com/WalterGarcia638/CustomerCrud/assets/46503881/e62eb7a2-87d6-4985-a4cf-f2f2d6097927)

## Diagrama de estructura del proyecto
![DiagramaEstructura](https://github.com/WalterGarcia638/CustomerCrud/assets/46503881/875a5e70-787b-4e74-af4a-99d04a14dbb7)

