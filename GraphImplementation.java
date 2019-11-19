import java.util.*;

public class GraphImplementation implements Graph{
	private float[][] matrix;

	public GraphImplementation(int vertices) {
		matrix = new float[vertices][vertices];
	}

	public void addEdge(int v1, int v2) throws Exception {
		if(v1 < 0 || v2 < 0 || v1 >= matrix[0].length || v2 >= matrix[0].length) {
			throw new Exception("vertice is wrong");
		}
		matrix[v1][v2] = 1;
	}

	public List<Integer> neighbors(int vertex) throws Exception {
		if(vertex < 0 || vertex >= matrix[0].length) {
			throw new Exception();
		}

		List<Integer> myList = new ArrayList<Integer>();
		for(int i = 0; i < matrix[0].length; i++) {
			if(matrix[vertex][i] != 0) {
				myList.add(i);
			}
		}

		return myList;
	}

	public List<Integer> topologicalSort() {
		List<Integer> schedule = new ArrayList<Integer>();
		int vertices =  matrix[0].length;
		int[] sum = new int[vertices];
		for(int i = 0; i < vertices; i++) {
			for(int j = 0; j < vertices; j++) {
				sum[i] += matrix[j][i];
			}
		}

		for(int i = 0; i < vertices; i++) {
			int n = findZero(sum);
			schedule.add(n);
			sum[n] = -1;
			for(int k = 0; k < vertices; k++) {
				sum[k] -= matrix[n][k];
			}
		}

		return schedule;
	}

	private int findZero(int[] sum) {
		for(int i = 0; i < matrix[0].length; i++) {
			if(sum[i] == 0) {
				return i;
			}
		}

		return 0;
	}
}