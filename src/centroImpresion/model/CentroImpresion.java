package centroImpresion.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import centroImpresion.exception.ImpresoraException;

public class CentroImpresion {

	private String codigo;
	private List<Documento> listaDocumentos = new ArrayList<Documento>();
	private List<Impresora> listaImpresoras = new ArrayList<Impresora>();

	public CentroImpresion(String codigo, List<Documento> listaDocumentos, List<Impresora> listaImpresoras) {
		super();
		this.codigo = codigo;
		this.listaDocumentos = listaDocumentos;
		this.listaImpresoras = listaImpresoras;
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
	 *
	 * @param documento
	 * @return true si el documento se pudo imprimir
	 * @throws ImpresoraException si el documento no se pudo imprimir
	 */
	public boolean imprimirDocumento(Documento documento) throws ImpresoraException {
		Impresora impresora = obtenerImpresora();
		addDocumentoImprimir(documento);

		if(impresora != null) {
			impresora.imprimirDocumento(listaDocumentos.get(0));
			listaDocumentos.remove(0);
			return true;
		} else {
			throw new ImpresoraException("Impresora no conectada");
		}
	}

	public void conectarImpresora() {

	}

	/**
	 *
	 * @param documento
	 */
	private void addDocumentoImprimir(Documento documento) {
		listaDocumentos.add(documento);
		Collections.sort(listaDocumentos);
	}


	/**
	 *
	 * @return
	 */
	/*private Impresora obtenerImpresora() {
		if(getImpresoraConectada() != null && getImpresoraConectada().getEstadoImpresora().equals(EstadoImpresora.ACTIVO)) {
			return getImpresoraConectada();
		}
		return null;
	}*/

	/**
	 * Verifica que una impresora esta activa y retorna la primera que encuentra activa
	 * @param impresora
	 * @return
	 */
	private Impresora obtenerImpresora() {
		for(Impresora impresora : listaImpresoras) {
			if (impresora.isActiva()) {
				return impresora;
			}
		}
		return null;
	}



}
