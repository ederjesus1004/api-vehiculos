package dev.eder.vehiculos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.eder.vehiculos.dto.VehiculoRequest;
import dev.eder.vehiculos.dto.VehiculoResponse;
import dev.eder.vehiculos.entity.Vehiculo;
import dev.eder.vehiculos.exception.RecursoDuplicadoException;
import dev.eder.vehiculos.exception.RecursoNoEncontradoException;
import dev.eder.vehiculos.mapper.VehiculoMapper;
import dev.eder.vehiculos.repository.VehiculoRespository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService{

    // Una sola dependencia: el mapper es static

    private final VehiculoRespository vehiculoRespository;

    @Override
    @Transactional(readOnly = true)
    public List<VehiculoResponse> listar(){
        return vehiculoRespository.findAll()
        .stream()
        .map(VehiculoMapper::toResponse)
        .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VehiculoResponse obtenerPorId(Long id){
    return VehiculoMapper.toResponse(buscarVehiculo(id));
    }
    
    @Override
    @Transactional
    public VehiculoResponse crear(VehiculoRequest request){
        // Regla: la placa no puede existir
        if (vehiculoRespository.existsByPlaca(request.placa())) {
            throw new RecursoDuplicadoException("Ya existe un vehiculo con la placa" + request.placa());
        }
        Vehiculo vehiculo = VehiculoMapper.toEntity(request);
        return VehiculoMapper.toResponse(vehiculoRespository.save(vehiculo));
    }

    @Override
    @Transactional
    public VehiculoResponse actualizar(Long id, VehiculoRequest request){
        Vehiculo vehiculo = buscarVehiculo(id); // 404 si no existe

        // Regla: la placa no puede ser de OTRO vehículo
        if (vehiculoRespository.existsByPlacaAndIdNot(request.placa(), id)) {
            throw new RecursoDuplicadoException("Ya existe un vehiculo con la placa" + request.placa());
        }
        VehiculoMapper.actualizarEntidad(request, vehiculo);
        return VehiculoMapper.toResponse(vehiculoRespository.save(vehiculo));
    }

    @Override
    @Transactional
    public void eliminar(Long id){
        Vehiculo vehiculo = buscarVehiculo(id); // 404 si no existe
        vehiculoRespository.delete(vehiculo);
    }

     // Busca o lanza 404. Lo usan obtener, actualizar y eliminar.
    private Vehiculo buscarVehiculo(Long id){
        return vehiculoRespository.findById(id)
        .orElseThrow(()-> new RecursoNoEncontradoException("No existe el vehiculo con id" + id));
    }

}
