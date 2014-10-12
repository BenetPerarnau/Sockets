import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
/*
 * Funciona com a servidor que simula un chat, comença parlant el que esta a la terminal
 * el chat finalitza al escriure exit.
 * Executar el programa i obrir una terminal i escriu =>  telnet 127.0.0.1 9000
 * 
 */

public class ConectorSocket {
	private static BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
	ServerSocket server;
	Socket socket;
	int puerto=9000;
	DataOutputStream salida;
	BufferedReader entrada;
	String msgE;
	String msgS;
	public void iniciar(){
		
		try{
			
			server = new ServerSocket(puerto);
			socket = new Socket();
			socket = server.accept();
			
			do{
					
			entrada = new BufferedReader (new InputStreamReader(socket.getInputStream()));
			salida = new DataOutputStream(socket.getOutputStream());
			
			msgE=entrada.readLine();
			
			if(!msgE.equalsIgnoreCase("exit")){
			System.out.println("Client => "+msgE);
			System.out.print("Servidor => ");
			msgS=stdin.readLine();
			}else{
				System.out.println("El Client ha tancat la sesio");
			}
			if(!msgS.equalsIgnoreCase("exit")){
			
			salida.writeUTF("Servidor => "+msgS+"\n");
			
			}else{
				salida.writeUTF("El Servidor ha tancat la sesio \n");
			}
			
			
			}while(!msgE.equals("exit") && !msgS.equals("exit"));
			
			
			System.out.println("Servidor tancat");
			
			socket.close();
			
		}catch(Exception e){
			
		}
		
		
	}
	
	
	

}
