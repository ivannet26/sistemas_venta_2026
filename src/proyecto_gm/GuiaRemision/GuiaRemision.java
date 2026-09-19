/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// GuiaRemision.java
package proyecto_gm.GuiaRemision;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GuiaRemision {

    private long idGuia;
    private int idEmpresa;
    private Long idComprobante;
    private int idCliente;
    private int idUsuario;
    private int idAlmacen;
    private int idSerieGuia;
    private long correlativo;
    private Timestamp fechaEmision;
    private Date fechaInicioTraslado;
    private int idMotivo;
    private String modalidadTraslado;
    private String direccionOrigen;
    private String idUbigeoOrigen;
    private String direccionDestino;
    private String idUbigeoDestino;
    private String destinatarioTipoDoc;
    private String destinatarioNumeroDoc;
    private String destinatarioNombre;
    private Integer idTransportista;
    private Integer idConductor;
    private Integer idVehiculo;
    private BigDecimal pesoTotal = BigDecimal.ZERO;
    private String unidadPeso = "KGM";
    private int numeroBultos;
    private String estado;
    private Timestamp fechaDespacho;
    private Timestamp fechaEntrega;
    private String observaciones;
    private Integer idUsuarioAnulacion;
    private Timestamp fechaAnulacion;
    private Timestamp fechaCreacion;
    private Timestamp fechaActualizacion;

    private String serie;
    private String codigoMotivo;
    private String motivoDescripcion;
    private String almacen;

    private List<Detalle> detalles = new ArrayList<>();
    private List<ConformidadEntrega> conformidades = new ArrayList<>();

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

    public Long getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Long idComprobante) {
        this.idComprobante = idComprobante;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public int getIdSerieGuia() {
        return idSerieGuia;
    }

    public void setIdSerieGuia(int idSerieGuia) {
        this.idSerieGuia = idSerieGuia;
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

    public String getModalidadTraslado() {
        return modalidadTraslado;
    }

    public void setModalidadTraslado(String modalidadTraslado) {
        this.modalidadTraslado = modalidadTraslado;
    }

    public String getDireccionOrigen() {
        return direccionOrigen;
    }

    public void setDireccionOrigen(String direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }

    public String getIdUbigeoOrigen() {
        return idUbigeoOrigen;
    }

    public void setIdUbigeoOrigen(String idUbigeoOrigen) {
        this.idUbigeoOrigen = idUbigeoOrigen;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public String getIdUbigeoDestino() {
        return idUbigeoDestino;
    }

    public void setIdUbigeoDestino(String idUbigeoDestino) {
        this.idUbigeoDestino = idUbigeoDestino;
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

    public Integer getIdTransportista() {
        return idTransportista;
    }

    public void setIdTransportista(Integer idTransportista) {
        this.idTransportista = idTransportista;
    }

    public Integer getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(Integer idConductor) {
        this.idConductor = idConductor;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public BigDecimal getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(BigDecimal pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public String getUnidadPeso() {
        return unidadPeso;
    }

    public void setUnidadPeso(String unidadPeso) {
        this.unidadPeso = unidadPeso;
    }

    public int getNumeroBultos() {
        return numeroBultos;
    }

    public void setNumeroBultos(int numeroBultos) {
        this.numeroBultos = numeroBultos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(Timestamp fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public Timestamp getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Timestamp fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdUsuarioAnulacion() {
        return idUsuarioAnulacion;
    }

    public void setIdUsuarioAnulacion(Integer idUsuarioAnulacion) {
        this.idUsuarioAnulacion = idUsuarioAnulacion;
    }

    public Timestamp getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Timestamp fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
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

    public void setMotivoDescripcion(String motivoDescripcion) {
        this.motivoDescripcion = motivoDescripcion;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles != null ? detalles : new ArrayList<>();
    }

    public List<ConformidadEntrega> getConformidades() {
        return conformidades;
    }

    public void setConformidades(List<ConformidadEntrega> conformidades) {
        this.conformidades = conformidades != null
                ? conformidades : new ArrayList<>();
    }

    @Override
    public String toString() {
        if (serie == null || serie.trim().isEmpty()) {
            return String.valueOf(idGuia);
        }
        return serie + "-" + correlativo;
    }

    public static class Detalle {

        private long idDetalleGuia;
        private long idGuia;
        private int numeroItem;
        private int idArticulo;
        private Long idDetalleVenta;
        private String codigoArticulo;
        private String descripcion;
        private Integer idUnidadMedida;
        private String unidadMedida;
        private BigDecimal cantidad = BigDecimal.ZERO;
        private BigDecimal pesoUnitario = BigDecimal.ZERO;
        private BigDecimal pesoTotal = BigDecimal.ZERO;

        public Detalle() {
        }

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

        public Long getIdDetalleVenta() {
            return idDetalleVenta;
        }

        public void setIdDetalleVenta(Long idDetalleVenta) {
            this.idDetalleVenta = idDetalleVenta;
        }

        public String getCodigoArticulo() {
            return codigoArticulo;
        }

        public void setCodigoArticulo(String codigoArticulo) {
            this.codigoArticulo = codigoArticulo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public Integer getIdUnidadMedida() {
            return idUnidadMedida;
        }

        public void setIdUnidadMedida(Integer idUnidadMedida) {
            this.idUnidadMedida = idUnidadMedida;
        }

        public String getUnidadMedida() {
            return unidadMedida;
        }

        public void setUnidadMedida(String unidadMedida) {
            this.unidadMedida = unidadMedida;
        }

        public BigDecimal getCantidad() {
            return cantidad;
        }

        public void setCantidad(BigDecimal cantidad) {
            this.cantidad = cantidad;
        }

        public BigDecimal getPesoUnitario() {
            return pesoUnitario;
        }

        public void setPesoUnitario(BigDecimal pesoUnitario) {
            this.pesoUnitario = pesoUnitario;
        }

        public BigDecimal getPesoTotal() {
            return pesoTotal;
        }

        public void setPesoTotal(BigDecimal pesoTotal) {
            this.pesoTotal = pesoTotal;
        }
    }

    public static class ConformidadEntrega {

        private long idConformidad;
        private long idGuia;
        private int idUsuario;
        private Timestamp fechaEntrega;
        private String receptorNombre;
        private String receptorDocumento;
        private String observaciones;

        public ConformidadEntrega() {
        }

        public long getIdConformidad() {
            return idConformidad;
        }

        public void setIdConformidad(long idConformidad) {
            this.idConformidad = idConformidad;
        }

        public long getIdGuia() {
            return idGuia;
        }

        public void setIdGuia(long idGuia) {
            this.idGuia = idGuia;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public Timestamp getFechaEntrega() {
            return fechaEntrega;
        }

        public void setFechaEntrega(Timestamp fechaEntrega) {
            this.fechaEntrega = fechaEntrega;
        }

        public String getReceptorNombre() {
            return receptorNombre;
        }

        public void setReceptorNombre(String receptorNombre) {
            this.receptorNombre = receptorNombre;
        }

        public String getReceptorDocumento() {
            return receptorDocumento;
        }

        public void setReceptorDocumento(String receptorDocumento) {
            this.receptorDocumento = receptorDocumento;
        }

        public String getObservaciones() {
            return observaciones;
        }

        public void setObservaciones(String observaciones) {
            this.observaciones = observaciones;
        }
    }
}