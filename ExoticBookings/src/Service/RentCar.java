package Service;

import Booking.CarBooking;
import People.Client;
import Properties.Car;

import java.util.*;

public class RentCar {

//    private static RentCar instance = null;
    private List<Car> cars = new ArrayList<>();
    private List<CarBooking> carBookings = new ArrayList<>();
    protected static List<Client> clients = new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<CarBooking> getCarBookings() {
        return carBookings;
    }

    public void setCarBookings(List<CarBooking> carBookings) {
        this.carBookings = carBookings;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public RentCar(){}
//    public static synchronized RentCar getInstance() {
//        if (instance == null)
//            instance = new RentCar();
//        return instance;
//    }

    // 1. Show all available cars from the dealership
    public boolean showCars() {
        if(cars.isEmpty()) {
            System.out.println("There are no available cars");
            return false;
        }
            System.out.println("\nThese are the available cars:");
            for (var car : cars) {
                System.out.println(car.toString());
            }
        return true;
    }


    // 2. Add a new car to the dealership
    public void addCar() {
        System.out.println("Add a new car: ");
        Car car = new Car();
        car.read();
        cars.add(car);
        System.out.println("Car added successfully");
    }

    // 3. Remove a car from the dealership
    public boolean carExists (int id) {
        for (var car : cars)
            if (car.getCarId() == id)
                return true;
        System.out.println("There is no car with this id. Choose another id: ");
        return false;
    }
    public void removeCar() {
        boolean flag = showCars();

        if (flag) {
            System.out.println("\nChoose the id of the car you want to remove: ");

            Scanner r = new Scanner(System.in);
            int id = Integer.parseInt(r.nextLine());

            while (!carExists(id)) {
                r = new Scanner(System.in);
                id = Integer.parseInt(r.nextLine());
            }
            int finalId = id;
            cars.removeIf(car -> car.getCarId() == finalId);
        }

//        for (var car : cars) {
//            if (car.getCarId() == id)
//                cars.remove(car);
//        }

        System.out.println("Car removed successfully");
    }


    // 4. Open garage (Show all the cars rented by a client)
    public boolean showClients () {
        if (clients.isEmpty()) {
            System.out.println("There aren't any clients");
            return false;
        }
        System.out.println("These are the clients:");
        for (var client : clients) {
            System.out.println(client.toString());
        }
        return true;
    }

    public Client readClientName () {
        System.out.print("\nEnter the name of the client: ");
        String name;
        while (true) {
            Scanner r = new Scanner(System.in);
            name = r.nextLine();
            for (var client : clients)
                if (client.getName().equals(name)) {
                    return client;
                }
            System.out.println("There isn't any client with this name. Please enter another name: ");
        }
    }
    public void garage() {
        boolean flag = showClients();
        if (!flag)
            return;
        Client client = readClientName();
        boolean k = false;
        System.out.println(client.getName() + " rented the following cars: ");
        for (var carBooking : carBookings) {
            if (carBooking.getClient().equals(client)) {
                System.out.println(carBooking.getDuration() + " day rental - " + carBooking.getCar().toString());
                k = true;
            }
        }
        if (!k)
            System.out.println(client.getName() + " didn't rent any cars");
    }

    // 5. Rent a car
    public void rentCar () {
        boolean ff = showClients();
        if (!ff)
            return;
        Client client = readClientName();
        CarBooking carBooking = new CarBooking();
        carBooking.setClient(client);

        boolean flag = showCars();
        if (flag) {
            System.out.print("\nChoose the id of the car you want to rent: ");
            Scanner r = new Scanner(System.in);
            int id = Integer.parseInt(r.nextLine());

            while (!carExists(id)) {
                r = new Scanner(System.in);
                id = Integer.parseInt(r.nextLine());
            }
            carBooking.read();
            int totalPrice = 0;
            for (var car : cars) {
                if (car.getCarId() == id) {
                    totalPrice = car.getcarPrice() * carBooking.getDuration();
                    break;
                }
            }

            int balance = client.getBalance();
            if (totalPrice <= balance) {
                for (var car : cars) {
                    if (car.getCarId() == id) {
                        carBooking.setCar(car);
                        cars.remove(car);
                        break;
                    }
                }
                System.out.println("Car rented successfully!");
                System.out.println("Total rental price: " + totalPrice + ". Old balance: " + balance + ". New balance: " + (balance - totalPrice));
                client.setBalance(balance - totalPrice);
                carBookings.add(carBooking);
            }
            else System.out.println("Not enough money!");
        }
    }

    // MENU

    public void menuRentCar() {
        int option = -1;
        while (option != 0) {
            System.out.println("\nChoose one of the following actions:");
            System.out.println("1. Show all available cars from the dealership");
            System.out.println("2. Add a new car to the dealership");
            System.out.println("3. Remove a car from the dealership");
            System.out.println("4. Open garage (Show all the cars rented by a client)");
            System.out.println("5. Rent a car");
            System.out.println("6. Show all clients");
            //System.out.println("6. Cancel a car booking");
            System.out.println("------------------------------");
            System.out.println("0. Back to menu");

            Scanner input = new Scanner(System.in);
            option = Integer.parseInt(input.nextLine());

            switch (option) {
                case 1: {
                    showCars();
                    break;
                }
                case 2: {
                    addCar();
                    break;
                }
                case 3: {
                    removeCar();
                    break;
                }
                case 4: {
                    garage();
                    break;
                }
                case 5: {
                    rentCar();
                    break;
                }
                case 6: {
                    showClients();
                    break;
                }
                default: {
                    System.out.print("\nPlease enter a valid option: ");
                }
            }
        }
    }


}


