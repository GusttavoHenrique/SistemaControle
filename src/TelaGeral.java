import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

@SuppressWarnings("serial")
public class TelaGeral extends JFrame{
	
	//private double A[][] = {{1, 0.0000}, {0, -2}};
	private double G[][] = {{0.9935, 0}, {0.00656, 0.9935}};
	//private double AElevadoADois[][] = {{0.000043, 0.0000}, {-0.000086, 0.000043}};	
//	private double C[][] = {{1, 1.0000}, {0, 0}};
	private double C[][] = {{0,1}};
	private double I[][] = {{1.0000, 0.0000}, {0.0000, 1.0000}};
	//private double V[][] = {{0.0000, 1.0000}, {0.00656, -0.00656}};
	private double V[][] = {{0.0000, 1.0000}, {0.00656, 0.9935}};
	//private double V[][] = {{1, 1}, {1, -2}};
	//private double InvV[][] = {{2.0/3.0, 1.0/3.0}, {1.0/3.0, -1.0/3.0}};
	private double Transp[][] = {{0.0000}, {1.0000}};
	
	//private Matrix AMatriz = new Matrix(A);
	private Matrix GMatriz = new Matrix(G);
	//private Matrix AElevadoADoisMatriz = new Matrix(AElevadoADois);
	private Matrix CMatriz = new Matrix(C);
	private Matrix IMatriz = new Matrix(I);
	private Matrix VMatriz = new Matrix(V);
	private Matrix InvVMatriz = new Matrix(VMatriz.inverse().getArray());
	private Matrix TranspMatriz = new Matrix(Transp);
	
	public TelaGeral(){
	
	}
	
	protected Matrix calculaMatrizL(JTextField textFieldReP, JTextField textFieldImP, JTextField textFieldReP2,JTextField textFieldImP2 ) {
		
		
		double [][] L_calculado = new double[2][1];
		
		Matrix L_calc_matrix = new Matrix (L_calculado);
		
		double [][] qL_G = new double [2][2];
		
		Matrix ql_G_matrix = new Matrix(qL_G);
		
		double soma_de_polos = 0;
		double produto_de_polos = 0;
		double var = Double.parseDouble(textFieldImP.getText());
		if(var == 0){
			produto_de_polos = Double.parseDouble(textFieldReP.getText())*Double.parseDouble(textFieldReP2.getText());
			
		}else{
			produto_de_polos = Math.pow(Double.parseDouble(textFieldReP.getText()), 2) + Math.pow(Double.parseDouble(textFieldImP.getText()), 2);
		}
		
		soma_de_polos = Double.parseDouble(textFieldReP.getText()) + Double.parseDouble(textFieldReP2.getText());
		
		ql_G_matrix = (GMatriz.times(GMatriz)).plus(GMatriz.times(soma_de_polos)).plus((IMatriz.times(produto_de_polos)));
		
		L_calc_matrix = (ql_G_matrix.times(InvVMatriz)).times(TranspMatriz);
		
		return L_calc_matrix;
		
		
	}
	
	protected EigenvalueDecomposition calculaPolos(JTextField textFieldL1, JTextField textFieldL2) {
		double[][] L = {{Double.parseDouble(textFieldL1.getText())}, {Double.parseDouble(textFieldL2.getText())}};
		Matrix LMatrix = new Matrix(L);
		
		Matrix LCMatriz = LMatrix.times(CMatriz);
		Matrix MenosLCMatriz = LCMatriz.times(-1);
		
		Matrix GMatrizMenosLCMatriz = GMatriz.plus(MenosLCMatriz);
		
		EigenvalueDecomposition polos = GMatrizMenosLCMatriz.eig();
		
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
