package co.yedam.mapper;

import co.yedam.vo.ProductVO;

public interface ProductMapper {
	int deleteProdAll();
	int insertProduct(ProductVO[] array);
}
