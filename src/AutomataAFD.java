import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Diego Castaneda on 05/08/2017.
 */
public class AutomataAFD {
    private ArrayList<HashSet<Nodo>> nodosIniciales  = new ArrayList<>();
    private ArrayList<String> transiciones = new ArrayList<>();
    private ArrayList<HashSet<Nodo>> nodosFinales = new ArrayList<>();

    public AutomataAFD(){}


    public void add(HashSet<Nodo> inicial, String s, HashSet<Nodo> finiti){
        nodosIniciales.add(inicial);
        nodosFinales.add(finiti);
        transiciones.add(s);

    }

}
