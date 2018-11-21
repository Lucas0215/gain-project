package gainProject;

import java.io.*;
import java.util.Vector;

public class Main {
	public static void main(String[] args) throws IOException {
		Vector<String> storeInfoList = new Vector<>();
		new GetRestaurantList(storeInfoList);
		new CreateDepot(storeInfoList);
	}
}

class GetRestaurantList {
	public GetRestaurantList(Vector<String> storeInfoList) throws IOException {
		File restaurantFile = new File("./res/restaurantinfo.txt");
		FileReader fileReader = new FileReader(restaurantFile);
		BufferedReader storeList = new BufferedReader(fileReader);
		String storeInfo = "";

		while ((storeInfo = storeList.readLine()) != null)
			storeInfoList.add(storeInfo);

		storeList.close();
		fileReader.close();
	}
}

class SaveRestaurantList {
	public SaveRestaurantList(Vector<String> storeInfoList) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("./res/restaurantinfo.txt"));
		for (int i = 0; i < storeInfoList.size(); i++)
			writer.write(storeInfoList.get(i) + "\r\n");

		writer.close();
	}
}