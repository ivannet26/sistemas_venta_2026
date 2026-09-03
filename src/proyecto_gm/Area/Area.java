package proyecto_gm.Area;

public class Area {
    //Atributos
    int idArea;  
    String descripcionArea;

    public Area() {   
    }
     
    public Area(int idArea, String descripcionArea) {
        this.idArea = idArea;
        this.descripcionArea = descripcionArea;
    }
    
    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
    
    public String getDescripcionArea() {
        return descripcionArea;
    }

    public void setDescripcionArea(String descripcionArea) {
        this.descripcionArea = descripcionArea;
    }
    
    @Override
    public String toString() {
        return descripcionArea; 
    }
}
