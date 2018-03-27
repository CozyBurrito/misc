/**
 * Name: Mohammad Hameed
 * ID: 201597382
 * E-Mail: mhame382@mtroyal.ca
 * Assignment #: 3
 * Due Date: October 20th, 2015
 * Instructor: Charlie Hepler
 * Files: Main.java, Sort.java, Star.java
 * 
 */
public class Star {
		public double ra, dec, x, y, z, vx, vy, vz, distance;
		public String proper, spect;
		public float mag, absmag, ci;
		
		public Star(double ra, double dec, String proper, float mag, float absmag, String spect, float ci, double x,
				double y, double z, double vx, double vy, double vz, double distance) {
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
			this.distance = distance;
		}
	}