package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ServidorEcho {

	private ServerSocket sckServidor;

	public ServidorEcho() throws IOException {
		this.sckServidor = new ServerSocket(4000);

		for (;;) {
			Socket sckEcho;
			InputStream canalEntrada;
			OutputStream canalSaida;
			BufferedReader entrada;
			PrintWriter saida;

			sckEcho = this.sckServidor.accept();
			canalEntrada = sckEcho.getInputStream();
			canalSaida = sckEcho.getOutputStream();
			entrada = new BufferedReader(new InputStreamReader(canalEntrada));
			saida = new PrintWriter(canalSaida, true);

			saida.println("Digite uma frase para usar como base para a seed.");
				while(true) {
					String linhaPedido = entrada.readLine();

					if (linhaPedido == null || linhaPedido.length() == 0)
						break;

					StringBuilder sb = new StringBuilder();
					for (char c : linhaPedido.toCharArray())
						sb.append((int)c);

					BigInteger mInt = new BigInteger(sb.toString());

					int senha = mInt.intValue();
					System.out.println(senha);
					LocalDateTime lt = LocalDateTime.now();
					senha *= lt.getNano();
					if(senha < 0)
						senha *= -1;
					saida.println(senha);
				}
			sckEcho.close();
		}
	}
}
