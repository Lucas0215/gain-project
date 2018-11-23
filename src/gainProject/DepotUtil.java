package gainProject;

import java.util.Vector;

public class DepotUtil {

	public static int search(String name, Vector<String[]> shopList) {
		int result = -1;

		for (int i = 0; i < shopList.size(); i++) {
			String[] shop = shopList.get(i);
			if (shop[1].equals(name)) {
				result = i;
				break;
			}
		}
		return result;
	}

	public static String[] randomRecommend(Vector<String[]> shopList) {
		String[] randomShop;
		randomShop = shopList.get((int) (Math.random() * shopList.size()));
		return randomShop;
	}

	public void show(String[] shopInfo) {
		System.out.println(shopInfo[0] + " " + shopInfo[1] + " " + shopInfo[2]);
	}
}