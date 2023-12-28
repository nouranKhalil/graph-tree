import java.util.Scanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class App {
    public static void main(String[] args) throws Exception {

        HashMap<String, ArrayList<String>> graph = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Classes: ");
        String line = sc.nextLine().replaceAll(" ", "");
        String[] nodes = line.split(",");

        for (String node : nodes) {
            ArrayList<String> arr = new ArrayList<>();
            graph.put(node, arr);
        }

        System.out.println("Conflicting classes (cannot occur simultaneously): ");
        line = sc.nextLine().replaceAll(" ", "");
        String[] edges = line.split(",");

        try {
            for (String edge : edges) {
                ArrayList<String> node1 = (ArrayList) graph.get(Character.toString(edge.charAt(0))).clone();
                ArrayList<String> node2 = (ArrayList) graph.get(Character.toString(edge.charAt(2))).clone();

                node1.add(String.valueOf(edge.charAt(2)));
                graph.put(String.valueOf(edge.charAt(0)), node1);

                node2.add(String.valueOf(edge.charAt(0)));
                graph.put(String.valueOf(edge.charAt(2)), node2);
            }
        } catch (Exception e) {
            System.err.println("Invalid!\nConflicting classes should be between the provided classes.\n");
            System.exit(0);
        }

        GraphColouring scheduleClasses = new GraphColouringImp(graph);
        scheduleClasses.setColouring();
        System.out.println("Optimized Class Schedule:");
        HashMap<String, colors> result = scheduleClasses.getColouring();
        for(String key : result.keySet()){
            System.out.println(key + " - " + result.get(key));
        }
        

    }
}
