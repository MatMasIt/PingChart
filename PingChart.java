
import java.awt.BasicStroke;
import java.awt.Color;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PingChart extends JFrame {
	private int nPings,timeBetweenPings;
    public PingChart(final String title,int nPings,int timeBetweenPings) {

        super(title);
        this.nPings=nPings;
        this.timeBetweenPings=timeBetweenPings;
        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);

    }

   
    private JFreeChart createChart(final XYDataset dataset) { // crea chart

        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Ping",
            "Tempo", 
            "TTL (ms)",
            dataset,
            true,
            true,
            false
        );

        chart.setBackgroundPaint(Color.white);
      
        final XYPlot plot = chart.getXYPlot(); 
        
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(false);
        
        final XYItemRenderer renderer = plot.getRenderer();
        if (renderer instanceof StandardXYItemRenderer) {
            final StandardXYItemRenderer rr = (StandardXYItemRenderer) renderer;
            renderer.setSeriesStroke(0, new BasicStroke(2.0f));
            renderer.setSeriesStroke(1, new BasicStroke(2.0f));
           }
        
        final DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
        
        return chart;
    }

    private XYDataset createDataset() {//crea dataset

        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        
        final TimeSeries s1 = new TimeSeries("Ping", Second.class);
        for(int i=0;i<this.nPings;i++) {// aggiungi i punti calcolando il ping e associandoli al tempo
        	s1.add(new Second(), Pinger.ping());
        	try {
				Thread.sleep(this.timeBetweenPings);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        dataset.addSeries(s1);

        return dataset;

    }


}