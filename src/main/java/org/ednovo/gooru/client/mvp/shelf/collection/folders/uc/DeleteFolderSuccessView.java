package org.ednovo.gooru.client.mvp.shelf.collection.folders.uc;

import org.ednovo.gooru.client.uc.AppPopUp;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
/**
 * @author Search Team
 * 
 */
public abstract class DeleteFolderSuccessView extends Composite implements MessageProperties{

	private static DeleteFolderSuccessViewUiBinder uiBinder = GWT
			.create(DeleteFolderSuccessViewUiBinder.class);

	interface DeleteFolderSuccessViewUiBinder extends
			UiBinder<Widget, DeleteFolderSuccessView> {
	}
	@UiField Button cancelButton,okButton;
	@UiField Label headerTitle;
	@UiField HTMLPanel buttonContainer,contentPnl;
	
	public AppPopUp appPopUp;
	
	public DeleteFolderSuccessView() {
//		setWidget(uiBinder.createAndBindUi(this));
		initWidget(uiBinder.createAndBindUi(this));
		appPopUp = new AppPopUp();
		appPopUp.setStyleName("removeResourcePopup");
		appPopUp.getElement().getStyle().setWidth(456, Unit.PX);
		appPopUp.getElement().getStyle().setHeight(245, Unit.PX);
		appPopUp.getHeaderPanel().getElement().getStyle().setMarginBottom(-2, Unit.PX);
		appPopUp.setContent(GL1176, uiBinder.createAndBindUi(this));
		appPopUp.show();
		appPopUp.center();
		cancelButton.setText(GL0142);
		Window.enableScrolling(false);
		okButton.setText(GL0190);
		headerTitle.setText(GL1173);
//		titleLabel.setText(MessageProperties.GL1176);
		cancelButton.setVisible(false);
		buttonContainer.getElement().setAttribute("Style", "margin-bottom: 22px !important;");
	}
	
	public DeleteFolderSuccessView(String movedFolderName) {
		initWidget(uiBinder.createAndBindUi(this));
		appPopUp = new AppPopUp();
		appPopUp.setStyleName("removeResourcePopup");
		appPopUp.getElement().getStyle().setWidth(456, Unit.PX);
		appPopUp.getElement().getStyle().setHeight(245, Unit.PX);
		appPopUp.getHeaderPanel().getElement().getStyle().setMarginBottom(-2, Unit.PX);
		appPopUp.setContent(GL1367, uiBinder.createAndBindUi(this));
		appPopUp.show();
		appPopUp.center();
		cancelButton.setText(GL0142);
		Window.enableScrolling(false);
		okButton.setText(GL0190);
		headerTitle.setText(GL1366+"\""+movedFolderName+"\"");
		cancelButton.setVisible(false);
		buttonContainer.getElement().setAttribute("Style", "margin-bottom: 22px !important;");
	}
	
	public DeleteFolderSuccessView(String title, String desc) {
		initWidget(uiBinder.createAndBindUi(this));
		appPopUp = new AppPopUp();
		appPopUp.setStyleName("removeResourcePopup");
		appPopUp.getElement().getStyle().setWidth(456, Unit.PX);
		appPopUp.getElement().getStyle().setHeight(245, Unit.PX);
		appPopUp.getHeaderPanel().getElement().getStyle().setMarginBottom(-2, Unit.PX);
		appPopUp.setContent(title, uiBinder.createAndBindUi(this));
		appPopUp.show();
		appPopUp.center();
		cancelButton.setText(GL0142);
		Window.enableScrolling(false);
		okButton.setText(GL0190);
		contentPnl.getElement().getStyle().setHeight(117, Unit.PX);
		headerTitle.getElement().setInnerHTML(desc);	
		headerTitle.getElement().getStyle().setFontSize(12, Unit.PX);
		cancelButton.setVisible(false);
		buttonContainer.getElement().setAttribute("Style", "margin-bottom: 22px !important;");
	}
	
	

	@UiHandler("cancelButton")
	public void onClickOfCancelButton(ClickEvent event){
		
	}
	
	public abstract void onClickPositiveButton(ClickEvent event);
	
	@UiHandler("okButton")
	public void onClickOfokButton(ClickEvent event){
		onClickPositiveButton(event);
	}
}
