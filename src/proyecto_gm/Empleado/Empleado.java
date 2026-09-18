package proyecto_gm.Empleado;

import java.sql.Date;

public class Empleado {

    private int idEmpleado;
    private int idArea;
    private int idTipoEmpleado;
    private int idCargo;
    private String apellidos;
    private String nombres;
    private Date fechaNacimiento;
    private String sexo;
    private String correo;
    private String dni;
    private String celular;
    private String distrito;
    private String direccion;
    private String estado;
    private String anio;
    private String mes;

    public Empleado() {
    }

    public Empleado(
            int idEmpleado,
            int idArea,
            int idTipoEmpleado,
            int idCargo,
            String apellidos,
            String nombres,
            Date fechaNacimiento,
            String sexo,
            String correo,
            String dni,
            String celular,
            String distrito,
            String direccion,
            String estado,
            String anio,
            String mes) {
        this.idEmpleado = idEmpleado;
        this.idArea = idArea;
        this.idTipoEmpleado = idTipoEmpleado;
        this.idCargo = idCargo;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.correo = correo;
        this.dni = dni;
        this.celular = celular;
        this.distrito = distrito;
        this.direccion = direccion;
        this.estado = estado;
        this.anio = anio;
        this.mes = mes;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(int idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return apellidos + " " + nombres;
    }
}
