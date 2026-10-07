package dev.eder.vehiculos.service;

import java.util.List;

import dev.eder.vehiculos.dto.VehiculoRequest;
import dev.eder.vehiculos.dto.VehiculoResponse;

public interface VehiculoService {

    List<VehiculoResponse> listar();

    VehiculoResponse obtenerPorId(Long id);

    VehiculoResponse crear(VehiculoRequest request);

    VehiculoResponse actualizar(Long id, VehiculoRequest request);

    void eliminar(Long id);

}
