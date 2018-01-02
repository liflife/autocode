package com.autocode.util;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class TestFile {
	public static String outpath="D:/Other_WorkSpace/other/AutoCode/outFiles/src/goodsibatis.xml";

	public static void main(String[] args) throws IOException {
			FileOutputStream fileOutputStream = new FileOutputStream(new File(outpath));
			FileChannel channel = fileOutputStream.getChannel();
			long size = channel.size();
			System.out.println(size);
			
			FileDescriptor fd = fileOutputStream.getFD();
			System.out.println(fd.toString());
			
			
			fileOutputStream.close();
	}
}
