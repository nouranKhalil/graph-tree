import java.util.HashMap;
import java.util.ArrayList;

enum colors {
    blue,
    red,
    green,
    lightGray,
    magenta,
    pink,
    black,
    yellow,
    cyan,
    orange,
    gray,
    white,
    darkGray
}

public class GraphColouringImp implements GraphColouring {

    private HashMap<String, ArrayList<String>> graph = new HashMap<>();
    private HashMap<String, colors> colouring = new HashMap<>();

    public GraphColouringImp(HashMap<String, ArrayList<String>> graph) {
        this.graph = graph;
    }

    public HashMap<String, colors> getColouring() {
        return colouring;
    }

    public void setColouring() {
        for (String node : graph.keySet()) {
            this.colouring.put(node, validColor(node));
        }
    }

    private colors validColor(String nodeKey) {
        ArrayList<String> edges = (ArrayList) graph.get(nodeKey).clone();
        colors color = colors.blue;
        int counter = 0;
        if(edges.size() == 0)
                return color;
        for (colors edgeColor : colors.values()) {
            color = edgeColor;
            for (String edge : edges) {
                counter ++;
                if (edgeColor == colouring.get(edge)) {
                    counter = 0;
                    break;
                }
                else if (counter == edges.size()){
                    return color;
                }
            }
        }
        return null;
    }

}
