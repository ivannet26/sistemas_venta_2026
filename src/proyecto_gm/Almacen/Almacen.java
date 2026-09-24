package proyecto_gm.Almacen;

public class Almacen {

    private int IdAlmacen;
    private String descripcion;

    public Almacen() {
    }

    public Almacen(int IdAlmacen, String descripcion) {
        this.IdAlmacen = IdAlmacen;
        this.descripcion = descripcion;
    }

    public int getIdAlmacen() {
        return IdAlmacen;
    }

    public void setIdAlmacen(int IdAlmacen) {
        this.IdAlmacen = IdAlmacen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
