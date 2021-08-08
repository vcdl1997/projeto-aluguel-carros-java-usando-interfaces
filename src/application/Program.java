package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalServices;

public class Program {

	public static void main(String[] args) throws ParseException{
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Enter rental data");
		System.out.print("Car Model: ");
		String model = scan.nextLine();
		
		System.out.print("Pickup (dd/MM/yyyy HH:ss): ");
		Date start = sdf.parse(scan.nextLine());
		
		System.out.print("Return (dd/MM/yyyy HH:ss): ");
		Date finish = sdf.parse(scan.nextLine());

		CarRental carRental = new CarRental(start, finish, new Vehicle(model));
		
		System.out.print("Enter price per hour: ");
		double pricePerHour = scan.nextDouble();
		
		System.out.print("Enter price per day: ");
		double pricePerDay = scan.nextDouble();
		
		RentalServices rentalServices = new RentalServices(pricePerDay, pricePerHour, new BrazilTaxService());
		rentalServices.processInvoice(carRental);
		
		System.out.println("INVOICE");
		System.out.println("Basic Payment: " + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f", carRental.getInvoice().getTax()));
		System.out.println("Total Payment: " + String.format("%.2f", carRental.getInvoice().getTotalPayment()));

		scan.close();
	}

}
