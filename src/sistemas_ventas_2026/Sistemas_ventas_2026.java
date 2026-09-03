/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemas_ventas_2026;

import principal.inicio;

/**
 *
 * @author sistemas
 */
public class Sistemas_ventas_2026 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try
        {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            principal.inicio frm = new inicio();
            frm.setVisible(true);
        }catch( Exception e)
        {
            System.err.println("No se pudo establecer el estilo visual: " + e.getMessage());
        }
        
    }
    
}
