package proyecto_gm.Cliente;

public class Cliente {

    private int IdCliente;
    private String NombreEmpresa;
    private String proyecto;
    private String TipoCliente;

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getNombreEmpresa() {
        return NombreEmpresa;
    }

    public void setNombreEmpresa(String NombreEmpresa) {
        this.NombreEmpresa = NombreEmpresa;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getTipoCliente() {
        return TipoCliente;
    }

    public void setTipoCliente(String TipoCliente) {
        this.TipoCliente = TipoCliente;
    }

    public Cliente(int IdCliente, String NombreEmpresa, String proyecto, String TipoCliente) {
        this.IdCliente = IdCliente;
        this.NombreEmpresa = NombreEmpresa;
        this.proyecto = proyecto;
        this.TipoCliente = TipoCliente;
    }

    public Cliente() {
    }

}
