import java.awt.Color;
import java.util.LinkedList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartNivel {


	public LinkedList<Ponto> filaDeNivelUm = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeNivelDois = new LinkedList<Ponto>();
	
	
	Ponto ponto = new Ponto();
	public ChartPanel  painelG2;
	public Dados dados = new Dados();
	

	
	
	public ChartNivel(Dados dados) {
		XYDataset dataset = criarDataset();
		JFreeChart graph = criarGrafico(dataset);
		this.painelG2 = new ChartPanel(graph);
		
				
	}	

	public void atualizarGrafico(){
		painelG2.setChart(criarGrafico(criarDataset()));
	}
	
	public void atualizarFilaDeNivelUm(Ponto ponto){
		
			filaDeNivelUm.addLast(ponto);
			
			if (filaDeNivelUm.size() > 600) filaDeNivelUm.removeFirst();
		
	}
	
	public void atualizarFilaDeNivelDois(Ponto ponto){
		
		filaDeNivelDois.addLast(ponto);
		
		if (filaDeNivelDois.size() > 600) filaDeNivelDois.removeFirst();
	
    }
	


		
	public XYDataset criarDataset()
	{

		XYSeries serieNivelUm = new XYSeries("NivelUm");
		XYSeries serieNivelDois = new XYSeries("Nivel 2");
		
		
		//isso trava a thread?
		
		for (int i = 0; i < filaDeNivelUm.size(); i++)
			serieNivelUm.add(filaDeNivelUm.get(i).getX(), filaDeNivelUm.get(i).getY());
		
		for (int i = 0; i < filaDeNivelDois.size(); i++)
			serieNivelUm.add(filaDeNivelDois.get(i).getX(), filaDeNivelDois.get(i).getY());
		
		
		
		
		XYSeriesCollection dataset= new XYSeriesCollection();
		dataset.addSeries(serieNivelUm);
		dataset.addSeries(serieNivelDois);
		
		
		
		return dataset;
	}
	
	public JFreeChart criarGrafico(XYDataset dataset){
		
		JFreeChart graph = null;
		
		graph = ChartFactory.createXYLineChart("Gr�fico de N�vel", "segundos", "centimetros", dataset, PlotOrientation.VERTICAL, false, false, false);
		graph.getXYPlot().setBackgroundPaint(Color.white);
			
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		
		//Nivel 1
		renderer.setSeriesShapesVisible(0, false);
		if(dados.isNivel1())
			renderer.setSeriesLinesVisible(0, true);
		else{renderer.setSeriesLinesVisible(0, false);}
		renderer.setSeriesPaint(0, Color.BLACK);
		
		//Nivel 2
		renderer.setSeriesShapesVisible(0, false);
		if(dados.isNivel2())
			renderer.setSeriesLinesVisible(0, true);
		else{renderer.setSeriesLinesVisible(0, false);}
		renderer.setSeriesPaint(0, Color.BLUE);
		
				
				
		
		
        graph.getXYPlot().setRenderer(renderer);
        
		return graph;
	}
	


}
