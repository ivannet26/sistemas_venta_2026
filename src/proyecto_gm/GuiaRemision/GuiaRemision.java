package proyecto_gm.GuiaRemision;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GuiaRemision {

    // Datos internos de control
    private long idGuia;
    private int idEmpresa;
    private Integer idCliente;
    private int idUsuario;
    private long correlativo;

    // Datos visibles del formulario
    private Timestamp fechaEmision;
    private Date fechaInicioTraslado;
    private int idMotivo;

    private String direccionOrigen;
    private String direccionDestino;

    private String destinatarioTipoDoc;
    private String destinatarioNumeroDoc;
    private String destinatarioNombre;
    private String destinatarioDireccion;

    private String documentoTransportista;
    private String licenciaConducir;
    private String vehiculoMarcaPlaca;

    // Datos informativos
    private String estado;
    private String codigoMotivo;
    private String motivoDescripcion;

    private Timestamp fechaCreacion;
    private Timestamp fechaActualizacion;

    private List<Detalle> detalles = new ArrayList<>();

    public GuiaRemision() {
    }

    public long getIdGuia() {
        return idGuia;
    }

    public void setIdGuia(long idGuia) {
        this.idGuia = idGuia;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(long correlativo) {
        this.correlativo = correlativo;
    }

    public Timestamp getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Timestamp fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaInicioTraslado() {
        return fechaInicioTraslado;
    }

    public void setFechaInicioTraslado(Date fechaInicioTraslado) {
        this.fechaInicioTraslado = fechaInicioTraslado;
    }

    public int getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(int idMotivo) {
        this.idMotivo = idMotivo;
    }

    public String getDireccionOrigen() {
        return direccionOrigen;
    }

    public void setDireccionOrigen(String direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public String getDestinatarioTipoDoc() {
        return destinatarioTipoDoc;
    }

    public void setDestinatarioTipoDoc(String destinatarioTipoDoc) {
        this.destinatarioTipoDoc = destinatarioTipoDoc;
    }

    public String getDestinatarioNumeroDoc() {
        return destinatarioNumeroDoc;
    }

    public void setDestinatarioNumeroDoc(String destinatarioNumeroDoc) {
        this.destinatarioNumeroDoc = destinatarioNumeroDoc;
    }

    public String getDestinatarioNombre() {
        return destinatarioNombre;
    }

    public void setDestinatarioNombre(String destinatarioNombre) {
        this.destinatarioNombre = destinatarioNombre;
    }

    public String getDestinatarioDireccion() {
        return destinatarioDireccion;
    }

    public void setDestinatarioDireccion(String destinatarioDireccion) {
        this.destinatarioDireccion = destinatarioDireccion;
    }

    public String getDocumentoTransportista() {
        return documentoTransportista;
    }

    public void setDocumentoTransportista(
            String documentoTransportista) {

        this.documentoTransportista = documentoTransportista;
    }

    public String getLicenciaConducir() {
        return licenciaConducir;
    }

    public void setLicenciaConducir(String licenciaConducir) {
        this.licenciaConducir = licenciaConducir;
    }

    public String getVehiculoMarcaPlaca() {
        return vehiculoMarcaPlaca;
    }

    public void setVehiculoMarcaPlaca(
            String vehiculoMarcaPlaca) {

        this.vehiculoMarcaPlaca = vehiculoMarcaPlaca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoMotivo() {
        return codigoMotivo;
    }

    public void setCodigoMotivo(String codigoMotivo) {
        this.codigoMotivo = codigoMotivo;
    }

    public String getMotivoDescripcion() {
        return motivoDescripcion;
    }

    public void setMotivoDescripcion(
            String motivoDescripcion) {

        this.motivoDescripcion = motivoDescripcion;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(
            Timestamp fechaCreacion) {

        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(
            Timestamp fechaActualizacion) {

        this.fechaActualizacion = fechaActualizacion;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles != null
                ? detalles
                : new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.valueOf(idGuia);
    }

    public static class Detalle {

        private long idDetalleGuia;
        private long idGuia;
        private int numeroItem;
        private int idArticulo;

        private Integer idCategoria;

        private String codigoArticulo;
        private String descripcion;
        private String categoriaDescripcion;

        /*
         * Esta cantidad corresponde exclusivamente a la cantidad
         * mostrada en la guía. No representa ni modifica inventario.
         */
        private BigDecimal cantidad = BigDecimal.ZERO;

        public long getIdDetalleGuia() {
            return idDetalleGuia;
        }

        public void setIdDetalleGuia(long idDetalleGuia) {
            this.idDetalleGuia = idDetalleGuia;
        }

        public long getIdGuia() {
            return idGuia;
        }

        public void setIdGuia(long idGuia) {
            this.idGuia = idGuia;
        }

        public int getNumeroItem() {
            return numeroItem;
        }

        public void setNumeroItem(int numeroItem) {
            this.numeroItem = numeroItem;
        }

        public int getIdArticulo() {
            return idArticulo;
        }

        public void setIdArticulo(int idArticulo) {
            this.idArticulo = idArticulo;
        }

        public Integer getIdCategoria() {
            return idCategoria;
        }

        public void setIdCategoria(Integer idCategoria) {
            this.idCategoria = idCategoria;
        }

        public String getCodigoArticulo() {
            return codigoArticulo;
        }

        public void setCodigoArticulo(
                String codigoArticulo) {

            this.codigoArticulo = codigoArticulo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getCategoriaDescripcion() {
            return categoriaDescripcion;
        }

        public void setCategoriaDescripcion(
                String categoriaDescripcion) {

            this.categoriaDescripcion =
                    categoriaDescripcion;
        }

        public BigDecimal getCantidad() {
            return cantidad;
        }

        public void setCantidad(BigDecimal cantidad) {
            this.cantidad = cantidad;
        }
    }

    public static class Categoria {

        private int idCategoria;
        private String descripcion;

        public Categoria() {
        }

        public Categoria(
                int idCategoria,
                String descripcion) {

            this.idCategoria = idCategoria;
            this.descripcion = descripcion;
        }

        public int getIdCategoria() {
            return idCategoria;
        }

        public void setIdCategoria(int idCategoria) {
            this.idCategoria = idCategoria;
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
}