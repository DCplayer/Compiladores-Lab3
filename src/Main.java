import sun.plugin.javascript.navig.AnchorArray;
import sun.plugin.javascript.navig.Array;

import javax.net.ssl.SSLEngineResult;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        NormalizadorDeRegex norm = new NormalizadorDeRegex();
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese la expresion regular: ");
        String regex = sc.nextLine();
        Controlador control = new Controlador(regex);
        ConvertidorAfnAfd convert = new ConvertidorAfnAfd();

        Automata AutomataFinal = control.LectorDeExpresiones();
        ArrayList<Nodo> grafo = AutomataFinal.getHistorial();

        /*Obtener las transiciones del grafo*/
        ArrayList<Transicion> t = control.AlgoritmoCreaTransiciones(grafo);


        /*Obtener la Numeracion de ID de nodos*/
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for(Nodo i: grafo){
            ids.add(i.getId());
        }

        /*Obtener los simbolos del grafo*/
        ArrayList<String> s = control.AlgoritmoImplantaSimbolos(grafo);

        /*Obtener el Nodo Inicial*/
        int IdInicial = AutomataFinal.getNodoInicial().getId();

        /*Obtener el Nodo Final*/
        int IdFinal = AutomataFinal.getNodoFinal().getId();

        HashSet<Nodo> inicial = new HashSet<>();
        inicial.add(AutomataFinal.getNodoInicial());
        ArrayList<HashSet<Nodo>> AFD = convert.ConvertirAfnAfd(inicial, s);


        System.out.println("ESTADOS = " + ids);
        System.out.println("SIMBOLOS = " + s);
        System.out.println("INICIO = " + IdInicial);
        System.out.println("ACEPTACION = " + IdFinal);
        System.out.println("TRANSICION = " + t);

        for(HashSet<Nodo> h : AFD){
            System.out.println("------------");
            for (Nodo i: h){
                System.out.println(i.getId());
            }
        }

    }
}

