package gainProject;

import java.io.*;
import java.util.Vector;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Vector<String> storeInfoList = new Vector<>();
		Vector<String> typeList = new Vector<>();
		Vector<String> locationList = new Vector<>();

		File restaurantFile = new File("./res/restaurantinfo.txt");
		File typeFile = new File("./res/typelist.txt");
		File locationFile = new File("./res/locationlist.txt");

		new GetRestaurantList(restaurantFile, storeInfoList);
		new GetRestaurantList(typeFile, typeList);
		new GetRestaurantList(locationFile, locationList);

		CreateDepot store = new CreateDepot(storeInfoList, typeList, locationList);
		new UserInterface(store);
	}
}

class GetRestaurantList {
	public GetRestaurantList(File file, Vector<String> List) throws IOException {
		FileReader fileReader = new FileReader(file);
		BufferedReader storeList = new BufferedReader(fileReader);
		String storeInfo = "";

		while ((storeInfo = storeList.readLine()) != null)
			List.add(storeInfo);

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

class SaveTypeList {
	public SaveTypeList(Vector<String> typeList) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("./res/typelist.txt"));
		for (int i = 0; i < typeList.size(); i++)
			writer.write(typeList.get(i) + "\r\n");

		writer.close();
	}
}

class SaveLocationList {
	public SaveLocationList(Vector<String> locationList) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("./res/locationlist.txt"));
		for (int i = 0; i < locationList.size(); i++)
			writer.write(locationList.get(i) + "\r\n");

		writer.close();
	}
}