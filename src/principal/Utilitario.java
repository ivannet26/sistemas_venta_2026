/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc_sistemas2022
 */
public class Utilitario {

    public static void mostrarMensaje(String seleccione_un_registro_para_editar, TipoMensaje tipoMensaje) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static boolean mostrarMensajePregunta(String está_seguro_de_que_desea_eliminar_este_ca) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public static enum EstadoProceso{
        NUEVO,
        EDITAR,
        ELIMINAR,
        CONSULTAR
    }
    public static enum TipoMensaje{
        alerta,
        informativo,
        error,
        pregunta
    }
    public static void MostrarMensaje(String mensaje, TipoMensaje tipo  ){
        if(null != tipo)switch (tipo) {
            case informativo:
                JOptionPane.showMessageDialog(null, mensaje, "Sistema", JOptionPane.INFORMATION_MESSAGE);
                break;
            case alerta:
                JOptionPane.showMessageDialog(null, mensaje, "Sistema", JOptionPane.WARNING_MESSAGE);
                break;
            case error:
                JOptionPane.showMessageDialog(null, mensaje, "Sistema", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                
                break;
        }
        
    }
    
    public static boolean MostrarMensajePregunta(String mensaje, TipoMensaje tipo){
        Locale.setDefault(new Locale("es", "ES"));
        String[] botones = new String[2] ;
        botones[0]= "SI";
        botones[1] = "NO";
        boolean estado = false;
        int seleccion = JOptionPane.showOptionDialog(null, 
                mensaje, "sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
                null, botones, botones[0]);

        //yesy => 0, No => 1
        if(seleccion == JOptionPane.YES_OPTION){
            estado =  true;
        }else if(seleccion == JOptionPane.NO_OPTION){
                estado = false;
        }

        return estado;
        
        
    }
    
    static String TraerFechaActual(){
        String resultado = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
        LocalDateTime fechaActual = LocalDateTime.now();
        
        String sFecha = fechaActual.format(dtf);
        resultado = sFecha;
        return resultado;
    }
    public static String TraerFechaInicial(){
        String resultado = "";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
        LocalDateTime fechaActual = LocalDateTime.now();
        LocalDateTime fechaInicio = LocalDateTime.of(2023, 3, 1,0,0);
        resultado   = fechaInicio.format(dtf);
        return resultado;
    }
    
    protected static String LeerValor(JTable tabla, String[] nombresColumnas, String nombreBusqueda){
        String indiceColumn = nombresColumnas[0];
        
        //codigo
        //descripcion
        //categoaia
        //nombres
        //apellidos
        //recorrer las columnas
        String dato = "";
        for (int i = 0; i < nombresColumnas.length; i++) {
            if(nombresColumnas[i].equals(nombreBusqueda) ){
                dato =  tabla.getValueAt(tabla.getSelectedRow(), i).toString();
                return dato;
            }
        }
        //tabla.getValueAt(tabla.getSelectedRow(), 0)
        //DefaultTableModel modelo  =  (DefaultTableModel) tabla.getModel();
        return "";
    }
    
    public static String LeerValortexto(JTable tabla, int posicionColumna){
         return tabla.getValueAt(tabla.getSelectedRow(), posicionColumna).toString();
    }
    public static Object LeerValor(JTable tabla, int posicionColumna){
         return tabla.getValueAt(tabla.getSelectedRow(), posicionColumna);
    }
    public static String LeerValortexto(DefaultTableModel modelo, int fila,int columna){
        if (modelo.getValueAt(fila, columna) == null){
            return "";
        }
        return modelo.getValueAt(fila, columna).toString();
    }
//    static void MostrarMensajeInformacion(String mensaje, String titulo){
//    
//    }
    
    
}
