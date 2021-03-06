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
package org.ednovo.gooru.client.mvp.shelf.collection.tab.collaborators.vc;

import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.search.event.SetHeaderZIndexEvent;
import org.ednovo.gooru.client.mvp.shelf.collection.tab.collaborators.CollectionCollaboratorsCBundle;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author BLR Team
 * 
 */
public abstract class DeletePopupViewVc extends PopupPanel implements
		MessageProperties {

	@UiField(provided = true)
	CollectionCollaboratorsCBundle collaborators;

	@UiField
	Button btnNegitive, btnPositive;
	
	@UiField Label lblTitle, lblRemoving;
	
	@UiField HTML htmlNotes, htmlDescription;
	
	@UiField TextBox txtConfirmAction;
	
	boolean isValidate=false;

	private String deleteCode=null;
	
	@UiTemplate("DeletePopupVc.ui.xml")
	interface Binder extends UiBinder<Widget, DeletePopupViewVc> {

	}

	private static final Binder binder = GWT.create(Binder.class);

	/**
	 * 
	 */
	public DeletePopupViewVc() {
		super(false);
		this.collaborators = CollectionCollaboratorsCBundle.INSTANCE;
		collaborators.css().ensureInjected();
		add(binder.createAndBindUi(this));
		this.setGlassEnabled(true);
				
		htmlNotes.setVisible(false);
		lblRemoving.setVisible(false);
		lblRemoving.getElement().getStyle().setMargin(26, Unit.PX);
		txtConfirmAction.setVisible(false);
		setButtonVisibility(true);
		setElementId();
		
		txtConfirmAction.addKeyUpHandler(new ValidateConfirmText());
		txtConfirmAction.getElement().setAttribute("placeholder", GL1175);

		Window.enableScrolling(false);
		AppClientFactory.fireEvent(new SetHeaderZIndexEvent(98, false));
		this.center();
//		this.show();
	}
	

	/**
	 * @function setElementId 
	 * 
	 * @created_date : Jan 31, 2014
	 * 
	 * @description
	 * 
	 * 
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 * 
	 *
	 * 
	*/
	
	private void setElementId() {
		btnPositive.getElement().setId("btnPositive");
		btnNegitive.getElement().setId("btnNegitive");
	}

	/* Setters */
	/**
	 * 
	 * @function setPositiveButtonText 
	 * 
	 * @created_date : Jan 31, 2014
	 * 
	 * @description
	 * 
	 * 
	 * @param text
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 * 
	 *
	 *
	 */
	public void setPositiveButtonText(String text) {
		btnPositive.setText(text);
	}
	/**
	 * 
	 * @function setNegitiveButtonText 
	 * 
	 * @created_date : Jan 31, 2014
	 * 
	 * @description
	 * 
	 * 
	 * @param text
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 * 
	 *
	 *
	 */
	public void setNegitiveButtonText(String text) {
		btnNegitive.setText(text);
	}
	/**
	 * 
	 * @function setPleaseWaitText 
	 * 
	 * @created_date : Feb 8, 2014
	 * 
	 * @description
	 * 
	 * 
	 * @param text
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 * 
	 *
	 *
	 */
	public void setPleaseWaitText(String text){
		lblRemoving.setText(text);
	}
	/**
	 * 
	 * @function setPopupTitle 
	 * 
	 * @created_date : Jan 31, 2014
	 * 
	 * @description
	 * 
	 * 
	 * @param title
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 * 
	 *
	 *
	 */
	public void setPopupTitle(String title) {
		lblTitle.setText(title);
	}
	/**
	 * 
	 * @function setDescText 
	 * 
	 * @created_date : Feb 8, 2014
	 * 
	 * @description
	 * 
	 * 
	 * @param desc
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 * 
	 *
	 *
	 */
	public void setDescText(String desc){
		htmlDescription.setHTML(desc);
	}
	/**
	 * 
	 * @function setNotes 
	 * 
	 * @created_date : Feb 8, 2014
	 * 
	 * @description
	 * 
	 * 
	 * @param notes
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 * 
	 *
	 *
	 */
	public void setNotes(String notes){
		htmlNotes.setVisible(true);
		htmlNotes.setHTML(notes);
	}
	
	/**
	 * @function setButtonVisibility 
	 * 
	 * @created_date : Feb 8, 2014
	 * 
	 * @description
	 * 
	 * 
	 * @param b
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 * 
	 *
	 * 
	*/
	
	private void setButtonVisibility(boolean visibility) {
		btnPositive.setVisible(visibility);
		btnNegitive.setVisible(visibility);
	}


	/**
	 * @function showIsRemoving 
	 * 
	 * @created_date : Feb 8, 2014
	 * 
	 * @description
	 * 
	 * 
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 * 
	 *
	 * 
	*/
	
	public void showIsRemoving() {
		lblRemoving.setVisible(true);
	}
	
	
	public void setDeleteValidate(String deleteCode){
		isValidate = true;
		this.deleteCode = deleteCode;
		
		txtConfirmAction.setVisible(true);
		
		btnPositive.setEnabled(false);
		btnPositive.getElement().addClassName("disabled");
	}
	
	
	/**
	 * key handler to validate action text
	 * 
	 * @author BLR Team
	 * 
	 */
	private class ValidateConfirmText implements KeyUpHandler {

		@Override
		public void onKeyUp(KeyUpEvent event) {
			if (isValidate){
				if (txtConfirmAction.getText().trim().equalsIgnoreCase(deleteCode)) {
					btnPositive.getElement().removeClassName("disabled");
					btnPositive.setEnabled(true);
					if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
						if (lblRemoving.getText()!=null && !lblRemoving.getText().equalsIgnoreCase("")){
							setButtonVisibility(false);
							showIsRemoving();
						}
						onClickPositiveButton(null);
					}
				}else{
					btnPositive.getElement().addClassName("disabled");
					btnPositive.setEnabled(false);
				}
			}
		}
	}
	
	
	/* Click handler...*/
	
	@UiHandler("btnPositive")
	public void onPostitiveClickEvent(ClickEvent event){
		if (lblRemoving.getText()!=null && !lblRemoving.getText().equalsIgnoreCase("")){
			showIsRemoving();
			setButtonVisibility(false);
		}
		onClickPositiveButton(null);
	}
	
	@UiHandler("btnNegitive")
	public void onNegitiveClickEvent(ClickEvent event){
		onClickNegitiveButton(event);
	}
	
	/* Abstract methods to handle button events*/
	public abstract void onClickPositiveButton(ClickEvent event);

	public abstract void onClickNegitiveButton(ClickEvent event);
	
}
