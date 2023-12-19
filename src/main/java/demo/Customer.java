package demo;

public final class Customer {
    private String firstName;
    private String secondName;
    private Address address;
    private int age;
    public Customer(String firstName, String secondName, Address address, int age) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (secondName == null || secondName.isEmpty()) {
            throw new IllegalArgumentException("Second name cannot be empty");
        }

        if (age <= 0) {
            throw new IllegalArgumentException("Age cannot be less than or equal to zero");
        }
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getSecondName() {
        return secondName;
    }
    public Address getAddress() {
        return address;
    }
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getSecondName() + ", " + getAge() + " years.";
    }
}