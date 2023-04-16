package centroImpresion.controller;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import centroImpresion.aplicacion.Aplicacion;
import centroImpresion.model.CentroImpresion;
import centroImpresion.model.Documento;
import centroImpresion.model.EstadoDocumento;
import centroImpresion.model.EstadoImpresora;
import centroImpresion.model.Impresora;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class GestionCentroImpresionController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Documento> tableViewDocumentos;

    @FXML
    private Button btnEliminarDocumento;

    @FXML
    private TextField txtTituloDocumento;

    @FXML
    private Button btnActualizarDocumento;

    @FXML
    private TextField txtCodigoDocumento;

    @FXML
    private TextField txtPrioridadDocumento;

    @FXML
    private Button btnNuevoDocumento;

    @FXML
    private Button btnAgregarDocumento;

    @FXML
    private TableColumn<Documento, String> columnCodigoDocumento;

    @FXML
    private TableColumn<Documento, EstadoDocumento> columnEstadoDocumento;

    @FXML
    private TableColumn<Documento, Integer> columnPrioridadDocumento;

    @FXML
    private TableColumn<Documento, String> columnTituloDocumento;

    @FXML
    private Button btnNuevaImpresora;

    @FXML
    private Button btnActualizarImpresora;

    @FXML
    private TextField txtMarcaImpresora;

    @FXML
    private TextField txtCodigoImpresora;

    @FXML
    private Button btnEliminarImpresora;

    @FXML
    private TableView<Impresora> tableViewImpresoras;

    @FXML
    private TableColumn<Impresora, EstadoImpresora> columnEstadoImpresora;

    @FXML
    private TableColumn<Impresora, String> columnNombreImpresora;

    @FXML
    private TableColumn<Impresora, String> columMarcaImpresora;

    @FXML
    private TableColumn<Impresora, String> columnCodigoImpresora;

    @FXML
    private ComboBox<EstadoImpresora> comboBoxEstadoImpresora;

    @FXML
    private TextField txtNombreImpresora;

    @FXML
    private Button btnAgregarImpresora;

    @FXML
    private Button btnImprimirDoc;

    @FXML
    private Button btnSeleccionarArchivo;

    @FXML
    private Label labArchivo;


    //Creamos una variable aplicacion para comunicarme con la aplicacion y me pase datos de la empresa
	private Aplicacion aplicacion;

	//Creamos el unico centro de impresion que se va a usar
	private CentroImpresion centroImpresion;

	//Creo la lista de documentos que se va a ver
	ObservableList<Documento> listadoDocumentos = FXCollections.observableArrayList();

	//Creo el documento seleccion para cuando le den click en el doc de una tabla
	private Documento documentoSeleccion;

	//Creo la lista de impresoras que se va a ver
	ObservableList<Impresora> listadoImpresoras = FXCollections.observableArrayList();

	//Creo el Impresora seleccion para cuando le den click en la imp de una tabla
	private Impresora impresoraSeleccion;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//Datos en la table view de documentos
		this.columnCodigoDocumento.setCellValueFactory(new PropertyValueFactory<>("codigoDocumento"));
		this.columnTituloDocumento.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		this.columnPrioridadDocumento.setCellValueFactory(new PropertyValueFactory<>("prioridad"));
		this.columnEstadoDocumento.setCellValueFactory(new PropertyValueFactory<>("estadoDocumento"));

		//Esto es para poder seleccionar los documentos de una tabla
		tableViewDocumentos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null) {
				documentoSeleccion = newSelection; //Guarda el documento clickeado en documento seleccion
				mostrarInformacionDocumento();
			}
		});

		//Datos en la table view de impresoras
		this.columnCodigoImpresora.setCellValueFactory(new PropertyValueFactory<>("codigoImpresora"));
		this.columnNombreImpresora.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columMarcaImpresora.setCellValueFactory(new PropertyValueFactory<>("marca"));
		this.columnEstadoImpresora.setCellValueFactory(new PropertyValueFactory<>("estadoImpresora"));

		//Datos del comboBox de impresoras
		this.comboBoxEstadoImpresora.getItems().addAll(EstadoImpresora.values());

		//Esto es para poder seleccionas las impresoras de una tabla
		tableViewImpresoras.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			impresoraSeleccion = newSelection;
			mostrarInformacionImpresora();
		});
	}

	/**
	 * Para seleccionar unicamente los archivos txt
	 * @param event
	 */
    @FXML
    void seleccionarArchivo(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("Txt File", "*.txt"));
    	File f = fc.showOpenDialog(null);
    	if(f != null) {
    		labArchivo.setText(f.getAbsolutePath());
    	}
    }

	/**
	 * Muestra los datos del documento seleccionado en el table view
	 */
	private void mostrarInformacionDocumento() {
		if(documentoSeleccion != null) {
	    	txtCodigoDocumento.setText(documentoSeleccion.getCodigoDocumento());
	    	txtTituloDocumento.setText(documentoSeleccion.getTitulo());
	    	txtPrioridadDocumento.setText(String.valueOf(documentoSeleccion.getPrioridad()));
	    	labArchivo.setText(documentoSeleccion.getTexto());
	    	//Deshabilito este textField
	    	txtCodigoDocumento.setDisable(true);
		}
	}


	/**
	 * Resetea los datos de los txt de documento
	 * @param event
	 */
    @FXML
    void nuevoDocumento(ActionEvent event) {
    	txtCodigoDocumento.setText("Ingrese el código del documento");
    	txtTituloDocumento.setText("Ingrese el titulo del documento");
    	txtPrioridadDocumento.setText("");
    	labArchivo.setText("");
    	//Habilito este textField
    	txtCodigoDocumento.setDisable(false);
    }

    /**
     * Dado un documento seleccionado se actualizan los datos siempre y cuando estos esten validos
     * @param event
     */
    @FXML
    void actualizarDocumento(ActionEvent event) {
    	String codigo = txtCodigoDocumento.getText();
    	String titulo = txtTituloDocumento.getText();
    	int prioridad = Integer.parseInt(txtPrioridadDocumento.getText());
    	String texto = labArchivo.getText();

    	if(documentoSeleccion != null) {
    	   	if(datosValidos(codigo, titulo, prioridad, texto)) {
    	   		aplicacion.actualizarDocumento(codigo, titulo, prioridad, texto);
    	   		//Actualizo los datos de la interfaz
    	   		documentoSeleccion.setTitulo(titulo);
    	   		documentoSeleccion.setPrioridad(prioridad);
    	   		documentoSeleccion.setTexto(texto);
    	   		//Refresh de la tabla para actualizar los cambios
    			tableViewDocumentos.getItems().clear(); //Limpio la lista
    			tableViewDocumentos.setItems(getDocumentos()); //Agrego los datos a la lista
        		mostrarMensaje("Notificación Documento", "Documento actualizado", "El documento " +
        				titulo + " ha sido actualizado", AlertType.INFORMATION);
    	   	}
    	} else {
			mostrarMensaje("Documento Seleccion", "Documento Seleccion", "No se ha seleccionado ningún documento",
					AlertType.WARNING);
    	}
    }

    /**
     * Agrega un documento a la lista de documentos
     * @param event
     */
    @FXML
    void agregarDocumento(ActionEvent event) {
    	String codigo = txtCodigoDocumento.getText();
    	String titulo = txtTituloDocumento.getText();
    	int prioridad = Integer.parseInt(txtPrioridadDocumento.getText());
    	String texto = labArchivo.getText();

    	if(datosValidos(codigo, titulo, prioridad, texto)) {
    		crearDocumento(codigo, titulo, prioridad, texto);
    	}
    }

    /**
     * Me verifica si los datos de un documento son validos
     * @param codigo
     * @param titulo
     * @param prioridad
     * @param texto
     * @return
     */
	private boolean datosValidos(String codigo, String titulo, int prioridad, String texto) {
		String notificacion = "";
		if(codigo == null || codigo.equals("")) {
			notificacion += "El codigo es invalido\n";
		}
		if(titulo == null || titulo.equals("")) {
			notificacion += "El titulo es invalido\n";
		}
		if(prioridad < 0 || prioridad > 10) {
			notificacion += "La prioridad es invalida\n";
		}
		if(texto == null || texto.equals("")) {
			notificacion += "La direccion es invalida\n";
		}

		if(notificacion.equals("")) {
			return true;
		}

   		//notifica al ususario que la info es invalida
		mostrarMensaje("Notificación documento", "Información del documento invalida", notificacion,
				AlertType.WARNING);

		return false;
	}

	/**
	 * Crea un documento si en el código no está repetido
	 * @param codigo
	 * @param titulo
	 * @param prioridad
	 * @param texto
	 */
    private void crearDocumento(String codigo, String titulo, int prioridad, String texto) {
    	boolean fueCreado = aplicacion.crearDocumento(codigo, titulo, prioridad, texto);
    	if(fueCreado) {
    		//Añado el documento a el listadoDocumento(tableView) si este pudo ser agregado
    		//listadoDocumentos.add(documento); //Esta es otra manera de hacerlo
    		tableViewDocumentos.getItems().clear(); //Limpio la lista
    		tableViewDocumentos.setItems(getDocumentos()); //Agrego los datos a la lista
    		mostrarMensaje("Notificación documento", "Documento registrado", "El documento " +
    				titulo + " ha sido registrado", AlertType.INFORMATION);

    	} else {
       		mostrarMensaje("Notificación documento", "Documento no registrado", "El documento " +
    				titulo + " no ha sido registrado ya que su código está repetido", AlertType.WARNING);
    	}
	}

    /**
     * Muestra un mensaje dependiendo con el tipo de alerta seleccionado
     * @param title
     * @param header
     * @param content
     * @param alertType
     */
    public void mostrarMensaje(String title, String header, String content, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
    }

    /**
     * Elimina un documento, si no se pudo eliminar le informa al usuario
     * @param event
     */
	@FXML
    void eliminarDocumento(ActionEvent event) {
		if(documentoSeleccion != null) {
			if(aplicacion.eliminarDocumento(documentoSeleccion.getCodigoDocumento())) {
				mostrarMensaje("Documento Eliminado", "Documento Eliminado", "Se ha eliminado correctamente",
						AlertType.INFORMATION);
				//Elimina el documento de la lista de la tableView
				listadoDocumentos.remove(documentoSeleccion);
			} else {
				mostrarMensaje("Documento Seleccion", "Documento Seleccion", "No se ha podido eliminar",
						AlertType.INFORMATION);
			}
		} else {
			mostrarMensaje("Documento Seleccion", "Documento Seleccion", "No se ha seleccionado ningún documento",
					AlertType.WARNING);
		}
    }

	/**
	 * Muestra los datos de la impresora seleccionada en el table view
	 */
	private void mostrarInformacionImpresora() {
		if(impresoraSeleccion != null) {
	    	txtCodigoImpresora.setText(impresoraSeleccion.getCodigoImpresora());
	    	txtNombreImpresora.setText(impresoraSeleccion.getNombre());
	    	txtMarcaImpresora.setText(impresoraSeleccion.getMarca());
	    	comboBoxEstadoImpresora.setValue(impresoraSeleccion.getEstadoImpresora());
	    	//Deshabilito este textField
	    	txtCodigoImpresora.setDisable(true);
		}
	}

	/**
	 * Hace set a los textfields para crear una impresora
	 * @param event
	 */
    @FXML
    void nuevaImpresora(ActionEvent event) {
    	txtCodigoImpresora.setText("Ingrese el código de la impresora");
    	txtNombreImpresora.setText("Ingrese el nombre de la impresora");
    	txtMarcaImpresora.setText("Ingrese la marca de la impresora");
    	comboBoxEstadoImpresora.setValue(EstadoImpresora.ACTIVO);
    	//Habilito este textField
    	txtCodigoImpresora.setDisable(false);
    }

    /**
     * Actualiza los datos de la impresora si son validos
     * @param event
     */
    @FXML
    void actualizarImpresora(ActionEvent event) {
       	String codigoImpresora = txtCodigoImpresora.getText();
    	String nombreImpresora = txtNombreImpresora.getText();
    	String marcaImpresora = txtMarcaImpresora.getText();
    	EstadoImpresora estadoImpresora = comboBoxEstadoImpresora.getValue();

    	if(impresoraSeleccion != null) {
    		if(validarDatosImpresora(codigoImpresora, nombreImpresora, marcaImpresora, estadoImpresora)) {
    			aplicacion.actualizarImpresora(codigoImpresora, nombreImpresora, marcaImpresora, estadoImpresora);
    	   		//Actualizo los datos de la interfaz
    	   		impresoraSeleccion.setNombre(nombreImpresora);
    	   		impresoraSeleccion.setMarca(marcaImpresora);
    	   		impresoraSeleccion.setEstadoImpresora(estadoImpresora);
    	   		//Refresh de la tabla para actualizar los cambios
    			tableViewImpresoras.getItems().clear(); //Limpio la lista
    			tableViewImpresoras.setItems(getImpresoras()); //Agrego los datos a la lista
    			//Muestro el mensaje al usuario
        		mostrarMensaje("Notificación Impresora", "Impresora actualizada", "La impresora " +
        				nombreImpresora + " ha sido actualizada", AlertType.INFORMATION);
    		}
    	} else {
			mostrarMensaje("Impresora Seleccion", "Impresora Seleccion", "No se ha seleccionado ninguna impresora",
					AlertType.WARNING);
    	}
    }

    /**
     * Agrega la impresora siempre y cuando se validen los datos correctamente
     * @param event
     */
    @FXML
    void agregarImpresora(ActionEvent event) {
    	String codigoImpresora = txtCodigoImpresora.getText();
    	String nombreImpresora = txtNombreImpresora.getText();
    	String marcaImpresora = txtMarcaImpresora.getText();
    	EstadoImpresora estadoImpresora = comboBoxEstadoImpresora.getValue();

    	if(validarDatosImpresora(codigoImpresora, nombreImpresora, marcaImpresora, estadoImpresora)) {
    		crearImpresora(codigoImpresora, nombreImpresora, marcaImpresora, estadoImpresora);
    	}
    }

    /**
     * Valida que los datos de la impresora cumplan con la info correspondiente
     * @param codigoImpresora
     * @param nombreImpresora
     * @param marcaImpresora
     * @param estadoImpresora
     * @return
     */
    private boolean validarDatosImpresora(String codigoImpresora, String nombreImpresora, String marcaImpresora,
    		EstadoImpresora estadoImpresora) {

		String notificacion = "";
		if(codigoImpresora == null || codigoImpresora.equals("")) {
			notificacion += "El codigo es invalido\n";
		}
		if(nombreImpresora == null || nombreImpresora.equals("")) {
			notificacion += "El nombre es invalido\n";
		}
		if(marcaImpresora == null || marcaImpresora.equals("")) {
			notificacion += "La marca es invalida\n";
		}
		if(estadoImpresora == null) {
			notificacion += "El estado de la impresora es invalido\n";
		}

		if(notificacion.equals("")) {
			return true;
		}

   		//notifica al ususario que la info es invalida
		mostrarMensaje("Notificación Impresora", "Información de la impresora invalida", notificacion,
				AlertType.WARNING);

		return false;
    }

    /**
     * Crea una impresora siempre y cuando el codigo no este repetido
     * @param codigo
     * @param nombre
     * @param marca
     * @param estadoImpresora
     */
    private void crearImpresora(String codigo, String nombre, String marca, EstadoImpresora estadoImpresora) {
    	boolean fueCreado = aplicacion.crearImpresora(codigo, nombre, marca, estadoImpresora);
    	if(fueCreado) {
    		tableViewImpresoras.getItems().clear(); //Limpio la lista
    		tableViewImpresoras.setItems(getImpresoras()); //Agrego los datos a la lista
    		mostrarMensaje("Notificación Impresora", "Impresora registrada", "La impresora " +
    				nombre + "ha sido registrada", AlertType.INFORMATION);
    	} else {
       		mostrarMensaje("Notificación Impresora", "Impresora no registrado", "La impresora " +
    				nombre + " no ha sido registrada ya que su codigo está repetido", AlertType.WARNING);
    	}
	}

    /**
     * Elimina una impresora, si no se puede le informa al usuario
     * @param event
     */
    @FXML
    void eliminarImpresora(ActionEvent event) {
    	if(impresoraSeleccion != null) {
    		if(aplicacion.eliminarImpresora(impresoraSeleccion.getCodigoImpresora())) {
    			mostrarMensaje("Impresora eliminada", "Impresora eliminada", "Se ha eliminado correctamente",
    					AlertType.INFORMATION);
    			//Elimina la impresora de la table view
    			listadoImpresoras.remove(impresoraSeleccion);
    		} else {
    			mostrarMensaje("Impresora selección", "Impresora Selección", "No se pudo eliminar",
    					AlertType.INFORMATION);
    		}
    	} else {
    		mostrarMensaje("Impresora selección", "Impresora selección", "No se ha seleccionado ninguna impresora",
    				AlertType.WARNING);
    	}
    }

    /**
     * Imprime el documento y muestra la respectiva información
     * @param event
     */
    @FXML
    void imprimirDocumento(ActionEvent event) {
    	if(impresoraSeleccion != null) {
    		String documentoImpreso = aplicacion.imprimirDocumento(impresoraSeleccion);
    		if(!documentoImpreso.equals("")) {
    			mostrarMensaje("Documento Impreso", "Se ha impreso el documento: ", documentoImpreso,
    					AlertType.INFORMATION);
    	   		//Refresh de la tabla para actualizar los cambios
    			tableViewDocumentos.getItems().clear(); //Limpio la lista
    			tableViewDocumentos.setItems(getDocumentos()); //Agrego los datos a la lista
    		} else {
    			mostrarMensaje("Documento no impreso", "Ha ocurrido un error", "Verifique el estado de la impresora"
    					+ " o que si existan documentos para imprimir", AlertType.WARNING);
    		}
    	} else {
    		mostrarMensaje("Impresora selección", "Impresora selección", "No se ha seleccionado ninguna impresora",
    				AlertType.WARNING);
    	}
    }

    /**
     *
     * @param aplicacion
     */
	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		this.centroImpresion = aplicacion.getCentroImpresion();

		//Lista que se va a mostrar documentos
		tableViewDocumentos.getItems().clear(); //Limpio la lista
		tableViewDocumentos.setItems(getDocumentos()); //Agrego los datos a la lista

		//Lista que se va a mostrar impresoras
		tableViewImpresoras.getItems().clear();
		tableViewImpresoras.setItems(getImpresoras());
	}

	/**
	 * Me obtiene los documentos para la tableView
	 * @return
	 */
	private ObservableList<Documento> getDocumentos() {
		listadoDocumentos.addAll(centroImpresion.getListaDocumentos());
		return listadoDocumentos;
	}

	/**
	 * Me obtiene las impresoras para la tableView
	 * @return
	 */
	private ObservableList<Impresora> getImpresoras() {
		listadoImpresoras.addAll(centroImpresion.getListaImpresoras());
		return listadoImpresoras;
	}

}

