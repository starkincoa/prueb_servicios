package definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import support.requestResource;

import java.util.List;
import java.util.Map;

public class ServicioResourceDefinition {
    //Declaramos la variable resource
    requestResource resource;

    //Constructor para inicializar variable
    public ServicioResourceDefinition() {
        resource = new requestResource();
    }

    //Usamos el motodo get para listar los usuarios
    @Given("Listar resources")
    public void listarResources() {
        resource.getResource();
    }

//CReamos una variable responsebody para almacenar los datos
    @When("Mostrar el listado de resources")
    public void mostrarElListadoDeResources() {
        ResponseBody body = requestResource.responseResource;
        System.out.print(body.asString());
    }


    @And("Validar codido de respuesta {string}")
    public void validarCodidoDeRespuesta(String codigo) {
        Assert.assertEquals(Integer.parseInt(codigo),requestResource.responseResource.getStatusCode());
    }

    @Then("Validar numero de registros")
    public void validarNumeroDeRegistros() {
        ResponseBody body = requestResource.responseResource;
        //Guardamos el body en un archivo json y lo convertimos en string
        JsonPath json = new JsonPath(body.asString());
        List<String> listado = json.with(body.asString()).get("data");
        int cantidad = json.getInt("per_page");
        Assert.assertEquals(cantidad,listado.size());
    }

    @Given("Listar resource con id {string}")
    public void listarResourceConId(String id) {
        resource.getResources(id);
    }

//En parametros definir Datatable para que funcione el asMaps
    @Then("Validar informacion de lo consultado")
    public void validarInformacionDeLoConsultado(DataTable resource) {
        ResponseBody body = requestResource.responseResource;
        //Guardamos el body en un archivo json y lo convertimos en string
        JsonPath json = new JsonPath(body.asString()).setRootPath("data");
        List<Map<String, String>> data = resource.asMaps(String.class, String.class);
        for (int i=0; i < data.size(); i++){
            Assert.assertEquals(data.get(i).get("name"), json.getString("name"));
            Assert.assertEquals(data.get(i).get("year"), json.getString("year"));
            Assert.assertEquals(data.get(i).get("color"), json.getString("color"));
        }

    }
}
