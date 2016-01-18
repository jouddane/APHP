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

public class TestVisu extends ApplicationFrame{

	public TestVisu(String title){
		super(title);
		
		GanttCategoryDataset dataset = createDataset( new Donnees());
        JFreeChart chart = createChart(dataset);

        // add the chart to a panel...
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1200, 800));
        setContentPane(chartPanel);


	}
	
	public GanttCategoryDataset createDataset(Donnees donnees) {
		TaskSeriesCollection collection = new TaskSeriesCollection();
		TaskSeries P = new TaskSeries("Tous les parcours");
		for(int i=0; i<donnees.nbPatients();i++){
			String s ="Parcours";
			Task S = new Task("Parcours"+i, new Date(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), donnees.getDebutH(i,0),donnees.getDebutM(i, 0)), new Date(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH),donnees.getDebutH(i, donnees.nbSoins()),donnees.getDebutM(i, donnees.nbSoins())));
			for (int j=0; j<donnees.nbSoins()-1;j++){
				if(donnees.getSoin(i, j).getDuree()==15){
					
				}
				else{
					S.addSubtask(new Task("Soin "+j, new Date(2016, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), donnees.getDebutH(i, j), donnees.getDebutM(i, j)), new Date(2016,Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH),donnees.getDebutH(i, j+1),donnees.getDebutM(i, j+1))));
				}
			}
			P.add(S);
		}
		
		 /* Task S00= new Task("Parcours 1", new Date(2016, 1, 9, 8, 0), new Date(2016,1,9,10,5));
		Task S0 = new Task("Soin1", new Date(2016, 1, 9, 8, 0), new Date(2016,1,9,8,45));
        Task S1 = new Task("Soin2", new Date(2016, 1, 9, 8, 30), new Date(2016,1,9,10,5));
      	Task S1 = new Task("Consultation", new SimpleTimePeriod(480, 495));
        Task S2 = new Task("IDE-Prise de sang", new SimpleTimePeriod(510, 525));
        S1.setPercentComplete(1.0);
        Task S3 = new Task("IDE-Exemple1", new SimpleTimePeriod(535, 560));*/
        
        //S00.addSubtask(S0);
        //S00.addSubtask(S1);
        /*S0.addSubtask(S3);

        TaskSeries P2 = new TaskSeries("Parcours2");
        
        Task S4 = new Task("Total2", new SimpleTimePeriod(480, 1075));
        Task S5 = new Task("Consultation", new SimpleTimePeriod(480, 510));
        Task S6 = new Task("Chimio", new SimpleTimePeriod(800, 845));

        Task S7 = new Task("IDE-Exemple2", new SimpleTimePeriod(1000, 1010));

        Task S8 = new Task("IDE-Exemple3", new SimpleTimePeriod(1050, 1075));

        S4.addSubtask(S5);
        S4.addSubtask(S6);
        S4.addSubtask(S7);
        S4.addSubtask(S8);*/
        
        //P1.add(S0);
        //P1.add(S1);
        //P2.add(S4);

        
        //collection.add(P1);
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
	       /* gr.setItemLabelGenerator();
	        gr.setItemLabelsVisible(true);
	       Marker marker = new Marker() {
		};
			marker.setAlpha(0);
			marker.setLabel("Heure actuelle");
	       plot.addRangeMarker(marker);*/
	       
	       
	        return chart;
	    }
	 
	 
	  public static void main(final String[] args) {

	        TestVisu demo = new TestVisu("Parcours");
	        demo.pack();
	        RefineryUtilities.centerFrameOnScreen(demo);
	        demo.setVisible(true);

	    }



	
	
	
}