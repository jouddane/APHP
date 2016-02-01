package visu;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.data.time.TimePeriodValues;
import org.jfree.data.time.TimePeriodValuesCollection;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;



public class VisuCheckeur extends ApplicationFrame{
	
	
		public Donnees donnees;

	    /**
	     * Constructs a new demonstration application.
	     *
	     * @param title  the frame title.
	     */
	    public VisuCheckeur(final String title,int i) {

	        super(title);
	        this.donnees=new Donnees();
	       
	        final JFreeChart chart = createCombinedChart(this.donnees, i);
	        final ChartPanel panel = new ChartPanel(chart, true, true, true, true, true);
	        panel.setPreferredSize(new java.awt.Dimension(900, 600));
	        setContentPane(panel);
	        
	        

	    }

	    /**
	     * Creates a combined chart.
	     *
	     * @return The combined chart.
	     */
	    private JFreeChart createCombinedChart(Donnees donnees,int i) {
	    	 // parent plot...
	    	DateAxis x = new DateAxis("Domaine");
	        x.setTickUnit(new DateTickUnit(DateTickUnitType.MINUTE,1));
	        final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new DateAxis("Domain"));
	        plot.setGap(1.0);
	        
	        
	        final DateAxis domainAxis = new DateAxis("Date");
	        domainAxis.setVerticalTickLabels(true);
	       // domainAxis.setTickUnit(new DateTickUnit(DateTickUnit.MINUTE, 1));
	        domainAxis.setDateFormatOverride(new SimpleDateFormat("hh:mm"));
	        domainAxis.setLowerMargin(0.01);
	        domainAxis.setUpperMargin(0.01);
	    	
	        
	               
	        
	        // create subplots...
	    		
	    		
	    		
	    		//for (int i=0; i<3; i++){ boucle for pour plusieurs graphs dans la même fenêtre
	    		final XYDataset data1 = createDataset1(donnees, i);
		        final XYItemRenderer renderer1 = new StandardXYItemRenderer();
		        final NumberAxis rangeAxis1 = new NumberAxis("Utilisation RH :"+i);
		        

		        final XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1, renderer1);
		        
		        
		     // add secondary axis
		        subplot1.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
		        rangeAxis1.setAutoRangeIncludesZero(false);
		    
		        plot.add(subplot1, 1);
		        
		        
		        final XYDataset data2 = createDataset2(donnees, i);
		        final XYItemRenderer renderer2 = new StandardXYItemRenderer();
		        final NumberAxis rangeAxis2 = new NumberAxis("Utilisation RM : "+i);
		        
		        
		        final XYPlot subplot2 = new XYPlot(data2, null, rangeAxis2, renderer2);
		        
		        
		        
		        subplot2.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
		        rangeAxis2.setAutoRangeIncludesZero(false);
		        
		        
		        plot.add(subplot2, 1);
		        //plot.setOrientation(PlotOrientation.VERTICAL);
	    		// } fin boucle for
		        return new JFreeChart("CombinedDomainXYPlot Demo",
                        JFreeChart.DEFAULT_TITLE_FONT, plot, true);

	    }

	    /**
	     * Creates a sample dataset.
	     *
	     * @return Series 1.
	     */
	    private XYDataset createDataset1(Donnees donnees, int j) {
	    	// Il faut que je cherche les soins qui commencent à heure = qqchose et p
	    	 TimeSeriesCollection dataset = new TimeSeriesCollection();
	    	 dataset.setDomainIsPointsInTime(true);
	    	 
	    	 final TimeSeries s1 = new TimeSeries("Capacité utilisée RH "+j, Minute.class);
	    	 int [][] tableau = donnees.TableauCapacitesRH();
	    	 for (int h=8*60; h<tableau[0].length; h++){
	    		 int heure = h/60;
	    		 int minute = h%60;
	    		 
	    		 s1.add(new Minute(minute, heure, Calendar.getInstance().get(Calendar.DAY_OF_MONTH),Calendar.getInstance().get(Calendar.MONTH)+1,Calendar.getInstance().get(Calendar.YEAR)),tableau[j][h]);
	    	 }
	         final TimeSeries s2 = new TimeSeries("Capacité max RH "+j, Minute.class);
	         s2.add(new Minute(0, 8,Calendar.getInstance().get(Calendar.DAY_OF_MONTH),Calendar.getInstance().get(Calendar.MONTH)+1,Calendar.getInstance().get(Calendar.YEAR)), donnees.R.getRH()[j].getQuantiteMax());
	         s2.add(new Minute(0, 23,Calendar.getInstance().get(Calendar.DAY_OF_MONTH),Calendar.getInstance().get(Calendar.MONTH)+1,Calendar.getInstance().get(Calendar.YEAR)), donnees.R.getRH()[j].getQuantiteMax());
	         
	         dataset.addSeries(s1);
	         dataset.addSeries(s2);


	         

	         return dataset;

	    
	    }

	    /**
	     * Creates a sample dataset.
	     *
	     * @return A sample dataset.
	     */
	    private XYDataset createDataset2(Donnees donnees, int j) {

	        // create dataset 2...
	    	TimeSeriesCollection dataset = new TimeSeriesCollection();
	    	 dataset.setDomainIsPointsInTime(true);
	    	 
	    	 final TimeSeries s1 = new TimeSeries("Capacité utilisée RM "+j, Minute.class);
	    	 int[][] tableau = donnees.TableauCapacitesRM();
	    	 for (int h=8*60; h<tableau[0].length; h++){
	    		 int heure = h/60;
	    		 int minute = h%60;
	    		 
	    		 s1.add(new Minute(minute, heure, Calendar.getInstance().get(Calendar.DAY_OF_MONTH),Calendar.getInstance().get(Calendar.MONTH)+1,Calendar.getInstance().get(Calendar.YEAR)),tableau[j][h]);
	    	 }
	         final TimeSeries s2 = new TimeSeries("Capacité max RM "+j, Minute.class);
	         s2.add(new Minute(0, 8, Calendar.getInstance().get(Calendar.DAY_OF_MONTH),Calendar.getInstance().get(Calendar.MONTH)+1,Calendar.getInstance().get(Calendar.YEAR)), donnees.R.getRM()[j].getQuantiteMax());
	         s2.add(new Minute(0, 19, Calendar.getInstance().get(Calendar.DAY_OF_MONTH),Calendar.getInstance().get(Calendar.MONTH)+1,Calendar.getInstance().get(Calendar.YEAR)), donnees.R.getRM()[j].getQuantiteMax());
	         
	         dataset.addSeries(s1);
	         dataset.addSeries(s2);
			return dataset;

	    }

	    
	    public static void main(final String[] args) {
	    	for (int i=0; i<20; i++){
	        final VisuCheckeur demo = new VisuCheckeur("Checkeur",i);
	        demo.pack();
	        RefineryUtilities.centerFrameOnScreen(demo);
	        demo.setVisible(true);
	    	}

	    }

	

}
