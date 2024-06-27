package co.yedam.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.yedam.common.Control;

public class ImageDownload implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// [ {"src":"http","name":"헬리우스"},{},{}]
		ServletInputStream sis = req.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, String>> list = mapper.readValue(sis, //
				new TypeReference<List<Map<String, String>>>() {
				});

		for (Map<String, String> map : list) {
			String src = map.get("src");
			String name = map.get("name");
			// String[] str = name.split("/");
			// String dir = str[0];
			// name = str[1];
			String dir = "dongwon";

			System.out.println("src: " + src + ", dir: " + dir + ", name: " + name);
			System.out.println("-----------------------------");
//			fileCreate(src, dir, name);
			dongwonCreate(src, dir, name);

		}
		System.out.println("end of prog.");

	}

	public void dongwonCreate(String src, String dir, String name) {
		URL url;
		SimpleDateFormat sdf = new SimpleDateFormat("HH_mm");

		try {
			url = new URL(src);

			InputStream is = null;
			OutputStream os = null;

			is = url.openStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			String filePath = "c:/temp/" + sdf.format(new Date()) + "/" + dir.trim();

			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			os = new BufferedOutputStream(new FileOutputStream(filePath + "/" + name.trim() + ".jpg"));
			while (true) {
				int data = bis.read();
				if (data == -1) {
					break;
				}
				os.write(data);
				os.flush();
			}
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fileCreate(String src, String dir, String name) {
		URL url;
		SimpleDateFormat sdf = new SimpleDateFormat("HH_mm");

		try {
			url = new URL(src);

			InputStream is = null;
			OutputStream os = null;

			is = url.openStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			String filePath = "c:/temp/" + sdf.format(new Date()) + "/" + dir.trim();

			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			os = new BufferedOutputStream(new FileOutputStream(filePath + "/" + name.trim() + ".jpg"));
			while (true) {
				int data = bis.read();
				if (data == -1) {
					break;
				}
				os.write(data);
				os.flush();
			}
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
