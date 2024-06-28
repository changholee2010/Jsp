package co.yedam.vo;

import java.util.Date;

import lombok.Data;

@Data
public class TemplateVO {
	private int prodNo;
	private String prodCode;
	private String prodName;
	private String prodDesc;
	private String prodContent;
	private String prodImg1;
	private String prodImg2;
	private String prodImg3;
	private String attribute1;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;
	private Date creationDate;
	private String trialNo; // 실행그룹으로 찾을 칼럼.
}
