package de.materna.GraphGsb;

public class Main {

	public static void main(String[] args) {
	//algA();
	algB();
	}

	public static void algB() {
		System.out.println();
		System.out.println("JETZT ALG B");
		System.out.println();
		Source z = new Filesystem();
		Reader r = new GraphContentHandler();

		long time = System.currentTimeMillis();
		reportPerformanceFor("starting at", time);
		r.printGraphB(z.getList());
		reportPerformanceFor("end", time);

	}

	public static void algA() {
		System.out.println();
		System.out.println("JETZT ALG A");
		System.out.println();
		Source z2 = new Filesystem();
		Reader r2 = new GraphContentHandler();

		long time = System.currentTimeMillis();
		reportPerformanceFor("starting at", time);
		r2.printGraphA(z2.getList());
		reportPerformanceFor("end", time);

	}

	private static void reportPerformanceFor(String msg, long refTime) {

		double time = (System.currentTimeMillis() - refTime) / 1000.0;
		double mem = usedMemory() / (1024.0 * 1024.0);
		mem = Math.round(mem * 100) / 100.0;
		System.out.println(msg + " (" + time + " sec, " + mem + "MB)");
	}

	private static long usedMemory() {

		Runtime rt = Runtime.getRuntime();
		return rt.totalMemory() - rt.freeMemory();
	}
}
