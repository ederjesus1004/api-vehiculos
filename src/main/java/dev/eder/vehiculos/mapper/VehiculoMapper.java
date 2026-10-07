package dev.eder.vehiculos.mapper;

import dev.eder.vehiculos.dto.VehiculoRequest;
import dev.eder.vehiculos.dto.VehiculoResponse;
import dev.eder.vehiculos.entity.Vehiculo;

// Sin @Component: todos los métodos son static
public final class VehiculoMapper {

     // Nadie puede hacer "new VehiculoMapper()"
    private VehiculoMapper(){
    }

    // Request → entidad NUEVA
    public static Vehiculo toEntity(VehiculoRequest request){
        Vehiculo vehiculo = new Vehiculo();
        actualizarEntidad(request, vehiculo);
        return vehiculo;
    }
    
    // Copia el request sobre una entidad que YA EXISTE
    public static void actualizarEntidad(VehiculoRequest request, Vehiculo vehiculo){
        vehiculo.setPlaca(request.placa().trim());
        vehiculo.setMarca(request.marca().trim());
        vehiculo.setModelo(request.modelo().trim());
        vehiculo.setAnio(request.anio());
        vehiculo.setColor(request.color());
        vehiculo.setPrecio(request.precio());
    }

     // Entidad → response con BUILDER

    public static VehiculoResponse toResponse(Vehiculo vehiculo){
        return VehiculoResponse.builder()
        .id(vehiculo.getId())
        .placa(vehiculo.getPlaca())
        .descripcion(vehiculo.getMarca() + " " + vehiculo.getModelo() + " " + vehiculo.getAnio())
        .color(vehiculo.getColor())
        .precio(vehiculo.getPrecio())
        .fechaRegistro(vehiculo.getFechaRegistro())
        .build();
    }

}
