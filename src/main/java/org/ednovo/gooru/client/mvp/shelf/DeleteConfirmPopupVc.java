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
package org.ednovo.gooru.client.mvp.shelf;

import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.search.event.SetHeaderZIndexEvent;
import org.ednovo.gooru.client.uc.AppPopUp;
import org.ednovo.gooru.client.uc.BlueButtonUc;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.FontStyle;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Search Team
 * 
 */
public abstract class DeleteConfirmPopupVc extends AppPopUp implements MessageProperties{

	/*@UiField
	Anchor cancelAnr;*/

	@UiField Button okButtonUc,cancelAnr;

	@UiField
	TextBox inlineTxtBox;

	@UiField
	Label entityLbl,loadingTextLbl,permenantText,typeDeleteText;
	
	@UiField
	FlowPanel buttonContainer,msgFlowPanel;
	
	@UiField
	HTML confirmMessagesText;

	private String confirmText = GL0558.toUpperCase();

	private static DeleteConfirmPopupVcUiBinder uiBinder = GWT.create(DeleteConfirmPopupVcUiBinder.class);

	interface DeleteConfirmPopupVcUiBinder extends UiBinder<Widget, DeleteConfirmPopupVc> {
	}

	/**
	 * Class constructor to set title and content text for pop up
	 * 
	 * @param title
	 *            is the header of the pop up
	 * @param entityInfo
	 *            is the content text of the pop up
	 */
	public DeleteConfirmPopupVc(String title, String entityInfo) {
		super();
		setContent(title, uiBinder.createAndBindUi(this));
		setStyleName("deleteResourcePopup");
		inlineTxtBox.getElement().setAttribute("placeholder", GL0826);
		inlineTxtBox.addKeyUpHandler(new ValidateConfirmText());
		inlineTxtBox.getElement().setId("txtInline");
		okButtonUc.getElement().setId("btnOk");
		cancelAnr.getElement().setId("lnkCancel");
		msgFlowPanel.getElement().getStyle().setTextAlign(TextAlign.CENTER);
		entityLbl.setText(" "+entityInfo);
        setModal(true);
		Window.enableScrolling(false);
        AppClientFactory.fireEvent(new SetHeaderZIndexEvent(99, false));
        loadingTextLbl.getElement().getStyle().setFontStyle(FontStyle.ITALIC);
        loadingTextLbl.setVisible(false);
        buttonContainer.setVisible(true);
		show();
		center();
		confirmMessagesText.setHTML(GL0824);
		permenantText.setText(GL0825);
		typeDeleteText.setText(GL0826);
		okButtonUc.setText(GL0190);
		cancelAnr.setText(GL0142);
		loadingTextLbl.setText(GL0560);
		//GL0190
	}

	/*@Override
	public void onUnload(){
		hide(true);
	}*/

	/**
	 * Hide {@link DeleteConfirmPopupVc} popup
	 * 
	 * @param clickEvent
	 *            instance of {@link ClickEvent}
	 */
	@UiHandler("cancelAnr")
	public void onCancelClick(ClickEvent clickEvent) {
		hide();
		Window.enableScrolling(true);
        AppClientFactory.fireEvent(new SetHeaderZIndexEvent(0, true));

	}

	/**
	 * confirm to to remove the content
	 * 
	 * @param clickEvent
	 *            instance of {@link ClickEvent}
	 */
	@UiHandler("okButtonUc")
	public void removeResource(ClickEvent clickEvent) {
		if (inlineTxtBox.getText().trim().equalsIgnoreCase(getConfirmText())) {
			this.onTextConfirmed();
			buttonContainer.setVisible(false);
			loadingTextLbl.setVisible(true);
		   
		}
	}

	/**
	 * key handler to validate action text
	 * 
	 * @author Search Team
	 * 
	 */
	private class ValidateConfirmText implements KeyUpHandler {

		@Override
		public void onKeyUp(KeyUpEvent event) {
			okButtonUc.setStyleName("deleteCollectionDisabledButton");
			if (inlineTxtBox.getText().trim().equalsIgnoreCase(getConfirmText())) {
				okButtonUc.setStyleName("overRideBlueButtonDelete");
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					onTextConfirmed();
				}
			}
		}
	}

	public abstract void onTextConfirmed();

	/**
	 * @return the confirmText
	 */
	public String getConfirmText() {
		return confirmText;
	}

	/**
	 * @param confirmText
	 *            the confirmText to set
	 */
	public void setConfirmText(String confirmText) {
		this.confirmText = confirmText;
	}

}
