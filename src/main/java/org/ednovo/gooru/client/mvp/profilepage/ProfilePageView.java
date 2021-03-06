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
package org.ednovo.gooru.client.mvp.profilepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ednovo.gooru.client.PlaceTokens;
import org.ednovo.gooru.client.SimpleAsyncCallback;
import org.ednovo.gooru.client.effects.FadeInAndOut;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.gin.BaseViewWithHandlers;
import org.ednovo.gooru.client.mvp.home.FooterUc;
import org.ednovo.gooru.client.mvp.profilepage.content.PPPCollectionResult;
import org.ednovo.gooru.client.mvp.profilepage.tab.content.item.ProfilePageItemChildView;
import org.ednovo.gooru.client.mvp.search.SearchResultWrapperCBundle;
import org.ednovo.gooru.client.mvp.settings.UserSettingStyle;
import org.ednovo.gooru.client.mvp.shelf.ShelfCBundle;
import org.ednovo.gooru.client.mvp.shelf.collection.CollectionCBundle;
import org.ednovo.gooru.client.mvp.socialshare.SocialShareView;
import org.ednovo.gooru.client.uc.AlertContentUc;
import org.ednovo.gooru.client.uc.CloseLabel;
import org.ednovo.gooru.client.uc.DownToolTipWidgetUc;
import org.ednovo.gooru.client.uc.ProfilePageDescriptionEditUc;
import org.ednovo.gooru.client.uc.ProfilePageGradeLabel;
import org.ednovo.gooru.client.uc.tooltip.GlobalToolTip;
import org.ednovo.gooru.client.ui.HTMLEventPanel;
import org.ednovo.gooru.client.util.MixpanelUtil;
import org.ednovo.gooru.client.util.SetStyleForProfanity;
import org.ednovo.gooru.shared.model.code.CodeDo;
import org.ednovo.gooru.shared.model.code.LibraryCodeDo;
import org.ednovo.gooru.shared.model.code.ProfileCodeDo;
import org.ednovo.gooru.shared.model.content.CollectionItemDo;
import org.ednovo.gooru.shared.model.social.SocialShareDo;
import org.ednovo.gooru.shared.model.user.ProfileDo;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.tractionsoftware.gwt.user.client.ui.GroupedListBox;

public class ProfilePageView extends BaseViewWithHandlers<ProfilePageUiHandlers> implements IsProfilePageView,MessageProperties {
	@UiField
	Anchor /* shareTabVc, */contentTabVc;

	@UiField
	Label userName, userBio, aboutUsCharacterValidation, courseMaxMsg,profilePageViewMsg,mySubjectsText;
	
	@UiField Label cancelBtn,gradeText,courseLabel,profilePageText,shareWithOthersText;

	@UiField
	Image userProfilePic,errorImage;

	@UiField
	HTMLPanel profileOnContainerPanel, profileOffContainerPanel;

	@UiField
	SimplePanel shelfTabSimPanel;

	@UiField
	HTMLPanel publicPPRightContainer, contentview, shareLinkFloPanel,
			contentNavigationPanel, socialButtonContainer, bioMainContainer;

	@UiField
	HTMLPanel loadingPanel, userGradeList, userCourseList, metaDataContainer;

	@UiField
	HTMLEventPanel profileImageContainer, profileDescriptionlabel,
			pencilTextAreaImage, userCoursePopup,
			userMetadata, editPencil;

	@UiField
	Button /*editMyPage,*/ profileOnButton, profileOffButton, btnSave,
			addCourseBtn, saveBtn, addBioBtn, addCourseGradeBtn,biographyCancelButton;

	@UiField
	HTMLPanel gooruSocialButtonsContainer, gooruProfileOnOffContainer,
			profilePageEditBioPanel,aboutMeTextContainer;
	
	@UiField FlowPanel moreGradeCourseLbl;
	
	@UiField
	UserSettingStyle settingsStyle;

	@UiField
	TextBox bitlyLink;

	@UiField(provided = true)
	ProfilePageDescriptionEditUc profileTextArea;

	@UiField
	FocusPanel noAboutUsContainer;

	@UiField
	FlowPanel gradeTopList, gradeMiddleList, KinderGarten, higherEducation;

	@UiField
	FlowPanel courseData, collectionCourseLstPanel, coursesPanel;

	Label noCollectionMsgPanel = new Label();

	private Label editImageButton = new Label(GL0800);

	final private String WORKSPACE_FOLDER = "folder";

	final private String WORKSPACE_COLLECTION = "scollection";

	final private int DEFAULT_PROFILE_LIST_VIEW_HEIGHT = 300;

	private FooterUc footerUc = new FooterUc();
	
	private AlertContentUc alertContentUc;
	
	private PopupPanel toolTipPopupPanel=new PopupPanel();
	
	private SocialShareView socialView=null;
	
	boolean enableEdit = true;

	@UiField(provided = true)
	ShelfCBundle res;

	@UiField(provided = true)
	CollectionCBundle ccb;

	/* HTML5 Storage implementation for tab persistance */
	private Storage stockStore = null;

    PPPCollectionResult pppCollectionResult;

    boolean isProfileAdmin = false;
	
	private boolean isShowing=false;

	private ProfileDo profileDo;

	private GroupedListBox collectionCourseLst;

	private SocialShareDo shareDo;
	
	private String profileOnStatus = "false";
	
	private static String USER_META_ACTIVE_FLAG = "1";
	
	private static int RENDER_MARGIN_WIDTH = 4;
	
	private boolean isGradeCourseBtnClick = false;
	
	private String profileImageUrl="images/profilepage/user-profile-pic.png";
	
	private static ProfilePageViewUiBinder uiBinder = GWT
			.create(ProfilePageViewUiBinder.class);

	interface ProfilePageViewUiBinder extends UiBinder<Widget, ProfilePageView> {
	}

	@Inject
	public ProfilePageView() {
		this.res = ShelfCBundle.INSTANCE;
		res.css().ensureInjected();
		ccb = CollectionCBundle.INSTANCE;
		CollectionCBundle.INSTANCE.css().ensureInjected();
		profileTextArea = new ProfilePageDescriptionEditUc() {
			@Override
			public void onEditDisabled(String text) {
				aboutUsCharacterValidation.setVisible(false);
				btnSave.setVisible(false);
				biographyCancelButton.setVisible(false);
				if (text.isEmpty()) {
					enableAddBioBtn("addBioBtn");
				}else{
					addBioBtn.setVisible(false);
				}
					
				userBio.setText(text);
				getUiHandlers().updateUserBiography(profileDo.getUser().getGooruUId(), text,profileDo.getUserType(),profileDo.getUser().getFirstName(),
						profileDo.getUser().getLastName(),profileDo.getGender().getGenderId());
			}

			@Override
			public void checkCharacterLimit(String text) {
				if (text.length() >= 500) {
					aboutUsCharacterValidation.setVisible(true);
				} else {
					aboutUsCharacterValidation.setVisible(false);
				}
			}
		};
		profileTextArea.getElement().getStyle().setWidth(650, Unit.PX);

		setWidget(uiBinder.createAndBindUi(this));
		mySubjectsText.setText(GL1074);
		addCourseGradeBtn.setText(GL1075);
		editPencil.getElement().setInnerHTML(GL0140);
		gradeText.setText(GL1076);
		courseLabel.setText(GL0574);
		addCourseBtn.setText(GL0590);
		courseMaxMsg.setText(GL0822);
		saveBtn.setText(GL0141);
		cancelBtn.setText(GL0142);
		aboutMeTextContainer.getElement().setInnerHTML(GL1077);
		addBioBtn.setText(GL0140);
		aboutUsCharacterValidation.setText(GL0143);
		btnSave.setText(GL0141);
		biographyCancelButton.setText(GL0142);
		profilePageViewMsg.setText(GL1078);
		profilePageText.setText(GL1079);
		profileOnButton.setText(GL0802);
		profileOffButton.setText(GL0803);
		shareWithOthersText.setText(GL1080);
		bitlyLink.setText(GL1081);
		contentTabVc.setText(GL1082);
		errorImage.setTitle(GL1091_1);
		errorImage.setAltText(GL1091_1);
		errorImage.setUrl("images/404_message.png");
		profileOnContainerPanel.setVisible(false);
		profileOffContainerPanel.setVisible(false);
		loadingPanel.setVisible(true);
		addCourseGradeBtn.getElement().setId("btnAddCourseGrade");
		addCourseBtn.getElement().setId("btnAddCourse");
		editImageButton.getElement().setId("btnEditImage");
		int clientHeight = Window.getClientHeight();
		clientHeight = clientHeight - DEFAULT_PROFILE_LIST_VIEW_HEIGHT;
		contentNavigationPanel.getElement().getStyle().setHeight(clientHeight, Unit.PX);
		publicPPRightContainer.getElement().getStyle().setHeight(clientHeight, Unit.PX);
		editImageButton.setStyleName(CollectionCBundle.INSTANCE.css().profileEditImageButton());
		userCoursePopup.setVisible(false);
		profileOnButton.getElement().setAttribute("id", "btnProfileOn");
		profileOffButton.getElement().setAttribute("id", "btnProfileOff");
		contentTabVc.removeStyleName(res.css().tabsLi());
		contentTabVc.setStyleName(res.css().tabsLiActive());
		contentTabVc.getElement().setId("lnkContentTab");
		shareLinkFloPanel.setVisible(false);
		profilePageEditBioPanel.setVisible(false);
		editImageButton.setVisible(false);
		addCourseGradeBtn.setVisible(false);
		enableAddBioBtn("userBio");
		addBioBtn.getElement().setId("btnBioEdit");
		bitlyLink.getElement().setId("txtBitlyLink");

		if(AppClientFactory.getLoggedInUser().getConfirmStatus()==1){
			profileOnButton.addClickHandler(new ProfileOnClickEvent());
		 }else{
			profileOnButton.addMouseOverHandler(new MouseOverHandler() {
				
				@Override
				public void onMouseOver(MouseOverEvent event) {
					toolTipPopupPanel.clear();
					toolTipPopupPanel.setWidget(new GlobalToolTip());
					toolTipPopupPanel.setStyleName("");
					toolTipPopupPanel.setPopupPosition(profileOnButton.getElement().getAbsoluteLeft()+10, profileOnButton.getElement().getAbsoluteTop()+4);
					toolTipPopupPanel.show();
				}
			});
			profileOnButton.addMouseOutHandler(new MouseOutHandler() {
				
				@Override
				public void onMouseOut(MouseOutEvent event) {
					toolTipPopupPanel.hide();
				}
			});
			
		}
		contentTabVc.getElement().getStyle().setCursor(Cursor.POINTER);

		userCoursePopup.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				isShowing=true;
				
			}
		});
		ClickHandler eve1=new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(userCoursePopup.isVisible() && isShowing && enableEdit){
						userCoursePopup.setVisible(true);
				}else{
					userCoursePopup.setVisible(false);	
				}
				isShowing=false;
			}
		};
		RootPanel.get().addDomHandler(eve1, ClickEvent.getType());
		
	}

	public class ProfileOnClickEvent implements ClickHandler  {

		@Override
		public void onClick(ClickEvent event) {
			MixpanelUtil.Click_On();
			enableProfileButton(true);
			shareDo.setShareType("public");
			socialView = new SocialShareView(shareDo);
			socialButtonContainer.clear();
			socialButtonContainer.add(socialView);
			bitlyLink.removeStyleName(ShelfCBundle.INSTANCE.css().shareLinkBoxDisabled());
			gooruSocialButtonsContainer.getElement().getStyle().setOpacity(1.0);
			
			//update profile setting
			getUiHandlers().updateProfileVisibilitySetting("true");
		}
		
		
	}

	@UiHandler("profileOffButton")
	public void onClickOffButton(ClickEvent event) {
		MixpanelUtil.Click_Off();
		enableProfileButton(false);
		shareDo.setShareType("private");
		socialView = new SocialShareView(shareDo);
		socialButtonContainer.clear();
		socialButtonContainer.add(socialView);
		bitlyLink.addStyleName(ShelfCBundle.INSTANCE.css().shareLinkBoxDisabled());
		gooruSocialButtonsContainer.getElement().getStyle().setOpacity(0.6);

		//update profile setting
		getUiHandlers().updateProfileVisibilitySetting("false");
	}

	void enableProfileButton(boolean isToEnabled) {
		if (isToEnabled) {
			this.profileOnStatus = "true";
			MixpanelUtil.Click_Profile_On();
			profileOnButton.setStyleName(settingsStyle.publicProfileOnButtonActive());
			profileOnButton.removeStyleName(settingsStyle.publicProfileOnButtonDeActive());
			profileOffButton.setStyleName(settingsStyle.publicProfileOffButtonsDeActive());
			profileOffButton.removeStyleName(settingsStyle.publicProfileOffButtonsActive());
			setPublicShareDo("public");
		} else {
			this.profileOnStatus = "false";
			MixpanelUtil.Click_Profile_Off();
			profileOffButton.setStyleName(settingsStyle.publicProfileOffButtonsActive());
			profileOffButton.removeStyleName(settingsStyle.publicProfileOffButtonsDeActive());
			profileOnButton.setStyleName(settingsStyle.publicProfileOnButtonDeActive());
			profileOnButton.removeStyleName(settingsStyle.publicProfileOnButtonActive());
			setPublicShareDo("private");
		}
	}

	public void setInitialProfileStatus(String isEnabled) {
		if(isEnabled == "true") {
			setPublicShareDo("public");
		} else if(isEnabled == "false"){
			if(enableEdit==true) {
				setPublicShareDo("private");
			} else {
				setPublicShareDo("public");
			}
		}
	}
	
	private void setPublicShareDo(String privatePublic) {
		try {
			shareDo.setShareType(privatePublic);
			socialView = new SocialShareView(shareDo);
			socialButtonContainer.clear();
			socialButtonContainer.add(socialView);
			if(privatePublic.equalsIgnoreCase("private")) {
				bitlyLink.addStyleName(ShelfCBundle.INSTANCE.css().shareLinkBoxDisabled());
				gooruSocialButtonsContainer.getElement().getStyle().setOpacity(0.6);
			} else {
				bitlyLink.removeStyleName(ShelfCBundle.INSTANCE.css().shareLinkBoxDisabled());
				gooruSocialButtonsContainer.getElement().getStyle().setOpacity(1.0);
			}
		} catch (Exception e) {
		}
		

	}
	
	@Override
	public void setInSlot(Object slot, Widget content) {
		if (content != null) {
			if (slot == ProfilePageUiHandlers.TYPE_PUBLIC_SHELF_VIEW) {
				shelfTabSimPanel.setWidget(content);
			}
		}
	}

	@Override
	public void setProfileData(final ProfileDo profileDo) {
		if(profileDo.getAboutMe()==null) {
			profileDo.setAboutMe("");
		}
		this.profileDo = profileDo;
		profileTextArea.cancel();
		btnSave.setVisible(false);
		biographyCancelButton.setVisible(false);
		moreGradeCourseLbl.clear();
		noCollectionMsgPanel.setText(profileDo.getUser().getUsernameDisplay()+" "+GL1083);
		userName.setText(profileDo.getUser().getUsernameDisplay());
		userBio.setText(profileDo.getAboutMe());
		profileTextArea.setText(profileDo.getAboutMe());
		profileImageUrl=profileDo.getUser().getProfileImageUrl() + "?p="+ Math.random();
		userProfilePic.setUrl(profileImageUrl);
		userProfilePic.setAltText(profileDo.getUser().getUsername());
		userProfilePic.setTitle(profileDo.getUser().getUsername());
		userProfilePic.getElement().getStyle().setHeight(100, Unit.PX);
		userProfilePic.getElement().getStyle().setWidth(100, Unit.PX);
		userProfilePic.addErrorHandler(new ErrorHandler() {
			@Override
			public void onError(ErrorEvent event) {
				userProfilePic.setUrl("images/profilepage/user-profile-pic.png");
				userProfilePic.setAltText(profileDo.getUser().getUsername());
				userProfilePic.setTitle(profileDo.getUser().getUsername());
			}
		});
		getUiHandlers().setShareView();
		gooruSocialButtonsContainer.setVisible(true);
		setUserGradeList(profileDo.getGrade());
		setUserCourseList(profileDo.getCourses());
		setMetaDataContainerWidth();
		setAddGradeCourseBtnVisibility();
		getEnableWidget(enableEdit,profileDo.getAboutMe(),profileDo.getCourses());
		
	}

	private void setUserCourseList(Set<ProfileCodeDo> codeList) {
		profileDo.setCourses(codeList);
		userCourseList.clear();
		for (ProfileCodeDo profileCodeDo : codeList) {
			Label courseLabel = new Label(profileCodeDo.getCode().getLabel());
			courseLabel.addClickHandler(new OnGradeEditImageClick());
			courseLabel.setStyleName(CollectionCBundle.INSTANCE.css().userCourseName());
			userGradeList.add(courseLabel);
		}
	}

	private void setUserGradeList(String grade) {
		profileDo.setGrade(grade);
		userGradeList.clear();

	if(grade!=null && !grade.isEmpty()) {
		boolean isHigherEducation = false;
		if(grade.contains("Kindergarten")) {
			Label gradeLabel = new Label(GL0850);
			grade = grade.replaceAll("Kindergarten", "");
			gradeLabel.setStyleName(CollectionCBundle.INSTANCE.css().userNumber());
			gradeLabel.addClickHandler(new OnGradeEditImageClick());
			userGradeList.add(gradeLabel);
		}
		if(grade.contains("Higher Education")) {
			isHigherEducation = true;
			grade = grade.replaceAll("Higher Education", "");
		}
		
		String[] grades = grade.split(",");
		Set set= new HashSet();
		for(int i = 0; i < grades.length; i ++) {
					if(!grades[i].isEmpty()) {
						set.add(Integer.parseInt(grades[i]));
					}
		}
		Iterator gradeLbl = set.iterator();
		while(gradeLbl.hasNext()) {
				Label gradeLabel = new Label(gradeLbl.next().toString());
				gradeLabel.setStyleName(CollectionCBundle.INSTANCE.css().userNumber());
				gradeLabel.addClickHandler(new OnGradeEditImageClick());
				userGradeList.add(gradeLabel);
		}
		
		if(isHigherEducation == true) {
			Label gradeLabel = new Label(GL0169);
			gradeLabel.setStyleName(CollectionCBundle.INSTANCE.css().userNumber());
			gradeLabel.addClickHandler(new OnGradeEditImageClick());
			userGradeList.add(gradeLabel);
		}
		}
	}

	public void setMetaDataContainerWidth() {
		int gradeContainerWidth = userGradeList.getOffsetWidth();
//		int courseContainerWidth = userCourseList.getOffsetWidth();
//		int metaDataContainerWidth = metaDataContainer.getOffsetWidth();
		boolean isValue=true;
		List<String> moreGradeCourseLbls = new ArrayList<String>();
		
			int gradeWidth = 0;
			Iterator<Widget> gradeWidgets = userGradeList.iterator();
			while (gradeWidgets.hasNext()) {
				Widget widget = gradeWidgets.next();
				if (widget instanceof Label) {
					if((gradeContainerWidth - gradeWidth)>widget.getOffsetWidth()) {
						if(isValue){
							gradeWidth = gradeWidth + widget.getOffsetWidth() + RENDER_MARGIN_WIDTH;
						}else {
							isValue=false;
							moreGradeCourseLbls.add(((Label) widget).getText());
						}
					} else {
						isValue=false;
						moreGradeCourseLbls.add(((Label) widget).getText());
					}
				}
			}
			renderExtraGradeCourse(moreGradeCourseLbls);
	}
	
	public void renderExtraGradeCourse(List<String> datas) {
		if (datas.size() > 0) {
			FlowPanel toolTipwidgets = new FlowPanel();
			for (int count = 0; count < datas.size(); count++) {
				Label label = new Label(datas.get(count));
				label.setStyleName(SearchResultWrapperCBundle.INSTANCE.css().moreMetaLbl());
				toolTipwidgets.add(label);
			}
			DownToolTipWidgetUc toolTipUc = new DownToolTipWidgetUc(new Label(GL_SPL_PLUS + datas.size() +" "+GL1152), toolTipwidgets);
			toolTipUc.setStyleName(SearchResultWrapperCBundle.INSTANCE.css().blueLinkPad());
			moreGradeCourseLbl.add(toolTipUc);
		}
	}

	public void setContentTabVisibility(boolean isVisible) {
		if (isVisible) {
			setContentViewCss();
			contentview.getElement().getStyle().setDisplay(Display.BLOCK);
			shareLinkFloPanel.getElement().getStyle().setDisplay(Display.NONE);
		}
	}

	private void setContentViewCss() {
		contentTabVc.removeStyleName(res.css().tabsLi());
		contentTabVc.setStyleName(res.css().tabsLiActive());
	}

	@UiHandler("contentTabVc")
	public void onContentTabVc(ClickEvent event) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", AppClientFactory.getPlaceManager().getRequestParameter("id"));
		params.put("user", AppClientFactory.getPlaceManager().getRequestParameter("user"));
		AppClientFactory.getPlaceManager().revealPlace(PlaceTokens.PROFILE_PAGE, params);
	}
	
	@Override
	public void setContentItemData(List<CollectionItemDo> collectionItemDo) {
		if (collectionItemDo.size() > 0) {

			Iterator<CollectionItemDo> iterator = collectionItemDo.iterator();
			while (iterator.hasNext()) {
				CollectionItemDo collectionItem = iterator.next();
				if (collectionItem.getResource().getResourceType().getName()
						.equalsIgnoreCase(WORKSPACE_FOLDER)) {
					publicPPRightContainer.add(new ProfilePageItemChildView(
							collectionItem));
				} else if (collectionItem.getResource().getResourceType()
						.getName().equalsIgnoreCase(WORKSPACE_COLLECTION)) {
					publicPPRightContainer.add(new PPPCollectionResult(
							collectionItem));
				}
			}
		} else {
			noCollectionMsgPanel.setStyleName(res.css()
					.noCollectionMessageRight());
			noCollectionMsgPanel.getElement().getStyle()
					.setHeight(300, Unit.PX);
			publicPPRightContainer.add(noCollectionMsgPanel);
			footerUc.getElement().getStyle().setMarginBottom(50, Unit.PX);
		}
		loadingPanel.setVisible(false);
		displayFooter();
		publicPPRightContainer.add(footerUc);
	}

	@Override
	public void clearContentItemData() {
		publicPPRightContainer.clear();
	}

	@Override
	public void setMetaData(CollectionItemDo collectionItemDo) {
		publicPPRightContainer.add(new ProfilePageCollectionMetaData(
				collectionItemDo));
	}

	@Override
	public void setShareData(ProfileDo profileDo, List<String> shortenUrl,
			String profileUrl) {

		bitlyLink.setReadOnly(true);
		bitlyLink.addClickHandler(new OnTextBoxClick());
		bitlyLink.setText(shortenUrl.get(2));
		/*profileUrl = "http://qa.goorulearning.org/gooru-gwt/" + "%23"
				+ profileUrl;
		profileUrl = profileUrl.replaceAll("#", "%23");
		profileUrl = profileUrl.replaceAll("&", "%26");
		profileUrl = profileUrl.replaceAll("!", "%21");*/

		shareDo = new SocialShareDo();
		shareDo.setBitlylink(shortenUrl.get(0));
		shareDo.setRawUrl(shortenUrl.get(1));
		shareDo.setTitle(profileDo.getUser().getUsername());
		shareDo.setDescription(GL1085
				+ profileDo.getUser().getUsername() + GL1086);
		shareDo.setThumbnailurl(profileImageUrl);
		shareDo.setCategoryType("profile");
		shareDo.setPppBitlylink(profileUrl);
		shareDo.setOnlyIcon(true);
		shareDo.setIsSearchShare(false);
		setInitialProfileStatus(profileOnStatus);
	}

	public class OnTextBoxClick implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			bitlyLink.selectAll();
			bitlyLink.setFocus(true);
		}
	}

	@Override
	public HTMLPanel getOnProfileContainer() {
		return profileOnContainerPanel;
	}

	@Override
	public HTMLPanel getOffProfileContainer() {
		return profileOffContainerPanel;
	}

	@Override
	public void setCollectionData(CollectionItemDo collectionItemDo) {
		PPPCollectionResult pppCollectionResult = new PPPCollectionResult(
				collectionItemDo);
		publicPPRightContainer.add(pppCollectionResult);
		pppCollectionResult.openDisclosurePanel();
		displayFooter();
		publicPPRightContainer.add(footerUc);
	}

	public void displayFooter() {
		footerUc.setFooterWidth();
		footerUc.getElement().getStyle().setMarginLeft(-119, Unit.PX);
		footerUc.getElement().getStyle().setDisplay(Display.BLOCK);
	}

	@Override
	public void showProfileView(boolean isVisible) {
		userName.setText("");
		userBio.setText("");
		userProfilePic.setUrl("");
		publicPPRightContainer.clear();
		shareLinkFloPanel.clear();
		bitlyLink.setText("");
		socialButtonContainer.clear();
		profileTextArea.setText("");
	}

	private class ShowImageUploadWidget implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			if (enableEdit){
				MixpanelUtil.Click_On_EditMyImage();
				getUiHandlers().showUploadImageWidget();
				editImageButton.getElement().getStyle().clearDisplay();
			}
		}
	}

	private class ShowEditButton implements MouseOverHandler {
		@Override
		public void onMouseOver(MouseOverEvent event) {
			if(enableEdit){
				editImageButton.getElement().getStyle().setDisplay(Display.BLOCK);
			}
		}
	}

	private class HideEditButton implements MouseOutHandler {
		@Override
		public void onMouseOut(MouseOutEvent event) {
				editImageButton.getElement().getStyle().clearDisplay();
		}
	}

	@Override
	public void setUserProfileImage(String imageUrl) {
		profileImageUrl=imageUrl + "?" + Math.random();
		userProfilePic.setUrl(profileImageUrl);
		shareDo.setThumbnailurl(profileImageUrl);
		if(socialView!=null){
			socialView.setShareDo(shareDo);
		}
	}

	@Override
	public void enableEditableData(String profileOnStatus) {
		this.profileOnStatus = profileOnStatus;
		isProfileAdmin = true;
		// Profile Image Edit option
		profileImageContainer.add(editImageButton);
		
		if(profileDo.getUser().getProfileImageUrl()==null) {
			editImageButton.setText(GL1087);
		}
		courseData.getElement().getStyle().setWidth(324, Unit.PX);
		saveBtn.getElement().getStyle().setFloat(Float.LEFT);
		saveBtn.getElement().setAttribute("id", "btnSave");
		cancelBtn.getElement().setAttribute("id", "btnCancel");
		
		biographyCancelButton.getElement().setAttribute("id", "btnCancelEditBio");
		
		editImageButton.addClickHandler(new ShowImageUploadWidget());
		profileImageContainer.addMouseOverHandler(new ShowEditButton());
		profileImageContainer.addMouseOutHandler(new HideEditButton());
		if (profileDo.getAboutMe()!=null && !profileDo.getAboutMe().equalsIgnoreCase("")){
			enableAddBioBtn("other");
		}else{
			enableAddBioBtn("addBioBtn");
		}
		gooruSocialButtonsContainer.setVisible(true);
		// On/Off button and Edit My Page Button
		//editMyPage.setVisible(true);
		profilePageEditBioPanelUpdate();
		if (profileOnStatus == "true" || profileOnStatus.equalsIgnoreCase("true")) {
			enableProfileButton(true);
		} else {
			enableProfileButton(false);
		}
	}

	private void profilePageEditBioPanelUpdate() {
		btnSave.getElement().setAttribute("id", "btnSaveEditBio");
		biographyCancelButton.getElement().setAttribute("id", "btnCancelEditBio");
		
		aboutUsCharacterValidation.setWidth("100%");
		aboutUsCharacterValidation.setVisible(false);
		noAboutUsContainer.setVisible(false);
		profileTextArea.getBiographyEditImage().setVisible(false);
		profileTextArea.getBiographyEditImage().getElement().setAttribute("style", "none");
		btnSave.setVisible(false);
		biographyCancelButton.setVisible(false);
		pencilTextAreaImage.setVisible(false);
		noAboutUsContainer.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				if (enableEdit){
					pencilTextAreaImage.setVisible(true);
				}
			}
		});
		noAboutUsContainer.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				if (enableEdit){
					pencilTextAreaImage.setVisible(false);
				}
			}
		});
		pencilTextAreaImage.addClickHandler(new OnPencilImageClick());
		profileTextArea.getBiographyEditImage().addClickHandler(new OnEditImageClick());
		profileTextArea.getBiographyEditImage().setVisible(false);
		profileDescriptionlabel.addClickHandler(new OnEditImageClick());
		profileDescriptionlabel.addMouseOverHandler(new hideEditPencil());
		profileDescriptionlabel.addMouseOutHandler(new showEditPencil());
		editPencil.setVisible(false);
		userCoursePopup.setVisible(false);
		editPencil.addClickHandler(new OnGradeEditImageClick());
		addCourseGradeBtn.getElement().getStyle().setWidth(163, Unit.PX);
		addCourseGradeBtn.getElement().getStyle().setMarginTop(-3, Unit.PX);
		addCourseGradeBtn.getElement().getStyle().setMarginLeft(9, Unit.PX);
		addCourseGradeBtn.addClickHandler(new OnGradeEditImageClick());
		editPencil.getElement().getStyle().setMarginTop(-3, Unit.PX);
		editPencil.getElement().getStyle().setMarginLeft(0, Unit.PX);
		userMetadata.addMouseOverHandler(new showGradeEditPencil());
		userMetadata.addMouseOutHandler(new hideGradeEditPencil());
		getProfileBiographyEditUC().setText(profileDo.getAboutMe());
	}

	private class OnEditImageClick implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			if (enableEdit){
				btnSave.setVisible(true);
				biographyCancelButton.setVisible(true);
				profileTextArea.switchToEdit();
			}
		}
	}

	private class OnPencilImageClick implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			if(enableEdit){
				profileTextArea.switchToEdit();
				noAboutUsContainer.setVisible(false);
				btnSave.setVisible(true);
				biographyCancelButton.setVisible(true);
			}
		}
	}

	public class hideEditPencil implements MouseOverHandler {
		@Override
		public void onMouseOver(MouseOverEvent event) {
			if (enableEdit){
				profileTextArea.getBiographyEditImage().setVisible(true);
			}
		}
	}

	public class showEditPencil implements MouseOutHandler {
		@Override
		public void onMouseOut(MouseOutEvent event) {
			if (enableEdit){
				profileTextArea.getBiographyEditImage().setVisible(false);
			}
		}
	}

	private class OnGradeEditImageClick implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			if (enableEdit)
				if(!isGradeCourseBtnClick) {
					clickGradeCourseEditBtn();
					isGradeCourseBtnClick = true;
				}
				userCoursePopup.setVisible(true);
	        	isShowing=true;
		}
	}

	public class showGradeEditPencil implements MouseOverHandler {
		@Override
		public void onMouseOver(MouseOverEvent event) {
			if (enableEdit)
				if(userCourseList.getWidgetCount() > 0 || userGradeList.getWidgetCount() > 0) {
					editPencil.setVisible(true);
				}
			}
	}

	public class hideGradeEditPencil implements MouseOutHandler {
		@Override
		public void onMouseOut(MouseOutEvent event) {
			if (enableEdit)
				editPencil.setVisible(false);
		}
	}

	@UiHandler("addBioBtn")
	public void OnClickAddBioButton(ClickEvent event) {
		if(enableEdit){
			enableAddBioBtn("other");
			btnSave.setVisible(true);
			biographyCancelButton.setVisible(true);
			profileTextArea.switchToEdit();
		}
	}

	@UiHandler("saveBtn")
	public void OnClickGradeEditButton(ClickEvent event) {
		if (enableEdit){
			AppClientFactory.getInjector().getUserService().getUserProfileV2Details(profileDo.getUser().getGooruUId(), USER_META_ACTIVE_FLAG, new SimpleAsyncCallback<ProfileDo>(){
				@Override
				public void onSuccess(ProfileDo result) {
					setUserGradeList(result.getGrade());
					setUserCourseList(result.getCourses());
					setMetaDataContainerWidth();
					setAddGradeCourseBtnVisibility();
				}
			});
			moreGradeCourseLbl.clear();
			userCoursePopup.setVisible(false);
		}
	}

	private void setAddGradeCourseBtnVisibility() {
		if(userGradeList.getWidgetCount() > 0 || userCourseList.getWidgetCount() > 0) {
			addCourseGradeBtn.setVisible(false);
			editPencil.setVisible(true);
		} else {
			addCourseGradeBtn.setVisible(true);
			editPencil.setVisible(false);
		}
	}
	@UiHandler("cancelBtn")
	public void OnCancelGradeEditButton(ClickEvent event) {
		userCoursePopup.setVisible(false);
	}

	@UiHandler("btnSave")
	public void OnClickbiographySaveButton(ClickEvent event) {
		Map<String, String> parms = new HashMap<String, String>();
		parms.put("text", profileTextArea.geteditTextBox().getText());
		AppClientFactory.getInjector().getResourceService().checkProfanity(parms, new SimpleAsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean value) {
				if(value){
					SetStyleForProfanity.SetStyleForProfanityForTextArea(profileTextArea.geteditTextBox(), profileTextArea.getErrorLabel(), value);
				}else{
					if (enableEdit){
						noAboutUsContainer.setVisible(false);
						aboutUsCharacterValidation.setVisible(false);
						MixpanelUtil.Click_On_Save();
						profileTextArea.switchToLabel();
					}
				}
			}
		});
	}

	@UiHandler("biographyCancelButton")
	public void OnClickBiographyCancelButton(ClickEvent event) {
		//disableContentAndSetOldContent(profileDo.getAboutMe());
		aboutUsCharacterValidation.setVisible(false);
		btnSave.setVisible(false);
		biographyCancelButton.setVisible(false);
		profileTextArea.cancel();
		Map<String, String> parms = new HashMap<String, String>();
		parms.put("text", profileTextArea.geteditTextBox().getText());
		AppClientFactory.getInjector().getResourceService().checkProfanity(parms, new SimpleAsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean value) {
				profileTextArea.getErrorLabel().setVisible(false);
			}
		});
	}

	/*public void disableContentAndSetOldContent(String aboutMe) {
		if (aboutMe == null || aboutMe.equalsIgnoreCase("null")) {
			noAboutUsContainer().setVisible(true);
		} else {
			noAboutUsContainer().setVisible(false);
		}

		if (aboutMe.isEmpty() || aboutMe == null
				|| aboutMe.equalsIgnoreCase("null")) {
			enableAddBioBtn("addBioBtn");
		} else {
			profileTextArea.switchToLabel();
		}

		aboutUsCharacterValidation.setVisible(false);
		btnSave.setVisible(false);
		biographyCancelButton.setVisible(false);
		profileTextArea.cancel();
	}
*/
	@Override
	public ProfilePageDescriptionEditUc getProfileBiographyEditUC() {
		return profileTextArea;
	}

	/**
	 * separate the view according to grade level of the collection
	 */
	public void setGradeList(String grades) {
		KinderGarten.clear();
		higherEducation.clear();
		gradeTopList.clear();
		gradeMiddleList.clear();
		KinderGarten.add(new ProfilePageGradeLabel(GL0850, profileDo));
		higherEducation.add(new ProfilePageGradeLabel(GL0169,profileDo));
		for (int i = 1; i <= 12; i++) {
			if (i <= 6) {
				gradeTopList.add(new ProfilePageGradeLabel(i + "", profileDo));
			}
			if (i >= 7) {
				gradeMiddleList.add(new ProfilePageGradeLabel(i + "", profileDo));
			}
		}
	}

	/**
	 * to display message if course exceeds more than five
	 */
	public void courseMaxShow() {
		courseMaxMsg.setStyleName(CollectionCBundle.INSTANCE.css().profileCourseMaxMsgShow());
		new FadeInAndOut(courseMaxMsg.getElement(), 10000, 10000);
	}
	
	/**
	 * to hide message if course exceeds less than five
	 */
	public void courseMaxHide() {
		courseData.setStyleName(CollectionCBundle.INSTANCE.css().floatLeft());
		collectionCourseLst.setStyleName(CollectionCBundle.INSTANCE.css()
				.infoTextBox());
		addCourseBtn.setStyleName(CollectionCBundle.INSTANCE.css()
				.infoAddButton());
		courseMaxMsg.setStyleName(CollectionCBundle.INSTANCE.css()
				.courseMaxMsg());
		courseMaxMsg.getElement().getStyle().setFloat(Float.LEFT);
	}

	@Override
	public void setCourseList(List<LibraryCodeDo> libraryCode) {
		collectionCourseLst = new GroupedListBox();
		collectionCourseLst.addStyleName(CollectionCBundle.INSTANCE.css()
				.infoTextBox());
		collectionCourseLst.addItem(" What course(s) do you teach? ", "-1");
		if (libraryCode != null) {
			for (LibraryCodeDo libraryCodeDo : libraryCode) {
				for (LibraryCodeDo liCodeDo : libraryCodeDo.getNode()) {
					collectionCourseLst.addItem(libraryCodeDo.getLabel() + "|"
							+ liCodeDo.getLabel(), liCodeDo.getCodeId() + "");
				}
			}
		}
		collectionCourseLstPanel.clear();
		collectionCourseLstPanel.add(collectionCourseLst);
	}

	/**
	 * New course is created for collection
	 * 
	 * @param courseLabel
	 *            the course of the label widget which is used to get all
	 *            courses
	 * @param courseCode
	 *            the course of the course code, an absolute course code will be
	 *            used to create a new course
	 * @return the label of all course created for the collection.
	 */
	protected CloseLabel createCourseLabel(final String courseLabel,
			final String courseCode) {
		return new CloseLabel(courseLabel) {

			@Override
			public void onCloseLabelClick(ClickEvent event) {
				this.removeFromParent();
				CodeDo codeDo = new CodeDo();
				codeDo.setCodeId(Integer.parseInt(courseCode));
				getUiHandlers().deleteCourse(codeDo);
			}
		};
	}

	/**
	 * Adding new course for the collection , will check it has more than five
	 * courses and the selected course is repeated or not
	 * 
	 * @param clickEvent
	 *            specifies event type
	 */
	@UiHandler("addCourseBtn")
	public void onAddCourseClick(ClickEvent clickEvent) {
		if (enableEdit){
			if (coursesPanel.getWidgetCount() < 5) {
				final String courseCodeLabel = collectionCourseLst
						.getItemText(collectionCourseLst.getSelectedIndex());
				final String courseCode = collectionCourseLst
						.getValue(collectionCourseLst.getSelectedIndex());
				if (collectionCourseLst.getSelectedIndex() == 0) {
					return;
				}
				if (validateCourse(courseCodeLabel) && courseCode != null) {
					alertContentUc=new AlertContentUc(GL1089, GL1090);
				} else {
					Set<ProfileCodeDo> profileCodeDoSet = new HashSet<ProfileCodeDo>();
					ProfileCodeDo profileCodeDo = new ProfileCodeDo();
					CodeDo codeDo = new CodeDo();
					codeDo.setCodeId(Integer.parseInt(courseCode));
					profileCodeDo.setCode(codeDo);
					profileCodeDoSet.add(profileCodeDo);
					getUiHandlers().addCourse(profileCodeDoSet);
	
					coursesPanel
							.add(createCourseLabel(courseCodeLabel, courseCode));
					collectionCourseLst.setSelectedIndex(0);
				}
				courseMaxHide();
			} else {
				courseMaxShow();
			}
		}
	}

	/**
	 * Will check it ,the selected course is repeated or not
	 * 
	 * @param course
	 *            to validate with already added course
	 * @return true if repeated course is selected or false
	 */
	protected boolean validateCourse(String course) {
		for (Widget widget : coursesPanel) {
			if (course.equals(((CloseLabel) widget).getSourceText())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ProfileDo getProfileDo() {
		return profileDo;
	}

	private void enableAddBioBtn(String textType) {
		if (textType.equalsIgnoreCase("addBioBtn")) {
			addBioBtn.setVisible(true);
			userBio.setVisible(false);
			profilePageEditBioPanel.setVisible(false);
		} else if (textType.equalsIgnoreCase("userBio")) {
			addBioBtn.setVisible(false);
			userBio.setVisible(true);
			profilePageEditBioPanel.setVisible(false);
		} else {
			addBioBtn.setVisible(false);
			userBio.setVisible(false);
			profilePageEditBioPanel.setVisible(true);
			profileTextArea.cancel();
		}
	}

	@Override
	public void disableChildData() {
		userMetadata.setVisible(false);
		bioMainContainer.setVisible(false);
		gooruSocialButtonsContainer.setVisible(false);
		gooruProfileOnOffContainer.setVisible(false);
	}
	
	@Override
	public void editOptions(boolean toEnable){
		enableEdit = toEnable;
	
		if(!toEnable){
			gooruProfileOnOffContainer.setVisible(false);
			editPencil.getElement().getStyle().setVisibility(Visibility.HIDDEN);
			addBioBtn.getElement().getStyle().setVisibility(Visibility.HIDDEN);
			addCourseGradeBtn.getElement().getStyle().setVisibility(Visibility.HIDDEN);
		}else{
			gooruProfileOnOffContainer.setVisible(true);
			editPencil.getElement().getStyle().setVisibility(Visibility.VISIBLE);
			addBioBtn.getElement().getStyle().setVisibility(Visibility.VISIBLE);
			addCourseGradeBtn.getElement().getStyle().setVisibility(Visibility.VISIBLE);
		}
	}

	@Override
	public Label getChilNoShareOption() {
		return profilePageViewMsg;
	}
	
	public void getEnableWidget(boolean toEnable,String about,Set<ProfileCodeDo> set) {
		if(toEnable){
			gooruProfileOnOffContainer.setVisible(true);
			editPencil.getElement().getStyle().setVisibility(Visibility.VISIBLE);
			addBioBtn.getElement().getStyle().setVisibility(Visibility.VISIBLE);
			addCourseGradeBtn.getElement().getStyle().setVisibility(Visibility.VISIBLE);
			bioMainContainer.setVisible(true);
			userMetadata.setVisible(true);	
			aboutMeTextContainer.setVisible(true);
			bioMainContainer.getElement().setAttribute("style", "margin-top:0px");
		} else {
			if(about==""||about.equalsIgnoreCase("")) {
				bioMainContainer.setVisible(false);
				aboutMeTextContainer.setVisible(false);
			} else {
				bioMainContainer.getElement().setAttribute("style", "margin-top:8px");
				aboutMeTextContainer.setVisible(true);
				bioMainContainer.setVisible(true);
			}
			if(set.isEmpty()) {
				userMetadata.setVisible(false);
			} else {
				userMetadata.setVisible(true);	
			}
			gooruProfileOnOffContainer.setVisible(false);
			editPencil.getElement().getStyle().setVisibility(Visibility.HIDDEN);
			addBioBtn.getElement().getStyle().setVisibility(Visibility.HIDDEN);
			addCourseGradeBtn.getElement().getStyle().setVisibility(Visibility.HIDDEN);
		}
	}

	@Override
	public void closeAllOpenedPopUp() {
		if(alertContentUc!=null){
			alertContentUc.getAlertBox().hide();
		}

	}
	
	private void clickGradeCourseEditBtn() {
		getUiHandlers().getTaxonomyData();
		setGradeList(profileDo.getGrade());
		Set<ProfileCodeDo> codeDo = profileDo.getCourses();
		coursesPanel.clear();
		for (ProfileCodeDo code : codeDo) {
			coursesPanel.add(createCourseLabel(code.getCode().getLabel(), code.getCode().getCodeId() + ""));
		}
	}
}
