package reader;

import java.util.List;

import multiverseTour.TourSystem;

import java.util.ArrayList;
import java.util.Arrays;
import product.AbsolutPromotion;
import product.Attraction;
import product.AxBPromotion;
import product.PercentagePromotion;
import product.Promotion;

public class PromotionsLoader implements Parser {
	List<Attraction> atracciones;

	public PromotionsLoader(List<Attraction> attractions) {
		this.atracciones = attractions;
	}

	List<Attraction> makeAttractionList(String attractionNames[]) {
		List<Attraction> promotionAttractions = new ArrayList<Attraction>();

		for (String nombre : attractionNames)
			for (Attraction atraccion : this.atracciones)
				if (atraccion.getName().equalsIgnoreCase(nombre))
					promotionAttractions.add(atraccion);

		return promotionAttractions;
	}

	int getFreeIdx(String[] splittedLine) {
		int gratisIdx = 0;
		while (gratisIdx < splittedLine.length && !splittedLine[gratisIdx].equalsIgnoreCase("Gratis"))
			gratisIdx++;
		return gratisIdx;
	}

	Promotion resolveLineCsv(String csvLine) {
		String[] splittedLine;
		Promotion promo = null;
		splittedLine = csvLine.split(",");

		if (splittedLine[0].toUpperCase().equals("PORCENTUAL")) {
			promo = new PercentagePromotion(splittedLine[1],
					makeAttractionList(Arrays.copyOf(splittedLine, splittedLine.length+1)),
					Double.parseDouble(splittedLine[2]));
		} else if (splittedLine[0].toUpperCase().equals("ABSOLUTA")) {
			promo = new AbsolutPromotion(splittedLine[1],
					makeAttractionList(Arrays.copyOf(splittedLine, splittedLine.length+1)),
					Double.parseDouble(splittedLine[2]));
		} else if (splittedLine[0].toUpperCase().equals("COMBO")) {
			int gratisIdx = getFreeIdx(splittedLine);

			promo = new AxBPromotion(splittedLine[1],
					makeAttractionList(Arrays.copyOfRange(splittedLine, 0, gratisIdx)),
					makeAttractionList(Arrays.copyOfRange(splittedLine, gratisIdx, splittedLine.length+1)));
		}
		
		return promo;
	}

	@Override
	public void Load(String linea, TourSystem tour) {
		Promotion pack = resolveLineCsv(linea);
		if (pack != null)
			tour.addPromotion(pack);
	}

}
