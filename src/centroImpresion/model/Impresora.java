package centroImpresion.model;

public class Impresora {

	private String codigoImpresora; //Es la marca diferenciadora de las impresoras
	private EstadoImpresora estadoImpresora;
	private String nombre;
	private String marca;

	public Impresora(String codigoImpresora, EstadoImpresora estadoImpresora, String nombre, String marca) {
		super();
		this.codigoImpresora = codigoImpresora;
		this.estadoImpresora = estadoImpresora;
		this.nombre = nombre;
		this.marca = marca;
	}

	public Impresora() {

	}

	public String getCodigoImpresora() {
		return codigoImpresora;
	}

	public void setCodigoImpresora(String codigoImpresora) {
		this.codigoImpresora = codigoImpresora;
	}

	public EstadoImpresora getEstadoImpresora() {
		return estadoImpresora;
	}

	public void setEstadoImpresora(EstadoImpresora estadoImpresora) {
		this.estadoImpresora = estadoImpresora;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}


//-----------------------------------------------------------------------------------------------------------
	/**
	 * devuelve el titulo del documento
	 * @param documento
	 * @return
	 */
	public String imprimirDocumento(Documento documento) {
		return documento.getTitulo();
	}

	/**
	 * me dice si una impresora esta activa o no
	 * @return
	 */
	public boolean isActiva() {
		return getEstadoImpresora().equals(EstadoImpresora.ACTIVO);
	}


}
