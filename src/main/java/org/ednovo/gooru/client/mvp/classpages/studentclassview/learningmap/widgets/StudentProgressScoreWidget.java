package org.ednovo.gooru.client.mvp.classpages.studentclassview.learningmap.widgets;

import org.ednovo.gooru.client.uc.H3Panel;
import org.ednovo.gooru.client.uc.PPanel;
import org.ednovo.gooru.client.ui.HTMLEventPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class StudentProgressScoreWidget extends Composite {
	
	@UiField HTMLEventPanel scorePanel;
	@UiField H3Panel score, timeSpent;
	@UiField PPanel scoreMessage, timeMessage;
	
	private static StudentProgressScoreWidgetUiBinder uiBinder = GWT.create(StudentProgressScoreWidgetUiBinder.class);
	
	interface StudentProgressScoreWidgetUiBinder extends UiBinder<Widget, StudentProgressScoreWidget> {}
	
	public StudentProgressScoreWidget(String scoreTxt, String style, String timeSpentTxt) {
		initWidget(uiBinder.createAndBindUi(this));
		setScoreData(scoreTxt,style);
		setCollectionData(timeSpentTxt);
	}
	
	public void setScoreData(String scoreTxt, String style) {
		scoreMessage.setText("Avg. Score");
		score.setText(scoreTxt);
		scorePanel.addStyleName(style);
	}
	
	public void setCollectionData(String timeSpentTxt) {
		timeMessage.setText("Total Study Time");
		timeSpent.setText(timeSpentTxt);
	}
}