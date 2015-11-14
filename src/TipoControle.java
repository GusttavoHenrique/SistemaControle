public enum TipoControle {
	SELECIONE(0, "Selecione"), SEM_CONTROLE(1, "Sem Controle"), SIMPLES(2, "Simples"), CASCATA(3, "Cascata"), 
	SEGUIDOR_REFERENCIA(4, "Seguidor de Referência");
	
	public int id;
	
	public String descricao;
	
	TipoControle(int id, String descricao) {
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

	public static String[] getItensComboTiposControle() {
		String[] tiposControle = {
				SELECIONE.getDescricao(),
				SEM_CONTROLE.getDescricao(),
				SIMPLES.getDescricao(), 
				CASCATA.getDescricao(),
				SEGUIDOR_REFERENCIA.getDescricao()
				};
		
		return tiposControle;
	}
}
