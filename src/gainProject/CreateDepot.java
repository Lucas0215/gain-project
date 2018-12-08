package gainProject;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class CreateDepot {
	Vector<String> storeInfoList = new Vector<>();
	Vector<String> typeList = new Vector<>();
	Vector<String> locationList = new Vector<>();
	Vector<String[]> storeList = new Vector<>();

	public CreateDepot(Vector<String> storeInfoList, Vector<String> typeList, Vector<String> locationList) {
		this.storeInfoList = storeInfoList;
		this.typeList = typeList;
		this.locationList = locationList;

		for (int i = 0; i < storeInfoList.size(); i++) {
			StringTokenizer storeInfo = new StringTokenizer(storeInfoList.get(i), "/");
			String[] store = new String[3];

			for (int j = 0; j < 3; j++)
				store[j] = storeInfo.nextToken();
			storeList.add(store);
		}
	}

	public void add(String[] shopInfo) throws IOException {
		storeList.add(shopInfo);
		storeInfoList.add(shopInfo[0] + "/" + shopInfo[1] + "/" + shopInfo[2]);
		new SaveRestaurantList(storeInfoList);
	}

	public void delete(int num) throws IOException {
		storeList.remove(num);
		storeInfoList.remove(num);
		new SaveRestaurantList(storeInfoList);
	}

	public void modify(String[] shopInfo, int num) throws IOException {
		storeList.remove(num);
		storeList.add(num, shopInfo);
		storeInfoList.remove(num);
		storeInfoList.add(num, shopInfo[0] + "/" + shopInfo[1] + "/" + shopInfo[2]);
		new SaveRestaurantList(storeInfoList);
	}
}