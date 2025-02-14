package com.jn.rest.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MaguVo {
	private String name;
	private int age;
	private List<String> hobby;
}
