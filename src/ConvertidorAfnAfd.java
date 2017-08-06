import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Diego Castaneda on 05/08/2017.
 */
public class ConvertidorAfnAfd {
    private int index = 0;
    private AutomataAFD AFD = new AutomataAFD();

    public ConvertidorAfnAfd(){}

    public boolean hastaAqui(Stack<HashSet<Nodo>> stack){
        boolean booleano = true;
        if(!stack.isEmpty()){
            HashSet<Nodo> Q = stack.pop();
            HashSet<Nodo> P = stack.pop();

            if(!Q.equals(P)){
                booleano = false;
            }

            HashSet<Nodo> R = new HashSet<Nodo>();
            R.addAll(Q);
            for(Nodo i: Q){
                int index = 0;
                while(index < i.getTransiciones().size()){
                    if (i.getTransiciones().get(index).equals("@")){
                        R.add(i.getNodos().get(index));
                    }
                    index = index + 1;
                }
            }
            stack.push(Q);
            stack.push(R);
        }return booleano;
    }

    public HashSet<Nodo> eClosure(HashSet<Nodo> C){
        HashSet<Nodo> x = new HashSet<Nodo>();
        x.addAll(C);

        Stack<Nodo> control = new Stack<Nodo>();
        for (Nodo i: C){
            control.push(i);
        }

        Stack<HashSet<Nodo>> resultados = new Stack<HashSet<Nodo>>();
        while(!control.isEmpty()){
            Nodo z = control.pop();
            HashSet<Nodo> inicial = new HashSet<Nodo>();
            HashSet<Nodo> finito = new HashSet<Nodo>();

            inicial.add(z);
            resultados.push(inicial);

            int index = 0;
            while (index < z.getTransiciones().size()){
                if (z.getTransiciones().get(index).equals("@")){
                    finito.add(z.getNodos().get(index));

                }
                index = index + 1;
            }
            resultados.push(finito);

            boolean respuesta = hastaAqui(resultados);
            while(respuesta){
                respuesta = hastaAqui(resultados);
            }

            x.addAll(resultados.pop());
        }return x;
    }

    public HashSet<Nodo> move(HashSet<Nodo> c , String s){
        HashSet<Nodo> resultante = new HashSet<>();

        HashSet<Nodo> epsiloneado = eClosure(c);

        for (Nodo i: epsiloneado){
            int index = 0;
            while (index < i.getTransiciones().size()){
                if(i.getTransiciones().get(index).equals(s) || i.getTransiciones().get(index).equals("@") ){
                    resultante.add(i.getNodos().get(index));

                }
                index = index +1;
            }
        }
        HashSet<Nodo> rescate = eClosure(resultante);
        return rescate;


    }

    public ArrayList<HashSet<Nodo>> ConvertirAfnAfd(HashSet<Nodo> inicial, ArrayList<String> simbolos){
        ArrayList<HashSet<Nodo>> marcado = new ArrayList<>();
        ArrayList<HashSet<Nodo>> noMarcado = new ArrayList<>();
        int tamano = 0;

        noMarcado.add(eClosure(inicial));
        while(true){
            tamano = noMarcado.size();
            if(index >= tamano){
                break;
            }

            HashSet<Nodo> x = noMarcado.get(index);
            marcado.add(x);
            index = index + 1;

            for (String s: simbolos){
                HashSet<Nodo> z = eClosure(x);
                HashSet<Nodo> y = move(z, s);

                AFD.add(x,s,y);
                if(!noMarcado.contains(y)){
                    noMarcado.add(y);
                }
            }
        }
        return marcado;
    }
}
