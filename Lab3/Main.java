import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the list of airports: ");
        String[] airportNames = scanner.nextLine().split(", ");
        if(airportNames.length <= 1)
        {
        	System.out.println("There must be at least two airports!");
        	System.exit(0);
        }
        Set<String> uniqueAirports = new HashSet<>();
        for (String airportName : airportNames) {
            if (!uniqueAirports.add(airportName)) {
                System.out.println("Duplicate airport found: " + airportName);
                System.exit(0);
            }
        }
        AirlineNetwork airlineNetwork = new AirlineNetwork();
        for (String airportName : airportNames) {
            airlineNetwork.addAirport(airportName);
        }

        System.out.print("Enter the flights: ");
        String[] flights = scanner.nextLine().split(", ");

        for (String flight : flights) {
            String[] airports = flight.split("-");
            if (airports.length != 2) {
                System.out.println("Invalid flight format: " + flight);
                return;
            }
            String source = airports[0];
            String destination = airports[1];

            System.out.print("The distance for flight " + flight + ": ");
            int distance = readInteger(scanner);
            
            if (distance < 0 ) {
                System.out.println("Invalid distance format.");
                return;
            }

            airlineNetwork.addFlight(source, destination, distance);
        }

        System.out.print("Enter source airport: ");
        String sourceAirport = scanner.nextLine();
        if (!airlineNetwork.airports.containsKey(sourceAirport)) {
            System.out.println("Source airport not found in the network.");
            return;
        }

        System.out.print("Enter destination airport: ");
        String destinationAirport = scanner.nextLine();
        if (!airlineNetwork.airports.containsKey(destinationAirport)) {
            System.out.println("Destination airport not found in the network.");
            return;
        }

        List<String>shortestPath = airlineNetwork.findShortestPath(sourceAirport, destinationAirport);

        if (shortestPath.isEmpty()) {
            System.out.println("No path found from " + sourceAirport + " to " + destinationAirport);
        } else {
            System.out.print("Shortest path from " + sourceAirport + " to " + destinationAirport + ": ");
            for (String airport : shortestPath) {
                System.out.print(airport);
            	if(shortestPath.size()-1 != shortestPath.indexOf(airport))
            		System.out.print(" - ");
            }

            System.out.println();
            System.out.println("Total distance: " + calculateTotalDistance(shortestPath, airlineNetwork) + " miles");
        }
    }

    private static int readInteger(Scanner scanner) {
        try {

            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static int calculateTotalDistance(List<String> shortestPath, AirlineNetwork airlineNetwork) {
        int totalDistance = 0;
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            String source = shortestPath.get(i);
            String destination = shortestPath.get(i + 1);
            for (Flight flight : airlineNetwork.airports.get(source).flights) {
                if (flight.destination.name.equals(destination)) {
                    totalDistance += flight.distance;
                    break;
                }
            }
        }
        return totalDistance;
    }
}
