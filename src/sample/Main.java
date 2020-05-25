package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Сигналища");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("N");
        //creating the chart

        //defining a series
        XYChart.Series series1 = new XYChart.Series();
        //series1.setName("автокореляція");

        int n = 10;
        final int N = 256;
        final int W = 2700;
        double[] x = new double[N];
        Functions function=new Functions();
        final LineChart<Number, Number> lineChart =
                new LineChart<>(xAxis, yAxis);
        Scene scene = new Scene(lineChart, 1200, 600);
        lineChart.setTitle("Графік випадкових сигналів");
        //Гармоніка
        function.garmonika(x,n,W,N,series1,lineChart, true);
        //
        String result = String.format("Mx=%3.3f and Dx=%3.3f", function.Mean(x), function.Despersion(x));
        series1.setName(result);
        //function.autocorrelation(x,N,series1,lineChart);
        double[] y=new double[N];
        function.garmonika(y,n,W,N,series1,lineChart,false);
       // function.autocorrelation(y,N,series1,lineChart);
       // function.crosscorrelation(x,y,N,series1,lineChart);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}
