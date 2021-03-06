//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.05.29 at 01:53:14 오후 KST 
//


package hr.member;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{}header"/>
 *         &lt;element ref="{}body"/>
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
    "header",
    "body"
})
@XmlRootElement(name = "horseRace")
public class HorseRace {

    @XmlElement(required = true)
    protected Header header;
    @XmlElement(required = true)
    protected Body body;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link Body }
     *     
     */
    public Body getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link Body }
     *     
     */
    public void setBody(Body value) {
        this.body = value;
    }
    
    public void setPlayer(List list){
    	this.getBody().getPlayers().setPlayer(list);
    }
    public List<Player> getPlayers(){
    	return body.getPlayers().getPlayer();
    }
    public Player getPlayer(int index){
    	return body.players.player.get(index);
    }
    
    public void setHorse(List list){
    	this.getBody().getHorses().setHorse(list);
    }
    public List<Horse> getHorses(){
    	return body.getHorses().getHorse();
    }
    public Horse getHorse(int index){
    	return body.horses.horse.get(index);
    }
    
    public void setResult(List list){
    	this.getBody().getResults().setResult(list);
    }
    public List<Result> getResults(){
    	return body.getResults().getResult();
    }
    public Result getResult(int index){
    	return body.results.result.get(index);
    }

    public HorseRace createChilds(){
		this.setHeader(new Header());
		this.setBody(new Body());
		this.getBody().setUsers(new Users());
		this.getBody().setPlayers(new Players());
		this.getBody().setHorses(new Horses());
		this.getBody().setResults(new Results());
		return this;
    }
    
    public HorseRace clone(){
    	try {
			return (HorseRace)super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
    }
    
    public List<? extends IMember> getMembers(ClassType type){
    	switch(type){
    	case USER:
    		return getBody().getUsers().getUser();
    	case PLAYER:
    		return getBody().getPlayers().getPlayer();
    	case HORSE:
    		return getBody().getHorses().getHorse();
    	case RESULT:
    		return getBody().getResults().getResult();
    	}
    	return null;
    }
    public void setMembers(List<? extends IMember> list){
    	try{
	    	Class<? extends IMember> cls = list.get(0).getClass(); 
	    	if(cls == User.class){
	    		getBody().setUsers(new Users((List<User>)list));
	    		return;
	    	}	
	    	else if(cls == Player.class){
	    		getBody().setPlayers(new Players((List<Player>)list));
	    		return;
	    	}
	    	else if(cls == Horse.class){
	    		getBody().setHorses(new Horses((List<Horse>)list));
	    		return;
	    	}
	    	else if(cls == Result.class){
	    		getBody().setResults(new Results((List<Result>)list));
	    		return;
	    	}
	    	else
	    		return;
    	}
    	catch(IndexOutOfBoundsException ex){}
    }
    public static IMember createInstance(ClassType type){
    	switch(type){
    	case USER:
    		return new User();
    	case PLAYER:
    		return new Player();
    	case HORSE:
    		return new Horse();
    	case RESULT:
    		return new Result();
    	case TODAY_HORSE:
    		return new TodayHorse();
    	}
    	return null;
    }
}
