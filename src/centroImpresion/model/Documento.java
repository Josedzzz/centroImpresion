package centroImpresion.model;

public class Documento implements Comparable<Documento>{

	private String codigoDocumento; //Es la marca diferenciadora los documentos
	private String titulo;
	private EstadoDocumento estadoDocumento;
	private int prioridad;
	private String texto; //Se comporta como la dirección del documento

	public Documento(String codigoDocumento, String titulo, EstadoDocumento estadoDocumento, int prioridad, String texto) {
		super();
		this.codigoDocumento = codigoDocumento;
		this.titulo = titulo;
		this.estadoDocumento = estadoDocumento;
		this.prioridad = prioridad;
		this.texto = texto;
	}

	public Documento() {

	}

	public String getCodigoDocumento() {
		return codigoDocumento;
	}

	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public EstadoDocumento getEstadoDocumento() {
		return estadoDocumento;
	}

	public void setEstadoDocumento(EstadoDocumento estadoDocumento) {
		this.estadoDocumento = estadoDocumento;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

//-----------------------------------------------------------------------------------------------------------

	@Override
	public int compareTo(Documento documento) {
		return documento.getPrioridad() - this.getPrioridad();
	}

}
