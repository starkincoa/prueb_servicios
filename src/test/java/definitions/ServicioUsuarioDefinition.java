package definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import support.requestUser;

import java.util.List;
import java.util.Map;

public class ServicioUsuarioDefinition {
    requestUser user;

//Constructor para inicializar variables
    public ServicioUsuarioDefinition() {
        user = new requestUser();
    }

    @Given("Listar usuarios")
    public void listarUsuarios() {
        user.getUser();
    }

    @When("Mostrar el listado de usuarios")
    public void mostrarElListadoDeUsuarios() {
        ResponseBody body = requestUser.responseuser;
        System.out.print(body.asString());
    }

    @And("Validar codigo de respuesta {string}")
    public void validarCodigoDeRespuesta(String codigo) {
        Assert.assertEquals(Integer.parseInt(codigo),requestUser.responseuser.getStatusCode());
    }

    @Then("Validar num de registros")
    public void validarNumDeRegistros() {
        ResponseBody body = requestUser.responseuser;
        //guardamos el body en un archivo json
        JsonPath json = new JsonPath(body.asString());
        //System.out.print("object"+body);
        //creamos una lista con valores internos de tipo string
        //con el json.with leo el json pero SOLO LA PARTE DE DATA
        List<String> listado = json.with(body.asString()).get("data");
        int cantidad = json.getInt("per_page");
        Assert.assertEquals(cantidad,listado.size());
    }

    @Given("Listar usuario con id {string}")
    public void listarUsuarioConId(String id) {
        user.getUsr(id);
    }

    @When("Mostrar informacion del usuario")
    public void mostrarInformacionDelUsuario() {
        mostrarElListadoDeUsuarios();
    }

    @Then("Validar informacion de la consulta")
    public void validarInformacionDeLaConsulta(DataTable user) {
        ResponseBody body =requestUser.responseuser;
        JsonPath json = new JsonPath(body.asString()).setRootPath("data");
        List<Map<String, String>> data = user.asMaps(String.class, String.class);
        for (int i=0; i < data.size(); i++){
            Assert.assertEquals(data.get(i).get("email"), json.getString("email"));
            Assert.assertEquals(data.get(i).get("nombre"), json.getString("first_name"));
            Assert.assertEquals(data.get(i).get("apellido"), json.getString("last_name"));
        }
    }

    @Given("que no existe usuario registrado")
    public void queNoExisteUsuarioRegistrado() {
    }

    @When("registrar datos del usuario")
    public void registrarDatosDelUsuario(DataTable dt) {
        List<Map<String, String>> data = dt.asMaps(String.class, String.class);
        for (int i=0; i < data.size(); i++){
            user.createUser(data.get(i).get("nombre"), data.get(i).get("puesto"));
            validarCodigoDeRespuesta(data.get(i).get("codigo"));
            mostrarElListadoDeUsuarios();
        }
    }


    @And("Mostrar los datos del registro")
    public void mostrarLosDatosDelRegistro() {
        mostrarElListadoDeUsuarios();
    }

    @Given("que el usuario este registrado")
    public void queElUsuarioEsteRegistrado() {
    }


    @When("actualizar datos del usuario")
    public void actualizarDatosDelUsuario(DataTable dt) {
        List<Map<String, String>> data = dt.asMaps(String.class, String.class);
        for (int i=0; i < data.size(); i++){
            user.updateUserPut(data.get(i).get("id"),data.get(i).get("nombre"), data.get(i).get("puesto"));
            validarCodigoDeRespuesta(data.get(i).get("codigo"));
            mostrarElListadoDeUsuarios();
        }
    }

    @When("actualizar datos del usuario con patch")
    public void actualizarDatosDelUsuarioConPatch(DataTable dt) {
        List<Map<String, String>> data = dt.asMaps(String.class, String.class);
        for (int i=0; i < data.size(); i++){
            user.updateUserPatch(data.get(i).get("id"),data.get(i).get("nombre"), data.get(i).get("puesto"));
            validarCodigoDeRespuesta(data.get(i).get("codigo"));
            mostrarElListadoDeUsuarios();
        }
    }
}
