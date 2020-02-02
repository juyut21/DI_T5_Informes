/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appinformes;

import static appinformes.AppInformes.conexion;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author usuario
 */
public class AppinformesController implements Initializable
{

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void onFacturas(ActionEvent event)
    {
        try
        {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("Factura.jasper"));
            Map parametros = new HashMap();
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp, false);

        } catch (JRException ex)
        {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void onVentasTotales(ActionEvent event)
    {
        try
        {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("Ventas_total.jasper"));
            Map parametros = new HashMap();
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp, false);

        } catch (JRException ex)
        {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void onFacturasCliente(ActionEvent event)
    {
        Stage stage = new Stage();
        TextField tituloIntro = new TextField("");
        Button btn = new Button();
        btn.setText("Informe");

        VBox root = new VBox();
        root.getChildren().addAll(tituloIntro, btn);

        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("Facturas_por_cliente.jasper"));
                    Map parametros = new HashMap();
                    int nproducto = Integer.valueOf(tituloIntro.getText());
                    parametros.put("Adressid", nproducto);
                    JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
                    JasperViewer.viewReport(jp, false);
                } catch (JRException ex)
                {
                    System.out.println("Error al recuperar el jasper");
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });

        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void onSubinforme(ActionEvent event)
    {
        try
        {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("SubinformeFactura.jasper"));
            JasperReport jr2 = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("SubinformeFactura_1.jasper"));
            Map parametros = new HashMap();
            parametros.put("subReportParameter", jr2);
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex)
        {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }

    }

}
