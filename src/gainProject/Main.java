package gainProject;

import java.io.*;
import java.util.Vector;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Vector<String> storeInfoList = new Vector<>();
		new GetRestaurantList(storeInfoList);
		CreateDepot store = new CreateDepot(storeInfoList);
		/*Scanner scan = new Scanner(System.in);

		String[] info = new String[3];
		int num;

		while (true) {
			System.out.println(store.storeInfoList);
			System.out.println("0 : exit 1 : add 2: delete 3 : modify 4 : random");
			String answer = scan.next();
			if (answer.equals("0"))
				break;

			switch (answer) {
			case "1":
				System.out.println("추가할 정보");
				for (int i = 0; i < 3; i++)
					info[i] = scan.next();
				store.add(info);
				break;
			case "2":
				System.out.println("지울 번호");
				num = scan.nextInt();
				store.delete(num);
				break;
			case "3":
				System.out.println("바꿀 번호");
				num = scan.nextInt();
				System.out.println("정보 재입력");
				for (int i = 0; i < 3; i++)
					info[i] = scan.next();
				store.modify(info, num);
				break;
			case "4":
				String[] a = DepotUtil.randomRecommend(store.storeList);
				System.out.println(a[0] + " " + a[1] + " " + a[2]);
				break;
			case "5":
				Vector<String> ab = DepotUtil.sort(store.storeInfoList);
				System.out.println(ab);
			default:
				break;
			}
		}
		scan.close();*/
		new UserInterface(store);
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