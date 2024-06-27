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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.common.Control;
import co.yedam.common.DataSource;
import co.yedam.mapper.ProductMapper;
import co.yedam.vo.ProductVO;

public class ImageDownload implements Control {
	String sql = "";

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/json;charset=utf-8");

		// [ {"src":"http","name":"헬리우스"},{},{}]
		Map<String, Object> resultMap = new HashMap<>();
		ProductVO[] array = null;
		int txnCnt = 0;

		ServletInputStream sis = req.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, String>> list = mapper.readValue(sis, //
				new TypeReference<List<Map<String, String>>>() {
				});

		array = new ProductVO[list.size()];

		for (Map<String, String> map : list) {

			String imgSrc = map.get("src"); // 이미지의 경로. 이미지 다운로드용.
			String prodName = map.get("name"); // 상품의 이름. 데이터베이스 입력쿼리.
			String prodDesc = map.get("desc"); // 상품의 간단설명. 데이터베이스 입력쿼리.
			String prodNo = map.get("id"); // 상품의 간단설명. 데이터베이스 입력쿼리.
//			String prodContent = map.get("content"); // 상품의 설명. 데이터베이스 입력쿼리.

			// String[] str = name.split("/");
			// String dir = str[0];
			// name = str[1];
			String dir = "dongwon";

			System.out.println("src: " + imgSrc + ", dir: " + dir + ", name: " + prodName);
			System.out.println("-----------------------------");
			dongwonCreate(imgSrc, dir, prodName);
//			insertQuery(prodNo, prodName, prodDesc);
			array[txnCnt++] = new ProductVO(prodNo, prodName, prodDesc);

//			fileCreate(src, dir, name);

		}
		executeQuery(array);
		resultMap.put("retCode", "OK");
		resultMap.put("txnCnt", txnCnt);
		resultMap.put("sql", "begin delete from prod_tbl; " + sql + " commit;  end;");

		Gson gson = new GsonBuilder().create();
		resp.getWriter().print(gson.toJson(resultMap));

		System.out.println("end of prog.");
	}

	// mapper 실행.
	public void executeQuery(ProductVO[] array) {
		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);

		int delCnt = mapper.deleteProdAll();
		System.out.println("delete cnt " + delCnt);

		int cnt = mapper.insertProduct(array);
		System.out.println("insert cnt " + cnt);
	}

	// 쿼리생성용.
	public void insertQuery(String prodNo, String prodName, String prodDesc) {
		// insert into prod_tbl (prod_no, prod_name, prod_desc, prod_content,
		// creation_date)
		// values(prod_seq.nextval, '팽이버섯 2입', '싱그러운 향의 아삭한 채소', 'html', sysdate);
		String[] nameAry = prodName.split("");
		String rName = "";
		for (int i = 0; i < nameAry.length; i++) {
			String str = nameAry[i];
			if (str.equals("'") || str.equals("&") || str.equals("/")) {
				continue;
			}
			rName += str;
		}
		String insertSQL = "insert into prod_tbl (prod_no, prod_name, prod_desc, creation_date, ord_no)";
		insertSQL += " values('" + prodNo + "', '" + rName + "', '" + prodDesc + "', sysdate, prod_seq.nextval); ";

		sql += insertSQL;
	}

	public void dongwonCreate(String imgSrc, String saveDir, String prodName) {
		URL url;
		SimpleDateFormat sdf = new SimpleDateFormat("HH_mm");

		try {
			url = new URL(imgSrc);

			InputStream is = null;
			OutputStream os = null;

			is = url.openStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			String filePath = "c:/temp/" + sdf.format(new Date()) + "/" + saveDir.trim();

			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			os = new BufferedOutputStream(new FileOutputStream(
					filePath + "/" + prodName.trim().replaceAll("/", "").replace("*", "") + ".jpg"));
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
