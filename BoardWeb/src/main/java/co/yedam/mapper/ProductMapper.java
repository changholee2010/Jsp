package co.yedam.mapper;

import co.yedam.vo.TemplateVO;

public interface ProductMapper {
	int deleteProdAll();
	int insertTemplate(TemplateVO[] array);
	int insertTemplate1(TemplateVO[] array);
	int selectCurrentCnt();
}
