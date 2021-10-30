package reader;

import multiverseTour.TourSystem;
import multiverseTour.User;

public class UsersLoader implements Parser {
	private static final int DISPONIBILIDAD_IDX = 2;
	private static final int MONEDAS_IDX = 1;
	private static final int NOMBRE_IDX = 0;
	
	
	
	private User generarUsuario(String snombre,String smonedas,String sdiponibilidad) {
		return new User(snombre,Integer.parseInt(smonedas),Double.parseDouble(sdiponibilidad));
	}
	
	@Override
	public void Load(String linea, TourSystem tour) {
		String[] splittedUser=linea.split(",");
		User u=generarUsuario(splittedUser[NOMBRE_IDX],splittedUser[MONEDAS_IDX],splittedUser[DISPONIBILIDAD_IDX]);
		tour.addUser(u);
		
	}
}
