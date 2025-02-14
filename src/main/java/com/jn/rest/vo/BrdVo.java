package com.jn.rest.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BrdVo {
	private int bdId;
	private String bdTitle; 
	private String bdCont;
	private String bdWriter; 
	private String bdImg;	//파일 경로 저장용으로 사용할 것임
	private MultipartFile bdFile;
}

