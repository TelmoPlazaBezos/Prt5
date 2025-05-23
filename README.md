# P5
Aplicación web que usa Spring JPA para persistir los datos de un API REST de gestión de usuarios.
El API permite el registro de nuevos usuarios y su identificación mediante email y password.
Una vez identificados, se emplea una cookie de sesión para autenticar las peticiones que permiten 
a los usuarios leer, modificar y borrar sus datos. También existe un endpoint para cerrar la sesión.  

## Endpoints

// TODO#1: rellena la tabla siguiente analizando el código del proyecto

## Endpoints

| Método | Ruta                      | Descripción                                          | Respuestas                                                  |
|--------|---------------------------|------------------------------------------------------|-------------------------------------------------------------|
| POST   | `/api/users`              | Registrar un nuevo usuario                           | `201 Created` con `ProfileResponse` <br> `409 Conflict` si el usuario ya existe |
| POST   | `/api/users/me/session`   | Iniciar sesión y crear cookie de sesión              | `201 Created` con cookie de sesión <br> `401 Unauthorized` si credenciales inválidas |
| DELETE | `/api/users/me/session`   | Cerrar sesión eliminando cookie de sesión            | `204 No Content` <br> `401 Unauthorized` si no autenticado |
| GET    | `/api/users/me`           | Obtener perfil del usuario autenticado               | `200 OK` con `ProfileResponse` <br> `401 Unauthorized` si no autenticado |
| PUT    | `/api/users/me`           | Actualizar perfil del usuario autenticado            | `200 OK` con `ProfileResponse` <br> `401 Unauthorized` si no autenticado |
| DELETE | `/api/users/me`           | Eliminar cuenta del usuario autenticado              | `204 No Content` <br> `401 Unauthorized` si no autenticado |



## Comandos 

- Construcción: 
  ```sh
  ./mvnw clean package
  ```

- Ejecución: 
  ```sh
  ./mvnw spring-boot:run
  ```

- Tests:
  ```sh
  ./mvnw test
  ```
#   P r t 5 
 
 
