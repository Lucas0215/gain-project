package gainProject;

import java.util.StringTokenizer;
import java.util.Vector;

public class CreateDepot {
	Vector<String> typeList = new Vector<>();
	Vector<String> nameList = new Vector<>();
	Vector<String> locationList = new Vector<>();

	public CreateDepot(Vector<String> storeInfoList) {
		for (int i = 0; i < storeInfoList.size(); i++) {
			StringTokenizer storeInfo = new StringTokenizer(storeInfoList.get(i), " ");

			typeList.add(storeInfo.nextToken());
			nameList.add(storeInfo.nextToken());
			locationList.add(storeInfo.nextToken());
		}
	}

	public CreateDepot() {

	}

	public Vector<String> getTypeList() {
		return typeList;
	}

	public Vector<String> getNameList() {
		return nameList;
	}

	public Vector<String> getLocationList() {
		return locationList;
	}
}