package Properties;

import java.util.Scanner;

public class Car {
    private static int countCarId = 100;
    private int carId;
    private int carPrice; // rent price per day
    private String brand;
    private int year; // manufacture year

    public Car(int carPrice, String brand, int year) {
        this.carId = ++countCarId;
        this.carPrice = carPrice;
        this.brand = brand;
        this.year = year;
    }

    public Car () {
        this.carId = ++countCarId;
    }

    public void read() {
        Scanner r = new Scanner(System.in);

        System.out.print("Car price: ");
        this.carPrice = Integer.parseInt(r.nextLine());

        System.out.print("Car brand: ");
        this.brand = r.nextLine();

        System.out.print("Manufacture year: ");
        this.year = Integer.parseInt(r.nextLine());

    }

    public int getCarId() {
        return carId;
    }

    public int getcarPrice() {
        return carPrice;
    }

    public void setcarPrice(int carPrice) {
        this.carPrice = carPrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }



    @Override
    public String toString() {
        return "Car " + carId + ". " + year + " " + brand + ", Price / Day = " + carPrice;
    }
}
