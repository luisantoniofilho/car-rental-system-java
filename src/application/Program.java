package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		
		System.out.println("Enter with car rent data");
		
		System.out.print("Car model: ");
		String carModel = sc.nextLine();
		
		System.out.print("Removal (DD/MM/YYYY HH:MM): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(),fmt);
		
		System.out.print("Return (DD/MM/YYYY HH:MM): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(),fmt);
		
		CarRental carRental = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Enter the price per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Enter the price per day: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		rentalService.processInvoice(carRental);
		
		System.out.println("Fatura: ");
		System.out.println("Pagamento basico: " + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f",carRental.getInvoice().getTax()));
		System.out.println("Total payment: " + String.format("%.2f",carRental.getInvoice().getTotalPayment()));
		
		sc.close();
	}
}