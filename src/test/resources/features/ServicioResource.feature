Feature: Prueba de servicio de resources

  Scenario: Listar todos los resources
    Given Listar resources
    When Mostrar el listado de resources
    And Validar codido de respuesta "200"
    Then Validar numero de registros

  Scenario: Listar un resource
    Given Listar resource con id "2"
    When Mostrar el listado de resources
    And Validar codido de respuesta "200"
    Then Validar informacion de lo consultado
      | name         | year | color   |
      | fuchsia rose | 2001 | #C74375 |

  Scenario: Resource no encontrado
    Given Listar resource con id "23"
    And Validar codido de respuesta "404"
