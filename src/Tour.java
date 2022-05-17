
public class Tour {

	private Node first;

	public Tour() {
	}
	public Tour(Point a, Point b, Point c, Point d) {
		first = new Node();
		first.p = a;

		Node second = new Node();
		second.p = b;
		first.next = second;

		Node third = new Node();
		third.p = c;
		second.next = third;

		Node fourth = new Node();
		fourth.p = d;
		third.next = fourth;

		fourth.next = first;

	}
	public int size() {

		if(first == null) {
			return 0;
		}

		int size = 1;
		Node nextNode = first.next;
		while(!nextNode.p.equals(first.p)) {
			size++;
			nextNode = nextNode.next;
		}
		return size;
	}
	public double length() {
		double answer = 0;

		if(first == null) {
			return 0;
		}
		Node nextNode = first.next;
		Point nextPoint = nextNode.p;
		answer = first.p.distanceTo(nextPoint);
		while(!nextPoint.equals(first.p)) {
			nextNode = nextNode.next;
			answer += nextPoint.distanceTo(nextNode.p);
			nextPoint = nextNode.p;
		}

		return answer;

	}
	public String toString() {
		StringBuilder aa = new StringBuilder();
		if(first == null) {
			return "";
		}
		aa.append(first.p + "\n");
		Node nextNode = first.next;
		Point nextPoint = nextNode.p;
		while(!nextPoint.equals(first.p)) {
			aa.append(nextPoint + "\n");
			nextNode = nextNode.next;
			nextPoint = nextNode.p;
		}
		
		aa.deleteCharAt(aa.length()-1);

		return aa.toString();

	}
	public void draw() {
		if(first == null) {
			return;
		}
		Node nextNode = first.next;
		Point nextPoint = nextNode.p;
		first.p.drawTo(nextPoint);
		while(!nextPoint.equals(first.p)) {
			nextNode = nextNode.next;
			nextPoint.drawTo(nextNode.p);
			nextPoint = nextNode.p;
		}

	}
	public void insertNearest(Point p) {
		if(first == null) {
			first = new Node();
			first.p = p;
			first.next = first;
			return;
		}

		Node bestNode = first;
		double small = first.p.distanceTo(p);
		Node next = first.next;

		while(!next.p.equals(first.p)) {
			if(next.p.distanceTo(p) < small) {
				small = next.p.distanceTo(p);
				bestNode = next;
			}
			next = next.next;
		}

		Node nextBest = bestNode.next;

		Node n = new Node();
		n.p = p;
		bestNode.next = n;
		n.next = nextBest;

	}
	public void insertSmallest(Point p) {

		if(first == null) {
			first = new Node();
			first.p = p;
			first.next = first;
			return;
		}
		else if(first.next == first) {
			Node aa = new Node();
			aa.p = p;
			first.next = aa;
			aa.next = first;
			return;
		}


		Node bestNode = first;
		double d1 = bestNode.p.distanceTo(bestNode.next.p);
		double d2 = p.distanceTo(bestNode.p);
		double d3 = p.distanceTo(bestNode.next.p);
		double min = d3 + d2 - d1;

		Node next = first.next;

		while(!next.p.equals(first.p)) {
			d1 = next.p.distanceTo(next.next.p);
			d2 = p.distanceTo(next.p);
			d3 = p.distanceTo(next.next.p);
			double minTemp = d3+d2-d1;
			if (minTemp < min) {
				bestNode = next;
				min = minTemp;
			}
			next = next.next;
		}

		Node nextBest = bestNode.next;

		Node n = new Node();
		n.p = p;
		bestNode.next = n;
		n.next = nextBest;

	}
	public static void main(String[] args) {
		Point a = new Point(100.0, 100.0);
		Point b = new Point(500.0, 100.0);
		Point c = new Point (500.0, 500.0);
		Point d = new Point(100, 500);

		Tour squareTour = new Tour(a, b, c, d);
		int size = squareTour.size();
		double length = squareTour.length();
		System.out.println("Number of points: " + size);
		System.out.println("Length: " + length);
		System.out.println(squareTour);

		squareTour.insertSmallest(new Point(200,200));

		StdDraw.setXscale(0, 600);
		StdDraw.setYscale(0, 600);
		squareTour.draw();

	}

	private class Node {
		private Point p;
		private Node next;
	}

}

