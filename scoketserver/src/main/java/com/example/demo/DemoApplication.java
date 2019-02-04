package com.example.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	
		  private static ServerSocket server;
		    //socket server port on which it will listen
		    private static int port = 6789;
		    
		    
		    public static void main(String args[]) throws IOException, ClassNotFoundException{
		        //create the socket server object
		        server = new ServerSocket(port);
		        //keep listens indefinitely until receives 'exit' call or program terminates
		        while(true){
		            System.out.println("Waiting for client request");
		            //creating socket and waiting for client connection
		            Socket socket = server.accept();
		            //read from socket to ObjectInputStream object
		            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		            //convert ObjectInputStream object to String
		            String message = (String) ois.readObject();
		            System.out.println("Message Received: " + message);
		            //create ObjectOutputStream object
		            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		            //write object to Socket
		            oos.writeObject("Success");
		            //close resources
		            ois.close();
		            oos.close();
		            socket.close();
		            
		            
		            
		            CloseableHttpClient httpclient = HttpClients.createDefault();
		            HttpPost httpPost = new HttpPost("http://localhost:8080/view/rest/hello");
		            String JSON_STRING="";
		            HttpEntity stringEntity = new StringEntity(message,ContentType.DEFAULT_TEXT);
		            httpPost.setEntity(stringEntity);
		            CloseableHttpResponse response2 = httpclient.execute(httpPost);
		            System.out.println("Done making post call");
		            }
		            
		            //terminate the server if client sends exit request
		            //if(message.equalsIgnoreCase("exit")) break;
		        }
		        //System.out.println("Shutting down Socket server!!");
		        //close the ServerSocket object
		        //server.close();
		    }



