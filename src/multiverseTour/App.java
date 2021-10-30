package multiverseTour;

import java.io.FileNotFoundException;

public class App {

	public static void main(String[] args)   {

		TourSystem system = new TourSystem();

		try {
			system.loadUsers();
			system.loadAttractions();
			system.loadPromotions();
			system.OfferAccordingUsers();
		} catch (FileNotFoundException e) {
		}
		System.out.println("*********************Proceso finalizado***********************");
	}

}
