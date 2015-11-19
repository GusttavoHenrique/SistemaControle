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

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class Tela extends TelaGeral{
	private JFrame frame;
	
	private JMenuBar menuBar;
	private JCheckBoxMenuItem zeroA100, cincoA95, dezA90;
	private JCheckBoxMenuItem doisPorcento, cincoPorcento, setePorcento, dezPorcento;
	private JCheckBoxMenuItem porcentagem, absoluto;
	
	private JPanel panelDadosServidor;
	public JLabel iPServidor, portaServidor;
	private JLabel leitura1, leitura2, escrita;
	
	private JPanel abaOpcoesEntrada;
	
	private JPanel panelBombas;
	private JRadioButton rdbtnTanque1, rdbtnTanque2;
	
	private JPanel panelTipoMalha;
	private JRadioButton rdbtnAberta, rdbtnFechada;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboTipoOnda;
	
	private JPanel panelDadosSinal;
	private JLabel lblPeriodo, lblAmplitude;
	private JSpinner amplitudeMin, amplitude, periodo, periodoMin, offSet;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboTipoControle;
	
	private JPanel panelParamsControladorMestre, panelParamsControladorEscravo;
	@SuppressWarnings("rawtypes")
	private JComboBox comboTipoControladorMestre, comboTipoControladorEscravo;
	private JLabel labelKpMestre, labelKiMestre, labelKdMestre, labelTaltMestre, labelTaliMestre, labelTaldMestre;
	private JLabel labelKpEscravo, labelKiEscravo, labelKdEscravo, labelTaliEscravo, labelTaldEscravo, labelTaltEscravo;
	private JCheckBox chckbxWindUpMestre, chckbxWindUpEscravo;
	
	private JButton botaoAtualizar, btnReset;
	
	private JPanel panelGraficos;
	private JLayeredPane panelGrafico1, panelGrafico2;
	private JLabel lblExibirCheckSinalGrafico1, lblExibirCheckSinalGrafico2;	
	private JCheckBox chckbxControleCSeguidor, chckbxControleMestre, chckbxAcaoD, chckbxAcaoI, chckbxAcaoP, chckbxP, chckbxI, chckbxD, chckbxTensaoSat, chckbxTensCalc;
	private JCheckBox chckbxErroMestre, chckbxSetPoint, chckbxErro, chckbxNivTanque1, chckbxNivTanque2, chckbxNivel1Estimado, chckbxNivel2Estimado, chckbxErroEstNivel1, chckbxErroEstNivel2;	
	
	private JPanel panelValores;
	private JLabel labelTr, labelMp, labelTp, labelTs;
	
	private JPanel painelObservadorEstados;
	private JCheckBox realizarObservacaoEstados;
	private JLabel lblCalculaPolosMatrizL;
	private JTextFieldAlterado textFieldReP1, textFieldImP1, textFieldReP2, textFieldImP2;
	private JTextFieldAlterado textFieldL1, textFieldL2;
	
	private JPanel painelSeguidorReferencia;
	private JLabel lblCalculaPolosMatrizK;
	private JTextFieldAlterado textFieldP1Seg, textFieldReP2Seg, textFieldImP2Seg, textFieldReP3Seg, textFieldImP3Seg;
	private JTextFieldAlterado textFieldK1, textFieldK21, textFieldK22;
	
	private ImageIcon iconSentidoConversao = new ImageIcon(Tela.class.getResource("/Icons/10885_32x32.png"));
	
	public String onda_limpa_tanque;
	public double amplitude_limpa_tanque;
	
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
		frame.setBounds(100, 0, 1154, 728);
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
				criaPaneilDadosConexao();
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
		zeroA100.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(zeroA100.isSelected()){
					dezA90.setSelected(false);
					cincoA95.setSelected(false);
				}
			}
		});
		mnTempoDeSubida.add(zeroA100);
		
		cincoA95 = new JCheckBoxMenuItem("5 - 95 %");
		cincoA95.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(cincoA95.isSelected()){
					dezA90.setSelected(false);
					zeroA100.setSelected(false);
				}
			}
		});
		mnTempoDeSubida.add(cincoA95);
		
		dezA90 = new JCheckBoxMenuItem("10 - 90 %");
		dezA90.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(dezA90.isSelected()){
					cincoA95.setSelected(false);
					zeroA100.setSelected(false);
				}
			}
		});
		mnTempoDeSubida.add(dezA90);

		//Cria menu Tempo de acomodação (Ts)
		JMenu mnTempoDeAcomodao = new JMenu("Tempo de Acomoda\u00E7\u00E3o (Ts)");
		mnEstatistica.add(mnTempoDeAcomodao);
		
		doisPorcento = new JCheckBoxMenuItem("2%");
		doisPorcento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(doisPorcento.isSelected()){
					cincoPorcento.setSelected(false);
					setePorcento.setSelected(false);
					dezPorcento.setSelected(false);
				}
			}
		});
		mnTempoDeAcomodao.add(doisPorcento);
		
		cincoPorcento = new JCheckBoxMenuItem("5%");
		cincoPorcento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(cincoPorcento.isSelected()){
					doisPorcento.setSelected(false);
					setePorcento.setSelected(false);
					dezPorcento.setSelected(false);
				}
			}
		});
		mnTempoDeAcomodao.add(cincoPorcento);
		
		setePorcento = new JCheckBoxMenuItem("7%");
		setePorcento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(setePorcento.isSelected()){
					doisPorcento.setSelected(false);
					cincoPorcento.setSelected(false);
					dezPorcento.setSelected(false);
				}
			}
		});
		mnTempoDeAcomodao.add(setePorcento);
		
		dezPorcento = new JCheckBoxMenuItem("10%");
		dezPorcento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(dezPorcento.isSelected()){
					doisPorcento.setSelected(false);
					cincoPorcento.setSelected(false);
					setePorcento.setSelected(false);
				}
			}
		});
		mnTempoDeAcomodao.add(dezPorcento);
		
		//Cria menu Overshoot (Mp)
		JMenu mnOvershootmp = new JMenu("Overshoot (Mp)");
		mnEstatistica.add(mnOvershootmp);
		
		porcentagem = new JCheckBoxMenuItem("Porcentagem");
		porcentagem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(porcentagem.isSelected()){
					absoluto.setSelected(false);
				}
			}
		});
		mnOvershootmp.add(porcentagem);
		
		absoluto = new JCheckBoxMenuItem("Absoluto");
		absoluto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(absoluto.isSelected()){
					porcentagem.setSelected(false);
				}
			}
		});
		mnOvershootmp.add(absoluto);
	}
	
	private void inicializarPainelValoresAtuais(){
		//Inicializando Painel Painel de exibição dos Valores
		panelValores = new JPanel();
		panelValores.setBounds(6, 525, 660, 43);
		panelGraficos.add(panelValores);
		panelValores.setBorder(new TitledBorder(null, "Valores Atuais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelValores.setLayout(null);
		
		JLabel lblTr = new JLabel("Tr:");
		lblTr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTr.setBounds(112, 15, 24, 17);
		panelValores.add(lblTr);
		
		labelTr = new JLabel();
		labelTr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelTr.setBackground(Color.WHITE);
		labelTr.setBounds(146, 15, 66, 17);
		panelValores.add(labelTr);
		
		JLabel lblMp = new JLabel("Mp:");
		lblMp.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblMp.setBounds(222, 15, 30, 17);
		panelValores.add(lblMp);
		
		labelMp = new JLabel();
		labelMp.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelMp.setBackground(Color.WHITE);
		labelMp.setBounds(262, 15, 66, 17);
		panelValores.add(labelMp);
		
		JLabel lblTp = new JLabel("Tp:");
		lblTp.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTp.setBounds(341, 15, 28, 17);
		panelValores.add(lblTp);
		
		labelTp = new JLabel();
		labelTp.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelTp.setBounds(379, 15, 66, 17);
		panelValores.add(labelTp);
		
		JLabel lblTs = new JLabel("Ts:");
		lblTs.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTs.setBounds(455, 15, 28, 17);
		panelValores.add(lblTs);
		
		labelTs = new JLabel();
		labelTs.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelTs.setBounds(493, 15, 66, 17);
		panelValores.add(labelTs);		
	}	
	
	private void inicializarPainelDadosServidor(){
		panelDadosServidor = new JPanel();
		panelDadosServidor.setBounds(6, 22, 340, 73);
		panelDadosServidor.setBorder(new TitledBorder(null, "Dados do Servidor", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelDadosServidor.setLayout(null);
		
		JLabel lblIPServidor = new JLabel("IP:");
		lblIPServidor.setBounds(10, 17, 14, 16);
		panelDadosServidor.add(lblIPServidor);
		
		iPServidor = new JLabel();
		iPServidor.setText("10.13.99.69");
		iPServidor.setBounds(28, 17, 65, 16);
		panelDadosServidor.add(iPServidor);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(10, 45, 36, 16);
		panelDadosServidor.add(lblPorta);
		
		portaServidor = new JLabel();
		portaServidor.setText("20081");
		portaServidor.setBounds(43, 45, 50, 16);
		panelDadosServidor.add(portaServidor);
		
		final JButton btnConectarDesconectar = new JButton("Conectar");
		btnConectarDesconectar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269447_gtk-apply.png")));
		btnConectarDesconectar.setForeground(new Color(0, 128, 0));
		btnConectarDesconectar.setBackground(new Color(0, 128, 0));
		btnConectarDesconectar.setBounds(205, 37, 119, 25);
		btnConectarDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(iPServidor.getText().equals("") || portaServidor.getText().equals("")){
					JOptionPane.showMessageDialog(frame, "                          Conexão não Realizada! " +
							"\nInforme o Ip do Servidor e/ou a Porta e tente novamente.");
				}else{
					thread = new Tanque();
					thread.setServer(iPServidor.getText(), Integer.parseInt(portaServidor.getText()));
					
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
		leitura1.setText("0");
		panelDadosServidor.add(leitura1);
		
		JLabel lblLeitura2 = new JLabel("Leitura 2:");
		lblLeitura2.setBounds(112, 45, 50, 16);
		panelDadosServidor.add(lblLeitura2);
		
		leitura2 = new JLabel();
		leitura2.setBounds(172, 45, 36, 16);
		leitura2.setText("1");
		panelDadosServidor.add(leitura2);
		
		JLabel lblEscrita = new JLabel("Escrita:");
		lblEscrita.setBounds(235, 18, 36, 14);
		panelDadosServidor.add(lblEscrita);
		
		escrita = new JLabel();
		escrita.setBounds(281, 17, 36, 16);
		escrita.setText("0");
		panelDadosServidor.add(escrita);
	}
	
	private void inicializarBotões(){
		botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269378_gtk-refresh.png")));		
		botaoAtualizar.setEnabled(false);
		botaoAtualizar.setBounds(65, 655, 101, 37);
		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dados = new Dados();					
				
				if(validaPoupulaTudoNaDados()){			
					
					// Seta na classe dados o tipo de controle
					//populaTipoControleNaDados();
					
					// Seta na classe dados os checkBox dos gráficos que serão exibidos
					populaCheckDosGraficosNaDados();
					
					// Seta os dados parâmetros desejados para a resposta na classe dados					
					populaParamsRespostaNaDados();
					
					// Seta os parâmetros do observador de estados na classe dados
					populaParamsObservadorEstadosNaDados();

					// Seta os parâmetros do observador de estados na classe dados					
					populaParamsSeguidorReferenciaNaDados();
					
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
		btnReset.setEnabled(false);
		btnReset.setBounds(185, 655, 101, 37);
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
		if(comboTipoControle.getSelectedItem().equals(TipoControle.SEGUIDOR_REFERENCIA.getDescricao())){
			dados.setTipoDeControle(TipoControle.SEGUIDOR_REFERENCIA.getDescricao());
		}else if(comboTipoControle.getSelectedItem().equals(TipoControle.CASCATA.getDescricao())){
			dados.setTipoDeControle(TipoControle.CASCATA.getDescricao());
		}else if(comboTipoControle.getSelectedItem().equals(TipoControle.SIMPLES.getDescricao())){
			dados.setTipoDeControle(TipoControle.SIMPLES.getDescricao());
		}else{
			dados.setTipoDeControle(TipoControle.SEM_CONTROLE.getDescricao());
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
		
		dados.setProporcional_c2(chckbxAcaoP.isSelected());
		dados.setIntegral_c2(chckbxAcaoI.isSelected());
		dados.setDerivativo_c2(chckbxAcaoD.isSelected());
		dados.setSinalCascata(chckbxControleMestre.isSelected());
		dados.setControleCSeguidor(chckbxControleCSeguidor.isSelected());
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
	
	private void populaParamsSeguidorReferenciaNaDados(){
		if(!textFieldP1Seg.getText().equals(""))
			dados.setParteReP1Seg(Double.parseDouble(textFieldP1Seg.getText()));
		
		if(!textFieldReP2Seg.getText().equals(""))
			dados.setParteReP2Seg(Double.parseDouble(textFieldReP2Seg.getText()));
		
		if(!textFieldImP2Seg.getText().equals(""))
			dados.setParteImP2Seg(Double.parseDouble(textFieldImP2Seg.getText()));
		
		if(!textFieldReP3Seg.getText().equals(""))
			dados.setParteReP3Seg(Double.parseDouble(textFieldReP3Seg.getText()));
		
		if(!textFieldImP3Seg.getText().equals(""))
			dados.setParteImP3Seg(Double.parseDouble(textFieldImP3Seg.getText()));
		
		if(!textFieldK1.getText().equals(""))
			dados.setK1(Double.parseDouble(textFieldK1.getText()));
		
		if(!textFieldK21.getText().equals(""))
			dados.setK21(Double.parseDouble(textFieldK21.getText()));
		
		if(!textFieldK22.getText().equals(""))
			dados.setK22(Double.parseDouble(textFieldK22.getText()));
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
		if(comboTipoControle.getSelectedItem().equals(TipoControle.SIMPLES.getDescricao()) 
				|| comboTipoControle.getSelectedItem().equals(TipoControle.CASCATA.getDescricao())){
		
			sucesso = sucesso && validaParamsControladorMestre(comboTipoControladorMestre, labelKpEscravo, labelKiEscravo, labelKdEscravo, labelTaltEscravo, labelTaliEscravo, labelTaldEscravo);
		}

		//Validando painel de escolha dos parametros do controlador mestre
		if(comboTipoControle.getSelectedItem().equals(TipoControle.CASCATA.getDescricao()))
			sucesso = sucesso && validaParamsControladorEscravo(comboTipoControladorEscravo, labelKpMestre, labelKiMestre, labelKdMestre, labelTaltMestre, labelTaliMestre, labelTaldMestre);

		return sucesso;
	}
	
	private boolean validaPolosObservador(JTextField campo1ParaApagar, JTextField campo2ParaApagar){
		double somaDosQuadradosP1 = 0.0, somaDosQuadradosP2 = 0.0;;
		
		if(!textFieldReP1.getText().equals(textFieldReP2.getText())){
			somaDosQuadradosP1 = Math.pow(Double.parseDouble(textFieldReP1.getText()), 2);
			somaDosQuadradosP2 = Math.pow(Double.parseDouble(textFieldReP2.getText()), 2);
		}else{
			somaDosQuadradosP1 = Math.pow(Double.parseDouble(textFieldReP1.getText()), 2) + Math.pow(Double.parseDouble(textFieldImP1.getText()), 2);
			somaDosQuadradosP2 = somaDosQuadradosP1;
		}
		
		boolean instavel = (Math.sqrt(somaDosQuadradosP1) >= 1) || (Math.sqrt(somaDosQuadradosP2) >= 1);
		
		if(instavel){			
			JOptionPane.showMessageDialog(frame, "Não é possível realizar observação de estados utilizando esses polos.");
			
			campo1ParaApagar.setText("");
			campo2ParaApagar.setText("");
			
			return false;
		}else{
			return true;
		}
	}
	
	private boolean validaPolosSeguidor(JTextField campo1ParaApagar, JTextField campo2ParaApagar, JTextField campo3ParaApagar){
		double somaDosQuadradosP1 = 0.0, somaDosQuadradosP2 = 0.0, somaDosQuadradosP3 = 0.0;
		
		somaDosQuadradosP1 = Math.pow(Double.parseDouble(textFieldP1Seg.getText()), 2);
		
		if(!textFieldReP2Seg.getText().equals(textFieldReP3Seg.getText())){
			somaDosQuadradosP2 = Math.pow(Double.parseDouble(textFieldReP2Seg.getText()), 2);
			somaDosQuadradosP3 = Math.pow(Double.parseDouble(textFieldReP3Seg.getText()), 2);
		}else{
			somaDosQuadradosP2 = Math.pow(Double.parseDouble(textFieldReP2Seg.getText()), 2) + Math.pow(Double.parseDouble(textFieldImP2Seg.getText()), 2);
			somaDosQuadradosP3 = somaDosQuadradosP2;
		}
		
		boolean instavel = (Math.sqrt(somaDosQuadradosP1) >= 1) || (Math.sqrt(somaDosQuadradosP2) >= 1 || (Math.sqrt(somaDosQuadradosP3) >= 1));
		
		if(instavel){			
			JOptionPane.showMessageDialog(frame, "Não é possível realizar observação de estados utilizando esses polos.");
			
			if(campo1ParaApagar != null)
				campo1ParaApagar.setText("");
			
			if(campo2ParaApagar != null)
				campo2ParaApagar.setText("");
			
			if(campo3ParaApagar != null)
				campo3ParaApagar.setText("");
			
			return false;
		}else{
			return true;
		}
	}
	
	private boolean validaTipoControle(){
		if(comboTipoControle.equals(TipoControle.SELECIONE.getDescricao())){
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
			dados.setTipoMalha(rdbtnAberta.isSelected() ? TipoMalha.MALHA_ABERTA.getDescricao() : TipoMalha.MALHA_FECHADA.getDescricao());
		}
		
		return true;
	}
	
	/** 
	 * Valida campos e Popula os parâmetros do sinal na classe Dados.
	 */
	public boolean validaOnda(){		
		if(comboTipoOnda.getSelectedItem().equals(TipoOnda.SELECIONE.getDescricao())){
			JOptionPane.showMessageDialog(frame, "Informe o Tipo de Onda.");
			
			return false;
		}else {
			dados.setTipoSinal(comboTipoOnda.getSelectedItem().toString());
			
			if(amplitude.getValue().equals("")){
				String amplitude = comboTipoOnda.getSelectedItem().equals(TipoOnda.DEGRAU.getDescricao()) ? "Amplitude (Máx)" : "Amplitude";  

				JOptionPane.showMessageDialog(frame, "Informe a " + amplitude + " do sinal.");
				
				return false;
			}else{
				dados.setAmplitude((double)amplitude.getValue());
			}
			
			if(comboTipoOnda.getSelectedItem().equals(TipoOnda.QUADRADA.getDescricao()) || 
					comboTipoOnda.getSelectedItem().equals(TipoOnda.SENOIDAL.getDescricao()) || 
					comboTipoOnda.getSelectedItem().equals(TipoOnda.DENTE_SERRA.getDescricao())){
				
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
			
			if(comboTipoOnda.getSelectedItem().equals(TipoOnda.ALEATORIA.getDescricao())){				
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
			if(comboTipoControlador.getSelectedItem().equals(TipoControlador.SELECIONE.getDescricao())){
				JOptionPane.showMessageDialog(frame, "Informe o tipo de controlador do mestre!");
				
				return false;
			}else{
				dados.setTipoDeControlador(comboTipoControlador.getSelectedItem().toString());
				
				if(labelKpMestre.getText().equals("")){
					JOptionPane.showMessageDialog(frame, "Informe o valor de Kp do mestre.");
					
					return false;
				}else{
					dados.setKP(Double.parseDouble(labelKpMestre.getText()));
					
					if((comboTipoControlador.getSelectedItem().equals(TipoControlador.PI.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PID.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PI_D.getDescricao()))
							&& (labelKiMestre.getText().equals("") || labelTaliMestre.getText().equals(""))){
						
						JOptionPane.showMessageDialog(frame, "Informe todos os parâmetros do controlador integrativo (Ki e Ti) do mestre.");
						
						return false;
					}else if((comboTipoControlador.getSelectedItem().equals(TipoControlador.PI.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PID.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PI_D.getDescricao()))
							&& !(labelKiMestre.getText().equals("") || labelTaliMestre.getText().equals(""))){
				
						dados.setKI(Double.parseDouble(labelKiMestre.getText()));
					}
					
					if((comboTipoControlador.getSelectedItem().equals(TipoControlador.PD.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PID.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PI_D.getDescricao()))
							&& (labelKdMestre.getText().equals("") || labelTaldMestre.getText().equals(""))){
						
						JOptionPane.showMessageDialog(frame, "Informe todos os parâmetros do controlador derivativo (Kd e Td) do mestre.");
						
						return false;
					}else if((comboTipoControlador.getSelectedItem().equals(TipoControlador.PD.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PID.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PI_D.getDescricao()))
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
			if(comboTipoControlador.getSelectedItem().equals(TipoControlador.SELECIONE.getDescricao())){
				JOptionPane.showMessageDialog(frame, "Informe o tipo de controlador do escravo!");
				
				return false;
			}else{
				dados.setTipoDeControladorEscravo(comboTipoControlador.getSelectedItem().toString());
				
				if(labelKpEscravo.getText().equals("")){
					JOptionPane.showMessageDialog(frame, "Informe o valor de Kp do escravo.");
					
					return false;
				}else{
					dados.setKpEscravo(Double.parseDouble(labelKpEscravo.getText()));
					
					if((comboTipoControlador.getSelectedItem().equals(TipoControlador.PI.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PID.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PI_D.getDescricao()))
							&& (labelKiEscravo.getText().equals("") || labelTaliEscravo.getText().equals(""))){
						
						JOptionPane.showMessageDialog(frame, "Informe todos os parâmetros do controlador integrativo (Ki e Ti) do escravo.");
						
						return false;
					}else if((comboTipoControlador.getSelectedItem().equals(TipoControlador.PI.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PID.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PI_D.getDescricao()))
							&& !(labelKiEscravo.getText().equals("") || labelTaliEscravo.getText().equals(""))){
				
						dados.setKiEscravo(Double.parseDouble(labelKiEscravo.getText()));
					}
					
					if((comboTipoControlador.getSelectedItem().equals(TipoControlador.PD.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PID.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PI_D.getDescricao()))
							&& (labelKdEscravo.getText().equals("") || labelTaldEscravo.getText().equals(""))){
						
						JOptionPane.showMessageDialog(frame, "Informe todos os parâmetros do controlador derivativo (Kd e Td) do escravo.");
						
						return false;
					}else if((comboTipoControlador.getSelectedItem().equals(TipoControlador.PD.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PID.getDescricao()) 
							|| comboTipoControlador.getSelectedItem().equals(TipoControlador.PI_D.getDescricao()))
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
		panelBombas.setBounds(170, 2, 160, 55);
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
		abas.setBounds(6, 94, 340, 552);
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
		
		inicializarPainelObservadorEstados();
		
		inicializarPainelSeguidorReferencia();
	}
	
	private void inicializarPainelObservadorEstados(){
		painelObservadorEstados = new JPanel();
		painelObservadorEstados.setLayout(null);
		painelObservadorEstados.setBorder(new TitledBorder(null, "Observador de Estados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelObservadorEstados.setBounds(347, 598, 357, 100);
		frame.getContentPane().add(painelObservadorEstados);
		
		insereCamposPolosObservador();
		
		lblCalculaPolosMatrizL = new JLabel("");
		lblCalculaPolosMatrizL.setBounds(178, 50, 32, 32);
		lblCalculaPolosMatrizL.setToolTipText("Sentido da \u00FAltima transforma\u00E7\u00E3o");
		lblCalculaPolosMatrizL.setIcon(iconSentidoConversao);
		painelObservadorEstados.add(lblCalculaPolosMatrizL);
		
		insereCamposMatrizL();
		
		insereCheckNoPainel();
	}
	
	private void insereCamposPolosObservador(){
		JLabel lblP1Obs = new JLabel("P1 = ");
		lblP1Obs.setBounds(10, 50, 28, 14);
		painelObservadorEstados.add(lblP1Obs);
		
		textFieldReP1 = new JTextFieldAlterado(6, false);
		textFieldReP1.setToolTipText("Parte real do polo 1");
		textFieldReP1.setEnabled(false);
		textFieldReP1.setColumns(10);
		textFieldReP1.setBounds(36, 48, 46, 16);
		textFieldReP1.setToolTipText("Parte real do polo 1");
		textFieldReP1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(textFieldReP1.getText().equals("") && !textFieldImP1.getText().equals("")){
					textFieldReP2.setText(textFieldReP1.getText());
				}
				
				if(!textFieldReP1.getText().equals(textFieldReP2.getText()) && 
						textFieldImP1.getText().equals("") && textFieldImP2.getText().equals("")){
					
					if(!textFieldReP2.getText().equals("")){
						textFieldImP1.setText("");
						textFieldImP2.setText("");
						
						textFieldImP1.setEnabled(false);
						textFieldImP2.setEnabled(false);
						
						textFieldImP1.setToolTipText("<html><p style='background-color:FFFFAA; color:red;'>" +
								"<b>Atenção!</b><br></br><br></br>" +
								"Esse campo só é habilitado quando os polos P1 e P2 possuem partes reais iguais." +
								"</p></html>");
						
						textFieldImP2.setToolTipText("<html><p style='background-color:FFFFAA; color:red;'>" +
								"<b>Atenção!</b><br></br><br></br>" +
								"Esse campo só é habilitado quando os polos P1 e P2 possuem partes reais iguais." +
								"</p></html>");
					}
				}else{
					textFieldReP2.setText(textFieldReP1.getText());
					
					textFieldImP1.setEnabled(true);
					textFieldImP2.setEnabled(true);
					
					textFieldImP1.setToolTipText("Parte imaginária do polo 1");
					textFieldImP2.setToolTipText("Parte imaginária do polo 2");
				}
				
				if(!textFieldReP1.getText().equals(textFieldReP1Anterior)){
					textFieldL1.setText("");
					textFieldL2.setText("");
					
					textFieldReP1Anterior = textFieldReP1.getText();
					textFieldReP2Anterior = textFieldReP2.getText();
				}
				
				if(allCamposPolosObservadorPreenchidos(false) && !setaAzulIconObservadorParaDireita){
					setaAzulIconObservadorParaDireita = rotacionarIcon(iconSentidoConversao, lblCalculaPolosMatrizL, setaAzulIconObservadorParaDireita);
				}
				
				if(allCamposPolosObservadorPreenchidos(false) && allCamposMatrizLVazios() && validaPolosObservador(textFieldReP1, textFieldReP2)){
					Matrix matrizL = calculaMatrizL(textFieldReP1, textFieldImP1, textFieldReP2, textFieldImP2);
					
					textFieldL1.setText(setText(matrizL.get(0, 0) + "", matrizL.get(0, 0) < 0 ? 7 : 6));
					textFieldL2.setText(setText(matrizL.get(1, 0) + "", matrizL.get(1, 0) < 0 ? 7 : 6));					
				}
			}
		});
		painelObservadorEstados.add(textFieldReP1);
		
		JLabel lblMais1 = new JLabel("+");
		lblMais1.setBounds(86, 49, 13, 15);
		painelObservadorEstados.add(lblMais1);
		
		textFieldImP1 = new JTextFieldAlterado(6, false);
		textFieldImP1.setToolTipText("Parte imagin\u00E1ria do polo 1");
		textFieldImP1.setEnabled(false);
		textFieldImP1.setColumns(10);
		textFieldImP1.setBounds(99, 48, 46, 16);
		textFieldImP1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				textFieldImP2.setText(textFieldImP1.getText());
				
				if(!textFieldImP1.getText().equals("") && !textFieldReP1.getText().equals("")){
					textFieldReP2.setText(textFieldReP1.getText());
				}		
				
				if(!textFieldImP1.getText().equals(textFieldImP1Anterior)){
					textFieldL1.setText("");
					textFieldL2.setText("");
					
					textFieldImP1Anterior = textFieldImP1.getText();
					textFieldImP2Anterior = textFieldImP2.getText();
				}

				if(allCamposPolosObservadorPreenchidos(false) && !setaAzulIconObservadorParaDireita){
					setaAzulIconObservadorParaDireita = rotacionarIcon(iconSentidoConversao, lblCalculaPolosMatrizL, setaAzulIconObservadorParaDireita);
				}
								
				if(allCamposPolosObservadorPreenchidos(false) && allCamposMatrizLVazios() && validaPolosObservador(textFieldImP1, textFieldImP2)){
					Matrix matrizL = calculaMatrizL(textFieldReP1, textFieldImP1, textFieldReP2, textFieldImP2);
					
					textFieldL1.setText(setText(matrizL.get(0, 0) + "", matrizL.get(0, 0) < 0 ? 7 : 6));
					textFieldL2.setText(setText(matrizL.get(1, 0) + "", matrizL.get(1, 0) < 0 ? 7 : 6));					
				}
			}
		});
		painelObservadorEstados.add(textFieldImP1);
		
		JLabel lblI1 = new JLabel("i");
		lblI1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblI1.setBounds(150, 49, 13, 15);
		painelObservadorEstados.add(lblI1);
		
		JLabel lblP2Obs = new JLabel("P2 = ");
		lblP2Obs.setBounds(10, 70, 28, 14);
		painelObservadorEstados.add(lblP2Obs);
		
		textFieldReP2 = new JTextFieldAlterado(6, false);
		textFieldReP2.setToolTipText("Parte real do polo 2");
		textFieldReP2.setEnabled(false);
		textFieldReP2.setColumns(10);
		textFieldReP2.setBounds(36, 68, 46, 16);
		textFieldReP2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(textFieldReP2.getText().equals("") && !textFieldImP2.getText().equals("")){
					textFieldReP1.setText(textFieldReP2.getText());
				}
				
				if(!textFieldReP2.getText().equals(textFieldReP1.getText()) && 
						textFieldImP1.getText().equals("") && textFieldImP2.getText().equals("")){
					
					if(!textFieldReP1.getText().equals("")){
						textFieldImP1.setText("");
						textFieldImP2.setText("");
						
						textFieldImP1.setEnabled(false);
						textFieldImP2.setEnabled(false);
						 
						textFieldImP1.setToolTipText("<html><p style='background-color:FFFFAA; color:red;'>" +
								"<b>Atenção!</b><br></br><br></br>" +
								"Esse campo só é habilitado quando os polos P1 e P2 possuem partes reais iguais." +
								"</p></html>");
						
						textFieldImP2.setToolTipText("<html><p style='background-color:FFFFAA; color:red;'>" +
								"<b>Atenção!</b><br></br><br></br>" +
								"Esse campo só é habilitado quando os polos P1 e P2 possuem partes reais iguais." +
								"</p></html>");						
					}
				}else{
					textFieldReP1.setText(textFieldReP2.getText());
					
					textFieldImP1.setEnabled(true);
					textFieldImP2.setEnabled(true);
					
					textFieldImP1.setToolTipText("Parte imaginária do polo 1");
					textFieldImP2.setToolTipText("Parte imaginária do polo 2");
				}
				
				if(!textFieldReP2.getText().equals(textFieldReP2Anterior)){
					textFieldL1.setText("");
					textFieldL2.setText("");
					
					textFieldReP1Anterior = textFieldReP1.getText();
					textFieldReP2Anterior = textFieldReP2.getText();
				}
				
				if(allCamposPolosObservadorPreenchidos(false) && !setaAzulIconObservadorParaDireita){
					setaAzulIconObservadorParaDireita = rotacionarIcon(iconSentidoConversao, lblCalculaPolosMatrizL, setaAzulIconObservadorParaDireita);
				}
				
				if(allCamposPolosObservadorPreenchidos(false) && allCamposMatrizLVazios() && validaPolosObservador(textFieldReP2, textFieldReP1)){
					Matrix matrizL = calculaMatrizL(textFieldReP1, textFieldImP1, textFieldReP2, textFieldImP2);
					
					textFieldL1.setText(setText(matrizL.get(0, 0) + "", matrizL.get(0, 0) < 0 ? 7 : 6));
					textFieldL2.setText(setText(matrizL.get(1, 0) + "", matrizL.get(1, 0) < 0 ? 7 : 6));					
				}
			}
		});
		painelObservadorEstados.add(textFieldReP2);
		
		JLabel lblMenos1 = new JLabel("-");
		lblMenos1.setBounds(88, 69, 13, 15);
		painelObservadorEstados.add(lblMenos1);
		
		textFieldImP2 = new JTextFieldAlterado(6, false);
		textFieldImP2.setToolTipText("Parte imagin\u00E1ria do polo 2");
		textFieldImP2.setEnabled(false);
		textFieldImP2.setColumns(10);
		textFieldImP2.setBounds(99, 68, 46, 16);
		textFieldImP2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				textFieldImP1.setText(textFieldImP2.getText());
				
				if(!textFieldImP2.getText().equals("") && !textFieldReP2.getText().equals("")){
					textFieldReP1.setText(textFieldReP2.getText());
				}

				if(!textFieldImP2.getText().equals(textFieldImP2Anterior)){
					textFieldL1.setText("");
					textFieldL2.setText("");
					
					textFieldImP1Anterior = textFieldImP1.getText();
					textFieldImP2Anterior = textFieldImP2.getText();
				}

				if(allCamposPolosObservadorPreenchidos(false) && !setaAzulIconObservadorParaDireita){
					setaAzulIconObservadorParaDireita = rotacionarIcon(iconSentidoConversao, lblCalculaPolosMatrizL, setaAzulIconObservadorParaDireita);
				}
				
				if(allCamposPolosObservadorPreenchidos(false) && allCamposMatrizLVazios() && validaPolosObservador(textFieldImP2, textFieldImP1)){
					Matrix matrizL = calculaMatrizL(textFieldReP1, textFieldImP1, textFieldReP2, textFieldImP2);
					
					textFieldL1.setText(setText(matrizL.get(0, 0) + "", matrizL.get(0, 0) < 0 ? 7 : 6));
					textFieldL2.setText(setText(matrizL.get(1, 0) + "", matrizL.get(1, 0) < 0 ? 7 : 6));					
				}
			}
		});
		painelObservadorEstados.add(textFieldImP2);
		
		JLabel lblI2 = new JLabel("i");
		lblI2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblI2.setBounds(150, 69, 13, 15);
		painelObservadorEstados.add(lblI2);
	}
	
	private void insereCamposMatrizL(){		
		JLabel lblLIgual = new JLabel("L = ");
		lblLIgual.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLIgual.setBounds(240, 48, 23, 14);
		painelObservadorEstados.add(lblLIgual);
		
		textFieldL1 = new JTextFieldAlterado(6, false);
		textFieldL1.setToolTipText("valor de L1");
		textFieldL1.setEnabled(false);
		textFieldL1.setColumns(10);
		textFieldL1.setBounds(277, 33, 46, 16);
		textFieldL1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!textFieldL1.getText().equals(textFieldL1Anterior)){
					textFieldReP1.setText("");
					textFieldReP2.setText("");
					textFieldImP1.setText("");
					textFieldImP2.setText("");
					
					textFieldL1Anterior = textFieldL1.getText();
					textFieldL2Anterior = textFieldL2.getText();
				}
				
				if(allCamposMatrizLPreenchidos() && setaAzulIconObservadorParaDireita){
					setaAzulIconObservadorParaDireita = rotacionarIcon(iconSentidoConversao, lblCalculaPolosMatrizL, setaAzulIconObservadorParaDireita);
				}
				
				if(allCamposPolosObservadorVazios() && allCamposMatrizLPreenchidos()){
					EigenvalueDecomposition polos = calculaPolosObservadorEstados(textFieldL1, textFieldL2);
					
					double[] imagEigenvalues = polos.getImagEigenvalues();
					double[] realEigenvalues = polos.getRealEigenvalues();
					
					textFieldReP1.setText(setText(realEigenvalues[0]*(-1) + "", realEigenvalues[0] < 0 ? 7 : 6));
					textFieldImP1.setText(setText(Math.abs(imagEigenvalues[1]) + "", 6));
					textFieldReP2.setText(setText(realEigenvalues[0]*(-1) + "", realEigenvalues[0] < 0 ? 7 : 6));
					textFieldImP2.setText(setText(Math.abs(imagEigenvalues[1]) + "", 6));
					
					if(!validaPolosObservador(textFieldL1, textFieldL2)){
						textFieldReP1.setText("");
						textFieldImP1.setText("");
						textFieldReP2.setText("");
						textFieldImP2.setText("");
					}
				}
			}
		});
		painelObservadorEstados.add(textFieldL1);
		
		textFieldL2 = new JTextFieldAlterado(6, false);
		textFieldL2.setToolTipText("valor de L2");
		textFieldL2.setEnabled(false);
		textFieldL2.setColumns(10);
		textFieldL2.setBounds(277, 63, 46, 16);
		textFieldL2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!textFieldL2.getText().equals(textFieldL2Anterior)){
					textFieldReP1.setText("");
					textFieldReP2.setText("");
					textFieldImP1.setText("");
					textFieldImP2.setText("");
					
					textFieldL1Anterior = textFieldL1.getText();
					textFieldL2Anterior = textFieldL2.getText();
				}

				if(allCamposMatrizLPreenchidos() && setaAzulIconObservadorParaDireita){
					setaAzulIconObservadorParaDireita = rotacionarIcon(iconSentidoConversao, lblCalculaPolosMatrizL, setaAzulIconObservadorParaDireita);
				}
				
				if(allCamposPolosObservadorVazios() && allCamposMatrizLPreenchidos()){
					EigenvalueDecomposition polos = calculaPolosObservadorEstados(textFieldL1, textFieldL2);
					
					double[] imagEigenvalues = polos.getImagEigenvalues();
					double[] realEigenvalues = polos.getRealEigenvalues();
					
					textFieldReP1.setText(setText(realEigenvalues[0]*(-1) + "", realEigenvalues[0] < 0 ? 7 : 6));
					textFieldImP1.setText(setText(Math.abs(imagEigenvalues[1]) + "", 6));
					textFieldReP2.setText(setText(realEigenvalues[0]*(-1) + "", realEigenvalues[0] < 0 ? 7 : 6));
					textFieldImP2.setText(setText(Math.abs(imagEigenvalues[1]) + "", 6));
					
					if(!validaPolosObservador(textFieldL2, textFieldL1)){
						textFieldReP1.setText("");
						textFieldImP1.setText("");
						textFieldReP2.setText("");
						textFieldImP2.setText("");
					}
				}
			}
		});
		
		JLabel lblColchete1 = new JLabel("[");
		lblColchete1.setForeground(Color.GRAY);
		lblColchete1.setFont(new Font("Calibri Light", Font.BOLD, 85));
		lblColchete1.setBounds(253, -14, 35, 138);
		painelObservadorEstados.add(lblColchete1);
		painelObservadorEstados.add(textFieldL2);
	}
	
	private void insereCheckNoPainel(){
		realizarObservacaoEstados = new JCheckBox("Realizar Observa\u00E7\u00E3o de Estados");
		realizarObservacaoEstados.setFont(new Font("Tahoma", Font.PLAIN, 10));
		realizarObservacaoEstados.setEnabled(false);
		realizarObservacaoEstados.setBounds(10, 17, 185, 15);
		realizarObservacaoEstados.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				if(realizarObservacaoEstados.isSelected()){
					textFieldReP1.setEnabled(true);
					textFieldReP2.setEnabled(true);
					
					textFieldImP1.setEnabled(true);
					textFieldImP2.setEnabled(true);
					
					textFieldL1.setEnabled(true);
					textFieldL2.setEnabled(true);
			
					comboTipoControle.removeItem(TipoControle.SIMPLES.getDescricao());
					comboTipoControle.removeItem(TipoControle.CASCATA.getDescricao());
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
					
					comboTipoControle.addItem(TipoControle.SIMPLES.getDescricao());
					comboTipoControle.addItem(TipoControle.CASCATA.getDescricao());
					
					textFieldImP1.setToolTipText("Parte imaginária do polo 1");
					textFieldImP2.setToolTipText("Parte imaginária do polo 2");
				}
			}
		});
		painelObservadorEstados.add(realizarObservacaoEstados);
		
		JLabel lblColchete2 = new JLabel("]");
		lblColchete2.setForeground(Color.GRAY);
		lblColchete2.setFont(new Font("Calibri Light", Font.BOLD, 85));
		lblColchete2.setBounds(322, -14, 35, 138);
		painelObservadorEstados.add(lblColchete2);		
	}
	
	private void insereCamposPolosSeguidor(){
		JLabel lblP1Seg = new JLabel("P1 = ");
		lblP1Seg.setBounds(10, 25, 28, 14);
		painelSeguidorReferencia.add(lblP1Seg);
		
		textFieldP1Seg = new JTextFieldAlterado(6, false);
		textFieldP1Seg.setToolTipText("Polo 1");
		textFieldP1Seg.setEnabled(false);
		textFieldP1Seg.setColumns(10);
		textFieldP1Seg.setBounds(36, 23, 46, 16);
		textFieldP1Seg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!textFieldP1Seg.getText().equals(textFieldP1SegAnterior)){
					textFieldK1.setText("");
					textFieldK21.setText("");
					textFieldK22.setText("");
					
					textFieldP1SegAnterior = textFieldP1Seg.getText();
					textFieldReP2SegAnterior = textFieldReP2Seg.getText();
					textFieldImP2SegAnterior = textFieldImP2Seg.getText();
					textFieldReP3SegAnterior = textFieldReP3Seg.getText();
					textFieldImP3SegAnterior = textFieldImP3Seg.getText();
				}
				
				if(allCamposPolosSeguidorPreenchidos(false) && !setaAzulIconSeguidorParaDireita){
					setaAzulIconSeguidorParaDireita = rotacionarIcon(iconSentidoConversao, lblCalculaPolosMatrizK, setaAzulIconSeguidorParaDireita);
				}
				
				if(allCamposPolosSeguidorPreenchidos(false) && allCamposMatrizKVazios() && validaPolosSeguidor(textFieldP1Seg, null, null)){
					Matrix matrizK = calculaMatrizK(textFieldP1Seg, textFieldReP3Seg, textFieldImP2Seg, textFieldReP3Seg);
					
					textFieldK21.setText(setText(matrizK.get(0, 0) + "", matrizK.get(0, 0) < 0 ? 7 : 6));
					textFieldK22.setText(setText(matrizK.get(0, 1) + "", matrizK.get(0, 1) < 0 ? 7 : 6));
					textFieldK1.setText(setText(matrizK.get(0, 2) + "", matrizK.get(0, 2) < 0 ? 7 : 6));
					//TODO
				}
			}
		});
		painelSeguidorReferencia.add(textFieldP1Seg);		
		
		JLabel lblP2Seg = new JLabel("P2 = ");
		lblP2Seg.setBounds(10, 48, 28, 14);
		painelSeguidorReferencia.add(lblP2Seg);
		
		textFieldReP2Seg = new JTextFieldAlterado(6, false);
		textFieldReP2Seg.setToolTipText("Parte real do polo 2");
		textFieldReP2Seg.setEnabled(false);
		textFieldReP2Seg.setColumns(10);
		textFieldReP2Seg.setBounds(36, 47, 46, 16);
		textFieldReP2Seg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(textFieldReP2Seg.getText().equals("") && !textFieldImP2Seg.getText().equals("")){
					textFieldReP3Seg.setText(textFieldReP2Seg.getText());
				}
				
				if(!textFieldReP2Seg.getText().equals(textFieldReP3Seg.getText()) && 
						textFieldImP2Seg.getText().equals("") && textFieldImP3Seg.getText().equals("")){
					
					if(!textFieldReP3Seg.getText().equals("")){
						textFieldImP2Seg.setText("");
						textFieldImP3Seg.setText("");
						
						textFieldImP2Seg.setEnabled(false);
						textFieldImP3Seg.setEnabled(false);
						
						textFieldImP2Seg.setToolTipText("<html><p style='background-color:FFFFAA; color:red;'>" +
								"<b>Atenção!</b><br></br><br></br>" +
								"Esse campo só é habilitado quando os polos P2 e P3 possuem partes reais iguais." +
								"</p></html>");
						
						textFieldImP3Seg.setToolTipText("<html><p style='background-color:FFFFAA; color:red;'>" +
								"<b>Atenção!</b><br></br><br></br>" +
								"Esse campo só é habilitado quando os polos P2 e P3 possuem partes reais iguais." +
								"</p></html>");
					}
				}else{
					textFieldReP3Seg.setText(textFieldReP2Seg.getText());
					
					textFieldImP2Seg.setEnabled(true);
					textFieldImP3Seg.setEnabled(true);
					
					textFieldImP2Seg.setToolTipText("Parte imaginária do polo 2");
					textFieldImP3Seg.setToolTipText("Parte imaginária do polo 3");
				}
				
				if(!textFieldReP2Seg.getText().equals(textFieldReP2SegAnterior)){
					textFieldK1.setText("");
					textFieldK21.setText("");
					textFieldK22.setText("");
					
					textFieldP1SegAnterior = textFieldP1Seg.getText();
					textFieldReP2SegAnterior = textFieldReP2Seg.getText();
					textFieldImP2SegAnterior = textFieldImP2Seg.getText();
					textFieldReP3SegAnterior = textFieldReP3Seg.getText();
					textFieldImP3SegAnterior = textFieldImP3Seg.getText();
				}
				
				if(allCamposPolosSeguidorPreenchidos(false) && !setaAzulIconSeguidorParaDireita){
					setaAzulIconSeguidorParaDireita = rotacionarIcon(iconSentidoConversao, lblCalculaPolosMatrizK, setaAzulIconSeguidorParaDireita);
				}
				
				if(allCamposPolosSeguidorPreenchidos(false) && allCamposMatrizKVazios() && validaPolosSeguidor(null, textFieldReP2Seg, textFieldReP3Seg)){
					Matrix matrizK = calculaMatrizK(textFieldP1Seg, textFieldReP3Seg, textFieldImP2Seg, textFieldReP3Seg);
					
					textFieldK21.setText(setText(matrizK.get(0, 0) + "", matrizK.get(0, 0) < 0 ? 7 : 6));
					textFieldK22.setText(setText(matrizK.get(0, 1) + "", matrizK.get(0, 1) < 0 ? 7 : 6));
					textFieldK1.setText(setText(matrizK.get(0, 2) + "", matrizK.get(0, 2) < 0 ? 7 : 6));
					//TODO
				}
			}
		});
		painelSeguidorReferencia.add(textFieldReP2Seg);
		
		JLabel lblMais2 = new JLabel("+");
		lblMais2.setBounds(86, 48, 13, 15);
		painelSeguidorReferencia.add(lblMais2);
		
		textFieldImP2Seg = new JTextFieldAlterado(6, false);
		textFieldImP2Seg.setToolTipText("Parte imagin\u00E1ria do polo 2");
		textFieldImP2Seg.setEnabled(false);
		textFieldImP2Seg.setColumns(10);
		textFieldImP2Seg.setBounds(99, 47, 46, 16);
		textFieldImP2Seg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				textFieldImP3Seg.setText(textFieldImP2Seg.getText());
				
				if(!textFieldImP2Seg.getText().equals("") && !textFieldReP2Seg.getText().equals("")){
					textFieldReP3Seg.setText(textFieldReP2Seg.getText());
				}		
				
				if(!textFieldImP2Seg.getText().equals(textFieldImP2SegAnterior)){
					textFieldK1.setText("");
					textFieldK21.setText("");
					textFieldK22.setText("");
					
					textFieldP1SegAnterior = textFieldP1Seg.getText();
					textFieldReP2SegAnterior = textFieldReP2Seg.getText();
					textFieldImP2SegAnterior = textFieldImP2Seg.getText();
					textFieldReP3SegAnterior = textFieldReP3Seg.getText();
					textFieldImP3SegAnterior = textFieldImP3Seg.getText();
				}

				if(allCamposPolosSeguidorPreenchidos(false) && !setaAzulIconSeguidorParaDireita){
					//Falta ver se a setaAzulIconSeguidorParaDireita funciona e não há conflito
					setaAzulIconSeguidorParaDireita = rotacionarIcon(iconSentidoConversao, lblCalculaPolosMatrizK, setaAzulIconSeguidorParaDireita);
				}
								
				if(allCamposPolosSeguidorPreenchidos(false) && allCamposMatrizKVazios() && validaPolosSeguidor(null, textFieldImP2Seg, textFieldImP3Seg)){
					Matrix matrizK = calculaMatrizK(textFieldP1Seg, textFieldReP2Seg, textFieldImP2Seg, textFieldReP3Seg);
					
					textFieldK21.setText(setText(matrizK.get(0, 0) + "", matrizK.get(0, 0) < 0 ? 7 : 6));
					textFieldK22.setText(setText(matrizK.get(0, 1) + "", matrizK.get(0, 1) < 0 ? 7 : 6));
					textFieldK1.setText(setText(matrizK.get(0, 2) + "", matrizK.get(0, 2) < 0 ? 7 : 6));
					//TODO
				}
			}
		});
		painelSeguidorReferencia.add(textFieldImP2Seg);
		
		JLabel lblI3 = new JLabel("i");
		lblI3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblI3.setBounds(150, 48, 13, 15);
		painelSeguidorReferencia.add(lblI3);
		
		JLabel lblP3Seg = new JLabel("P3 = ");
		lblP3Seg.setBounds(10, 73, 28, 14);
		painelSeguidorReferencia.add(lblP3Seg);
		
		textFieldReP3Seg = new JTextFieldAlterado(6, false);
		textFieldReP3Seg.setToolTipText("Parte real do polo 3");
		textFieldReP3Seg.setEnabled(false);
		textFieldReP3Seg.setColumns(10);
		textFieldReP3Seg.setBounds(36, 72, 46, 16);
		textFieldReP3Seg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(textFieldReP3Seg.getText().equals("") && !textFieldImP3Seg.getText().equals("")){
					textFieldReP2Seg.setText(textFieldReP3Seg.getText());
				}
				
				if(!textFieldReP3Seg.getText().equals(textFieldReP2Seg.getText()) && 
						textFieldImP3Seg.getText().equals("") && textFieldImP2Seg.getText().equals("")){
					
					if(!textFieldReP2Seg.getText().equals("")){
						textFieldImP2Seg.setText("");
						textFieldImP3Seg.setText("");
						
						textFieldImP2Seg.setEnabled(false);
						textFieldImP3Seg.setEnabled(false);
						
						textFieldImP2Seg.setToolTipText("<html><p style='background-color:FFFFAA; color:red;'>" +
								"<b>Atenção!</b><br></br><br></br>" +
								"Esse campo só é habilitado quando os polos P2 e P3 possuem partes reais iguais." +
								"</p></html>");
						
						textFieldImP3Seg.setToolTipText("<html><p style='background-color:FFFFAA; color:red;'>" +
								"<b>Atenção!</b><br></br><br></br>" +
								"Esse campo só é habilitado quando os polos P2 e P3 possuem partes reais iguais." +
								"</p></html>");
					}
				}else{
					textFieldReP2Seg.setText(textFieldReP3Seg.getText());
					
					textFieldImP2Seg.setEnabled(true);
					textFieldImP3Seg.setEnabled(true);
					
					textFieldImP2Seg.setToolTipText("Parte imaginária do polo 2");
					textFieldImP3Seg.setToolTipText("Parte imaginária do polo 3");
				}
				
				if(!textFieldReP3Seg.getText().equals(textFieldReP3SegAnterior)){
					textFieldK1.setText("");
					textFieldK21.setText("");
					textFieldK22.setText("");
					
					textFieldP1SegAnterior = textFieldP1Seg.getText();
					textFieldReP2SegAnterior = textFieldReP2Seg.getText();
					textFieldImP2SegAnterior = textFieldImP2Seg.getText();
					textFieldReP3SegAnterior = textFieldReP3Seg.getText();
					textFieldImP3SegAnterior = textFieldImP3Seg.getText();
				}
				
				if(allCamposPolosSeguidorPreenchidos(false) && !setaAzulIconSeguidorParaDireita){
					setaAzulIconSeguidorParaDireita = rotacionarIcon(iconSentidoConversao, lblCalculaPolosMatrizK, setaAzulIconSeguidorParaDireita);
				}
				
				if(allCamposPolosSeguidorPreenchidos(false) && allCamposMatrizKVazios() && validaPolosSeguidor(null, textFieldReP2Seg, textFieldReP3Seg)){
					Matrix matrizK = calculaMatrizK(textFieldP1Seg, textFieldReP3Seg, textFieldImP2Seg, textFieldReP3Seg);
					
					textFieldK21.setText(setText(matrizK.get(0, 0) + "", matrizK.get(0, 0) < 0 ? 7 : 6));
					textFieldK22.setText(setText(matrizK.get(0, 1) + "", matrizK.get(0, 1) < 0 ? 7 : 6));
					textFieldK1.setText(setText(matrizK.get(0, 2) + "", matrizK.get(0, 2) < 0 ? 7 : 6));
					//TODO
				}
			}
		});
		painelSeguidorReferencia.add(textFieldReP3Seg);
		
		JLabel lblMenos2 = new JLabel("-");
		lblMenos2.setBounds(88, 73, 13, 15);
		painelSeguidorReferencia.add(lblMenos2);
		
		textFieldImP3Seg = new JTextFieldAlterado(6, false);
		textFieldImP3Seg.setToolTipText("Parte imagin\u00E1ria do polo 3");
		textFieldImP3Seg.setEnabled(false);
		textFieldImP3Seg.setColumns(10);
		textFieldImP3Seg.setBounds(99, 72, 46, 16);
		textFieldImP3Seg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				textFieldImP2Seg.setText(textFieldImP3Seg.getText());
				
				if(!textFieldImP3Seg.getText().equals("") && !textFieldImP3Seg.getText().equals("")){
					textFieldReP2Seg.setText(textFieldReP3Seg.getText());
				}		
				
				if(!textFieldImP3Seg.getText().equals(textFieldImP3SegAnterior)){
					textFieldK1.setText("");
					textFieldK21.setText("");
					textFieldK22.setText("");
					
					textFieldImP3SegAnterior = textFieldImP3Seg.getText();
					textFieldImP2SegAnterior = textFieldImP2Seg.getText();
				}

				if(allCamposPolosSeguidorPreenchidos(false) && !setaAzulIconSeguidorParaDireita){
					setaAzulIconSeguidorParaDireita = rotacionarIcon(iconSentidoConversao, lblCalculaPolosMatrizK, setaAzulIconSeguidorParaDireita);
				}
								
				if(allCamposPolosSeguidorPreenchidos(false) && allCamposMatrizKVazios() && validaPolosSeguidor(null, textFieldImP2Seg, textFieldImP3Seg)){
					Matrix matrizK = calculaMatrizK(textFieldP1Seg, textFieldReP2Seg, textFieldImP2Seg, textFieldReP3Seg);
					
					textFieldK21.setText(setText(matrizK.get(0, 0) + "", matrizK.get(0, 0) < 0 ? 7 : 6));
					textFieldK22.setText(setText(matrizK.get(0, 1) + "", matrizK.get(0, 1) < 0 ? 7 : 6));
					textFieldK1.setText(setText(matrizK.get(0, 2) + "", matrizK.get(0, 2) < 0 ? 7 : 6));
					//TODO
				}
			}
		});
		painelSeguidorReferencia.add(textFieldImP3Seg);
		
		JLabel lblI4 = new JLabel("i");
		lblI4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblI4.setBounds(150, 73, 13, 15);
		painelSeguidorReferencia.add(lblI4);
	}
	
	private void insereCamposMatrizK(){
		JLabel lblK21Igual = new JLabel("K21 = ");
		lblK21Igual.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblK21Igual.setBounds(227, 70, 33, 14);
		painelSeguidorReferencia.add(lblK21Igual);
		
		textFieldK1 = new JTextFieldAlterado(6, false);
		textFieldK1.setToolTipText("valor de K1");
		textFieldK1.setEnabled(false);
		textFieldK1.setColumns(10);
		textFieldK1.setBounds(259, 44, 46, 16);
		textFieldK1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!textFieldK1.getText().equals(textFieldK1Anterior)){
					textFieldP1Seg.setText("");
					textFieldReP2Seg.setText("");
					textFieldImP2Seg.setText("");
					textFieldReP3Seg.setText("");
					textFieldImP3Seg.setText("");
					
					textFieldK1Anterior = textFieldK1.getText();
					textFieldK21Anterior = textFieldK21.getText();
					textFieldK22Anterior = textFieldK22.getText();
				}
				
				if(allCamposMatrizKPreenchidos() && setaAzulIconSeguidorParaDireita){
					setaAzulIconSeguidorParaDireita = rotacionarIcon(iconSentidoConversao, lblCalculaPolosMatrizK, setaAzulIconSeguidorParaDireita);
				}
				
				if(allCamposPolosSeguidorVazios() && allCamposMatrizKPreenchidos()){
					EigenvalueDecomposition polos = calculaPolosSeguidorReferencia(textFieldK1, textFieldK21, textFieldK22);
					
					double[] imagEigenvalues = polos.getImagEigenvalues();
					double[] realEigenvalues = polos.getRealEigenvalues();
					
					textFieldReP2Seg.setText(setText(realEigenvalues[0]*(-1) + "", realEigenvalues[0] < 0 ? 7 : 6));
					textFieldImP2Seg.setText(setText(Math.abs(imagEigenvalues[0]) + "", 6));
					textFieldReP3Seg.setText(setText(realEigenvalues[1]*(-1) + "", realEigenvalues[1] < 0 ? 7 : 6));
					textFieldImP3Seg.setText(setText(Math.abs(imagEigenvalues[1]) + "", 6));
					textFieldP1Seg.setText(setText(realEigenvalues[2]*(-1) + "", realEigenvalues[0] < 0 ? 7 : 6));
					
					if(!validaPolosSeguidor(textFieldK1, null, null)){
						textFieldP1Seg.setText("");
						textFieldReP2Seg.setText("");
						textFieldImP2Seg.setText("");
						textFieldReP3Seg.setText("");
						textFieldImP3Seg.setText("");
					}
				}
			}
		});
		painelSeguidorReferencia.add(textFieldK1);
		
		textFieldK21 = new JTextFieldAlterado(6, false);
		textFieldK21.setToolTipText("valor de K21");
		textFieldK21.setEnabled(false);
		textFieldK21.setColumns(10);
		textFieldK21.setBounds(259, 69, 46, 16);
		textFieldK21.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!textFieldK21.getText().equals(textFieldK21Anterior)){
					textFieldP1Seg.setText("");
					textFieldReP2Seg.setText("");
					textFieldImP2Seg.setText("");
					textFieldReP3Seg.setText("");
					textFieldImP3Seg.setText("");
					
					textFieldK1Anterior = textFieldK1.getText();
					textFieldK21Anterior = textFieldK21.getText();
					textFieldK22Anterior = textFieldK22.getText();
				}
				
				if(allCamposMatrizKPreenchidos() && setaAzulIconSeguidorParaDireita){
					setaAzulIconSeguidorParaDireita = rotacionarIcon(iconSentidoConversao, lblCalculaPolosMatrizK, setaAzulIconSeguidorParaDireita);
				}
				
				if(allCamposPolosSeguidorVazios() && allCamposMatrizKPreenchidos()){
					EigenvalueDecomposition polos = calculaPolosSeguidorReferencia(textFieldK1, textFieldK21, textFieldK22);
					
					double[] imagEigenvalues = polos.getImagEigenvalues();
					double[] realEigenvalues = polos.getRealEigenvalues();
					
					textFieldReP2Seg.setText(setText(realEigenvalues[0]*(-1) + "", realEigenvalues[0] < 0 ? 7 : 6));
					textFieldImP2Seg.setText(setText(Math.abs(imagEigenvalues[0]) + "", 6));
					textFieldReP3Seg.setText(setText(realEigenvalues[1]*(-1) + "", realEigenvalues[1] < 0 ? 7 : 6));
					textFieldImP3Seg.setText(setText(Math.abs(imagEigenvalues[1]) + "", 6));
					textFieldP1Seg.setText(setText(realEigenvalues[2]*(-1) + "", realEigenvalues[0] < 0 ? 7 : 6));
					
					if(!validaPolosSeguidor(null, textFieldK21, null)){
						textFieldP1Seg.setText("");
						textFieldReP2Seg.setText("");
						textFieldImP2Seg.setText("");
						textFieldReP3Seg.setText("");
						textFieldImP3Seg.setText("");
					}
				}
			}
		});
		painelSeguidorReferencia.add(textFieldK21);
		
		textFieldK22 = new JTextFieldAlterado(6, false);
		textFieldK22.setToolTipText("valor de K22");
		textFieldK22.setEnabled(false);
		textFieldK22.setColumns(10);
		textFieldK22.setBounds(368, 69, 46, 16);
		textFieldK22.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!textFieldK22.getText().equals(textFieldK22Anterior)){
					textFieldP1Seg.setText("");
					textFieldReP2Seg.setText("");
					textFieldImP2Seg.setText("");
					textFieldReP3Seg.setText("");
					textFieldImP3Seg.setText("");
					
					textFieldK1Anterior = textFieldK1.getText();
					textFieldK21Anterior = textFieldK21.getText();
					textFieldK22Anterior = textFieldK22.getText();
				}
				
				if(allCamposMatrizKPreenchidos() && setaAzulIconSeguidorParaDireita){
					setaAzulIconSeguidorParaDireita = rotacionarIcon(iconSentidoConversao, lblCalculaPolosMatrizK, setaAzulIconSeguidorParaDireita);
				}
				
				if(allCamposPolosSeguidorVazios() && allCamposMatrizKPreenchidos()){
					EigenvalueDecomposition polos = calculaPolosSeguidorReferencia(textFieldK1, textFieldK21, textFieldK22);
					
					double[] imagEigenvalues = polos.getImagEigenvalues();
					double[] realEigenvalues = polos.getRealEigenvalues();
					
					textFieldReP2Seg.setText(setText(realEigenvalues[0]*(-1) + "", realEigenvalues[0] < 0 ? 7 : 6));
					textFieldImP2Seg.setText(setText(Math.abs(imagEigenvalues[0]) + "", 6));
					textFieldReP3Seg.setText(setText(realEigenvalues[1]*(-1) + "", realEigenvalues[1] < 0 ? 7 : 6));
					textFieldImP3Seg.setText(setText(Math.abs(imagEigenvalues[1]) + "", 6));
					textFieldP1Seg.setText(setText(realEigenvalues[2]*(-1) + "", realEigenvalues[0] < 0 ? 7 : 6));
					
					if(!validaPolosSeguidor(null, null, textFieldK22)){
						textFieldP1Seg.setText("");
						textFieldReP2Seg.setText("");
						textFieldImP2Seg.setText("");
						textFieldReP3Seg.setText("");
						textFieldImP3Seg.setText("");
					}
				}
			}
		});
		painelSeguidorReferencia.add(textFieldK22);
		
		JLabel lblK1Igual = new JLabel("K1 = ");
		lblK1Igual.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblK1Igual.setBounds(230, 45, 26, 14);
		painelSeguidorReferencia.add(lblK1Igual);
		
		JLabel lblK22Igual = new JLabel("K22 = ");
		lblK22Igual.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblK22Igual.setBounds(333, 70, 33, 14);
		painelSeguidorReferencia.add(lblK22Igual);
	}
	
	private void inicializarPainelSeguidorReferencia(){
		painelSeguidorReferencia = new JPanel();
		painelSeguidorReferencia.setLayout(null);
		painelSeguidorReferencia.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seguidor de Refer\u00EAncia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		painelSeguidorReferencia.setBounds(708, 598, 432, 100);
		frame.getContentPane().add(painelSeguidorReferencia);
		
		insereCamposPolosSeguidor();
		
		lblCalculaPolosMatrizK = new JLabel("");
		lblCalculaPolosMatrizK.setToolTipText("Sentido da \u00FAltima transforma\u00E7\u00E3o");
		lblCalculaPolosMatrizK.setIcon(iconSentidoConversao);
		lblCalculaPolosMatrizK.setBounds(170, 48, 32, 32);
		painelSeguidorReferencia.add(lblCalculaPolosMatrizK);
		
		insereCamposMatrizK();
	}
	
	private boolean allCamposPolosObservadorVazios(){
		return(textFieldImP1.getText().equals("") && textFieldImP2.getText().equals("") &&
				textFieldReP1.getText().equals("") && textFieldReP2.getText().equals(""));
	}
	
	private boolean allCamposPolosObservadorPreenchidos(boolean retornaMensagem){
		boolean polosComPartesReaisDistintas = (!textFieldReP1.getText().equals("") && !textFieldReP2.getText().equals(""))
				&& !textFieldReP1.getText().equals(textFieldReP2.getText()) 
				&& (textFieldImP1.getText().equals("") && textFieldImP2.getText().equals(""));

		boolean polosComPartesReaisIguais = !textFieldReP1.getText().equals("") && !textFieldReP2.getText().equals("")
				&& !textFieldImP1.getText().equals("") && !textFieldImP2.getText().equals("");		
		
		if(polosComPartesReaisDistintas || polosComPartesReaisIguais){
			return true;
		}else{			
			if(retornaMensagem)
				JOptionPane.showMessageDialog(frame, "Informe os polos (ou a matriz L) do observador de estados.");
			
			return false;
		}
	}
	
	private boolean allCamposMatrizLVazios(){
		return (textFieldL1.getText().equals("") && textFieldL2.getText().equals(""));
	}
	
	private boolean allCamposMatrizLPreenchidos(){
		return (!textFieldL1.getText().equals("") && !textFieldL2.getText().equals(""));
	}	

	private boolean allCamposPolosSeguidorVazios(){
		return(textFieldP1Seg.getText().equals("") && 
				textFieldReP2Seg.getText().equals("") && textFieldImP2Seg.getText().equals("") &&
				textFieldReP3Seg.getText().equals("") && textFieldImP3Seg.getText().equals(""));
	}
	
	private boolean allCamposPolosSeguidorPreenchidos(boolean retornaMensagem){
		boolean polosComPartesReaisDistintas = 
				(!textFieldP1Seg.getText().equals("") && !textFieldReP2Seg.getText().equals("") && !textFieldReP3Seg.getText().equals(""))
				&& !textFieldReP2Seg.getText().equals(textFieldReP3Seg.getText()) 
				&& (textFieldImP2Seg.getText().equals("") && textFieldImP3Seg.getText().equals(""));

		boolean polosComPartesReaisIguais = 
				!textFieldP1Seg.getText().equals("") && !textFieldReP2Seg.getText().equals("") && !textFieldReP3Seg.getText().equals("")				
				&& !textFieldImP2Seg.getText().equals("") && !textFieldImP3Seg.getText().equals("");		
		
		if(polosComPartesReaisDistintas || polosComPartesReaisIguais){
			return true;
		}else{			
			if(retornaMensagem)
				JOptionPane.showMessageDialog(frame, "Informe os polos (ou a matriz L) do observador de estados.");
			
			return false;
		}
	}
	
	private boolean allCamposMatrizKVazios(){
		return (textFieldK1.getText().equals("") && textFieldK21.getText().equals("") && textFieldK22.getText().equals(""));
	}
	
	private boolean allCamposMatrizKPreenchidos(){
		return (!textFieldK1.getText().equals("") && !textFieldK21.getText().equals("") && !textFieldK22.getText().equals(""));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void inicializarComboTipoOnda(){
		JLabel lblTipoOnda = new JLabel("Tipo de Onda:");
		lblTipoOnda.setBounds(47, 65, 78, 18);
		abaOpcoesEntrada.add(lblTipoOnda);
		
		comboTipoOnda = new JComboBox(TipoOnda.getItensComboTiposOnda());
		comboTipoOnda.setEnabled(false);
		comboTipoOnda.setBounds(126, 65, 151, 18);
		comboTipoOnda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboTipoOnda.getSelectedItem() != null && comboTipoOnda.getSelectedItem().equals(TipoOnda.SELECIONE.getDescricao())){
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
				}else if(comboTipoOnda.getSelectedItem() != null && comboTipoOnda.getSelectedItem().equals(TipoOnda.QUADRADA.getDescricao())){
					lblAmplitude.setText("Amplitude:");
					amplitude.setEnabled(true);
					amplitudeMin.setEnabled(false);
					amplitudeMin.setValue(0);
					lblPeriodo.setText("Período:");
					periodo.setEnabled(true);
					periodoMin.setEnabled(false);
					periodoMin.setValue(0);
					offSet.setEnabled(true);
				}else if(comboTipoOnda.getSelectedItem() != null && comboTipoOnda.getSelectedItem().equals(TipoOnda.DEGRAU.getDescricao())){
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
				}else if(comboTipoOnda.getSelectedItem() != null && comboTipoOnda.getSelectedItem().equals(TipoOnda.ALEATORIA.getDescricao())){
					lblAmplitude.setText("Amplitude (Máx):");
					amplitude.setEnabled(true);
					amplitudeMin.setEnabled(true);
					lblPeriodo.setText("Período (Máx):");
					periodo.setEnabled(true);
					periodoMin.setEnabled(true);
					offSet.setEnabled(false);
					offSet.setValue(0);
				}else if(comboTipoOnda.getSelectedItem() != null && comboTipoOnda.getSelectedItem().equals(TipoOnda.SENOIDAL.getDescricao())){
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
		lblTipoDeControle.setBounds(47, 215, 91, 18);
		abaOpcoesEntrada.add(lblTipoDeControle);
		
		comboTipoControle = new JComboBox(TipoControle.getItensComboTiposControle());
		comboTipoControle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboTipoControle.getSelectedItem() != null && comboTipoControle.getSelectedItem().equals(TipoControle.CASCATA.getDescricao())){
					comboTipoControladorMestre.setEnabled(true);
					comboTipoControladorEscravo.setEnabled(true);
					
					textFieldP1Seg.setEnabled(false);
					textFieldP1Seg.setText("");
					textFieldReP2Seg.setEnabled(false);
					textFieldReP2Seg.setText("");
					textFieldImP2Seg.setEnabled(false);
					textFieldImP2Seg.setText("");
					textFieldReP3Seg.setEnabled(false);
					textFieldReP3Seg.setText("");
					textFieldImP3Seg.setEnabled(false);
					textFieldImP3Seg.setText("");
					textFieldK1.setEnabled(false);
					textFieldK1.setText("");					
					textFieldK21.setEnabled(false);
					textFieldK21.setText("");
					textFieldK22.setEnabled(false);
					textFieldK22.setText("");
					
					dados.setTipoDeControle(TipoControle.CASCATA.getDescricao());
				}else if(comboTipoControle.getSelectedItem() != null && comboTipoControle.getSelectedItem().equals(TipoControle.SIMPLES.getDescricao())){					
					comboTipoControladorMestre.setEnabled(true);
					comboTipoControladorEscravo.setEnabled(false);
					
					comboTipoControladorEscravo.setSelectedItem(TipoControlador.SELECIONE.getDescricao());

					chckbxWindUpEscravo.setSelected(false);
					
					desabilitarParamsControladorEscravo(true, true);
					
					textFieldP1Seg.setEnabled(false);
					textFieldP1Seg.setText("");
					textFieldReP2Seg.setEnabled(false);
					textFieldReP2Seg.setText("");
					textFieldImP2Seg.setEnabled(false);
					textFieldImP2Seg.setText("");
					textFieldReP3Seg.setEnabled(false);
					textFieldReP3Seg.setText("");
					textFieldImP3Seg.setEnabled(false);
					textFieldImP3Seg.setText("");
					textFieldK1.setEnabled(false);
					textFieldK1.setText("");					
					textFieldK21.setEnabled(false);
					textFieldK21.setText("");
					textFieldK22.setEnabled(false);
					textFieldK22.setText("");
					
					dados.setTipoDeControle(TipoControle.SIMPLES.getDescricao());
				}else if(comboTipoControle.getSelectedItem() != null && comboTipoControle.getSelectedItem().equals(TipoControle.SEGUIDOR_REFERENCIA.getDescricao())){					
					comboTipoControladorMestre.setEnabled(false);
					comboTipoControladorEscravo.setEnabled(false);
					
					comboTipoControladorMestre.setSelectedItem(TipoControlador.SELECIONE.getDescricao());
					comboTipoControladorEscravo.setSelectedItem(TipoControlador.SELECIONE.getDescricao());

					chckbxWindUpMestre.setSelected(false);
					chckbxWindUpEscravo.setSelected(false);
					
					desabilitarParamsControladorMestre(true, true);
					desabilitarParamsControladorEscravo(true, true);
					
					textFieldP1Seg.setEnabled(true);
					textFieldReP2Seg.setEnabled(true);
					textFieldImP2Seg.setEnabled(true);
					textFieldReP3Seg.setEnabled(true);
					textFieldImP3Seg.setEnabled(true);					
					textFieldK1.setEnabled(true);
					textFieldK21.setEnabled(true);
					textFieldK22.setEnabled(true);
					
					dados.setTipoDeControle(TipoControle.SEGUIDOR_REFERENCIA.getDescricao());
				}else{
					comboTipoControladorMestre.setEnabled(false);
					comboTipoControladorMestre.setSelectedItem(TipoControlador.SELECIONE.getDescricao());
					comboTipoControladorEscravo.setEnabled(false);
					comboTipoControladorEscravo.setSelectedItem(TipoControlador.SELECIONE.getDescricao());
					
					chckbxWindUpMestre.setSelected(false);
					chckbxWindUpEscravo.setSelected(false);
					
					desabilitarParamsControladorMestre(true, true);
					desabilitarParamsControladorEscravo(true, true);
					
					textFieldP1Seg.setEnabled(false);
					textFieldP1Seg.setText("");
					textFieldReP2Seg.setEnabled(false);
					textFieldReP2Seg.setText("");
					textFieldImP2Seg.setEnabled(false);
					textFieldImP2Seg.setText("");
					textFieldReP3Seg.setEnabled(false);
					textFieldReP3Seg.setText("");
					textFieldImP3Seg.setEnabled(false);
					textFieldImP3Seg.setText("");
					textFieldK1.setEnabled(false);
					textFieldK1.setText("");					
					textFieldK21.setEnabled(false);
					textFieldK21.setText("");
					textFieldK22.setEnabled(false);
					textFieldK22.setText("");
					
					dados.setTipoDeControle(TipoControle.SEM_CONTROLE.getDescricao());
				}
			}
		});
		comboTipoControle.setEnabled(false);
		comboTipoControle.setBounds(148, 215, 151, 18);
	}
		
	private void inicializarPainelTiposMalha(){
		panelTipoMalha = new JPanel();
		panelTipoMalha.setBounds(5, 2, 160, 55);
		panelTipoMalha.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Malha", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panelTipoMalha.setLayout(null);	
				
		rdbtnAberta = new JRadioButton(TipoMalha.MALHA_ABERTA.getDescricaoReduzida());
		rdbtnAberta.setEnabled(false);
		rdbtnAberta.setBounds(6, 24, 67, 18);		
		rdbtnAberta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnFechada.setSelected(false);
				
				comboTipoControladorMestre.setEnabled(false);
				comboTipoControladorMestre.setSelectedItem(TipoControlador.SELECIONE.getDescricao());
				
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
		
		rdbtnFechada = new JRadioButton(TipoMalha.MALHA_FECHADA.getDescricaoReduzida());
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
		panelDadosSinal.setBounds(5, 92, 325, 113);		
		panelDadosSinal.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Par\u00E2metros do Sinal", TitledBorder.RIGHT, TitledBorder.TOP, null, Color.GRAY));
		panelDadosSinal.setLayout(null);
		
		lblAmplitude = new JLabel("Amplitude:");
		lblAmplitude.setBounds(11, 17, 84, 20);
		panelDadosSinal.add(lblAmplitude);
		
		amplitude = new JSpinner();
		amplitude.setEnabled(false);
		amplitude.setBounds(92, 17, 51, 20);
		//amplitude.setModel(new SpinnerNumberModel(0.0, -4.0, 4.0, 0.0));
		amplitude.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		panelDadosSinal.add(amplitude);
		
		JLabel lblAmplitudeMin = new JLabel("Amplitude (M\u00EDn):");
		lblAmplitudeMin.setBounds(177, 46, 85, 20);
		panelDadosSinal.add(lblAmplitudeMin);
		
		amplitudeMin = new JSpinner();
		amplitudeMin.setEnabled(false);
		amplitudeMin.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		amplitudeMin.setBounds(264, 46, 51, 20);
		panelDadosSinal.add(amplitudeMin);
		
		lblPeriodo = new JLabel("Per\u00EDodo:");
		lblPeriodo.setBounds(11, 46, 85, 20);
		panelDadosSinal.add(lblPeriodo);
		
		periodo = new JSpinner();
		periodo.setEnabled(false);
		periodo.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		periodo.setBounds(93, 46, 51, 20);
		panelDadosSinal.add(periodo);
		
		JLabel lblPeriodoMin = new JLabel("Per\u00EDodo (M\u00EDn):");
		lblPeriodoMin.setBounds(177, 80, 85, 20);
		panelDadosSinal.add(lblPeriodoMin);
		
		periodoMin = new JSpinner();
		periodoMin.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		periodoMin.setEnabled(false);
		periodoMin.setBounds(264, 80, 51, 20);
		panelDadosSinal.add(periodoMin);
		
		JLabel lblOffSet = new JLabel("Off-Set:");
		lblOffSet.setBounds(11, 80, 67, 20);
		panelDadosSinal.add(lblOffSet);
		
		offSet = new JSpinner();
		offSet.setBounds(93, 80, 51, 20);
		offSet.setEnabled(false);
		offSet.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		panelDadosSinal.add(offSet);		
	}
	
	private void inicializarPainelParamsControlador(){
		panelParamsControladorMestre = new JPanel();
		panelParamsControladorMestre.setBounds(5, 244, 325, 135);
		panelParamsControladorMestre.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Par\u00E2metros do Controlador Mestre", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
		panelParamsControladorMestre.setLayout(null);
		inicializarPainelParamsMestre();
		abaOpcoesEntrada.add(panelParamsControladorMestre);
	
		panelParamsControladorEscravo = new JPanel();
		panelParamsControladorEscravo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Par\u00E2metros do Controlador Escravo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
		panelParamsControladorEscravo.setBounds(5, 385, 325, 135);
		panelParamsControladorEscravo.setLayout(null);
		inicializarPainelParamsEscravo();
		abaOpcoesEntrada.add(panelParamsControladorEscravo);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void inicializarPainelParamsMestre(){
		JLabel lblTipoControladorMestre = new JLabel("Tipo do Controlador:");
		lblTipoControladorMestre.setBounds(10, 23, 100, 15);
		panelParamsControladorMestre.add(lblTipoControladorMestre);
		
		comboTipoControladorMestre = new JComboBox(TipoControlador.getItensComboTiposControlador());
		comboTipoControladorMestre.setBounds(111, 23, 151, 18);
		comboTipoControladorMestre.setEnabled(false);
		comboTipoControladorMestre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboTipoControladorMestre.getSelectedItem().equals(TipoControlador.SELECIONE.getDescricao()) ||
						comboTipoControladorMestre.getSelectedItem().equals(TipoControlador.SEM_CONTROLADOR.getDescricao())){
					
					labelKpMestre.setText("");
					labelKiMestre.setText("");
					labelKdMestre.setText("");
					labelTaliMestre.setText("");
					labelTaldMestre.setText("");
					labelTaltMestre.setText("");
					
					chckbxWindUpMestre.setSelected(false);
				}else if(comboTipoControladorMestre.getSelectedItem().equals(TipoControlador.P.getDescricao())){
					labelKiMestre.setText("");
					labelKdMestre.setText("");
					labelTaliMestre.setText("");
					labelTaldMestre.setText("");
				}else if(comboTipoControladorMestre.getSelectedItem().equals(TipoControlador.PI.getDescricao())){
					labelKdMestre.setText("");
					labelTaldMestre.setText("");
				}else if(comboTipoControladorMestre.getSelectedItem().equals(TipoControlador.PD.getDescricao())){					
					labelKiMestre.setText("");
					labelTaliMestre.setText("");				
				}
			}
		});
		panelParamsControladorMestre.add(comboTipoControladorMestre);
		
		JLabel lblKp = new JLabel("Kp:");
		lblKp.setBounds(10, 49, 22, 15);
		panelParamsControladorMestre.add(lblKp);
		
		labelKpMestre = new JLabel();
		labelKpMestre.setBounds(36, 49, 66, 15);
		panelParamsControladorMestre.add(labelKpMestre);		
		
		JLabel lblKi = new JLabel("Ki:");
		lblKi.setBounds(10, 78, 22, 15);
		panelParamsControladorMestre.add(lblKi);
		
		labelKiMestre = new JLabel();		
		labelKiMestre.setBounds(36, 78, 66, 15);	
		panelParamsControladorMestre.add(labelKiMestre);
		
		JLabel lblTali = new JLabel("\u03C4i:");
		lblTali.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		lblTali.setBounds(112, 78, 23, 15);
		panelParamsControladorMestre.add(lblTali);
		
		labelTaliMestre = new JLabel();		
		labelTaliMestre.setBounds(145, 78, 66, 15);		
		panelParamsControladorMestre.add(labelTaliMestre);
		
		JLabel lblKd = new JLabel("Kd:");
		lblKd.setBounds(10, 108, 22, 15);
		panelParamsControladorMestre.add(lblKd);
		
		labelKdMestre = new JLabel();
		labelKdMestre.setBounds(36, 108, 66, 15);
		panelParamsControladorMestre.add(labelKdMestre);
		
		JLabel lblTald = new JLabel("\u03C4d:");
		lblTald.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		lblTald.setBounds(112, 108, 23, 15);
		panelParamsControladorMestre.add(lblTald);
		
		labelTaldMestre = new JLabel();
		labelTaldMestre.setBounds(145, 108, 66, 15);
		panelParamsControladorMestre.add(labelTaldMestre);
		
		JLabel lblTalt = new JLabel("Tt:");
		lblTalt.setBounds(113, 49, 22, 15);
		panelParamsControladorMestre.add(lblTalt);

		labelTaltMestre = new JLabel();
		labelTaltMestre.setBounds(145, 49, 66, 15);
		panelParamsControladorMestre.add(labelTaltMestre);
		
		JLabel labelChamaConfiguracaoMestre = new JLabel("");
		labelChamaConfiguracaoMestre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				criaPaneilDadosControle(comboTipoControladorMestre, chckbxWindUpMestre, labelKpMestre, labelKiMestre, labelKdMestre,
						labelTaltMestre, labelTaliMestre, labelTaldMestre, true);
			}
		});
		labelChamaConfiguracaoMestre.setToolTipText("Clique para atualizar os par\u00E2metros");
		labelChamaConfiguracaoMestre.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1444113669_Pinion.png")));
		labelChamaConfiguracaoMestre.setBounds(283, 11, 32, 32);
		panelParamsControladorMestre.add(labelChamaConfiguracaoMestre);
		
		chckbxWindUpMestre = new JCheckBox("Wind Up");
		chckbxWindUpMestre.setBounds(240, 104, 75, 23);
		chckbxWindUpMestre.setToolTipText("Acionar Wind Up");
		chckbxWindUpMestre.setEnabled(false);
		panelParamsControladorMestre.add(chckbxWindUpMestre);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void inicializarPainelParamsEscravo(){
		JLabel lblTipoControladorEscravo = new JLabel("Tipo do Controlador:");
		lblTipoControladorEscravo.setBounds(10, 21, 100, 15);
		panelParamsControladorEscravo.add(lblTipoControladorEscravo);
		
		comboTipoControladorEscravo = new JComboBox(TipoControlador.getItensComboTiposControlador());
		comboTipoControladorEscravo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboTipoControladorEscravo.getSelectedItem().equals(TipoControlador.SELECIONE.getDescricao()) ||
					comboTipoControladorEscravo.getSelectedItem().equals(TipoControlador.SEM_CONTROLADOR.getDescricao())){					
					
					labelKpEscravo.setText("");
					labelKiEscravo.setText("");
					labelKdEscravo.setText("");
					labelTaliEscravo.setText("");
					labelTaldEscravo.setText("");
					labelTaltEscravo.setText("");
					
					chckbxWindUpEscravo.setSelected(false);					
				}else if(comboTipoControladorEscravo.getSelectedItem().equals(TipoControlador.P.getDescricao())){
					labelKiEscravo.setText("");
					labelKdEscravo.setText("");
					labelTaliEscravo.setText("");
					labelTaldEscravo.setText("");					
				}else if(comboTipoControladorEscravo.getSelectedItem().equals(TipoControlador.PI.getDescricao())){
					labelKdEscravo.setText("");
					labelTaldEscravo.setText("");
				}else if(comboTipoControladorEscravo.getSelectedItem().equals(TipoControlador.PD.getDescricao())){					
					labelKiEscravo.setText("");
					labelTaliEscravo.setText("");
				}
			}
		});
		comboTipoControladorEscravo.setEnabled(false);
		comboTipoControladorEscravo.setBounds(111, 21, 151, 18);
		panelParamsControladorEscravo.add(comboTipoControladorEscravo);
		
		JLabel lblKpEscravo = new JLabel("Kp:");
		lblKpEscravo.setBounds(10, 47, 22, 15);
		panelParamsControladorEscravo.add(lblKpEscravo);
		
		labelKpEscravo = new JLabel();		
		labelKpEscravo.setBounds(36, 47, 66, 15);
		panelParamsControladorEscravo.add(labelKpEscravo);
		
		JLabel lblKiEscravo = new JLabel("Ki:");
		lblKiEscravo.setBounds(10, 78, 22, 15);
		panelParamsControladorEscravo.add(lblKiEscravo);
		
		labelKiEscravo = new JLabel();
		labelKiEscravo.setBounds(36, 78, 66, 15);
		panelParamsControladorEscravo.add(labelKiEscravo);
		
		JLabel lblTaliEscravo = new JLabel("\u03C4i:");
		lblTaliEscravo.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		lblTaliEscravo.setBounds(112, 78, 23, 15);
		panelParamsControladorEscravo.add(lblTaliEscravo);
		
		labelTaliEscravo = new JLabel();
		labelTaliEscravo.setBounds(145, 78, 66, 15);
		panelParamsControladorEscravo.add(labelTaliEscravo);
		
		JLabel lblKdEscravo = new JLabel("Kd:");
		lblKdEscravo.setBounds(10, 108, 22, 15);
		panelParamsControladorEscravo.add(lblKdEscravo);
		
		labelKdEscravo = new JLabel();
		labelKdEscravo.setBounds(36, 108, 66, 15);
		panelParamsControladorEscravo.add(labelKdEscravo);
		
		JLabel lblTaldEscravo = new JLabel("\u03C4d:");
		lblTaldEscravo.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		lblTaldEscravo.setBounds(112, 108, 23, 15);
		panelParamsControladorEscravo.add(lblTaldEscravo);
		
		labelTaldEscravo = new JLabel();
		labelTaldEscravo.setBounds(145, 108, 66, 15);
		panelParamsControladorEscravo.add(labelTaldEscravo);
		
		JLabel lblTaltEscravo = new JLabel("Tt:");
		lblTaltEscravo.setBounds(113, 47, 22, 15);
		panelParamsControladorEscravo.add(lblTaltEscravo);
		
		labelTaltEscravo = new JLabel();
		labelTaltEscravo.setBounds(145, 47, 66, 15);
		panelParamsControladorEscravo.add(labelTaltEscravo);
		
		JLabel labelChamaConfiguracaoEscravo = new JLabel("");
		labelChamaConfiguracaoEscravo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				criaPaneilDadosControle(comboTipoControladorEscravo, chckbxWindUpEscravo, labelKpEscravo, labelKiEscravo, labelKdEscravo,
						labelTaltEscravo, labelTaliEscravo, labelTaldEscravo, false);
			}
		});
		labelChamaConfiguracaoEscravo.setToolTipText("Clique para atualizar os par\u00E2metros");
		labelChamaConfiguracaoEscravo.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1444113669_Pinion.png")));
		labelChamaConfiguracaoEscravo.setBounds(283, 11, 32, 32);
		panelParamsControladorEscravo.add(labelChamaConfiguracaoEscravo);
		
		chckbxWindUpEscravo = new JCheckBox("Wind Up");
		chckbxWindUpEscravo.setToolTipText("Acionar Wind Up");
		chckbxWindUpEscravo.setEnabled(false);
		chckbxWindUpEscravo.setBounds(240, 104, 75, 23);
		panelParamsControladorEscravo.add(chckbxWindUpEscravo);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void criaPaneilDadosConexao(){
		JTextField ip = new JTextFieldAlterado(false);
		ip.setBounds(100, 170, 140, 160);
		ip.setText(iPServidor.getText());
		
		JTextField porta = new JTextFieldAlterado(true);
		porta.setText(portaServidor.getText());
		 
		JComboBox leitura1 = new JComboBox(getItensComboLeituraEscrita());
		leitura1.setSelectedItem(Tela.this.leitura1.getText().equals("") ? "Selecione" : Integer.parseInt(Tela.this.leitura1.getText()));
		 
		JComboBox leitura2 = new JComboBox(getItensComboLeituraEscrita());
		leitura2.setSelectedItem(Tela.this.leitura2.getText().equals("") ? "Selecione" : Integer.parseInt(Tela.this.leitura2.getText()));
		 
		JComboBox escrita = new JComboBox(getItensComboLeituraEscrita());
		escrita.setSelectedItem(Tela.this.escrita.getText().equals("") ? "Selecione" : Integer.parseInt(Tela.this.escrita.getText()));
		 
		Object[] message = {"IP do Servidor:", ip, "Porta:", porta, "Leitura 1:", leitura1, "Leitura 2:", leitura2, "Escrita:", escrita}; 
		int option = JOptionPane.showConfirmDialog(null, message, "Dados de Conexão", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE); 
		 
		if (option == JOptionPane.OK_OPTION) { 
			iPServidor.setText(ip.getText());
			portaServidor.setText(porta.getText());
			
			Tela.this.leitura1.setText(leitura1.getSelectedItem().toString().equals("Selecione") ? "" : leitura1.getSelectedItem().toString());
			Tela.this.leitura2.setText(leitura2.getSelectedItem().toString().equals("Selecione") ? "" : leitura2.getSelectedItem().toString());
			Tela.this.escrita.setText(escrita.getSelectedItem().toString().equals("Selecione") ? "" : escrita.getSelectedItem().toString());
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void criaPaneilDadosControle(JComboBox comboTipoControlador, JCheckBox chckbxWindUp, JLabel labelKp, JLabel labelKi, JLabel labelKd, JLabel labelTalT, JLabel labelTalI, JLabel labelTalD, boolean isMestre){		
		final JTextField kP = new JTextFieldAlterado(6, false);
		final JTextField kI = new JTextFieldAlterado(6, false);				
		final JTextField kD = new JTextFieldAlterado(6, false);
		final JTextField tT = new JTextFieldAlterado(6, false);
		final JTextField tI = new JTextFieldAlterado(6, false);
		final JTextField tD = new JTextFieldAlterado(6, false);
		final JCheckBox windUp = new JCheckBox("Wind Up");
		
		windUp.setEnabled(rdbtnFechada.isSelected());
		windUp.setSelected(chckbxWindUp.isSelected());
				
		setEnableCamposControlador(comboTipoControlador, windUp, kP, kI, kD, tT, tI, tD);
		
		kP.setText(labelKp.getText());
		
		kI.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				try{
					double calculo = Double.parseDouble(kP.getText())/Double.parseDouble(kI.getText()); 
					tI.setText(setText(calculo + "", calculo < 0 ? 7 : 6));
				}catch (Exception e){}
			}
		});
		kI.setText(labelKi.getText());
		
		kD.setText(labelKd.getText());
		kD.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				try {
					double calculo = Double.parseDouble(kD.getText())/Double.parseDouble(kP.getText()); 
					tD.setText(setText(calculo + "", calculo < 0 ? 7 : 6));
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
					double calculo = Double.parseDouble(kP.getText())/Double.parseDouble(tI.getText());
					kI.setText(setText(calculo + "", calculo < 0 ? 7 : 6));
				}catch(Exception e){}
			}
		});
						
		tD.setText(labelTalD.getText());
		tD.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				try {
					double calculo = Double.parseDouble(kP.getText())*Double.parseDouble(tD.getText()); 
					kD.setText(setText(calculo + "", calculo < 0 ? 7 : 6));
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
		int option = JOptionPane.showConfirmDialog(null, message, "Parâmetros do Controlador" + (isMestre ? " Mestre" : " Escravo"), JOptionPane.OK_CANCEL_OPTION); 
		 
		if (option == JOptionPane.OK_OPTION) { 
			labelKp.setText(kP.getText());			
			labelKi.setText(kI.getText());
			labelKd.setText(kD.getText());
			labelTalT.setText(tT.getText());
			labelTalI.setText(tI.getText());
			labelTalD.setText(tD.getText());				
			
			chckbxWindUp.setSelected(windUp.isSelected());
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void setEnableCamposControlador(JComboBox tipoControlador, JCheckBox windUp, JTextField kP, JTextField kI, JTextField kD, JTextField tT, JTextField tI, JTextField tD){
		if(tipoControlador.getSelectedItem().equals(TipoControlador.SELECIONE.getDescricao()) || 
				tipoControlador.getSelectedItem().equals(TipoControlador.SEM_CONTROLADOR.getDescricao())){
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
			windUp.setEnabled(false);
		}else if(tipoControlador.getSelectedItem().equals(TipoControlador.P.getDescricao())){
			kP.setEnabled(true);
			kI.setEnabled(false);
			kI.setText("");
			kD.setEnabled(false);
			kD.setText("");
			tI.setEnabled(false);
			tI.setText("");
			tD.setEnabled(false);
			tD.setText("");
		}else if(tipoControlador.getSelectedItem().equals(TipoControlador.PI.getDescricao())){
			kP.setEnabled(true);
			kI.setEnabled(true);
			kD.setEnabled(false);
			kD.setText("");
			tI.setEnabled(true);
			tD.setEnabled(false);
			tD.setText("");
		}else if(tipoControlador.getSelectedItem().equals(TipoControlador.PD.getDescricao())){					
			kP.setEnabled(true);
			kI.setEnabled(false);
			kI.setText("");
			kD.setEnabled(true);
			tI.setEnabled(false);
			tI.setText("");
			tD.setEnabled(true);
		}else if(tipoControlador.getSelectedItem().equals(TipoControlador.PID.getDescricao())){
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
		panelGraficos.setBounds(347, 22, 793, 574);
		panelGraficos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Gr\u00E1ficos", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelGraficos.setLayout(null);
		
		inicializaPainelGrafico1();
		panelGraficos.add(panelGrafico1);
		
		inicializarPainelValoresAtuais();
		
		inicializaPainelGrafico2();
		panelGraficos.add(panelGrafico2);
		
		inicializarPainelCheckSinaisGraficos();
		
//		redimensionarPainelGrafico2(panelGrafico1, panelGrafico2);
	}
	
	private void inicializaPainelGrafico1(){
		panelGrafico1 = new JLayeredPane();
		panelGrafico1.setForeground(Color.WHITE);
		panelGrafico1.setBounds(8, 10, 656, 255);
		panelGrafico1.setBackground(Color.WHITE);
		panelGrafico1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGrafico1.setLayout(null);
		
		//redimensionarPainelGrafico1(panelGrafico1, panelGrafico2);
	}
	
	private void inicializaPainelGrafico2(){
		panelGrafico2 = new JLayeredPane();
		panelGrafico2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGrafico2.setBounds(8, 270, 656, 255);
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
			
			chckbxAcaoP.setEnabled(true);
			chckbxAcaoD.setEnabled(true);
			chckbxAcaoI.setEnabled(true);
			chckbxControleMestre.setEnabled(true);
			chckbxControleCSeguidor.setEnabled(true);
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
			comboTipoControle.setSelectedItem(TipoControle.SELECIONE.getDescricao());
			
	//		rdbtnTanque1.setEnabled(false);
		//	rdbtnTanque1.setSelected(false);
			//rdbtnTanque2.setEnabled(false);
		//	rdbtnTanque2.setSelected(false);

			comboTipoOnda.setEnabled(false);
			comboTipoOnda.setSelectedItem(TipoOnda.SELECIONE.getDescricao());
			
			comboTipoControladorMestre.setEnabled(false);
			comboTipoControladorMestre.setSelectedItem(TipoControlador.SELECIONE.getDescricao());
			
			comboTipoControladorEscravo.setEnabled(false);
			comboTipoControladorEscravo.setSelectedItem(TipoControlador.SELECIONE.getDescricao());
			
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
			
			chckbxAcaoP.setEnabled(false);
			chckbxAcaoP.setVisible(false);
			chckbxAcaoP.setSelected(false);
			
			chckbxAcaoD.setEnabled(false);
			chckbxAcaoD.setVisible(false);
			chckbxAcaoD.setSelected(false);
			
			chckbxAcaoI.setEnabled(false);
			chckbxAcaoI.setVisible(false);
			chckbxAcaoI.setSelected(false);
			
			chckbxControleMestre.setEnabled(false);
			chckbxControleMestre.setVisible(false);
			chckbxControleMestre.setSelected(false);
			
			chckbxControleCSeguidor.setEnabled(false);
			chckbxControleCSeguidor.setVisible(false);
			chckbxControleCSeguidor.setSelected(false);
			
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
			
			resetarPainelObservadorEstados();
			
			resetarPainelSeguidorReferencia();
			
			lblExibirCheckSinalGrafico1.setIcon(new ImageIcon(Tela.class.getResource("/Icons/Chart-Curve-Add-32.png")));
			lblExibirCheckSinalGrafico2.setIcon(new ImageIcon(Tela.class.getResource("/Icons/Chart-Curve-Add-32.png")));
		}
	}
	
	private void resetarPainelObservadorEstados(){
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
		
		realizarObservacaoEstados.setEnabled(false);
		realizarObservacaoEstados.setSelected(false);
		
		textFieldReP1Anterior = "";
		textFieldReP2Anterior = "";
		textFieldImP1Anterior = "";
		textFieldImP2Anterior = "";
		textFieldL1Anterior = "";
		textFieldL2Anterior = "";
	}
	
	private void resetarPainelSeguidorReferencia(){
		textFieldP1Seg.setEnabled(false);
		textFieldP1Seg.setText("");
		
		textFieldReP2Seg.setEnabled(false);
		textFieldReP2Seg.setText("");
		
		textFieldImP3Seg.setEnabled(false);
		textFieldImP3Seg.setText("");
		
		textFieldImP3Seg.setEnabled(false);
		textFieldImP3Seg.setText("");
		
		textFieldK1.setEnabled(false);
		textFieldK1.setText("");
		
		textFieldK21.setEnabled(false);
		textFieldK21.setText("");
		
		textFieldK22.setEnabled(false);
		textFieldK22.setText("");
		
		textFieldP1SegAnterior = "";		
		textFieldReP2SegAnterior = "";
		textFieldImP2SegAnterior = "";		
		textFieldReP3SegAnterior = "";
		textFieldImP3SegAnterior = "";		
		textFieldK1Anterior = "";
		textFieldK21Anterior = "";
		textFieldK22Anterior = "";
	}
	
	private void inicializaCheckSinaisGrafico1(){
		chckbxTensaoSat = new JCheckBox("Tens\u00E3o Sat. ");
		chckbxTensaoSat.setBounds(670, 217, 79, 13);		
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
		chckbxTensCalc.setBounds(670, 201, 79, 13);
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
		chckbxP.setBounds(670, 153, 102, 13);
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
		chckbxI.setBounds(670, 169, 102, 13);
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
		chckbxD.setBounds(670, 185, 102, 13);
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
		
		chckbxAcaoP = new JCheckBox("A\u00E7\u00E3o P - Mestre");
		chckbxAcaoP.setEnabled(false);
		chckbxAcaoP.setVisible(false);
		chckbxAcaoP.setForeground(new Color(0, 191, 255));
		chckbxAcaoP.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxAcaoP.setBounds(670, 105, 97, 13);
		chckbxAcaoP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setProporcional_c2(chckbxAcaoP.isSelected());
				thread.setDadosGrafico(dados);
			}
		});		
		panelGraficos.add(chckbxAcaoP);
		
		chckbxAcaoI = new JCheckBox("Ac\u00E3o I - Mestre");
		chckbxAcaoI.setEnabled(false);
		chckbxAcaoI.setVisible(false);
		chckbxAcaoI.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxAcaoI.setBounds(670, 121, 97, 13);
		chckbxAcaoI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setIntegral_c2(chckbxAcaoI.isSelected());
				thread.setDadosGrafico(dados);
			}
		});		
		panelGraficos.add(chckbxAcaoI);
		
		chckbxAcaoD = new JCheckBox("A\u00E7\u00E3o D - Mestre");
		chckbxAcaoD.setEnabled(false);
		chckbxAcaoD.setVisible(false);
		chckbxAcaoD.setForeground(new Color(50, 205, 50));
		chckbxAcaoD.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxAcaoD.setBounds(670, 137, 97, 13);
		chckbxAcaoD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setDerivativo_c2(chckbxAcaoD.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		panelGraficos.add(chckbxAcaoD);
		
		chckbxControleMestre = new JCheckBox("Controle Mestre");
		chckbxControleMestre.setEnabled(false);
		chckbxControleMestre.setVisible(false);
		chckbxControleMestre.setForeground(Color.PINK);
		chckbxControleMestre.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxControleMestre.setBounds(670, 89, 97, 13);
		chckbxControleMestre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setSinalCascata(chckbxControleMestre.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		panelGraficos.add(chckbxControleMestre);
		
		chckbxControleCSeguidor = new JCheckBox("Controle c/ Seguidor");
		chckbxControleCSeguidor.setForeground(new Color(160, 82, 45));
		chckbxControleCSeguidor.setEnabled(false);
		chckbxControleCSeguidor.setVisible(false);
		chckbxControleCSeguidor.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxControleCSeguidor.setBounds(670, 73, 113, 13);
		chckbxControleCSeguidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setControleCSeguidor(chckbxControleCSeguidor.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		panelGraficos.add(chckbxControleCSeguidor);
	}
	
	private void inicializaCheckSinaisGrafico2(){				
		chckbxNivTanque1 = new JCheckBox("N\u00EDvel do Tanque 1");
		chckbxNivTanque1.setBounds(670, 391, 102, 13);
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
		chckbxNivTanque2.setBounds(670, 408, 102, 13);
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
		chckbxErro.setBounds(670, 375, 102, 13);
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
		chckbxSetPoint.setBounds(670, 359, 102, 13);		
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
		chckbxErroMestre.setBounds(670, 342, 102, 13);
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
		chckbxNivel1Estimado.setBounds(670, 424, 97, 13);
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
		chckbxNivel2Estimado.setBounds(670, 440, 97, 13);
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
		chckbxErroEstNivel1.setBounds(670, 456, 120, 13);
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
		chckbxErroEstNivel2.setBounds(670, 472, 120, 13);
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
		lblExibirCheckSinalGrafico1.setBounds(753, 225, 32, 43);
		lblExibirCheckSinalGrafico1.setToolTipText("Exibir Sinal");
		lblExibirCheckSinalGrafico1.setIcon(new ImageIcon(Tela.class.getResource("/Icons/Chart-Curve-Add-32.png")));		
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
					
					chckbxAcaoP.setVisible(true);
					chckbxAcaoI.setVisible(true);
					chckbxAcaoD.setVisible(true);
					chckbxControleMestre.setVisible(true);
					chckbxControleCSeguidor.setVisible(false);
				}else{
					lblExibirCheckSinalGrafico1.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Add-32.png")));
					chckbxTensCalc.setVisible(false);
					chckbxTensaoSat.setVisible(false);
					
					chckbxP.setVisible(false);
					chckbxI.setVisible(false);
					chckbxD.setVisible(false);
					
					chckbxAcaoP.setVisible(false);
					chckbxAcaoI.setVisible(false);
					chckbxAcaoD.setVisible(false);
					chckbxControleMestre.setVisible(false);
					chckbxControleCSeguidor.setVisible(false);
				}
			}
		});
		panelGraficos.add(lblExibirCheckSinalGrafico1);
		
		inicializaCheckSinaisGrafico1();
		
		lblExibirCheckSinalGrafico2 = new JLabel("");
		lblExibirCheckSinalGrafico2.setBounds(753, 485, 32, 43);
		lblExibirCheckSinalGrafico2.setIcon(new ImageIcon(Tela.class.getResource("/Icons/Chart-Curve-Add-32.png")));
		lblExibirCheckSinalGrafico2.setToolTipText("Exibir Sinal");
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
		panelGraficos.add(lblExibirCheckSinalGrafico2);
		
		inicializaCheckSinaisGrafico2();
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
	
	@SuppressWarnings("unchecked")
	private void recarregaComboTipoControle(){
		for(TipoControle tc : TipoControle.values()){
			comboTipoControle.addItem(tc.getDescricao());
		}
		
		comboTipoControladorMestre.setSelectedItem(TipoControlador.SELECIONE.getDescricao());
	}
}