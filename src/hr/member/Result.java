//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.03.15 at 07:55:23 오후 KST 
//


package hr.member;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="betRate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="rank" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "date",
    "betRate",
    "winRate"
})
@XmlRootElement(name = "result")
public class Result implements IMember, Serializable{
	
    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    protected double betRate;
    protected double winRate;

    public Result(){}
    public Result(String id, String date, double betRate, double winRate){
    	try{
	    	this.id = id;
	    	this.date = DatatypeFactory.newInstance().newXMLGregorianCalendar(date);
	    	this.betRate = betRate;
	    	this.winRate = winRate;
    	}catch(DatatypeConfigurationException e){}
    }
    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the betRate property.
     * 
     */
    public double getBetRate() {
        return betRate;
    }

    /**
     * Sets the value of the betRate property.
     * 
     */
    public void setBetRate(double value) {
        this.betRate = value;
    }

    /**
     * Gets the value of the rank property.
     * 
     */
    public double getWinRate() {
        return winRate;
    }

    /**
     * Sets the value of the rank property.
     * 
     */
    public void setWinRate(double value) {
        this.winRate = value;
    }

    public String[] toStrings(){
    	String[] str = new String[4];
    	str[0] = id;
    	str[1] = date.toString();
    	str[2] = betRate + "";
    	str[3] = winRate + "";
    	return str;
    }
    public Object[] toArray(){
    	Object[] obj = new Object[4];
    	obj[0] = id;
    	obj[1] = date.toString();
    	obj[2] = new Double(betRate);
    	obj[3] = new Double(winRate);
    	return obj;
    }
    public String[] getColumnName(){
    		String[] str = {"선수 식별 번호", "날짜", "베팅률(%)", "승률(%)"};
    		return str;
    }
	@Override
	public void setData(String... str) {
		try {
			this.id = str[0];
			this.date = DatatypeFactory.newInstance().newXMLGregorianCalendar(str[1].toString().split(" ")[0]);
			this.betRate = Double.parseDouble(str[2]);
			this.winRate = Double.parseDouble(str[3]);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public IMember clone(){
		try {
			return (IMember)super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	public String[] getFieldName(){
    	String[] str = {"id", "fdate", "betRate", "winRate"};
    	return str;
    }
	@Override
	public ClassType getClassType() {
		return ClassType.RESULT;
	}
}
