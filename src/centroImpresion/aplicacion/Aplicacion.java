package centroImpresion.aplicacion;

import centroImpresion.controller.GestionCentroImpresionController;
import centroImpresion.model.CentroImpresion;
import centroImpresion.model.EstadoImpresora;
import centroImpresion.model.Impresora;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Aplicacion extends Application{

	private Stage primaryStage;
	private CentroImpresion centroImpresion;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.centroImpresion = new CentroImpresion("12345");

		mostrarVentanaPrincipal();
	}

	//Tiene una serie de codigo que siempre es igual
	private void mostrarVentanaPrincipal() {
		try {

			FXMLLoader loader = new FXMLLoader();
			//De donde se va a cargar la intefaz
			loader.setLocation(Aplicacion.class.getResource("/centroImpresion/view/GestionCentroImpresionView.fxml"));
			//Definimos el anchorPane
			AnchorPane anchorPane = (AnchorPane)loader.load();
			//Obtenemos el controlador
			GestionCentroImpresionController gestionCentroImpresionController = loader.getController();
			//Para decir que el controlador pueda acceder a aplicacion
			gestionCentroImpresionController.setAplicacion(this);
			//Para crear la escena (Panel principal). Le decimos el layout que va a cargar
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public CentroImpresion getCentroImpresion() {
		return centroImpresion;
	}

	public void setCentroImpresion(CentroImpresion centroImpresion) {
		this.centroImpresion = centroImpresion;
	}

	/**
	 * Me dice si se pudo crear el documento
	 * @param codigo
	 * @param titulo
	 * @param prioridad
	 * @param texto
	 * @return
	 */
	public boolean crearDocumento(String codigo, String titulo, int prioridad, String texto) {
		boolean fueCreado = centroImpresion.agregarDocumento(codigo, titulo, prioridad, texto);
		return fueCreado;
	}

	/**
	 * Me dice si se pudo eliminar el documento
	 * @param codigoDocumento
	 * @return
	 */
	public boolean eliminarDocumento(String codigoDocumento) {
		boolean eliminarDocumento = centroImpresion.eliminarDocumento(codigoDocumento);
		return eliminarDocumento;
	}

	/**
	 * Me dice si se pudo actualizar el documento
	 * @param codigo
	 * @param titulo
	 * @param prioridad
	 * @param texto
	 */
	public void actualizarDocumento(String codigo, String titulo, int prioridad, String texto) {
		centroImpresion.actualizarDocumento(codigo, titulo, prioridad, texto);
	}

	/**
	 * Me dice si se pudo crear la impresora
	 * @param codigo
	 * @param nombre
	 * @param marca
	 * @param estadoImpresora
	 * @return
	 */
	public boolean crearImpresora(String codigo, String nombre, String marca, EstadoImpresora estadoImpresora) {
		boolean fueCreado = centroImpresion.agregarImpresora(codigo, nombre, marca, estadoImpresora);
		return fueCreado;
	}

	/**
	 * Actualiza los datos de la impresora excepto el código
	 * @param codigoImpresora
	 * @param nombreImpresora
	 * @param marcaImpresora
	 * @param estadoImpresora
	 */
	public void actualizarImpresora(String codigoImpresora, String nombreImpresora, String marcaImpresora,
			EstadoImpresora estadoImpresora) {
		centroImpresion.actualizarImpresora(codigoImpresora, nombreImpresora, marcaImpresora, estadoImpresora);
	}

	/**
	 * Me dice si se pudo eliminar la impresora
	 * @param codigoImpresora
	 * @return
	 */
	public boolean eliminarImpresora(String codigoImpresora) {
		boolean eliminarImpresora = centroImpresion.eliminarImpresora(codigoImpresora);
		return eliminarImpresora;
	}

	/**
	 * Me retorna el titulo del documento que se imprimió
	 * @param impresoraSeleccion
	 * @return
	 */
	public String imprimirDocumento(Impresora impresoraSeleccion) {
		String documentoImpreso = centroImpresion.imprimirDocumento(impresoraSeleccion);
		return documentoImpreso;
	}

}
