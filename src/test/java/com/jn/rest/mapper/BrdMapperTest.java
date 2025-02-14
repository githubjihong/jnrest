package com.jn.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jn.rest.vo.BrdVo;

// table -> vo -> mapper interface -> sql 작성 -> 테스트

@SpringBootTest //자바에서 유명한 테스트 프레임워크는 jUnit5
public class BrdMapperTest {
	
	@Autowired
	private BrdMapper brdMapper;
	
	@Test
	@DisplayName("Brd List 가져오깅")
	@Disabled
	void getListTest() {
		assertEquals(14,brdMapper.getList().size());
	}
	
	@Test
	@DisplayName("Brd getOne 가져오깅")
	void getOneTest() {
		// 요기 빨리 작성해 보아용, 메소드 return 타입에 주목
		// 매개변수와 return 타입이 머릿속에 잘 정리 안된 사람들을 후다닥 다시 정리
		int bdId =25;
		assertEquals(25,brdMapper.getBrd(bdId).getBdId());
	}
	
	@Test
	@DisplayName("Brd 삭제 테스트")
	@Disabled
	void deleteBrdsTest() {
		int bdId = 31;
		assertEquals(1, brdMapper.deleteBrd(bdId)); 
	}
	
	@Test
	@DisplayName("Brd 수정 테스트")
	@Disabled
	void updBrdsTest() {
		
		BrdVo brdVo = new BrdVo();
		brdVo.setBdId(30);
		brdVo.setBdTitle("수정 타이틀11");
		brdVo.setBdCont("수정 내용11");
		brdVo.setBdWriter("지홍홍11");
		
		assertEquals(1, brdMapper.updateBrd(brdVo)); 
	}
	
	
	@Test
	@DisplayName("Brd 추가 테스트")
	@Disabled	//테스트 끝나서 이제 안할 꺼얌
	void insertBrdTest() {
		
		BrdVo brdVo = new BrdVo();
		brdVo.setBdCont("내용");
		brdVo.setBdTitle("제목");
		brdVo.setBdWriter("쭈니");
		
		int cnt = brdMapper.insertBrd(brdVo);
		
		// 진짜 테스트
		//assert: ~이여야 한다.
		assertEquals(1, cnt);
	}
	
	@Test
	@DisplayName("Brd 여러개 입력 추가 테스트")
	@Disabled	//테스트 끝나서 이제 안할 꺼얌
	void insertsBrdTest() {
		
		BrdVo brdVo = null;
		int sum = 15;
		int realSum = 0;
		for(int i=1;i<=sum;i++) {
			brdVo = new BrdVo();
			brdVo.setBdCont("쭈니내용" + i);
			brdVo.setBdTitle("쭈미 제목" + i);
			brdVo.setBdWriter("쭈닝");
			
			realSum += brdMapper.insertBrd(brdVo);
			
		}
		
		int cnt = brdMapper.insertBrd(brdVo);
		
		// 진짜 테스트
		//assert: ~이여야 한다.
		assertEquals(sum, realSum);
	}
}
