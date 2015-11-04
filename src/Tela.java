import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;

import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSpinner;

import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;

import Jama.Matrix;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class Tela extends TelaGeral{
	private JFrame frame;
	
	private JMenuBar menuBar;
	
	private JLayeredPane panelGrafico1, panelGrafico2;
	
	private JPanel panelGraficos, abaOpcoesEntrada, panelTipoMalha, panelDadosSinal, panelParamsControladorMestre, panelParamsControladorEscravo, panelBombas;
	
	public JLabel IPServidor, Porta;
	
	private JLabel leitura1, leitura2, escrita;
	
	private JCheckBox chckbxTensaoSat, chckbxTensCalc, chckbxNivTanque1, chckbxNivTanque2, chckbxSetPoint, chckbxErro;
	private JCheckBox chckbxP, chckbxI, chckbxD;
	
	private JLabel lblPeriodo, lblAmplitude, lblAmplitudeMin;
	
	private JSpinner amplitudeMin, amplitude, periodo, periodoMin, offSet;
	
	private JRadioButton rdbtnAberta, rdbtnFechada;
	
	private JButton botaoAtualizar, btnReset;
	
	private JLabel labelKpMestre, labelKiMestre, labelKdMestre, labelTaltMestre, labelTaliMestre, labelTaldMestre;
	private JLabel labelKpEscravo, labelKiEscravo, labelKdEscravo, labelTaliEscravo, labelTaldEscravo, labelTaltEscravo;
	
	private JCheckBox chckbxWindUpMestre, chckbxWindUpEscravo;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboTipoOnda;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboTipoControladorMestre;
	
	private JPanel panelDadosServidor;
	private JPanel panelValores;
	
	private JLabel labelTr, labelMp, labelTp, labelTs;
	
	private JCheckBoxMenuItem zeroA100, cincoA95, dezA90;
	private JCheckBoxMenuItem doisPorcento, cincoPorcento, setePorcento, dezPorcento;
	private JCheckBoxMenuItem porcentagem, absoluto;
	
	public String onda_limpa_tanque;
	public double amplitude_limpa_tanque;
	
	private JRadioButton rdbtnTanque1, rdbtnTanque2;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboTipoControle, comboTipoControladorEscravo;
	
	private JCheckBox chckbxAoD, chckbxAcoI, chckbxAoP;
	private JCheckBox chckbxControleMestre;
	private JCheckBox chckbxErroMestre;
	private JLabel label;
	
	private Tanque thread;
	private Dados dados;
	private JPanel panelObsEstados;
	private JTextField textFieldL1;
	private JTextField textFieldL2;
	private JLabel lblColchete1;
	private JLabel lblMatrizL;
	private JLabel lblColchete2;
	private JLabel lblP1;
	private JLabel lblP2;
	private JTextField textFieldReP1;
	private JTextField textFieldReP2;
	private JTextField textFieldImP1;
	private JTextField textFieldImP2;
	private JLabel lblMais;
	private JLabel lblMenos;
	private JLabel lblI1;
	private JLabel lblI2;
	private JLabel lblNewLabel_1;
	
	private JCheckBox realizarObservacaoEstados;
	private JCheckBox chckbxNivel1Estimado;
	private JCheckBox chckbxNivel2Estimado;
	private JCheckBox chckbxErroEstNivel1;
	private JCheckBox chckbxErroEstNivel2;
	
	private JLabel lblExibirCheckSinalGrafico1, lblExibirCheckSinalGrafico2; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//Muda disgn para o disgn padrão do SO.
				try {   
				    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
				} catch (UnsupportedLookAndFeelException ex1) {  
				    ex1.printStackTrace();  
				} catch (IllegalAccessException ex2) {  
				    ex2.printStackTrace();  
				} catch (InstantiationException ex3) {  
				    ex3.printStackTrace();  
				} catch (ClassNotFoundException ex4) {  
					ex4.printStackTrace();  
				} 
				
				Tela window = new Tela();
				window.frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setLocation(0, -113);
		frame.setBounds(100, 100, 1154, 732);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		dados = new Dados();
		
		inicializarMenu();
		frame.getContentPane().add(menuBar);
		
		inicializarPainelDadosServidor();
		frame.getContentPane().add(panelDadosServidor);
		
		inicializarPainelOpcoesEntrada();
		
		inicializarPainelGraficos();
		frame.getContentPane().add(panelGraficos);
		
		inicializarBotões();
		
		inicializarPaineisParametrosSinal();
		
		inicializarPainelCheckSinaisGraficos();
		
//		redimensionarPainelGrafico2(panelGrafico1, panelGrafico2);
	}
	
	private void inicializarMenu(){
		//Cria barra de menu
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1148, 21);

		//Cria menu conexão
		JMenu mnConexao = new JMenu("Conex\u00E3o");
		menuBar.add(mnConexao);
		
		//Cria menu configuração de conexão
		JMenuItem mntmConfigurarConexao = new JMenuItem("Configurar");
		mntmConfigurarConexao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				criaPaneilDadosControle();
			}
		});
		mnConexao.add(mntmConfigurarConexao);

		//Cria menu estatísticas
		JMenu mnEstatistica = new JMenu("Estat\u00EDsticas");
		menuBar.add(mnEstatistica);

		//Cria menu Tempo de Subida (Tr)
		JMenu mnTempoDeSubida = new JMenu("Tempo de Subida (Tr)");
		mnEstatistica.add(mnTempoDeSubida);
		
		zeroA100 = new JCheckBoxMenuItem("0 - 100 %");
		mnTempoDeSubida.add(zeroA100);
		
		cincoA95 = new JCheckBoxMenuItem("5 - 95 %");
		mnTempoDeSubida.add(cincoA95);
		
		dezA90 = new JCheckBoxMenuItem("10 - 90 %");
		mnTempoDeSubida.add(dezA90);

		//Cria menu Tempo de acomodação (Ts)
		JMenu mnTempoDeAcomodao = new JMenu("Tempo de Acomoda\u00E7\u00E3o (Ts)");
		mnEstatistica.add(mnTempoDeAcomodao);
		
		doisPorcento = new JCheckBoxMenuItem("2%");
		mnTempoDeAcomodao.add(doisPorcento);
		
		cincoPorcento = new JCheckBoxMenuItem("5%");
		mnTempoDeAcomodao.add(cincoPorcento);
		
		setePorcento = new JCheckBoxMenuItem("7%");
		mnTempoDeAcomodao.add(setePorcento);
		
		dezPorcento = new JCheckBoxMenuItem("10%");
		mnTempoDeAcomodao.add(dezPorcento);
		
		//Cria menu Overshoot (Mp)
		JMenu mnOvershootmp = new JMenu("Overshoot (Mp)");
		mnEstatistica.add(mnOvershootmp);
		
		porcentagem = new JCheckBoxMenuItem("Porcentagem");
		mnOvershootmp.add(porcentagem);
		
		absoluto = new JCheckBoxMenuItem("Absoluto");
		mnOvershootmp.add(absoluto);
	}
	
	private void inicializarPaineisParametrosSinal(){	
		//Inicializando Painel Painel de exibição dos Valores
		panelValores = new JPanel();
		panelValores.setBorder(new TitledBorder(null, "Valores Atuais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelValores.setBounds(562, 633, 578, 68);
		frame.getContentPane().add(panelValores);
		panelValores.setLayout(null);
		
		JLabel lblTr = new JLabel("Tr:");
		lblTr.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTr.setBounds(10, 21, 24, 36);
		panelValores.add(lblTr);
		
		labelTr = new JLabel();
		labelTr.setBackground(Color.WHITE);
		labelTr.setBounds(38, 21, 101, 36);
		panelValores.add(labelTr);
		
		JLabel lblMp = new JLabel("Mp:");
		lblMp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMp.setBounds(291, 21, 30, 36);
		panelValores.add(lblMp);
		
		labelMp = new JLabel();
		labelMp.setBackground(Color.WHITE);
		labelMp.setBounds(325, 21, 101, 36);
		panelValores.add(labelMp);
		
		JLabel lblTp = new JLabel("Tp:");
		lblTp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTp.setBounds(149, 21, 28, 36);
		panelValores.add(lblTp);
		
		labelTp = new JLabel();
		labelTp.setBounds(180, 21, 101, 36);
		panelValores.add(labelTp);
		
		JLabel lblTs = new JLabel("Ts:");
		lblTs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTs.setBounds(436, 21, 28, 36);
		panelValores.add(lblTs);
		
		labelTs = new JLabel();
		labelTs.setBounds(467, 21, 101, 36);
		panelValores.add(labelTs);		
	}	
	
	private void inicializarPainelDadosServidor(){
		panelDadosServidor = new JPanel();
		panelDadosServidor.setBounds(10, 22, 327, 73);
		panelDadosServidor.setBorder(new TitledBorder(null, "Dados do Servidor", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelDadosServidor.setLayout(null);
		
		JLabel lblIPServidor = new JLabel("IP:");
		lblIPServidor.setBounds(10, 17, 14, 16);
		panelDadosServidor.add(lblIPServidor);
		
		IPServidor = new JLabel();
		IPServidor.setBounds(28, 17, 65, 16);
		panelDadosServidor.add(IPServidor);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(10, 45, 36, 16);
		panelDadosServidor.add(lblPorta);
		
		Porta = new JLabel();
		Porta.setBounds(43, 45, 50, 16);
		panelDadosServidor.add(Porta);
		
		final JButton btnConectarDesconectar = new JButton("Conectar");
		btnConectarDesconectar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269447_gtk-apply.png")));
		btnConectarDesconectar.setForeground(new Color(0, 128, 0));
		btnConectarDesconectar.setBackground(new Color(0, 128, 0));
		btnConectarDesconectar.setBounds(196, 37, 119, 25);
		btnConectarDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(IPServidor.getText().equals("") || Porta.getText().equals("")){
					JOptionPane.showMessageDialog(frame, "                          Conexão não Realizada! " +
							"\nInforme o Ip do Servidor e/ou a Porta e tente novamente.");
				}else{
					thread = new Tanque();
					thread.setServer(IPServidor.getText(), Integer.parseInt(Porta.getText()));
					
					//Muda nome (conectar ou desconectar) e cor (verde ou vermelho) do botão.
					//Também desabilita alguns componentes da tela.			
					if(btnConectarDesconectar.getText().equals("Conectar")){
						JOptionPane.showMessageDialog(frame, "Conexão Realizada com Sucesso!");									
						
						btnConectarDesconectar.setForeground(Color.RED);
						btnConectarDesconectar.setBackground(Color.RED);
						btnConectarDesconectar.setText("Desconectar");
						btnConectarDesconectar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269745_gtk-dialog-error.png")));
						mudarPropriedadesBotoes("Conectar");
					}else{
						
						btnConectarDesconectar.setForeground(new Color(0, 128, 0));
						btnConectarDesconectar.setBackground(new Color(0, 128, 0));
						btnConectarDesconectar.setText("Conectar");
						btnConectarDesconectar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269447_gtk-apply.png")));
						mudarPropriedadesBotoes("Desconectar");
					}
				}
			}
		});
		panelDadosServidor.add(btnConectarDesconectar);
		
		//Inicializa dados de entrada/saída
		JLabel lblLeitura1 = new JLabel("Leitura 1:");
		lblLeitura1.setBounds(112, 17, 50, 16);
		panelDadosServidor.add(lblLeitura1);
		
		leitura1 = new JLabel();
		leitura1.setBounds(172, 17, 36, 16);
		panelDadosServidor.add(leitura1);
		
		JLabel lblLeitura2 = new JLabel("Leitura 2:");
		lblLeitura2.setBounds(112, 45, 50, 16);
		panelDadosServidor.add(lblLeitura2);
		
		leitura2 = new JLabel();
		leitura2.setBounds(172, 45, 36, 16);
		panelDadosServidor.add(leitura2);
		
		JLabel lblEscrita = new JLabel("Escrita:");
		lblEscrita.setBounds(235, 18, 36, 14);
		panelDadosServidor.add(lblEscrita);
		
		escrita = new JLabel();
		escrita.setBounds(281, 17, 36, 16);
		panelDadosServidor.add(escrita);
	}
	
	private void inicializarBotões(){
		botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(391, 635, 101, 23);
		botaoAtualizar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269378_gtk-refresh.png")));		
		botaoAtualizar.setEnabled(false);
		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				dados = new Dados();					
				
				if(validaPoupulaTudoNaDados()){				
					
					// Seta na classe dados o tipo de controle
					populaTipoControleNaDados();
					
					// Seta na classe dados os checkBox dos gráficos que serão exibidos
					populaCheckDosGraficosNaDados();
					
					// Seta os dados parâmetros desejados para a resposta na classe dados					
					populaParamsRespostaNaDados();
					
					// Seta os parâmetros do observador de estados na classe dados
					populaParamsObservadorEstadosNaDados();
					
			
					
					dados.setTanque1(rdbtnTanque1.isSelected());
					dados.setTanque2(rdbtnTanque2.isSelected());
					dados.setObservando(realizarObservacaoEstados.isSelected());
					
					if(chckbxWindUpMestre.isSelected()){
						dados.setTt(Double.parseDouble(labelTaltMestre.getText()));
					}
					dados.setWindUP(chckbxWindUpMestre.isSelected());
					
					if(chckbxWindUpEscravo.isSelected()){
						dados.setTtEscravo((Double.parseDouble(labelTaltEscravo.getText())));
					}
					dados.setWindUpEscravo(chckbxWindUpEscravo.isSelected());					
					
					//grafico
					thread.setDados(dados);
					thread.setDadosGrafico(dados);
					if (!thread.isAlive()) {		
						thread.setPainelTensao(panelGrafico1);
						thread.setPainelAltura(panelGrafico2);
						thread.start();
					}
				}
			}
		});
		frame.getContentPane().add(botaoAtualizar);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(391, 670, 101, 23);
		btnReset.setEnabled(false);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//onda_limpa_tanque = "Degrau";
				//amplitude_limpa_tanque = 0;
				
				thread.limparTela();
				dados.setTanque_Seco(false);
			}
		});
		frame.getContentPane().add(btnReset);
	}
	
	private void populaTipoControleNaDados(){
		if(comboTipoControle.getSelectedItem().equals("Cascata")){
			dados.setTipoDeControle("Cascata");
		}else if(comboTipoControle.getSelectedItem().equals("Simples")){
			dados.setTipoDeControle("Simples");
		}else{
			dados.setTipoDeControle("Sem Controle");
		}
	}
	
	private void populaCheckDosGraficosNaDados(){
		// Setar na dados os checkBox dos gráficos
		dados.setTensao(chckbxTensCalc.isSelected());
		dados.setTensaoSat(chckbxTensaoSat.isSelected());
		dados.setSetPoint(chckbxSetPoint.isSelected());
		dados.setErroMesmo(chckbxErro.isSelected());
		
		dados.setProporcional(chckbxP.isSelected());
		dados.setIntegral(chckbxI.isSelected());
		dados.setDerivativo(chckbxD.isSelected());
		dados.setNivel1(chckbxNivTanque1.isSelected());
		dados.setNivel2(chckbxNivTanque2.isSelected());
		
		dados.setNvlUmEstimado(chckbxNivel1Estimado.isSelected());
		dados.setNvlDoisEstimado(chckbxNivel2Estimado.isSelected());
		dados.setErroEstimacaoUm(chckbxErroEstNivel1.isSelected());
		dados.setErroEstimacaoDois(chckbxErroEstNivel2.isSelected());
		
		dados.setProporcional_c2(chckbxAoP.isSelected());
		dados.setIntegral_c2(chckbxAcoI.isSelected());
		dados.setDerivativo_c2(chckbxAoD.isSelected());
		dados.setSinalCascata(chckbxControleMestre.isSelected());
		dados.setErro_c1(chckbxErroMestre.isSelected());
	}
	
	private void populaParamsRespostaNaDados(){
		dados.settPico(labelTp);
		dados.settAcomoda(labelTs);
		dados.settSubida(labelTr);
		dados.setNivelPico(labelMp);
		
		dados.setPicoAbs(absoluto.isSelected());
		
		if (zeroA100.isSelected()){
			dados.setFatInf(0);
			dados.setFatSup(1);
		}else if (cincoA95.isSelected()){
			dados.setFatInf(0.05);
			dados.setFatSup(0.95);
		}else if (dezA90.isSelected()){
			dados.setFatInf(0.1);
			dados.setFatSup(0.9);
		}
		
		if(doisPorcento.isSelected()){
			dados.setFaixa2(true);
		}else if(cincoPorcento.isSelected()){
			dados.setFaixa5(true);
		}else if(setePorcento.isSelected()){
			dados.setFaixa7(true);			
		}else if(dezPorcento.isSelected()){
			dados.setFaixa10(true);	
		}
	}
	
	private void populaParamsObservadorEstadosNaDados(){
		if(!textFieldReP1.getText().equals(""))
			dados.setParteReP1(Double.parseDouble(textFieldReP1.getText()));
		
		if(!textFieldImP1.getText().equals(""))
			dados.setParteImP1(Double.parseDouble(textFieldImP1.getText()));
		
		if(!textFieldReP2.getText().equals(""))
			dados.setParteReP2(Double.parseDouble(textFieldReP2.getText()));
		
		if(!textFieldImP2.getText().equals(""))
			dados.setParteImP2(Double.parseDouble(textFieldImP2.getText()));
		
		if(!textFieldL1.getText().equals(""))
			dados.setL1(Double.parseDouble(textFieldL1.getText()));
		
		if(!textFieldL2.getText().equals(""))
			dados.setL2(Double.parseDouble(textFieldL2.getText()));
	}
	
	private boolean validaPoupulaTudoNaDados(){
		boolean sucesso = false;
		
		//Validando painel de dados de entradas e saídas		
		sucesso = validaDadosDeIO();

		//Validando painel de escolha do tipo de malha
		sucesso = sucesso && validaTipoMalha();
		
		//Validando painel de escolha do tanque		
		sucesso = sucesso && validaTanque();

		//Validando painel de escolha do tipo de onda
		sucesso = sucesso && validaOnda();
		
		sucesso = sucesso && validaTipoControle();

		//Validando painel de escolha dos parametros do controlador mestre
		if(comboTipoControle.getSelectedItem().equals("Simples") || comboTipoControle.getSelectedItem().equals("Cascata"))
			sucesso = sucesso && validaParamsControladorMestre(comboTipoControladorMestre, labelKpEscravo, labelKiEscravo, labelKdEscravo, labelTaltEscravo, labelTaliEscravo, labelTaldEscravo);

		//Validando painel de escolha dos parametros do controlador mestre
		if(comboTipoControle.getSelectedItem().equals("Cascata"))
			sucesso = sucesso && validaParamsControladorEscravo(comboTipoControladorEscravo, labelKpMestre, labelKiMestre, labelKdMestre, labelTaltMestre, labelTaliMestre, labelTaldMestre);

		return sucesso;
	}
	
	private boolean validaTipoControle(){
		if(comboTipoControle.equals("Selecione")){
			JOptionPane.showMessageDialog(frame, "Informe o tipo de controle.");
		
			return false;
		}else{
			dados.setTipoDeControle(comboTipoControle.getSelectedItem().toString());
		}
		
		return true;
	}
	
	/** 
	 * Valida o tanque que será controlado. Depois, Popula os parâmetros de Leitura e Escrita na classe Dados.
	 */
	public boolean validaTanque(){
		if(!rdbtnTanque1.isSelected() && !rdbtnTanque2.isSelected()){
			JOptionPane.showMessageDialog(frame, "Informe o tanque que você deseja controlar.");
		
			return false;
		}
		
		if(rdbtnTanque1.isSelected()){		
			dados.setPinoDeLeitura1(0);
		
			return true;
		}
		
		if(rdbtnTanque2.isSelected()){
			dados.setPinoDeLeitura2(1);
			
			return true;
		}
		
		return true;
	}	

	/** 
	 * Valida campos e Popula os parâmetros de Leitura(1 e 2) e Escrita na classe Dados.
	 */
	public boolean validaDadosDeIO(){			
		if(leitura1.getText().equals("")){
				JOptionPane.showMessageDialog(frame, "Informe o porta de Leitura 1.");
			dados.setPinoDeLeitura1((int)Integer.parseInt(leitura1.getText()));
			
			return false;
		}else{
			dados.setPinoDeLeitura1((int)Integer.parseInt(leitura1.getText()));
		}
		
		if(leitura2.getText().equals("")){		
			JOptionPane.showMessageDialog(frame, "Informe o porta de Leitura 2.");
			dados.setPinoDeLeitura2((int)Integer.parseInt(leitura2.getText()));
			
			return false;
		}else{
			dados.setPinoDeLeitura2((int)Integer.parseInt(leitura2.getText()));
		}
			
		if(escrita.getText().equals("")){
			JOptionPane.showMessageDialog(frame, "Informe a porta de Escrita.");
			return false;
		}else{
			dados.setPinoDeEscrita((int)Integer.parseInt(escrita.getText()));
		}
		
		return true;
	}
	
	/** 
	 * Valida campos e Popula o tipo de malha na classe Dados.
	 */
	public boolean validaTipoMalha(){
		if(!rdbtnAberta.isSelected() && !rdbtnFechada.isSelected()){
			JOptionPane.showMessageDialog(frame, "Informe o Tipo de Malha.");
			
			return false;
		}else{
			dados.setTipoMalha(rdbtnAberta.isSelected() ? "Malha Aberta" : "Malha Fechada");
		}
		
		return true;
	}
	
	/** 
	 * Valida campos e Popula os parâmetros do sinal na classe Dados.
	 */
	public boolean validaOnda(){
		
		if(comboTipoOnda.getSelectedIndex() == 0){
			JOptionPane.showMessageDialog(frame, "Informe o Tipo de Onda.");
			
			return false;
		}else {
			dados.setTipoSinal(comboTipoOnda.getSelectedItem().toString());
			
			if(amplitude.getValue().equals("")){
				String amplitude = comboTipoOnda.getSelectedItem().equals("Degrau") ? "Amplitude (Máx)" : "Amplitude";  

				JOptionPane.showMessageDialog(frame, "Informe a " + amplitude + " do sinal.");
				
				return false;
			}else{
				dados.setAmplitude((double)amplitude.getValue());
			}
			
			if(comboTipoOnda.getSelectedItem().equals("Quadrada") || 
					comboTipoOnda.getSelectedItem().equals("Senoidal") || comboTipoOnda.getSelectedItem().equals("Dente de Serra")){
				
				if(periodo.getValue().equals("")){
					JOptionPane.showMessageDialog(frame, "Informe o Período do sinal.");
					
					return false;
				}else{
					dados.setPeriodo((double)periodo.getValue());
				}
				
				if(offSet.getValue().equals("")){
					JOptionPane.showMessageDialog(frame, "Informe o OffSet do sinal.");
					
					return false;
				}else{
					dados.setOffset((double)offSet.getValue());
				}
			}
			
			if(comboTipoOnda.getSelectedItem().equals("Aleatória")){
				
				if(amplitudeMin.getValue().equals("")){
					JOptionPane.showMessageDialog(frame, "Informe a Amplitude (Mín) do sinal.");
				
					return false;
				}else{
					dados.setAmplitudeMinima(((double) amplitudeMin.getValue()));
				}

				if(periodo.getValue().equals("")){
					JOptionPane.showMessageDialog(frame, "Informe o Período do sinal.");
				
					return false;
				}else{
					dados.setPeriodo((double)periodo.getValue());
				}
				
				if(periodoMin.getValue().equals("")){
					JOptionPane.showMessageDialog(frame, "Informe o Período (Mín) do sinal.");
					
					return false;
				}else{
					dados.setPeriodoMinino((double) periodoMin.getValue());
				}
			}
		}
		
		return true;
	}
		
	@SuppressWarnings("rawtypes")
	private boolean validaParamsControladorMestre(JComboBox comboTipoControlador, JLabel labelKp, JLabel labelKi, JLabel labelKd, JLabel labelTalT, JLabel labelTalI, JLabel labelTalD){
		if(rdbtnFechada.isSelected()){
			if(comboTipoControlador.getSelectedIndex() == 0){
				JOptionPane.showMessageDialog(frame, "Informe o tipo de controlador do mestre!");
				
				return false;
			}else{
				dados.setTipoDeControlador(comboTipoControlador.getSelectedItem().toString());
				
				if(labelKpMestre.getText().equals("")){
					JOptionPane.showMessageDialog(frame, "Informe o valor de Kp do mestre.");
					
					return false;
				}else{
					dados.setKP(Double.parseDouble(labelKpMestre.getText()));
					
					if((comboTipoControlador.getSelectedItem().equals("PI") || comboTipoControlador.getSelectedItem().equals("PID") || comboTipoControlador.getSelectedItem().equals("PI-D"))
							&& (labelKiMestre.getText().equals("") || labelTaliMestre.getText().equals(""))){
						
						JOptionPane.showMessageDialog(frame, "Informe todos os parâmetros do controlador integrativo (Ki e Ti) do mestre.");
						
						return false;
					}else if((comboTipoControlador.getSelectedItem().equals("PI") || comboTipoControlador.getSelectedItem().equals("PID") || comboTipoControlador.getSelectedItem().equals("PI-D"))
							&& !(labelKiMestre.getText().equals("") || labelTaliMestre.getText().equals(""))){
				
						dados.setKI(Double.parseDouble(labelKiMestre.getText()));
					}
					
					if((comboTipoControlador.getSelectedItem().equals("PD") || comboTipoControlador.getSelectedItem().equals("PID") || comboTipoControlador.getSelectedItem().equals("PI-D"))
							&& (labelKdMestre.getText().equals("") || labelTaldMestre.getText().equals(""))){
						
						JOptionPane.showMessageDialog(frame, "Informe todos os parâmetros do controlador derivativo (Kd e Td) do mestre.");
						
						return false;
					}else if((comboTipoControlador.getSelectedItem().equals("PD") || comboTipoControlador.getSelectedItem().equals("PID") || comboTipoControlador.getSelectedItem().equals("PI-D"))
							&& !(labelKdMestre.getText().equals("") || labelTaldMestre.getText().equals(""))){
						
						dados.setKD(Double.parseDouble(labelKdMestre.getText()));
					}
				}
			}
		}
		
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	private boolean validaParamsControladorEscravo(JComboBox comboTipoControlador, JLabel labelKp, JLabel labelKi, JLabel labelKd, JLabel labelTalT, JLabel labelTalI, JLabel labelTalD){
		if(rdbtnFechada.isSelected()){
			if(comboTipoControlador.getSelectedIndex() == 0){
				JOptionPane.showMessageDialog(frame, "Informe o tipo de controlador do escravo!");
				
				return false;
			}else{
				dados.setTipoDeControladorEscravo(comboTipoControlador.getSelectedItem().toString());
				
				if(labelKpEscravo.getText().equals("")){
					JOptionPane.showMessageDialog(frame, "Informe o valor de Kp do escravo.");
					
					return false;
				}else{
					dados.setKpEscravo(Double.parseDouble(labelKpEscravo.getText()));
					
					if((comboTipoControlador.getSelectedItem().equals("PI") || comboTipoControlador.getSelectedItem().equals("PID") || comboTipoControlador.getSelectedItem().equals("PI-D"))
							&& (labelKiEscravo.getText().equals("") || labelTaliEscravo.getText().equals(""))){
						
						JOptionPane.showMessageDialog(frame, "Informe todos os parâmetros do controlador integrativo (Ki e Ti) do escravo.");
						
						return false;
					}else if((comboTipoControlador.getSelectedItem().equals("PI") || comboTipoControlador.getSelectedItem().equals("PID") || comboTipoControlador.getSelectedItem().equals("PI-D"))
							&& !(labelKiEscravo.getText().equals("") || labelTaliEscravo.getText().equals(""))){
				
						dados.setKiEscravo(Double.parseDouble(labelKiEscravo.getText()));
					}
					
					if((comboTipoControlador.getSelectedItem().equals("PD") || comboTipoControlador.getSelectedItem().equals("PID") || comboTipoControlador.getSelectedItem().equals("PI-D"))
							&& (labelKdEscravo.getText().equals("") || labelTaldEscravo.getText().equals(""))){
						
						JOptionPane.showMessageDialog(frame, "Informe todos os parâmetros do controlador derivativo (Kd e Td) do escravo.");
						
						return false;
					}else if((comboTipoControlador.getSelectedItem().equals("PD") || comboTipoControlador.getSelectedItem().equals("PID") || comboTipoControlador.getSelectedItem().equals("PI-D"))
							&& !(labelKdEscravo.getText().equals("") || labelTaldEscravo.getText().equals(""))){
						
						dados.setKdEscravo(Double.parseDouble(labelKdEscravo.getText()));
					}
				}
			}
		}
		
		return true;
	}
	
	private void inicializePainelOpcoesTanque(){
		panelBombas = new JPanel();
		panelBombas.setBounds(161, 4, 153, 51);
		panelBombas.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Opções de Tanque", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panelBombas.setLayout(null);
		
		rdbtnTanque1 = new JRadioButton("Tanque 1");
		rdbtnTanque1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnTanque2.setSelected(false);
				
				realizarObservacaoEstados.setEnabled(false);
				realizarObservacaoEstados.setSelected(false);
				
				textFieldReP1.setEnabled(false);
				textFieldReP1.setText("");
				
				textFieldReP2.setEnabled(false);
				textFieldReP2.setText("");
				
				textFieldImP1.setEnabled(false);
				textFieldImP1.setText("");
				
				textFieldImP2.setEnabled(false);
				textFieldImP2.setText("");
				
				textFieldL1.setEnabled(false);
				textFieldL1.setText("");
				
				textFieldL2.setEnabled(false);
				textFieldL2.setText("");
			}
		});
		rdbtnTanque1.setEnabled(false);
		rdbtnTanque1.setBounds(6, 24, 71, 18);
		panelBombas.add(rdbtnTanque1);
		
		rdbtnTanque2 = new JRadioButton("Tanque 2");
		rdbtnTanque2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnTanque1.setSelected(false);
				
				if(rdbtnFechada.isSelected())
					realizarObservacaoEstados.setEnabled(true);
			}
		});
		rdbtnTanque2.setEnabled(false);
		rdbtnTanque2.setBounds(79, 24, 71, 18);
		panelBombas.add(rdbtnTanque2);
	}
	
	private void inicializarPainelOpcoesEntrada(){
		JTabbedPane abas = new JTabbedPane(JTabbedPane.TOP);
		abas.setBounds(10, 96, 327, 605);
		frame.getContentPane().add(abas);
		
		abaOpcoesEntrada = new JPanel();
		abas.addTab("Opções de Entrada", null, abaOpcoesEntrada, null);
		abaOpcoesEntrada.setLayout(null);
		
		inicializePainelOpcoesTanque();
		abaOpcoesEntrada.add(panelBombas);
				
		inicializarPainelTiposMalha();
		abaOpcoesEntrada.add(panelTipoMalha);
		
		inicializarPainelDadosSinal();
		abaOpcoesEntrada.add(panelDadosSinal);
		
		inicializarPainelParamsControlador();
		
		inicializarOutrosComponentesPainelPrincipal();
		
		label = new JLabel("");
		label.setToolTipText("Para Atualizar os parametros, basta clicar nos campos.");
		label.setBounds(282, 11, 24, 26);
		panelParamsControladorEscravo.add(label);	
		
		panelObsEstados = new JPanel();
		panelObsEstados.setBorder(new TitledBorder(null, "Observador de Estados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelObsEstados.setBounds(5, 474, 314, 100);
		abaOpcoesEntrada.add(panelObsEstados);
		panelObsEstados.setLayout(null);
		
		textFieldL1 = new JTextField();
		textFieldL1.setEnabled(false);
		textFieldL1.setBounds(245, 35, 46, 16);
		panelObsEstados.add(textFieldL1);
		textFieldL1.setColumns(10);
		
		textFieldL2 = new JTextField();
		textFieldL2.setEnabled(false);
		textFieldL2.setBounds(245, 65, 46, 16);
		panelObsEstados.add(textFieldL2);
		textFieldL2.setColumns(10);
		
		lblColchete1 = new JLabel("[");
		lblColchete1.setForeground(Color.GRAY);
		lblColchete1.setFont(new Font("Calibri Light", Font.PLAIN, 85));
		lblColchete1.setBounds(221, -12, 35, 138);
		panelObsEstados.add(lblColchete1);
		
		lblMatrizL = new JLabel("L = ");
		lblMatrizL.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMatrizL.setBounds(208, 50, 23, 14);
		panelObsEstados.add(lblMatrizL);
		
		lblColchete2 = new JLabel("]");
		lblColchete2.setForeground(Color.GRAY);
		lblColchete2.setFont(new Font("Calibri Light", Font.PLAIN, 85));
		lblColchete2.setBounds(290, -12, 35, 138);
		panelObsEstados.add(lblColchete2);
		
		lblP1 = new JLabel("P1 = ");
		lblP1.setBounds(10, 50, 28, 14);
		panelObsEstados.add(lblP1);
		
		lblP2 = new JLabel("P2 = ");
		lblP2.setBounds(10, 70, 28, 14);
		panelObsEstados.add(lblP2);
		
		textFieldReP1 = new JTextField();
		textFieldReP1.setEnabled(false);
		textFieldReP1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				textFieldReP2.setText(textFieldReP1.getText());
			}
		});
		textFieldReP1.setToolTipText("Parte real do polo 1");
		textFieldReP1.setBounds(36, 48, 46, 16);
		panelObsEstados.add(textFieldReP1);
		textFieldReP1.setColumns(10);
		
		textFieldReP2 = new JTextField();
		textFieldReP2.setEnabled(false);
		textFieldReP2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				textFieldReP1.setText(textFieldReP2.getText());
			}
		});
		textFieldReP2.setToolTipText("Parte real do polo 2");
		textFieldReP2.setColumns(10);
		textFieldReP2.setBounds(36, 68, 46, 16);
		panelObsEstados.add(textFieldReP2);
		
		textFieldImP1 = new JTextField();
		textFieldImP1.setEnabled(false);
		textFieldImP1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				textFieldImP2.setText(textFieldImP1.getText());
			}
		});
		textFieldImP1.setToolTipText("Parte imagin\u00E1ria do polo 1");
		textFieldImP1.setColumns(10);
		textFieldImP1.setBounds(99, 48, 46, 16);
		panelObsEstados.add(textFieldImP1);
		
		textFieldImP2 = new JTextField();
		textFieldImP2.setEnabled(false);
		textFieldImP2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				textFieldImP1.setText(textFieldImP2.getText());
			}
		});
		textFieldImP2.setToolTipText("Parte imagin\u00E1ria do polo 2");
		textFieldImP2.setColumns(10);
		textFieldImP2.setBounds(99, 68, 46, 16);
		panelObsEstados.add(textFieldImP2);
		
		lblMais = new JLabel("+");
		lblMais.setBounds(86, 49, 13, 15);
		panelObsEstados.add(lblMais);
		
		lblMenos = new JLabel("-");
		lblMenos.setBounds(88, 69, 13, 15);
		panelObsEstados.add(lblMenos);
		
		lblI1 = new JLabel("i");
		lblI1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblI1.setBounds(150, 49, 13, 15);
		panelObsEstados.add(lblI1);
		
		lblI2 = new JLabel("i");
		lblI2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblI2.setBounds(150, 69, 13, 15);
		panelObsEstados.add(lblI2);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!textFieldReP1.getText().equals("") && !textFieldImP1.getText().equals("") 
						&& !textFieldReP2.getText().equals("") && !textFieldImP2.getText().equals("")){
					
					Matrix matrizL = calculaMatrizL(textFieldReP1, textFieldImP1);
					
					textFieldL1.setText(matrizL.get(0, 0) + "");
					textFieldL2.setText(matrizL.get(1, 0) + "");
				}else if(!textFieldL1.getText().equals("") && !textFieldL2.getText().equals("")){
					
					double[] polos = calculaPolos(textFieldL1, textFieldL2);
					
					textFieldReP1.setText(polos[0] + "");
					textFieldImP1.setText(polos[1] + "");
					textFieldReP1.setText(polos[2] + "");
					textFieldImP1.setText(polos[3] + "");
				}else{
					JOptionPane.showMessageDialog(frame, "Informe os parâmetros do observador de estados!");
				}
			}
		});
		lblNewLabel_1.setToolTipText("Clique para atualizar os par\u00E2metros");
		lblNewLabel_1.setIcon(new ImageIcon(Tela.class.getResource("/Icons/10885_32x32.png")));
		lblNewLabel_1.setBounds(165, 50, 30, 30);
		panelObsEstados.add(lblNewLabel_1);
		
		realizarObservacaoEstados = new JCheckBox("Realizar Observa\u00E7\u00E3o de Estados");
		realizarObservacaoEstados.setEnabled(false);
		realizarObservacaoEstados.setBounds(10, 17, 185, 23);
		realizarObservacaoEstados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(realizarObservacaoEstados.isSelected()){
					textFieldReP1.setEnabled(true);
					textFieldReP2.setEnabled(true);
					
					textFieldImP1.setEnabled(true);
					textFieldImP2.setEnabled(true);
					
					textFieldL1.setEnabled(true);
					textFieldL2.setEnabled(true);
			
					if(comboTipoControle.getSelectedIndex() != 0 && comboTipoControle.getSelectedIndex() != 3){
						comboTipoControladorMestre.setSelectedIndex(1);
						comboTipoControladorMestre.setEnabled(false);
						
						if(comboTipoControle.getSelectedIndex() == 2){
							comboTipoControladorEscravo.setSelectedIndex(1);
							comboTipoControladorEscravo.setEnabled(false);
						}
					}
				}else{
					textFieldReP1.setEnabled(false);
					textFieldReP1.setText("");
					
					textFieldReP2.setEnabled(false);
					textFieldReP2.setText("");
					
					textFieldImP1.setEnabled(false);
					textFieldImP1.setText("");
					
					textFieldImP2.setEnabled(false);
					textFieldImP2.setText("");
					
					textFieldL1.setEnabled(false);
					textFieldL1.setText("");
					
					textFieldL2.setEnabled(false);
					textFieldL2.setText("");
					
					comboTipoControladorMestre.setSelectedIndex(0);
					if(comboTipoControle.getSelectedIndex() == 1 || comboTipoControle.getSelectedIndex() == 2)
						comboTipoControladorMestre.setEnabled(true);
					
					if(comboTipoControle.getSelectedIndex() == 2){
						comboTipoControladorEscravo.setSelectedIndex(0);
						comboTipoControladorEscravo.setEnabled(true);
					}else{
						comboTipoControladorEscravo.setEnabled(false);
					}
					
				}
			}
		});
		panelObsEstados.add(realizarObservacaoEstados);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void inicializarComboTipoOnda(){
		JLabel lblTipoOnda = new JLabel("Tipo de Onda:");
		lblTipoOnda.setBounds(47, 57, 78, 18);
		abaOpcoesEntrada.add(lblTipoOnda);
		
		comboTipoOnda = new JComboBox(getItensComboTiposOnda());
		comboTipoOnda.setEnabled(false);
		comboTipoOnda.setBounds(126, 57, 151, 18);
		comboTipoOnda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboTipoOnda.getSelectedItem().equals("Selecione")){
					lblAmplitude.setText("Amplitude:");	
					amplitude.setEnabled(false);
					amplitudeMin.setEnabled(false);
					amplitudeMin.setValue(0);
					lblPeriodo.setText("Período:");
					periodo.setEnabled(false);
					periodo.setValue(0);
					periodoMin.setEnabled(false);
					periodoMin.setValue(0);
					offSet.setEnabled(false);
					offSet.setValue(0);
				}else if(comboTipoOnda.getSelectedItem().equals("Quadrada")){
					lblAmplitude.setText("Amplitude:");
					amplitude.setEnabled(true);
					amplitudeMin.setEnabled(false);
					amplitudeMin.setValue(0);
					lblPeriodo.setText("Período:");
					periodo.setEnabled(true);
					periodoMin.setEnabled(false);
					periodoMin.setValue(0);
					offSet.setEnabled(true);
				}else if(comboTipoOnda.getSelectedItem().equals("Degrau")){
					lblAmplitude.setText("Amplitude:");	
					amplitude.setEnabled(true);
					amplitudeMin.setEnabled(false);
					amplitudeMin.setValue(0);
					lblPeriodo.setText("Período:");
					periodo.setEnabled(false);
					periodo.setValue(0);
					periodoMin.setEnabled(false);
					periodoMin.setValue(0);
					offSet.setEnabled(false);
					offSet.setValue(0);
				}else if(comboTipoOnda.getSelectedItem().equals("Aleatória")){
					lblAmplitude.setText("Amplitude (Máx):");
					amplitude.setEnabled(true);
					amplitudeMin.setEnabled(true);
					lblPeriodo.setText("Período (Máx):");
					periodo.setEnabled(true);
					periodoMin.setEnabled(true);
					offSet.setEnabled(false);
					offSet.setValue(0);
				}else if(comboTipoOnda.getSelectedItem().equals("Senoidal")){
					lblAmplitude.setText("Amplitude:");	
					amplitude.setEnabled(true);
					amplitudeMin.setEnabled(false);
					amplitudeMin.setValue(0);
					lblPeriodo.setText("Período:");
					periodo.setEnabled(true);
					periodoMin.setEnabled(false);
					periodoMin.setValue(0);
					offSet.setEnabled(true);
				}else{
					lblAmplitude.setText("Amplitude:");
					amplitude.setEnabled(true);
					amplitudeMin.setEnabled(false);
					amplitudeMin.setValue(0);
					lblPeriodo.setText("Período:");
					periodo.setEnabled(true);
					periodoMin.setEnabled(false);
					periodoMin.setValue(0);
					offSet.setEnabled(true);	
				}
			}
		});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void inicializarComboTipoControle(){
		JLabel lblTipoDeControle = new JLabel("Tipo de Controle:");
		lblTipoDeControle.setBounds(46, 180, 91, 18);
		abaOpcoesEntrada.add(lblTipoDeControle);
		
		comboTipoControle = new JComboBox(getItensComboTiposControle());
		comboTipoControle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboTipoControle.getSelectedItem().equals("Cascata") && !realizarObservacaoEstados.isSelected()){
					comboTipoControladorMestre.setEnabled(true);
					comboTipoControladorEscravo.setEnabled(true);
					
					dados.setTipoDeControle("Cascata");
				}else if(comboTipoControle.getSelectedItem().equals("Cascata") && realizarObservacaoEstados.isSelected()){
					comboTipoControladorMestre.setEnabled(false);
					comboTipoControladorEscravo.setEnabled(false);
					
					comboTipoControladorMestre.setSelectedIndex(1);
					comboTipoControladorEscravo.setSelectedIndex(1);
					
					dados.setTipoDeControle("Cascata");
				}else if(comboTipoControle.getSelectedItem().equals("Simples") && !realizarObservacaoEstados.isSelected()){					
					comboTipoControladorMestre.setEnabled(true);
					comboTipoControladorEscravo.setEnabled(false);
					
					comboTipoControladorEscravo.setSelectedIndex(0);

					chckbxWindUpEscravo.setSelected(false);
					
					dados.setTipoDeControle("Simples");
					
					desabilitarParamsControladorEscravo(true, true);
				}else if(comboTipoControle.getSelectedItem().equals("Simples") && realizarObservacaoEstados.isSelected()){
					comboTipoControladorMestre.setEnabled(false);
					comboTipoControladorEscravo.setEnabled(false);
					
					comboTipoControladorMestre.setSelectedIndex(1);
					comboTipoControladorEscravo.setSelectedIndex(0);
					
					chckbxWindUpEscravo.setSelected(false);
					
					dados.setTipoDeControle("Simples");
					
					desabilitarParamsControladorEscravo(true, true);
				}else{
					comboTipoControladorMestre.setEnabled(false);
					comboTipoControladorMestre.setSelectedIndex(0);
					comboTipoControladorEscravo.setEnabled(false);
					comboTipoControladorEscravo.setSelectedIndex(0);
					dados.setTipoDeControle("Sem Controle");
					
					chckbxWindUpMestre.setSelected(false);
					chckbxWindUpEscravo.setSelected(false);
					
					desabilitarParamsControladorMestre(true, true);
					desabilitarParamsControladorEscravo(true, true);
				}
				
				if(!realizarObservacaoEstados.isSelected()){
					
				}
			}
		});
		comboTipoControle.setEnabled(false);
		comboTipoControle.setBounds(147, 180, 151, 18);
	}
		
	private void inicializarPainelTiposMalha(){
		panelTipoMalha = new JPanel();
		panelTipoMalha.setBounds(5, 4, 153, 51);
		panelTipoMalha.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Malha", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panelTipoMalha.setLayout(null);	
				
		rdbtnAberta = new JRadioButton("Aberta");
		rdbtnAberta.setEnabled(false);
		rdbtnAberta.setBounds(6, 24, 67, 18);		
		rdbtnAberta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnFechada.setSelected(false);
				
				comboTipoControladorMestre.setEnabled(false);
				comboTipoControladorMestre.setSelectedIndex(0);
				
//				chckbxWindUpMestre.setEnabled(false);
				chckbxWindUpMestre.setSelected(false);
				
//				chckbxWindUpEscravo.setEnabled(false);
				chckbxWindUpEscravo.setSelected(false);
				
				desabilitarParamsControladorMestre(true, true);
				desabilitarParamsControladorEscravo(true, true);
				
				realizarObservacaoEstados.setEnabled(false);
				realizarObservacaoEstados.setSelected(false);
				
				textFieldReP1.setEnabled(false);
				textFieldReP1.setText("");
				
				textFieldReP2.setEnabled(false);
				textFieldReP2.setText("");
				
				textFieldImP1.setEnabled(false);
				textFieldImP1.setText("");
				
				textFieldImP2.setEnabled(false);
				textFieldImP2.setText("");
				
				textFieldL1.setEnabled(false);
				textFieldL1.setText("");
				
				textFieldL2.setEnabled(false);
				textFieldL2.setText("");
			}
		});
		panelTipoMalha.add(rdbtnAberta);
		
		rdbtnFechada = new JRadioButton("Fechada");
		rdbtnFechada.setEnabled(false);
		rdbtnFechada.setBounds(79, 24, 68, 18);		
		rdbtnFechada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnAberta.setSelected(false);
			
//				chckbxWindUpMestre.setEnabled(true);
//				chckbxWindUpEscravo.setEnabled(true);
				
				if(rdbtnTanque2.isSelected())
					realizarObservacaoEstados.setEnabled(true);
			}
		});
		panelTipoMalha.add(rdbtnFechada);
	}
	
	private void inicializarPainelDadosSinal(){
		panelDadosSinal = new JPanel();
		panelDadosSinal.setBounds(5, 80, 314, 97);		
		panelDadosSinal.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Par\u00E2metros do Sinal", TitledBorder.RIGHT, TitledBorder.TOP, null, Color.GRAY));
		panelDadosSinal.setLayout(null);
		
		lblAmplitude = new JLabel("Amplitude:");
		lblAmplitude.setBounds(11, 11, 84, 20);
		panelDadosSinal.add(lblAmplitude);
		
		amplitude = new JSpinner();
		amplitude.setEnabled(false);
		amplitude.setBounds(92, 11, 51, 20);
		//amplitude.setModel(new SpinnerNumberModel(0.0, -4.0, 4.0, 0.0));
		amplitude.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		panelDadosSinal.add(amplitude);
		
		lblAmplitudeMin = new JLabel("Amplitude (M\u00EDn):");
		lblAmplitudeMin.setBounds(169, 40, 85, 20);
		panelDadosSinal.add(lblAmplitudeMin);
		
		amplitudeMin = new JSpinner();
		amplitudeMin.setEnabled(false);
		amplitudeMin.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		amplitudeMin.setBounds(256, 40, 51, 20);
		panelDadosSinal.add(amplitudeMin);
		
		lblPeriodo = new JLabel("Per\u00EDodo:");
		lblPeriodo.setBounds(10, 40, 85, 20);
		panelDadosSinal.add(lblPeriodo);
		
		periodo = new JSpinner();
		periodo.setEnabled(false);
		periodo.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		periodo.setBounds(92, 40, 51, 20);
		panelDadosSinal.add(periodo);
		
		JLabel lblPeriodoMin = new JLabel("Per\u00EDodo (M\u00EDn):");
		lblPeriodoMin.setBounds(169, 69, 85, 20);
		panelDadosSinal.add(lblPeriodoMin);
		
		periodoMin = new JSpinner();
		periodoMin.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		periodoMin.setEnabled(false);
		periodoMin.setBounds(256, 69, 51, 20);
		panelDadosSinal.add(periodoMin);
		
		JLabel lblOffSet = new JLabel("Off-Set:");
		lblOffSet.setBounds(10, 69, 67, 20);
		panelDadosSinal.add(lblOffSet);
		
		offSet = new JSpinner();
		offSet.setBounds(92, 69, 51, 20);
		offSet.setEnabled(false);
		offSet.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		panelDadosSinal.add(offSet);		
	}
	
	private void inicializarPainelParamsControlador(){
		panelParamsControladorMestre = new JPanel();
		panelParamsControladorMestre.setBounds(5, 204, 314, 134);
		panelParamsControladorMestre.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Par\u00E2metros do Controlador Mestre", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
		panelParamsControladorMestre.setLayout(null);
		inicializarPainelParamsMestre();
		abaOpcoesEntrada.add(panelParamsControladorMestre);
	
		panelParamsControladorEscravo = new JPanel();
		panelParamsControladorEscravo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Par\u00E2metros do Controlador Escravo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
		panelParamsControladorEscravo.setBounds(5, 340, 314, 134);
		panelParamsControladorEscravo.setLayout(null);
		inicializarPainelParamsEscravo();
		abaOpcoesEntrada.add(panelParamsControladorEscravo);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void inicializarPainelParamsMestre(){
		JLabel lblTipoControladorMestre = new JLabel("Tipo do Controlador:");
		lblTipoControladorMestre.setBounds(10, 28, 100, 15);
		panelParamsControladorMestre.add(lblTipoControladorMestre);
		
		comboTipoControladorMestre = new JComboBox(getItensComboTiposControlador());
		comboTipoControladorMestre.setBounds(111, 28, 151, 18);
		comboTipoControladorMestre.setEnabled(false);
		comboTipoControladorMestre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboTipoControladorMestre.getSelectedItem().equals("Selecione") || comboTipoControladorMestre.getSelectedItem().equals("Sem Controle")){
					labelKpMestre.setText("");
					labelKiMestre.setText("");
					labelKdMestre.setText("");
					labelTaliMestre.setText("");
					labelTaldMestre.setText("");
					labelTaltMestre.setText("");
					
					chckbxWindUpMestre.setSelected(false);
				}else if(comboTipoControladorMestre.getSelectedItem().equals("P")){
					labelKiMestre.setText("");
					labelKdMestre.setText("");
					labelTaliMestre.setText("");
					labelTaldMestre.setText("");
				}else if(comboTipoControladorMestre.getSelectedItem().equals("PI")){
					labelKdMestre.setText("");
					labelTaldMestre.setText("");
				}else if(comboTipoControladorMestre.getSelectedItem().equals("PD")){					
					labelKiMestre.setText("");
					labelTaliMestre.setText("");				
				}
			}
		});
		panelParamsControladorMestre.add(comboTipoControladorMestre);
		
		JLabel lblKp = new JLabel("Kp:");
		lblKp.setBounds(10, 58, 22, 15);
		panelParamsControladorMestre.add(lblKp);
		
		labelKpMestre = new JLabel();
		labelKpMestre.setBounds(36, 58, 66, 15);
		panelParamsControladorMestre.add(labelKpMestre);		
		
		JLabel lblKi = new JLabel("Ki:");
		lblKi.setBounds(10, 84, 22, 15);
		panelParamsControladorMestre.add(lblKi);
		
		labelKiMestre = new JLabel();		
		labelKiMestre.setBounds(36, 84, 66, 15);	
		panelParamsControladorMestre.add(labelKiMestre);
		
		JLabel lblTali = new JLabel("\u03C4i:");
		lblTali.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		lblTali.setBounds(112, 84, 23, 15);
		panelParamsControladorMestre.add(lblTali);
		
		labelTaliMestre = new JLabel();		
		labelTaliMestre.setBounds(145, 84, 66, 15);		
		panelParamsControladorMestre.add(labelTaliMestre);
		
		JLabel lblKd = new JLabel("Kd:");
		lblKd.setBounds(10, 110, 22, 15);
		panelParamsControladorMestre.add(lblKd);
		
		labelKdMestre = new JLabel();
		labelKdMestre.setBounds(36, 110, 66, 15);
		panelParamsControladorMestre.add(labelKdMestre);
		
		JLabel lblTald = new JLabel("\u03C4d:");
		lblTald.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		lblTald.setBounds(112, 110, 23, 15);
		panelParamsControladorMestre.add(lblTald);
		
		labelTaldMestre = new JLabel();
		labelTaldMestre.setBounds(145, 110, 66, 15);
		panelParamsControladorMestre.add(labelTaldMestre);
		
		JLabel lblTalt = new JLabel("Tt:");
		lblTalt.setBounds(113, 58, 22, 15);
		panelParamsControladorMestre.add(lblTalt);

		labelTaltMestre = new JLabel();
		labelTaltMestre.setBounds(145, 58, 66, 15);
		panelParamsControladorMestre.add(labelTaltMestre);
		
		JLabel labelChamaConfiguracaoMestre = new JLabel("");
		labelChamaConfiguracaoMestre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				criaPaneilDadosControle(comboTipoControladorMestre, chckbxControleMestre, labelKpMestre, labelKiMestre, labelKdMestre,
						labelTaltMestre, labelTaliMestre, labelTaldMestre);
			}
		});
		labelChamaConfiguracaoMestre.setToolTipText("Clique para atualizar os par\u00E2metros");
		labelChamaConfiguracaoMestre.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1444113669_Pinion.png")));
		labelChamaConfiguracaoMestre.setBounds(274, 11, 32, 32);
		panelParamsControladorMestre.add(labelChamaConfiguracaoMestre);
		
		chckbxWindUpMestre = new JCheckBox("Wind Up");
		chckbxWindUpMestre.setBounds(231, 98, 75, 23);
		chckbxWindUpMestre.setToolTipText("Acionar Wind Up");
		chckbxWindUpMestre.setEnabled(false);
		panelParamsControladorMestre.add(chckbxWindUpMestre);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void inicializarPainelParamsEscravo(){
		JLabel lblTipoControladorEscravo = new JLabel("Tipo do Controlador:");
		lblTipoControladorEscravo.setBounds(10, 29, 100, 15);
		panelParamsControladorEscravo.add(lblTipoControladorEscravo);
		
		comboTipoControladorEscravo = new JComboBox(getItensComboTiposControlador());
		comboTipoControladorEscravo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboTipoControladorEscravo.getSelectedItem().equals("Selecione") || comboTipoControladorEscravo.getSelectedItem().equals("Sem Controle")){					labelKpEscravo.setText("");
					labelKiEscravo.setText("");
					labelKdEscravo.setText("");
					labelTaliEscravo.setText("");
					labelTaldEscravo.setText("");
					labelTaltEscravo.setText("");
					
					chckbxWindUpEscravo.setSelected(false);					
				}else if(comboTipoControladorEscravo.getSelectedItem().equals("P")){
					labelKiEscravo.setText("");
					labelKdEscravo.setText("");
					labelTaliEscravo.setText("");
					labelTaldEscravo.setText("");					
				}else if(comboTipoControladorEscravo.getSelectedItem().equals("PI")){
					labelKdEscravo.setText("");
					labelTaldEscravo.setText("");
				}else if(comboTipoControladorEscravo.getSelectedItem().equals("PD")){					
					labelKiEscravo.setText("");
					labelTaliEscravo.setText("");
				}
			}
		});
		comboTipoControladorEscravo.setEnabled(false);
		comboTipoControladorEscravo.setBounds(111, 29, 151, 18);
		panelParamsControladorEscravo.add(comboTipoControladorEscravo);
		
		JLabel lblKpEscravo = new JLabel("Kp:");
		lblKpEscravo.setBounds(10, 58, 22, 15);
		panelParamsControladorEscravo.add(lblKpEscravo);
		
		labelKpEscravo = new JLabel();
		labelKpEscravo.setEnabled(false);		
		labelKpEscravo.setBounds(36, 58, 66, 15);
		panelParamsControladorEscravo.add(labelKpEscravo);
		
		JLabel lblKiEscravo = new JLabel("Ki:");
		lblKiEscravo.setBounds(10, 84, 22, 15);
		panelParamsControladorEscravo.add(lblKiEscravo);
		
		labelKiEscravo = new JLabel();
		labelKiEscravo.setBounds(36, 84, 66, 15);
		panelParamsControladorEscravo.add(labelKiEscravo);
		
		JLabel lblTaliEscravo = new JLabel("\u03C4i:");
		lblTaliEscravo.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		lblTaliEscravo.setBounds(112, 84, 23, 15);
		panelParamsControladorEscravo.add(lblTaliEscravo);
		
		labelTaliEscravo = new JLabel();
		labelTaliEscravo.setBounds(145, 84, 66, 15);
		panelParamsControladorEscravo.add(labelTaliEscravo);
		
		JLabel lblKdEscravo = new JLabel("Kd:");
		lblKdEscravo.setBounds(10, 110, 22, 15);
		panelParamsControladorEscravo.add(lblKdEscravo);
		
		labelKdEscravo = new JLabel();
		labelKdEscravo.setBounds(36, 110, 66, 15);
		panelParamsControladorEscravo.add(labelKdEscravo);
		
		JLabel lblTaldEscravo = new JLabel("\u03C4d:");
		lblTaldEscravo.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		lblTaldEscravo.setBounds(112, 110, 23, 15);
		panelParamsControladorEscravo.add(lblTaldEscravo);
		
		labelTaldEscravo = new JLabel();
		labelTaldEscravo.setBounds(145, 110, 66, 15);
		panelParamsControladorEscravo.add(labelTaldEscravo);
		
		JLabel lblTaltEscravo = new JLabel("Tt:");
		lblTaltEscravo.setBounds(113, 58, 22, 15);
		panelParamsControladorEscravo.add(lblTaltEscravo);
		
		labelTaltEscravo = new JLabel();
		labelTaltEscravo.setBounds(145, 58, 66, 15);
		panelParamsControladorEscravo.add(labelTaltEscravo);
		
		JLabel labelChamaConfiguracaoEscravo = new JLabel("");
		labelChamaConfiguracaoEscravo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				criaPaneilDadosControle(comboTipoControladorEscravo, chckbxWindUpEscravo, labelKpEscravo, labelKiEscravo, labelKdEscravo,
						labelTaltEscravo, labelTaliEscravo, labelTaldEscravo);
			}
		});
		labelChamaConfiguracaoEscravo.setToolTipText("Clique para atualizar os par\u00E2metros");
		labelChamaConfiguracaoEscravo.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1444113669_Pinion.png")));
		labelChamaConfiguracaoEscravo.setBounds(274, 11, 32, 32);
		panelParamsControladorEscravo.add(labelChamaConfiguracaoEscravo);
		
		chckbxWindUpEscravo = new JCheckBox("Wind Up");
		chckbxWindUpEscravo.setToolTipText("Acionar Wind Up");
		chckbxWindUpEscravo.setEnabled(false);
		chckbxWindUpEscravo.setBounds(231, 98, 75, 23);
		panelParamsControladorEscravo.add(chckbxWindUpEscravo);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void criaPaneilDadosControle(){
		JTextField ip = new JTextField();
		ip.setBounds(100, 170, 140, 160);
		ip.setText(IPServidor.getText());
		
		JTextField porta = new JTextFieldAlterado();
		porta.setText(Porta.getText());
		 
		JComboBox leitura1 = new JComboBox(getItensComboLeituraEscrita());
		leitura1.setSelectedItem(Tela.this.leitura1.getText().equals("") ? "Selecione" : Integer.parseInt(Tela.this.leitura1.getText()));
		 
		JComboBox leitura2 = new JComboBox(getItensComboLeituraEscrita());
		leitura2.setSelectedItem(Tela.this.leitura2.getText().equals("") ? "Selecione" : Integer.parseInt(Tela.this.leitura2.getText()));
		 
		JComboBox escrita = new JComboBox(getItensComboLeituraEscrita());
		escrita.setSelectedItem(Tela.this.escrita.getText().equals("") ? "Selecione" : Integer.parseInt(Tela.this.escrita.getText()));
		 
		Object[] message = {"IP do Servidor:", ip, "Porta:", porta, "Leitura 1:", leitura1, "Leitura 2:", leitura2, "Escrita:", escrita}; 
		int option = JOptionPane.showConfirmDialog(null, message, "Dados de Conexão", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE); 
		 
		if (option == JOptionPane.OK_OPTION) { 
			IPServidor.setText(ip.getText());
			Porta.setText(porta.getText());
			
			Tela.this.leitura1.setText(leitura1.getSelectedItem().toString().equals("Selecione") ? "" : leitura1.getSelectedItem().toString());
			Tela.this.leitura2.setText(leitura2.getSelectedItem().toString().equals("Selecione") ? "" : leitura2.getSelectedItem().toString());
			Tela.this.escrita.setText(escrita.getSelectedItem().toString().equals("Selecione") ? "" : escrita.getSelectedItem().toString());
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void criaPaneilDadosControle(JComboBox comboTipoControlador, JCheckBox chckbxWindUp, JLabel labelKp, JLabel labelKi, JLabel labelKd, JLabel labelTalT, JLabel labelTalI, JLabel labelTalD){
		String strControlador = comboTipoControlador.equals(comboTipoControladorMestre) ? " Mestre" : " Escravo";
		
		final JTextField kP = new JTextFieldAlterado();
		final JTextField kI = new JTextFieldAlterado();				
		final JTextField kD = new JTextFieldAlterado();
		final JTextField tT = new JTextFieldAlterado();
		final JTextField tI = new JTextFieldAlterado();
		final JTextField tD = new JTextFieldAlterado();
		final JCheckBox windUp = new JCheckBox("Wind Up");
		
		windUp.setEnabled(rdbtnFechada.isSelected());
		
		if(strControlador.equals(" Mestre")){
			windUp.setSelected(chckbxWindUpMestre.isSelected());
		}else{
			windUp.setSelected(chckbxWindUpEscravo.isSelected());
		}
				
		setEnableCamposControlador(comboTipoControlador, windUp, kP, kI, kD, tT, tI, tD);
		
		kP.setText(labelKp.getText());
		
		kI.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				try{
					tI.setText("" + Double.parseDouble(kP.getText())
							/Double.parseDouble(kI.getText()));
				}catch (Exception e){}
			}
		});
		kI.setText(labelKi.getText());
		
		kD.setText(labelKd.getText());
		kD.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				try {
					tD.setText("" + Double.parseDouble(kD.getText())
							/Double.parseDouble(kP.getText()));
				} catch (Exception e) {	}
			}
		});
		
		if(!windUp.isSelected())
			tT.setEnabled(false);
		tT.setText(labelTalT.getText());
		
		tI.setText(labelTalI.getText());
		tI.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				try{
					kI.setText("" + Double.parseDouble(kP.getText())
							/Double.parseDouble(tI.getText()));
				}catch(Exception e){}
			}
		});
						
		tD.setText(labelTalD.getText());
		tD.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				try {
					kD.setText("" + Double.parseDouble(kP.getText())
							*Double.parseDouble(tD.getText()));
				} catch (Exception e) {}
			}
		});
		
		windUp.setToolTipText("Acionar Wind Up");
		windUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(windUp.isSelected()){
					tT.setEnabled(true);
				}else{ 
					tT.setEnabled(false);
					tT.setText("");
				}
			}
		});
						 	
		Object[] message = {"Kp:", kP, "Ki:", kI, "Kd:", kD, "Tt:", tT, "Ti:", tI, "Td:", tD, "", windUp}; 
		int option = JOptionPane.showConfirmDialog(null, message, "Parâmetros do Controlador" + strControlador, JOptionPane.OK_CANCEL_OPTION); 
		 
		if (option == JOptionPane.OK_OPTION) { 
			labelKp.setText(kP.getText());			
			labelKi.setText(kI.getText());
			labelKd.setText(kD.getText());
			labelTalT.setText(tT.getText());
			labelTalI.setText(tI.getText());
			labelTalD.setText(tD.getText());				
			
			if(strControlador.equals(" Mestre")){
				chckbxWindUpMestre.setSelected(windUp.isSelected());
			}else{
				chckbxWindUpEscravo.setSelected(windUp.isSelected());
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void setEnableCamposControlador(JComboBox tipoControlador, JCheckBox windUp, JTextField kP, JTextField kI, JTextField kD, JTextField tT, JTextField tI, JTextField tD){
		if(tipoControlador.getSelectedItem().equals("Selecione") || tipoControlador.getSelectedItem().equals("Sem Controle")){
			kP.setEnabled(false);
			kP.setText("");
			kI.setEnabled(false);
			kI.setText("");
			kD.setEnabled(false);
			kD.setText("");
			tI.setEnabled(false);
			tI.setText("");
			tD.setEnabled(false);
			tD.setText("");
			tT.setEnabled(false);
			tT.setText("");
			
			windUp.setSelected(false);
			windUp.setEnabled(rdbtnFechada.isSelected());
		}else if(tipoControlador.getSelectedItem().equals("P")){
			kP.setEnabled(true);
			kI.setEnabled(false);
			kI.setText("");
			kD.setEnabled(false);
			kD.setText("");
			tI.setEnabled(false);
			tI.setText("");
			tD.setEnabled(false);
			tD.setText("");
		}else if(tipoControlador.getSelectedItem().equals("PI")){
			kP.setEnabled(true);
			kI.setEnabled(true);
			kD.setEnabled(false);
			kD.setText("");
			tI.setEnabled(true);
			tD.setEnabled(false);
			tD.setText("");
		}else if(tipoControlador.getSelectedItem().equals("PD")){					
			kP.setEnabled(true);
			kI.setEnabled(false);
			kI.setText("");
			kD.setEnabled(true);
			tI.setEnabled(false);
			tI.setText("");
			tD.setEnabled(true);
		}else if(tipoControlador.getSelectedItem().equals("PID")){
			kP.setEnabled(true);
			kI.setEnabled(true);
			kD.setEnabled(true);
			tI.setEnabled(true);
			tD.setEnabled(true);				
		}else{
			kP.setEnabled(true);
			kI.setEnabled(true);
			kD.setEnabled(true);
			tI.setEnabled(true);
			tD.setEnabled(true);
		}
	}
	
	private void inicializarOutrosComponentesPainelPrincipal(){
		inicializarComboTipoOnda();
		abaOpcoesEntrada.add(comboTipoOnda);
		
		inicializarComboTipoControle();
		abaOpcoesEntrada.add(comboTipoControle);
	}
	
	private void inicializarPainelGraficos(){
		panelGraficos = new JPanel();
		panelGraficos.setBounds(347, 22, 793, 600);
		panelGraficos.setBorder(new TitledBorder(null, "Gr\u00E1ficos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGraficos.setLayout(null);
		
		inicializaPainelGrafico1();
		panelGraficos.add(panelGrafico1);
		
		inicializaPainelGrafico2();
		panelGraficos.add(panelGrafico2);
	}
	
	private void inicializaPainelGrafico1(){
		panelGrafico1 = new JLayeredPane();
		panelGrafico1.setForeground(Color.WHITE);
		panelGrafico1.setBounds(8, 18, 656, 285);
		panelGrafico1.setBackground(Color.WHITE);
		panelGrafico1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGrafico1.setLayout(null);
		
		//redimensionarPainelGrafico1(panelGrafico1, panelGrafico2);
	}
	
	private void inicializaPainelGrafico2(){
		panelGrafico2 = new JLayeredPane();
		panelGrafico2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGrafico2.setBounds(8, 306, 656, 285);
	}
	
	public void mudarPropriedadesBotoes(String acao){
		if(acao.equals("Conectar")){			
			habilitarComponentesPainelTipoMalha(true);
			
			//rdbtnTanque1.setEnabled(true);
			//rdbtnTanque2.setEnabled(true);
			
			comboTipoControle.setEnabled(true);
			
			comboTipoOnda.setEnabled(true);
			
			botaoAtualizar.setEnabled(true);
			btnReset.setEnabled(true);
			
			chckbxTensCalc.setEnabled(true);
			chckbxTensaoSat.setEnabled(true);
			chckbxNivTanque1.setEnabled(true);
			chckbxNivTanque2.setEnabled(true);
			chckbxSetPoint.setEnabled(true);
			chckbxErro.setEnabled(true);
			chckbxErroMestre.setEnabled(true);
			
			chckbxNivel1Estimado.setEnabled(true);			
			chckbxNivel2Estimado.setEnabled(true);
			chckbxErroEstNivel1.setEnabled(true);
			chckbxErroEstNivel2.setEnabled(true);
						
			chckbxP.setEnabled(true);
			chckbxD.setEnabled(true);
			chckbxI.setEnabled(true);
			
			chckbxAoP.setEnabled(true);
			chckbxAoD.setEnabled(true);
			chckbxAcoI.setEnabled(true);
			chckbxControleMestre.setEnabled(true);
			
			/*chckbxComControle = false;*/
						
//			rdbtnAleatorio.setEnabled(true);
//			rdbtnDegrau.setEnabled(true);
//			rdbtnDenteSerra.setEnabled(true);
//			rdbtnQuadrada.setEnabled(true);
//			rdbtnSenoidal.setEnabled(true);
		}else{			
			rdbtnAberta.setEnabled(false);			
			rdbtnFechada.setEnabled(false);
			
			rdbtnTanque1.setEnabled(false);
			rdbtnTanque2.setEnabled(false);
			
			rdbtnTanque1.setSelected(false);
			rdbtnTanque2.setSelected(false);
			
			amplitude.setEnabled(false);
			amplitudeMin.setEnabled(false);
			periodo.setEnabled(false);
			periodoMin.setEnabled(false);
			offSet.setEnabled(false);
			
			comboTipoControle.setEnabled(false);
			comboTipoControle.setSelectedIndex(0);
			
	//		rdbtnTanque1.setEnabled(false);
		//	rdbtnTanque1.setSelected(false);
			//rdbtnTanque2.setEnabled(false);
		//	rdbtnTanque2.setSelected(false);

			comboTipoOnda.setEnabled(false);
			comboTipoOnda.setSelectedIndex(0);
			
			comboTipoControladorMestre.setEnabled(false);
			comboTipoControladorMestre.setSelectedIndex(0);
			
			comboTipoControladorEscravo.setEnabled(false);
			comboTipoControladorEscravo.setSelectedIndex(0);
			
//			chckbxWindUpMestre.setEnabled(false);
			chckbxWindUpMestre.setSelected(false);
			
//			chckbxWindUpEscravo.setEnabled(false);
			chckbxWindUpEscravo.setSelected(false);
			
			botaoAtualizar.setEnabled(false);
			btnReset.setEnabled(false);
			
			zeroA100.setSelected(false);
			cincoA95.setSelected(false);
			dezA90.setSelected(false);
			
			doisPorcento.setSelected(false);
			cincoPorcento.setSelected(false);
			setePorcento.setSelected(false);
			dezPorcento.setSelected(false);
			
			porcentagem.setSelected(false);
			absoluto.setSelected(false);
			
			chckbxP.setEnabled(false);
			chckbxP.setVisible(false);
			chckbxP.setSelected(false);
			
			chckbxD.setEnabled(false);
			chckbxD.setVisible(false);
			chckbxD.setSelected(false);
			
			chckbxI.setEnabled(false);
			chckbxI.setVisible(false);
			chckbxI.setSelected(false);
			
			chckbxAoP.setEnabled(false);
			chckbxAoP.setVisible(false);
			chckbxAoP.setSelected(false);
			
			chckbxAoD.setEnabled(false);
			chckbxAoD.setVisible(false);
			chckbxAoD.setSelected(false);
			
			chckbxAcoI.setEnabled(false);
			chckbxAcoI.setVisible(false);
			chckbxAcoI.setSelected(false);
			
			chckbxControleMestre.setEnabled(false);
			chckbxControleMestre.setVisible(false);
			chckbxControleMestre.setSelected(false);
			
			chckbxTensCalc.setEnabled(false);
			chckbxTensCalc.setVisible(false);
			chckbxTensCalc.setSelected(false);
			
			chckbxTensaoSat.setEnabled(false);
			chckbxTensaoSat.setVisible(false);
			chckbxTensaoSat.setSelected(false);
			
			chckbxNivTanque1.setEnabled(false);
			chckbxNivTanque1.setVisible(false);
			chckbxNivTanque1.setSelected(false);
			
			chckbxNivTanque2.setEnabled(false);
			chckbxNivTanque2.setVisible(false);
			chckbxNivTanque2.setSelected(false);
			
			chckbxErroMestre.setEnabled(false);
			chckbxErroMestre.setVisible(false);
			chckbxErroMestre.setSelected(false);
			
			chckbxSetPoint.setEnabled(false);
			chckbxSetPoint.setVisible(false);
			chckbxSetPoint.setSelected(false);
			
			chckbxErro.setEnabled(false);
			chckbxErro.setVisible(false);
			chckbxErro.setSelected(false);
			
			chckbxNivel1Estimado.setEnabled(false);
			chckbxNivel1Estimado.setVisible(false);
			chckbxNivel1Estimado.setSelected(false);
			
			chckbxNivel2Estimado.setEnabled(false);
			chckbxNivel2Estimado.setVisible(false);
			chckbxNivel2Estimado.setSelected(false);
			
			chckbxErroEstNivel1.setEnabled(false);
			chckbxErroEstNivel1.setVisible(false);
			chckbxErroEstNivel1.setSelected(false);
			
			chckbxErroEstNivel2.setEnabled(false);
			chckbxErroEstNivel2.setVisible(false);
			chckbxErroEstNivel2.setSelected(false);
			
			lblAmplitude.setText("Amplitude:");
			lblPeriodo.setText("Período:");
			
			amplitude.setValue(0);
			amplitudeMin.setValue(0);
			periodo.setValue(0);
			periodoMin.setValue(0);
			offSet.setValue(0);
			
			habilitarComponentesPainelTipoMalha(false);
			
			desabilitarParamsControladorMestre(true, true);
			desabilitarParamsControladorEscravo(true, true);
			
			textFieldReP1.setEnabled(false);
			textFieldReP1.setText("");
			
			textFieldReP2.setEnabled(false);
			textFieldReP2.setText("");
			
			textFieldImP1.setEnabled(false);
			textFieldImP1.setText("");
			
			textFieldImP2.setEnabled(false);
			textFieldImP2.setText("");
			
			textFieldL1.setEnabled(false);
			textFieldL1.setText("");
			
			textFieldL2.setEnabled(false);
			textFieldL2.setText("");
			
			lblExibirCheckSinalGrafico1.setIcon(new ImageIcon(Tela.class.getResource("/Icons/Chart-Curve-Add-32.png")));
			lblExibirCheckSinalGrafico2.setIcon(new ImageIcon(Tela.class.getResource("/Icons/Chart-Curve-Add-32.png")));
		}
	}
	
	private void inicializaCheckSinaisGrafico1(){
		chckbxTensaoSat = new JCheckBox("Tens\u00E3o Sat. ");
		chckbxTensaoSat.setBounds(670, 201, 79, 13);		
		chckbxTensaoSat.setBackground(SystemColor.menu);
		chckbxTensaoSat.setVisible(false);
		chckbxTensaoSat.setEnabled(false);
		chckbxTensaoSat.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxTensaoSat.setForeground(new Color(255, 0, 0));
		chckbxTensaoSat.setToolTipText("Sinal da Tens\u00E3o Saturada");
		chckbxTensaoSat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setTensaoSat(chckbxTensaoSat.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		panelGraficos.add(chckbxTensaoSat);
		
		chckbxTensCalc = new JCheckBox("Tens\u00E3o Calc.");
		chckbxTensCalc.setBounds(670, 185, 79, 13);
		chckbxTensCalc.setBackground(SystemColor.menu);
		chckbxTensCalc.setEnabled(false);
		chckbxTensCalc.setVisible(false);
		chckbxTensCalc.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxTensCalc.setForeground(new Color(0, 0, 205));
		chckbxTensCalc.setToolTipText("Sinal da Tens\u00E3o Calculada");
		chckbxTensCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dados.setTensao(chckbxTensCalc.isSelected());
				thread.setDadosGrafico(dados);}
		});
		panelGraficos.add(chckbxTensCalc);
		
		//novos pos mudanca de chk box para esse grafico do grafico de baixo		
		chckbxP = new JCheckBox("Ação P");
		chckbxP.setEnabled(false);
		chckbxP.setBounds(670, 137, 102, 13);
		chckbxP.setBackground(SystemColor.menu);
		chckbxP.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxP.setVisible(false);
		chckbxP.setToolTipText("Sinal da ação Proporcional");
		chckbxP.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxP.setForeground(new Color(255, 165, 0));
		chckbxP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setProporcional(chckbxP.isSelected());
				thread.setDadosGrafico(dados);
			}
		});		
		panelGraficos.add(chckbxP);
		
		chckbxI = new JCheckBox("Ação I");
		chckbxI.setEnabled(false);
		chckbxI.setBounds(670, 153, 102, 13);
		chckbxI.setBackground(SystemColor.menu);
		chckbxI.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxI.setVisible(false);
		chckbxI.setToolTipText("Sinal da ação Integrativa");
		chckbxI.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxI.setForeground(Color.MAGENTA);
		chckbxI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setIntegral(chckbxI.isSelected());
				thread.setDadosGrafico(dados);
			}
		});		
		panelGraficos.add(chckbxI);
		
		chckbxD = new JCheckBox("Ação D");
		chckbxD.setEnabled(false);
		chckbxD.setBounds(670, 169, 102, 13);
		chckbxD.setBackground(SystemColor.menu);
		chckbxD.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxD.setVisible(false);
		chckbxD.setToolTipText("Sinal da ação Derivativa");
		chckbxD.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxD.setForeground(Color.GRAY);
		chckbxD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setDerivativo(chckbxD.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		panelGraficos.add(chckbxD);
		
		chckbxAoP = new JCheckBox("A\u00E7\u00E3o P - Mestre");
		chckbxAoP.setEnabled(false);
		chckbxAoP.setVisible(false);
		chckbxAoP.setForeground(new Color(0, 191, 255));
		chckbxAoP.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxAoP.setBounds(670, 89, 97, 13);
		chckbxAoP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setProporcional_c2(chckbxAoP.isSelected());
				thread.setDadosGrafico(dados);
			}
		});		
		panelGraficos.add(chckbxAoP);
		
		chckbxAcoI = new JCheckBox("Ac\u00E3o I - Mestre");
		chckbxAcoI.setEnabled(false);
		chckbxAcoI.setVisible(false);
		chckbxAcoI.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxAcoI.setBounds(670, 105, 97, 13);
		chckbxAcoI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setIntegral_c2(chckbxAcoI.isSelected());
				thread.setDadosGrafico(dados);
			}
		});		
		panelGraficos.add(chckbxAcoI);
		
		chckbxAoD = new JCheckBox("A\u00E7\u00E3o D - Mestre");
		chckbxAoD.setEnabled(false);
		chckbxAoD.setVisible(false);
		chckbxAoD.setForeground(new Color(50, 205, 50));
		chckbxAoD.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxAoD.setBounds(670, 121, 97, 13);
		chckbxAoD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setDerivativo_c2(chckbxAoD.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		panelGraficos.add(chckbxAoD);
		
		chckbxControleMestre = new JCheckBox("Controle Mestre");
		chckbxControleMestre.setEnabled(false);
		chckbxControleMestre.setVisible(false);
		chckbxControleMestre.setForeground(Color.PINK);
		chckbxControleMestre.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxControleMestre.setBounds(670, 73, 97, 13);
		chckbxControleMestre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setSinalCascata(chckbxControleMestre.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		panelGraficos.add(chckbxControleMestre);		
	}
	
	private void inicializaCheckSinaisGrafico2(){				
		chckbxNivTanque1 = new JCheckBox("N\u00EDvel do Tanque 1");
		chckbxNivTanque1.setBounds(670, 451, 102, 13);
		chckbxNivTanque1.setBackground(SystemColor.menu);
		chckbxNivTanque1.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxNivTanque1.setVisible(false);
		chckbxNivTanque1.setEnabled(false);
		chckbxNivTanque1.setToolTipText("Sinal de N\u00EDvel do Tanque 1");
		chckbxNivTanque1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxNivTanque1.setForeground(Color.BLACK);
		chckbxNivTanque1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setNivel1(chckbxNivTanque1.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		panelGraficos.add(chckbxNivTanque1);
		
		chckbxNivTanque2 = new JCheckBox("N\u00EDvel do Tanque 2");
		chckbxNivTanque2.setBounds(670, 468, 102, 13);
		chckbxNivTanque2.setBackground(SystemColor.menu);
		chckbxNivTanque2.setVisible(false);
		chckbxNivTanque2.setEnabled(false);
		chckbxNivTanque2.setToolTipText("Sinal de N\u00EDvel do Tanque 2");
		chckbxNivTanque2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxNivTanque2.setForeground(new Color(0, 0, 205));
		chckbxNivTanque2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setNivel2(chckbxNivTanque2.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		panelGraficos.add(chckbxNivTanque2);
		
		chckbxErro = new JCheckBox("Erro");
		chckbxErro.setBounds(670, 435, 102, 13);
		chckbxErro.setBackground(SystemColor.menu);
		chckbxErro.setVisible(false);
		chckbxErro.setEnabled(false);
		chckbxErro.setToolTipText("ERRO");
		chckbxErro.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxErro.setForeground(Color.PINK);
		chckbxErro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setErroMesmo(chckbxErro.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		panelGraficos.add(chckbxErro);
		
		chckbxSetPoint = new JCheckBox("Set-Point");
		chckbxSetPoint.setBounds(670, 419, 102, 13);		
		chckbxSetPoint.setBackground(SystemColor.menu);
		chckbxSetPoint.setVisible(false);
		chckbxSetPoint.setEnabled(false);
		chckbxSetPoint.setToolTipText("Sinal do Set-Point");
		chckbxSetPoint.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxSetPoint.setForeground(Color.RED);
		chckbxSetPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setSetPoint(chckbxSetPoint.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		panelGraficos.add(chckbxSetPoint);
		
		chckbxErroMestre = new JCheckBox("Erro Mestre");
		chckbxErroMestre.setEnabled(false);
		chckbxErroMestre.setVisible(false);
		chckbxErroMestre.setForeground(new Color(255, 165, 0));
		chckbxErroMestre.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxErroMestre.setBounds(670, 402, 102, 13);
		chckbxErroMestre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setErro_c1(chckbxErroMestre.isSelected());
				thread.setDadosGrafico(dados);
			}
		});		
		panelGraficos.add(chckbxErroMestre);
		
		chckbxNivel1Estimado = new JCheckBox("N\u00EDvel 1 Estimado");
		chckbxNivel1Estimado.setEnabled(false);
		chckbxNivel1Estimado.setVisible(false);
		chckbxNivel1Estimado.setForeground(new Color(0, 128, 0));
		chckbxNivel1Estimado.setBounds(670, 484, 97, 13);
		chckbxNivel1Estimado.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxNivel1Estimado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dados.setNvlUmEstimado(chckbxNivel1Estimado.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		panelGraficos.add(chckbxNivel1Estimado);
		
		chckbxNivel2Estimado = new JCheckBox("N\u00EDvel 2 Estimado");
		chckbxNivel2Estimado.setEnabled(false);
		chckbxNivel2Estimado.setVisible(false);
		chckbxNivel2Estimado.setForeground(Color.MAGENTA);
		chckbxNivel2Estimado.setBounds(670, 500, 97, 13);
		chckbxNivel2Estimado.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxNivel2Estimado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dados.setNvlDoisEstimado(chckbxNivel2Estimado.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		panelGraficos.add(chckbxNivel2Estimado);
		
		chckbxErroEstNivel1 = new JCheckBox("Erro de Est. do N\u00EDvel 1");
		chckbxErroEstNivel1.setEnabled(false);
		chckbxErroEstNivel1.setVisible(false);
		chckbxErroEstNivel1.setForeground(Color.GRAY);
		chckbxErroEstNivel1.setToolTipText("Erro de Estima\u00E7\u00E3o do N\u00EDvel 1");
		chckbxErroEstNivel1.setBounds(670, 516, 120, 13);
		chckbxErroEstNivel1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxErroEstNivel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dados.setErroEstimacaoUm(chckbxErroEstNivel1.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		panelGraficos.add(chckbxErroEstNivel1);
		
		chckbxErroEstNivel2 = new JCheckBox("Erro de Est. do N\u00EDvel 2");
		chckbxErroEstNivel2.setEnabled(false);
		chckbxErroEstNivel2.setVisible(false);
		chckbxErroEstNivel2.setForeground(new Color(128, 0, 128));
		chckbxErroEstNivel2.setBounds(670, 532, 120, 13);
		chckbxErroEstNivel2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxErroEstNivel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dados.setErroEstimacaoDois(chckbxErroEstNivel2.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		panelGraficos.add(chckbxErroEstNivel2);	
	}
	
	private void inicializarPainelCheckSinaisGraficos(){		
		lblExibirCheckSinalGrafico1 = new JLabel("");
		lblExibirCheckSinalGrafico1.setBounds(751, 209, 32, 43);
		lblExibirCheckSinalGrafico1.setToolTipText("Exibir Sinal");
		lblExibirCheckSinalGrafico1.setIcon(new ImageIcon(Tela.class.getResource("/Icons/Chart-Curve-Add-32.png")));
		panelGraficos.add(lblExibirCheckSinalGrafico1);
		
		inicializaCheckSinaisGrafico1();
		
		lblExibirCheckSinalGrafico1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!chckbxTensaoSat.isVisible()){
					lblExibirCheckSinalGrafico1.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Delete-32.png")));
					chckbxTensCalc.setVisible(true);
					chckbxTensaoSat.setVisible(true);
					
					chckbxP.setVisible(true);
					chckbxI.setVisible(true);
					chckbxD.setVisible(true);
					
					chckbxAoP.setVisible(true);
					chckbxAcoI.setVisible(true);
					chckbxAoD.setVisible(true);
					chckbxControleMestre.setVisible(true);
				}else{
					lblExibirCheckSinalGrafico1.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Add-32.png")));
					chckbxTensCalc.setVisible(false);
					chckbxTensaoSat.setVisible(false);
					
					chckbxP.setVisible(false);
					chckbxI.setVisible(false);
					chckbxD.setVisible(false);
					
					chckbxAoP.setVisible(false);
					chckbxAcoI.setVisible(false);
					chckbxAoD.setVisible(false);
					chckbxControleMestre.setVisible(false);
				}
			}
		});
		
		lblExibirCheckSinalGrafico2 = new JLabel("");
		lblExibirCheckSinalGrafico2.setBounds(751, 545, 32, 43);
		lblExibirCheckSinalGrafico2.setIcon(new ImageIcon(Tela.class.getResource("/Icons/Chart-Curve-Add-32.png")));
		lblExibirCheckSinalGrafico2.setToolTipText("Exibir Sinal");
		panelGraficos.add(lblExibirCheckSinalGrafico2);
		
		inicializaCheckSinaisGrafico2();
		
		lblExibirCheckSinalGrafico2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!chckbxNivTanque1.isVisible()){
					lblExibirCheckSinalGrafico2.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Delete-32.png")));
					chckbxNivTanque1.setVisible(true);
					chckbxNivTanque2.setVisible(true);
					chckbxErro.setVisible(true);
					chckbxSetPoint.setVisible(true);
					chckbxErroMestre.setVisible(true);
					chckbxNivel1Estimado.setVisible(true);
					chckbxNivel2Estimado.setVisible(true);
					chckbxErroEstNivel1.setVisible(true);
					chckbxErroEstNivel2.setVisible(true);
				}else{
					lblExibirCheckSinalGrafico2.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Add-32.png")));
					chckbxSetPoint.setVisible(false);
					chckbxNivTanque1.setVisible(false);
					chckbxNivTanque2.setVisible(false);
					chckbxErro.setVisible(false);
					chckbxErroMestre.setVisible(false);
					chckbxNivel1Estimado.setVisible(false);
					chckbxNivel2Estimado.setVisible(false);
					chckbxErroEstNivel1.setVisible(false);
					chckbxErroEstNivel2.setVisible(false);
				}
			}
		});
	}
	
	private void desabilitarParamsControladorMestre(boolean limparCampos, boolean desabCampos){		
		
		if(limparCampos){
			labelKpMestre.setText("");
			labelKiMestre.setText("");
			labelKdMestre.setText("");
			labelTaliMestre.setText("");
			labelTaldMestre.setText("");
			labelTaltMestre.setText("");
		}
	}
	
	private void desabilitarParamsControladorEscravo(boolean limparCampos, boolean desabCampos){		
		
		if(limparCampos){
			labelKpEscravo.setText("");
			labelKiEscravo.setText("");
			labelKdEscravo.setText("");
			labelTaliEscravo.setText("");
			labelTaldEscravo.setText("");
			labelTaltEscravo.setText("");
		}
	}
	
	private void habilitarComponentesPainelTipoMalha(Boolean bool){
		rdbtnAberta.setEnabled(bool);
		rdbtnFechada.setEnabled(bool);
		
		rdbtnTanque1.setEnabled(bool);
		rdbtnTanque2.setEnabled(bool);
		
		if(!bool){
			rdbtnAberta.setSelected(bool);
			rdbtnFechada.setSelected(bool);
		}
	}
}
