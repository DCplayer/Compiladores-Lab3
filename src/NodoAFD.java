import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Diego Castaneda on 05/08/2017.
 */
public class NodoAFD {
    private int id;
    private HashSet<Nodo> conjunto;
    private ArrayList<String> transiciones;
    private ArrayList<NodoAFD> arrivals;
}
