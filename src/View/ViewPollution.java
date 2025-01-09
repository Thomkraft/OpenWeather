/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.DbManager;
import java.awt.Dimension;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author apeyt
 */
public class ViewPollution extends JPanel{
    
    ChartPanel chartPanel;
    private final DbManager DbManager;
    
    public ViewPollution(DbManager data) {
        this.DbManager = data;
        initChart( ); 
        
        validate();
        repaint();
   }

    private void initChart() {
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Pollution chart",
                "Date",
                "Air Quality Indice",
                createDataset(),
                PlotOrientation.VERTICAL,
                false, true, false);
        
        chartPanel = new ChartPanel( lineChart );
        
        chartPanel.setPreferredSize(new Dimension(1500, 600)); // Taille du graphique
        
        this.add( chartPanel );
        
    }

    @Override
    public Dimension getPreferredSize() {
        
        Dimension dim = this.size();
        chartPanel.setPreferredSize(new Dimension(800, 600));
        return new Dimension(800, 600);
    }
   
    private CategoryDataset createDataset( ) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  
        
        String query = "SELECT dt,aqi FROM pollution";

        try(ResultSet result = DbManager.getCon().createStatement().executeQuery(query)) {
            while(result.next()){
                int dt = result.getInt("dt");
                int aqi = result.getInt("aqi");

                Date date = new Date((long) dt);

                String pattern = "DD:MM:yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String dateString = simpleDateFormat.format(date);

                dataset.addValue(aqi, "AQI", dateString);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        
        return dataset; 
    }

}
