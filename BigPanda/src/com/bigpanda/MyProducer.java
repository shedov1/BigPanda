package com.bigpanda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyProducer implements Runnable {

	private Process process = null;
	public void run() {

		try {
			
			process = new ProcessBuilder("c:\\testDov\\generator-windows-amd64.exe").start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;

			while ((line = br.readLine()) != null) {
				MyConsumer.getInstance().accept(line);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			shutProcess();
		}

	}
	public void shutProcess() {
		process.destroy();
	}

}
