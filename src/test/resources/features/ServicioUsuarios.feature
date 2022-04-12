@todoslosservicios
Feature: Pruebas de servicio al usuario

@metodoget
  Scenario: Listar todos los usuarios
    Given Listar usuarios
    When Mostrar el listado de usuarios
    And Validar codigo de respuesta "200"
    Then Validar num de registros

@metodoget @regresion
  Scenario: Listar un usuario
    Given Listar usuario con id "8"
    When Mostrar informacion del usuario
    And Validar codigo de respuesta "200"
    Then Validar informacion de la consulta
      | email                      | nombre  | apellido |
      | lindsay.ferguson@reqres.in | Lindsay | Ferguson |

@metodoget @regresion
  Scenario: Usuario no registrado
    Given Listar usuario con id "50"
    Then Validar codigo de respuesta "404"

@metodopost  @regresion
  Scenario: Crear usuario
    Given que no existe usuario registrado
    When registrar datos del usuario
      | nombre     | puesto | codigo |
      | Kristofers | qa     | 201    |
      | Kevin      | qa     | 201    |
      | Fabrizio   | qa     | 201    |
    Then Validar codigo de respuesta "201"
    And Mostrar los datos del registro

@metodoput
  Scenario: Actualizar usuario
    Given que el usuario este registrado
    When actualizar datos del usuario
      | id | nombre     | puesto |codigo|
      | 15 | Kristofers | dev    |200   |
    Then Validar codigo de respuesta "200"
    And Mostrar los datos del registro


@metodopatch
  Scenario: Actualizar usuario con patch
    Given que el usuario este registrado
    When actualizar datos del usuario con patch
    |id|nombre|puesto|codigo|
    |11|Frank |Qa    |200   |
    Then Validar codigo de respuesta "200"
    And Mostrar los datos del registro

