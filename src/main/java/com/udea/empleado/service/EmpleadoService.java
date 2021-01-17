package com.udea.empleado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udea.empleado.dao.IEmpleadoDAO;
import java.util.Optional;
import com.udea.empleado.model.Empleado;

@Service
public class EmpleadoService {

    @Autowired
    private IEmpleadoDAO dao;

    public Empleado save(Empleado t) {
        return dao.save(t);
    }

    public Empleado update(Empleado t) {
        return dao.save(t);
    }

    public void delete(Empleado t) {
        dao.delete(t);
    }

    public Long deleteById(Long id){
        dao.deleteById(id);
        return id;
    }

    public Iterable<Empleado> list() {
        return dao.findAll();
    }

    public Optional<Empleado> listId(long id) {
        return dao.findById(id);
    }
}