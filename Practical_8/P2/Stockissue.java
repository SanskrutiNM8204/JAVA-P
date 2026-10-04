package Practical_8.P2;

import java.util.List;
import java.util.Map;

public class Stockissue {
	private record Request(String item, int quantity) {
	}

	public static void main(String[] args) {
		Warehouse warehouse = new Warehouse(Map.of("rice", 10, "beans", 5));
		List<Request> requests = List.of(
				new Request("rice", 4),
				new Request("beans", 8),
				new Request("rice", 0),
				new Request("rice", 2));

		for (Request request : requests) {
			try {
				warehouse.issue(request.item(), request.quantity());
				System.out.println("Issued " + request.quantity() + " of " + request.item() + ".");
			} catch (OutOfStockException e) {
				System.out.println("Could not issue " + request.item() + ": " + e.getMessage());
			} catch (InvalidQuantityException e) {
				System.out.println("Could not issue " + request.item() + ": " + e.getMessage());
			}
		}

		System.out.println("Remaining stock: " + warehouse.getInventory());
	}
}
