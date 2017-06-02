package test2;

import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JSlider;

import com.fazecast.jSerialComm.SerialPort;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		JSlider slider = new JSlider();
		slider.setMaximum(1023);
		window.add(slider);
		window.pack();
		//window.setVisible(true);
		
		SerialPort[] portNames = SerialPort.getCommPorts();
		System.out.println("SELECT a port :");
		int i = 1;
		for(SerialPort port : portNames){
			System.out.println(i++ + ". " + port.getSystemPortName());
		}
		
		Scanner s = new Scanner(System.in);
		int chosenPort = s.nextInt();
		
		SerialPort port = portNames[chosenPort-1];
		if(port.openPort()){
			System.out.println("Berhasil konek ke " + port.getSystemPortName());
		}
		else
		{
			System.out.println("Gagal membuka port");
			return;
		}
		
		port.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		
		Scanner data = new Scanner(port.getInputStream());
		while(data.hasNextLine()){
			System.out.println(data.nextLine());
		}

	}

}
