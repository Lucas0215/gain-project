package gainProject;

import java.util.Vector;

public class DepotUtil {

	public static Vector<Integer> search(String content, Vector<String[]> shopList, int searchType) {
		Vector<Integer> result = new Vector<>();

		for (int i = 0; i < shopList.size(); i++) {
			String[] shop = shopList.get(i);
			String searchingShop = shop[searchType];
			if (searchingShop.indexOf(content) != -1)
				result.add(i);
			else
				result.add(-1);
		}
		return result;
	}

	public static String[] randomRecommend(Vector<String[]> shopList) {
		String[] randomShop;
		randomShop = shopList.get((int) (Math.random() * shopList.size()));
		return randomShop;
	}

	public static void ban(Vector<String[]> shopList, Vector<String> banList, int searchType) {
		for (int i = shopList.size() - 1; i >= 0; i--) {
			String[] shop = shopList.get(i);
			for (int j = 0; j < banList.size(); j++)
				if (banList.get(j).equals(shop[searchType]))
					shopList.remove(i);
		}
	}

	public static Vector<String> sort(Vector<String> shopList) {
		String[] storeList = shopList.toArray(new String[shopList.size()]);
		String tempString;

		for (int i = 0; i < storeList.length; i++) {
			for (int j = 0; j < storeList.length - i - 1; j++) {
				if (storeList[j].compareTo(storeList[j + 1]) > 0) {
					tempString = storeList[j + 1];
					storeList[j + 1] = storeList[j];
					storeList[j] = tempString;
				}

			}
		}
		Vector<String> sortedList = new Vector<String>();
		for (int i = 0; i < storeList.length; i++)
			sortedList.add(storeList[i]);

		return sortedList;
	}
}