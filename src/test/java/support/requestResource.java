package support;

import io.restassured.response.Response;

public class requestResource {
    apihelper api;
    //creamos variable tipo response
    public static Response responseResource;
    public static Response responseResource1;

    //inicializamos variable
    public requestResource() {
        api = new apihelper();
    }

    //Creamos metodo para definir el link
    public void getResource(){
        String url = "https://reqres.in/api/unknown";
        responseResource = api.get(url);
    }

    //CReamos metodo para definir el link mas extension
    public void getResources(String id){
        String url = "https://reqres.in/api/unknown/"+id;
        responseResource = api.get(url);
    }

}
