package com.udea.empleado.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.udea.empleado.model.Empleado;

@Repository
public interface IEmpleadoDAO extends CrudRepository<Empleado, Long> {
}