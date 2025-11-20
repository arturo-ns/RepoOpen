# Archivos Modificados y Creados - Gestión de Excepciones

## Archivos Creados

### Excepciones de Dominio
- `bounded_context/domain/exceptions/PartnerAlreadyExistsException.java`
- `bounded_context/domain/exceptions/InvalidEmailAddressException.java`
- `bounded_context/domain/exceptions/InvalidPhoneNumberException.java`
- `bounded_context/domain/exceptions/InvalidPersonNameException.java`
- `bounded_context/domain/exceptions/ValidationException.java`

### Manejador Global de Excepciones
- `bounded_context/interfaces/rest/exceptionhandlers/GlobalExceptionHandler.java`

## Archivos Modificados

### Capa de Aplicación
- `bounded_context/application/internal/commandservices/PartnerCommandServiceImpl.java`
  - Actualizado para usar excepciones personalizadas en lugar de `IllegalArgumentException` genéricas
  - Reemplazadas las excepciones genéricas por excepciones específicas del dominio

### Capa de Interfaces
- `bounded_context/interfaces/rest/PartnersController.java`
  - Simplificado el manejo de excepciones (ahora manejado por `GlobalExceptionHandler`)
  - Removido el bloque try-catch manual
  - Actualizado el código de respuesta HTTP 409 para conflictos
