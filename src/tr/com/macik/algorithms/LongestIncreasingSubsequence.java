package tr.com.macik.algorithms;

import java.util.ArrayList;

import tr.com.macik.tools.Log;

public class LongestIncreasingSubsequence implements Runnable{
	private static int[] result;
	private static int maxHops;
	private static String[] resultHops;
	private static int jobCount;
	
	private int [] workingSequence;
	private int workingIdx;
	
	// For testing purpose multiple test data sets
	static int[][] dataSets = {{3, 1, 6, 2}, {0, 3, 1, 6, 2, 2, 7},
			{10, 9, 2, 6, 3, 7, 101, 18},
			{3, 10, 2, 1, 10}};

	// Constructor for the thread with initialization arguments
	public LongestIncreasingSubsequence(int idx, int[] sequence) {
		workingSequence = sequence;
		workingIdx = idx;
		
	}

	public static void main(String[] args) {
		Log.debug = false;
		int[] testData;
		testData = dataSets[0];		// set test data
		
		// search the Longest Increasing Subsequence hops and print it out
		Log.print("Test data: ").print(testData).nl();
		Log.print("Hops: ").set("dlm", "\n").print(evaluate(testData)).nl();
		Log.print("max Hops: ").print(maxHops).nl();

		testData = dataSets[1];		// set test data
		
		// search the Longest Increasing Subsequence hops and print it out
		Log.print("Test data: ").print(testData).nl();
		Log.print("Hops: ").set("dlm", "\n").print(evaluate(testData)).nl();
		Log.print("max Hops: ").print(maxHops).nl();

		testData = dataSets[2];		// set test data
		
		// search the Longest Increasing Subsequence hops and print it out
		Log.print("Test data: ").print(testData).nl();
		Log.print("Hops: ").set("dlm", "\n").print(evaluate(testData)).nl();
		Log.print("max Hops: ").print(maxHops).nl();

		testData = dataSets[3];		// set test data

		// search the Longest Increasing Subsequence hops and print it out
		Log.print("Test data: ").print(testData).nl();
		Log.print("Hops: ").set("dlm", "\n").print(evaluate(testData)).nl();
		Log.print("max Hops: ").print(maxHops).nl();

	}

	private static String[] evaluate(int[] data) {
		maxHops = 0;
		jobCount = 0;
		
		int[][] dataSet = new int[data.length][];	// half matrix of the given array size

		// 3, 1, 6, 2	--> 4
		// 0  1  2  3
		// [0] <[0]><.><.><.>	[0][1][2][3]		
		// [1] <[1]><.><.>		[0][1][2]
		// [2] <[2]><.>			[0][1]
		// [3] <[3]>			[0]
		// build each sequences to check
		for (int i=0; i<data.length; i++) {
			Log.debug("Länge der Zeile", data.length-i);
			dataSet[i] = new int[data.length-i];
			for (int j=0; j<dataSet[i].length; j++) {
				Log.debug(new String[][] {{"Row/Column; Data[idx]", ""+i, "/", ""+j,
							"; "+data[j+i], "[", ""+(j+i), "]"}
				 						 });
				dataSet[i][j] = data[j+i];
			}
		}

		if (Log.debug)
			for (int[] row : dataSet) {
				for (int col : row) {
					System.out.print(col);
					System.out.print("\t");
				}
				System.out.println();
			}
		
		// start for each sequence a thread, only if it has more than one element
		result = new int[dataSet.length];
		resultHops = new String[dataSet.length];
		for (int i=0; i<dataSet.length; i++) {
			if (dataSet[i].length > 1) {
				Thread th = new Thread(new LongestIncreasingSubsequence(i, dataSet[i]));
				th.start();
			} else {
				result[i] = 1;
				jobCount++;
			}
		}
		
		// wait on all threads
		while (jobCount < dataSet.length) {
			;
		};
		
		// return the max hops
		//return maxHops;

		ArrayList<String> resultList = new ArrayList<>();
		for (int i=0; i<dataSet.length; i++) {
			if (result[i]==getMaxHops())
				resultList.add(resultHops[i]);
		}
		
		// return the list of max hops sequence
		return resultList.toArray(new String[0]);
	}

	private static int getMaxHops() {
		if (maxHops==0) { 
			// search the max hops
			for (int i=0; i<result.length; i++)
				maxHops = maxHops < result[i] ? result[i] : maxHops;
		}
		return maxHops;
	}

	@Override
	public void run() {
		// print identification of the thread
		Log.debug("Thread running!", workingIdx);
		Log.debug("Working Sequence", workingSequence);
		
		// search the increasing subsequence hops and store in static result array
		String resultString = "";		
		int lastitem = 0;
		for(int item : workingSequence) {
			if (result[workingIdx] == 0) {
				lastitem = item;
				resultString += lastitem;
				result[workingIdx]++;
			} else {
				if (lastitem < item) {
					lastitem = item;
					resultString += ", " + lastitem;
					result[workingIdx]++;
				}
			}
		}
		
		resultHops[workingIdx] = resultString;
		
		// after finish of the work, increase jobCount synchronized for one more thread
		synchronized (new Integer(jobCount)) {
			jobCount++;
			Log.debug(new String[][] {{"Job Count (synchronized)", ""+jobCount}
									 ,{""+workingIdx, resultString}});
			Log.debug("Thread finished!", workingIdx);
		}
	}

}
