import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;


public class TelaGeral extends JFrame{
	public TelaGeral(){
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
	
	protected void calculaMatrizL(JTextField textFieldReP1, JTextField textFieldImP1, JTextField textFieldReP2, 
			JTextField textFieldImP2, JTextField textFieldL1, JTextField textFieldL21, JTextField textFieldL22) {
//		textFieldL1.setText("" + Double.parseDouble(textFieldReP1.getText())*2);
//		textFieldL21.setText("" + Double.parseDouble(textFieldReP2.getText())*2);
//		
//		double valor = Double.parseDouble(textFieldReP1.getText()) + Double.parseDouble(textFieldReP2.getText());
//		textFieldL22.setText("" + valor);
	}
	
	protected void calculaPolos(JTextField textFieldReP1, JTextField textFieldImP1, JTextField textFieldReP2, 
			JTextField textFieldImP2, JTextField textFieldL1, JTextField textFieldL21, JTextField textFieldL22) {
		
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
