package product;

import java.util.List;

public interface Offer  {
	
	public String getName();
	
	public double getVisitCost();
	
	public double getTimeRequired();
	
	//Este método trae el tipo de la atracción o promoción.
	//public TipoDeAtraccion getTipo();
	
	public boolean checkVacancy();	
	
	public void subtractAvailability();
	
	public void printOffer();
	
	public boolean isAccepted(List<Attraction> acceptedAttractions);
	
	public void appendTo(List<Attraction> acceptedAttractions);

}