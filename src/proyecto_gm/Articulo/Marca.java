package proyecto_gm.Articulo;

import java.util.Objects;

public class Marca {
    private int idMarca;
    private String descripcion;
    
    public Marca() {}
    
    public Marca(int idMarca, String descripcion) {
        this.idMarca = idMarca;
        this.descripcion = descripcion;
    }
    
    // Getters y Setters
    public int getIdMarca() { return idMarca; }
    public void setIdMarca(int idMarca) { this.idMarca = idMarca; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    // Para que se muestre correctamente en el JComboBox
    @Override
    public String toString() {
        return descripcion;
    }
    
    // Esenciales para que la selección en el ComboBox funcione correctamente
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marca marca = (Marca) o;
        return idMarca == marca.idMarca;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMarca);
    }
}