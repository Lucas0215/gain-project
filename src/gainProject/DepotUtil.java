package gainProject;

import java.util.Arrays;
import java.util.Vector;

public class DepotUtil {

	public static Vector<String[]> search(String content, Vector<String[]> shopList, int searchType) {
		Vector<String[]> result = new Vector<>();

		for (int i = 0; i < shopList.size(); i++) {
			String[] shop = shopList.get(i);
			if (shop[searchType].equals(content)) {
				result.add(shop);
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

	public static Vector<String[]> ban(Vector<String[]> shopList, Vector<String> banList) {
		for (int i = 0; i < shopList.size(); i++) {
			String[] shop = shopList.get(i);
			for (int j = 0; j < banList.size(); j++)
				if (banList.get(j).equals(shop[0]))
					shopList.remove(i);
		}
		return shopList;
	}

	public static Vector<String> sort(Vector<String> shopList) {
		String[] storeList = shopList.toArray(new String[shopList.size()]);
		String tempString;

		for (int i = 0; i < storeList.length; i++) {
			for (int j = 0; j < storeList.length - i - 1; j++) {
				if (storeList[j].compareTo(storeList[j + 1]) > 0) {
					System.out.print("1");
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