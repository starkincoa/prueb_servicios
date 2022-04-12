package objetcs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class payload {
    @JsonProperty(value = "name") private String nombre;
    @JsonProperty(value = "job") private String puesto;

    public payload(String nombre, String puesto) {
        this.nombre = nombre;
        this.puesto = puesto;
    }
}
