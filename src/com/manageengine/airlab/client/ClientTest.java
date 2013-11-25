package com.manageengine.airlab.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;

import com.manageengine.airlab.FeedbackData;
import com.manageengine.airlab.TCPUtil;

public class ClientTest {

	/**
	 * @param args
	 */

	private ClientClassifier cc;
	public ClientTest() throws Exception {
		
		cc = new ClientClassifier("127.0.0.1", 8888);
		//train();
		//predict();
		//getLastTrained();
		sendFeedback();
	}
	
	public void predict() throws Exception {
		
		String clientFile = "/Users/sivanand-0290/Scratchpad/predict.txt";
		System.out.println(cc.predictCategory("Category", clientFile));
	}
	
public void getLastTrained() throws Exception {
		
		System.out.println(cc.getLastTrained("Category"));
	}
	
	public void train() throws Exception {

		String clientFile = "/Users/sivanand-0290/Scratchpad/data.csv";
		System.out.println(cc.trainClassifier("Category", clientFile));
	}
	
	public void sendFeedback() throws Exception {
		
		FeedbackData data = new FeedbackData(100, 80, 10);
		cc.sendFeedback("Category", data);
	}
	
//	public void execute(int command, String clientFile) throws Exception {
//
//		Socket sock = new Socket("127.0.0.1", 8888);
//		//sock.setSoTimeout(20 * 1000);
//		System.out.println("IS Connected: " + sock.isConnected());
//		BufferedOutputStream out = new BufferedOutputStream(sock.getOutputStream());
//
//		out.write(TCPUtil.getCommandBytes(command));
//		byte [] module = TCPUtil.getModuleNameBytes("Category");
//		long payloadLength = module.length;
//		if(clientFile != null) {
//			
//			FileInputStream fin = new FileInputStream(clientFile);
//			byte[] buffer = new byte[TCPUtil.BUFFER_LENGTH];
//			int len = 0;
//
//			System.out.println("File Length: "+new File(clientFile).length());
//			payloadLength += (new File(clientFile).length());
//			
//			while((len = fin.read(buffer)) > 0) {
//				out.write(buffer, 0, len);
//			}
//			fin.close();
//		}
//		else {
//			out.write(TCPUtil.getPayloadLengthBytes(0L));
//		}
//		out.write(TCPUtil.getPayloadLengthBytes(payloadLength));
//		out.write(module);
//		out.flush();
//		System.out.println(System.currentTimeMillis()+"Finished writing data");
//		
//		BufferedInputStream in = new BufferedInputStream(sock.getInputStream());
//		
////		try {
////			Thread.sleep(3000);
////		} catch(InterruptedException ie) {
////			ie.printStackTrace();
////		}
//		int status = TCPUtil.getIntStatus(in);
//		System.out.println(System.currentTimeMillis()+"Status: "+status);
//		payloadLength = TCPUtil.getLongPayloadLength(in);
//		System.out.println(System.currentTimeMillis()+"Payload length: "+payloadLength);
//	
//		byte[] buffer = new byte[TCPUtil.BUFFER_LENGTH];
//		StringBuilder outputString = new StringBuilder();
//		while(payloadLength > 0) {
//			
//			int len = in.read(buffer);
//			payloadLength -= len;
//			outputString.append(new String(buffer));
//		}
//		
//		System.out.println(System.currentTimeMillis()+"Output: "+outputString.toString());
//		
//		in.close();
//		out.close();
//		sock.close();
//		System.out.println(System.currentTimeMillis()+"Socket closed");
//	}

	public static void main(String[] args) throws Exception {
		new ClientTest();
	}
}
