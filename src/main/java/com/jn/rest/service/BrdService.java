package com.jn.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jn.rest.mapper.BrdMapper;
import com.jn.rest.vo.BrdVo;

// 서비스는 보통 비즈니스 로직 구현 레이어
// I/F, xxxImpl 구분해서 만들었는데, 쓸데 없이 I/F가 많이 만들어지는 경우가 많아서
// 바로 구현체 class로 만드는 경우가 점점 많아짐!
// 서비스에서 Mapper(DAO)를 부른다

@Service
public class BrdService {
	
	@Autowired
	private BrdMapper brdMapper;
	
	public List<BrdVo> getList(){
		return brdMapper.getList();
	}
	
	public BrdVo getBrd(int bdId) {
		return brdMapper.getBrd(bdId);
	}
	
	public int insertBrd(BrdVo brdVo) {
		return brdMapper.insertBrd(brdVo);
	}
	
	public int updateBrd(BrdVo brdVo) {
		return brdMapper.updateBrd(brdVo);
	}
	public int deleteBrd(int bdId) {
		return brdMapper.deleteBrd(bdId);
	}
}
