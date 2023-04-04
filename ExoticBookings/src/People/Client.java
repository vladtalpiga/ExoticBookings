package People;

import Properties.Car;

import java.util.*;

public class Client extends Person {
    int balance; // bank balance
//    private List<Car> myGarage = new ArrayList<Car>();

    public Client(String name, int age, String homeCity, int balance) {
        super(name, age, homeCity);
        this.balance = balance;
    }

    public Client(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return name + ", from " + homeCity + ", age: " + age + ", balance: " + balance;
    }
}
