package product;

import java.util.Comparator;

public class CostPromotionComparator implements Comparator<Promotion>{

	@Override
	public int compare(Promotion o1, Promotion o2) {
		// TODO Auto-generated method stub
		return Double.compare(o2.getVisitCost(), o1.getVisitCost());
	}

}
