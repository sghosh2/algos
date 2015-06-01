/**
 * 
 */
package sample.queue.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * This is basic persistent queue
 * 
 * @author sam
 *
 */
public class FileHandler {

	private static final int MB = 1024 * 1024;
	private static final int INDEX_DATA_LEN = 12;
	
	private static final int FILE_READ_CHUNK = 1 * MB;
	
	private String path;
	private String fileName;
	private long size;
	
	
	//appendIndex
	private volatile long rear = 0;
	// read index
	private volatile long front = 0;
	
	private RandomAccessFile dataFile;
	private RandomAccessFile indexFile;
	private RandomAccessFile metaFile;
	
	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	ReadLock readLock = lock.readLock();
	WriteLock writeLock = lock.writeLock();
	
	public FileHandler (int size, String path, String fileName) {
		try {
		File f = new File(path);
		f.getCanonicalPath();
		if(!f.exists()) {
			f.mkdir();
			System.out.println("Following directory is created " + path);
		}
			
		this.path = path;
		this.fileName = fileName;
		this.size = size * MB;
		dataFile = new RandomAccessFile(path + File.separator + fileName + ".dat", "rw");
		indexFile = new RandomAccessFile(path + File.separator + fileName + "_index.dat", "rw");
		metaFile = new RandomAccessFile(path + File.separator + fileName + "_meta.dat", "rw");
	    
		if(metaFile.length() > 0) {
		rear = metaFile.readLong();
		front = metaFile.readLong();
		} else {
			indexFile.writeLong(0);
			metaFile.writeLong(0);
			metaFile.writeLong(0);
		}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void writeBytes(byte[] data) {
		try {
			writeLock.lockInterruptibly();
			long tempIndexRearInd = rear;
			
			FileChannel indexfc = indexFile.getChannel();
			MappedByteBuffer indexmbb = indexfc.map(MapMode.READ_WRITE, tempIndexRearInd,
					FILE_READ_CHUNK);
			
			long tempDataRearInd = indexmbb.getLong();
			if (tempDataRearInd + data.length > size) {
				tempDataRearInd = 0;
				tempIndexRearInd = 0;
			} 
			
			long nextTempDataRearInd = tempIndexRearInd + INDEX_DATA_LEN;
			
			if(nextTempDataRearInd == front && rear > 0) {
				System.out.println("queue is full");
				return;
			}
			
			// Write data in the data file
			//dataFile.seek(temp);
			FileChannel datafc = dataFile.getChannel();
			// fc.position(temp);
			MappedByteBuffer datambb = datafc.map(MapMode.READ_WRITE, tempDataRearInd,
					FILE_READ_CHUNK);
			datambb.put(data);
			datambb.force();
			//fc.close();
			
			// Write index information in index file
			indexmbb.putInt(data.length);
			indexmbb.putLong(tempDataRearInd + data.length);
			indexmbb.force();
			
			rear = nextTempDataRearInd;
			writeMetaData();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			writeLock.unlock();
		}
	}
	
	public byte[] readBytes() {
		byte[] data = null;
		try {
			readLock.lockInterruptibly();
			long tempIndexFrontInd = front;
			
			if(tempIndexFrontInd == rear) {
				System.out.println("queue is empty");
				return data;
			}
			
			FileChannel indexfc = indexFile.getChannel();
			MappedByteBuffer indexmbb = indexfc.map(MapMode.READ_ONLY, tempIndexFrontInd,
					FILE_READ_CHUNK);
			long tempDataFrontInd = indexmbb.getLong();
			int datalen = indexmbb.getInt();
			
			FileChannel datafc = dataFile.getChannel();
			MappedByteBuffer mbb = datafc.map(MapMode.READ_ONLY, tempDataFrontInd, FILE_READ_CHUNK);
			data = new byte[datalen];
			mbb.get(data);
			
            // advance the index file rear pointer
			front = tempIndexFrontInd + INDEX_DATA_LEN;;
			writeMetaData();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			readLock.unlock();
		}
		return data;
	}

	
	private void writeMetaData() throws IOException {
		FileChannel mfc = metaFile.getChannel();
		MappedByteBuffer metabb = mfc.map(MapMode.READ_WRITE, 0,
				16);
		metabb.putLong(rear);
		metabb.putLong(front);
		metabb.force();
	}
	
	private void readWholeFile() {
		System.out.println("Data File");
		try {
			String str = null;
			dataFile.seek(0);
			while((str = dataFile.readLine())!=null) {
				System.out.println(str);
			}
			System.out.println("Index File");
			System.out.println("Index    size");
			indexFile.seek(0);
			str =  indexFile.readLong() + "  " + indexFile.readInt();
			while(str!=null) {
				System.out.println(str);
				str =  indexFile.readLong() + "  " + indexFile.readInt();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			final FileHandler fl = new FileHandler(4, "/tmp/persistentq", "queue");
/*			fl.readWholeFile();
			System.exit(0);*/
			Thread producer = new Thread(new Runnable() {
				
				public void run() {
					try {
						for (int i = 15; i < 20; i++) {
							int num = (int) (Math.random()*10000);
							String str = "test " + num;
							System.out.println(" Producing " + num);
							fl.writeBytes(str.getBytes("UTF-8"));
						}
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} 
				}
			});
			
			Thread consumer = new Thread(new Runnable() {
				
				public void run() {
					try {
						for (int i = 0; i < 10; i++) {
							byte [] bb = fl.readBytes();
							System.out.println("consuming " + (bb != null ? new String(bb, "UTF-8") : "empty"));
							//Thread.sleep(1000);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
				}
			});
			
			producer.start();
			consumer.start();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//fileSeek();

	}

	
	
	private static void fileSeek() {
		try {
			RandomAccessFile rf = new RandomAccessFile("test", "rw");
		    for (int i = 0; i < 10; i++)
		      rf.writeLong(i);
		    rf.close();
		    rf = new RandomAccessFile("test.dat", "rw");
		    rf.seek(5 * 8);
		    rf.writeLong(47);
		    rf.close();
		    rf = new RandomAccessFile("test.dat", "r");
			rf.seek(4 * 8);
			FileChannel fileChannel = rf.getChannel();
		    for (int i = 0; i < 10; i++)
		      System.out.println("Value " + i + ": " + rf.readLong());
		    rf.close();
			
			seekPosition();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void seekPosition() throws FileNotFoundException,
			IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile(
				"test.dat", "rw");
		randomAccessFile.seek(1000);
		FileChannel fileChannel = randomAccessFile.getChannel();
		// This will print "1000"
		System.out.println("file pos: " + fileChannel.position());
		randomAccessFile.seek(500);
		// This will print "500"
		System.out.println("file pos: " + fileChannel.position());
		fileChannel.position(200);
		// This will print "200"
		System.out
				.println("file pos: " + randomAccessFile.getFilePointer());
	}

}
