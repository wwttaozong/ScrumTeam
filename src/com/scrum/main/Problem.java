package com.scrum.main;

/**
 * 用于存储用户题目的结构
 * 
 * @author wwt
 *
 */
public class Problem {

	// 题目名称
	private String name;
	// 题目描述
	private String description;
	// 示例输入
	private String input;
	// 示例输出
	private String output;

	public Problem() {

	}

	public Problem(String name, String description, String input, String output) {
		super();
		this.name = name;
		this.description = description;
		this.input = input;
		this.output = output;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	@Override
	public String toString() {
		return "Problems [name=" + name + ", description=" + description
				+ ", input=" + input + ", output=" + output + "]";
	}

}
