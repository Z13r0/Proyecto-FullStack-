# Plataforma Multimedia - Microservicios

## 📋 Descripción

Proyecto de **microservicios** para una plataforma multimedia, implementado con **Spring Boot 4.0.6**, **Java 21**, y todas las herramientas obligatorias solicitadas.

## 🏗️ Arquitectura

```
┌─────────────────────────────────────────────────┐
│          API Gateway (Puerto 8080)              │
│   (Spring Cloud Gateway + Service Discovery)    │
└──────────────────┬──────────────────────────────┘
                   │
    ┌──────────────┼──────────────┬──────────────┬──────────────┐
    │              │              │              │              │
    ▼              ▼              ▼              ▼              ▼
┌────────┐ ┌──────────┐ ┌──────────┐ ┌─────────────┐ ┌──────────┐
│ Eureka │ │  MS      │ │  MS      │ │  MS         │ │  MySQL   │
│Server  │ │Usuarios  │ │Películas │ │Suscripciones│ │Database  │
│:8761   │ │:8081     │ │:8082     │ │:8084        │ │:3306     │
└────────┘ └──────────┘ └──────────┘ └─────────────┘ └──────────┘
    │          :8080         :8081         :8083
    └──────────────┼──────────────┬──────────────┘
                   │              │
           ┌───────▼──────┬───────▼────────┐
           │ MS Perfiles  │ Otra Servicios │
           │   :8083      │                │
           └──────────────┴────────────────┘
```

## 🚀 Herramientas Implementadas

✅ **Swagger/OpenAPI** - Documentación automática de APIs  
✅ **Spring HATEOAS** - Enlaces para RESTful APIs  
✅ **Data Faker** - Generación de datos de prueba  
✅ **JUnit 5** - Tests unitarios  
✅ **Eureka Server** - Service Discovery  
✅ **API Gateway** - Enrutamiento centralizado  
✅ **YAML Configuration** - Configuración en YAML  
✅ **Docker** - Containerización  
✅ **OpenFeign** - Comunicación entre microservicios  

## 📦 Estructura de Carpetas

```
Proyecto-FullStack-/
├── eureka-server/                    # Servidor de descubrimiento
│   ├── pom.xml
│   ├── Dockerfile
│   └── src/
├── api-gateway/                      # Puerta de entrada centralizada
│   ├── pom.xml
│   ├── Dockerfile
│   └── src/
├── ms_usuarios (TERMINADO)/          # Microservicio de Usuarios
│   ├── pom.xml
│   ├── Dockerfile
│   └── src/
├── ms_peliculas (TERMINADO)/         # Microservicio de Películas
│   ├── pom.xml
│   ├── Dockerfile
│   └── src/
├── ms_perfiles(TERMINADO)/           # Microservicio de Perfiles
│   ├── pom.xml
│   ├── Dockerfile
│   └── src/
├── ms_suscripciones(TERMINADO)/      # Microservicio de Suscripciones
│   ├── pom.xml
│   ├── Dockerfile
│   └── src/
├── docker-compose.yml                # Orquestación de contenedores
└── .env.example                      # Variables de entorno
```

## 🔧 Requisitos Previos

- **Java 21** o superior
- **Maven 3.9+**
- **Docker** (para containerización)
- **Docker Compose** (para orquestación)
- **MySQL 8.0+**

## 🚀 Instalación y Ejecución

### Opción 1: Ejecución Local

```bash
# 1. Clonar el repositorio
git clone https://github.com/Z13r0/Proyecto-FullStack-.git
cd Proyecto-FullStack-

# 2. Crear base de datos
mysql -u root -p < db/schema.sql

# 3. Compilar todos los módulos
mvn clean install

# 4. Ejecutar cada microservicio (en terminales diferentes)

# Terminal 1: Eureka Server
cd eureka-server
mvn spring-boot:run

# Terminal 2: API Gateway
cd api-gateway
mvn spring-boot:run

# Terminal 3: MS Usuarios
cd ms_usuarios\ \(TERMINADO\)
mvn spring-boot:run

# Terminal 4: MS Películas
cd ms_peliculas\ \(TERMINADO\)
mvn spring-boot:run

# Terminal 5: MS Perfiles
cd ms_perfiles\(TERMINADO\)
mvn spring-boot:run

# Terminal 6: MS Suscripciones
cd ms_suscripciones\(TERMINADO\)
mvn spring-boot:run
```

### Opción 2: Ejecución con Docker Compose (RECOMENDADO)

```bash
# 1. Crear archivo .env
cp .env.example .env

# 2. Construir y ejecutar todos los servicios
docker-compose up --build

# 3. Verificar que los servicios están corriendo
docker-compose ps
```

## 📍 Endpoints de Acceso

| Servicio | URL | Puerto |
|----------|-----|--------|
| **API Gateway** | http://localhost:8080 | 8080 |
| **Eureka Server** | http://localhost:8761 | 8761 |
| **MS Usuarios** | http://localhost:8081/api/usuarios | 8081 |
| **MS Películas** | http://localhost:8082/api/peliculas | 8082 |
| **MS Perfiles** | http://localhost:8083/api/perfiles | 8083 |
| **MS Suscripciones** | http://localhost:8084/api/suscripciones | 8084 |
| **Swagger UI** | http://localhost:8080/swagger-ui.html | - |
| **MySQL** | localhost:3306 | 3306 |

## 📚 Documentación de APIs

### Swagger/OpenAPI

Accede a la documentación interactiva:
```
http://localhost:8080/swagger-ui.html
http://localhost:8080/v3/api-docs
```

### Ejemplos de Requests

#### Crear Usuario
```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez García",
    "email": "juan@example.com",
    "contrasena": "Password123!",
    "direccion": "Calle Principal 123",
    "pais": "España"
  }'
```

#### Obtener Todos los Usuarios
```bash
curl -X GET http://localhost:8080/api/usuarios
```

#### Obtener Usuario por ID
```bash
curl -X GET http://localhost:8080/api/usuarios/1
```

#### Actualizar Usuario
```bash
curl -X PUT http://localhost:8080/api/usuarios/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Actualizado",
    "email": "juan.actualizado@example.com",
    "contrasena": "NewPassword123!",
    "direccion": "Nueva Dirección 456",
    "pais": "España"
  }'
```

#### Eliminar Usuario
```bash
curl -X DELETE http://localhost:8080/api/usuarios/1
```

## 🧪 Ejecución de Pruebas

```bash
# Ejecutar todos los tests
mvn test

# Ejecutar tests de un módulo específico
cd ms_usuarios\ \(TERMINADO\)
mvn test

# Ejecutar tests con cobertura
mvn test jacoco:report

# Ver reporte de cobertura
open target/site/jacoco/index.html
```

## 🔐 Configuración de Seguridad

### Variables de Entorno

Crea un archivo `.env`:

```env
# Database
DB_USERNAME=app_user
DB_PASSWORD=app_password_secure_123
DB_HOST=mysql
DB_PORT=3306
DB_NAME=db_plataforma_multimedia

# Services
EUREKA_URL=http://eureka-server:8761/eureka/
JAVA_OPTS=-Xmx512m -Xms256m
```

## 📊 Monitoreo

### Actuator Endpoints

```
http://localhost:8080/actuator/health
http://localhost:8080/actuator/metrics
http://localhost:8080/actuator/prometheus
```

## 🔍 Troubleshooting

### El servicio no se conecta a Eureka

1. Verificar que Eureka está corriendo: `http://localhost:8761`
2. Revisar logs: `docker-compose logs eureka-server`
3. Verificar configuración de conexión

### Error de conexión a MySQL

```bash
# Verificar que MySQL está corriendo
docker-compose ps mysql

# Revisar logs
docker-compose logs mysql

# Reiniciar servicio
docker-compose restart mysql
```

### Puertos en uso

```bash
# Liberar puerto (ejemplo puerto 8080)
lsof -ti:8080 | xargs kill -9
```

## 📈 Métricas y Logs

### Visualizar logs

```bash
# Logs de todos los servicios
docker-compose logs -f

# Logs de un servicio específico
docker-compose logs -f ms-usuarios
```

## 🛠️ Tecnologías Utilizadas

- **Spring Boot 4.0.6**
- **Spring Cloud 2025.1.1**
- **Java 21**
- **MySQL 8.0**
- **Docker & Docker Compose**
- **Lombok**
- **JUnit 5**
- **Mockito**
- **Flyway**
- **Swagger/OpenAPI**
- **HATEOAS**
- **DataFaker**
- **Resilience4j**
- **Prometheus & Micrometer**

## 📝 Convenciones de Código

- Nombres de clases: `PascalCase` (UsuarioController)
- Nombres de métodos: `camelCase` (obtenerUsuario)
- Nombres de variables: `camelCase` (nombreUsuario)
- Nombres de constantes: `UPPER_SNAKE_CASE` (API_BASE_URL)
- DTOs terminan con `DTO` (UsuarioDTO)
- Interfaces de Repository: `{Entity}Repository`
- Interfaces de Service: `{Entity}Service`
- Clases de Controlador: `{Entity}Controller`

## 🤝 Contribución

Para contribuir al proyecto:

1. Fork del repositorio
2. Crear rama de features: `git checkout -b feature/AmazingFeature`
3. Commit de cambios: `git commit -m 'Add some AmazingFeature'`
4. Push a la rama: `git push origin feature/AmazingFeature`
5. Abrir un Pull Request

## 📄 Licencia

Este proyecto está bajo la licencia Apache 2.0. Ver `LICENSE` para más detalles.

## 📧 Contacto

**Email**: support@plataforma.com  
**GitHub**: [@Z13r0](https://github.com/Z13r0)  
**Sitio Web**: https://plataforma.com

## 🎯 Próximos Pasos

- [ ] Implementar autenticación JWT
- [ ] Agregar caché con Redis
- [ ] Implementar búsqueda con Elasticsearch
- [ ] Agregar logging centralizado (ELK Stack)
- [ ] Implementar CI/CD con GitHub Actions
- [ ] Agregar tests de integración
- [ ] Implementar versionado de APIs
- [ ] Agregar rate limiting

---

**Última actualización:** 2026-06-11  
**Estado del Proyecto:** ✅ FASE FINAL LISTA PARA ENTREGA