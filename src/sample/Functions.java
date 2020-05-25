package sample;


import javafx.application.Application;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Locale;

public class Functions  {
    double Mx = 0, Dx = 0;
    public void garmonika(double[] x1, int n, int W, int N, XYChart.Series series, LineChart lineChart, boolean bool){
         double[] A1 = new double[n];
         double[] f1= new double[n];
        for (int i=1; i<n; i++)
        {
            A1[i] = Math.random();
            f1[i] = Math.random();
        }
        float A2= (float) 0.5;
        float f2= (float) 0.5;
        System.out.println("A=" + A1);
        System.out.println("f=" + f1);
        float Wp = W / n;
        for (int t = 1; t < N; t++) {
            for (int i = 1; i < n; i++) {
                x1[t] = (x1[t] + (A2 * Math.sin(Wp * t + f2)));
                Wp += W / n;
            }
        }

        if (bool) {
            for (int i = 1; i < N; i++) {
                series.getData().add(new XYChart.Data(i, x1[i]));
            /*System.out.println(i+" ");
            System.out.println(x[i]+" ");*/
            }
            lineChart.getData().add(series);
        }
    }
    public double Mean(double x[]){
        double Mx=0;
        for (int i = 1; i < x.length; i++) {
            Mx = Mx + x[i];
        }
        Mx = Mx / x.length;
     return Mx;
    }
    public double Despersion(double x[]){
        double Mx=Mean(x);
        double Dx=0;
        for (int i = 1; i < x.length; i++) {
            Dx = Dx + Math.pow((x[i] - Mx), 2);
        }
        Dx = Dx / (x.length- 1);
        return Dx;
    }
    public void autocorrelation(double x[], int N, XYChart.Series series, LineChart lineChart){
        double Mx=Mean(x);
        double R[] = new double[N];
        double[] hxHy = new double[N];
        x = Arrays.copyOf(x, x.length*2);
        int n2 = x.length / 2;
        for (int tau = 0; tau < n2; tau++) {
            double[] hx = Arrays.copyOfRange(x, 0, n2);
            double[] hy = Arrays.copyOfRange(x, tau, tau + n2);
            for (int i = 0; i < hx.length; i++) {
                hxHy[i] = (hx[i] - Mx) * (hy[i] - Mx);
            }
            R[tau] = Arrays.stream(hxHy).sum() / (n2 - 1);
        }
        for (int i = 1; i < R.length; i++) {


            series.getData().add(new XYChart.Data(i, R[i]));

        }
        lineChart.getData().add(series);
    }
    public void crosscorrelation(double x[],double y[], int N, XYChart.Series series, LineChart lineChart) {
        double Mx=Mean(x);
        double My=Mean(y);
        double R[] = new double[N];
        double[] hxHy = new double[N];
        x = Arrays.copyOf(x, x.length*2);
        int n2 = x.length / 2;
        for (int tau = 0; tau < n2; tau++) {
            double[] hx = Arrays.copyOfRange(x, 0, n2);
            double[] hy = Arrays.copyOfRange(y, tau, tau + n2);
            for (int i = 0; i < hx.length; i++) {
                hxHy[i] = (hx[i] - Mx) * (hy[i] - My);
            }
            R[tau] = Arrays.stream(hxHy).sum() / (n2 - 1);
        }
        for (int i = 1; i < R.length; i++) {


            series.getData().add(new XYChart.Data(i, R[i]));

        }
        lineChart.getData().add(series);
    }

}
