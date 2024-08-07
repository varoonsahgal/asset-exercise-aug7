import java.util.ArrayList;
import java.util.List;
import java.time.Year;

// Base class Asset
class Asset {
    private String description;
    private String dateAcquired;
    private  double originalCost;


    public Asset(String description, String dateAcquired, double originalCost) {
        this.description = description;
        this.dateAcquired = dateAcquired;
        this.originalCost = originalCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(String dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    public double getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(double originalCost) {
        this.originalCost = originalCost;
    }

    public double getValue() {
        return originalCost;
    }
}

// Derived class House
class House extends Asset {
    private String address;
    private int condition; // 1 - excellent, 2 - good, 3 - fair, 4 - poor
    private int squareFoot;
    private int lotSize;

    public House(String description, String dateAcquired, double originalCost, String address, int condition, int squareFoot, int lotSize) {
        super(description, dateAcquired, originalCost);
        this.address = address;
        this.condition = condition;
        this.squareFoot = squareFoot;
        this.lotSize = lotSize;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getSquareFoot() {
        return squareFoot;
    }

    public void setSquareFoot(int squareFoot) {
        this.squareFoot = squareFoot;
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    @Override
    public double getValue() {
        double valuePerSquareFoot = 0.0;
        switch (condition) {
            case 1:
                valuePerSquareFoot = 180.0;
                break;
            case 2:
                valuePerSquareFoot = 130.0;
                break;
            case 3:
                valuePerSquareFoot = 90.0;
                break;
            case 4:
                valuePerSquareFoot = 80.0;
                break;
        }
        double houseValue = squareFoot * valuePerSquareFoot;
        double lotValue = lotSize * 0.25;
        return houseValue + lotValue;
    }
}

// Derived class Vehicle
class Vehicle extends Asset {
    private String makeModel;
    private int year;
    private int odometer;

    public Vehicle(String description, String dateAcquired, double originalCost, String makeModel, int year, int odometer) {
        super(description, dateAcquired, originalCost);
        this.makeModel = makeModel;
        this.year = year;
        this.odometer = odometer;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    @Override
    public double getValue() {
        int currentYear = Year.now().getValue();
        int age = currentYear - year;
        double depreciatedValue = getOriginalCost();

        if (age <= 3) {
            depreciatedValue *= Math.pow(0.97, age);
        } else if (age <= 6) {
            depreciatedValue *= Math.pow(0.94, age - 3) * Math.pow(0.97, 3);
        } else if (age <= 10) {
            depreciatedValue *= Math.pow(0.92, age - 6) * Math.pow(0.94, 3) * Math.pow(0.97, 3);
        } else {
            depreciatedValue = 1000.0;
        }

        // Apply odometer reduction if applicable
        if (odometer > 100000 && !(makeModel.contains("Honda") || makeModel.contains("Toyota"))) {
            depreciatedValue *= 0.75;
        }

        return depreciatedValue;
    }
}

// Main application