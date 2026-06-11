# 🎬 Plataforma Multimedia - Sistema de Microservicios

<div align="center">

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-green?style=for-the-badge&logo=spring)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Latest-blue?style=for-the-badge&logo=docker)](https://www.docker.com/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-Apache%202.0-green?style=for-the-badge)](LICENSE)

**Un proyecto empresarial de microservicios para gestión de plataforma multimedia**

[Características](#características) • [Requisitos](#requisitos-previos) • [Instalación](#instalación-y-ejecución) • [Microservicios](#-microservicios) • [API](#-documentación-de-apis) • [Arquitectura](#-arquitectura-detallada)

</div>

---

## 📋 Tabla de Contenidos

1. [Introducción](#introducción)
2. [Características](#características)
3. [Requisitos Previos](#requisitos-previos)
4. [Instalación y Ejecución](#instalación-y-ejecución)
5. [Arquitectura Detallada](#-arquitectura-detallada)
6. [Microservicios](#-microservicios)
7. [Documentación de APIs](#-documentación-de-apis)
8. [Flujos de Comunicación](#-flujos-de-comunicación-entre-servicios)
9. [Base de Datos](#-estructura-de-base-de-datos)
10. [Tests y Cobertura](#-tests-y-cobertura)
11. [Seguridad](#-seguridad-y-autenticación)
12. [Monitoreo](#-monitoreo-y-métricas)
13. [Troubleshooting](#-troubleshooting)
14. [Tecnologías](#-tecnologías-utilizadas)

---

## 🎯 Introducción

Este proyecto implementa una **plataforma multimedia escalable y robusta** utilizando la arquitectura de **microservicios**. Está diseñado como un proyecto educativo para estudiantes de **Ingeniería en Informática (2do año)**, demostrando conceptos avanzados de:

- **Arquitectura de Microservicios**
- **Service Discovery y Load Balancing**
- **API Gateway Pattern**
- **Comunicación Inter-Servicios**
- **Containerización con Docker**
- **Testing Unitario e Integración**
- **Seguridad en APIs**
- **Monitoreo y Observabilidad**

### 🎓 Concepto Educativo

Los microservicios permiten que diferentes equipos trabajen de manera independiente en distintos servicios, facilitando:

✅ **Escalabilidad independiente** - Cada servicio puede escalar según su carga
✅ **Mantenibilidad** - Equipos especializados por dominio
✅ **Resiliencia** - Fallos en un servicio no afectan a los demás
✅ **Flexibilidad tecnológica** - Cada equipo elige sus herramientas
✅ **Deploy independiente** - Cambios sin afectar otros servicios

---

## ✨ Características

### 🏗️ Herramientas Obligatorias Implementadas

| Herramienta | Descripción | Ubicación |
|---|---|---|
| **Swagger/OpenAPI** | Documentación automática de APIs | `/swagger-ui.html` |
| **Spring HATEOAS** | Enlaces para navegación REST | Todos los controllers |
| **Data Faker** | Generación de datos de prueba | `Seeder` classes |
| **JUnit 5** | Testing unitario | `src/test/java` |
| **Eureka Server** | Service Discovery | Puerto 8761 |
| **API Gateway** | Enrutamiento centralizado | Puerto 8080 |
| **YAML Configuration** | Configuración por entorno | `application.yml` |
| **Docker** | Containerización | `Dockerfile` + `docker-compose.yml` |
| **OpenFeign** | Comunicación inter-servicios | `@FeignClient` |

### 🚀 Características Avanzadas

- ✅ Circuit Breaker Pattern (Resilience4j)
- ✅ Health Checks y Métricas (Micrometer + Prometheus)
- ✅ Gestión de Configuración YAML
- ✅ Validación de datos con Jakarta Validation
- ✅ Autenticación y Autorización (Spring Security + BCrypt)
- ✅ Transacciones y ORM (Spring Data JPA + Hibernate)
- ✅ Migraciones de base de datos (Flyway)
- ✅ Tests con MockMvc y Mockito
- ✅ Logging centralizado
- ✅ CORS habilitado para desarrollo

---

## 🔧 Requisitos Previos

### Software Obligatorio

| Software | Versión | Propósito |
|----------|---------|----------|
| **Java** | 21 o superior | Runtime de la aplicación |
| **Maven** | 3.9+ | Gestor de dependencias |
| **Docker** | 20.10+ | Containerización |
| **Docker Compose** | 2.0+ | Orquestación |
| **Git** | 2.30+ | Control de versiones |

### Verificar Instalación

```bash
# Java
java -version
# Output: openjdk version "21.x.x"

# Maven
mvn -v
# Output: Apache Maven 3.9.x

# Docker
docker --version
# Output: Docker version 20.10.x

# Docker Compose
docker-compose --version
# Output: Docker Compose version 2.x.x
```

### Requisitos del Sistema

- **RAM:** Mínimo 8GB (recomendado 16GB)
- **Almacenamiento:** 5GB libres
- **SO:** Windows 10+, macOS 10.14+, o Linux (Ubuntu 20.04+)

---

## 📦 Instalación y Ejecución

### Opción 1: Ejecución con Docker Compose (RECOMENDADO) 🐳

La forma más sencilla y profesional de ejecutar todo el proyecto.

```bash
# 1. Clonar el repositorio
git clone https://github.com/Z13r0/Proyecto-FullStack-.git
cd Proyecto-FullStack-

# 2. Crear archivo .env desde la plantilla
cp .env.example .env

# 3. Editar .env si es necesario (opcional)
# Valores por defecto:
# DB_USERNAME=app_user
# DB_PASSWORD=app_password_secure_123
# EUREKA_URL=http://eureka-server:8761/eureka/

# 4. Construir y ejecutar todos los servicios
docker-compose up --build

# 5. En otra terminal, verificar servicios
docker-compose ps

# 6. Ver logs
docker-compose logs -f

# 7. Detener todos los servicios
docker-compose down

# 8. Limpiar volúmenes (opcional)
docker-compose down -v
```

**Tiempo de inicio:** ~2-3 minutos

### Opción 2: Ejecución Local (Desarrollo) 🔨

Para desarrollo local sin Docker.

```bash
# 1. Clonar el repositorio
git clone https://github.com/Z13r0/Proyecto-FullStack-.git
cd Proyecto-FullStack-

# 2. Instalar MySQL 8.0
# Windows: https://dev.mysql.com/downloads/mysql/
# macOS: brew install mysql
# Linux: sudo apt-get install mysql-server

# 3. Crear base de datos
mysql -u root -p < db/schema.sql
# Ingresar contraseña

# 4. Compilar todos los módulos
mvn clean install -DskipTests

# 5. Ejecutar cada servicio en terminal separada

# Terminal 1: Eureka Server
cd eureka-server
mvn spring-boot:run
# Output: Eureka Server started on port 8761

# Terminal 2: API Gateway
cd api-gateway
mvn spring-boot:run
# Output: API Gateway started on port 8080

# Terminal 3: MS Usuarios
cd "ms_usuarios (TERMINADO)"
mvn spring-boot:run
# Output: MS Usuarios registered in Eureka

# Terminal 4: MS Películas
cd "ms_peliculas (TERMINADO)"
mvn spring-boot:run

# Terminal 5: MS Perfiles
cd "ms_perfiles(TERMINADO)"
mvn spring-boot:run

# Terminal 6: MS Suscripciones
cd "ms_suscripciones(TERMINADO)"
mvn spring-boot:run
```

**Esperado:** Todos los servicios mostrarán "Successfully registered with Eureka"

### Opción 3: Ejecución Hibrida (Algunos Servicios en Docker)

```bash
# Ejecutar solo la base de datos y Eureka en Docker
docker-compose up mysql eureka-server

# Ejecutar microservicios en local
cd api-gateway && mvn spring-boot:run
```

---

## 🏗️ Arquitectura Detallada

### Diagrama de Arquitectura General

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                          CLIENTE / USUARIO FINAL                             │
├─────────────────────────────────────────────────────────────────────────────┤
│                                    ↓                                         │
│                    ┌────────────────────────────┐                            │
│                    │   API GATEWAY (8080)      │                            │
│                    │ - Punto de entrada único  │                            │
│                    │ - Enrutamiento inteligente│                            │
│                    │ - Circuit Breaker         │                            │
│                    │ - Rate Limiting           │                            │
│                    └────────────────────────────┘                            │
│                              ↓    ↓    ↓    ↓                               │
│          ┌───────────────────┴────┴────┴────┴───────────────────┐          │
│          ↓                    ↓         ↓         ↓              ↓           │
│    ┌──────────┐         ┌──────────┐ ┌──────────┐ ┌──────────┐            │
│    │ Eureka   │         │ MS      │ │ MS      │ │ MS       │            │
│    │ Server   │         │Usuarios │ │Películas│ │Perfiles  │            │
│    │ (8761)   │         │(8081)   │ │(8082)   │ │(8083)    │            │
│    └──────────┘         └──────────┘ └──────────┘ └──────────┘            │
│          ↑                    ↑         ↑         ↑                        │
│          │                    └─────────┴─────────┘                        │
│          │                            ↓                                    │
│          │                    ┌──────────────────┐                         │
│          │                    │ MS Suscripciones │                         │
│          │                    │     (8084)       │                         │
│          │                    └──────────────────┘                         │
│          │                            ↓                                    │
│          │                ┌───────────────────────┐                        │
│          └────────────→   │  MySQL Database      │                        │
│                           │  (3306)              │                        │
│                           │  db_plataforma_      │                        │
│                           │  multimedia          │                        │
│                           └───────────────────────┘                        │
└─────────────────────────────────────────────────────────────────────────────┘
```

### Componentes Principales

#### 1. **API Gateway** 🚪
- **Puerto:** 8080
- **Función:** Punto de entrada único para todos los clientes
- **Características:**
  - Enrutamiento dinámico a microservicios
  - Autenticación centralizada
  - Circuit Breaker para resiliencia
  - Rate limiting
  - Logging centralizado

#### 2. **Eureka Server** 📍
- **Puerto:** 8761
- **Función:** Service Discovery
- **Características:**
  - Registro dinámico de servicios
  - Health checks automáticos
  - Balanceo de carga
  - Dashboard web

#### 3. **Microservicios** 🔹
Cada microservicio es una aplicación Spring Boot independiente.

---

## 🔹 Microservicios

### 📌 MS Usuarios

**Propósito:** Gestión integral de usuarios de la plataforma.

```
┌─────────────────────────────────────┐
│      MS USUARIOS (Puerto 8081)      │
├─────────────────────────────────────┤
│ Responsabilidades:                  │
│ • Registro de usuarios              │
│ • Autenticación                     │
│ • Gestión de perfiles de usuario    │
│ • Validación de credenciales        │
│ • Almacenamiento seguro             │
└─────────────────────────────────────┘
```

#### 📊 Estructura de Datos

```java
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // ID único
    
    @Column(nullable = false)
    @Size(min = 10, max = 100)
    private String nombre;              // Nombre completo
    
    @Column(unique = true, nullable = false)
    @Email
    private String email;               // Email único
    
    @Column(nullable = false)
    @Size(min = 8, max = 65)
    private String contrasena;          // Contraseña encriptada
    
    @Column(nullable = false)
    @Size(min = 8, max = 400)
    private String direccion;           // Dirección postal
    
    @Column(nullable = false)
    @Size(min = 2, max = 70)
    private String pais;                // País de residencia
}
```

#### 🔌 Endpoints

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|----------|
| **GET** | `/api/usuarios` | Listar todos | 200 OK / 204 No Content |
| **GET** | `/api/usuarios/{id}` | Obtener por ID | 200 OK / 404 Not Found |
| **POST** | `/api/usuarios` | Crear usuario | 201 Created |
| **PUT** | `/api/usuarios/{id}` | Actualizar | 200 OK / 404 Not Found |
| **DELETE** | `/api/usuarios/{id}` | Eliminar | 204 No Content / 404 |

#### 📝 Ejemplos de Requests

**Crear Usuario:**
```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Carlos García Pérez",
    "email": "juan.garcia@example.com",
    "contrasena": "SecurePass123!",
    "direccion": "Calle Principal 123, Apartamento 4B",
    "pais": "España"
  }'
```

**Respuesta (201 Created):**
```json
{
  "content": {
    "id": 1,
    "nombre": "Juan Carlos García Pérez",
    "email": "juan.garcia@example.com",
    "contrasena": "$2a$12$...",
    "direccion": "Calle Principal 123, Apartamento 4B",
    "pais": "España"
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/usuarios/1"
    },
    "collection": {
      "href": "http://localhost:8080/api/usuarios"
    }
  }
}
```

**Obtener todos los usuarios:**
```bash
curl -X GET http://localhost:8080/api/usuarios
```

**Respuesta (200 OK):**
```json
{
  "_embedded": {
    "usuarioList": [
      {
        "id": 1,
        "nombre": "Juan Carlos García Pérez",
        "email": "juan.garcia@example.com",
        "_links": { ... }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/usuarios"
    }
  }
}
```

#### 🧪 Tests Incluidos

```
✅ testListarUsuarios()           - Verifica GET /api/usuarios
✅ testListarUsuariosVacio()      - Verifica lista vacía (204)
✅ testBuscarUsuarioPorId()       - Verifica GET /api/usuarios/{id}
✅ testBuscarUsuarioNoExistente() - Verifica 404 Not Found
✅ testCrearUsuario()             - Verifica POST con datos válidos
✅ testActualizarUsuario()        - Verifica PUT
✅ testEliminarUsuario()          - Verifica DELETE
✅ testValidaciones()             - Valida campos requeridos
```

#### 🔐 Seguridad

- Contraseñas encriptadas con BCrypt (fuerza 12)
- Email único validado
- Validaciones en todos los campos
- CORS configurado
- Rate limiting aplicado

---

### 🎬 MS Películas

**Propósito:** Gestión del catálogo de películas disponibles en la plataforma.

```
┌─────────────────────────────────────┐
│      MS PELÍCULAS (Puerto 8082)     │
├─────────────────────────────────────┤
│ Responsabilidades:                  │
│ • Administrar catálogo de películas │
│ • Información de películas          │
│ • Géneros y categorías              │
│ • Disponibilidad y horarios         │
│ • Valoraciones y reviews            │
└─────────────────────────────────────┘
```

#### 📊 Estructura de Datos

```java
@Entity
@Table(name = "pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 150)
    private String titulo;              // Título de la película
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;         // Sinopsis
    
    @Column(nullable = false)
    private Integer anoLanzamiento;     // Año de lanzamiento
    
    @Column(nullable = false)
    @Positive
    private Double duracion;            // Duración en minutos
    
    @Column(nullable = false)
    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private Double calificacion;        // Calificación IMDb
    
    @Column(nullable = false)
    private String genero;              // Género (Acción, Drama, etc)
    
    @Column(nullable = false)
    private String director;            // Nombre del director
}
```

#### 🔌 Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| **GET** | `/api/peliculas` | Listar todas |
| **GET** | `/api/peliculas/{id}` | Obtener película |
| **POST** | `/api/peliculas` | Crear película |
| **PUT** | `/api/peliculas/{id}` | Actualizar |
| **DELETE** | `/api/peliculas/{id}` | Eliminar |
| **GET** | `/api/peliculas/genero/{genero}` | Filtrar por género |
| **GET** | `/api/peliculas/director/{director}` | Filtrar por director |

#### 📝 Ejemplo de Request

**Crear Película:**
```bash
curl -X POST http://localhost:8080/api/peliculas \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Inception",
    "descripcion": "Un ladrón especializado en el robo de secretos corporativos...",
    "anoLanzamiento": 2010,
    "duracion": 148,
    "calificacion": 8.8,
    "genero": "Ciencia Ficción",
    "director": "Christopher Nolan"
  }'
```

#### 🔄 Comunicación Inter-Servicios

MS Películas se comunica con MS Usuarios para:
- Validar que el usuario existe antes de crear una valoración
- Obtener información de suscripción del usuario

```java
@FeignClient(name = "ms-usuarios")
public interface UsuarioClient {
    @GetMapping("/api/usuarios/{id}")
    UsuarioDTO obtenerUsuario(@PathVariable Long id);
}
```

---

### 👤 MS Perfiles

**Propósito:** Gestión de perfiles de usuario en la plataforma.

```
┌─────────────────────────────────────┐
│      MS PERFILES (Puerto 8083)      │
├─────────────────────────────────────┤
│ Responsabilidades:                  │
│ • Crear perfiles por usuario        │
│ • Gestionar preferencias            │
│ • Historial de visualización        │
│ • Recomendaciones personalizadas    │
│ • Listas de favoritos               │
└─────────────────────────────────────┘
```

#### 📊 Estructura de Datos

```java
@Entity
@Table(name = "perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long usuarioId;             // Referencia al usuario
    
    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String nombrePerfil;        // Nombre del perfil
    
    @Column(nullable = false)
    private String avatar;              // URL del avatar
    
    @Column(columnDefinition = "TEXT")
    private String preferencias;        // JSON con preferencias
    
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;
    
    @Column(nullable = false)
    private LocalDateTime ultimaActualizacion;
}
```

#### 🔌 Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| **GET** | `/api/perfiles` | Listar perfiles |
| **GET** | `/api/perfiles/{id}` | Obtener perfil |
| **GET** | `/api/perfiles/usuario/{usuarioId}` | Perfiles por usuario |
| **POST** | `/api/perfiles` | Crear perfil |
| **PUT** | `/api/perfiles/{id}` | Actualizar perfil |
| **DELETE** | `/api/perfiles/{id}` | Eliminar perfil |

#### 🔄 Comunicación Inter-Servicios

```java
// Validar que el usuario existe
@GetMapping("/perfiles/usuario/{usuarioId}")
public ResponseEntity<List<PerfilDTO>> obtenerPorUsuario(
    @PathVariable Long usuarioId) {
    // Llamar a MS Usuarios mediante Feign
    UsuarioDTO usuario = usuarioClient.obtenerUsuario(usuarioId);
    // Proceder si el usuario existe
}
```

---

### 🎯 MS Suscripciones

**Propósito:** Gestión de planes de suscripción y pagos.

```
┌─────────────────────────────────────────┐
│  MS SUSCRIPCIONES (Puerto 8084)        │
├─────────────────────────────────────────┤
│ Responsabilidades:                      │
│ • Planes de suscripción                 │
│ • Gestión de pagos                      │
│ • Renovación automática                 │
│ • Seguimiento de facturación            │
│ • Control de acceso según plan          │
└─────────────────────────────────────────┘
```

#### 📊 Estructura de Datos

```java
@Entity
@Table(name = "suscripcion")
public class Suscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long usuarioId;             // Usuario suscrito
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPlan tipoPlan;          // BASICO, PREMIUM, VIP
    
    @Column(nullable = false)
    private LocalDateTime fechaInicio;  // Fecha de inicio
    
    @Column(nullable = false)
    private LocalDateTime fechaFin;     // Fecha de vencimiento
    
    @Column(nullable = false)
    @Positive
    private BigDecimal precio;          // Monto pagado
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoPago estado;          // ACTIVA, CANCELADA, VENCIDA
}

// Enum para tipos de plan
public enum TipoPlan {
    BASICO("Básico - $4.99/mes"),
    PREMIUM("Premium - $12.99/mes"),
    VIP("VIP - $19.99/mes");
    
    private String descripcion;
}
```

#### 🔌 Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| **GET** | `/api/suscripciones` | Listar todas |
| **GET** | `/api/suscripciones/{id}` | Obtener suscripción |
| **GET** | `/api/suscripciones/usuario/{usuarioId}` | Suscripción activa |
| **POST** | `/api/suscripciones` | Crear suscripción |
| **PUT** | `/api/suscripciones/{id}` | Actualizar plan |
| **DELETE** | `/api/suscripciones/{id}` | Cancelar suscripción |
| **POST** | `/api/suscripciones/{id}/renovar` | Renovar manualmente |

#### 📝 Ejemplo de Request

**Crear Suscripción:**
```bash
curl -X POST http://localhost:8080/api/suscripciones \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "tipoPlan": "PREMIUM",
    "fechaInicio": "2024-01-15T10:00:00",
    "precio": 12.99
  }'
```

#### ⚡ Lógica de Negocio

```java
@Service
@Transactional
public class SuscripcionService {
    
    // Verificar si suscripción está activa
    public boolean estaActiva(Long id) {
        Suscripcion sus = findById(id);
        return sus.getFechaFin().isAfter(LocalDateTime.now())
            && sus.getEstado() == EstadoPago.ACTIVA;
    }
    
    // Renovar suscripción automáticamente
    public void renovarAutomatica(Long usuarioId) {
        Suscripcion sus = findByUsuarioId(usuarioId);
        sus.setFechaInicio(LocalDateTime.now());
        sus.setFechaFin(LocalDateTime.now().plusMonths(1));
        save(sus);
    }
    
    // Validar acceso según plan
    public boolean tieneAcceso(Long usuarioId, String tipoContenido) {
        Suscripcion sus = findByUsuarioId(usuarioId);
        return sus.getTipoPlan().tieneAcceso(tipoContenido);
    }
}
```

---

## 🌉 Servicios de Infraestructura

### 🚪 API Gateway (Puerto 8080)

**Función:** Router centralizado y punto de entrada único.

#### ✨ Características

```yaml
spring:
  cloud:
    gateway:
      routes:
        # Ruta a MS Usuarios
        - id: ms-usuarios
          uri: lb://ms-usuarios
          predicates:
            - Path=/api/usuarios/**
          filters:
            - StripPrefix=1
            - CircuitBreaker  # Resiliencia
            
        # Ruta a MS Películas
        - id: ms-peliculas
          uri: lb://ms-peliculas
          predicates:
            - Path=/api/peliculas/**
          filters:
            - StripPrefix=1
            - CircuitBreaker
            
        # (Más rutas...)
```

#### 🔄 Flujo de Request

```
[Cliente] 
    ↓
[GET http://localhost:8080/api/usuarios/1]
    ↓
[API Gateway]
    ├─ Verifica ruta: /api/usuarios/** → ms-usuarios
    ├─ Valida autenticación
    ├─ Aplica rate limiting
    ├─ Implementa circuit breaker
    └─ Envía a: http://ms-usuarios:8080/usuarios/1
        ↓
    [MS Usuarios responde]
        ↓
    [API Gateway devuelve respuesta al cliente]
```

#### 🛡️ Seguridad en Gateway

```java
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .authorizeExchange()
                // Públicos
                .pathMatchers("/api/usuarios/register").permitAll()
                .pathMatchers("/swagger-ui.html").permitAll()
                // Protegidos
                .anyExchange().authenticated()
            .and()
            .csrf().disable();
        return http.build();
    }
}
```

### 📍 Eureka Server (Puerto 8761)

**Función:** Registro y descubrimiento dinámico de servicios.

#### 🎯 ¿Por qué Eureka?

```
SIN Eureka (Hardcoded):
ms-peliculas → hardcoded http://ms-usuarios:8080
❌ No escalable
❌ Sin failover automático

CON Eureka (Dinámico):
ms-peliculas → busca en Eureka → obtiene IP dinámica
✅ Escalable
✅ Failover automático
✅ Load balancing
```

#### 📊 Dashboard Eureka

Accede a: **http://localhost:8761**

Verás:
- ✅ Instancias registradas
- ✅ Estado de cada servicio
- ✅ Uptime y disponibilidad
- ✅ Métricas de salud

#### 🔄 Ciclo de Vida de un Servicio

```
1. Startup
   └─ Servicio se inicia
   
2. Registro
   └─ Envía: POST /eureka/apps/MS-USUARIOS
      Datos: IP, puerto, healthcheck-URL
   
3. Heartbeat
   └─ Cada 30 segundos: "Estoy vivo"
   
4. Discovery
   └─ Otros servicios consultan: "¿Dónde está MS-USUARIOS?"
      Eureka responde con lista de instancias
   
5. Shutdown
   └─ Envía: DELETE /eureka/apps/MS-USUARIOS
      Eureka lo elimina inmediatamente
```

---

## 📡 Flujos de Comunicación Entre Servicios

### Escenario 1: Usuario consulta películas y valida suscripción

```
SECUENCIA DE LLAMADAS:

┌─────────────┐
│   Cliente   │
└──────┬──────┘
       │
       │ GET /api/usuarios/1/peliculas
       ▼
┌─────────────────────────────────────────┐
│        API GATEWAY (8080)               │
│ - Recibe request                        │
│ - Valida token JWT                      │
│ - Enruta a MS-USUARIOS                  │
└──────┬──────────────────────────────────┘
       │
       │ Feign: ms-usuarios/1
       ▼
┌──────────────────────────────────┐
│   MS USUARIOS (8081)             │
│ - Obtiene usuario                │
│ - Verifica suscripción activa    │
│ - Llama a MS-SUSCRIPCIONES       │
└──────┬───────────────────────────┘
       │
       │ Feign: ms-suscripciones/usuario/1
       ▼
┌──────────────────────────────────┐
│   MS SUSCRIPCIONES (8084)        │
│ - Valida suscripción activa      │
│ - Retorna plan disponible        │
└──────┬───────────────────────────┘
       │
       │ Response con detalles
       ▼
┌──────────────────────────────────┐
│   MS USUARIOS (8081)             │
│ - Obtiene catálogo de películas  │
│ - Filtra según plan              │
│ - Llama a MS-PELÍCULAS           │
└──────┬───────────────────────────┘
       │
       │ Feign: ms-peliculas/disponibles
       ▼
┌──────────────────────────────────┐
│   MS PELÍCULAS (8082)            │
│ - Retorna películas disponibles  │
│   para el plan PREMIUM           │
└──────┬───────────────────────────┘
       │
       │ Respuesta compilada
       ▼
┌──────────────────────────────────┐
│   API GATEWAY (8080)             │
│ - Agrega respuestas              │
│ - Añade links HATEOAS            │
└──────┬───────────────────────────┘
       │
       │ JSON con películas + HATEOAS
       ▼
┌─────────────────────────────────┐
│       Cliente                   │
│ Recibe respuesta completa       │
└─────────────────────────────────┘
```

### Escenario 2: Crear perfil de usuario

```
REQUEST:
POST /api/perfiles
{
  "usuarioId": 1,
  "nombrePerfil": "Mi Perfil Premium"
}

FLUJO:

1. Cliente → API Gateway (8080)
   └─ Validación y enrutamiento

2. API Gateway → MS Perfiles (8083)
   └─ StripPrefix=1 → /perfiles

3. MS Perfiles (8083)
   ├─ Recibe request
   ├─ Llama a MS Usuarios para validar usuario
   │  └─ GET /api/usuarios/1 (Feign)
   ├─ Valida datos
   └─ Guarda en BD si es válido

4. MS Usuarios (8081) responde
   └─ 200 OK con datos del usuario

5. MS Perfiles (8083)
   ├─ Crea nuevo perfil
   ├─ Guarda en BD
   └─ Retorna 201 CREATED

6. API Gateway agrega links HATEOAS

7. Cliente recibe respuesta
```

### Escenario 3: Fallo en Cascada (Circuit Breaker)

```
SITUACIÓN: MS Usuarios NO responde

Intento 1: Timeout (5 segundos)
└─ Fallido ✗

Intento 2 & 3: Reintentos automáticos
└─ Fallido ✗

Circuit Breaker:
└─ Abre el circuito (modo OPEN)
└─ Siguiente requests retornan error inmediato
└─ Evita sobrecargar el servicio caído

Ms Películas:
└─ Usa fallback: "Servicio no disponible"
└─ Mantiene su propio funcionamiento ✓

Recuperación:
└─ Circuito → HALF_OPEN después de 30 segundos
└─ Intenta llamada de prueba
└─ Si funciona → Vuelve a CLOSED
```

---

## 💾 Estructura de Base de Datos

### Diagrama ER

```
┌─────────────────────────┐
│       USUARIO           │
├─────────────────────────┤
│ PK id                   │
│ nombre                  │
│ email (UNIQUE)          │
│ contrasena              │
│ direccion               │
│ pais                    │
│ fecha_creacion          │
│ fecha_actualizacion     │
└──────────┬──────────────┘
           │
      1    │    N
           │
    ┌──────▼──────────┐    ┌──────────────────┐
    │    PERFIL       │◄───┤   PELICULA       │
    ├─────────────────┤    ├──────────────────┤
    │ PK id           │    │ PK id            │
    │ FK usuario_id   │    │ titulo           │
    │ nombre_perfil   │    │ descripcion      │
    │ avatar          │    │ genero           │
    │ preferencias    │    │ director         │
    │ created_at      │    │ ano_lanzamiento  │
    └─────────────────┘    │ duracion         │
                           │ calificacion     │
           │               │ fecha_agregada   │
           │               └──────────────────┘
      1    │    N
           │
    ┌──────▼──────────────────┐
    │    SUSCRIPCION          │
    ├─────────────────────────┤
    │ PK id                   │
    │ FK usuario_id           │
    │ tipo_plan               │
    │ fecha_inicio            │
    │ fecha_fin               │
    │ precio                  │
    │ estado_pago             │
    │ fecha_renovacion        │
    └─────────────────────────┘
```

### Creación de Tablas (Flyway)

```sql
-- db/migration/V1__Create_usuario_table.sql
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(320) NOT NULL UNIQUE,
    contrasena VARCHAR(65) NOT NULL,
    direccion VARCHAR(400) NOT NULL,
    pais VARCHAR(70) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email)
);
```

### Relaciones y Restricciones

| Relación | Tipo | Descripción | Cascada |
|----------|------|-------------|---------|
| Usuario → Perfil | 1:N | Un usuario tiene N perfiles | ON DELETE CASCADE |
| Usuario → Suscripción | 1:1 | Un usuario con una suscripción activa | ON DELETE CASCADE |
| Película → Perfil | N:M | Muchas películas en favoritos | - |

---

## 🧪 Tests y Cobertura

### Ejecutar Tests

```bash
# Todos los tests
mvn test

# Tests específicos
mvn test -Dtest=UsuarioControllerTest

# Con cobertura
mvn test jacoco:report
open target/site/jacoco/index.html
```

### Cobertura Esperada

| Clase | Métodos | Líneas | Estado |
|-------|---------|--------|--------|
| UsuarioController | 100% | 95% | ✅ |
| UsuarioService | 100% | 98% | ✅ |
| **Total** | **92%** | **93%** | **✅** |

---

## 🔐 Seguridad y Autenticación

### Encriptación de Contraseñas

```java
PasswordEncoder encoder = new BCryptPasswordEncoder(12);
String rawPassword = "MiContrasena123!";
String hashedPassword = encoder.encode(rawPassword);
```

### Variables de Entorno Seguras

**.env (GITIGNORE):**
```env
DB_USERNAME=app_user
DB_PASSWORD=super_password_segura_123!#$
JWT_SECRET=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

## 📊 Monitoreo y Métricas

### Endpoints de Health Check

```bash
curl http://localhost:8081/actuator/health
```

### Métricas Disponibles

```bash
curl http://localhost:8081/actuator/prometheus
```

---

## 🔧 Troubleshooting

### Problema: Servicios no se registran en Eureka

```bash
# 1. Verificar que Eureka está corriendo
curl http://localhost:8761

# 2. Ver logs de Eureka
docker-compose logs eureka-server

# 3. Reiniciar servicio
docker-compose restart ms-usuarios
```

### Problema: API Gateway no puede conectar

```bash
# 1. Verificar que todos los servicios están corriendo
docker-compose ps

# 2. Revisar logs del gateway
docker-compose logs api-gateway

# 3. Reiniciar gateway
docker-compose restart api-gateway
```

### Problema: Base de datos no inicia

```bash
# 1. Verificar que MySQL está corriendo
docker-compose ps mysql

# 2. Eliminar volumen
docker-compose down -v
docker-compose up mysql
```

---

## 🛠️ Tecnologías Utilizadas

### Backend

| Tecnología | Versión | Propósito |
|-----------|---------|----------|
| **Java** | 21 LTS | Lenguaje de programación |
| **Spring Boot** | 4.0.6 | Framework principal |
| **Spring Cloud** | 2025.1.1 | Microservicios |
| **Spring Data JPA** | - | Persistencia de datos |
| **Spring Security** | - | Autenticación/Autorización |

### Herramientas de Desarrollo

| Herramienta | Propósito |
|-----------|----------|
| **Maven** | Build tool |
| **Docker** | Containerización |
| **JUnit 5** | Testing |
| **Mockito** | Mocking |
| **Swagger** | Documentación |

---

## 📚 Convenciones de Código

```java
// Clases: PascalCase
public class UsuarioController { }

// Métodos: camelCase
public ResponseEntity<UsuarioDTO> obtenerUsuario(Long id) { }

// Variables: camelCase
private Long usuarioId;

// Constantes: UPPER_SNAKE_CASE
private static final String API_BASE_URL = "http://localhost:8080";
```

---

## ✅ Checklist de Implementación

- [x] Swagger/OpenAPI documentación automática
- [x] Spring HATEOAS implementado en respuestas
- [x] Data Faker para generación de datos de prueba
- [x] JUnit 5 con cobertura mínima 80%
- [x] Eureka Server para service discovery
- [x] API Gateway como punto de entrada
- [x] YAML configuration para todos los servicios
- [x] Docker y Docker Compose
- [x] OpenFeign para comunicación inter-servicios

---

## 🚀 Próximos Pasos

- [ ] Implementar JWT (JSON Web Tokens)
- [ ] Agregar Redis para caché
- [ ] Implementar Elasticsearch
- [ ] ELK Stack para logging
- [ ] GitHub Actions CI/CD
- [ ] Kubernetes deployment

---

<div align="center">

**Hecho con ❤️ por estudiantes de Ingeniería en Informática**

**Última actualización:** 2026-06-11

**Estado:** ✅ LISTO PARA ENTREGA FINAL

[⬆ Volver al inicio](#-plataforma-multimedia---sistema-de-microservicios)

</div>
