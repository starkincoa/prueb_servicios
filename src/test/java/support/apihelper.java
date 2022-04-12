package support;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class apihelper {

    //En el metodo get las consultas se realizan a traves de la url
    public Response get(String url){
        Response response = given().get(url);
        //Response response2 = given().baseUri(url).get();
        return response;
    }

    //El metodo post necesita de un body
    //por lo tanto creamos un objeto llamado payload para guardar el body
    //cuando creamos la variable response, pasamos el payload por el parametro
    //Y colocamos el tipo de contenido que tendra, en este caso json
    //posteriormente colocamos la url
    public Response post(String url, Object payload){
        Response response = given().body(payload).contentType("application/json").post(url);
        return response;
    }

    public Response put(String url, Object payload){
        Response response = given().body(payload).contentType("application/json").put(url);
        return response;
    }

    public Response patch(String url, Object payload){
        Response response = given().body(payload).contentType("application/json").patch(url);
        return response;
    }


}
