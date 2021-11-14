package product;

import java.util.List;
import java.util.Objects;


public class Attraction implements Offer, Comparable<Attraction> {

	private String name;
	private double visitCost;
	private double timeRequired;
	private int quota;

	public Attraction(String name, double visitCost, double time, int quota) {

		this.name = name;
		this.visitCost = visitCost;
		this.timeRequired = time;
		this.quota = quota;
	}

	@Override
	public String toString() {
		return "" + name + "\t$" + visitCost + "\t" + timeRequired + " horas\n";
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getVisitCost() {
		return visitCost;
	}

	@Override
	public boolean checkVacancy() {
		return this.quota > 0;
	}

	@Override
	public double getTimeRequired() {
		return this.timeRequired;
	}

	@Override
	public void subtractAvailability() {
		this.quota--;
	}

	/*
	 * @Override public boolean itsPromotion() { return false; }
	 */

	@Override
	public void appendTo(List<Attraction> list) {
		list.add(this);
	}

	@Override
	public void printOffer() {

		System.out.println("Oferta de Atraccion: " + this.getName().toUpperCase() + ".");
		System.out.println("Costo de visita: $" + this.getVisitCost() + " pesos.");
		System.out.println("Duración estimada: " + this.getTimeRequired() + " horas.");
		System.out.println("********************************************************\n");
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, quota, timeRequired, visitCost);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Attraction other = (Attraction) obj;
		return Objects.equals(name, other.name) && quota == other.quota
				&& Double.doubleToLongBits(timeRequired) == Double.doubleToLongBits(other.timeRequired)
				&& Double.doubleToLongBits(visitCost) == Double.doubleToLongBits(other.visitCost);
	}

	@Override
	public int compareTo(Attraction otra) {
		return Double.compare(this.getVisitCost(), otra.getVisitCost()) == 0 ? Double.compare(this.timeRequired, otra.getTimeRequired()) : Double.compare(this.getVisitCost(), otra.getVisitCost());
	}

	@Override
	public boolean isAccepted(List<Attraction> acceptedAttractions) {
		return acceptedAttractions.contains(this);
	}

}