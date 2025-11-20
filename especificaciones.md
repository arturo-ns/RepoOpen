<!-- Spring Web (REST API) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- JPA (ORM y repositorios) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Validaciones (@NotNull, @Size, @Email, @Min, @Max, etc.) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<!-- Driver MySQL -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- Lombok (para reducir código) -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>

<!-- Swagger/OpenAPI (documentación) -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.13</version>
</dependency>

# Guía de Plantillas y Modificaciones del bounded_Context

**Modificaciones mínimas**:
- Cambiar `Xxx` por el nombre de tu entidad
- Agregar métodos de búsqueda específicos según necesidades

### 1. **Command Service Implementations** (Application Layer) (ARCHIVO QUE REQUIERE MODIFICACIONES ESPECÍFICAS)
**Ubicación**: `{context}/application/internal/commandservices/`

**Ejemplo**: `UserCommandServiceImpl.java`, `CourseCommandServiceImpl.java`

**¿Por qué requiere modificaciones?**
- Contiene la lógica de aplicación específica
- Validaciones de negocio
- Transformaciones de datos
- Llamadas a servicios externos

**Modificaciones necesarias**:
- Implementar lógica de creación/actualización específica
- Agregar validaciones de negocio
- Manejar excepciones específicas
- Integrar con otros servicios si es necesario

**Puedes usar como referencia**: `CourseCommandServiceImpl.java` para ver el patrón completo.

---

### 2. **Query Service Implementations** (Application Layer)
**Ubicación**: `{context}/application/internal/queryservices/`

**Ejemplo**: `UserQueryServiceImpl.java`, `CourseQueryServiceImpl.java`

**Patrón**:
```java
@Service
public class XxxQueryServiceImpl implements XxxQueryService {
    private final XxxRepository xxxRepository;
    
    public XxxQueryServiceImpl(XxxRepository xxxRepository) {
        this.xxxRepository = xxxRepository;
    }
    
    @Override
    public List<Xxx> handle(GetAllXxxsQuery query) {
        return xxxRepository.findAll();
    }
    
    @Override
    public Optional<Xxx> handle(GetXxxByIdQuery query) {
        return xxxRepository.findById(query.xxxId());
    }
}
```

**Modificaciones mínimas**:
- Cambiar `Xxx` por el nombre de tu entidad
- Cambiar nombres de repositorio
- Agregar lógica de búsqueda específica si es necesario

---

### 3. **Aggregates** (Domain Layer) (ARCHIVO QUE REQUIERE MODIFICACIONES ESPECÍFICAS)
**Ubicación**: `{context}/domain/model/aggregates/`

**Ejemplo**: `User.java`, `Student.java`, `Course.java`

**¿Por qué requiere modificaciones?**
- Contiene la lógica de negocio específica del dominio
- Define relaciones entre entidades
- Métodos de negocio únicos para cada agregado
- Validaciones específicas

**Modificaciones necesarias**:
- Cambiar campos y propiedades según el modelo
- Implementar métodos de negocio específicos
- Definir relaciones JPA (@ManyToOne, @OneToMany, etc.)
- Agregar validaciones de negocio

**Puedes usar como referencia**: `Student.java` o `User.java` para entender la estructura base.

---

### 4. **Commands** (Domain Layer)
**Ubicación**: `{context}/domain/model/commands/`

**Ejemplo**: `SignUpCommand.java`, `CreateCourseCommand.java`, `UpdateCourseCommand.java`

**Patrón**:
```java
public record CreateXxxCommand(String field1, String field2) {}
public record UpdateXxxCommand(Long xxxId, String field1) {}
public record DeleteXxxCommand(Long xxxId) {}
```

**Modificaciones mínimas**:
- Cambiar nombre del comando
- Agregar/quitar campos según el modelo de datos
- Cambiar tipos de datos

---

### 5. **Value Objects** (Domain Layer) (ARCHIVO QUE REQUIERE MODIFICACIONES ESPECÍFICAS)
**Ubicación**: `{context}/domain/model/valueobjects/`

**Ejemplo**: `ProfileId.java`, `AcmeStudentRecordId.java`, `EmailAddress.java`

**¿Por qué requiere modificaciones?**
- Representa conceptos del dominio específicos
- Validaciones de negocio únicas
- Lógica de creación específica

**Modificaciones necesarias**:
- Definir campos según el concepto
- Implementar validaciones específicas
- Agregar métodos de negocio si es necesario

---

### 6. **Query Service Interfaces** (Domain Layer)
**Ubicación**: `{context}/domain/services/`

**Ejemplo**: `UserQueryService.java`, `CourseQueryService.java`

**Patrón**:
```java
public interface XxxQueryService {
    List<Xxx> handle(GetAllXxxsQuery query);
    Optional<Xxx> handle(GetXxxByIdQuery query);
    Optional<Xxx> handle(GetXxxByFieldQuery query);
}
```

### 7. **Command Service Interfaces** (Domain Layer)
**Ubicación**: `{context}/domain/services/`

**Ejemplo**: `UserCommandService.java`, `CourseCommandService.java`

**Patrón**:
```java
public interface XxxCommandService {
    Optional<Xxx> handle(CreateXxxCommand command);
    Optional<Xxx> handle(UpdateXxxCommand command);
    void handle(DeleteXxxCommand command);
}
```

### 8. **Repositories** (Infrastructure Layer)
**Ubicación**: `{context}/infrastructure/persistence/jpa/repositories/`

**Ejemplo**: `UserRepository.java`, `CourseRepository.java`, `StudentRepository.java`

**Patrón**:
```java
@Repository
public interface XxxRepository extends JpaRepository<Xxx, Long> {
    Optional<Xxx> findByField(String field);
    boolean existsByField(String field);
}
```

### 9. **REST Controllers** (Interfaces Layer) (ARCHIVO QUE REQUIERE MODIFICACIONES ESPECÍFICAS)
**Ubicación**: `{context}/interfaces/rest/`

**Ejemplo**: `AuthenticationController.java`, `CoursesController.java`, `UsersController.java`

**¿Por qué requiere modificaciones?**
- Define endpoints específicos
- Maneja respuestas HTTP según el caso de uso
- Validaciones de entrada
- Documentación OpenAPI específica

**Modificaciones necesarias**:
- Cambiar rutas de endpoints
- Agregar/quitar endpoints según necesidades
- Modificar respuestas HTTP
- Actualizar documentación Swagger

**Puedes usar como referencia**: `CoursesController.java` para ver un CRUD completo.

---

### 10. **Resources** (Interfaces Layer)
**Ubicación**: `{context}/interfaces/rest/resources/`

**Ejemplo**: `UserResource.java`, `CourseResource.java`, `CreateCourseResource.java`

**Patrón**:
```java
public record XxxResource(Long id, String field1, String field2) {}
public record CreateXxxResource(String field1, String field2) {}
public record UpdateXxxResource(String field1, String field2) {}
```

**Modificaciones mínimas**:
- Cambiar nombre del resource
- Agregar/quitar campos según el modelo
- Cambiar tipos de datos

---

### 11. **Assemblers** (Interfaces Layer)
**Ubicación**: `{context}/interfaces/rest/transform/`

**Ejemplo**: `UserResourceFromEntityAssembler.java`, `CourseResourceFromEntityAssembler.java`

**Patrón**:
```java
public class XxxResourceFromEntityAssembler {
    public static XxxResource toResourceFromEntity(Xxx xxx) {
        return new XxxResource(
            xxx.getId(),
            xxx.getField1(),
            xxx.getField2()
        );
    }
}
```

**Modificaciones mínimas**:
- Cambiar nombres de clases
- Mapear campos de la entidad al resource
- Agregar transformaciones de datos si es necesario

---