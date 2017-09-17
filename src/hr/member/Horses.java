//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.03.16 at 01:45:26 ���� KST 
//


package hr.member;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


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
 *         &lt;element ref="{}horse" maxOccurs="unbounded"/>
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
    "horse"
})
@XmlRootElement(name = "horses")
public class Horses{

	@XmlElement(required = true)
    protected List<Horse> horse;

	public Horses(){}
	public Horses(List<Horse> list){
		horse = list;
	}
    /**
     * Gets the value of the horse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the horse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHorse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Horse }
     * 
     * 
     */
    public List<Horse> getHorse() {
        if (horse == null) {
            horse = new ArrayList<Horse>();
        }
        return this.horse;
    }
    public Horse getHorse(int index){
    	try{
    		return this.horse.get(index);
    	}catch(IndexOutOfBoundsException e){
    		return null;
    	}
    }
    public void setHorse(List<Horse> value){
    	this.horse = value;
    }
    public void setHorse(Iterator<Horse> iter){
    	this.horse = (List)iter;
    }
    public void add(Horse horse){
    	this.horse.add(horse);
    }
}
