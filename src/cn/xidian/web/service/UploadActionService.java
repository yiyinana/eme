package cn.xidian.web.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

public class UploadActionService {

	public void upload(File file,String uploadUrl,String filename) throws Exception {
		InputStream in = new FileInputStream(file);
		String dir = ServletActionContext.getServletContext().getRealPath(
				uploadUrl);
		File uploadFile = new File(dir, filename); // 上传的文件，文件名改这里
		OutputStream out = new FileOutputStream(uploadFile);
		byte[] buffer = new byte[1024 * 1024];
		int length;
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}
		if (in != null)
			in.close();
		if (out != null)
			out.close();
	}
}
