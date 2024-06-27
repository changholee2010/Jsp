package co.yedam.vo;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductVO {
	@NonNull
	private String prodNo;
	@NonNull
	private String prodName;
	@NonNull
	private String prodDesc;

	private String prodContent;
	private Date creationDate;
	private String ordNo;
}
