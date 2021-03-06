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
package org.ednovo.gooru.client.mvp.classpages.newclasspage;
/**
* @fileName : NewClasspagePopupView.java 
*
* @description :This file is responsible to show view based on ExistsResourceView.ui.xml for adding classpage title
*
* @version :5.2
*
* @date: Apr 17 2013
   	
* @Author  Gooru Team
* 
* @Reviewer 
*
*/
import java.util.HashMap;
import java.util.Map;

import org.ednovo.gooru.client.SimpleAsyncCallback;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.search.event.SetHeaderZIndexEvent;
import org.ednovo.gooru.client.uc.AppPopUp;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public abstract class NewClasspagePopupView extends AppPopUp implements MessageProperties{

	private static NewClasspagePopupViewUiBinder uiBinder = GWT.create(NewClasspagePopupViewUiBinder.class);
	
	@UiField(provided = true)
	NewClasspagePopupCBundle res;
	
	@UiField Label mandatoryClasspageTitleLbl;
	@UiField Button btnCancel, btnAdd;
	@UiField TextBox classpageTitleTxt;
	
	@UiField Label panelLoading;
	
	@UiField HTMLPanel panelControls,titlePanel,headerPanel;
		
	interface NewClasspagePopupViewUiBinder extends
		UiBinder<Widget, NewClasspagePopupView> {
	}
	
	public abstract void createNewClasspage(String title);
	
	public NewClasspagePopupView() {
		
		this.res = NewClasspagePopupCBundle.INSTANCE;
		res.css().ensureInjected();
		setWidget(uiBinder.createAndBindUi(this));
		this.getElement().getStyle().setWidth(450,Unit.PX);
		btnCancel.addClickHandler(new CloseExistsClickHandler());
		btnAdd.addClickHandler(new AddExistsClickHandler());
		classpageTitleTxt.getElement().setAttribute("placeholder", GL1124);
		classpageTitleTxt.getElement().setAttribute("maxlength", "50");
		classpageTitleTxt.getElement().setId("txtClassPageTitle");
		btnAdd.getElement().setId("btnAdd");
		btnAdd.setText(GL0745);
		btnCancel.setText(GL0142);
		btnCancel.getElement().setId("btnCancel");
		titlePanel.getElement().setInnerText(GL0318 + GL_SPL_STAR);
		headerPanel.getElement().setInnerText(GL0747);
		mandatoryClasspageTitleLbl.setText(GL0746);
		classpageTitleTxt.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				Map<String, String> parms = new HashMap<String, String>();
				parms.put("text", classpageTitleTxt.getText());
				AppClientFactory.getInjector().getResourceService().checkProfanity(parms, new SimpleAsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean value) {
						boolean isHavingBadWords = value;
						if (value){
							classpageTitleTxt.getElement().getStyle().setBorderColor("orange");
							mandatoryClasspageTitleLbl.setText(GL0554);
							mandatoryClasspageTitleLbl.setVisible(true);
						}else{							
							classpageTitleTxt.getElement().getStyle().clearBackgroundColor();
							classpageTitleTxt.getElement().getStyle().setBorderColor("#ccc");
							mandatoryClasspageTitleLbl.setVisible(false);
						}
					}
				});
			}
		});
		
		
		
		classpageTitleTxt.addKeyUpHandler(new TitleKeyUpHandler());
        setModal(true);
		Window.enableScrolling(false);
        AppClientFactory.fireEvent(new SetHeaderZIndexEvent(98, false));
		mandatoryClasspageTitleLbl.setVisible(false);
		panelLoading.setVisible(false);
		panelLoading.setText(GL0122);
		panelControls.setVisible(true);
		show();
		center();
		classpageTitleTxt.setFocus(true);
	}
	
	private class TitleKeyUpHandler implements KeyUpHandler {

		public void onKeyUp(KeyUpEvent event) {
			classpageTitleTxt.getElement().getStyle().clearBackgroundColor();
			classpageTitleTxt.getElement().getStyle().setBorderColor("#ccc");
			mandatoryClasspageTitleLbl.setVisible(false);
			if (classpageTitleTxt.getText().length() >= 50) {
				mandatoryClasspageTitleLbl.setText(GL0143);
				mandatoryClasspageTitleLbl.setVisible(true);
			}
		}
	}
	//Click handler for Close/Cancel
	private class CloseExistsClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			hide();
			Window.enableScrolling(true);
	        AppClientFactory.fireEvent(new SetHeaderZIndexEvent(0, true));
		}		
	}
	//Click event to handle Add existing resource/collection item to collection.
	private class AddExistsClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			if (validateFields()){
				final String title = classpageTitleTxt.getText().trim();
				
				Map<String, String> parms = new HashMap<String, String>();
				parms.put("text", title);
				AppClientFactory.getInjector().getResourceService().checkProfanity(parms, new SimpleAsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean value) {
						boolean isHavingBadWords = value;
						if (isHavingBadWords){
							classpageTitleTxt.getElement().getStyle().setBorderColor("orange");
						}else{
							createNewClasspage(title);
							panelControls.setVisible(false);
							panelLoading.setVisible(true);
							classpageTitleTxt.getElement().getStyle().clearBackgroundColor();
							classpageTitleTxt.getElement().getStyle().setBorderColor("#ccc");
						}
					}
				});
				
				
			}
		}
	}
	
	public boolean validateFields(){
		boolean isValid=true;
		String title = classpageTitleTxt.getText().trim();
		if (title==null || title.equalsIgnoreCase("")){
			mandatoryClasspageTitleLbl.setVisible(true);
			return false;
		}
		
		return isValid;
	}
	
	public void ClosePopup(){
		Window.enableScrolling(true);
        AppClientFactory.fireEvent(new SetHeaderZIndexEvent(0, true));
		panelLoading.setVisible(false);
		hide();
	}
	
}
