package com.mfizz.play.util;

import play.api.mvc.Call;

public class FormHelper {

	private final FormMode mode;
	private final String title;
	private final String submit;
	private final Call action;

	public FormHelper(FormMode mode, String title, String submit, Call action) {
		this.mode = mode;
		this.title = title;
		this.submit = submit;
		this.action = action;
	}
	public FormMode getMode() {
		return mode;
	}
	
	public boolean isUpdateMode() {
		return this.mode == FormMode.UPDATE;
	}
	
	public boolean isCreateMode() {
		return this.mode == FormMode.CREATE;
	}
	
	public FormHelper setMode(FormMode mode) {
		return new FormHelper(mode, this.title, this.submit, this.action);
	}
	
	public String getTitle() {
		return title;
	}
	
	public FormHelper setTitle(String title) {
		return new FormHelper(this.mode, title, this.submit, this.action);
	}
	
	public String getSubmit() {
		return submit;
	}
	
	public FormHelper setSubmit(String submit) {
		return new FormHelper(this.mode, this.title, submit, this.action);
	}
	
	public Call getAction() {
		return action;
	}
	
	public FormHelper setAction(Call action) {
		return new FormHelper(this.mode, this.title, this.submit, action);
	}
	
}
