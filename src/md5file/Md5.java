package md5file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

public class Md5 {

	private static FileInputStream inputStream;
	//包存放地址
	static String  path="D:/234";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub	
		//生成md5文件
		String md5=path+"/"+filename(path)[0]+".md5";
		
		writefile(md5);
	}
	
	//写入文件
	public static void writefile(String md5) {
		FileWriter writer=null;
		try {
			writer = new FileWriter(md5);
			writer.write(filename(path)[0]+"="+md5file(new File(filepath1(path)))+"\r\n");
//			System.out.println(filename(path)[1]+"="+md5file(new File(filepath1(path))));
			writer.write(filename(path)[2]+"="+md5file(new File(filepath2(path))));
			writer.flush();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if (writer!=null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
	
	//扫描文件夹中的文件
	public static String[] filename(String path) {
		File file=new File(path);
		String[] filename=file.list();		
		return filename;
	}
	
	//获取文件路径
	public static String filepath1(String path) {
		return path+"/"+filename(path)[0];
	}
	public static String filepath2(String path) {
		return path+"/"+filename(path)[2];
	}
	
	//获取文件的MD5方法  
	public static String md5file(File file) throws FileNotFoundException {
		String value=null;
		inputStream = new FileInputStream(file);
		try {
			MappedByteBuffer byteBuffer=inputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5= MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bigInteger=new BigInteger(1,md5.digest());
			value=bigInteger.toString(16);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
			if (null!=inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		return value;
	}

}
