import java.util.ArrayList;
import java.util.List;

public class AssetManager {
    public static void main(String[] args) {
        List<Asset> assets = new ArrayList<>(); //heterogenous collection - because it can hold anything that derives from Asset includes Homes/Vehicles...

        // Add some assets
        assets.add(new House("My House", "2010-05-15", 300000, "123 Main St", 1, 2000, 7000));
        assets.add(new House("Vacation Home", "2015-09-20", 250000, "456 Ocean Ave", 2, 1500, 5000));
        assets.add(new Vehicle("Tom's Truck", "2018-03-12", 35000, "Ford F-150", 2018, 85000));
        assets.add(new Vehicle("Family Car", "2020-06-25", 28000, "Toyota Camry", 2020, 30000));

        // Display asset details and values
        for (Asset asset : assets) {
            System.out.println("Description: " + asset.getDescription());
            System.out.println("Date Acquired: " + asset.getDateAcquired());
            System.out.println("Original Cost: $" + asset.getOriginalCost());
            System.out.println("Current Value: $" + asset.getValue());

            // Display specific details based on asset type
            String message = "";
            if (asset instanceof House) {
                House house = (House) asset;
                message = "House at " + house.getAddress();
            } else if (asset instanceof Vehicle) {
                Vehicle vehicle = (Vehicle) asset;
                message = "Vehicle: " + vehicle.getYear() + " " + vehicle.getMakeModel();
            }
            System.out.println(message);
            System.out.println("----------------------------------------");
        }
    }
}
