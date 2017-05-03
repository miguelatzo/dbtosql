/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etlmc;

import static etlmc.NewDialogTB.tableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nwni
 */
public class FindModel {
    
    static final FindModel objFindM = new FindModel();
    String currentTable, columnas;
    int stringValues[] = new int[10];
    
    String[] m_empleado = {"Empleado", "Nombre", "Apellido", "RFC", "Direccion", "Colonia", "Telefono", "Fecha de Nacimiento", "Area", "Jefe de Area"};
    String[] m_doctor = {"Doctor", "Especialidad"};
    String[] m_recepcionista = {"Recepcionista"};
    String[] m_laboratorista = {"Laboratorista", "Especialidad"};
    String[] m_enfermero = {"Enfermero", "Especialidad"};
    String[] m_cuarto = {"Cuarto", "Tipo de Cuarto", "Area"};
    String[] m_paciente = {"Paciente", "Nombre", "Apellido", "Direccion", "Telefono", "Tipo de Sangre", "Cuarto"};
    String[] m_expediente = {"Expediente", "Doctor", "Paciente", "Cirugia", "Citas", "Detalles"};
    String[] m_cirugia = {"Cirugia", "Fecha", "Doctor"};
    String[] m_citas = {"Cita", "Dia", "Hora", "Consultorio", "Detalles", "Tipo de Cita", "Expediente", "Recepcionista"};
    String[] m_factura = {"Factura", "Modo de Pago", "Paciente"};
    String[] m_medicamentos = {"Medicamento", "Nombre"};
    String[] m_farmacia = {"Farmacia", "Factura", "Medicamento", "Cantidad", "Precio"};
    
        public void foundTable(int column) {
        switch (column) {
            //tablaF es el nombre de la tabla que se elimina en la funcion cleanFile. Se le asigna de esa manera el nombre por el formato que da MYSQL
            case 0:tableModel = new DefaultTableModel(m_empleado,0); FileActions.tablaF = "(empleado)";
                    columnas = "ID_Empleado, Nombre, Apellido, RFC, Direccion, Colonia, Telefono, fecha_de_nacimiento, Area, Jefe_de_Area";
                    stringValues = new int[]{0,1,1,1,1,1,1,1,1,0};
                    break;
            case 1:tableModel = new DefaultTableModel(m_doctor,0); FileActions.tablaF = "(doctor)";
                    columnas = "ID_Doctor, Especialidad";
                    stringValues = new int[]{0,1};
                    break;
            case 2:tableModel = new DefaultTableModel(m_recepcionista,0); FileActions.tablaF = "(recepcionista)";
                    columnas = "ID_Recepcionista";
                    stringValues = new int[]{0};
                    break;
            case 3:tableModel = new DefaultTableModel(m_laboratorista,0); FileActions.tablaF = "(laboratorista)";
                    columnas = "ID_Laboratorista, Especialidad";
                    stringValues = new int[]{0,1};
                    break;
            case 4:tableModel = new DefaultTableModel(m_enfermero,0); FileActions.tablaF = "(enfermero)";
                    columnas = "ID_Enfermero, Especialidad";
                    stringValues = new int[]{0,1};
                    break;
            case 5:tableModel = new DefaultTableModel(m_cuarto,0); FileActions.tablaF = "(cuarto)";
                    columnas = "ID_Cuarto, Tipo_de_cuarto, Area";
                    stringValues = new int[]{0,1,1};
                    break;
            case 6:tableModel = new DefaultTableModel(m_paciente,0); FileActions.tablaF = "(paciente)";
                    columnas = "ID_Paciente, Nombre, Apellido, Direccion, Telefono, Tipo_de_sangre, ID_Cuarto";
                    stringValues = new int[]{0,1,1,1,1,1,0};
                    break;
            case 7:tableModel = new DefaultTableModel(m_expediente,0); FileActions.tablaF = "(expediente)";
                    columnas = "ID_Expediente, ID_Doctor, ID_Paciente, Cirugia, Citas, Detalles";
                    stringValues = new int[]{0,0,0,0,0,1};
                    break;
            case 8:tableModel = new DefaultTableModel(m_cirugia,0); FileActions.tablaF = "(cirugia)";
                    columnas = "ID_Cirugia, Fecha, ID_Doctor";
                    stringValues = new int[]{0,1,0};
                    break;
            case 9:tableModel = new DefaultTableModel(m_citas,0); FileActions.tablaF = "(citas)";
                    columnas = "ID_Citas, Dia, Hora, Consultorio, Detalles, Tipo_de_cita, ID_Expediente, ID_Recepcionista";
                    stringValues = new int[]{0,1,1,1,1,1,0,0};
                    break;
            case 10:tableModel = new DefaultTableModel(m_factura,0); FileActions.tablaF = "(factura)";
                    columnas = "ID_Factura, modo_de_pago, ID_Paciente";
                    stringValues = new int[]{0,1,0};
                    break;
            case 11:tableModel = new DefaultTableModel(m_medicamentos,0); FileActions.tablaF = "(medicamentos)";
                    columnas = "ID_Medicamentos, Nombre";
                    stringValues = new int[]{0,1};
                    break;
            case 12:tableModel = new DefaultTableModel(m_farmacia,0); FileActions.tablaF = "(farmacia)";
                    columnas = "ID_Farmacia, ID_Factura, ID_Medicamento, Cantidad, Precio";
                    stringValues = new int[]{0,0,0,0,0};
                    break;
            default:break;
        }
    }
}
