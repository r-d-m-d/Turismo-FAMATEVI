package reader;

import multiverseTour.TourSystem;
import product.Attraction;

public class AttractionsLoader implements Parser {
	private static final int CUPO_IDX = 3;
	private static final int TIEMPO_IDX = 2;
	private static final int MONEDAS_IDX = 1;
	private static final int NOMBRE_IDX = 0;
	public AttractionsLoader(String filename) {
	}

	private Attraction generarAtraccion(String nombre, String costo, String tiempo, String cupo) {
		return new Attraction(nombre, Double.parseDouble(costo), Double.parseDouble(tiempo), Short.parseShort(cupo));
	}

	@Override
	public void Load(String linea, TourSystem tour) {
		String[] splitedAtraccion = linea.trim().split(",");
	
		tour.addAttraction(generarAtraccion(splitedAtraccion[NOMBRE_IDX], splitedAtraccion[MONEDAS_IDX],
				splitedAtraccion[TIEMPO_IDX], splitedAtraccion[CUPO_IDX]));
	}
}
