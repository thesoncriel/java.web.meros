package hr.manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import hr.member.ClassType;
import hr.member.IMember;
import hr.member.Horse;
import hr.member.HorseRace;
import hr.member.Player;
import hr.member.Result;

/**
 * <p>HorseRace Edition v1.0</p>
 * <p>
 * 객체를 XML로 (Marshalling) 혹은 XML을 객체로 (Unmarshalling) 손쉽게 변환하기 위해 만들어진 클래스 입니다.<br>
 * javax.xml.bind의 JAXB를 이용하여 구현 하였습니다 :)
 * </p>
 * @author TheSON
 * 
 * @see {@link JAXBContext}, {@link Marshaller}, {@link Unmarshaller}
 * 
 * 
 *
 */
public class XMLManager extends AbstractFileManager{
	private String xslPath = null;
	//private String XMLPath;
	private File XMLFile;
	private JAXBContext jc;
	
	/**
	 * 생성자. HorseRace타입인 hr을 새로 생성하고 JAXBContext타입인 jc를 초기화 한다.
	 */
	public XMLManager(){
		this.hr = new HorseRace().createChilds();
		try{
			jc = JAXBContext.newInstance("hr.member");
		}catch(JAXBException e){
			e.printStackTrace();
			jc = null;
		}
	}
	/**
	 * 생성자. HorseRace타입인 hr을 새로 생성하고 JAXBContext타입인 jc를 초기화 한다.<br>
	 * XMLManager 생성시 HorseRace 데이터를 Vector 데이터로 채우고자 할 때 사용.
	 * @param vec :
	 * {@link Vector} - 채우고 싶은 데이터가 있는 Vector
	 */
	public XMLManager(HorseRace hr){
		this.hr = hr.clone();
		try{
			jc = JAXBContext.newInstance("hr.member");
		}catch(JAXBException e){
			e.printStackTrace();
			jc = null;
		}
	}
	public XMLManager(IMember imbr){
		this();
		try{
			List<IMember> list = new ArrayList<IMember>();
			list.add(imbr);
			this.hr.setMembers(list);;
		}
		catch(Exception ex){}
	}
	public XMLManager(List<? extends IMember> list){
		this();
		this.hr.setMembers(list);
	}
	/**
	 * XML파일을 객체로 읽어오는 메서드.
	 * XML파일을 클라이언트의 파일 시스템에서 읽어오고자 할 때 쓰임.<br>
	 * 파일경로를 문자열 형태로 지정.
	 * @param filePath
	 * {@link String} - 파일 경로
	 * @return
	 * {@link HorseRace} - 반환 형태
	 */
	
	public HorseRace read(File file){
		try{
			Unmarshaller umar = jc.createUnmarshaller();
			hr = (HorseRace)umar.unmarshal(file);

			return this.fixPicURL(file, hr);
		}
		catch(NullPointerException e){
			//System.out.println("널포인터");
			return null;//TODO 음 -_-
		}
		catch(JAXBException e){
			//System.out.println("JAXB오류");
			return null;
		}
	}
	
	/**
	 * 객체를 XML로 변환하여 저장하고자 할 때 쓰이는 메서드.<br>
	 * 저장되는 객체는 XMLManager에 이미 설정되어 있는 것을 사용하게 된다.
	 * @param filePath :
	 * {@link String} - 저장하고픈 경로
	 * @return
	 * {@link boolean} - 저장이 잘되었으면 true, 문제가 있었다면 false.
	 */
	public boolean write(String filePath){
		return this.write(new File(filePath));
	}
	public boolean write(File file){
		this.checkDir(file);
		
		//ImageSaver is = new ImageSaver(this.hr, file.getParent());
		
		//is.write();
		//this.modPicURL(this.SUB_FOLDER_NAME);
		return this.writeCommon(this.hr, file);
	}
	protected boolean writeCommon(HorseRace hr, File file){
		Marshaller mar = null;
		XMLFile = file;
		try {
			mar = jc.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			FileOutputStream fos = new FileOutputStream(file);
			if(xslPath != null){
				mar.setProperty(Marshaller.JAXB_FRAGMENT, true);
				fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n".getBytes());
				fos.write(("<?xml-stylesheet type=\"text/xsl\" href=\"" + xslPath + "\"?>").getBytes());
			}
			mar.marshal(hr, fos);
			fos.close();
			return true;
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public /*OutputStream*/String getXML(){
		Marshaller mar = null;
		try {
			mar = jc.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ByteArrayOutputStream fos = new ByteArrayOutputStream();
			
			if(xslPath != null){
				mar.setProperty(Marshaller.JAXB_FRAGMENT, true);
				//fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n".getBytes());
				fos.write(("<?xml-stylesheet type=\"text/xsl\" href=\"" + xslPath + "\" ?>").getBytes());
			}
			//mar.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml-stylesheet type=\"text/xsl\" href=\"foobar.xsl\" ?>"); 
			mar.marshal(hr, fos);
			fos.close();
			String result = fos.toString("UTF-8");
			//DataOutputStream dos = new DataOutputStream(fos);
			
			//return fos;
			return result;
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void setXslPath(String path){
		xslPath = path;
	}
	public String getXslPath(){
		return xslPath;
	}
	
	
	/**
	 * 내보내진 XML파일과 함께 첨부하여 일반적인 문서 양식으로 볼 수 있게 해주는 xsl파일을 저장한다.<br>
	 * xsl 파일은 xml 파일이 저장된 곳과 동일한 경로에 저장된다.
	 * @return
	 * {@link boolean} - 저장이 잘되었으면 true, 문제가 있었다면 false.
	 */
	/*
	@SuppressWarnings("finally")
	public boolean exportXSL(ClassType classType){
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(XMLFile.getParent() + XSLFileName), "UTF8"));
			XSLSample xsl = new XSLSample();
			////System.out.println(xsl.getXSL());
			bw.write(xsl.getXSL(classType));
			xsl.includeXSL(XMLFile);
			bw.close();
			////System.out.println(XMLPath + XSLFileName + " - XSL저장성공");
			return true;
		}
		catch (UnsupportedEncodingException e) {e.printStackTrace();}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e){e.printStackTrace();}
		finally {return false;}
	}*/
	
	
}

class XSLSample
{
	private StringBuffer xml;
	public XSLSample(){
		xml = new StringBuffer();
	}
	public void includeXSL(File file){
		try{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			
			String tmp = "";
			int c = 0;
			while((tmp = br.readLine()) != null){
				xml.append(tmp + "\r\n");
				if(c == 0) xml.append("<?xml:stylesheet type=\"text/xsl\" href=\"hr_form.xsl\"?>");
				c++;
			}
			br.close();
			//xml.insert("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>".length(), );
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"));
			
			bw.write(xml.toString());
			bw.close();
		}catch(FileNotFoundException ex){
			//System.out.println(file.getName() + " 파일을 찾을 수 없다네요 ㅠ.ㅠ 확인점..");
		}catch(IOException ex){
			//System.out.println(ex);
		}catch(Exception ex){
			//System.out.println(ex);
		}
	}
	public String getXSL(ClassType classType){
		if(classType == ClassType.PLAYER)
			return xslCommon1 + xslPlayer + xslCommon2;
		else
			return xslCommon1 + xslHorse + xslCommon2;
		
	}
	private String xslCommon1 =
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"+
		"<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\r\n"+
		"<xsl:template match=\"/horseRace\">\r\n"+
		"<html>\r\n"+
		"	<head>\r\n"+
		"		<title>\r\n"+
		"		<xsl:value-of select=\"header/author\"/>\r\n"+
		"	</title>\r\n"+
		"	<style type=\"text/css\">\r\n"+
		"		body{background-color:#eeeeee;}\r\n"+
		"		.title{\r\n"+
		"			font-size:26px;\r\n"+
		"			font-weight:bold;\r\n"+
		"			font-family:맑은 고딕, 굴림;\r\n"+
		"			letter-spacing:1.2em;\r\n"+
		"		}\r\n"+
		"		.leftPane{\r\n"+
		"			width:200px;\r\n"+
		"			padding-top:2em;\r\n"+
		"			text-align:center;\r\n"+
		"			float:left;\r\n"+
		"		}\r\n"+
		"		.image{\r\n"+
		"			width:150px;\r\n"+
		"			height:200px;\r\n"+
		"			border-color:#41aae0;\r\n"+
		"			border-width:2px;\r\n"+
		"			border-style:solid;\r\n"+
		"		}"+
		"		.rightPane{"+
		"			width:310px;"+
		"			padding-top:30px;"+
		"			text-align:left;"+
		"			float:left;"+
		"			line-height:1.7em;"+
		"		}"+
		"		.fieldName{"+
		"			width:110px;"+
		"			font-style:italic;"+
		"			font-weight:bold;"+
		"			font-family:맑은 고딕, 굴림;"+
		"			font-size:15px;"+
		"			text-align:right;"+
		"		}"+
		"		.fieldValue{"+
		"			width:190px;"+
		"			font-style:italic;"+
		"			font-family:맑은 고딕, 굴림;"+
		"			font-size:15px;"+
		"			text-align:left;"+
		"		}"+
		"		.graph{"+
		"			width:250px;"+
		"			height:200px;"+
		"			border-color:#41aa41;"+
		"			border-width:2px;"+
		"			border-style:solid;"+
		"		}"+
		"	</style>"+
		"</head>"+
		"<body>"+
		"	<div class=\"title\">:::: 문서 보기 ::::</div>";
	private String xslCommon2 =
		"	</div>"+
		"</body>"+
		"</html>"+
		"</xsl:template>"+
		"</xsl:stylesheet>";
	private String xslPlayer =
		"	<div class=\"leftPane\">"+
		"		<img class=\"image\">"+
		"			<xsl:attribute name=\"src\">"+
		"				<xsl:value-of select=\"body/players/player/picURL\"/>"+
		"			</xsl:attribute>"+
		"		</img>"+
		"	</div>"+
		"	<div class=\"rightPane\">"+
		"		<span class=\"fieldName\">선수 식별 번호 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/@id\"/></span><br/>"+
		"		<span class=\"fieldName\">이름 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/name\"/></span><br/>"+
		"		<span class=\"fieldName\">나이 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/age\"/></span><br/>"+
		"		<span class=\"fieldName\">성별 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/gender\"/></span><br/>"+
		"		<span class=\"fieldName\">신장(m) :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/height\"/></span><br/>"+
		"		<span class=\"fieldName\">몸무게(kg) :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/weight\"/></span><br/>"+
		"		<span class=\"fieldName\">경주 파트너 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/hId\"/></span><br/>"+
		"		<br/>"+
		"		<img src=\"pic/chart0.png\" class=\"graph\"></img>"+
		"		<br/>"+
		"		<br/>"+
		"		<img src=\"pic/chart1.png\" class=\"graph\"></img>";
	private String xslHorse =
		"	<div class=\"leftPane\">"+
		"		<img class=\"image\">"+
		"			<xsl:attribute name=\"src\">"+
		"				<xsl:value-of select=\"body/horses/horse/picURL\"/>"+
		"			</xsl:attribute>"+
		"		</img>"+
		"	</div>"+
		"	<div class=\"rightPane\">"+
		"		<span class=\"fieldName\">말 식별 번호 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/@id\"/></span><br/>"+
		"		<span class=\"fieldName\">이름 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/name\"/></span><br/>"+
		"		<span class=\"fieldName\">나이 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/age\"/></span><br/>"+
		"		<span class=\"fieldName\">성별 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/gender\"/></span><br/>"+
		"		<span class=\"fieldName\">신장(m) :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/height\"/></span><br/>"+
		"		<span class=\"fieldName\">몸무게(kg) :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/weight\"/></span><br/>"+
		"		<span class=\"fieldName\">별명 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/nick\"/></span><br/>"+
		"		<span class=\"fieldName\">말 종류 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/type\"/></span><br/>";
}