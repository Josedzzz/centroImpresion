package centroImpresion.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CentroImpresion {

	private String codigo;
	private List<Documento> listaDocumentos = new ArrayList<Documento>();;
	private List<Impresora> listaImpresoras = new ArrayList<Impresora>();

	/*public CentroImpresion(String codigo) {
		super();
		this.codigo = codigo;
		this.listaDocumentos = new ArrayList<Documento>();
		this.listaImpresoras = new ArrayList<Impresora>();
	}*/

	public CentroImpresion(String codigo) {
		super();
		this.codigo = codigo;

		//Agrego datos para probar
		Documento documento1 = new Documento("1230", "Ejemplo1", EstadoDocumento.ENCOLA, 0, "texto1");
		Documento documento2 = new Documento("1231", "Ejemplo2", EstadoDocumento.ENCOLA, 5, "texto2");
		getListaDocumentos().add(documento2);
		getListaDocumentos().add(documento1);

		Impresora impresora1 = new Impresora("1234", EstadoImpresora.ACTIVO, "C4ASD", "Lg");
		Impresora impresora2 = new Impresora("4312", EstadoImpresora.INACTIVO, "C4DSA", "Hp");
		getListaImpresoras().add(impresora1);
		getListaImpresoras().add(impresora2);
	}

	public CentroImpresion() {

	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Documento> getListaDocumentos() {
		return listaDocumentos;
	}

	public void setListaDocumentos(List<Documento> listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
	}


	public List<Impresora> getListaImpresoras() {
		return listaImpresoras;
	}

	public void setListaImpresoras(List<Impresora> listaImpresoras) {
		this.listaImpresoras = listaImpresoras;
	}

	//-----------------------------------------------------------------------------------------------------------

	/**
	 * Imprime un documento dado una impresora
	 * @param impresora
	 * @return
	 */
	public String imprimirDocumento(Impresora impresora) {
		String documentoImpreso = "";
		if(!listaDocumentos.isEmpty()) {
			if(impresora.isActiva()) {
				documentoImpreso = impresora.imprimirDocumento(listaDocumentos.get(0));
				listaDocumentos.remove(listaDocumentos.get(0));
			}
		}
		return documentoImpreso;
	}

	/**
	 * Este metodo lo llama aplicacion
	 * @param codigo
	 * @param titulo
	 * @param prioridad
	 * @param texto
	 */
	public boolean agregarDocumento(String codigo, String titulo, int prioridad, String texto) {
		boolean fueCreado = verificarCodigoDocumento(codigo);
		if(fueCreado) {
			Documento documento = new Documento(codigo, titulo, EstadoDocumento.ENCOLA, prioridad, texto);
			getListaDocumentos().add(documento);
			Collections.sort(listaDocumentos);
		}

		return fueCreado;
	}

	/**
	 * Verifico si a un codigo le corresponde un documento
	 * @param codigo
	 * @return
	 */
	private boolean verificarCodigoDocumento(String codigo) {
		for(Documento documento : listaDocumentos) {
			if(codigo.equals(documento.getCodigoDocumento())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Obtiene el documento dado el código
	 * @param codigo
	 * @return
	 */
	public Documento obtenerDocumento(String codigo) {
		Documento documentoEncontrado = null;
		for(Documento documento : listaDocumentos) {
			if(documento.getCodigoDocumento().equals(codigo)) {
				documentoEncontrado = documento;
			}
		}
		return documentoEncontrado;
	}

	/**
	 * Elimina un documento dado el codigo de este, retorna true si lo pudo hacer
	 * @param codigoDocumento
	 * @return
	 */
	public boolean eliminarDocumento(String codigoDocumento) {
		Documento documentoEncontrado = obtenerDocumento(codigoDocumento);
		if(documentoEncontrado == null) {
			return false;
		}

		listaDocumentos.remove(documentoEncontrado);
		return true;
	}

	/**
	 * Actualiza los datos de un documento
	 * @param codigo
	 * @param titulo
	 * @param prioridad
	 * @param texto
	 */
	public void actualizarDocumento(String codigo, String titulo, int prioridad, String texto) {
		Documento documentoEncontrado = obtenerDocumento(codigo);
		documentoEncontrado.setTitulo(titulo);
		documentoEncontrado.setPrioridad(prioridad);
		documentoEncontrado.setTexto(texto);
		//Por si actualiza la prioridad de un documento
		Collections.sort(listaDocumentos);
	}

	/**
	 * Retorna si se pudo registrar la impresora o no
	 * @param codigo2
	 * @param nombre
	 * @param marca
	 * @param estadoImpresora
	 * @return
	 */
	public boolean agregarImpresora(String codigo, String nombre, String marca, EstadoImpresora estadoImpresora) {
		boolean fueCreada = verificarCodigoImpresora(codigo);
		if(fueCreada) {
			Impresora impresora = new Impresora(codigo, estadoImpresora, nombre, marca);
			getListaImpresoras().add(0, impresora);
		}

		return fueCreada;
	}

	/**
	 * Verifica si una impresora ya existe por su codigo
	 * @param codigo
	 * @return
	 */
	private boolean verificarCodigoImpresora(String codigo) {
		for(Impresora impresora : listaImpresoras) {
			if(codigo.equals(impresora.getCodigoImpresora())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Da la impresora a la cual le correspode el código
	 * @param codigoImpresora
	 * @return
	 */
	public Impresora obtenerImpresora(String codigoImpresora) {
		Impresora impresoraEncontrada = null;
		for(Impresora impresora : listaImpresoras) {
			if(impresora.getCodigoImpresora().equals(codigoImpresora)) {
				impresoraEncontrada = impresora;
			}
		}
		return impresoraEncontrada;
	}

	/**
	 * Actualiza los datos de una impresora
	 * @param codigoImpresora
	 * @param nombreImpresora
	 * @param marcaImpresora
	 * @param estadoImpresora
	 */
	public void actualizarImpresora(String codigoImpresora, String nombreImpresora, String marcaImpresora,
			EstadoImpresora estadoImpresora) {
		Impresora impresoraEncontrada = obtenerImpresora(codigoImpresora);
		impresoraEncontrada.setNombre(nombreImpresora);
		impresoraEncontrada.setMarca(marcaImpresora);
		impresoraEncontrada.setEstadoImpresora(estadoImpresora);
	}

	/**
	 * Elimina la impresora si esta existe
	 * @param codigoImpresora
	 * @return
	 */
	public boolean eliminarImpresora(String codigoImpresora) {
		Impresora impresoraEncontrada = obtenerImpresora(codigoImpresora);
		if(impresoraEncontrada == null) {
			return false;
		}

		listaImpresoras.remove(impresoraEncontrada);
		return true;
	}

}
