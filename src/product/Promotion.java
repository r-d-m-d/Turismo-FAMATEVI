package product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Promotion implements Offer, Comparable<Promotion> {


	protected List<Attraction> includedAttractions = new ArrayList<Attraction>();
	private String name;
	//cambio 
	private double visitCost;

	public Promotion(String promotionName, List<Attraction> attractionList) {
		this.setName(promotionName);
		this.setArrayAttractions(attractionList);
		this.visitCost = this.calcularCosto();
	}
	@Override
	public int compareTo(Promotion otra) {
		return Double.compare(this.getVisitCost(), otra.getVisitCost()) == 0 ? Double.compare(this.getTimeRequired(), otra.getTimeRequired()) : Double.compare(this.getVisitCost(), otra.getVisitCost());
	}
	//funcion que se agrega para retornar el costo de visita
	public double calcularCosto() {
		double totalCost = 0;

		for (Attraction a : this.includedAttractions) {
			totalCost += a.getVisitCost();
		}
		return Math.round(totalCost);
	}
	
	public void setVisitCost(double visitCost) {
		this.visitCost = visitCost;
	}
	@Override
	public String toString() {
		String ts=this.name+"\n";
		for(Attraction a: includedAttractions)
			ts+="\t+"+a+"\n";
		ts += "\tPrecio Final: " + this.getVisitCost() + "\n";
		return ts;
	}
	public boolean hasAny(List<Attraction> attractionList) {
		for(Attraction a: this.includedAttractions) {
			if(attractionList.contains(a))
				return true;
		}
		return false;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void subtractAvailability() {

		for (int i = 0; i < includedAttractions.size(); i++) {
			includedAttractions.get(i).subtractAvailability();
		}
	}

	@Override
	public boolean checkVacancy() {

		for (Attraction a:this.includedAttractions) {
			if (!a.checkVacancy()) {
				return false;
			}
		}
		return true;
	}
	@Override
	public double getTimeRequired() {
		double totalTime = 0;

		for (int i = 0; i < includedAttractions.size(); i++) {
			totalTime += includedAttractions.get(i).getTimeRequired();
		}
		return totalTime;
	}

	@Override
	public void appendTo(List<Attraction> list) {
		for(Attraction attraction:this.includedAttractions) {
			list.add(attraction);
		}
	}

	/*@Override
	public boolean itsPromotion() {
		return true;
	}*/

	@Override
	public void printOffer() {

		System.out.println("La promocion ofrecida es: " + this.getName().toUpperCase() + ".");
		System.out.println("Las atracciones incluidas son: \n");

		for (int i = 0; i < this.getArrayAttractions().size(); i++) {
			System.out
					.println((i + 1) + ". " + this.getNameAttraction(this.getArrayAttractions().get(i)).toUpperCase());
		}
		System.out.println("Costo de Promoción: $" + this.getVisitCost() + " pesos.");
		System.out.println("Duracionn estimada: " + this.getTimeRequired() + " horas.");
		System.out.println("*******************************************************************\n");
	}

	public String getNameAttraction(Attraction attraction) {
		return attraction.getName();
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Attraction> getArrayAttractions() {
		return this.includedAttractions;
	}

	// Paso por parámetro las atracciones que estarán incluídas en la promoción.

	public void setArrayAttractions(List<Attraction> includedAttractions) {
		this.includedAttractions = includedAttractions;
	}
	
	@Override
	public double getVisitCost() {
		return this.visitCost;
	}
	public boolean hasAttraction(Attraction a) {
		return this.includedAttractions.contains(a);
		
	}
	@Override
	public int hashCode() {
		return Objects.hash(includedAttractions, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promotion other = (Promotion) obj;
		return Objects.equals(includedAttractions, other.includedAttractions) && Objects.equals(name, other.name);
	}
}