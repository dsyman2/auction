package bid;

public class Sealed {

	public static double roundDec(double num) {
		num = (double) Math.round(num*100)/100;
		return num;
	}

	public static void toString(double[] bids) {
		String str = "[";

		for(int i=0; i<bids.length-1; i++) {
			str += bids[i] + ", ";
		}

		str += bids[bids.length-1] + "]";

		System.out.println(str);
	}	

	public static double[] randPrivateVal() {

		// each index represents one bidder
		double[] bids = new double[5];
		// each bidder's private valuation generated
		// independently over [0,10]

		for(int i=0; i<bids.length; i++) {
			double valuation = roundDec(Math.random()*11);
			bids[i] = valuation;
		}

		return bids;
	}

	public static double[] shadeBid(double[] privateVal, double x) {

		double[] shaded = new double[5];

		for(int i=0; i<privateVal.length; i++) {
			shaded[i] = roundDec(privateVal[i]*x);
			//System.out.println("Originally " + privateVal[i] + " bid " + x +" of valuation at " + shaded[i]);
		}

		return shaded;
	}

	public static int maxBidder(double[] valuations) {

		double privateVal = 0;
		int winner = 0;

		for(int i=0; i<valuations.length; i++) {
			if(valuations[i] >= privateVal) {
				privateVal = valuations[i];
				winner = i;
			}
		}

		//System.out.println("Max bidder is " + winner + " of " + privateVal);
		return winner;
	}

	public static void simulation(double[] perc) {
		double revenue = 0;
		double bidderSurplus = 0;

		for(int j=0; j<perc.length; j++) {
			System.out.println("When x is " + perc[j]);
			for(int i=0; i<500; i++) {
				//System.out.println("Simulation " + i);
				double[] privVal = randPrivateVal();
				double[] shaded = shadeBid(privVal, perc[j]);
				//toString(shaded);
				int winner = maxBidder(shaded);

				double surplus = privVal[winner] - shaded[winner];
				bidderSurplus += surplus;

				revenue += shaded[winner];

			//	System.out.println("");
			}

			bidderSurplus = roundDec((bidderSurplus/500));
			revenue = roundDec((revenue/500));

			System.out.println("Average total bidder surplus " + bidderSurplus);
			System.out.println("Average total seller revenue " + revenue);
			System.out.println("");
		}
	}

	public static void main(String[] args) {

		double[] per = {0.90, 0.80, 0.70, 0.60, 0.50};
		simulation(per);

	}

}
