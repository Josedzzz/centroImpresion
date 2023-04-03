package centroImpresion.model;

public class Documento implements Comparable<Documento>{

	private String titulo;
	private EstadoDocumento estadoDocumento;
	private int prioridad;
	private String texto;

	public Documento(String titulo, EstadoDocumento estadoDocumento, int prioridad, String texto) {
		super();
		this.titulo = titulo;
		this.estadoDocumento = estadoDocumento;
		this.prioridad = prioridad;
		this.texto = texto;
	}

	public Documento() {

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
