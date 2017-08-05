import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Diego Castaneda on 05/08/2017.
 */
public class ConvertidorAfnAfd {

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
                    index = index + 1;
                }
            }
            resultados.push(finito);

            boolean respuesta = hastaAqui(resultados);
            while(respuesta){
                respuesta = hastaAqui(resultados);
            }

            x.addAll(resultados.pop());
        }return x;
    }
}
