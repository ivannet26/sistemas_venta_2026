package proyecto_gm.TipoEmpleado;

public class TipoEmpleado {

    private int idTipoEmpleado;
    private String descripcion;

    public TipoEmpleado() {
    }

    public TipoEmpleado(int idTipoEmpleado, String descripcion) {
        this.idTipoEmpleado = idTipoEmpleado;
        this.descripcion = descripcion;
    }

    public int getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(int idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
