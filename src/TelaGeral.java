import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

@SuppressWarnings("serial")
public class TelaGeral extends JFrame{
	protected Tanque thread;
	protected Dados dados;
	
	protected String textFieldReP1Anterior = "";
	protected String textFieldImP1Anterior = "";
	
	protected String textFieldReP2Anterior = "";
	protected String textFieldImP2Anterior = "";
	
	protected String textFieldL1Anterior = "";
	protected String textFieldL2Anterior = "";
	
	protected String textFieldP1SegAnterior = "";
	
	protected String textFieldReP2SegAnterior = "";
	protected String textFieldImP2SegAnterior = "";
	
	protected String textFieldReP3SegAnterior = "";
	protected String textFieldImP3SegAnterior = "";
	
	protected String textFieldK1Anterior = "";
	protected String textFieldK21Anterior = "";
	protected String textFieldK22Anterior = "";
	
	protected boolean setaAzulIconObservadorParaDireita = true;
	protected boolean setaAzulIconSeguidorParaDireita = true;	
	
	//Atributos utilizados para os cálculos do observador
	private double G[][] = {{0.9935, 0}, {0.00656, 0.9935}};
	static double H[][] = {{2.96/Math.pow(10, 2)}, {9.63/Math.pow(10, 5)}};
	private double C[][] = {{0,1}};
	private double I2x2[][] = {{1.0000, 0.0000}, {0.0000, 1.0000}};
	private double Wo[][] = {{0.0000, 1.0000}, {0.00656, 0.9935}};
	private double Transp[][] = {{0.0000}, {1.0000}};
	
	private Matrix GMatriz = new Matrix(G);
	private Matrix HMatriz = new Matrix(H);
	private Matrix CMatriz = new Matrix(C);
	private Matrix I2x2Matriz = new Matrix(I2x2);
	private Matrix WoMatriz = new Matrix(Wo);
	private Matrix InvWoMatriz = new Matrix(WoMatriz.inverse().getArray());
	private Matrix TranspMatriz = new Matrix(Transp);
	
	//Atributos utilizados para os cálculos do seguidor
	private double GChapeu[][] = {{0.9935, 0, 0.0296}, {0.00656, 0.9935, 0.0000967}, {0, 0, 0}};
	private double HChapeu[][] = {{0}, {0}, {1}};
	private double Wc[][] = {{0, 0.02960, 0.02941}, {0, 0.0000967, 0.0002902}, {1, 0, 0}};
	private double WcInversa[][] = {{0, 0, 1}, {50.5049, -5118.3672, 0}, {-16.8292, 5151.4339, 0}};
	
//	private double WcInversa[][] = {{0, 0, 1}, {51.9902, -5519.2524, 0}, {-18.2154, 5556.9265, 0}};
	
	private double I3x3[][] = {{1.0000, 0.0000, 0.0000}, {0.0000, 1.0000, 0.0000}, {0.0000, 0.0000, 1.0000}};
	private double TranspChapeu[][] = {{0, 0, 1}};
	private double GHCI[][] = {{-0.0065, 0, 0.0296}, {0.00656, -0.0065, 0.0000963}, {0.00656, 0.9935, 0.0000963}};
//	private double GHCI[][] = {{-0.0065, 0, 0.0295}, {0.0061, -0.0065, 0.0000967}, {0.9935, 0, 0.0295}};
	
	private Matrix GChapeuMatriz = new Matrix(GChapeu);
	private Matrix HChapeuMatriz = new Matrix(HChapeu);
	private Matrix WcMatriz = new Matrix(Wc);
	private Matrix WcInversaMatriz = new Matrix(WcMatriz.inverse().getArray());
	private Matrix I3x3Matriz = new Matrix(I3x3);
	private Matrix TranspChapeuMatriz = new Matrix(TranspChapeu);
	private Matrix GHCIMatriz = new Matrix(GHCI);
	private Matrix GHCIInvMatriz = new Matrix(GHCIMatriz.inverse().getArray());
	
	public TelaGeral(){
	
	}
	
	protected Matrix calculaMatrizK(JTextField textFieldP1, JTextField textFieldReP2, JTextField textFieldImP2, JTextField textFieldReP3) {
		
//		GMatriz.plus(I2x2Matriz.times(-1)).print(7, 7);
//		HMatriz.print(7, 7);
//		CMatriz.times(GMatriz).print(7, 7);
//		CMatriz.times(HMatriz).print(7, 7);
		
		double [][] K = new double[1][3];
		double [][] q = new double [3][3];
		
		double soma1 = 0, soma2 = 0, produto = 0;
		
		double parteReP1 = textFieldP1.getText().equals("") ? 0 : Double.parseDouble(textFieldP1.getText());
		double parteReP2 = textFieldReP2.getText().equals("") ? 0 : Double.parseDouble(textFieldReP2.getText());
		double parteReP3 = textFieldReP3.getText().equals("") ? 0 : Double.parseDouble(textFieldReP3.getText());
		double parteIm = textFieldImP2.getText().equals("") ? 0 : Double.parseDouble(textFieldImP2.getText());
		
		if(parteIm == 0){
			soma1 = parteReP1 + parteReP2 + parteReP3;
			soma2 = parteReP2*parteReP3 + parteReP1*(parteReP2 + parteReP3);
			
			produto = parteReP1*parteReP2*parteReP3;
		}else{
			double moduloP = Math.pow(parteReP2, 2) + Math.pow(parteIm, 2);
						
			soma1 = 2*parteReP2 + parteReP1;
			soma2 = moduloP + parteReP1*(2*parteReP2);
			
			produto = parteReP1*moduloP;
		}
				
		Matrix qCMatrix = new Matrix(q);		
		qCMatrix = (GChapeuMatriz.times(GChapeuMatriz)).times(GChapeuMatriz);
		qCMatrix = qCMatrix.plus((GChapeuMatriz.times(GChapeuMatriz)).times(soma1*(-1)));
		qCMatrix = qCMatrix.plus(GChapeuMatriz.times(soma2));
		qCMatrix = qCMatrix.plus(I3x3Matriz.times(produto*(-1)));
		
		Matrix KChapeuMatrix = new Matrix(K);
		KChapeuMatrix = (TranspChapeuMatriz.times(WcInversaMatriz)).times(qCMatrix);
		
		Matrix KMatrix = new Matrix(K);
		KMatrix = KChapeuMatrix.plus(TranspChapeuMatriz);
		KMatrix = KMatrix.times(GHCIInvMatriz);
		
		return KMatrix;
	}
	
	protected EigenvalueDecomposition calculaPolosSeguidorReferencia(JTextField textFieldK1, JTextField textFieldK21, JTextField textFieldK22) {
		double [][] KChapeu = new double[1][3];
		
		double k1 = textFieldK1.getText().equals("") ? 0 : Double.parseDouble(textFieldK1.getText());
		double k21 = textFieldK21.getText().equals("") ? 0 : Double.parseDouble(textFieldK21.getText());
		double k22 = textFieldK22.getText().equals("") ? 0 : Double.parseDouble(textFieldK22.getText());
		
		double K[][] = {{k21, k22, k1}};
		Matrix KMatrix = new Matrix(K);
		
		Matrix KChapeuMatrix = new Matrix(KChapeu);
		KChapeuMatrix = KMatrix.times(GHCIMatriz);
		KChapeuMatrix = KChapeuMatrix.plus(TranspChapeuMatriz.times(-1));
		
		Matrix GChapeuMenosHKChapeuMatriz = GChapeuMatriz.plus((HChapeuMatriz.times(KChapeuMatrix)).times(-1));
		
		EigenvalueDecomposition polos = GChapeuMenosHKChapeuMatriz.eig();
		
		return polos;
	}
	
	protected Matrix calculaMatrizL(JTextField textFieldReP, JTextField textFieldImP, JTextField textFieldReP2,JTextField textFieldImP2 ) {
		double [][] L_calculado = new double[2][1];
		
		Matrix L_calc_matrix = new Matrix (L_calculado);
		
		double [][] qL_G = new double [2][2];
		
		Matrix ql_G_matrix = new Matrix(qL_G);
		
		double soma_de_polos = 0;
		double produto_de_polos = 0;
		
		double var = textFieldImP.getText().equals("") ? 0 : Double.parseDouble(textFieldImP.getText());
		
		if(var == 0){
			produto_de_polos = Double.parseDouble(textFieldReP.getText())*Double.parseDouble(textFieldReP2.getText());
		}else{
			produto_de_polos = Math.pow(Double.parseDouble(textFieldReP.getText()), 2) + Math.pow(Double.parseDouble(textFieldImP.getText()), 2);
		}
		
		soma_de_polos = Double.parseDouble(textFieldReP.getText()) + Double.parseDouble(textFieldReP2.getText());
		
		ql_G_matrix = (GMatriz.times(GMatriz)).plus(GMatriz.times(soma_de_polos)).plus((I2x2Matriz.times(produto_de_polos)));
		
		L_calc_matrix = (ql_G_matrix.times(InvWoMatriz)).times(TranspMatriz);
		
		return L_calc_matrix;
	}
	
	protected EigenvalueDecomposition calculaPolosObservadorEstados(JTextField textFieldL1, JTextField textFieldL2) {
		double[][] L = {{Double.parseDouble(textFieldL1.getText())}, {Double.parseDouble(textFieldL2.getText())}};
		Matrix LMatrix = new Matrix(L);
		
		Matrix LCMatriz = LMatrix.times(CMatriz);
		Matrix MenosLCMatriz = LCMatriz.times(-1);
		
		Matrix GMatrizMenosLCMatriz = GMatriz.plus(MenosLCMatriz);
		
		EigenvalueDecomposition polos = GMatrizMenosLCMatriz.eig();
		
		return polos;
	}
	
	protected String setText(String textoAntigo, int maxCaracteres) {
		String textoNovo = "";
		
		if((textoAntigo.length() >= maxCaracteres) && (maxCaracteres != -1)){
			textoNovo = textoAntigo.substring(0, maxCaracteres);
		}else{
			textoNovo = textoAntigo;
		}
		
		return textoNovo;
	}
	
	/**
	 * @param args
	 */
	protected Object[] getItensComboLeituraEscrita() {
		Object[] io = {"Selecione", 0, 1, 2, 3, 4, 5, 6, 7};
		
		return io;
	}

	protected String conectarDesconectar(JButton botao, JFrame frame){
		return botao.getText();
	}
	
	protected boolean rotacionarIcon(ImageIcon icon, JLabel label, boolean sentidoSeta){
		int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        int type = BufferedImage.TRANSLUCENT;
        BufferedImage image = new BufferedImage(h, w, type);
        
        Graphics2D g2 = image.createGraphics();
        double x = (h - w)/2.0;
        double y = (w - h)/2.0;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
                
        if(sentidoSeta){
        	at.rotate(Math.toRadians(180), w/2.0, h/2.0);        	
        	g2.drawImage(icon.getImage(), at, label);
            g2.dispose();
            
            icon = new ImageIcon(image);        
            label.setIcon(icon);
        	
        	return false;
		}else{
			at.rotate(Math.toRadians(0), w/2.0, h/2.0);
			g2.drawImage(icon.getImage(), at, label);
	        g2.dispose();
	        
	        icon = new ImageIcon(image);        
	        label.setIcon(icon);
        	
			return true;
		}        
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