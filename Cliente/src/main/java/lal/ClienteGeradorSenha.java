package lal;

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

public class ClienteEcho {

	public static void main(String[] args) {

		Socket socket;
		InputStream canalEntrada;
		OutputStream canalSaida;
		BufferedReader entrada;
		PrintWriter saida;

		try {
			socket = new Socket("127.0.0.1", 4000);

			canalEntrada = socket.getInputStream();
			canalSaida = socket.getOutputStream();

			entrada = new BufferedReader(new InputStreamReader(canalEntrada));
			String resposta = entrada.readLine();
			System.out.println(resposta);
			Scanner answer = new Scanner(System.in);
			answer.nextLine();
			saida = new PrintWriter(canalSaida, true);
			saida.println(answer);
			terminate:while(true) {
				while(true) {
					String answ =entrada.readLine();

					if(answ.length() == 0 || answ == null)
						break;
					System.out.println(answ);
					break terminate;
				}
			}




		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}