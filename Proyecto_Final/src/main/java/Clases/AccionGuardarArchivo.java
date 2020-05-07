package Clases;

import java.awt.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;


/**
 * Salva el texto del JTextArea en un archivo.<br>
 * Hereda de AbstractAccionArchivo así que sólo se tiene que preocupar de configurarse
 * y de guardar el archivo.
 *
 * @author Dell Laptop
 *
  */
public class AccionGuardarArchivo extends AbstractAccionArchivo
{
    /**
     * serial uid
     */
    private static final long serialVersionUID = -4691302660823746530L;

    /**
     * Crea un nuevo objeto AccionGuardarArchivo.
     *
     * @param componenteTexto JTextArea que contiene el texto que se quiere guardar.
     */
    public AccionGuardarArchivo(JTextComponent componenteTexto)
    {
    	
        super(componenteTexto, Opciones.GUARDAR);
        this.putValue(Action.NAME, "Guardar como...");
        this.putValue(
            Action.ACCELERATOR_KEY,
            KeyStroke.getAWTKeyStroke('G', Event.CTRL_MASK));
    }

    /**
     * Salva el texto del JTextArea en el archivo que se le pasa.
     *
     * @param archivo Archivo en el que se quiere guardar el texto.
     *
     * @throws FileNotFoundException Excepción si hay problemas.
     */
    protected void actuarSobreElArchivo(File archivo)
        throws FileNotFoundException
    {
        PrintWriter writer = new PrintWriter(archivo);
        writer.print(componenteTexto.getText());
        writer.close();
    }
}