package dev.eder.vehiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.eder.vehiculos.entity.Vehiculo;

public interface VehiculoRespository extends JpaRepository<Vehiculo, Long>{

    // ¿Algún vehículo tiene esta placa? → al CREAR
    boolean existsByPlaca(String placa);

    // ¿OTRO vehículo tiene esta placa? → al EDITAR
    boolean existsByPlacaAndIdNot(String placa, Long id);

}
