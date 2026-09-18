package proyecto_gm.Empleado;

import java.sql.Date;

public class EmpleadoDTO extends Empleado {

    private final String area;
    private final String cargo;
    private final String tipoEmpleado;

    public EmpleadoDTO(
            int idEmpleado,
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
            String mes,
            int idArea,
            String area,
            int idCargo,
            String cargo,
            int idTipoEmpleado,
            String tipoEmpleado) {
        super(idEmpleado, idArea, idTipoEmpleado, idCargo, apellidos, nombres,
                fechaNacimiento, sexo, correo, dni, celular, distrito,
                direccion, estado, anio, mes);
        this.area = area;
        this.cargo = cargo;
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getArea() {
        return area;
    }

    public String getCargo() {
        return cargo;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }
}
