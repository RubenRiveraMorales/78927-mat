package mx.uv.practica04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.saludos.BuscarSaludosResponse;
import https.t4is_uv_mx.saludos.EliminarSaludoRequest;
import https.t4is_uv_mx.saludos.EliminarSaludoResponse;
import https.t4is_uv_mx.saludos.ModificarSaludoRequest;
import https.t4is_uv_mx.saludos.ModificarSaludoResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;

@Endpoint
public class EndPoint {
    @Autowired
    private Isaludador iSaludador;
    @PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")
    

    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion){
        SaludarResponse response = new SaludarResponse();
        response.setRespuesta("Hola " + peticion.getNombre());
       

        //PERSISTENCIA BD               

        Saludador saludador = new Saludador();
        saludador.setNombre(peticion.getNombre());
        iSaludador.save(saludador);
        

        return response;
    }
    
    @PayloadRoot(localPart = "BuscarSaludosRequest" ,namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarSaludosResponse buscarSaludos(){
        BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
        Iterable<Saludador> lista= iSaludador.findAll();
       
        
        for (Saludador saludo : lista) {
            BuscarSaludosResponse.Saludos e = new BuscarSaludosResponse.Saludos();
            e.setNombre(saludo.getNombre());
            e.setId(saludo.getId());
            respuesta.getSaludos().add(e);
        }
        return respuesta;
    }


    @PayloadRoot(localPart = "ModificarSaludoRequest" ,namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public ModificarSaludoResponse modificarSaludo(@RequestPayload ModificarSaludoRequest peticion){       
        ModificarSaludoResponse respuesta = new ModificarSaludoResponse(); 
        Saludador saludo = new Saludador();
        saludo.setId(peticion.getId());
        saludo.setNombre(peticion.getNombre());
        iSaludador.save(saludo);
        respuesta.setRespuesta("Saludo modificado");        
        return respuesta;
    }

    @PayloadRoot(localPart = "EliminarSaludoRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public EliminarSaludoResponse eliminarSaludo(@RequestPayload EliminarSaludoRequest peticion){
        EliminarSaludoResponse respuesta = new EliminarSaludoResponse();
        iSaludador.deleteById(peticion.getId());
        respuesta.setRespuesta("Saludo eliminado");
        return respuesta;
    }


}