import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class javaproject {
	private static final int SPEED_LIMIT = 100;
	private static final int SPEED_FINE_PER_10_KM = 500;
	private static final int UNDERAGE_DRIVER_FINE = 2000;
	private static final int NO_HELMET_FINE = 1000;
	private static final int NO_SEAT_BELT_FINE = 1000;
	private static final int INVALID_VEHICLE_NUMBER_FINE = 1000;

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {

			System.out.println("=== Traffic Violation Analyser ===");
			String vehicleNumber = readVehicleNumber(scanner);
			int speed = readNonNegativeInt(scanner, "Enter vehicle speed (km/h): ");
			int driverAge = readPositiveInt(scanner, "Enter driver age: ");
			boolean helmetWorn = readYesOrNo(scanner, "Is the helmet worn? (yes/no): ");
			boolean seatBeltWorn = readYesOrNo(scanner, "Is the seat belt fastened? (yes/no): ");
			boolean emergencyVehicle = readYesOrNo(scanner, "Is this an emergency vehicle? (yes/no): ");

			List<String> violations = new ArrayList<>();
			int totalFine = 0;
			if (vehicleNumber.length() != 10) {
				violations.add("Vehicle number must be exactly 10 characters long");
				totalFine += INVALID_VEHICLE_NUMBER_FINE;
			}

			if (!emergencyVehicle && speed > SPEED_LIMIT) {
			int excessSpeed = speed - SPEED_LIMIT;
			int speedFine = ((excessSpeed + 9) / 10) * SPEED_FINE_PER_10_KM;
			violations.add("Speeding: " + speed + " km/h (limit is " + SPEED_LIMIT + " km/h)");
			totalFine += speedFine;
			}

			if (driverAge < 18) {
			violations.add("Driver is under the minimum driving age of 18");
			totalFine += UNDERAGE_DRIVER_FINE;
			}

			if (!helmetWorn) {
			violations.add("Helmet not worn");
			totalFine += NO_HELMET_FINE;
			}

			if (!seatBeltWorn) {
			violations.add("Seat belt not fastened");
			totalFine += NO_SEAT_BELT_FINE;
			}

			printResult(vehicleNumber, speed, driverAge, emergencyVehicle, violations, totalFine);
		}
	}

	private static String readVehicleNumber(Scanner scanner) {
		while (true) {
			System.out.print("Enter vehicle number: ");
			String vehicleNumber = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
			if (vehicleNumber.matches("[A-Z0-9-]{3,15}")) {
				return vehicleNumber;
			}
			System.out.println("Invalid vehicle number. Use 3-15 letters, numbers, or hyphens.");
		}
	}

	private static int readNonNegativeInt(Scanner scanner, String prompt) {
		while (true) {
			System.out.print(prompt);
			if (scanner.hasNextInt()) {
				int value = scanner.nextInt();
				scanner.nextLine();
				if (value >= 0) {
					return value;
				}
			} else {
				scanner.nextLine();
			}
			System.out.println("Please enter a non-negative whole number.");
		}
	}

	private static int readPositiveInt(Scanner scanner, String prompt) {
		while (true) {
			int value = readNonNegativeInt(scanner, prompt);
			if (value > 0) {
				return value;
			}
			System.out.println("Age must be greater than zero.");
		}
	}

	private static boolean readYesOrNo(Scanner scanner, String prompt) {
		while (true) {
			System.out.print(prompt);
			String answer = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
			if (answer.equals("yes") || answer.equals("y")) {
				return true;
			}
			if (answer.equals("no") || answer.equals("n")) {
				return false;
			}
			System.out.println("Please answer yes or no.");
		}
	}

	private static void printResult(String vehicleNumber, int speed, int driverAge,
									boolean emergencyVehicle, List<String> violations,
									int totalFine) {
		System.out.println("\n=== Final Validation ===");
		System.out.println("Vehicle number: " + vehicleNumber);
		System.out.println("Recorded speed: " + speed + " km/h");
		System.out.println("Driver age: " + driverAge);
		System.out.println("Emergency vehicle: " + (emergencyVehicle ? "Yes" : "No"));

		if (violations.isEmpty()) {
			System.out.println("Status: VALID - No traffic violations detected.");
			System.out.println("Applicable fine: 0");
			return;
		}

		System.out.println("Status: INVALID - Traffic violation(s) detected.");
		System.out.println("Violations:");
		for (String violation : violations) {
			System.out.println("- " + violation);
		}
		System.out.println("Applicable fine: " + totalFine);
	}
}
