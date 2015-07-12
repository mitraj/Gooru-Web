/*******************************************************************************
 * Copyright 2013 Ednovo d/b/a Gooru. All rights reserved.
 *
 *  http://www.goorulearning.org/
 *
 *  Permission is hereby granted, free of charge, to any person obtaining
 *  a copy of this software and associated documentation files (the
 *  "Software"), to deal in the Software without restriction, including
 *  without limitation the rights to use, copy, modify, merge, publish,
 *  distribute, sublicense, and/or sell copies of the Software, and to
 *  permit persons to whom the Software is furnished to do so, subject to
 *  the following conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 *  LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 *  OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 *  WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package org.ednovo.gooru.client.mvp.gshelf.coursedetails;

import java.util.ArrayList;
import java.util.List;

import org.ednovo.gooru.application.client.gin.AppClientFactory;
import org.ednovo.gooru.application.client.gin.BaseViewWithHandlers;
import org.ednovo.gooru.application.shared.model.content.ClasspageDo;
import org.ednovo.gooru.application.shared.model.content.CollectionDo;
import org.ednovo.gooru.client.mvp.gshelf.util.ClassListWidget;
import org.ednovo.gooru.shared.util.StringUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author Search Team
 *
 */
public class CourseShareView extends BaseViewWithHandlers<CourseShareUiHandlers> implements IsCourseShareView {

	private static CourseShareViewUiBinder uiBinder = GWT.create(CourseShareViewUiBinder.class);

	@UiTemplate("CourseShareView.ui.xml")
	interface CourseShareViewUiBinder extends UiBinder<Widget, CourseShareView> {
	}
	
	@UiField ListBox classListBox;
	@UiField Button assignCourseBtn;
	@UiField HTMLPanel classListPnl;
	@UiField Label errorMsgLbl;
	
	/**
	 * Class constructor
	 * @param eventBus {@link EventBus}
	 */
	@Inject
	public CourseShareView() {
		setWidget(uiBinder.createAndBindUi(this));
		classListBox.addChangeHandler(new SelectClassHandler());
		errorMsgLbl.setVisible(false);
	}
	
	private class SelectClassHandler implements ChangeHandler{

		@Override
		public void onChange(ChangeEvent event) {
			// TODO Auto-generated method stub
			classListBox.getValue(classListBox.getSelectedIndex());
		}
	}

	@Override
	public void setClassesList(List<CollectionDo> searchResult) {
		classListBox.clear();
		classListBox.addItem("---Select the class--");
		for(CollectionDo collectiondo :searchResult){
			classListBox.addItem(collectiondo.getName(), collectiondo.getClassUid());
		}
	}
	
	@UiHandler("assignCourseBtn")
	public void clickOnAssignBtn(ClickEvent clickEvent){
		String courseId= AppClientFactory.getPlaceManager().getRequestParameter("o1",null);
		if(courseId!=null){
			if(classListBox.getSelectedIndex()!=0 && !StringUtil.isEmpty(classListBox.getValue(classListBox.getSelectedIndex()))){
				errorMsgLbl.setVisible(false);
				ClasspageDo classpageObj= new ClasspageDo();
				classpageObj.setCourseGooruOid(courseId);
				//classpageObj.setTitle(title)(courseId);
				getUiHandlers().assign2ClassPage(classListBox.getValue(classListBox.getSelectedIndex()),classpageObj);
			}else{
				errorMsgLbl.setVisible(true);
				errorMsgLbl.setText("Please select the class");
			}
			
		}else{
			errorMsgLbl.setVisible(true);
			errorMsgLbl.setText("Please save this course");
		}
		
	}

	@Override
	public void showClassesInList(ArrayList<ClasspageDo> classPageDo) {
		if(classPageDo!=null){
			classListPnl.clear();
			for(ClasspageDo classObj:classPageDo){
				ClassListWidget classListWidget = new ClassListWidget(classObj.getName(),classObj.getClassUid());
				classListPnl.add(classListWidget);
			}
		}else{
			String name=classListBox.getItemText(classListBox.getSelectedIndex());
			String classId= classListBox.getValue(classListBox.getSelectedIndex());
			ClassListWidget classListWidget = new ClassListWidget(name,classId);
			classListPnl.add(classListWidget);
		}
		
	}

	@Override
	public void clearSharePlanes() {
		classListPnl.clear();
		errorMsgLbl.setVisible(false);
	}
}