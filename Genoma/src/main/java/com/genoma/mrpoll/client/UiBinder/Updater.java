package com.genoma.mrpoll.client.UiBinder;

import java.util.List;

import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.uihelper.AnswerUI;

public interface Updater {
	public List<AnswerUI> getAnswersFromUi();
}
