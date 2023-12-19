package demo;

public final class Address {
    private String cityName;
    private String streetName;
    private int buildingNumber;
    private int apartmentNumber;
    private int zipCode;
    private char buildingLetter;
    public Address(String cityName, String streetName, int buildingNumber, int apartmentNumber, int zipCode, char buildingLetter) {
        this.cityName = cityName;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.zipCode = zipCode;
        this.buildingLetter = buildingLetter;
    }
    public String getCityName() {
        return cityName;
    }
    public String getStreetName() {
        return streetName;
    }
    public int getBuildingNumber() {
        return buildingNumber;
    }
    public int getApartmentNumber() {
        return apartmentNumber;
    }
    public int getZipCode() {
        return zipCode;
    }
    public char getBuildingLetter() {
        return buildingLetter;
    }

    @Override
    public String toString() {
        return getCityName() + ", " + getStreetName() + " st., " + getBuildingNumber() +
                getBuildingLetter() + ", " + getApartmentNumber() + ". ZIP: " + getZipCode() + ".";
    }
}