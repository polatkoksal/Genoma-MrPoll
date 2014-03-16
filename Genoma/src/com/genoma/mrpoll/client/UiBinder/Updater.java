package com.genoma.mrpoll.client.UiBinder;

import java.util.List;

import com.genoma.mrpoll.domain.Answer;

public interface Updater {
	public void updateUi(List<Answer> answers);
	public List<Answer> getAnswers();
}
