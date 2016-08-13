package com.mystore.business.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.ConfigReader;
import com.mystore.business.common.Constans;

@Controller("uploadAction")
@Scope("prototype")
public class UploadAction extends BaseAction{

		/**
		 * 
		 */
	    private static final long serialVersionUID = 1L;
	    
	    private Integer id_pro;
	    private File upload;  
	    private String uploadContentType;  
	    private String uploadFileName;  
	  
	    
	    public Integer getId_pro() {
			return id_pro;
		}

		public void setId_pro(Integer id_pro) {
			this.id_pro = id_pro;
		}

		public File getUpload() {  
	        return upload;  
	    }  
	  
	    public void setUpload(File upload) {  
	          
	        this.upload = upload;  
	    }  
	  
	    public String getUploadContentType() {  
	        return uploadContentType;  
	    }  
	  
	    public void setUploadContentType(String uploadContentType) {  
	        this.uploadContentType = uploadContentType;  
	    }  
	  
	    public String getUploadFileName() {  
	        return uploadFileName;  
	    }  
	  
	    public void setUploadFileName(String uploadFileName) {  
	        this.uploadFileName = uploadFileName;   
	    }  
	  
	    public String upload() throws Exception {  
	        HttpServletResponse response = ServletActionContext.getResponse();  
	        response.setCharacterEncoding("GBK");  
	        PrintWriter out = response.getWriter();  
	  
	  
	        //对文件进行校验  
	        if(upload==null || uploadContentType==null || uploadFileName==null){  
	            out.print("<font color=\"red\" size=\"2\">*请选择上传文件</font>");  
	            return null;  
	        }  
	          
	        if ((uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg"))  
	                && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".jpg")) {  
	            //IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg  
	        }else if(uploadContentType.equals("image/png") && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".png")){  
	              
	        }else if(uploadContentType.equals("image/gif") && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".gif")){  
	              
	        }else if(uploadContentType.equals("image/bmp") && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".bmp")){  
	              
	        }else{  
	            out.print("<font color=\"red\" size=\"2\">*文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）</font>");  
	            return null;  
	        }  
	          
	        if(upload.length() > Constans.SIZE_MAX_IMG_UPLOAD){  
	            out.print("<font color=\"red\" size=\"2\">*文件大小不得大于2M</font>");  
	            return null;  
	        }  
	          
	        //将文件保存到项目目录下  
	        InputStream is = new FileInputStream(upload);  
	        String fileName = new SimpleDateFormat(Constans.DATE_FORMATE).format(new Date()); 
	        fileName += uploadFileName.substring(uploadFileName.length() - 4); 
	        File toFile = new File(ConfigReader.getPath_pic_upload()+"/"+Constans.PATH_UPLOAD_INFO_PRO+id_pro, fileName);  
	        if(!toFile.getParentFile().exists()) {
	        	toFile.getParentFile().mkdirs();
 	    	}
	        OutputStream os = new FileOutputStream(toFile);     
	        byte[] buffer = new byte[1024];     
	        int length = 0;  
	        while ((length = is.read(buffer)) > 0) {     
	            os.write(buffer, 0, length);     
	        }     
	        is.close();  
	        os.close();  
	          
	          
	        //设置返回“图像”选项卡  
	        String callback = ServletActionContext.getRequest().getParameter("CKEditorFuncNum");    
	        out.println("<script type=\"text/javascript\">");    
	        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + ConfigReader.getPath_pic_service()+"/"+Constans.PATH_UPLOAD_INFO_PRO+id_pro +"/"+ fileName + "','')");    
	        out.println("</script>");  
	  
	          
	        return null;  
	    }  

}
