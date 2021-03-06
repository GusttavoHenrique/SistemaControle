import javax.swing.JLabel;

public class Dados {
	private int pinoDeLeitura1, pinoDeLeitura2, pinoDeEscrita;
	private double Amplitude, amplitudeMinima, periodo, periodoMinino, offset;	
	
	private String tipoSinal;
	private String tipoMalha;
	private String tipoDeControle;
	private String tipoDeControlador, tipoDeControladorEscravo;
	
	private double tempoDePico, tempoDeSubida, sobreSinalMax, settleTempo;
	
	private boolean windUP, windUpEscravo, tensao, tensaoSat, nivel1, nivel2, setPoint, proporcional, integral, derivativo, erroMesmo;
	private boolean faixa2, faixa5, faixa7, faixa10, tanque1, tanque2;
	private boolean proporcional_c2, integral_c2, derivativo_c2, sinalCascata, controleCSeguidor, Erro_c1, tanque_Seco = true;
	
	private boolean observando = false;
	private boolean nvlUmEstimado, nvlDoisEstimado, erroEstimacaoUm, erroEstimacaoDois;
	
	private double KP, KI, KD, Tt;	
	private double KpEscravo, KiEscravo, KdEscravo, TtEscravo;
	
	private double PV;
	private double PV_two;
	private double VP;
	
	private double duracao;
	
	private JLabel tPico, tSubida, tAcomoda, nivelPico;

	private double fatSup = 1, fatInf = 0; 

	private boolean picoAbs;
	
	private double l1, l2;
	
	private double parteReP1, parteImP1;	
	private double parteReP2, parteImP2;
	
	private double parteReP1Seg;
	private double parteReP2Seg, parteImP2Seg;
	private double parteReP3Seg, parteImP3Seg;
	private double k1, k21, k22;
	
	public Dados(){
	}

	public double getPV() {
		return PV;
	}

	public void setPV(double PV) {
		this.PV = PV;
	}

	public double getVP() {
		return VP;
	}
	public void setVP(double VP) {
		this.VP = VP;
	}

	public String getTipoSinal() {
		return tipoSinal;
	}

	public void setTipoSinal(String tipoSinal) {
		this.tipoSinal = tipoSinal;
	}

	public String getTipoMalha() {
		return tipoMalha;
	}

	public void setTipoMalha(String tipoMalha) {
		this.tipoMalha = tipoMalha;
	}

	public double getAmplitude() {
		return Amplitude;
	}

	public void setAmplitude(double amplitude) {
		Amplitude = amplitude;
	}

	public int getPinoDeLeitura1() {
		return pinoDeLeitura1;
	}

	public void setPinoDeLeitura1(int pinoDeLeitura1) {
		this.pinoDeLeitura1 = pinoDeLeitura1;
	}

	public int getPinoDeLeitura2() {
		return pinoDeLeitura2;
	}

	public void setPinoDeLeitura2(int pinoDeLeitura2) {
		this.pinoDeLeitura2 = pinoDeLeitura2;
	}

	public int getPinoDeEscrita() {
		return pinoDeEscrita;
	}

	public void setPinoDeEscrita(int pinoDeEscrita) {
		this.pinoDeEscrita = pinoDeEscrita;
	}

	public double getDuracao() {
		return duracao;
	}

	public void setDuracao(double duracao) {
		this.duracao = duracao;
	}

	public double getPeriodo() {
		return periodo;
	}

	public void setPeriodo(double periodo) {
		this.periodo = periodo;
	}

	public double getOffset() {
		return offset;
	}

	public void setOffset(double offset) {
		this.offset = offset;
	}

	public double getAmplitudeMinima() {
		return amplitudeMinima;
	}

	public void setAmplitudeMinima(double amplitudeMinima) {
		this.amplitudeMinima = amplitudeMinima;
	}

	public double getPeriodoMinino() {
		return periodoMinino;
	}

	public void setPeriodoMinino(double periodoMinino) {
		this.periodoMinino = periodoMinino;
	}
	
	public boolean isTensao() {
		return tensao;
	}

	public void setTensao(boolean tensao) {
		this.tensao = tensao;
	}

	public boolean isTensaoSat() {
		return tensaoSat;
	}

	public void setTensaoSat(boolean tensaoSat) {
		this.tensaoSat = tensaoSat;
	}

	public boolean isNivel1() {
		return nivel1;
	}

	public void setNivel1(boolean nivel1) {
		this.nivel1 = nivel1;
	}

	public boolean isNivel2() {
		return nivel2;
	}

	public void setNivel2(boolean nivel2) {
		this.nivel2 = nivel2;
	}

	public boolean isSetPoint() {
		return setPoint;
	}

	public void setSetPoint(boolean setPoint) {
		this.setPoint = setPoint;
	}

	
	public boolean isErroMesmo() {
		return erroMesmo;
	}

	public void setErroMesmo(boolean erroMesmo) {
		this.erroMesmo = erroMesmo;
	}
	
	public boolean isProporcional() {
		return proporcional;
	}

	public void setProporcional(boolean proporcional) {
		this.proporcional = proporcional;
	}
	
	public boolean isIntegral() {
		return integral;
	}

	public void setIntegral(boolean integral) {
		this.integral = integral;
	}
	
	public boolean isDerivativo() {
		return derivativo;
	}

	public void setDerivativo(boolean derivativo) {
		this.derivativo = derivativo;
	}
	
	public boolean isWindUP() {
		return windUP;
	}

	public void setFaixa2(boolean faixa2) {
		this.faixa2 = faixa2;
	}

	public boolean isFaixa2() {
		return faixa2;
	}

	public void setFaixa5(boolean faixa5) {
		this.faixa5 = faixa5;
	}

	public boolean isFaixa5() {
		return faixa5;
	}


	public void setFaixa7(boolean faixa7) {
		this.faixa7 = faixa7;
	}

	public boolean isFaixa7() {
		return faixa7;
	}

	public void setFaixa10(boolean faixa10) {
		this.faixa10 = faixa10;
	}

	public boolean isFaixa10() {
		return faixa10;
	}

	
	public void setWindUP(boolean windUP) {
		this.windUP = windUP;
	}
	
	
	public double getKP() {
		return KP;
	}

	public void setKP(double kP) {
		KP = kP;
	}

	public double getKI() {
		return KI;
	}

	public void setKI(double kI) {
		KI = kI;
	}

	public double getKD() {
		return KD;
	}

	public void setKD(double kD) {
		KD = kD;
	}

	public String getTipoDeControle() {
		return tipoDeControle;
	}

	public void setTipoDeControle(String tipoDeControle) {
		this.tipoDeControle = tipoDeControle;
	}

	/*public boolean isComControle() {
		return comControle;
	}*/

	/*public void setComControle(boolean comControle) {
		this.comControle = comControle;
	}*/

	public double getTt() {
		return Tt;
	}

	public void setTt(double tt) {
		Tt = tt;
	}

	public double getTempoDePico() {
		return tempoDePico;
	}

	public void setTempoDePico(double tempoDePico) {
		this.tempoDePico = tempoDePico;
	}

	public double getTempoDeSubida() {
		return tempoDeSubida;
	}

	public void setTempoDeSubida(double tempoDeSubida) {
		this.tempoDeSubida = tempoDeSubida;
	}

	public double getSobreSinalMax() {
		return sobreSinalMax;
	}

	public void setSobreSinalMax(double sobreSinalMax) {
		this.sobreSinalMax = sobreSinalMax;
	}

	public double getSettleTempo() {
		return settleTempo;
	}

	public void setSettleTempo(double settleTempo) {
		this.settleTempo = settleTempo;
	}

	public JLabel gettPico() {
		return tPico;
	}

	public void settPico(JLabel tPico) {
		this.tPico = tPico;
	}

	public JLabel gettSubida() {
		return tSubida;
	}

	public void settSubida(JLabel tSubida) {
		this.tSubida = tSubida;
	}

	public JLabel gettAcomoda() {
		return tAcomoda;
	}

	public void settAcomoda(JLabel tAcomoda) {
		this.tAcomoda = tAcomoda;
	}

	public JLabel getNivelPico() {
		return nivelPico;
	}

	public void setNivelPico(JLabel nivelPico) {
		this.nivelPico = nivelPico;
	}

	
	public double getFatSup() {
		return fatSup;
	}

	public void setFatSup(double fatSup) {
		this.fatSup = fatSup;
	}

	public double getFatInf() {
		return fatInf;
	}

	public void setFatInf(double fatInf) {
		this.fatInf = fatInf;
	}

	public boolean isPicoAbs() {
		return picoAbs;
	}

	public void setPicoAbs(boolean picoAbs) {
		this.picoAbs = picoAbs;
	}

	public double getPV_two() {
		return PV_two;
	}

	public void setPV_two(double pV_two) {
		PV_two = pV_two;
	}

	public boolean isTanque1() {
		return tanque1;
	}

	public void setTanque1(boolean tanque1) {
		this.tanque1 = tanque1;
	}

	public boolean isTanque2() {
		return tanque2;
	}

	public void setTanque2(boolean tanque2) {
		this.tanque2 = tanque2;
	}

	public String getTipoDeControlador() {
		return tipoDeControlador;
	}

	public void setTipoDeControlador(String tipoDeControlador) {
		this.tipoDeControlador = tipoDeControlador;
	}

	public boolean isWindUpEscravo() {
		return windUpEscravo;
	}

	public void setWindUpEscravo(boolean windUpEscravo) {
		this.windUpEscravo = windUpEscravo;
	}

	public String getTipoDeControladorEscravo() {
		return tipoDeControladorEscravo;
	}

	public void setTipoDeControladorEscravo(String tipoDeControladorEscravo) {
		this.tipoDeControladorEscravo = tipoDeControladorEscravo;
	}

	public double getKpEscravo() {
		return KpEscravo;
	}

	public void setKpEscravo(double kpEscravo) {
		KpEscravo = kpEscravo;
	}

	public double getKiEscravo() {
		return KiEscravo;
	}

	public void setKiEscravo(double kiEscravo) {
		KiEscravo = kiEscravo;
	}

	public double getKdEscravo() {
		return KdEscravo;
	}

	public void setKdEscravo(double kdEscravo) {
		KdEscravo = kdEscravo;
	}

	public double getTtEscravo() {
		return TtEscravo;
	}

	public void setTtEscravo(double ttEscravo) {
		TtEscravo = ttEscravo;
	}

	public boolean isProporcional_c2() {
		return proporcional_c2;
	}

	public void setProporcional_c2(boolean proporcional_c2) {
		this.proporcional_c2 = proporcional_c2;
	}

	public boolean isIntegral_c2() {
		return integral_c2;
	}

	public void setIntegral_c2(boolean integral_c2) {
		this.integral_c2 = integral_c2;
	}

	public boolean isDerivativo_c2() {
		return derivativo_c2;
	}

	public void setDerivativo_c2(boolean derivativo_c2) {
		this.derivativo_c2 = derivativo_c2;
	}

	public boolean isSinalCascata() {
		return sinalCascata;
	}

	public void setSinalCascata(boolean sinalCascata) {
		this.sinalCascata = sinalCascata;
	}

	public boolean isErro_c1() {
		return Erro_c1;
	}

	public void setErro_c1(boolean Erro_c1) {
		this.Erro_c1 = Erro_c1;
	}

	public boolean isTanque_Seco() {
		return tanque_Seco;
	}

	public void setTanque_Seco(boolean tanque_Seco) {
		this.tanque_Seco = tanque_Seco;
	}

	public double getL1() {
		return l1;
	}

	public void setL1(double l1) {
		this.l1 = l1;
	}

	public double getL2() {
		return l2;
	}

	public void setL2(double l2) {
		this.l2 = l2;
	}

	public double getParteReP1() {
		return parteReP1;
	}

	public void setParteReP1(double parteReP1) {
		this.parteReP1 = parteReP1;
	}

	public double getParteImP1() {
		return parteImP1;
	}

	public void setParteImP1(double parteImP1) {
		this.parteImP1 = parteImP1;
	}

	public double getParteReP2() {
		return parteReP2;
	}

	public void setParteReP2(double parteReP2) {
		this.parteReP2 = parteReP2;
	}

	public double getParteImP2() {
		return parteImP2;
	}

	public void setParteImP2(double parteImP2) {
		this.parteImP2 = parteImP2;
	}

	public boolean isObservando() {
		return observando;
	}

	public void setObservando(boolean observando) {
		this.observando = observando;
	}

	public boolean isNvlUmEstimado() {
		return nvlUmEstimado;
	}

	public void setNvlUmEstimado(boolean nvlUmEstimado) {
		this.nvlUmEstimado = nvlUmEstimado;
	}

	public boolean isNvlDoisEstimado() {
		return nvlDoisEstimado;
	}

	public void setNvlDoisEstimado(boolean nvlDoisEstimado) {
		this.nvlDoisEstimado = nvlDoisEstimado;
	}


	public boolean isErroEstimacaoUm() {
		return erroEstimacaoUm;
	}

	public void setErroEstimacaoUm(boolean erroEstimacaoUm) {
		this.erroEstimacaoUm = erroEstimacaoUm;
	}

	public boolean isErroEstimacaoDois() {
		return erroEstimacaoDois;
	}

	public void setErroEstimacaoDois(boolean erooEstimacaoDois) {
		this.erroEstimacaoDois = erooEstimacaoDois;
	}

	public boolean isControleCSeguidor() {
		return controleCSeguidor;
	}

	public void setControleCSeguidor(boolean controleCSeguidor) {
		this.controleCSeguidor = controleCSeguidor;
	}

	public double getParteReP1Seg() {
		return parteReP1Seg;
	}

	public void setParteReP1Seg(double parteReP1Seg) {
		this.parteReP1Seg = parteReP1Seg;
	}

	public double getParteReP2Seg() {
		return parteReP2Seg;
	}

	public void setParteReP2Seg(double parteReP2Seg) {
		this.parteReP2Seg = parteReP2Seg;
	}

	public double getParteImP2Seg() {
		return parteImP2Seg;
	}

	public void setParteImP2Seg(double parteImP2Seg) {
		this.parteImP2Seg = parteImP2Seg;
	}

	public double getParteReP3Seg() {
		return parteReP3Seg;
	}

	public void setParteReP3Seg(double parteReP3Seg) {
		this.parteReP3Seg = parteReP3Seg;
	}

	public double getParteImP3Seg() {
		return parteImP3Seg;
	}

	public void setParteImP3Seg(double parteImP3Seg) {
		this.parteImP3Seg = parteImP3Seg;
	}

	public double getK1() {
		return k1;
	}

	public void setK1(double k1) {
		this.k1 = k1;
	}

	public double getK21() {
		return k21;
	}

	public void setK21(double k21) {
		this.k21 = k21;
	}

	public double getK22() {
		return k22;
	}

	public void setK22(double k22) {
		this.k22 = k22;
	}
}