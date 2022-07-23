package com.stock.market;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Stock {

	ShareDetails[] stockList;
	int numOfShares;

	public void readStocks() {

		File stockFile = new File("stock.txt");
		FileReader fileReader = null;
		BufferedReader bfr = null;
		try {
			fileReader = new FileReader(stockFile);
			bfr = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String content;
		numOfShares = 0;
		try {
			bfr.mark(1000);
			while (bfr.readLine() != null) {
				numOfShares++;
			}
			bfr.reset();
			System.out.println("Number of shares : " + numOfShares);
			stockList = new ShareDetails[numOfShares];
			numOfShares = 0;
			while ((content = bfr.readLine()) != null) {
				stockList[numOfShares] = new ShareDetails();
				stockList[numOfShares].setShareName(content.split(" ")[0]);
				stockList[numOfShares]
						.setValue(Double.parseDouble(content.split(" ")[1]));
				stockList[numOfShares]
						.setQuantity(Integer.parseInt(content.split(" ")[2]));
				numOfShares++;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void printAllSharesDetails() {
		for (int i = 0; i < numOfShares; i++) {
			ShareDetails share = stockList[i];
			System.out.println(share.getShareName() + "\t" + share.getValue() + "\t"
					+ share.getQuantity());
		}
	}

	public void createPortFolioReport() {
		double totalPortFolioValue = 0;
		for (int i = 0; i < numOfShares; i++) {
			ShareDetails share = stockList[i];
			double totalShareValue = (share.value * share.quantity);
			totalPortFolioValue += totalShareValue;
			System.out.println(share.getShareName() + "\tINR " + share.getValue() + "\t"
					+ share.getQuantity() + "\t INR " + totalShareValue);
		}
		System.out.println("---------------------------------------------");
		System.out.println("Total portfolio value is : INR " + totalPortFolioValue);
	}
}