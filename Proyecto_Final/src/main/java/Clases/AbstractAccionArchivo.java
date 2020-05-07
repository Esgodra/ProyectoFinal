package Clases;

import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;


/**
 * Clase padre de las acciones de guardar archivo y abrir archivo con el código común
 * para ambas acciones.<br>.
 *
 * @author Dell Laptop
 *
  */
public abstract class AbstractAccionArchivo extends AbstractAction
{
    /** 
     * Componente con el texto que se quiere guardar o en el que se quiere mostrar el
     * contenido del archivo.
     */
    protected JTextComponent componenteTexto;

    /** 
     * Panel que permite elegir un archivo del disco y navegar.  
     */
    private JFileChooser fileChooser = null;

    /** 
     * Si la acción va a ser para guardar o para abrir. 
     */
    private Opciones opcion;

    /** Enumerado con las posibles opciones para la acción */
    public enum Opciones
    {
    	GUARDAR, ABRIR;
    }

    /**
     * Crea un nuevo objeto AbstractAccionArchivo.
     *
     * @param componenteTexto Componente de texto sobre el que actuar, guardando su
     * contenido o mostrando en él el contenido de un archivo.
     * @param opcion GUARDAR o ABRIR.
     */
    public AbstractAccionArchivo(
        JTextComponent componenteTexto, Opciones opcion)
    {
        this.componenteTexto = componenteTexto;
        this.opcion = opcion;
    }

    /**
     * Se ha pulsado el botón y se muestra el File Chooser.
     *
     * @param arg0 Evento de pulsación del botón.
     */
    public void actionPerformed(ActionEvent arg0)
    {
    	// Se crea el FileChooser si no estaba creado.
        if (fileChooser == null)
        {
            fileChooser = new JFileChooser();
        }

        int opcionSeleccionada;

        // Se muestra el FileChooser como dialogo de guardar o de abrir según la opción
        // GUARDAR o ABRIR que se haya pasado en el constructor.
        if (opcion == Opciones.GUARDAR)
        {
            opcionSeleccionada = fileChooser.showSaveDialog(componenteTexto);
        }
        else
        {
            opcionSeleccionada = fileChooser.showOpenDialog(componenteTexto);
        }

        // Si el usuario elige un archivo y pulsa "OK"...
        if (JFileChooser.APPROVE_OPTION == opcionSeleccionada)
        {
        	// Se obtiene el archivo
            File archivo = fileChooser.getSelectedFile();

            try
            {
            	// Se guarda o abre. Las clases hijas deben redefinir este método.
                actuarSobreElArchivo(archivo);
            }
            catch (Exception e)
            {
            	// Mensaje de error si se produce.
                JOptionPane.showMessageDialog(
                    componenteTexto, e, "Error en el archivo " + archivo,
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Las clases hijas deben guardar el contenido del area de texto en el archivo que se
     * pasa como parámetro o leer el archivo y meter su contenido en el area de texto.
     *
     * @param archivo archivo que se debe leer o en el que se debe grabar.
     *
     * @throws FileNotFoundException Excepción si el archivo no existe.
     */
    protected abstract void actuarSobreElArchivo(File archivo)
        throws FileNotFoundException;
}

