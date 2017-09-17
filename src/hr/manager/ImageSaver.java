package hr.manager;

import hr.member.Horse;
import hr.member.HorseRace;
import hr.member.Player;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * �̹��� ũ�⸦ ��d�ϰ� �����ϴ� ���; ����.
 * @author TheSON -_-v
 *
 */
public class ImageSaver {
	public static final int MOD_IMAGE_WIDTH = 150;
	public static final int MOD_IMAGE_HEIGHT = 200;
	public static final String MOD_IMAGE_TYPE = "png";
	public static final String SUB_FOLDER = "/pic/";
	private HorseRace hr = null;
	private String dirPath = null;
	
	public ImageSaver(){
		try {
			File dir = new File("__theson").getCanonicalFile().getParentFile();
			this.dirPath = dir.getAbsolutePath();
			//System.out.println(dirPath);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("����-_-");
		}
	}
	public ImageSaver(String dirPath){
		this.dirPath = dirPath;
	}
	public ImageSaver(HorseRace hr, String dirPath){
		this.hr = hr;
		this.dirPath = dirPath;
	}
	/**
	 * ��ü�� ��d�� picURL; ��� �̹��� �����ϰ� ��dũ��� �ٲ۴�= ��˵� �����Ѵ�.(�⺻ png ���)<br>
	 * ����Ǵ� �̹����� ��δ� �Ϲ���8�� dirPath�� ��' ����� pic�̴�.
	 */
	public void write(){
		
		File file;
		FileOutputStream fos;
		
		List<Player> mList1 = hr.getPlayers();
		for(Player p: mList1){
			try{
				//System.out.println(dirPath + p.getPicURL() + "---");
				String picPath = p.getPicURL();
				URL url = null;
				if(picPath.contains("://") == false)
					url = new URL("file:///" + picPath);
				else
					url = new URL(picPath);
				pngSaver(url, p.getId());
			}
			catch (IOException e){
				e.printStackTrace();
			}
			
		}
		
		List<Horse> mList2 = hr.getHorses();
		for(Horse h: mList2){
			try{
				pngSaver(new URL(h.getPicURL()), h.getId());
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * �ҷ��� �̹��� ������ ũ�⸦ �����ϰ� png ���8�� �ٲ۴�.<br>
	 * �̹����� ũ��� MOD_IMAGE_WIDTH ���� MOD_IMAGE_HEIGHT ����� ��8�� ��d �ȴ�.
	 * @param picURL :
	 * {@link URL} - �ҷ��0� ��: �̹����� ���
	 * @param id :
	 * {@link String} - ����Ű���� �ϴ� �̹����� ��/ �̸�
	 */
	
	public String pngSaver(URL picURL, String id){
		return pngSaver(picURL, id, true);
	}
	public String pngSaver(URL picURL, String id, boolean resize){
		BufferedImage imgSource;
		try {
			imgSource = ImageIO.read(picURL);
			return pngSaver(imgSource, id, resize);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public String pngSaver(BufferedImage imgSource, String id){
		return pngSaver(imgSource, id, true);
	}
	public String pngSaver(BufferedImage imgSource, String id, boolean resize){
		try{
			BufferedImage bi = null;
			
			String type = this.MOD_IMAGE_TYPE;
			File subDir = new File(dirPath + SUB_FOLDER);
			System.out.println(dirPath);
			if(subDir.exists() == false){
				subDir.mkdirs();
			}
			
			if(resize){
				int newWidth = this.MOD_IMAGE_WIDTH;
				int newHeight = this.MOD_IMAGE_HEIGHT;
				bi = getResizedImage(imgSource, newWidth, newHeight);
			}
			else{
				bi = imgSource; 
			}
			
			String fileName = dirPath + SUB_FOLDER + id + "." + type;
			FileOutputStream fos = new FileOutputStream(fileName);
			
			ImageIO.write(bi, type, fos);
			//System.out.println("����" + XMLPath + "/pic/" + id + "." + type);
			fos.close();
			return fileName;
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e){e.printStackTrace();}
		return null;
	}
	public String pngSaver(byte[] pngData, String id){
		try {
			String fileName = dirPath + SUB_FOLDER + id + ".png";
			FileOutputStream fos = new FileOutputStream(fileName);
			fos.write(pngData);
			return fileName;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @param imgSource
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static BufferedImage getResizedImage(Image imgSource, int newWidth, int newHeight){
		try {
			Image imgTarget = imgSource.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
			
			int pixels[] = new int[newWidth * newHeight];
	
			PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, newWidth, newHeight, pixels, 0, newWidth);
			
			pg.grabPixels();
			BufferedImage bi = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
			bi.setRGB(0, 0, newWidth, newHeight, pixels, 0, newWidth);
			return bi;
		}
		catch (InterruptedException e) {
			return null;
		}
	}
}
