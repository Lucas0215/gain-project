package gainProject;

import java.io.*;
import java.util.Vector;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Vector<String> storeInfoList = new Vector<>();
		new GetRestaurantList(storeInfoList);
		CreateDepot store = new CreateDepot(storeInfoList);
		DepotUtil manager = new DepotUtil();
		Scanner scan = new Scanner(System.in);

		while (true) {
			System.out.println(store.storeInfoList);
			System.out.println("1 : add 2: delete 3 : random");
			int answer = scan.nextInt();
			switch (answer) {
			case 1:
				String[] info = new String[3];
				for(int i=0;i<3;i++)
					info[i]=scan.next();
				store.add(info);
				break;
			case 2:
				String name = scan.next();
				store.delete(name);
				break;
			case 3:
				String[] a = DepotUtil.randomRecommend(store.storeList);
				System.out.println(a[0] + " " + a[1] + " " + a[2]);
				break;
			}
		}
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