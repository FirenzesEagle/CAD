package com.siims.szb.goods.picture.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;


import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;

public class UploadImageAction extends StrutsAction{

	/**
	 * liu
	 */
	private static final long serialVersionUID = -1835243216072479685L;
	/************************ 上传相关属性 *********************************/
    // 封装上传文件域的属性
    private File uploadImg;
    // 封装上传文件类型的属性
    private String uploadImgContentType;
    // 封装上传文件名的属性
    private String uploadImgFileName;
    //图片对象
   // private PictureData pictureObjectData = new PictureData();
    
	/************************ 截图相关属性 ********************************/
    // 封装截图距原图顶部属性
    private Integer top;
    // 封装截图距原图左侧属性
    private Integer left;
    // 封装截图长
    private Integer width;
    // 封装截图宽
    private Integer height;
    // 是否截图
    private boolean cut;
    
    String fanxiegang = "/";
    
    @Action(value = "uploadImage")
    public void uploadImage()
	{
    	//response.setHeader("Access-Control-Allow-Origin", "*");
		
		
		System.out.println("Pic");
		FileOutputStream fos = null;
        FileInputStream fis = null;
        FileOutputStream fos1 = null;
        FileInputStream fis1 = null;
        Calendar cal=Calendar.getInstance();//使用日历类
        int year=cal.get(Calendar.YEAR);//得到年
        int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
        int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
        String filePath = ServletActionContext.getServletContext().getRealPath("/component_uploadImg") + fanxiegang + year + fanxiegang + month + fanxiegang + day + fanxiegang ;
        boolean isdeleteImg = false;
        String deleteFileName = "";
        String newFileName = "";
        String FileName = "";
        try {
            String filename = getUploadImgFileName();
            String fileType = filename.substring(filename.lastIndexOf("."), filename.length());// 获取文件后缀名
            if(fileType.toLowerCase().equals(".jpeg")){
                fileType = ".jpg";
            }
            newFileName = System.currentTimeMillis() + fileType;
            deleteFileName = filePath + newFileName;
            
            File file = new File(filePath);
            //如果文件夹不存在则创建    
            if (!file.exists()  && !file .isDirectory()){
                file.mkdirs();
            } 
            
            fos = new FileOutputStream(filePath + fanxiegang + newFileName);
            // 建立文件上传流
            fis = new FileInputStream(getUploadImg());
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            //判断是否进行截图操作
//            if(cut){
//                CutImgUtil.cut(filePath+newFileName, top < 0 ? 0 : top, left < 0 ? 0 : left, width, height);
//            }
        } catch (Exception e) {
//        	try {
//        		String s = "{\"SUCCESS\":\"false\",\"ERRMSG\":\"1\"}";
//                //response.getWriter().write(s);
//                return;
//            } catch (IOException e1) 
//        	{
//                e1.printStackTrace();
//            }
            e.printStackTrace();
        } finally {
            close(fos, fis);
            close(fos1, fis1);
        }


        //localhost:8580
        
        String path = "http://182.92.4.200:8838/component_uploadImg"+fanxiegang + year + 
        		fanxiegang + month + fanxiegang + day + fanxiegang+fanxiegang+newFileName;
        System.out.println(path);
        String msg = "{"+"\""+"pathURL"+"\""+":"+"\""+path+"\""+"}";
        System.out.println(msg);
        //String responseResult = "{\"SUCCESS\":\"true\",\"DATA\":"+msg+",\"ERROMSG\":\"修改成功\"}";
       String rs = "{\"SUCCESS\":\"true\",\"DATA\":" + msg + ",\"ERROMSG\":\"\"}";
       System.out.println("111");
       response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(rs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(rs);

      //  ActionUtil.sendJson(rs);

    }
    
    private void close(FileOutputStream fos, FileInputStream fis) {
        if (fis != null) {
            try { 
                fis.close();
            } catch (IOException e) {
                System.out.println("FileInputStream关闭失败");
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
                System.out.println("FileOutputStream关闭失败");
                e.printStackTrace();
            }
        }
    }
    
    public File getUploadImg() {
		return uploadImg;
	}

	public void setUploadImg(File uploadImg) {
		this.uploadImg = uploadImg;
	}

	public String getUploadImgContentType() {
		return uploadImgContentType;
	}

	public void setUploadImgContentType(String uploadImgContentType) {
		this.uploadImgContentType = uploadImgContentType;
	}

	public String getUploadImgFileName() {
		return uploadImgFileName;
	}

	public void setUploadImgFileName(String uploadImgFileName) {
		this.uploadImgFileName = uploadImgFileName;
	}

//	public PictureObjectData getPictureObjectData() {
//		return pictureObjectData;
//	}
//
//	public void setPictureObjectData(PictureObjectData pictureObjectData) {
//		this.pictureObjectData = pictureObjectData;
//	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getLeft() {
		return left;
	}

	public void setLeft(Integer left) {
		this.left = left;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public boolean isCut() {
		return cut;
	}

	public void setCut(boolean cut) {
		this.cut = cut;
	}
	
}
