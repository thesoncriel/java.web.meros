package hr.manager;



import hr.manager.*;
import hr.member.Horse;
import hr.member.HorseRace;
import hr.member.IMember;
import hr.member.Member;
import hr.member.Player;
import hr.member.Result;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;



public class TextManager extends AbstractFileManager{
	public static final String DEFAULT_SEPRATOR = ",";
	private String seprator;

	public TextManager(){
		this.hr = new HorseRace();
		this.hr.createChilds();
		this.seprator = DEFAULT_SEPRATOR;
	}

	public TextManager(HorseRace hr){
		this.hr = hr.clone();
	}
	public TextManager(IMember imbr){
		this();
		Class cls = imbr.getClass();
		if(cls == Player.class){
			hr.getBody().getPlayers().getPlayer().add((Player)imbr.clone());
		}
		else if(cls == Horse.class){
			hr.getBody().getHorses().getHorse().add((Horse)imbr.clone());
		}
		else if(cls == Result.class){
			hr.getBody().getResults().getResult().add((Result)imbr.clone());
		}
		else{
			System.out.println("���� imbr�� ����� �߸� �Ǿ�4ϴ� �Ф�");
		}
	}

	public HorseRace read(File file){
		try{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			List<String> data = new ArrayList<String>();
			
			String tmp = "";
			while((tmp = br.readLine()) != null){
				data.add(tmp);
			}
			br.close();
			
			for(String str : data){
				Scanner scn = new Scanner(str);
				scn.useDelimiter(seprator);
				List<String> tList = new ArrayList<String>();
				while(scn.hasNext()){
					tList.add(scn.next());
				}
				System.out.println(tList.size());
				switch(tList.size()){
				case 8:
					Player p = new Player();
					p.setData(tList.toArray(new String[0]));
					hr.getPlayers().add(p);
					break;
				case 9:
					Horse h = new Horse();
					h.setData(tList.toArray(new String[0]));
					hr.getHorses().add(h);
					break;
				case 4:
					Result r = new Result();
					r.setData(tList.toArray(new String[0]));
					hr.getResults().add(r);
					break;
				}
			}
			return this.fixPicURL(file, hr);
		}catch(FileNotFoundException e){
			System.out.println(file.getName() + " ����; ã; �� ��ٳ׿� ��.�� Ȯ��a..");
		}catch(IOException e){
			System.out.println(e);
		}catch(Exception e){
			System.out.println(e);
		}
		return hr;
	}

	public boolean write(File file){
		this.checkDir(file);
		ImageSaver is = new ImageSaver(this.hr, file.getParent());
		
		is.write();
		this.modPicURL(this.SUB_FOLDER_NAME);
		return this.writeCommon(this.hr, file);
	}
	
	protected boolean writeCommon(HorseRace hr, File file){
		try{
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			StringBuffer sb = null;
			
			for(Object obj : hr.getPlayers()){
				IMember mbr = (IMember)obj;
				sb = new StringBuffer();
				for(String tmp : mbr.toStrings()){
					sb.append(tmp + DEFAULT_SEPRATOR);
				}
				sb.deleteCharAt(sb.length()-1);
				bw.write(sb.toString());
				bw.newLine();
			}
			for(Object obj : hr.getHorses()){
				IMember mbr = (IMember)obj;
				sb = new StringBuffer();
				for(String tmp : mbr.toStrings()){
					sb.append(tmp + DEFAULT_SEPRATOR);
				}
				sb.deleteCharAt(sb.length()-1);
				bw.write(sb.toString());
				bw.newLine();
			}
			for(Object obj : hr.getResults()){
				IMember mbr = (IMember)obj;
				sb = new StringBuffer();
				for(String tmp : mbr.toStrings()){
					sb.append(tmp + DEFAULT_SEPRATOR);
				}
				sb.deleteCharAt(sb.length()-1);
				bw.write(sb.toString());
				bw.newLine();
			}
			bw.close();
			return true;
		}
		catch(FileNotFoundException e){}
		catch(IOException e){}
		return false;
	}
}