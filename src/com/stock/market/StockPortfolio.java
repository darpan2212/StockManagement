package com.stock.market;

public class StockPortfolio {

	public static void main(String[] args) {
		
		Stock stock = new Stock();
		stock.readStocks();
		stock.createPortFolioReport();
		
	}
	
}