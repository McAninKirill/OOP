package org.example.plot;

import org.example.hasnotprimecheck.ConsistentlyPrime;
import org.example.hasnotprimecheck.StreamPrime;
import org.example.hasnotprimecheck.ThreadPrime;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class Plot {
    private static final int numOfThreads = Runtime.getRuntime().availableProcessors();

    /**
     * Builder for a chart.
     *
     * @param dataset dataset to be displayed.
     * @return a chart.
     */
    private static JFreeChart plotBuilder(XYSeriesCollection dataset) {
        return ChartFactory.createXYLineChart(
                "Plot for multithreading",
                "Number of threads",
                "Time",
                dataset
        );
    }

    /**
     * Create series for dataset.
     */
    private static XYSeries createSeries(String name, int[] xData, int[] yData) {
        XYSeries series = new XYSeries(name);
        for (int i = 0; i < xData.length; i++) {
            series.add(xData[i], yData[i]);
        }
        return series;
    }


    /**
     * Create dateset.
     */
    private static XYSeriesCollection createDataset(){
        int[] threads = new int[numOfThreads];
        for(int i = 0; i < numOfThreads; i++) {
            threads[i] = i + 1;
        }

        int[] arr = new int[10000000];
        Arrays.fill(arr, 99971);
        int[] thrTime = new int[numOfThreads];
        for (int i = 1; i <= numOfThreads; i++) {
            ThreadPrime thr = new ThreadPrime(i);
            long start = System.currentTimeMillis();
            thr.hasPrime(arr);
            long end = System.currentTimeMillis();
            thrTime[i-1] = (int) (end - start);
        }
        XYSeries serThr = createSeries("Threads", threads, thrTime);

        int[] strTime = new int[numOfThreads];
        for (int i = 1; i <= numOfThreads; i++) {
            StreamPrime str = new StreamPrime(i);
            long start = System.currentTimeMillis();
            str.hasPrime(arr);
            long end = System.currentTimeMillis();
            strTime[i-1] = (int) (end - start);
        }
        XYSeries serStr = createSeries("Stream", threads, strTime);

        int[] consTime = new int[numOfThreads];
        for (int i = 1; i <= numOfThreads; i++) {
            ConsistentlyPrime cons = new ConsistentlyPrime();
            long start = System.currentTimeMillis();
            cons.hasPrime(arr);
            long end = System.currentTimeMillis();
            consTime[i-1] = (int) (end - start);
        }
        XYSeries serCons = createSeries("Consistenly", threads, consTime);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(serCons);
        dataset.addSeries(serStr);
        dataset.addSeries(serThr);
        return dataset;
    }

    /**
     * Create plot.
     */
    public static void showPlot() {
        XYSeriesCollection dataset = createDataset();
        JFreeChart plot = plotBuilder(dataset);
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Plot");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            ChartPanel chartPanel = new ChartPanel(plot);
            frame.add(chartPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        showPlot();
    }
}
