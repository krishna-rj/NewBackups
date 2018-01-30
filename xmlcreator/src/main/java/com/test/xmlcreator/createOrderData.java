package com.test.xmlcreator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class createOrderData {

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String POST_URL = "http://35.194.49.17:8080/smcfs/interop/InteropHttpServlet";
	
    public static void main(String[] args) throws IOException{

    	//String path = args[0];
    			
	try {
			 
		int count=0;
		
		//File pickupFolder;//"C:\\Users\\IBM_ADMIN\\Desktop\\orderDetails\\orderDetails\\1\\"
		String hostname="C:\\Users\\IBM_ADMIN\\Desktop\\orderDetails\\";
		System.out.println("Output"+hostname);
		
		File pickupFolder=new File(hostname);
		
		File[] files=pickupFolder.listFiles();
		
		int noOfFiles=files.length;
		//System.out.println("Output"+noOfFiles);
String apiInput="<MultiApi>";
	
		for(int i=0;i<noOfFiles;i++){
		
			//System.out.println(files[i]);
		 	File xmlFile = new File(""+files[i]);
			 
			if (xmlFile.exists()) {
				   if (isValidXMLFile(xmlFile.getAbsolutePath().toString())) {
			
					   BufferedReader br = new BufferedReader(new FileReader(files[i]));
				    
					   
				    try {
				    	
				        StringBuilder sb = new StringBuilder();
				        String line = br.readLine();

				        while (line != null) {
				            sb.append(line);
				            sb.append("\n");
				            line = br.readLine();
				            
				        }
				    
				        // Logic for String Replace
				        
				        String[] dataSplitted = sb.toString().split(" ");
						
						String strItemID = "OrderNo";
						
						String  itemIDKey = null;
						
						String[] dataItemIdfnl;
				        
						for(int j=0;j<dataSplitted.length;j++)
						{
							 int strItemIDIndex = dataSplitted[j].indexOf(strItemID);
							 
							 if(strItemIDIndex == 0){
								 
								  //System.out.println(dataSplitted[j]);
								  dataItemIdfnl=dataSplitted[j].split("=");
						          //System.out.println(dataOrderfnl[0]+"::"+dataOrderfnl[1]);
						          itemIDKey=dataItemIdfnl[1].toString();
						          itemIDKey=itemIDKey.substring(1, itemIDKey.length()-1);
						          //itemIDKeynew+=itemIDKey+",";
						          //System.out.println(itemIDKeynew);
						        				        
						        }
							 
							}    
						
						StringBuffer text = new StringBuffer(sb.toString());
					    text.replace( 16 , 19 ,"20171127"+itemIDKey);
				
				    apiInput ="<MultiApi><API Name='createOrder'><Input>"+text+"</Input></API></MultiApi>";
				   
				    System.out.println(apiInput);
				    
				    String POST_PARAMS = "InteropApiName=multiApi&IsFlow=N&YFSEnvironment.userId=admin&YFSEnvironment.password=password&InteropApiData="+apiInput;
				    
				  
				    URL obj = new URL(POST_URL);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
					con.setRequestMethod("POST");
					con.setRequestProperty("User-Agent", USER_AGENT);

					// For POST only - START
					con.setDoOutput(true);
					OutputStream os = con.getOutputStream();
					os.write(POST_PARAMS.getBytes());
					os.flush();
					os.close();
					// For POST only - END

					int responseCode = con.getResponseCode();
					
					if (responseCode == HttpURLConnection.HTTP_OK) { //success
						BufferedReader in = new BufferedReader(new InputStreamReader(
								con.getInputStream()));
						String inputLine;
						StringBuffer response = new StringBuffer();

						while ((inputLine = in.readLine()) != null) {
							response.append(inputLine);
						}
						in.close();

						System.out.println(response);
						
						Thread.sleep(200);
						
					} else {
						System.out.println("POST request not worked");
					}

				    }
				 
				    finally {
				        br.close();
				    }
				   }
			}

		 }
		}
	   catch (Exception ex) {
        	ex.printStackTrace();
        }
	

	 }


	    private static boolean isValidXMLFile(String filename) {

	    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	    	  try {
	    	   File f = new File(filename);
	    	   if (f.exists()) {
	    	    DocumentBuilder builder = factory.newDocumentBuilder();
	    	    Document document = builder.parse(f);
	    	    return true;
	    	   }


	    	  } catch (SAXParseException spe) {
	    	   System.out.println("Invalid XML");
	    	   return false;


	    	  } catch (SAXException sxe) {
	    	   System.out.println("Invalid XML");
	    	   return false;


	    	  } catch (ParserConfigurationException pce) {
	    	   System.out.println("Invalid XML");
	    	   return false;


	    	  } catch (IOException ioe) {
	    	   System.out.println("Invalid XML");
	    	   return false;
	    	  }
	    	  return true;
	    	 }
}

