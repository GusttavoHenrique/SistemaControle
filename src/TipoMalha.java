
public enum TipoMalha {
	MALHA_ABERTA(1, "Malha Aberta", "Aberta"), MALHA_FECHADA(2, "Malha Fechada", "Fechada");

	public int id;
	
	public String descricao;
	
	public String descricaoReduzida;
	
	TipoMalha(int id, String descricao, String descricaoReduzida) {
		this.id = id;
		this.descricao = descricao;
		this.descricaoReduzida = descricaoReduzida; 
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

	public String getDescricaoReduzida() {
		return descricaoReduzida;
	}

	public void setDescricaoReduzida(String descricaoReduzida) {
		this.descricaoReduzida = descricaoReduzida;
	}
}
