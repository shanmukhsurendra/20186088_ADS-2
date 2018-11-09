import java.awt.Color;

public class SeamCarver {
	// create a seam carver object based on the given picture
	private int height;
	private int width;
	Picture picture;
	private double[][] energy;
	public SeamCarver(Picture picture) {
		this.picture = picture;
			// width = picture.width();
			// height = picture.height();
			energy = new double[height][width];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					energy[i][j] = energy(j, i);
				}
			}
	}
	// current picture
	public Picture picture() {
		return null;
	}
	// width of current picture
	public int width() {
		return picture.width();
	}

	// height of current picture
	public int height() {
		return picture.height();
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if(x == 0 || y == 0 || x == picture.width() - 1 || y == picture.height() - 1) {
			return 1000;
		}
		Color cobj1 = picture.get(x+1, y);
		Color cobj2 = picture.get(x-1, y);
		Color cobj3 = picture.get(x, y+1);
		Color cobj4 = picture.get(x, y-1);
		int re =  Math.abs(cobj1.getRed() - cobj2.getRed());
		int bl = Math.abs(cobj1.getBlue() - cobj2.getBlue());
		int gr = Math.abs(cobj1.getGreen() - cobj2.getGreen());
		int xValue = re*re + bl*bl + gr*gr;
		int re2 = Math.abs(cobj3.getRed() - cobj4.getRed());
		int bl2 = Math.abs(cobj3.getBlue() - cobj4.getBlue());
		int gr2 = Math.abs(cobj3.getGreen() - cobj4.getGreen());
		int yValue = re2*re2 + bl2*bl2 + gr2*gr2;
		return Math.sqrt(xValue + yValue);
		// System.out.print(redColor + greenColor + blueColor);
		// return  0;
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {

		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		height = height();
		width = width();
		energy = new double[height][width];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					energy[i][j] = energy(j, i);
				}
			}
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph((width * height) + 2);
		for (int j = 0; j < width; j++) {

			graph.addEdge(new DirectedEdge(graph.V() - 2, j, energy[0][j]));
		}
		for (int i = 0; i < height - 1; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0) {
					graph.addEdge(new DirectedEdge(i, (((i + 1) * width) + j), energy[i + 1][j]));
					graph.addEdge(new DirectedEdge(i, (((i + 1) * width) + (j + 1)), energy[i + 1][j + 1]));
				} else if (i == width - 1) {
					graph.addEdge(new DirectedEdge(i, (((i + 1) * width) + j), energy[i + 1][j]));
					graph.addEdge(new DirectedEdge(i, (((i + 1) * width) + (j - 1)), energy[i + 1][j - 1]));
				} else {
					graph.addEdge(new DirectedEdge(i, (((i + 1) * width) + j - 1), energy[i + 1][j - 1]));
					graph.addEdge(new DirectedEdge(i, (((i + 1) * width) + j), energy[i + 1][j]));
					graph.addEdge(new DirectedEdge(i, (((i + 1) * width) + j + 1), energy[i + 1][j + 1]));
				}
			}
		}
		for (int j = 0; j < width; j++) {
			graph.addEdge(new DirectedEdge(((height - 1) * (width)) + j, graph.V() - 1, energy[height - 1][j]));
		}
		AcyclicSP sp = new AcyclicSP(graph, graph.V() - 2);
		Iterable<DirectedEdge> path = sp.pathTo(graph.V() - 1);
		int[] sparray = new int[height];
		int i = 0;
		for (DirectedEdge t : path) {
			sparray[i++] = t.from();
		}
		return sparray;
		// return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}