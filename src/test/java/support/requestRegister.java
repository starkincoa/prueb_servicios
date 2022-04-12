package support;

import io.restassured.response.Response;
import objetcs.payloadd;

public class requestRegister {
    apihelper api;
    payloadd registro;

    public static Response responseuser;

    public requestRegister() {
        api = new apihelper();
    }

    public void createRegister(String correo, String clave){
        String url="https://reqres.in/api/register";
        registro = new payloadd(correo,clave);
        responseuser = api.post(url,registro);
    }
}
