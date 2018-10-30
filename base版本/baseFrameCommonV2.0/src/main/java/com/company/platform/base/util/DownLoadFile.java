package com.company.platform.base.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class DownLoadFile {
	
	private static final int BUFFER_SIZE = 1024 *1024;
	// 在线打开方式 下载
    public static void downLoad(String filePath, HttpServletResponse response,String fileNewName) throws Exception {
        File f = new File(filePath);
        OutputStream out = response.getOutputStream();
        if (!f.exists()) {
            response.setCharacterEncoding("UTF-8");
            String notFileHtml="文件找不到";
            out.write(notFileHtml.getBytes("UTF-8"));
            out.flush();
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileNewName);

        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }
    
	public static void readFile(String filePath, HttpServletResponse response,String fileNewName) throws IOException{
    	
    		File file = new File(filePath);
    			if(file!=null&&file.exists()){
    		        BufferedInputStream in = null;
    		        OutputStream ot = null;
    	 	        try {
    		        	in = new BufferedInputStream(new FileInputStream(file)) ;
//    		        	response.setContentType("application/octet-stream");
    		        	response.setContentType("application/x-download");
    		        	//String filename=URLEncoder.encode(file.getName(),"utf-8"); 
    		        	//filename=filename.replace("arjr", "arjr_"+System.currentTimeMillis());
    		        	response.setHeader("Content-Disposition","attachment;filename=\""+fileNewName+"\""); 
    		        	response.addHeader("Content-Length", "" + file.length());
    		            ot = response.getOutputStream();
    	 	            byte[] buffer = new byte[BUFFER_SIZE];
    		            int len = 0;
    		            while ((len = in.read(buffer)) != -1) {
    		            	ot.write(buffer, 0, len);
    		            }
    		        } catch (Exception e) {
    		            e.printStackTrace();
    		        } finally {
    		            if (in != null) {
    		                in.close();
    		                in = null;
    		                file = null;
    		            }
    		             
    		            if (ot != null)  {
    		            	ot.close();
    		            }
    		        }

    			}
		}
	
}
