package hr.manager;
import hr.member.ClassType;
import hr.member.HorseRace;
import hr.member.IMember;
import hr.member.Member;
import hr.member.ObjectFactory;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ExcelManager extends AbstractFileManager{
	
	public static final double THUMBNAIL_POSITION = 12.0;
	public static final double THUMBNAIL_WIDTH = 1.0;
	public static final double THUMBNAIL_HEIGHT = 3.0;
	//private File XMLFile;

	public ExcelManager(){
		this.hr = new HorseRace();
		this.hr.createChilds();
	}

	public ExcelManager(HorseRace hr){
		this.hr = hr.clone();
	}

	public HorseRace read(File file){
		
		try {
			Workbook book = Workbook.getWorkbook(file);
			for(Sheet sheet : book.getSheets()){
				if(isMemberData(sheet, ClassType.PLAYER)){
					setMembers(sheet, ClassType.PLAYER);
				}
				else if(isMemberData(sheet, ClassType.HORSE)){
					setMembers(sheet, ClassType.HORSE);
				}
				else if(isMemberData(sheet, ClassType.RESULT)){
					setMembers(sheet, ClassType.RESULT);
				}
				else{
					return null;
				}
			}
			return hr;
		} catch (BiffException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected boolean isMemberData(Sheet sheet, ClassType type){
		String[] comp = HorseRace.createInstance(type).getColumnName();
		int colPass = 0;
		for(String str : comp){
			if(sheet.findCell(str) != null){
				colPass++;
				////System.out.println(sheet.findCell(str) != null);
			}
		}
		if(colPass == comp.length){
			return true;
		}
		else{
			return false;
		}
	}
	protected void setMembers(Sheet sheet, ClassType type){
		int maxcol = HorseRace.createInstance(type).getColumnName().length;
		List<IMember> list = new ArrayList<IMember>();
		
		for(int r = 1; r < sheet.getRows(); r++){
			IMember imbr = HorseRace.createInstance(type);
			List<String> data = new ArrayList<String>();
			for(int c = 0; c < maxcol; c++){
				data.add(sheet.getCell(c, r).getContents());
				////System.out.println(sheet.getCell(c, r).getContents());
			}
			
			imbr.setData(data.toArray(new String[1]));
			list.add(imbr);
		}
		list = picSave(sheet, type, list);
		this.setData(list);
		////System.out.println(hr.getPlayer(0).getName() + "setMembers����");
	}
	protected List<IMember> picSave(Sheet sheet, ClassType type, List<IMember> list){
		if(type != ClassType.RESULT){
			ImageSaver is = new ImageSaver(AbstractFileManager.DEFAULT_PATH);
			for(int i = 0; i < list.size(); i++){
				String url; 
				url = is.pngSaver(sheet.getDrawing(i).getImageData(), sheet.getCell(0, i+1).getContents());
				((Member)list.get(i)).setPicURL(url);
			}
		}
		return list;
	}
	public boolean write(File file){
		this.checkDir(file);
		
		try {
			WritableWorkbook book = Workbook.createWorkbook(file);

			fillSheet(book.createSheet("경마 선수", 0), ClassType.PLAYER);
			fillSheet(book.createSheet("경주마", 1), ClassType.HORSE);
			fillSheet(book.createSheet("경주 결과", 2), ClassType.RESULT);
			
			book.write();
			book.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return false;
		//return this.writeCommon(this.hr, file);
	}
	
	protected WritableSheet fillSheet(WritableSheet sheet, ClassType type){
		try{
			String[] colNames = HorseRace.createInstance(type).getColumnName();
			int row = 1, col = 0;
			ImageSaver is = new ImageSaver();
			WritableCell cell = null;;
			WritableImage image = null;
			
			for(int i = 0; i < colNames.length; i++){
				cell = new Label(i, 0, colNames[i]);
				sheet.addCell(cell);
			}
			
			Iterator<? extends IMember> iter = hr.getMembers(type).iterator();
			
			while(iter.hasNext()){
				IMember imbr = iter.next();
				col = 0;
				for(Object obj : imbr.toArray()){
					sheet.addCell(createCell(col, row, obj));
					col++;
				}
				if(type != ClassType.RESULT){
					Member mbr = (Member)imbr;
					File imgFile = null;
					String url = mbr.getPicURL();
					url = (url.contains("://") == false)? "file:///" + url : url;
					try {
						imgFile = new File(is.pngSaver(new URL(url), mbr.getId(), false));
					} catch (MalformedURLException e) {
						//imgFile = new File(is.pngSaver(new URL(mbr.getPicURL()), mbr.getId(), false));
						e.printStackTrace();
					}
					image = new WritableImage(THUMBNAIL_POSITION, (row-1) * THUMBNAIL_HEIGHT, THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, imgFile);
					sheet.addImage(image);
				}
				row++;
			}
			return sheet;
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected WritableCell createCell(int col, int row, Object obj){
		WritableCell cell = null;
		Class cls = obj.getClass();
		if(cls == Integer.class || cls == Double.class){
			cell = new jxl.write.Number(col, row, Double.parseDouble(obj.toString()));
		}
		else if(cls == Boolean.class){
			if(obj.toString() == "true"){
				cell = new Label(col, row, "남");
			}
			else{
				cell = new Label(col, row, "여");
			}
		}
		else{
			cell = new Label(col, row, obj.toString());
		}
		return cell;
	}
}
