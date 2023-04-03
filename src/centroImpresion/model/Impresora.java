package centroImpresion.model;

public class Impresora {

	private EstadoImpresora estadoImpresora;
	private String nombre;
	private String marca;

	public Impresora(EstadoImpresora estadoImpresora, String nombre, String marca) {
		super();
		this.estadoImpresora = estadoImpresora;
		this.nombre = nombre;
		this.marca = marca;
	}

	public Impresora() {

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
	public void imprimirDocumento(Documento documento) {
		// TODO Auto-generated method stub

	}

	public boolean isActiva() {
		return getEstadoImpresora().equals(EstadoImpresora.ACTIVO);
	}


}
