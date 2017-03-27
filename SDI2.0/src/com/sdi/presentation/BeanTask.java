package com.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.*;
import javax.faces.event.ActionEvent;

import com.sdi.dto.Task;

@ManagedBean(name = "task")
@SessionScoped
public class BeanTask extends Task implements Serializable {
	private static final long serialVersionUID = 55556L;

	public BeanTask() {
		iniciaTask(null);
	}

	public void setTask(Task task) {
		setId(task.getId());
		setUserId(task.getUserId());
		setCategoryId(task.getCategoryId());
		setTitle(task.getTitle());
		setComments(task.getComments());
		setCreated(task.getCreated());
		setPlanned(task.getPlanned());
		setFinished(task.getFinished());

	}

	public void iniciaTask(ActionEvent event) {
		setId(null);
		setUserId(null);
		setCategoryId(null);
		setCreated(null);
		setPlanned(null);
		setFinished(null);
		setComments("");
		setTitle("new Task");
	}

}