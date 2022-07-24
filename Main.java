import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{  
		String [] addNo =new String [100];
		String [] sumNo =new String [100];
		String [] addNoValue =new String [100];
		int firstNo=0,secondNo=-1;
		int count=0,count1=0,count2=0;
		String thirdNo="";
		
		DocumentBuilderFactory dBfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dBfactory.newDocumentBuilder();
		// Fetch XML File
		Document document = builder.parse(new File("data.xml"));
		document.getDocumentElement().normalize();
		//Get root node
		Element root = document.getDocumentElement();
		//Get all values
		NodeList nList = document.getElementsByTagName("var");

		for (int i = 0; i < nList.getLength(); i++)
		{
			Node node = nList.item(i);
			System.out.println();    //Just a separator
			if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element element = (Element) node;
				if(!element.getAttribute("value").equals("")){
				  addNo[count++]=element.getAttribute("name");
				  addNoValue[count1++]=element.getAttribute("value");
				}
				else{
				  addNo[count++]=element.getAttribute("name");
				}
			}
		}
	
		NodeList nList1 = document.getElementsByTagName("add");

		for (int i = 0; i < nList1.getLength(); i++)
		{
			Node node = nList1.item(i);
			
			if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element element = (Element) node;
				for(int i1=0;i1<count;i1++){
				  String s= element.getAttribute("n1");
				  if(addNo[i1].equals(s)){
				    firstNo=i1;
				  }
				}
				for(int i1=0;i1<count;i1++){
				   String s= element.getAttribute("n2");
				  if(addNo[i1].equals(s)){
				    secondNo=i1;
				  }
				}
				if(secondNo==-1){
				  thirdNo=element.getAttribute("n2");
				}
				
				for(int i1=0;i1<count;i1++){
				  if(addNo[i1].equals(element.getAttribute("to"))){
				    if(thirdNo.equals("")){
				      int sum=Integer.valueOf(addNoValue[firstNo])+Integer.valueOf(addNoValue[secondNo]);
				      addNoValue[count1++]=Integer.toString(sum);
				      System.out.println(element.getAttribute("to")+"="+sum);
				    }else{
				      int sum=Integer.valueOf(addNoValue[firstNo])+Integer.valueOf(thirdNo);
				      addNoValue[count1++]=Integer.toString(sum);
				      System.out.println(element.getAttribute("to")+"="+sum);
				      
				    }
				  }
				}
				secondNo=-1;
			}
		}
	}
}