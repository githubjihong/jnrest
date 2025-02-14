package com.jn.rest.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jn.rest.service.BrdService;
import com.jn.rest.vo.BrdVo;
import com.jn.rest.vo.MaguVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")	//모두에게 풀어주겠음(수정)... //http://localhost:8282 특정 IP만 풀어주기
@RestController	//@ResponseBody 포함됨
@RequestMapping("/api")
public class BrdController {
	
	@Autowired
	private BrdService brdService;
	
	// 전체 리스트 조회 => 조회니깡 get
	@GetMapping("/bds")
	public List<BrdVo> getList(){
		log.debug("getList에 왔낭??");
		return brdService.getList();
	}
	
	// 게시글 1개 조회, id 기준
	@GetMapping("/bds/{bdId}")
	public BrdVo getBrd(@PathVariable int bdId) throws JsonProcessingException {
		log.debug("bdId {}", bdId);
		
		/*
		 *	Jackson 라이브러리 동작 확인, @RequestBody와 @ResponseBody에 자동으로 동작 
		 */
		
		ObjectMapper objMapper = new ObjectMapper();	//잭슨 라이브러리에 있는 변환기
		
		BrdVo jnVo = new BrdVo();
		jnVo.setBdId(33);
		jnVo.setBdTitle("쭈니 제목");
		jnVo.setBdTitle("내용");
		jnVo.setBdWriter("쭈쭈니");
		
		// @ResponseBody의 동작
		String jsonString = objMapper.writeValueAsString(jnVo);	//객체를 json문자열로..
		
		// @RequestBody의 동작 json문자열 => 자바객체 보통 Vo
		String sampleString = "{\"bdId\":33,\"bdTitle\":\"호산나\",\"bdCont\":null,\"bdWriter\":\"쭈쭈니\",\"bdImg\":null}";
		BrdVo testVo = objMapper.readValue(sampleString, BrdVo.class);
		
		log.debug("눈으로 확인 {}", testVo);
		log.debug("눈으로 확인 {}", jsonString);
		
		log.debug("brdService.getBrd(bdId) : {}",brdService.getBrd(bdId) );
		
		return brdService.getBrd(bdId);
	}
	
	// 브라우져에서 넘어온 JSON 문자열 <=> 자바객체 Jackson 라이브러리, GSON 등등
	@PostMapping("/bds")
	public String insertBrd(@RequestBody BrdVo brdVo) {
		log.debug("bdId {}", brdVo);	//디버깅 습관적으로 할 것
		
		int cnt = brdService.insertBrd(brdVo);
		String result = "success";
		
		if(cnt == 0) result = "fail";
		
		return result;
	}
	
	// 브라우져에서 FormData(binary formData/mulit-part)가 넘어 왔을 때
	// 브라우져에서 넘어온 JSON 문자열 <=> 자바객체 Jackson 라이브러리, GSON 등등
	// RequestPart -> MultipartParser가 먼저 넘어온 걸 해석한 뒤에 해당 키값에 대한 문자열만
	// Jackson 라이브러리에 넘기면서 자바 객체로 변환됨!
	@PostMapping("/bds2")
	public String insertBrd2(BrdVo brdVo, 
							 @RequestPart("young") List<String> youngList,
							 @RequestPart("sein") Map<String,Object> seinMap,
							 @RequestPart("mix") List<MaguVo> maguList) throws IllegalStateException, IOException {
		
		log.debug("part List 체크 {}",youngList);
		log.debug("part Map 체크 {}",seinMap);
		log.debug("part 체크 {}", maguList);
		
		log.debug("bdId {}", brdVo);	//디버깅 습관적으로 할 것
		log.debug("bdFile {}",brdVo.getBdFile());
		log.debug("bdFile 이름{}",brdVo.getBdFile().getOriginalFilename());
		
		MultipartFile jnFile = brdVo.getBdFile();
		
		//서버 하드디스크에 저장, 웹경로와 물리적 경로 맵핑이 필요, 왜? 물리적 경로 안알려 줄꺼니깡
		String saveDir = "d:/jnUp/";
		
		jnFile.transferTo(new File(saveDir + jnFile.getOriginalFilename()));
		
		String webUrl = "/jnweb/" + jnFile.getOriginalFilename();
		brdVo.setBdImg(webUrl);
		
		int cnt = brdService.insertBrd(brdVo);
		
		String result = "success";
		
		if(cnt == 0) result = "fail";
		
		return result;
	}	
	
	// 브라우져에서 넘어온 JSON 문자열 <=> 자바객체 Jackson 라이브러리, GSON 등등
	@PutMapping("/bds")
	public String updateBrd(@RequestBody BrdVo brdVo) {
		log.debug("brdVo {}", brdVo); // 디버깅 습관적으로 할 것
		
		int cnt = brdService.updateBrd(brdVo);
		String result = "success";
		
		if(cnt == 0) result = "fail";
		
		return result;
	}
	
	// 브라우져에서 넘어온 JSON 문자열 <=> 자바객체 Jackson 라이브러리, GSON 등등
	@PutMapping("/bds2")
	public String updateBrd2(BrdVo brdVo) throws IllegalStateException, IOException {
		log.debug("brdVo {}", brdVo); // 디버깅 습관적으로 할 것
		
		MultipartFile jnFile = brdVo.getBdFile();
		
		//서버 하드디스크에 저장, 웹경로와 물리적 경로 맵핑이 필요, 왜? 물리적 경로 안알려 줄꺼니깡
		String saveDir = "d:/jnUp/";
		
		jnFile.transferTo(new File(saveDir + jnFile.getOriginalFilename()));
		
		String webUrl = "/jnweb/" + jnFile.getOriginalFilename();
		brdVo.setBdImg(webUrl);
		
		
		int cnt = brdService.updateBrd(brdVo);
		String result = "success";
		
		if(cnt == 0) result = "fail";
		
		return result;
	}	
	
	// 브라우져에서 넘어온 JSON 문자열 <=> 자바객체 Jackson 라이브러리, GSON 등등
	@DeleteMapping("/bds/{bdId}")
	public String deleteBrd(@PathVariable int bdId) {
		log.debug("bdId {}", bdId); // 디버깅 습관적으로 할 것
		
		int cnt = brdService.deleteBrd(bdId);
		String result = "success";
		
		if(cnt == 0) result = "fail";
		
		return result;
	}
}