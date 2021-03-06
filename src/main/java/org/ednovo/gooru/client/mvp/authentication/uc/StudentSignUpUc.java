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
package org.ednovo.gooru.client.mvp.authentication.uc;

import org.ednovo.gooru.client.SimpleAsyncCallback;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.authentication.SignUpCBundle;
import org.ednovo.gooru.client.mvp.faq.CopyRightPolicyVc;
import org.ednovo.gooru.client.mvp.faq.TermsAndPolicyVc;
import org.ednovo.gooru.client.mvp.faq.TermsOfUse;
import org.ednovo.gooru.client.mvp.search.event.SetHeaderEvent;
import org.ednovo.gooru.client.mvp.search.event.SetHeaderZIndexEvent;
import org.ednovo.gooru.client.uc.DateBoxUc;
import org.ednovo.gooru.client.uc.ErrorLabelUc;
import org.ednovo.gooru.client.uc.TextBoxWithPlaceholder;
import org.ednovo.gooru.client.ui.HTMLEventPanel;
import org.ednovo.gooru.client.util.MixpanelUtil;
import org.ednovo.gooru.shared.model.user.UserDo;
import org.ednovo.gooru.shared.util.MessageProperties;
import org.ednovo.gooru.shared.util.StringUtil;

import com.google.debugging.sourcemap.dev.protobuf.Message;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @fileName : StudentSignUpUc.java
 *
 * @description : 
 *
 *
 * @version : 1.0
 *
 * @date: Dec 9, 2013
 *
 * @Author Gooru Team
 *
 * @Reviewer:
 */
public class StudentSignUpUc extends PopupPanel implements MessageProperties{

	private static StudentSignUpUcUiBinder uiBinder = GWT
			.create(StudentSignUpUcUiBinder.class);

	interface StudentSignUpUcUiBinder extends UiBinder<Widget, StudentSignUpUc> {
	}
	@UiField(provided = true)
	SignUpCBundle res;
	@UiField Label lblCancel,lblTitle,lblStuDes,lblParentEmailId,lblTxtParent,lblPickWisely,lblQuestionMark,lblWhyEnterBirthday,lblWhyEnterBirthdayDesc;
	@UiField SimplePanel sPanelDateOfBirth;

	@UiField
	ErrorLabelUc dateValidationUc,lblSelectRole,passwordValidUc;
	@UiField HTMLPanel panelUserNamePopUp;
	@UiField TextBoxWithPlaceholder txtChooseUsername,txtChoosePassword,txtConfirmPassword;
	@UiField Label lblTeacher,lblStudent, lblParent, lblOther,lblPasswordTooltipContent,lblPleaseWait;
	@UiField HTMLPanel rdTeacher, rdStudent, rdParent, rdOther,panelPassword;
	@UiField Button btnSignUp;
	@UiField Anchor ancCopyRight, ancTermsAndPrivacy,ancPrivacy;
	@UiField HTMLEventPanel panelDataOfBirth;
	@UiField InlineLabel lblAgree,andText;
	
	private String privateGooruUId = null;
	
	private String homeEndPoint = null;
	
	private static final String PARENT = "Parent";
	
	private static final String BIRTH_DAY = "birthday";
	
	private static final String GOORU_UID = "gooruUid";
	
	private static final String ACCOUNT_TYPE = "accountType";
	
	private static final String DATE_OF_BIRTH = "dateOfBirth";
	
	private static final String FIRST_NAME = "firstName";
	
	private static final String USER_NAME = "username";
	
	private static final String PASSWORD = "password";
	
	private static final String EMAIL_ID = "emailId";
	
	private static final String ORGANIZATION_CODE = "organizationCode";
	
	private static final String LAST_NAME = "lastName";
	
	private static final String GOORU = "gooru";
	
	RadioButton rbTeacher;
	RadioButton rbStudent;
	RadioButton rbParent;
	RadioButton rbOther;
	private String selectedRole = null;
	private static final String PWD_PATTERN = "[0-9]|[$@!#*%^/[/]}{()_&-+=.,<>;\\|]";
	
	private DateBoxUc dateBoxUc;
	/**
	 * 
	 * @param emailId
	 * @param username
	 * @param dob
	 */
	public StudentSignUpUc(String emailId,String username,String dob, String privateGooruUId) {
		super(false);
		this.res = SignUpCBundle.INSTANCE;
		res.css().ensureInjected();
		this.privateGooruUId = privateGooruUId;
		setWidget(uiBinder.createAndBindUi(this));
		this.setGlassEnabled(true);
		this.center();
		Window.enableScrolling(false);
		AppClientFactory.fireEvent(new SetHeaderZIndexEvent(98, false));
		this.addStyleName(SignUpCBundle.INSTANCE.css().popupBackground());
		this.setGlassStyleName(SignUpCBundle.INSTANCE.css().signUpPopUpGlassCss());
	
		//this.getElement().getStyle().setBackgroundColor("transparent");
		lblTitle.getElement().setAttribute("style", "height: 17px");
		lblParentEmailId.setText(emailId != null && !emailId.equalsIgnoreCase("") ? emailId : AppClientFactory.getPlaceManager().getRequestParameter("emailId"));
		txtChooseUsername.setText(username);
		txtChooseUsername.setReadOnly(true);
		lblPleaseWait.setVisible(false);
		setUiAndIds(dob);
		//this.setSize("500px","500px");
		txtChoosePassword.setFocus(true);
		
		AppClientFactory.getInjector().getSearchService().getHomeEndPointUrl(new SimpleAsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				homeEndPoint = result;
			}
		});
		
	}
	@UiHandler("lblCancel")
	public void onClickLblCancel(ClickEvent event){
		this.hide();
		LeaveRegistrationPopUpUc leaveRegistrationPopUpUc=new LeaveRegistrationPopUpUc(GL1394,lblParentEmailId.getText(),txtChooseUsername.getValue(),dateBoxUc.getDateBox().getText());
		leaveRegistrationPopUpUc.show();
	}
	/**
	 * 
	 * @function setUiAndIds 
	 * 
	 * @created_date : Dec 9, 2013
	 * 
	 * @description
	 * 
	 * 
	 * @parm(s) : @param dob
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 * 
	 *
	 *
	 */
	private void setUiAndIds(String dob){
		lblStuDes.setText(GL0467);
		lblTitle.setText(GL0186 + GL_SPL_EXCLAMATION);
		
		lblTxtParent.setText(GL0468);
		
		dateBoxUc = new DateBoxUc(true, true,true);
		sPanelDateOfBirth.add(dateBoxUc);
		dateBoxUc.getDateBox().setText(dob);
		panelUserNamePopUp.setVisible(false);
		panelPassword.setVisible(false);
		lblPickWisely.setText(GL0410);
		txtChoosePassword.setPlaceholder(GL0204);
		txtConfirmPassword.setPlaceholder(GL0427);
		lblQuestionMark.setText(GL_SPL_QUESTION);
		lblWhyEnterBirthday.setText(GL0411 + GL_SPL_QUESTION);
		lblWhyEnterBirthdayDesc.setText(GL0412);
		lblTeacher.setText(GL0416);
		lblStudent.setText(GL0417);
		lblParent.setText(GL0418);
		lblOther.setText(GL0419);
		rbTeacher = new RadioButton("roleOption","");
		rbStudent = new RadioButton("roleOption","");
		rbParent = new RadioButton("roleOption","");
		rbOther = new RadioButton("roleOption","");
		btnSignUp.setText(GL0186);
		btnSignUp.getElement().setId("btnSignUp");
		lblAgree.setText(GL0420);
		ancCopyRight.setText(GL0421+",");
		ancTermsAndPrivacy.setText(GL0422);
		ancPrivacy.setText(GL0452);
		lblPasswordTooltipContent.setText(GL0415);
		rbStudent.setChecked(true);
		MixpanelUtil.select_student();
		lblSelectRole.setText(GL1146);
		andText.setText(GL_GRR_AND);
		lblSelectRole.setVisible(false);
		
		lblPleaseWait.setText(GL0469);
		
		txtChooseUsername.addMouseOverHandler(new OnMouseOver());
		txtChoosePassword.addMouseOverHandler(new OnMouseOver());
		txtConfirmPassword.addMouseOverHandler(new OnMouseOver());
		panelDataOfBirth.addMouseOverHandler(new OnMouseOver());
		
		txtChooseUsername.addMouseOutHandler(new OnMouseOut());
		txtChoosePassword.addMouseOutHandler(new OnMouseOut());
		txtConfirmPassword.addMouseOutHandler(new OnMouseOut());
		panelDataOfBirth.addMouseOutHandler(new OnMouseOut());
		
		txtChoosePassword.addKeyUpHandler(new OnKeyUpHandler());
		txtConfirmPassword.addKeyUpHandler(new OnKeyUpHandler());
		
		rbParent.setEnabled(false);
		rbTeacher.setEnabled(false);
		rbOther.setEnabled(false);
		rdTeacher.add(rbTeacher);
		rdStudent.add(rbStudent);
		rdParent.add(rbParent);
		rdOther.add(rbOther);
	
	}
	private TermsAndPolicyVc termsAndPolicyVc;
	private CopyRightPolicyVc copyRightPolicy;
	private TermsOfUse termsOfUse;

	
	@UiHandler("ancTermsAndPrivacy")
	public void onClickTrems(ClickEvent event){
		Window.enableScrolling(false);
		termsOfUse = new TermsOfUse() {
			@Override
			public void openParentPopup() {
				Window.enableScrolling(false);
				AppClientFactory.fireEvent(new SetHeaderZIndexEvent(98,false));
			}
		};
		
		termsOfUse.show();
		termsOfUse.setSize("902px", "300px");
		termsOfUse.center();
		termsOfUse.getElement().getStyle().setZIndex(999);
	
	}
	
	/**
	 * Opens up Privacy pop-up.
	 * 
	 * @param event instance of {@link ClickEvent}
	 */
	
	@UiHandler("ancPrivacy")
	public void onClickPrivacy(ClickEvent event){
		Window.enableScrolling(false);
		termsAndPolicyVc = new TermsAndPolicyVc(false) {
			@Override
			public void openParentPopup() {
				Window.enableScrolling(false);
				AppClientFactory.fireEvent(new SetHeaderZIndexEvent(98,false));
			}
		};
		
		termsAndPolicyVc.show();
		termsAndPolicyVc.setSize("902px", "300px");
		termsAndPolicyVc.center();
		termsAndPolicyVc.getElement().getStyle().setZIndex(999);
	
	}
	
	/**
	 * Opens up Copy rights pop-up.
	 * 
	 * @param event instance of {@link ClickEvent}
	 */
	
	@UiHandler("ancCopyRight")
	public void onClickCopyright(ClickEvent event){
		Window.enableScrolling(false);
		copyRightPolicy = new  CopyRightPolicyVc() {
			@Override
			public void openParentPopup() {
				Window.enableScrolling(false);
				AppClientFactory.fireEvent(new SetHeaderZIndexEvent(98,false));
			}
		};
		
		copyRightPolicy.show();
		copyRightPolicy.setSize("902px", "300px");
		copyRightPolicy.center();
		copyRightPolicy.getElement().getStyle().setZIndex(999);
	}
	
	private class OnMouseOver implements MouseOverHandler{

		@Override
		public void onMouseOver(MouseOverEvent event) {
			if (event.getSource() ==  txtChooseUsername || event.getSource() == panelDataOfBirth){
				panelUserNamePopUp.setVisible(true);
			}else if (event.getSource() == txtChoosePassword || event.getSource() == txtConfirmPassword){
				panelPassword.setVisible(true);
			}	
		}
	}
	private class OnMouseOut implements MouseOutHandler{

		@Override
		public void onMouseOut(MouseOutEvent event) {
			panelUserNamePopUp.setVisible(false);
			panelPassword.setVisible(false);
		}
		
	}
	/**
	 * 
	 * @function validateUserInput 
	 * 
	 * @created_date : Dec 9, 2013
	 * 
	 * @description
	 * 
	 * 
	 * @parm(s) : @return
	 * 
	 * @return : boolean
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 * 
	 *
	 *
	 */
	public boolean validateUserInput(){
		boolean isValid = true;
		String userName = txtChooseUsername.getText().trim();
		String password = txtChoosePassword.getText().trim();
		String confirmPassword = txtConfirmPassword.getText().trim();
	
		if (userName.equalsIgnoreCase("") || userName == null){
			txtChooseUsername.addStyleName(res.css().errorMsgDisplay());
			isValid= false;
		}
		if (password.equalsIgnoreCase("") || password == null){
			txtChoosePassword.addStyleName(res.css().errorMsgDisplay());
			isValid= false;
		}
		if (confirmPassword.equalsIgnoreCase("") || confirmPassword == null){
			txtConfirmPassword.addStyleName(res.css().errorMsgDisplay());
			isValid= false;
		}
		
		//TODO Validate Password fields are match each other.
		if (!password.equalsIgnoreCase(confirmPassword)){
			txtConfirmPassword.addStyleName(res.css().errorMsgDisplay());
			txtChoosePassword.addStyleName(res.css().errorMsgDisplay());
			passwordValidUc.setText(GL0446);
			passwordValidUc.setVisible(true);
			isValid= false;
		}
		
		RegExp reg = RegExp.compile(PWD_PATTERN, "gi");
		boolean validatePwd=reg.test(password);
		if (!validatePwd && password.length() >= 5 && password.length() <= 14){
			passwordValidUc.setText(StringUtil.generateMessage(GL0073, "Password"));
			passwordValidUc.setVisible(true);
			isValid = false;
		}
		return isValid;

	}
	private class OnKeyUpHandler implements KeyUpHandler {

		@Override
		public void onKeyUp(KeyUpEvent event) {
			passwordValidUc.setVisible(false);
			if (event.getSource() == txtChoosePassword) {
				txtChoosePassword.removeStyleName(res.css().errorMsgDisplay());
			} else if (event.getSource() == txtConfirmPassword) {
				txtConfirmPassword.removeStyleName(res.css().errorMsgDisplay());
			}
		}
	}
	@UiHandler("btnSignUp")
	public void onClickSignUp(ClickEvent event){
		lblPleaseWait.setVisible(true);
		btnSignUp.setVisible(false);
		if (validateUserInput()){
			checkUserNameAvailability();
		}else{
			lblPleaseWait.setVisible(false);
			btnSignUp.setVisible(true);
		}
	}
	/**
	 * 
	 * @function checkUserNameAvailability 
	 * 
	 * @created_date : Dec 9, 2013
	 * 
	 * @description
	 * 
	 * 
	 * @parm(s) : 
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 * 
	 *
	 *
	 */
	public void checkUserNameAvailability() {
		MixpanelUtil.sign_up_Child_registration();
		String userRole = "student";

		String userName = txtChooseUsername.getText();
		String emilId = lblParentEmailId.getText();
		String password = txtChoosePassword.getText().trim();
		String confirmPassword = txtConfirmPassword.getText().trim();
		
		final JSONObject userCreate = new JSONObject();
		JSONObject user = new JSONObject();

		user.put(USER_NAME, new JSONString(userName));
		user.put(EMAIL_ID, new JSONString(emilId));
		
		JSONObject organization = new JSONObject();
		organization.put(ORGANIZATION_CODE, new JSONString(GOORU));
		user.put("organization", organization);
		
		user.put(FIRST_NAME, new JSONString("Child"));
		user.put(LAST_NAME, new JSONString("User"));
//			userCreate.put("gender", new JSONString("Male"));
		userCreate.put(PASSWORD, new JSONString(password));
		userCreate.put("gooruBaseUrl", new JSONString(homeEndPoint + "#discover"));
		userCreate.put("role", new JSONString(userRole));
		userCreate.put("dateOfBirth", new JSONString(dateBoxUc.getDateBox().getText()));
		userCreate.put("accountType", new JSONString("Child"));
		userCreate.put("userParentId", new JSONString(AppClientFactory.isAnonymous() ? privateGooruUId : AppClientFactory.getLoggedInUser().getGooruUId()));
		
		userCreate.put("user", user);
		
		final JSONObject login = new JSONObject();
		login.put("username", new JSONString(userName));
		login.put("password", new JSONString(password));
		
		
		AppClientFactory.getInjector().getUserService().createUser(userCreate.toString(),new SimpleAsyncCallback<UserDo>() {
				@Override
				public void onSuccess(UserDo result) {
					if (result.getGooruUId() != null && !result.getGooruUId().equalsIgnoreCase("")) {
						AppClientFactory.getInjector().getAppService().v2Signin(login.toString(),new SimpleAsyncCallback<UserDo>() {
							@Override
							public void onSuccess(UserDo result) {
								hide();
								AppClientFactory.setLoggedInUser(result);
								AppClientFactory.fireEvent(new SetHeaderEvent(result));
								SignUpGradeCourseView gradeCourseView = new SignUpGradeCourseView(result);
							}
						});
					}
				}
			});
	}
}
