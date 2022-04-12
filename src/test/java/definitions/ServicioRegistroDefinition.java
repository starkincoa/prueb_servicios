package definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import support.requestRegister;
import support.requestUser;

import java.util.List;
import java.util.Map;

public class ServicioRegistroDefinition {
    requestRegister registro;

    public ServicioRegistroDefinition() {
        registro = new requestRegister();
    }

    @Given("que no existe ese registro")
    public void queNoExisteEseRegistro() {

    }

    @When("guardar datos del resgistro")
    public void guardarDatosDelResgistro(DataTable dt) {
        List<Map<String, String>> data = dt.asMaps(String.class, String.class);
        for (int i=0; i < data.size(); i++){
            registro.createRegister(data.get(i).get("correo"), data.get(i).get("clave"));
            validarCodigoDeRpta(data.get(i).get("codigo"));
            mostrarElRegistro();
        }
    }


    @Then("Validar codigo de rpta {string}")
    public void validarCodigoDeRpta(String codigo) {
        Assert.assertEquals(Integer.parseInt(codigo), requestRegister.responseuser.getStatusCode());
    }


    @And("Mostrar el registro")
    public void mostrarElRegistro() {
        ResponseBody body = requestRegister.responseuser;
        System.out.print(body.asString());
    }
}
