package hr.manager;

import hr.member.ClassType;
import hr.member.Horse;
import hr.member.HorseRace;
import hr.member.IMember;
import hr.member.Player;
import hr.member.Result;
import hr.member.User;

import java.io.File;
import java.io.IOException;
import java.util.List;


public abstract class AbstractFileManager {
	public static final String DEFAULT_PATH = "C:/temp_folder";
	public static final String WEB_PATH = DEFAULT_PATH;//"http://localhost:8080/WebMeroz/";
	public static final String SUB_FOLDER_NAME = "pic";
	public static final int UPLOAD_LIMIT = 1024*1024*10;
	protected HorseRace hr = null;
	
	public void setHorseRace(HorseRace hr){
		this.hr = hr;
	}
	public HorseRace getHorseRace(){
		return hr;
	}
	public void setData(List<? extends IMember> list){
		hr.setMembers(list);
	}
	public List<Player> getPlayers(){
		return hr.getBody().getPlayers().getPlayer();
	}
	public List<Horse> getHorses(){
		return hr.getBody().getHorses().getHorse();
	}
	public List<Result> getResults(){
		return hr.getBody().getResults().getResult();
	}
	public List<? extends IMember> getMembers(ClassType type){
		return hr.getMembers(type);
	}
	protected void modPicURL(String subDir){
		List mList1 = this.hr.getPlayers();
		List mList2 = this.hr.getHorses();
		subDir = ( (subDir.indexOf("/") > 0) || subDir.contains("\\"))? subDir : subDir+"/" ;
		for(Object m: mList1){
			Player p = (Player)m;
			p.setPicURL(subDir + p.getId() + ".png");
		}
		for(Object m: mList2){
			Horse h = (Horse)m;
			h.setPicURL(subDir + h.getId() + ".png");
		}
	}

	protected HorseRace fixPicURL(File file, HorseRace hr){
		List<Player> list = hr.getPlayers();
		for(Player p : list){
			String url = p.getPicURL();
			if(url.startsWith(XMLManager.SUB_FOLDER_NAME))
				url = file.getParent() + "/" + url;
			if(url.contains("://") == false)
				p.setPicURL("file:///" + url);
		}
		List<Horse> list2 = hr.getHorses();
		for(Horse p : list2){
			String url = p.getPicURL();
			if(url.startsWith(XMLManager.SUB_FOLDER_NAME))
				url = file.getParent() + "/" + url;
			if(url.contains("://") == false)
				p.setPicURL("file:///" + url);
		}
		return hr;
	}


	public void checkDir(File file){
		this.makeDir(file);
		
		if(file.getPath().contains(".")){
			this.makeDir(file);
			//System.out.println(file.getParent() + "���� ����" + XMLPath);
		}else{
			//System.out.println("����");
			this.makeDir(file);
		}
	}
	/**
	 * ���丮�� x�� /���� Ȯ���Ѵ�. ���� ��ٸ�, ��' ���丮 x�� /���� Ȯ��,
	 * �׷��� ��ٸ� �װ�; �����, �ִٸ� �ڽ� ���丮�� �����.
	 * @param filePath :
	 * {@link String} - ����ϰ� ��: ���丮�� ���
	 */
	protected void makeDir(File file){
		if(!file.exists()){
			if(file.getPath().contains(".")){
				File pf = file.getParentFile();
				try{
					if(!pf.exists()){
						pf.mkdirs();
					}
				}
				catch(NullPointerException ex){
					try {
						pf = file.getCanonicalFile();
						pf.mkdirs();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else{
				file.mkdirs();
			}
		}
	}
	
	public HorseRace read(String filePath){
		try{
			return this.read(new File(filePath));
		}catch(Exception e){
			return null;
		}
	}
	public boolean write(String filePath){
		return this.write(new File(filePath));
	}
	public abstract HorseRace read(File file);
	public abstract boolean write(File file);
}
