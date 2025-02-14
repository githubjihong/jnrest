package com.jn.rest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jn.rest.vo.BrdVo;

@Mapper	//단순 마커(표시)
public interface BrdMapper {
	// get 2개, insert, update, delete
	
	//select의 경우 여기 I/F에서 return 타입으로 selectListㄴ, selectOne이냐가 결정됨
	public List<BrdVo> getList();
	public BrdVo getBrd(int bdId);
	
	public int insertBrd(BrdVo brdVo);
	public int updateBrd(BrdVo brdVo);
	public int deleteBrd(int bdId);
}
