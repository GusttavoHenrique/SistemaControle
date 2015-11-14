public enum TipoControlador {
	SELECIONE(0, "Selecione"), P(1, "P"), PI(2, "PI"), PD(3, "PD"),	
	PID(4, "PID"), PI_D(5, "PI-D"), SEM_CONTROLADOR(6, "Sem Controlador");
	
	public int id;
	
	public String descricao;
	
	TipoControlador(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static Object[] getItensComboTiposControlador() {
		String[] tiposControle = {
				SELECIONE.getDescricao(),
				P.getDescricao(),
				PI.getDescricao(), 
				PD.getDescricao(),
				PID.getDescricao(),
				PI_D.getDescricao(),
				SEM_CONTROLADOR.getDescricao()
				};
		
		return tiposControle;
	}
}
