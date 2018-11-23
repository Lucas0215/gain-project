package gainProject;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class CreateDepot {
	Vector<String> storeInfoList = new Vector<>();
	Vector<String[]> storeList = new Vector<>();

	public CreateDepot(Vector<String> storeInfoList) {
		this.storeInfoList = storeInfoList;

		for (int i = 0; i < storeInfoList.size(); i++) {
			StringTokenizer storeInfo = new StringTokenizer(storeInfoList.get(i), " ");
			String[] store = new String[3];

			for (int j = 0; j < 3; j++)
				store[j] = storeInfo.nextToken();
			storeList.add(store);
		}
	}

	public void add(String[] shopInfo) throws IOException {
		int result=DepotUtil.search(shopInfo[1], storeList);
		if (result == -1) {
			storeList.add(shopInfo);
			storeInfoList.add(shopInfo[0] + " " + shopInfo[1] + " " + shopInfo[2]);
			new SaveRestaurantList(storeInfoList);
		}
	}

	public void delete(String name) throws IOException {
		int result=DepotUtil.search(name, storeList);
		if (result >= 0) {
			storeList.removeElementAt(result);
			storeInfoList.removeElementAt(result);
			new SaveRestaurantList(storeInfoList);
		}
	}
}