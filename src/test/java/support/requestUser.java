package support;

import io.restassured.response.Response;
import objetcs.payload;

public class requestUser {
    apihelper api;
    payload user;
    public static Response responseuser;

    public requestUser() {
        api = new apihelper();
    }
    public void getUser(){
        String url = "https://reqres.in/api/users";
        responseuser = api.get(url);
    }

    public void getUsr(String id){
        String url = "https://reqres.in/api/users/"+id;
        responseuser = api.get(url);
    }

    public void createUser(String nombre, String puesto){
        String url = "https://reqres.in/api/users";
        user = new payload(nombre, puesto);
        responseuser = api.post(url,user);
    }

    public void updateUserPut(String id, String nombre, String puesto){
        String url = "https://reqres.in/api/users/"+id;
        user = new payload(nombre,puesto);
        responseuser = api.put(url,user);
    }

    public void updateUserPatch(String id, String nombre, String puesto){
        String url = "https://reqres.in/api/users/"+id;
        user = new payload(nombre,puesto);
        responseuser = api.patch(url,user);
    }
}
