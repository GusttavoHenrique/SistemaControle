
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

/*public class Chart extends ApplicationFrame{
	
	public static LinkedList<Ponto> filaDePontos = new LinkedList<Ponto>();
	Ponto ponto = new Ponto();
	ChartPanel  painel;
	
	
	public Chart(String title) {
		super(title);
		XYDataset dataset = criarDataset();
		JFreeChart graph = criarGrafico(dataset);
		painel = new ChartPanel(graph);
		painel.setPreferredSize(new Dimension(700, 400));
		setContentPane(painel);
				
	}*/
public class Chart{
	
	public LinkedList<Ponto> filaDePontos = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaSaturada = new LinkedList<Ponto>();
	Ponto ponto = new Ponto();
	public ChartPanel  painel;
	public Dados dados = new Dados();
	

	
	
	public Chart(Dados dados) {
		XYDataset dataset = criarDataset();
		JFreeChart graph = criarGrafico(dataset);
		this.painel = new ChartPanel(graph);
		
		/*painel = new ChartPanel(graph);
		painel.setPreferredSize(new Dimension(700, 400));
		setContentPane(painel);*/
				
	}	
	
	/*public void setFila(LinkedList<Ponto> fila){
		this.filaDePontos = fila;
	}*/

	public void atualizarGrafico(){
		painel.setChart(criarGrafico(criarDataset()));
	}
	
	public void atualizarFila(Ponto ponto){
		
			filaDePontos.addLast(ponto);
			
			if (filaDePontos.size() > 40) filaDePontos.removeFirst();
		
	}
	public void atualizarSaturada(Ponto ponto){
		
		filaSaturada.addLast(ponto);
		
		if (filaSaturada.size() > 40) filaSaturada.removeFirst();
	
	}
	public XYDataset criarDataset()
	{

		XYSeries serieDePlot = new XYSeries("Serie");
		XYSeries serieSaturada = new XYSeries("Saturada");
		
		//isso trava a thread?
		for (int i = 0; i < filaDePontos.size(); i++)
			serieDePlot.add(filaDePontos.get(i).getX(), filaDePontos.get(i).getY());
		
		for (int i = 0; i < filaSaturada.size(); i++)
			serieSaturada.add(filaSaturada.get(i).getX(), filaSaturada.get(i).getY());
		
		XYSeriesCollection dataset= new XYSeriesCollection();
		dataset.addSeries(serieDePlot);
		dataset.addSeries(serieSaturada);
		
		
		return dataset;
	}
	
	public JFreeChart criarGrafico(XYDataset dataset){
		
		JFreeChart graph = null;
		
		graph = ChartFactory.createXYLineChart("Gr�fico de Tens�o", "segundos", "volts", dataset, PlotOrientation.VERTICAL, false, false, false);
		graph.getXYPlot().setBackgroundPaint(Color.white);
			
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		
		
		renderer.setSeriesShapesVisible(0, false);
		renderer.setSeriesLinesVisible(0, true);
		renderer.setSeriesFillPaint(0, Color.BLUE);
		
		
		renderer.setSeriesShapesVisible(1, false);
		renderer.setSeriesLinesVisible(1, true);
		renderer.setSeriesFillPaint(0, Color.RED);
		
		
        graph.getXYPlot().setRenderer(renderer);
        
		return graph;
	}
	
	public static  void main (String[] args){
		
		
		
/*		EnvioDenteDeSerra dente = new EnvioDenteDeSerra(10, 2, 40, 1);
		
		while (dente.duracao > 0)
		{
			filaDePontos.addLast(new Ponto(dente.envioDenteDeSerra())); 
		}*/
		
		/*Chart plot = new Chart("Teste");
		
		plot.pack();
		RefineryUtilities.centerFrameOnScreen(plot);
		plot.setVisible(true);	*/
		
		/*Ponto p = new Ponto();
		Ponto p1 = new Ponto();
		
		p.setY(4);
		p.setX(6);
		
		p1.setX(8);
		p1.setY(5);*/
		
		/*filaDePontos.addLast(p);
		filaDePontos.addLast(p1);
		
		plot.atualizarGrafico();*/
		
	}
		
	

}
