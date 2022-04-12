package objetcs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class payloadd {
    @JsonProperty(value = "email") private String correo;
    @JsonProperty(value = "password") private String clave;

    public payloadd(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }
}
