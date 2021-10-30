package multiverseTour;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import input.files.InputFile;
import product.Attraction;
import product.CostAttractionComparator;
import product.CostPromotionComparator;
import product.Offer;
import product.Promotion;
import reader.PromotionsLoader;
import reader.AttractionsLoader;
import reader.Parser;
import reader.UsersLoader;

public class TourSystem {

	private List<Promotion> currentPacks = new ArrayList<Promotion>();
	private List<Attraction> attractions = new ArrayList<Attraction>();
	private List<User> users = new ArrayList<User>();
	private List<Offer> offers = new ArrayList<Offer>();//(new OfferComparator());

	public void addUser(User user) {
		this.users.add(user);
	}

	public void addAttraction(Attraction attraction) {
		this.attractions.add(attraction);
	}

	public void addPromotion(Promotion promotion) {
		this.currentPacks.add(promotion);
	}

	public boolean isAccepted(Promotion promotion, List<Attraction> acceptedAttractions) {
		return promotion.hasAny(acceptedAttractions);
	}

	public boolean isAccepted(Offer offer, List<Attraction> acceptedAttractions) {
		if (!(offer instanceof Attraction))
			return isAccepted((Promotion) offer, acceptedAttractions);
		else
			return acceptedAttractions.contains((Attraction) offer);
	}

	public void offerAccordingUser(User user) {

		List<Attraction> acceptedAttractions = new ArrayList<Attraction>();
		List<Offer> itinerary = new ArrayList<Offer>();
		
		
		this.offers.addAll(this.currentPacks);
		this.offers.addAll(this.attractions);
		
		
		System.out.println("");
		System.out.println("<<<<<<<<<<<<<<<< Bienvenid@ al Multiverso! >>>>>>>>>>>>>>>>>>\n");
		System.out.println("Estas son las promociones que tenemos para vos en este dia!\n");
		System.out.println("*************************************************************");
		
		for (Offer offer : offers) {
			if (!isAccepted(offer, acceptedAttractions)
					&& user.isOffertViable((int)offer.getVisitCost(),offer.getTimeRequired())
					&& offer.checkVacancy()) {

				offerAccepted(user, acceptedAttractions, itinerary, offer);
			}
		}
		user.receiveItinerary(itinerary);
		this.printItinerary(user);
	}

	private void offerAccepted(User user, List<Attraction> acceptedAttractions, List<Offer> itinerary, Offer offer) {
		if (user.takeOffer(offer)) {
			offer.subtractAvailability();
			user.acceptSuggestedOffer(offer);
			itinerary.add(offer);
			System.out.println("Su saldo disponible es de: " + user.getMoneyAvailability());
			System.out.println("Su tiempo disponible es de: " + user.getTimeRequired());
			System.out.println("*************************************************************\n");
			offer.appendTo(acceptedAttractions);
		}
	}

	public void OfferAccordingUsers() {
		Collections.sort(this.currentPacks, new CostPromotionComparator());
		Collections.sort(this.attractions, new CostAttractionComparator());
		
		for (User user : this.users) {
			System.out.println("Estimad@: " + user.getName().toUpperCase());
			offerAccordingUser(user);
			offers.clear();
		}
	}

	public boolean itsAttractionInAttractions(Attraction attraction, List<Attraction> acceptedAttractions) {
		acceptedAttractions.contains(attraction);
		for (Attraction aux : acceptedAttractions) {

			if (aux.equals(attraction)) {
				return true;
			}
		}
		return false;
	}

	public boolean itsAttractionInPromotion(Promotion promotion, List<Attraction> acceptedAttractions) {

		for (Attraction oneAttraction : promotion.getArrayAttractions()) { 

			if (acceptedAttractions.contains(oneAttraction)) {
				return true;
			}
		}
		return false;
	}

	public void loadAttractions() throws FileNotFoundException {
		Parser attractionsLoader = new AttractionsLoader(null);
		InputFile.loadFile("attractions.csv", attractionsLoader, this);
		
	}

	public void loadUsers() throws FileNotFoundException {
		Parser usersLoader = new UsersLoader();
		InputFile.loadFile("users.csv", usersLoader, this);
	}

	public void loadPromotions() throws FileNotFoundException {
		Parser promotionsLoader = new PromotionsLoader(this.attractions);
		InputFile.loadFile("promotions.csv", promotionsLoader, this);
	}
	public String itineraytoString(User user) {
		int totalCost = 0;
		double totalTime = 0.0;
		String itineraryString="Estimado Sr/a: "+ user.getName() + "\n"+"Ud. ha adquirido: \n";
				for(Offer offer:user.getItinerary()) {
					if (offer != null) {
						totalCost += offer.getVisitCost();
						totalTime += offer.getTimeRequired();
						itineraryString+=offer;
					}else {
						itineraryString+="null";
					}
				}
		itineraryString+="\nEl costo total de su itinerario es: " + totalCost + " pesos.\n";
		itineraryString+="El tiempo necesario para realizar su itinerario es: " + totalTime + " horas.\n";
		itineraryString+="Muchas gracias por haber elegido nuestros servicios.¡Esperamos que disfrute su recorrido!\n\n\n\n\n\n\n\n\n\n\n\n";
		return itineraryString;
	}
	public void printItinerary(User user) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		PrintWriter outFile;
		try {
			outFile = new PrintWriter(new FileWriter(dtf.format(now) + " " + user.getName() + " Ticket.out"));
			String systemOutput=this.itineraytoString(user);
			outFile.println(systemOutput);
			System.out.println(systemOutput);
			outFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}