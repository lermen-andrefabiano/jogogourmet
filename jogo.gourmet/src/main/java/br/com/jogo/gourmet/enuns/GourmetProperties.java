package br.com.jogo.gourmet.enuns;

public enum GourmetProperties {

	TITULO("gourmet.titulo"),
	DIALOGO_GOSTO("gourmet.dialogo.gosto"),
	OK("gourmet.ok"),	
	MASSA("gourmet.massa"),
	LASANHA("gourmet.lasanha"),	
	BOLO_CHOCOLATE("gourmet.bolo.chocolate"),
	PRATO_PENSOU("gourmet.prato.pensou"),
	CONFIRMACAO("gourmet.confirmacao"),
	ACERTEI_NOVO("gourmet.acertei.novo"),
	ACERTEI("gourmet.acertei"),
	QUAL_PRATO("gourmet.qual.prato"),
	PRATO("gourmet.prato"),	
	TIPO_PRATO("gourmet.tipo.prato"),
	CARACTERISTICA_PRATO("gourmet.caracteristica.prato");

	private String key;

	GourmetProperties(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return this.key;
	}
}
