package Clases;


import java.awt.Event;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;


/**
 * Accion para el menú de "buscar".
 *
 * @author Dell Laptop
 *
  */
public class AccionBuscar extends AbstractAction
{
    /**
     * serial uid
     */
    private static final long serialVersionUID = -3542731242829577691L;

    /** 
     * Componente de texto sobre el que se actua. Se usa como padre para la ventana
     * de entrada del texto a buscar, de forma que dicha ventana no se pueda quedar detrás
     * y oculta por el editor.
     */
    private JTextComponent areaTexto;

    /**
     * Crea un nuevo objeto AccionBuscar.
     *
     * @param areaTexto Area de texto sobre la que actuar.
     */
    public AccionBuscar(JTextComponent areaTexto)
    {
        this.areaTexto = areaTexto;
        
        // Nombre visible y acelerador para estar acción de "Buscar".
        this.putValue(Action.NAME, "Buscar ...");
        this.putValue(
            Action.ACCELERATOR_KEY,
            KeyStroke.getAWTKeyStroke('B', Event.CTRL_MASK));
    }

    /**
     * Busca y resalta el texto en el JTextArea.
     *
     * @param arg0 El evento.
     */
    public void actionPerformed(ActionEvent arg0)
    {
    	// Se comrpueba si hay texto ya seleccionado.
        String textoABuscar = areaTexto.getSelectedText();

        if (textoABuscar == null)
        {
            textoABuscar = "";
        }

        // Se abre el diálogo para pedir la cadena a buscar, rellenándolo con
        // el texto actualmente seleccionado.
        textoABuscar = JOptionPane.showInputDialog(
                areaTexto, "Texto a buscar", textoABuscar);

        // Se obtiene el texto del JTextArea.
        String texto = areaTexto.getText();
        
        // Se comprueba la posición del texto seleccionado si lo hay.
        Caret seleccion = areaTexto.getCaret();
        int posicion = 0;
        if (seleccion.getDot() != seleccion.getMark())
        {
            posicion = seleccion.getDot();
        }

        // Se busca el texto a partir de la posición del texto seleccionado si lo
        // habia
        posicion = texto.indexOf(textoABuscar, posicion);

        // Si no se encuentra el texto, se termina.
        if (posicion == -1)
        {
            return;
        }

        // Se selecciona el texto encontrado. Valdría la llamada a areaTexto.select(),
        // pero dice la API que es mejor llamar a esto.
        areaTexto.setCaretPosition(posicion);
        areaTexto.moveCaretPosition(posicion + textoABuscar.length());
    }
}

