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

import dev.Probleme;

public class VisuSolution extends ApplicationFrame{


	public VisuSolution(String title, Integer[][][] donnees, Probleme aResoudre){
		super(title);

		GanttCategoryDataset dataset = createDataset( donnees, aResoudre);
		JFreeChart chart = createChart(dataset);

		// Ajoute le graph � un Panel
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1200, 800));
		setContentPane(chartPanel);


	}
/**
 * Cr�e les donn�ees pour le gantt
 * @param donnees
 * @param aResoudre
 * @return
 */
	public GanttCategoryDataset createDataset(Integer[][][] donnees, Probleme aResoudre) {
		
		int Annee = Calendar.getInstance().get(Calendar.YEAR);
		int Mois =  Calendar.getInstance().get(Calendar.MONTH);
		int Jour = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		TaskSeriesCollection collection = new TaskSeriesCollection();
		TaskSeries P = new TaskSeries("Tous les parcours");
		for(int i=0; i<aResoudre.getnPatients();i++){
			String s ="Patient"+i;
			Task S = new Task(s, new Date(Annee, Mois, Jour, 6,0), new Date(Annee, Mois, Jour, 21,0));
			for(int j=0; j<aResoudre.getnG_i()[aResoudre.getP_i()[i]];j++){
				for( int k=0; k< aResoudre.getnS_ij()[aResoudre.getP_i()[i]][j]; k++){
					int HeureD=donnees[i][j][k]/60;
					int MinD=donnees[i][j][k]%60;
					int Duree=aResoudre.getL_ijk()[aResoudre.getP_i()[i]][j][k];
					int HeureDnext = (donnees[i][j][k]+Duree)/60;
					int MinDnext =(donnees[i][j][k]+Duree)%60;
					S.addSubtask(new Task(" Groupe "+j+" Soin "+k, new Date(Annee,Mois,Jour,HeureD,MinD), new Date(Annee,Mois,Jour,HeureDnext,MinDnext)));
				}
			}
			P.add(S);
		}


		collection.add(P);

		return collection;
	}

	
	public JFreeChart createChart(GanttCategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createGanttChart(
				"Journee ", // Titre du graphe
				"Patients", // Domaine des y
				"Heure", // DOmaine des x
				dataset, // donnees
				true, // ajoute une l�gende
				true, // "tootltips"
				false // urls
				);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		GanttRenderer gr = (GanttRenderer) plot.getRenderer();

		return chart;
	}


	public static void main(final String[] args) {

//		VisuSolution demo = new VisuSolution("Parcours");
//		demo.pack();
//		RefineryUtilities.centerFrameOnScreen(demo);
//		demo.setVisible(true);

	}






}