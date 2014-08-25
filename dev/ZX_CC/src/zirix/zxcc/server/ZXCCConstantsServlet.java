package zirix.zxcc.server;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

@WebServlet( name="ZXCCConstantsServlet", urlPatterns = {"/services/constants"}, loadOnStartup=1)
public class ZXCCConstantsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static int READ = 0;
	public static int WRITE = 1;

	public static String URL_ADRESS_= null; //"http://192.168.0.50:8080/zxcc/"; "http://www.zirix.com/zxcc_prod/";
	public static String DB_NAME_= null; //"ZX_CC_DEV.dbo."; "ZX_CC_PROD";
	public static String LOCAL_= null; //"DEV"; "PROD";

    public ZXCCConstantsServlet() {
        main();
    }

    public static void main() {

    	try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse (new File("webapps/zxcc/WEB-INF/VariaveisZXCC.xml"));

			doc.getDocumentElement ().normalize ();

			NodeList listOfPersons = doc.getElementsByTagName("parametros");

			for(int s=0; s<listOfPersons.getLength() ; s++){
				Node firstPersonNode = listOfPersons.item(s);
				if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){

					Element firstPersonElement = (Element)firstPersonNode;

					NodeList firstNameList = firstPersonElement.getElementsByTagName("db_name");
					Element firstNameElement = (Element)firstNameList.item(0);
					NodeList textFNList = firstNameElement.getChildNodes();
					DB_NAME_ = (String)textFNList.item(0).getNodeValue().trim();
					System.err.println("DB_NAME_ = " + DB_NAME_);

					NodeList lastNameList = firstPersonElement.getElementsByTagName("adress");
					Element lastNameElement = (Element)lastNameList.item(0);
					NodeList textLNList = lastNameElement.getChildNodes();
					URL_ADRESS_ = (String)textLNList.item(0).getNodeValue().trim();
					System.err.println("URL_ADRESS_ = " + URL_ADRESS_);

					NodeList ageList = firstPersonElement.getElementsByTagName("local");
					Element ageElement = (Element)ageList.item(0);
					NodeList textAgeList = ageElement.getChildNodes();
					LOCAL_ = (String)textAgeList.item(0).getNodeValue().trim();
					System.err.println("LOCAL_ = " + LOCAL_);
				}
			}
		}catch (SAXParseException err) {
			System.out.println ("** Parsing error" + ", line " + err.getLineNumber () + ", uri " + err.getSystemId ());
			System.out.println(" " + err.getMessage ());
		}catch (SAXException e) {
			Exception x = e.getException ();
			((x == null) ? e : x).printStackTrace ();
		}catch (Throwable t) {
			t.printStackTrace ();
		}
	}

    public static String getLocal(){return LOCAL_;}
    public static String getDbName(){return DB_NAME_;}
    public static String getAdress(){return URL_ADRESS_;}
    
}
