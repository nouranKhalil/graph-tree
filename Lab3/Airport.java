import java.util.ArrayList;
import java.util.List;

public class Airport {
    String name;
    //List of edges(flights) connected to the node(airport)
    List<Flight> flights;

    public Airport(String name) {
        this.name = name;
        this.flights = new ArrayList<>();
    }
    //adding the edges to the node
    public void addFlight(Flight flight) {
        flights.add(flight);
    }
}