
public class Star {

	public double ra, dec, x, y, z, vx, vy, vz;
	public String proper, spect;
	public float mag, absmag, ci;

	public Star() {
		
	}

	public Star(double ra, double dec, String proper, float mag, float absmag, String spect, float ci, double x,
			double y, double z, double vx, double vy, double vz) {
		this.ra = ra;
		this.dec = dec;
		this.proper = proper;
		this.mag = mag;
		this.absmag = absmag;
		this.spect = spect;
		this.ci = ci;
		this.x = x;
		this.y = y;
		this.z = z;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
	}

	public void printStar() {
		
		System.out.print(ra            + "\t" +
						 dec           + "\t" +
						 proper.trim() + "\t" +
						 mag           + "\t" + 
						 absmag        + "\t" +
						 spect.trim()  + "\t" +
						 ci            + "\t" +
						 x             + "\t" +
						 y             + "\t" +
						 z             + "\t" +
						 vx            + "\t" +
						 vy            + "\t" +
						 vz);
		
		
		System.out.println("");

	}

}
