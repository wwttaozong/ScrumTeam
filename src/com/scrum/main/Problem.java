package com.scrum.main;

/**
 * ���ڴ洢�û���Ŀ�Ľṹ
 * 
 * @author wwt
 *
 */
public class Problem {

	// ��Ŀ����
	private String name;
	// ��Ŀ����
	private String description;
	// ʾ������
	private String input;
	// ʾ�����
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
