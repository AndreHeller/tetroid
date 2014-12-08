package _1245_tetroid;

import java.awt.Image;
import javax.swing.Icon;

/**
 * Instance rozhraní {@code ITetMaster} slouží ke komunikaci se hrou a reakcím
 * tetrisu na situace ve hře.
 *
 * @author Tetroids
 */
public interface ITetMaster
{
    /**************************************************************************
     * Vrátí obrázek následujícího tvaru
     *
     * @return název obrázku následujícího tvaru
     */
    public abstract String getNextShape();

    /***************************************************************************
     * Zjistí aktuální periodu arkanoidu
     */
    public abstract int getTetPeriod();

    /***************************************************************************
     * Inicializační metoda
     */
    public abstract void initialize();

}
