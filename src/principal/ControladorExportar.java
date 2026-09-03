package principal;

import java.io.File;
import java.io.FileOutputStream;
import java.awt.Desktop; 
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ControladorExportar {

    public static void exportarTablaExcel(JTable tabla) {
        if (tabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay datos para exportar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar Reporte Excel");
        chooser.setFileFilter(new FileNameExtensionFilter("Libro de Excel (*.xlsx)", "xlsx"));

        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().getAbsolutePath();
            if (!ruta.toLowerCase().endsWith(".xlsx")) {
                ruta += ".xlsx";
            }

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Datos");

         
                Font headerFont = workbook.createFont();
                headerFont.setBold(true); 
                headerFont.setColor(IndexedColors.WHITE.getIndex());

                CellStyle headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setFont(headerFont);
                headerCellStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
                
            
                headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerCellStyle.setAlignment(HorizontalAlignment.CENTER); 

                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < tabla.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(tabla.getColumnName(i));
                    cell.setCellStyle(headerCellStyle);
                }

                for (int f = 0; f < tabla.getRowCount(); f++) {
                    Row row = sheet.createRow(f + 1);
                    for (int c = 0; c < tabla.getColumnCount(); c++) {
                        Object valor = tabla.getValueAt(f, c);
                        if (valor != null) {
                            row.createCell(c).setCellValue(valor.toString());
                        }
                    }
                }

                for (int i = 0; i < tabla.getColumnCount(); i++) {
                    sheet.autoSizeColumn(i);
                }

                try (FileOutputStream out = new FileOutputStream(new File(ruta))) {
                    workbook.write(out);
                    JOptionPane.showMessageDialog(null, "Reporte exportado exitosamente.");
                    
                    
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(new File(ruta));
                    }
                }

            } catch (NoClassDefFoundError e) {
                JOptionPane.showMessageDialog(null, "Error: Librería faltante o desactualizada.", "Error Crítico", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al exportar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}