package bid;


public class Auction {

	public static double maxBidder(double[] valuations) {

		double privateVal = 0;
		int winner = 0;

		for(int i=0; i<valuations.length; i++) {
			if(valuations[i] >= privateVal) {
				privateVal = valuations[i];
				winner = i;
			}
		}

		System.out.println("Max bidder is " + winner + " of " + privateVal);
		return privateVal;
	}


	public static double secondHighest(double privateVal, double[] valuations) {

		double max = privateVal;
		double secondMax = 0;
		int loc = 0;

		for(int i=0; i<valuations.length; i++) {
			if(valuations[i] > secondMax && valuations[i] < max) {
				secondMax = valuations[i];
				loc = i;
			}
		}
		System.out.println("Second highest (and revenue) is " + loc + " of " + secondMax);
		return secondMax;
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

	public static double bidderSurplus(double privateVal, double secondHighest) {

		double bidSurplus = privateVal - secondHighest;
		bidSurplus = roundDec(bidSurplus);
		System.out.println("Bid surplus " + bidSurplus);

		return bidSurplus;
	}

	public static void toString(double[] bids) {
		String str = "[";

		for(int i=0; i<bids.length-1; i++) {
			str += bids[i] + ", ";
		}

		str += bids[bids.length-1] + "]";

		System.out.println(str);
	}

	public static double roundDec(double num) {
		num = (double) Math.round(num*100)/100;
		return num;
	}

	public static void main(String[] args) {


		double bidderSurplus = 0;
		double revenue = 0;

		for(int i=0; i<500; i++) {
			System.out.println("Simulation " + i);
			double[] test = randPrivateVal();
			toString(test);

			double findMax = maxBidder(test);
			double secondH = secondHighest(findMax, test);

			double surplus = bidderSurplus(findMax, secondH);
			bidderSurplus += surplus;
			revenue += secondH;
			System.out.println("");
		}

		bidderSurplus = roundDec((bidderSurplus/500));
		revenue = roundDec((revenue/500));


		System.out.println("Avgerage total bidder surplus " + bidderSurplus);
		System.out.println("Average total seller revenue " + revenue);
	}

}
