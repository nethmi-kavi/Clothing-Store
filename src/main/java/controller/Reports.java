package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;

public class Reports {

    @FXML
    void btnProductDetails(ActionEvent event) {
        try {
            JasperDesign design= JRXmlLoader.load("src/main/resources/report/product_report.jrxml");
            JasperReport jasperReport= JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint,"produ_report.pdf");
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnSalesDetails(ActionEvent event) {

        try {
            JasperDesign design= JRXmlLoader.load("src/main/resources/report/sales_report.jrxml");
            JasperReport jasperReport= JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint,"sales_report.pdf");
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUserDetails(ActionEvent event) {

        try {
            JasperDesign design= JRXmlLoader.load("src/main/resources/report/user_details.jrxml");
            JasperReport jasperReport= JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint,"user_report.pdf");
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
