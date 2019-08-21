package com.jk.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


public class FileUtil {

	public static String upload(MultipartFile file, HttpServletRequest request) {

		if (file != null && file.getSize() > 0) {
			// 上传文件的名字
			String name = file.getOriginalFilename();
			// 获取后缀名
			String suffix = name.substring(name.lastIndexOf("."));
			// 生成唯一标示
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			// 文件名 唯一
			String prefix = format.format(date);

			String realPath = request.getServletContext().getRealPath("upload");

			File fileTemp = new File(realPath, prefix + suffix);
			// model.addAttribute("name", prefix + suffix);
			if (!fileTemp.exists()) {
				fileTemp.mkdirs();
			}

			try {
				file.transferTo(fileTemp);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return prefix + suffix;
		} else {
			return "not found";
		}

	}

	/**
	 * <pre>
	 * download(这里用一句话描述这个方法的作用)   
	 * 创建人：wzstart     
	 * 创建时间：2018-10-13 上午9:56:48    
	 * 修改人：wzstart      
	 * 修改时间：2018-10-13 上午9:56:48    
	 * 修改备注：
	 * </pre>
	 */
	public static ResponseEntity<byte[]> download(HttpServletRequest request, String filename) {
		// 获取文件的在服务器地址
		String realPath = request.getServletContext().getRealPath("upload");
		// 要下载的文件对象
		File downloadFile = new File(realPath, filename);

		// 设置http的响应头
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", filename);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		// 设置下载的内容
		ResponseEntity<byte[]> entity = null;
		try {
			entity = new ResponseEntity<byte[]>(
					FileUtils.readFileToByteArray(downloadFile), headers,
					HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	
public static String uploadFile(MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException{
	
	//获取服务器的存储路径
	String path = request.getServletContext().getRealPath("")+"//upload";
	
	//判断不存在 创建
	File savePath = new File(path);
	
	if(!savePath.exists()){
		
		savePath.mkdirs();
	}
	
	//重命名  1. 时间戳  毫秒  2.UUID  16  32  不重复字符串  1 10亿  100 50% 
	
	//获取后缀名     abc.jpg
	int index = file.getOriginalFilename().lastIndexOf(".");
	
	String suffix = file.getOriginalFilename().substring(index);
	
	//System.out.println(suffix);
	//重命名
	//时间戳
	String newFileName = System.currentTimeMillis()+suffix;
	
	String newFileName2 = UUID.randomUUID()+suffix;
	
	//转存
	file.transferTo(new File(path+"//"+newFileName));
	
	return newFileName;
}


}
