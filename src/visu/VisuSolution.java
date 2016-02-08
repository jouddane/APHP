package visu;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.IntervalXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.chart.renderer.category.LayeredBarRenderer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.GanttCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.data.time.TimePeriod;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.PaintList;

import com.orsoncharts.marker.MarkerLine;

public class VisuSolution extends ApplicationFrame{

	public VisuSolution(String title){
		super(title);
		
		GanttCategoryDataset dataset = createDataset( new Donnees());
        JFreeChart chart = createChart(dataset);

        // add the chart to a panel...
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1200, 800));
        setContentPane(chartPanel);


	}
	
	public GanttCategoryDataset createDataset(Donnees donnees) {
		int Annee = Calendar.getInstance().get(Calendar.YEAR);
		int Mois =  Calendar.getInstance().get(Calendar.MONTH);
		int Jour = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		TaskSeriesCollection collection = new TaskSeriesCollection();
		TaskSeries P = new TaskSeries("Tous les parcours");
		for(int i=0; i<donnees.nbPatients();i++){
			String s ="Parcours";
			Task S = new Task("Parcours"+i, new Date(Annee, Mois, Jour, donnees.getDebutH(i,0),donnees.getDebutM(i, 0)), new Date(Annee, Mois, Jour,donnees.getDebutH(i, donnees.nbSoins()-1),donnees.getDebutM(i, donnees.nbSoins()-1)));
			for (int j=0; j<donnees.nbSoins();j++){
				if(donnees.getSoin(i, j).getDuree()==15){
					
				}
				else 
				if (j<donnees.nbSoins()-1){
					S.addSubtask(new Task("Soin "+j, new Date(Annee, Mois, Jour, donnees.getDebutH(i, j), donnees.getDebutM(i, j)), new Date(Annee, Mois, Jour,donnees.getDebutH(i, j+1),donnees.getDebutM(i, j+1))));
				}
				else {
					S.addSubtask(new Task("Soin "+j, new Date(Annee, Mois, Jour, donnees.getDebutH(i, j), donnees.getDebutM(i, j)), new Date(Annee, Mois, Jour,donnees.getDebutH(i, j)+donnees.getSoin(i, j).getDuree()/60,donnees.getDebutM(i, j)+donnees.getSoin(i, j).getDuree()%60)));
				}
			}
			
			
			P.add(S);
		}
		
		
        collection.add(P);

        return collection;
    }
	
	 public JFreeChart createChart(GanttCategoryDataset dataset) {
	        JFreeChart chart = ChartFactory.createGanttChart(
	                "Test ", // chart title
	                "Parcours", // domain axis label
	                "Heure", // range axis label
	                dataset, // data
	                true, // include legend
	                true, // tooltips
	                false // urls
	        );
	       
	        CategoryPlot plot = (CategoryPlot) chart.getPlot();
	        GanttRenderer gr = (GanttRenderer) plot.getRenderer();
	      	       
	        return chart;
	    }
	 
	 
	  public static void main(final String[] args) {

	        VisuSolution demo = new VisuSolution("Parcours");
	        demo.pack();
	        RefineryUtilities.centerFrameOnScreen(demo);
	        demo.setVisible(true);

	    }



	
	
	
}