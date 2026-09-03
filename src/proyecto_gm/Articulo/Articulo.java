package proyecto_gm.Articulo;

import proyecto_gm.Categoria.Categoria;

public class Articulo {
    private int id;
    private String descripcion;
    private String caracteristicas;
    private double cantidad;
    
    // Objetos para las relaciones (Categoría y Marca)
    private Categoria categoria;
    private Marca marca;

    public Articulo() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getCaracteristicas() { return caracteristicas; }
    public void setCaracteristicas(String caracteristicas) { this.caracteristicas = caracteristicas; }

    public double getCantidad() { return cantidad; }
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }
}