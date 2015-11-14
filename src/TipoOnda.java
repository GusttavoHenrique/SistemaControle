public enum TipoOnda {
	SELECIONE(0, "Selecione"), QUADRADA(1, "Quadrada"), DEGRAU(2, "Degrau"), ALEATORIA(3, "Aleatória"),	
	SENOIDAL(4, "Senoidal"), DENTE_SERRA(5, "Dente de Serra");

	public int id;
	
	public String descricao;
	
	TipoOnda(int id, String descricao) {
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

	public static Object[] getItensComboTiposOnda() {
		String[] tiposControle = {
				SELECIONE.getDescricao(),
				QUADRADA.getDescricao(),
				DEGRAU.getDescricao(), 
				ALEATORIA.getDescricao(),
				SENOIDAL.getDescricao(),
				DENTE_SERRA.getDescricao()
				};
		
		return tiposControle;
	}
}
