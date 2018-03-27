import java.io.IOException;
import java.io.RandomAccessFile;

public class BinaryHashTable {

	private RandomAccessFile table = null;
	private int hashType;
	public int size = 0, coll = 0, totalStarBlocks, hash, hash2;
	public boolean probed = false;
	public double q = 1;

	public BinaryHashTable (String filename, int hashType, int hashSize) throws IOException {
		table = new RandomAccessFile(filename, "rw");
		this.hashType = hashType;
		totalStarBlocks = 3 * hashSize;

		table.setLength(8 + (totalStarBlocks * Main.STAR_BYTE_SIZE));

		table.seek(0);
		if(hashType == 1)
			table.writeChars("Li");
		else
			table.writeChars("Qr");

		table.seek(8);
	}

	public void destruct() throws IOException {
		table.close();
	}
	
	public void writeSize(int size) throws IOException {
		table.seek(4);
		table.writeInt(size);
	}

	public void setSeek(int seek) throws IOException {
		table.seek(seek);
	}

	public void addToTable(Star star) throws IOException {	
		hash = hashCode(star.ra, star.dec);
		boolean added = false; 
		
		while (!added){
			if (probed && hashType == 2)
				table.seek(8 + (hash2 * Main.STAR_BYTE_SIZE));
			else
				table.seek(8 + (hash * Main.STAR_BYTE_SIZE));
			
			long offset = table.getFilePointer();
			if(table.readInt() == 0) {
				table.seek(offset);
				
				int flag = 1;
				table.writeInt(flag);

				table.writeDouble(star.ra);
				table.writeDouble(star.dec);
				table.writeUTF(star.proper);
				table.writeFloat(star.mag);
				table.writeFloat(star.absmag);
				table.writeUTF(star.spect);
				table.writeFloat(star.ci);
				table.writeDouble(star.x);
				table.writeDouble(star.y);
				table.writeDouble(star.z);
				table.writeDouble(star.vx);
				table.writeDouble(star.vy);
				table.writeDouble(star.vz);
				
				table.writeByte(0);
				table.writeByte(0);

				size++;
				writeSize(size);
				added = true;
			}
			else {
				probe();
				
				coll++;
				probed = true;
			}
		}
		probed = false;
		q = 1;
	}

	public void probe() {
		if (hashType == 1){
			hash = (hash + 1) % totalStarBlocks;
		}
		else if (hashType == 2){
			hash2 = (hash + (int) Math.pow(q , 2)) % totalStarBlocks;
			q++;
		}
	}

	public Star getStar(double ra, double dec) throws IOException {
		if (!probed)
			hash = hashCode(ra, dec);
		
		Star star = null;
		int flag;

		if (size > 0) {
			
			
			if (probed && hashType == 2)
				table.seek(8 + (hash2 * Main.STAR_BYTE_SIZE));
			else
				table.seek(8 + (hash * Main.STAR_BYTE_SIZE));
			
			flag = table.readInt();
			if (flag == 1) {
				star = new Star();
				
				star.ra     = table.readDouble();
				star.dec    = table.readDouble();
				star.proper = table.readUTF();
				star.mag    = table.readFloat();
				star.absmag = table.readFloat();
				star.spect  = table.readUTF();
				star.ci     = table.readFloat();
				star.x      = table.readDouble();
				star.y      = table.readDouble();
				star.z      = table.readDouble();
				star.vx     = table.readDouble();
				star.vy     = table.readDouble();
				star.vz     = table.readDouble();
				
				table.readByte();
				table.readByte();
				
			}
		}

		return star;
	}

	public int hashCode(double ra, double dec) {
		int hash = 7;

		hash = hash * 13 + (int) (dec / ra);
		hash = hash * 31 + (String.valueOf(ra)).hashCode();
		hash = hash + 3;
		hash = Math.abs(hash % totalStarBlocks);
		return hash;
	}
}






