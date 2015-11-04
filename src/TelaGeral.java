import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import Jama.Matrix;

@SuppressWarnings("serial")
public class TelaGeral extends JFrame{
	
	private double A[][] = {{-0.0656, 0.0000}, {0.0656, -0.0656}};
	private double AElevadoADois[][] = {{0.0043, 0.0000}, {-0.0086, 0.0043}};	
	private double C[][] = {{0.0000, 1.0000}, {0, 0}};
	private double I[][] = {{1.0000, 0.0000}, {0.0000, 1.0000}};
	private double V[][] = {{0.0000, 1.0000}, {0.0656, -0.0656}};
	private double InvV[][] = {{1.0000, 15.2439}, {1.0000, 0.0000}};
	private double Transp[][] = {{0.0000}, {1.0000}};
	
	private Matrix AMatriz = new Matrix(A);
	private Matrix AElevadoADoisMatriz = new Matrix(AElevadoADois);
	private Matrix CMatrix = new Matrix(C);
	private Matrix IMatriz = new Matrix(I);
	private Matrix VMatriz = new Matrix(V);
	private Matrix InvVMatriz = new Matrix(InvV);
	private Matrix TranspMatriz = new Matrix(Transp);
	
	public TelaGeral(){
	
	}
	
	protected Matrix calculaMatrizL(JTextField textFieldReP, JTextField textFieldImP) {
		Matrix ql = calculaQl(Double.parseDouble(textFieldReP.getText()), Double.parseDouble(textFieldImP.getText()));
		
		Matrix primeiraParteProduto = ql.times(InvVMatriz);
		
		return primeiraParteProduto.times(TranspMatriz);
	}
	
	protected Matrix calculaQl(double realP, double imaginarioP) {
		Matrix terceiroTermo = IMatriz.times(Math.pow(realP, 2) + Math.pow(imaginarioP, 2));
		Matrix segundoTermo = (AMatriz.times(2*realP*(-1))).plus(terceiroTermo);
		Matrix qlMatriz = AElevadoADoisMatriz.plus(segundoTermo);
		
		return qlMatriz;
	}
	
	protected double[] calculaPolos(JTextField textFieldL1, JTextField textFieldL2) {
		double[][] L = {{Double.parseDouble(textFieldL1.getText())}, {Double.parseDouble(textFieldL2.getText())}};
		Matrix LMatrix = new Matrix(L);
		
		Matrix LCMatriz = LMatrix.times(CMatrix);
		Matrix MenosLCMatriz = LCMatriz.times(-1);
		Matrix AMatrizMenosLCMatriz = AMatriz.plus(MenosLCMatriz);
		Matrix MenosAMatrizMenosLCMatriz = AMatrizMenosLCMatriz.times(-1);
		
		double[] polos = {0, 0, 0, 0};		
		
		return polos;
	}
	
	/**
	 * @param args
	 */
	protected Object[] getItensComboLeituraEscrita() {
		Object[] io = {"Selecione", 0, 1, 2, 3, 4, 5, 6, 7};
		
		return io;
	}

	protected Object[] getItensComboTiposOnda() {
		String[] tiposOnda = {"Selecione", "Quadrada", "Degrau", "Aleatória", "Senoidal", "Dente de Serra"};
		
		return tiposOnda;
	}
	
	protected Object[] getItensComboTiposControlador() {
		String[] tiposControle = {"Selecione", "P", "PI", "PD", "PID", "PI-D", "Sem Controle"};
		
		return tiposControle;
	}
	
	protected Object[] getItensComboTiposControle() {
		String[] tiposControle = {"Selecione", "Simples", "Cascata", "Sem Controle"};
		
		return tiposControle;
	}

	protected String conectarDesconectar(JButton botao, JFrame frame){
		return botao.getText();
	}
	
	protected void habilitarDesabilitarRdbtnTipoOnda(Boolean habilitar){
	}
	
	/** 
	 * Método usado para redimensionar, ao clicar, o panelGrafico1. 
	 */
	protected void redimensionarPainelGrafico2(final JLayeredPane panelGrafico1, final JLayeredPane panelGrafico2){
		//Método usado para expandir o frame de panelGrafico2			
		panelGrafico2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(panelGrafico1.isVisible()){
					panelGrafico1.setVisible(false);
					panelGrafico2.setBounds(8, 20, 388, 400);
				}else{					
					panelGrafico2.setBounds(8, 225, 388, 200);
					panelGrafico1.setVisible(true);
				}
			}
		});
	}
	
	/** 
	 * Método usado para redimensionar, ao clicar, o panelGrafico1. 
	 */
	protected void redimensionarPainelGrafico1(final JLayeredPane panelGrafico1, final JLayeredPane panelGrafico2){
		panelGrafico1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {			
				if(panelGrafico2.isVisible()){
					panelGrafico2.setVisible(false);
					panelGrafico1.setBounds(8, 20, 388, 400);
				}else{
					panelGrafico1.setBounds(8, 20, 388, 200);
					panelGrafico2.setVisible(true);
				}
			}
		});
	}
}
