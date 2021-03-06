package Clases;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;


/**
 * Para terminar la aplicación.
 *
 * @author Dell Laptop
 *
  */
public class AccionSalir extends AbstractAction
{
    /**
     * serial uid
     */
    private static final long serialVersionUID = -3421118973626026775L;

    /** 
     * Componente padre del dialogo de confirmación que se usará. Al usar un componente
     * padre evitamos que el dialogo se pueda ir detrás del editor y quedar oculto por
     * este.
     */
    private Component padre;

    /**
     * Crea un nuevo objeto AccionSalir.
     *
     * @param padre Un padre para el diálogo.
     */
    public AccionSalir(Component padre)
    {
        this.padre = padre;
        putValue(Action.NAME, "Salir");
    }

    /**
     * Muestra una ventana de confirmación y si se acepta, se hace un System.exit().
     *
     * @param e El evento.
     */
    public void actionPerformed(ActionEvent e)
    {
        int opcion = JOptionPane.showConfirmDialog(
                padre, "¿Seguro que desea salir?");

        if (opcion == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }
}
