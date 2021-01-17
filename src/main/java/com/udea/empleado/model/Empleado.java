package com.udea.empleado.model;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(description = "Devuelve todos los detalles del empleado")
@ToString
@Entity
public class Empleado implements Serializable {

    @ApiModelProperty(notes = "La BD genera el ID del empleado")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idemployee")
    private Long idEmployee;
    
    @ApiModelProperty(notes = "El nombre del empleado")
    @Column(name = "firstname", nullable = false, length = 80)
    private @NonNull
    String firstName;
    
    @ApiModelProperty(notes = "El apellido del empleado")
    @Column(name = "lastname", nullable = false, length = 80)
    private @NonNull
    String lastName;
    
    @ApiModelProperty(notes = "El email del empleado")
    @Column(name = "email", nullable = false, length = 80)
    private @NonNull
    String email;
    
    @ApiModelProperty(notes = "El teléfono del empleado")
    @Column(name = "phonenumber", nullable = false, length = 80)
    private @NonNull
    String phoneNumber;
    
    @ApiModelProperty(notes = "El nivel educacional del empleado")
    @Column(name = "educationallevel", nullable = false, length = 80)
    private @NonNull
    String educationalLevel;
    
    @ApiModelProperty(notes = "El salario del empleado")
    @Column(name = "salary", nullable = false, length = 80)
    private @NonNull
    double salary;
    
    @ApiModelProperty(notes = "El cargo del empleado")
    @Column(name = "role", nullable = false, length = 80)
    private @NonNull
    String role;
    
    @ApiModelProperty(notes = "La dirección del empleado")
    @Column(name = "address", nullable = false, length = 80)
    private @NonNull
    String address;
    
    @ApiModelProperty(notes = "La oficina del empleado")
    @Column(name = "office", nullable = false, length = 80)
    private @NonNull
    String office;
    
    @ApiModelProperty(notes = "La dependencia del empleado")
    @Column(name = "dependency", nullable = false, length = 80)
    private @NonNull
    String dependency;
    
    @ApiModelProperty(notes = "1 si el empleado cumple dos años en la empresa, 0 de lo contrario")
    @Column(name = "flagtwoyears", nullable = false, length = 80)
    private @NonNull
    int flagTwoYears;

    @ApiModelProperty(notes = "La fecha de ingreso del empleado")
    @Column(name = "startingdate", nullable = false, length = 80)
    @Temporal(TemporalType.DATE)
    private @NonNull
    Date startingDate;
    
}
