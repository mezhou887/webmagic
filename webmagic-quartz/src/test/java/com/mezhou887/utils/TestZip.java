package com.mezhou887.utils;

public class TestZip {
	
	public static void main(String[] args) {
		ZipCompressor zc = new ZipCompressor("E:\\szhzip.zip");
		zc.compressExe("E:\\test");

		ZipCompressorByAnt zca = new ZipCompressorByAnt("E:\\szhzipant.zip");
		zca.compressExe("E:\\test");
	}
}
