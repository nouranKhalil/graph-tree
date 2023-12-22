import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class AirlineNetwork {
	//defining the airlineNetwork graph as adjacency list.
	//the adjacency list maps each airport name to its connections(flights). 
    Map<String, Airport> airports;

    public AirlineNetwork() {
        this.airports = new HashMap<>();
    }
    // adding a node(airport) to the graph
    public void addAirport(String name) {
        airports.put(name, new Airport(name));
    }
    // adding the edges(flights) to the node.
    public void addFlight(String sourceName, String destinationName, int distance) {
        Airport source = airports.get(sourceName);
        Airport destination = airports.get(destinationName);
        Flight flight = new Flight(destination, distance);

        source.addFlight(flight);
    }

    public List<String> findShortestPath(String sourceName, String destinationName) {
        Airport source = airports.get(sourceName);
        Airport destination = airports.get(destinationName);

        // distances stores the shortest distances from the source to each airport
        Map<Airport, Integer> distances = new HashMap<>();
        //previousAirports stores the previous airport on the shortest path
        Map<Airport, Airport> previousAirports = new HashMap<>();
        //keeps track of visited airports
        Set<Airport> visitedAirports = new HashSet<>();
        //prioritizes the airports to visit based on their distances
        PriorityQueue<Airport> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Initialize distances of all airports except the source to infinity
        for (Airport airport : airports.values()) {
            distances.put(airport, Integer.MAX_VALUE);
            previousAirports.put(airport, null);
        }
        //initialize the distance of the source to 0
        distances.put(source, 0);

        // Dijkstra's algorithm
        queue.offer(source);
        //in the loop the algorithm selects the airport with the minimum distance from the queue and explores its flights.
        //for each flight it updates the distance to the neighboring airport if the new distance is shorter than the previous distance recorded.
        //then updates the previous airport on the shortest path.
        while (!queue.isEmpty()) {
            Airport currentAirport = queue.poll();
            visitedAirports.add(currentAirport);

            for (Flight flight : currentAirport.flights) {
                Airport neighbor = flight.destination;
                int newDistance = distances.get(currentAirport) + flight.distance;

                if (!visitedAirports.contains(neighbor) && newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previousAirports.put(neighbor, currentAirport);
                    queue.offer(neighbor);
                }
            }
        }

        // Check if there is a path from source to destination
        if (previousAirports.get(destination) == null) {
            return new ArrayList<>(); // Empty path
        }

        // Build the shortest path
        List<String> shortestPath = new ArrayList<>();
        Airport airport = destination;
        while (airport != null) {
            shortestPath.add(0, airport.name);
            airport = previousAirports.get(airport);
        }

        return shortestPath;
    }
}