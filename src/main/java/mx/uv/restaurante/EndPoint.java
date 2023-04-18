package mx.uv.restaurante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.empleado.EmpleadoResponse;
import https.t4is_uv_mx.empleado.EmpleadoRequest;
import https.t4is_uv_mx.empleado.BuscarIdResponse;
import https.t4is_uv_mx.empleado.ModificarEmpleadoResponse;
import https.t4is_uv_mx.empleado.ModificarEmpleadoRequest;
import https.t4is_uv_mx.empleado.EliminarEmpleadoRequest;
import https.t4is_uv_mx.empleado.EliminarEmpleadoResponse;

@Endpoint
public class EndPoint {
    @Autowired
    private Empleados empleados;
    @PayloadRoot(localPart = "EmpleadoRequest", namespace = "https://t4is.uv.mx/empleado")
    

    @ResponsePayload
    public EmpleadoResponse Empleado(@RequestPayload EmpleadoRequest peticion){
        EmpleadoResponse response = new EmpleadoResponse();
        response.setRespuesta("Se ha registrado: " + peticion.getNombre()+" "+"Con rol: "+peticion.getRol());
        
       
        Empleado empleado = new Empleado();
        empleado.setNombre(peticion.getNombre());
        empleado.setRol(peticion.getRol());
        empleados.save(empleado);
        
        
  
        return response;
    }

    @PayloadRoot(localPart = "BuscarIdRequest" ,namespace = "https://t4is.uv.mx/empleado")
    @ResponsePayload
    public BuscarIdResponse buscarEmpleado(){
        BuscarIdResponse respuesta = new BuscarIdResponse();
        Iterable<Empleado> lista= empleados.findAll();
       
        
        for (Empleado empleado : lista) {
            BuscarIdResponse.Empleados e = new BuscarIdResponse.Empleados();
            e.setNombre(empleado.getNombre());
            
            e.setId(empleado.getId());
            e.setRol(empleado.getRol());
            respuesta.getEmpleados().add(e);
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "ModificarEmpleadoRequest" ,namespace = "https://t4is.uv.mx/empleado")
    @ResponsePayload
    public ModificarEmpleadoResponse modificarEmpleado(@RequestPayload ModificarEmpleadoRequest peticion){       
        ModificarEmpleadoResponse respuesta = new ModificarEmpleadoResponse(); 
        Empleado empleado = new Empleado();
        empleado.setId(peticion.getId());
        empleado.setNombre(peticion.getNombre());
        empleado.setRol(peticion.getRol());
        empleados.save(empleado);
        respuesta.setRespuesta("El empleado ha sido modificado");        
        return respuesta;
    }

    @PayloadRoot(localPart = "EliminarEmpleadoRequest", namespace = "https://t4is.uv.mx/empleado")
    @ResponsePayload
    public EliminarEmpleadoResponse eliminarSaludo(@RequestPayload EliminarEmpleadoRequest peticion){
        EliminarEmpleadoResponse respuesta = new EliminarEmpleadoResponse();
        empleados.deleteById(peticion.getId());
        respuesta.setRespuesta("Empleado eliminado");
        return respuesta;
    }

}