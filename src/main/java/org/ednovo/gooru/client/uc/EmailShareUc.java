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
package org.ednovo.gooru.client.uc;

import java.util.HashMap;
import java.util.Map;

import org.ednovo.gooru.client.PlaceTokens;
import org.ednovo.gooru.client.SimpleAsyncCallback;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.home.HomeCBundle;
import org.ednovo.gooru.client.mvp.socialshare.SentEmailSuccessVc;
import org.ednovo.gooru.client.service.ClasspageServiceAsync;
import org.ednovo.gooru.client.util.SetStyleForProfanity;
import org.ednovo.gooru.shared.model.social.SocialShareDo;
import org.ednovo.gooru.shared.util.MessageProperties;
import org.ednovo.gooru.shared.util.StringUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BodyElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.logical.shared.InitializeEvent;
import com.google.gwt.event.logical.shared.InitializeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @fileName : EmailShareUc.java
 *
 * @description : This file used to show popUp for share the data through email.
 * 
 * @version : 5.4
 *
 * @date:  August, 2013.
 *
 * @Author: Gooru Team
 * 
 * @Reviewer: Gooru Team
 */
public class EmailShareUc extends PopupPanel implements MessageProperties {

	private static EmailShareUcUiBinder uiBinder = GWT
			.create(EmailShareUcUiBinder.class);

	interface EmailShareUcUiBinder extends UiBinder<Widget, EmailShareUc> {
	}

	@UiField
	Label  cancelLbl, fromValidation, toValidation, checkCopyEmail, lblEmailFriend,lblFrom, lblTo,lblSendMeCopy,lblSubject,lblMessage,mandatoryErrorLbl,mandatoryErrorRichTextArea,noteTxt;

	@UiField
	Button btnSend;

	@UiField
	TextBoxWithPlaceholder toTxt, fromTxt;
	
	@UiField
	TextBox subTxt;

	@UiField
	RichTextArea msgTxa;

	/*
	 * @UiField CheckBox checkCopyEmail;
	 */

	SocialShareDo socialShareDo;

	private static boolean isCheckedValue;

	private static boolean isvalid;

	private SimpleAsyncCallback<Void> socialShareAsyncCallback = null;

	@Inject
	private ClasspageServiceAsync classpageServiceAsync;

	private String cfm;

	private String loggedEmailId;
	boolean isHavingBadWordsInTextbox=false,isHavingBadWordsInRichText=false;
	private int count=0;

	private static final String AT_SYMBOL = "@";

	/**
	 * Class constructor , create a new pop up
	 * @param socialDo, Object of SocialShareDo. 
	 */
	public EmailShareUc(SocialShareDo socialDo) {
		super(false);
		setWidget(uiBinder.createAndBindUi(this));
		UcCBundle.INSTANCE.css().ensureInjected();
		HomeCBundle.INSTANCE.css().ensureInjected();
		this.socialShareDo = socialDo;
		setSocialShareAsyncCallback(new SimpleAsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
			}

		});
		this.getElement().getStyle().setZIndex(999999);
		this.setGlassStyleName(HomeCBundle.INSTANCE.css()
				.loginPopupGlassStyle());
		fromValidation.setText(GL0215);
		toValidation.setText(GL0216);
		lblEmailFriend.setText(GL0222);
		lblFrom.setText(GL0223+GL_SPL_SEMICOLON);
		lblTo.setText(GL0224+GL_SPL_SEMICOLON);
		lblSendMeCopy.setText(GL0225);
		lblSubject.setText(GL0226+GL_SPL_SEMICOLON);
		lblMessage.setText(GL0227+GL_SPL_SEMICOLON);
		btnSend.setText(GL0228);
		cancelLbl.setText(GL0142);
		
		fromTxt.setMaxLength(50);

		mandatoryErrorLbl.setVisible(false);
		mandatoryErrorRichTextArea.setVisible(false);
		
		fromValidation.setVisible(false);
		toValidation.setVisible(false);
		
		noteTxt.setText(GL1636);
		if(AppClientFactory.getPlaceManager().getCurrentPlaceRequest().getNameToken().equals(PlaceTokens.EDIT_CLASSPAGE)){
			noteTxt.setVisible(false);
		}else{
			noteTxt.setVisible(true);
		}
		
		if(!AppClientFactory.isAnonymous())
		{
		loggedEmailId=AppClientFactory.getLoggedInUser().getFirstName() + " " +AppClientFactory.getLoggedInUser().getLastName();
		}
		else
		{
			loggedEmailId = "";
		}
		cancelLbl.getElement().setId("lblCancel");
		toTxt.getElement().setId("tbTo");
		subTxt.getElement().setId("tbSubject");
		fromTxt.getElement().setId("tbFrom");
		msgTxa.getElement().setId("taMessage");
		btnSend.getElement().setId("btnSend");
		//fromTxt.getElement().getStyle().setBorderWidth(0, Unit.PX);
		fromTxt.setText(loggedEmailId);
		//fromTxt.setReadOnly(true);
//		toTxt.getElement().setAttribute("placeholder", GL1184_1);
		toTxt.setPlaceholder(GL1184_1);
		fromTxt.addBlurHandler(new CheckProfanityInOnBlur(fromTxt,null, fromValidation));
		subTxt.addBlurHandler(new CheckProfanityInOnBlur(subTxt,null, mandatoryErrorLbl));
		msgTxa.addBlurHandler(new CheckProfanityInOnBlur(null,msgTxa, mandatoryErrorRichTextArea));
		if(AppClientFactory.getCurrentPlaceToken().equals(PlaceTokens.PROFILE_PAGE)){
			if(socialShareDo.getIsSearchShare()){
				//				subTxt.setText("Gooru -"+socialShareDo.getTitle());
				subTxt.setText(StringUtil.generateMessage(GL0218, socialShareDo.getTitle()));
				//				msgTxa.setHTML(socialShareDo.getTitle() +"<div><br/></div>"+"<div>" +socialShareDo.getBitlylink() + "</div><div><br/></div>"+ "<div>"+"Sent using Gooru. Visit www.goorulearning.org for more great resources and collections. It's free!"+"</div>");
				msgTxa.setHTML(StringUtil.generateMessage(GL0219, socialShareDo.getTitle(), socialShareDo.getDecodeRawUrl(), AppClientFactory.getLoggedInUser().getSettings().getHomeEndPoint()));
			}else{
				//				subTxt.setText("Check out "+socialShareDo.getTitle()+" Gooru profile and fantastic collections");
				subTxt.setText(StringUtil.generateMessage(GL0220, socialShareDo.getTitle()));
				//				msgTxa.setHTML(socialShareDo.getTitle() +" is an active member of the Gooru community! Take a look and browse through all their great collections - " +socialShareDo.getBitlylink()
				//						+ "<div><br/></div>"+ "<div>"+"Gooru is a free search engine for learning used by thousands of teachers around the world to discover, organize and create teaching materials."+ "</div><div><br/></div>"+ "<div>"+"Experience Gooru today at http://goorulearning.org/"+"</div>");
				msgTxa.setHTML(StringUtil.generateMessage(GL0221, socialShareDo.getTitle(), socialShareDo.getBitlylink(), AppClientFactory.getLoggedInUser().getSettings().getHomeEndPoint()));
			}
		}else if(AppClientFactory.getCurrentPlaceToken().equals(PlaceTokens.EDIT_CLASSPAGE)){
			   lblEmailFriend.setText(GL0222_1);
			   subTxt.setText(StringUtil.generateMessage(GL0218_1, socialShareDo.getCategoryType(), "\"" + socialShareDo.getTitle() + "\""));
			   msgTxa.setHTML(StringUtil.generateMessage(GL0219_1, socialShareDo.getCategoryType(), socialShareDo.getTitle(), socialShareDo.getBitlylink(), socialShareDo.getDecodeRawUrl(), AppClientFactory.getLoggedInUser().getSettings().getHomeEndPoint()));
		}else{
			//			subTxt.setText("Gooru -"+socialShareDo.getTitle());
			subTxt.setText(StringUtil.generateMessage(GL0218, socialShareDo.getTitle()));
			//			msgTxa.setHTML(socialShareDo.getTitle() +"<div><br/></div>"+"<div>" +socialShareDo.getBitlylink() + "</div><div><br/></div>"+ "<div>"+"Sent using Gooru. Visit www.goorulearning.org for more great resources and collections. It's free!"+"</div>");
			msgTxa.setHTML(StringUtil.generateMessage(GL0219, socialShareDo.getTitle(), socialShareDo.getDecodeRawUrl(), AppClientFactory.getLoggedInUser().getSettings().getHomeEndPoint()));
		}

		isCheckedValue = false;
		isvalid = true;
		Window.enableScrolling(false);
		this.setGlassEnabled(true);
		this.center();
		msgTxa.addInitializeHandler(new InitializeHandler() {
			@Override
			public void onInitialize(InitializeEvent event) {
				Document document = IFrameElement.as(msgTxa.getElement()).getContentDocument();
				BodyElement body = document.getBody();
				body.setAttribute("style", "font-family: Arial;font-size:12px;");
			}
		});

	}

	/**
	 * Hide {@link EmailShareUc} popup
	 * @param clickEvent instOLance of {@link ClickEvent}
	 */
	@UiHandler("cancelLbl")
	public void onCancelClickEvent(ClickEvent event) {
		this.hide();
		String placeToken = AppClientFactory.getPlaceManager().getCurrentPlaceRequest().getNameToken();
		if(!placeToken.equals(PlaceTokens.COLLECTION_PLAY) || !placeToken.equals(PlaceTokens.PREVIEW_PLAY)|| !placeToken.equals(PlaceTokens.RESOURCE_PLAY)) {
			Window.enableScrolling(true);
		}
	}

	/**
	 * Validates and send the data.
	 * @param clickEvent instance of {@link ClickEvent}
	 */
	@UiHandler("btnSend")
	public void onSendBtnClickEvent(ClickEvent event) {
		isvalid = true;
/*		if(fromTxt.getText() != null || !fromTxt.getText().equals("")){
			String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
			Boolean from = fromTxt.getText().matches(EMAIL_REGEX);
			if(from){
				isvalid = true;
			}else{
				toValidation.setText(GL1027);
				toValidation.setVisible(true);
				isvalid = false;
			}
		}*/
		if(!toTxt.getText().trim().equals("")){
			String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
			String strEmails = toTxt.getText().trim();
			String emailIds[] = strEmails.split("\\s*,\\s*");
			if (strEmails.contains(",")){
				emailIds = strEmails.split("\\s*,\\s*");
			}else if (strEmails.contains(";")){
				emailIds = strEmails.split("\\s*;\\s*");
			}
			
			for (int i=0; i<emailIds.length; i++){
				boolean to = emailIds[i].matches(EMAIL_REGEX);
				if(to){
					isvalid = true;
				}else{
					toValidation.setText(StringUtil.generateMessage(GL1019, emailIds[i]));
					toValidation.setVisible(true);
					isvalid = false;
					break;
				}
			}
		}
		if ((toTxt.getText() != null && !toTxt.getText().isEmpty())
				&& !toTxt.getText().contains(AT_SYMBOL)) {
			toValidation.setText(GL1027);
			toValidation.setVisible(true);
			isvalid = false;
	
		}
/*		if (fromTxt.getText().equals("")) {
			fromValidation.setText(GL0215);
			fromValidation.setVisible(true);
			isvalid = false;
		}*/
		if (toTxt.getText().equals("") || toTxt.getText().trim().equals("")) {
			toValidation.setText(GL0216);
			toValidation.setVisible(true);
			isvalid = false;
		}
/*		if ((fromTxt.getText() != null && !fromTxt.getText().isEmpty())
				&& !fromTxt.getText().contains(AT_SYMBOL)) {

			fromValidation.setText(GL1027);
			fromValidation.setVisible(true);
			isvalid = false;
		}*/
		if(isvalid){
			final Map<String, String> parms = new HashMap<String, String>();
			parms.put("text", subTxt.getValue());
			AppClientFactory.getInjector().getResourceService().checkProfanity(parms, new SimpleAsyncCallback<Boolean>() {
				@Override
				public void onSuccess(Boolean value) {
						isHavingBadWordsInTextbox = value;
						if(value){
							SetStyleForProfanity.SetStyleForProfanityForTextBox(subTxt, mandatoryErrorLbl,value);
						}else{
							parms.put("text", msgTxa.getText());
							AppClientFactory.getInjector().getResourceService().checkProfanity(parms,new SimpleAsyncCallback<Boolean>() {
								
								@Override
								public void onSuccess(Boolean result) {
									isHavingBadWordsInRichText=result;
									if(result){
										SetStyleForProfanity.SetStyleForProfanityForRichTextArea(msgTxa, mandatoryErrorRichTextArea, result);
									}else{
										if(count==0){
										if (!isHavingBadWordsInRichText && !isHavingBadWordsInTextbox) {
											AppClientFactory
											.getInjector()
											.getClasspageService()
											.socialShareEmail(fromTxt.getText(), toTxt.getText(), cfm,
													subTxt.getText(), msgTxa.getHTML(),
													new SimpleAsyncCallback<Void>() {

												@Override
												public void onSuccess(Void result) {
													hide();
													String placeToken = AppClientFactory.getPlaceManager().getCurrentPlaceRequest().getNameToken();
													if(!(placeToken.equals(PlaceTokens.COLLECTION_PLAY) || !placeToken.equals(PlaceTokens.PREVIEW_PLAY)||placeToken.equals(PlaceTokens.RESOURCE_PLAY))) {
														Window.enableScrolling(true);
													}
													new SentEmailSuccessVc(toTxt.getText());
												}
											});
										}
									}
										count++;
									}
									
								}
							});
						}
				}
			});
		}
	/*	if (isvalid  && !isHavingBadWordsInRichText && !isHavingBadWordsInTextbox) {
			AppClientFactory
			.getInjector()
			.getClasspageService()
			.socialShareEmail(fromTxt.getText(), toTxt.getText(), cfm,
					subTxt.getText(), msgTxa.getHTML(),
					new SimpleAsyncCallback<Void>() {

				@Override
				public void onSuccess(Void result) {
					hide();
					String placeToken = AppClientFactory.getPlaceManager().getCurrentPlaceRequest().getNameToken();
					if(!(placeToken.equals(PlaceTokens.COLLECTION_PLAY) || !placeToken.equals(PlaceTokens.PREVIEW_PLAY)||placeToken.equals(PlaceTokens.RESOURCE_PLAY))) {
						Window.enableScrolling(true);
					}
					new SentEmailSuccessVc(toTxt.getText());
				}

			});

		}*/
	}

	@UiHandler("fromTxt")
	public void onKeydownEvent(KeyDownEvent event) {
		fromValidation.setVisible(false);
	}

	@UiHandler("toTxt")
	public void onKeydownClickEvent(KeyDownEvent event) {
		toValidation.setVisible(false);
	}

	@UiHandler("checkCopyEmail")
	public void oncheckCopyEmailEvent(ClickEvent event) {

		if (isCheckedValue) {
			checkCopyEmail.setStyleName(UcCBundle.INSTANCE.css()
					.classPageEmailCheckBoxBgHoverSprite());
			isCheckedValue = false;
			cfm = "no";
		} else {
			checkCopyEmail.setStyleName(UcCBundle.INSTANCE.css()
					.classPageEmailCheckBoxBgHover());
			isCheckedValue = true;
			cfm = "yes";
		}
		/*
		 * if(cfm.equalsIgnoreCase("yes")){
		 * toTxt.setText(toTxt.getText()+","+fromTxt.getText()); }else{
		 * 
		 * }
		 */

	}

	/*public ClasspageServiceAsync getclasspageServiceAsync() {
		return classpageServiceAsync;
	}

	public void setclasspageServiceAsync(
			ClasspageServiceAsync classpageServiceAsync) {
		this.classpageServiceAsync = classpageServiceAsync;
	}*/

	public SimpleAsyncCallback<Void> getSocialShareAsyncCallback() {
		return socialShareAsyncCallback;
	}

	public void setSocialShareAsyncCallback(
			SimpleAsyncCallback<Void> socialShareAsyncCallback) {
		this.socialShareAsyncCallback = socialShareAsyncCallback;
	}
	public class CheckProfanityInOnBlur implements BlurHandler{
		private TextBox textBox;
		private Label label;
		private RichTextArea richTextArea;
		public CheckProfanityInOnBlur(TextBox textBox,RichTextArea richTextArea,Label label){
			this.textBox=textBox;
			this.label=label;
			this.richTextArea=richTextArea;
		}
		@Override
		public void onBlur(BlurEvent event) {
			Map<String, String> parms = new HashMap<String, String>();
			if(textBox!=null){
				parms.put("text", textBox.getValue());
			}else{
				parms.put("text", richTextArea.getText());
			}
			btnSend.setEnabled(false);
			AppClientFactory.getInjector().getResourceService().checkProfanity(parms, new SimpleAsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean value) {
					btnSend.setEnabled(true);
					if(textBox!=null){
						isHavingBadWordsInTextbox = value;
						SetStyleForProfanity.SetStyleForProfanityForTextBox(textBox, label, value);
					}else{
						isHavingBadWordsInRichText=value;
						SetStyleForProfanity.SetStyleForProfanityForRichTextArea(richTextArea, label, value);
					}
					
				}
			});
		}
	}
	
	@UiHandler("fromTxt")
	public void fromTxtKeyUpEvent(KeyUpEvent event){
		String fromTxtText=fromTxt.getText();
		if(fromTxtText.length()>=50){
			//fromValidation.setVisible(true);
			
		}else{
			fromValidation.setVisible(false);
		}
	}
	
}
