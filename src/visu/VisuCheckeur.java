package visu;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

import dev.Probleme;



public class VisuCheckeur extends ApplicationFrame{


/**
 * Construit le graph pour visualiser si la contrainte de ressource est respectée
 * @param title
 * @param donnees
 * @param aResoudre
 * @param i
 */
	public VisuCheckeur(final String title,Integer[][][] donnees, Probleme aResoudre, int i) {

		super(title);
		final JFreeChart chart = createCombinedChart(donnees, aResoudre, i);
		chart.setTitle("Occupation Ressource "+i);
		final ChartPanel panel = new ChartPanel(chart, true, true, true, true, true);
		panel.setPreferredSize(new java.awt.Dimension(400, 300));
		setContentPane(panel);



	}

	/**
	 * Crée un graph
	 * @param donnees
	 * @param aResoudre
	 * @param i
	 * @return
	 */
	private JFreeChart createCombinedChart(Integer [][][] donnees, Probleme aResoudre, int i) {
		DateAxis x = new DateAxis("Domaine");
		x.setTickUnit(new DateTickUnit(DateTickUnitType.MINUTE,1));
		final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new DateAxis("Domaine"));
		plot.setGap(1.0);


		final DateAxis domainAxis = new DateAxis("Date");
		domainAxis.setVerticalTickLabels(true);
		domainAxis.setDateFormatOverride(new SimpleDateFormat("hh:mm"));
		domainAxis.setLowerMargin(0.01);
		domainAxis.setUpperMargin(0.01);







		final XYDataset data1 = createDataset1(donnees, aResoudre, i);
		final XYItemRenderer renderer1 = new StandardXYItemRenderer();
		final NumberAxis rangeAxis1 = new NumberAxis("Utilisation Ressources"+i);


		final XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1, renderer1);


		subplot1.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
		rangeAxis1.setAutoRangeIncludesZero(false);

		plot.add(subplot1, 1);


		return new JFreeChart("Utilisation des Ressources",
				JFreeChart.DEFAULT_TITLE_FONT, plot, true);

	}

/**
 *  Crée les données nécessaires pour la ressource a.
 * @param donnees
 * @param aResoudre
 * @param a
 * @return
 */
	private XYDataset createDataset1(Integer [][][] donnees, Probleme aResoudre, int a) {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.setDomainIsPointsInTime(true);
		// Ici il faut que je choppe les soins de la ressource a
		final TimeSeries s1 = new TimeSeries("Utilisation ressource "+a, Minute.class);



		//Création du tableau ou on stocke toutes les ressources utilisees au cours de la journee
		int[] tableau= new int[24*60];
		for(int i=0;i<aResoudre.getnPatients();i++){
			for (int j = 0; j < aResoudre.getnG_i()[aResoudre.getP_i()[i]]; j++) {
				for (int k = 0; k < aResoudre.getnS_ij()[aResoudre.getP_i()[i]][j]; k++) {

					if(aResoudre.getQ_ijkr()[aResoudre.getP_i()[i]][j][k][a]!=0){
						for (int l=0; l< aResoudre.getL_ijk()[aResoudre.getP_i()[i]][j][k]; l++){
							tableau[donnees[i][j][k]+l]+=1;
						}
					}
				}
			}
		}


			for (int h=aResoudre.getHOuverture(); h<aResoudre.getHFermeture(); h++){
				int heure = h/60;
				int minute = h%60;
				int jour  = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
				int mois = Calendar.getInstance().get(Calendar.MONTH)+1;
				int annee = Calendar.getInstance().get(Calendar.YEAR);
				s1.add(new Minute(minute, heure, jour,mois,annee),tableau[h]);

			}
			
			
			final TimeSeries s2 = new TimeSeries("Capacité max Ressource "+a, Minute.class);
			s2.add(new Minute(aResoudre.getHOuverture()%60, aResoudre.getHOuverture()/60, Calendar.getInstance().get(Calendar.DAY_OF_MONTH),Calendar.getInstance().get(Calendar.MONTH)+1,Calendar.getInstance().get(Calendar.YEAR)), aResoudre.getCpij_max()[a]);
	        s2.add(new Minute(aResoudre.getHFermeture()%60, aResoudre.getHFermeture()/60, Calendar.getInstance().get(Calendar.DAY_OF_MONTH),Calendar.getInstance().get(Calendar.MONTH)+1,Calendar.getInstance().get(Calendar.YEAR)), aResoudre.getCpij_max()[a]);

	        dataset.addSeries(s1);
	        dataset.addSeries(s2);

			return dataset;


		}


		public static void main(final String[] args) {
			/*for (int i=0; i<20; i++){
	        final VisuCheckeur demo = new VisuCheckeur("Checkeur",i);
	        demo.pack();
	        RefineryUtilities.centerFrameOnScreen(demo);
	        demo.setVisible(true);
	    	}*/

		}



	}
