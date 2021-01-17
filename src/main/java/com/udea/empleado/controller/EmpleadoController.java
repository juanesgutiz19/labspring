/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.empleado.controller;

import com.udea.empleado.exception.ModelNotFoundException;
import com.udea.empleado.model.Empleado;
import com.udea.empleado.service.EmpleadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Optional;
import java.lang.Long;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

@RestController
@RequestMapping("/empleado")
@CrossOrigin("*")
@Api(value = "Employee Management System", description = "Operations pertaining to employee in Employee Management System")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @ApiOperation(value = "Add a employee")
    @PostMapping("/save")
    public long save(@ApiParam(value = "Employee object store in database table", required = true) @RequestBody Empleado empleado) {
        empleadoService.save(empleado);
        return empleado.getIdEmployee();
    }

    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list")
        ,
    @ApiResponse(code = 401, message = "You are not authorized to view the resource")
        ,
    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
        ,
    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/listAll")
    public Iterable<Empleado> listAllEmployees() {
        return empleadoService.list();
    }

    @ApiOperation(value = "Get a employee by Id")
    @GetMapping("/list/{id}")
    public Empleado listEmpleoyeeById(@ApiParam(value = "Employee id from which employee object will retrieve", required = true) @PathVariable("id") int id) {
        Optional<Empleado> empleado = empleadoService.listId(id);
        if (empleado.isPresent()) {
            return empleado.get();
        }
        throw new ModelNotFoundException("ID de empleado invalido");
    }

    @ApiOperation(value = "Increase 10% the salary if the employee has been working for two years")
    @GetMapping("/increaseSalary/{id}")
    public Empleado increaseSalary(@ApiParam(value = "Employee id", required = true) @PathVariable("id") int id) {
        Optional<Empleado> empleado = empleadoService.listId(id);
        if (empleado.isPresent()) {
            Empleado e = empleado.get();
            Date startingDate = e.getStartingDate();
            Date finalDate = new Date();
            int years = obtenerAnios(startingDate, finalDate);
            if(years >= 2 && e.getFlagTwoYears() == 0){
                double salary = e.getSalary();
                salary = salary*1.1;
                e.setSalary(salary);
                e.setFlagTwoYears(1);
                empleadoService.update(e);
            }
            return e;
        }
        throw new ModelNotFoundException("ID de empleado invalido");

    }

    @ApiOperation(value = "Delete an employee by Id")
    @DeleteMapping("/delete/{id}")
    public Long deleteEmpleoyeeById(@ApiParam(value = "Employee id from which employee object will be removed", required = true) @PathVariable("id") int id) {
        Optional<Empleado> empleado = empleadoService.listId(id);
        if (empleado.isPresent()) {
            Long newId = Long.valueOf(id);
            return empleadoService.deleteById(newId);
        }
        throw new ModelNotFoundException("ID de empleado invalido");
    }

    @ApiOperation(value = "Update an employee")
    @PostMapping("/update")
    public Long UpdateEmpleoyee(@ApiParam(value = "Employee which is going to be updated", required = true) @RequestBody Empleado empleado) {
        Long id = empleado.getIdEmployee();
        Optional<Empleado> empleado1 = empleadoService.listId(id);
        if (empleado1.isPresent()) {
            empleadoService.update(empleado);
            return empleado.getIdEmployee();
        }
        throw new ModelNotFoundException("ID de empleado invalido");
    }

    public static int obtenerAnios(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
            (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) &&   
            a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

}
