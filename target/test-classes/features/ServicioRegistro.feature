Feature: Prueba de servicio de rgistro

  Scenario: Crear un registro exitoso
    Given que no existe ese registro
    When guardar datos del resgistro
      | correo             | clave  |codigo|
      | eve.holt@reqres.in | pistol |200   |
    Then Validar codigo de rpta "200"
    And Mostrar el registro
