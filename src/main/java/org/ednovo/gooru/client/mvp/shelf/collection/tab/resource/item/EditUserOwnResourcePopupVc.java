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
package org.ednovo.gooru.client.mvp.shelf.collection.tab.resource.item;

import org.ednovo.gooru.client.SimpleAsyncCallback;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.faq.CopyRightPolicyVc;
import org.ednovo.gooru.client.mvp.faq.TermsAndPolicyVc;
import org.ednovo.gooru.client.mvp.faq.TermsOfUse;
import org.ednovo.gooru.client.mvp.search.event.SetHeaderZIndexEvent;
import org.ednovo.gooru.client.mvp.shelf.event.GetEditPageHeightEvent;
import org.ednovo.gooru.client.uc.AppPopUp;
import org.ednovo.gooru.client.uc.HTMLEventPanel;
import org.ednovo.gooru.client.util.MixpanelUtil;
import org.ednovo.gooru.shared.model.content.CollectionItemDo;
import org.ednovo.gooru.shared.model.content.ResourceMetaInfoDo;
import org.ednovo.gooru.shared.util.MessageProperties;
import org.ednovo.gooru.shared.util.ResourceImageUtil;
import org.ednovo.gooru.shared.util.StringUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;

public abstract class EditUserOwnResourcePopupVc extends AppPopUp implements MessageProperties  {
	CollectionItemDo collectionItemDo;

	@UiField
	public Button addResourceBtn,changeFileBtn,browseResourceBtn;
	
	@UiField
	FormPanel fileuploadForm;
	
	@UiField
	HTMLPanel uploadContainer,uploadName,defaultFileTxtContainer,panelContentRights,imagesText,textsText;
	

	@UiField
	Label resourceContentChkLbl, mandatoryTitleLbl,uploadImageLbl,fileTextLbl,rightsLbl;

	@UiField
	Label mandatoryCategoryLbl/*urlTextLbl*/;

	@UiField
	public TextBox titleTextBox,resourcePathTextBox;
	
	@UiField
	FileUpload chooseResourceBtn;

	@UiField
	public TextArea descriptionTxtAera;
	
	@UiField Label lblAdding,resoureDropDownLbl;
	
	@UiField HTMLEventPanel lblContentRights;

	@UiField
	Image setThumbnailImage,clipImage;
	
	@UiField
	Label descCharcterLimit;
	@UiField
	CheckBox rightsChkBox;
	@UiField Label resourceCategoryLabel,andText,additionalText,agreeText;
	@UiField HTMLPanel categorypanel,texts,image,resourceTypePanel,panelAction,fileTitleText,
	descriptionText,categoryLabel,thumbnailImageText;
	@UiField Anchor copyRightAnr,rollBackToPaperClip;
	@UiField Anchor termsAndPolicyAnr,privacyAnr,cancelResourcePopupBtnLbl;
	@UiField Anchor commuGuideLinesAnr;
	private CopyRightPolicyVc copyRightPolicy;
	private TermsAndPolicyVc termsAndPolicyVc;
	private TermsOfUse termsOfUse;
	ResourceMetaInfoDo resMetaInfoDo = null;

	boolean isValidImageSize=true;
	
	private String thumbnailUrlStr = null;
	
	String fileNameWithOutRespUrl = null;
	
	public boolean resoureDropDownLblOpen = false;
	
	private static final String DEFULT_IMAGE_PREFIX = "images/default-";

	private static final String PNG =GL0899;
	public boolean fileChanged=false;
	String mediaFileName=null;
	String originalFileName=null;
	String titleStr ;
	String categoryStr ;
	String filePath;
	
	private static final String RESOURCE_UPLOAD_FILE_PATTERN = "([^\\s]+([^?#]*\\.(?:jpg|jpeg|pdf))$)";
	private static final String RESOURCE_FILE_SUPPORT_MSG =GL0955;
	private static final String IMAGE_UPLOAD_URL = "/v2/media?sessionToken={0}";
	
	private static EditUserOwnResourcePopupVcUiBinder uiBinder = GWT.create(EditUserOwnResourcePopupVcUiBinder.class);
	
	interface EditUserOwnResourcePopupVcUiBinder extends UiBinder<Widget, EditUserOwnResourcePopupVc> {
	}
	


	public EditUserOwnResourcePopupVc(CollectionItemDo collectionItemDo) {
		
		super();
		this.collectionItemDo = collectionItemDo;
		setContent(GL0949, uiBinder.createAndBindUi(this));
		uploadName.getElement().setInnerHTML(" "+GL0948);
		browseResourceBtn.setText(GL0902);
		rollBackToPaperClip.setText(GL0950);
		changeFileBtn.setText(GL0951);
		fileTitleText.getElement().setInnerHTML(GL0952);
		mandatoryTitleLbl.setText(GL0173);
		descriptionText.getElement().setInnerHTML(GL0904);
		categoryLabel.getElement().setInnerHTML(GL0906);
		mandatoryCategoryLbl.setText(GL0917);
		/*videoLabel.getElement().setInnerHTML(GL0918);
		interactiveText.getElement().setInnerHTML(GL0919);
		websiteText.getElement().setInnerHTML(GL0920);*/
		/*slideText.getElement().setInnerHTML(GL0908);
		handoutText.getElement().setInnerHTML(GL0907);
		textbookLabel.getElement().setInnerHTML(GL0909);
		lessonText.getElement().setInnerHTML(GL0910);
		examText.getElement().setInnerHTML(GL0921);*/
		textsText.getElement().setInnerHTML(GL1044);
		//audioText.getElement().setInnerHTML(GL1045);
		imagesText.getElement().setInnerHTML(GL1046);
		//otherText.getElement().setInnerHTML(GL1047);
		
		
		
		/*slideText.getElement().setInnerHTML(GL0908);
		handoutText.getElement().setInnerHTML(GL0907);
		textbookLabel.getElement().setInnerHTML(GL0909);
		lessonText.getElement().setInnerHTML(GL0910);*/
		thumbnailImageText.getElement().setInnerHTML(GL0911);
		uploadImageLbl.setText(GL0912);
		rightsLbl.setText(GL0869);
		agreeText.setText(GL0870);
		commuGuideLinesAnr.setText(GL0871);
		termsAndPolicyAnr.setText(" "+GL0872+GL_GRR_COMMA);
		privacyAnr.setText(" "+GL0873);
		andText.setText(" "+GL_GRR_AND+" ");
		copyRightAnr.setText(" "+GL0875);
		additionalText.setText(GL0874);
		addResourceBtn.setText(GL0141);
		cancelResourcePopupBtnLbl.setText(GL0142);
		lblAdding.setText(GL0591.toLowerCase());
		clipImage.setUrl("images/paperclip.png");
		addResourceBtn.addClickHandler(new AddClickHandler());
		addResourceBtn.getElement().getStyle().setFloat(Float.LEFT);
		uploadImageLbl.addClickHandler(new OnEditImageClick());
		
		changeFileBtn.addClickHandler(new ChangeFileBtnClick());

		titleTextBox.addKeyUpHandler(new TitleKeyUpHandler());
		descriptionTxtAera.addKeyUpHandler(new DescriptionKeyUpHandler());
		resourcePathTextBox.addKeyUpHandler(new ResourcePathKeyUpHandler());
		titleTextBox.getElement().setAttribute("maxlength", "50");
		descriptionTxtAera.getElement().setAttribute("maxlength", "300");
		resourceContentChkLbl.setVisible(false);
		mandatoryTitleLbl.setVisible(false);
		mandatoryCategoryLbl.setVisible(false);
		descCharcterLimit.setVisible(false);
		setThumbnailImage.setVisible(true);
		resourceTypePanel.setVisible(false);
		chooseResourceBtn.getElement().setId("uploadFile");
		panelContentRights.setVisible(false);
		rightsChkBox.addClickHandler(new rightsChecked());
		rightsChkBox.getElement().setId("chkRights");

        setModal(true);
		Window.enableScrolling(false);
        AppClientFactory.fireEvent(new SetHeaderZIndexEvent(99, false));

		
		displayResourceInfo();
		show();
		center();
		AppClientFactory.fireEvent(new GetEditPageHeightEvent(this, false));
		getResourceMetaInfo(collectionItemDo.getResource().getUrl());
		handelFormEvent();
		
		lblAdding.getElement().getStyle().setDisplay(Display.NONE);
		panelAction.getElement().getStyle().setDisplay(Display.BLOCK);
		
		defaultFileTxtContainer.getElement().getStyle().setDisplay(Display.BLOCK);
		uploadContainer.getElement().getStyle().setDisplay(Display.NONE);
		uploadName.getElement().getStyle().setDisplay(Display.NONE);
		fileTextLbl.setText(GL0954);
		browseResourceBtn.getElement().getStyle().setMarginRight(9, Unit.PX);
		copyRightAnr.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
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
		});
		
		termsAndPolicyAnr.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
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
			
		});
		privacyAnr.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
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
			
		});
		commuGuideLinesAnr.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Window.open("http://support.goorulearning.org/hc/en-us/articles/200688506","_blank",""); 
			}
		});
	}
	
	public void onLoad(){
		super.onLoad();
		Scheduler.get().scheduleDeferred(new ScheduledCommand(){

			@Override
			public void execute() {
				setResourceDescription();
			}
        });
	}
	
	@UiHandler("cancelResourcePopupBtnLbl")
	public void cancelPopUp(ClickEvent clickEvent) {
		AppClientFactory.fireEvent(new GetEditPageHeightEvent(this, true));

		Window.enableScrolling(true);
        AppClientFactory.fireEvent(new SetHeaderZIndexEvent(0, true));

		hide();
	}
	
	public void getResourceMetaInfo(String url) {
		AppClientFactory
				.getInjector()
				.getResourceService()
				.getResourceMetaInfo(url,
						new SimpleAsyncCallback<ResourceMetaInfoDo>() {

							@Override
							public void onSuccess(ResourceMetaInfoDo result) {
								setData(result);
							}
						});
	}

	public void setData(ResourceMetaInfoDo result) {
		setResMetaInfo(result);
		updateUi();
	}

	 private class rightsChecked implements ClickHandler {
			@Override
			public void onClick(ClickEvent event) {
				if(rightsChkBox.getValue()){
					rightsLbl.getElement().getStyle().setColor("black");
				}
				else{
					rightsLbl.getElement().getStyle().setColor("orange");
				}
				
			}
	}
	private void setResMetaInfo(ResourceMetaInfoDo result) {
		this.resMetaInfoDo = result;
	}
	
	public void setResourceDescription(){
		descriptionTxtAera.setText(collectionItemDo.getResource().getDescription());
	}
	
	@UiHandler("chooseResourceBtn")
	public void onChangeFileUploadBtn(ChangeEvent event){
		if (!"".equalsIgnoreCase(chooseResourceBtn.getFilename())) {
			String size=getFileNameSize();
			double sizeOfImage=Double.parseDouble(size);
			if(sizeOfImage>5){
				isValidImageSize=false;
				resourceContentChkLbl.setText(GL0913);
				resourceContentChkLbl.setVisible(true);
				fileuploadForm.reset();
				if(!resourcePathTextBox.getText().equalsIgnoreCase("")){
					resourcePathTextBox.setText("");
				}
			}
		}
	}
	

	public void displayResourceInfo() {
		String url = collectionItemDo.getResource().getUrl();
		if (collectionItemDo.getResource().getDescription().length() >= 300) {
			descriptionTxtAera.setText(collectionItemDo.getResource()
					.getDescription().substring(0, 300));
		} else {
			descriptionTxtAera.setText(collectionItemDo.getResource()
					.getDescription());
		}
		if (collectionItemDo.getResource().getTitle().length() >= 50) {
			titleTextBox.setText(collectionItemDo.getResource().getTitle()
					.substring(0, 50));
		} else {
			titleTextBox.setText(collectionItemDo.getResource().getTitle());
		}

		setThumbnailImage.setVisible(true);
		String category = collectionItemDo.getResource().getCategory();
		
		/* if (category.equalsIgnoreCase(GL0918)) {
			resourceCategoryLabel.setText(GL0918 );
			categorypanel.setStyleName(video.getStyleName());
			resourceTypePanel.setVisible(false);
			resoureDropDownLblOpen=false;
		} else if (category.equalsIgnoreCase(GL0919)) {
			resourceCategoryLabel.setText(GL0919);
			categorypanel.setStyleName(interactive.getStyleName());
			resourceTypePanel.setVisible(false);
			resoureDropDownLblOpen=false;
		} else if (category.equalsIgnoreCase(GL0920)|| category.equalsIgnoreCase("Exam")) {
			resourceCategoryLabel.setText(GL0920);
			categorypanel.setStyleName(website.getStyleName());
			resourceTypePanel.setVisible(false);
			resoureDropDownLblOpen=false;
		} */  if (category.equalsIgnoreCase(GL1046) || category.equalsIgnoreCase("slide")||category.equalsIgnoreCase("image")) {
			resourceCategoryLabel.setText(GL1046);
			categorypanel.setStyleName(image.getStyleName());
			resourceTypePanel.setVisible(false);
			resoureDropDownLblOpen=false;
//		} else if (category.equalsIgnoreCase("Question")) {
//			resourceCategoryLabel.setText("Question");
//			categorypanel.setStyleName(question.getStyleName());
//			resourceTypePanel.setVisible(false);
//			resoureDropDownLblOpen=false;
		} else if (category.equalsIgnoreCase(GL1044)|| category.equalsIgnoreCase("Textbook")|| category.equalsIgnoreCase("Lesson")|| category.equalsIgnoreCase("Handout")||category.equalsIgnoreCase("Text")) {
			resourceCategoryLabel.setText(GL1044);
			categorypanel.setStyleName(texts.getStyleName());
			resourceTypePanel.setVisible(false);
			resoureDropDownLblOpen=false;
		/*}else if (category.equalsIgnoreCase(GL1045)) {
			resourceCategoryLabel.setText(GL1045);
			categorypanel.setStyleName(audio.getStyleName());
			resourceTypePanel.setVisible(false);
			resoureDropDownLblOpen=false;
		}else if (category.equalsIgnoreCase(GL1047)) {
			resourceCategoryLabel.setText(GL1047);
			categorypanel.setStyleName(other.getStyleName());
			resourceTypePanel.setVisible(false);
			resoureDropDownLblOpen=false;*/
		}

		thumbnailUrlStr = collectionItemDo.getResource().getThumbnailUrl();
		setImage(url, category);
	}

	public void setImage(String url, String category){
		if(category.contains("lesson")||category.contains("textbook")||category.contains("handout"))
		{
			category=category.replaceAll("lesson", "text").replaceAll("textbook", "text").replaceAll("handout", "text");
		}
		if(category.contains("slide")||category.contains("Slide"))
		{
			category=category.replaceAll("slide","image");
		}
		if (thumbnailUrlStr.endsWith("null")) {
			if (url.indexOf("youtube") >0){
				String youTubeIbStr = ResourceImageUtil.getYoutubeVideoId(url);
				thumbnailUrlStr = "http://img.youtube.com/vi/"+youTubeIbStr+"/1.jpg";
			}else{
				thumbnailUrlStr = DEFULT_IMAGE_PREFIX + category.toLowerCase() + PNG;
			}
		} 
		setThumbnailImage.setUrl(thumbnailUrlStr);
	}
	public void updateUi() {
		setThumbnailImage.setVisible(true);

	}
	
	private class ChangeFileBtnClick implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			defaultFileTxtContainer.getElement().getStyle().setDisplay(Display.NONE);
			uploadName.getElement().getStyle().setDisplay(Display.BLOCK);
			uploadContainer.getElement().getStyle().setDisplay(Display.BLOCK);
			if(!resourcePathTextBox.getText().trim().equalsIgnoreCase("")){
				resourcePathTextBox.setText("");
			}
			
		}
		
	}
	@UiHandler("lblContentRights")
	public void onMouseOver(MouseOverEvent event){
		panelContentRights.setVisible(true);
	}
	
	@UiHandler("lblContentRights")
	public void onMouseOut(MouseOutEvent event){
		panelContentRights.setVisible(false);
	}
	@UiHandler("rollBackToPaperClip")
	public void onClickRollBackPaperClip(ClickEvent clickEvent){
		uploadName.getElement().getStyle().setDisplay(Display.NONE);
		uploadContainer.getElement().getStyle().setDisplay(Display.NONE);
		defaultFileTxtContainer.getElement().getStyle().setDisplay(Display.BLOCK);
		resourceContentChkLbl.setVisible(false);
	}

	private class AddClickHandler implements ClickHandler {
		@SuppressWarnings("deprecation")
		@Override
		public void onClick(ClickEvent event) {
			
			lblAdding.getElement().getStyle().setDisplay(Display.BLOCK);
			panelAction.getElement().getStyle().setDisplay(Display.NONE);

			boolean isValidate = true;

			 titleStr = titleTextBox.getText().trim();
			 categoryStr =resourceCategoryLabel.getText();// resourceTypeListBox.getItemText(resourceTypeListBox.getSelectedIndex());
			 filePath = resourcePathTextBox.getText().trim();
			 if(categoryStr.contains("Videos")||categoryStr.contains("Interactives")||categoryStr.contains("Images")||categoryStr.contains("Texts"))
				{
				 categoryStr=categoryStr.substring(0, categoryStr.length()-1);
					 if(categoryStr.contains("Image")){
						 categoryStr="Slide";
					 }
				}
			 if(uploadContainer.isVisible()){
				 if(resourcePathTextBox.getText().trim() == null || resourcePathTextBox.getText().trim().equalsIgnoreCase("")){
					 resourceContentChkLbl.setText(GL0914);
					 resourceContentChkLbl.setVisible(true);
					 isValidate = false;
				 }
			 }
			
			
			if (titleStr == null || titleStr.equalsIgnoreCase("")) {
				mandatoryTitleLbl.setText(GL0173);
				mandatoryTitleLbl.setVisible(true);
				isValidate = false;
			}
			if(!rightsChkBox.getValue()){
				rightsLbl.getElement().getStyle().setColor("orange");
				isValidate = false;
			}
			if(descriptionTxtAera.getText().trim()==null || descriptionTxtAera.getText().trim().equals("")){
				isValidate = false;
				descCharcterLimit.setText(GL0905);
				descCharcterLimit.setVisible(true);
			}
			if (categoryStr == null	|| categoryStr.equalsIgnoreCase("-1")|| categoryStr.equalsIgnoreCase("Choose a resource category")) {
				mandatoryCategoryLbl.setText(GL0917);
				mandatoryCategoryLbl.setVisible(true);
				isValidate = false;
			}

			if (isValidate) {
				/*String str ="{\"deleteType\":\"DELETE\",\"deleteUrl\":\"media/0deeb890-8a4b-468e-87c0-5615d69e856e.jpg\",\"imageValidationMsg\":null,\"name\":\"555cb7a6-4312-434e-8e09-004a72fa4073.jpg\",\"originalFilename\":\"download(2).jpg\",\"size\":462358,\"statusCode\":200,\"uploadImageSource\":\"local\",\"url\":\"http://devrepo.goorulearning.org/qalive/uploaded-media/0deeb890-8a4b-468e-87c0-5615d69e856e.jpg\"}";
				parseUploadFileDetails(str);*/ 
				
				if(fileChanged && (uploadContainer.isVisible())){
					fileuploadForm.setAction(AppClientFactory.getLoggedInUser().getSettings().getRestEndPoint() + StringUtil.generateMessage(IMAGE_UPLOAD_URL, AppClientFactory.getLoggedInUser().getToken(), chooseResourceBtn.getFilename()));
					fileuploadForm.addFormHandler(new FormHandler() {
						public void onSubmitComplete(FormSubmitCompleteEvent event) {
							if(isValidImageSize){
								parseUploadFileDetails(event.getResults());
							}
							
						}
						
						@Override
						public void onSubmit(FormSubmitEvent event) {
							
						}
					});
					fileuploadForm.submit();
				}
				else{
					if(categoryStr.contains("Images")||categoryStr.contains("Texts"))
					{
						categoryStr=categoryStr.substring(0, categoryStr.length()-1);
						 if(categoryStr.contains("Image")||categoryStr.contains("Images")){
							 categoryStr="Slide";
						 }
					}
					updateUserOwnResource(filePath,mediaFileName,originalFileName,titleStr,descriptionTxtAera.getText().trim(),categoryStr,thumbnailUrlStr);
				}
			}
			else{
				lblAdding.getElement().getStyle().setDisplay(Display.NONE);
				panelAction.getElement().getStyle().setDisplay(Display.BLOCK);
			}
		}

		protected void parseUploadFileDetails(String jsonString) {
			if(jsonString!=null){
				JSONValue jsonParseValue=JSONParser.parseStrict(jsonString);
				JSONObject jsonObject=jsonParseValue.isObject();
				JSONValue jsonMediaFileValue=jsonObject.get("name");
				String mediaFileName=jsonMediaFileValue.isString().toString().replaceAll("^\"|\"$", "");
				JSONValue jsonOriginalFileValue=jsonObject.get("originalFilename");
				String originalFileName=jsonOriginalFileValue.isString().toString().replaceAll("^\"|\"$", "");
				updateUserOwnResource(filePath,mediaFileName,originalFileName,titleStr,descriptionTxtAera.getText().trim(),categoryStr,thumbnailUrlStr);
			}
		}
	}

	
	private class OnEditImageClick implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			resourceImageUpload();
		}
	}

	public abstract void resourceImageUpload();

	public abstract void updateUserOwnResource(String resourceFilePath,String resMediaFileName,String resOriginalFileName,String titleStr, String desc,String categoryStr, String thumbnailUrlStr);
	
	private class ResourcePathKeyUpHandler implements KeyUpHandler {
		public void onKeyUp(KeyUpEvent event) {
			resourceContentChkLbl.setVisible(false);
		}
	}

	private class TitleKeyUpHandler implements KeyUpHandler {

		public void onKeyUp(KeyUpEvent event) {
			mandatoryTitleLbl.setVisible(false);
			if (titleTextBox.getText().length() >= 50) {
				mandatoryTitleLbl.setText(GL0143);
				mandatoryTitleLbl.setVisible(true);
			}
		}
	}

	private class DescriptionKeyUpHandler implements KeyUpHandler {

		public void onKeyUp(KeyUpEvent event) {
			descCharcterLimit.setVisible(false);
			if (descriptionTxtAera.getText().length() >= 300) {
				descriptionTxtAera.setText(descriptionTxtAera.getText().trim().substring(0,300));
				descCharcterLimit.setText(GL0143);
				descCharcterLimit.setVisible(true);
			}
		}
	}


	
	/*@UiHandler("videoResourcePanel")
	void videoResourcePanel(ClickEvent event) {
		MixpanelUtil.mixpanelEvent("organize_add_resource_video_selected");
		resourceCategoryLabel.setText(GL0918);
		categorypanel.setStyleName(video.getStyleName());
		resourceTypePanel.setVisible(false);
		resoureDropDownLblOpen = false;
		mandatoryCategoryLbl.setVisible(false);
	}*/

	/*@UiHandler("interactiveResourcePanel")
	void interactiveResourcePanel(ClickEvent event) {
		MixpanelUtil.mixpanelEvent("organize_add_resource_interactive_selected");
		resourceCategoryLabel.setText(GL0919);
		categorypanel.setStyleName(interactive.getStyleName());
		resourceTypePanel.setVisible(false);
		resoureDropDownLblOpen = false;
		mandatoryCategoryLbl.setVisible(false);
	}

	@UiHandler("websiteResourcePanel")
	void websiteResourcePanel(ClickEvent event) {
		MixpanelUtil.mixpanelEvent("organize_add_resource_website_selected");
		resourceCategoryLabel.setText(GL0920);
		categorypanel.setStyleName(website.getStyleName());
		resourceTypePanel.setVisible(false);
		resoureDropDownLblOpen = false;
		mandatoryCategoryLbl.setVisible(false);
	}*/

	@UiHandler("imageResourcePanel")
	void slideResourcePanel(ClickEvent event) {
		MixpanelUtil.mixpanelEvent("organize_add_resource_image_selected");
		resourceCategoryLabel.setText(GL1046);
		categorypanel.setStyleName(image.getStyleName());
		resourceTypePanel.setVisible(false);
		resoureDropDownLblOpen = false;
		mandatoryCategoryLbl.setVisible(false);
	}

	@UiHandler("textResourcePanel")
	void handoutResourcePanel(ClickEvent event) {
		MixpanelUtil.mixpanelEvent("organize_add_resource_text_selected");
		resourceCategoryLabel.setText(GL1044);
		categorypanel.setStyleName(texts.getStyleName());
		resourceTypePanel.setVisible(false);
		resoureDropDownLblOpen = false;
		mandatoryCategoryLbl.setVisible(false);
	}

	/*@UiHandler("audioResourcePanel")
	void textbookResourcePanel(ClickEvent event) {
		MixpanelUtil.mixpanelEvent("organize_add_resource_audio_selected");
		resourceCategoryLabel.setText(GL1045);
		categorypanel.setStyleName(audio.getStyleName());
		resourceTypePanel.setVisible(false);
		resoureDropDownLblOpen = false;
		mandatoryCategoryLbl.setVisible(false);
	}

	@UiHandler("otherResourcePanel")
	void lessonResourcePanel(ClickEvent event) {
		MixpanelUtil.mixpanelEvent("organize_add_resource_other_selected");
		resourceCategoryLabel.setText(GL1047);
		categorypanel.setStyleName(other.getStyleName());
		resourceTypePanel.setVisible(false);
		resoureDropDownLblOpen = false;
		mandatoryCategoryLbl.setVisible(false);
	}*/

	/*public void setImageThumbnail() {
		setThumbnailImage.setUrl(thumbnailImagesLink.get(activeImageIndex));
		thumbnailUrlStr = thumbnailImagesLink.get(activeImageIndex);
	}*/

	/*public void clearFields() {
		titleTextBox.setText("");
		descriptionTxtAera.setText("");
		//resourceTypeListBox.setSelectedIndex(0);
		setThumbnailImage.setUrl("");
	}*/


	/** 
	 * This method is to get the setThumbnailImage
	 */
	public Image getSetThumbnailImage() {
		return setThumbnailImage;
	}

	/** 
	 * This method is to set the setThumbnailImage
	 */
	public void setSetThumbnailImage(Image setThumbnailImage) {
		this.setThumbnailImage = setThumbnailImage;
	}
	
	/** 
	 * This method is to get the thumbnailUrlStr
	 */
	public String getThumbnailUrlStr() {
		return thumbnailUrlStr;
	}

	/** 
	 * This method is to set the thumbnailUrlStr
	 */
	public void setThumbnailUrlStr(String thumbnailUrlStr) {
		this.thumbnailUrlStr = thumbnailUrlStr;
	}
	
	public void setFileNameWithOutRespUrl(String fileNameWithOutRespUrl ){
		this.fileNameWithOutRespUrl = fileNameWithOutRespUrl;
	}
	
	private void handelFormEvent() {
		chooseResourceBtn.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				if(hasValidateResource()){
					 isValidImageSize=true;
					 resourceContentChkLbl.setVisible(false);
					 resourcePathTextBox.setText(chooseResourceBtn.getFilename().trim());
					 fileChanged=true;
					 
				 }
				 else{
						if(!chooseResourceBtn.getFilename().trim().equalsIgnoreCase("")){
							 resourceContentChkLbl.setText(RESOURCE_FILE_SUPPORT_MSG);
							 resourceContentChkLbl.setVisible(true);
							 
						 }
					
					 
					 
				 }
				
			}
		});
	}
	@UiHandler("resoureDropDownLbl")
	public void dropDownClick(ClickEvent event) {
		if (resoureDropDownLblOpen == false) {
			resourceTypePanel.setVisible(true);
			resoureDropDownLblOpen = true;

		} else {
			resourceTypePanel.setVisible(false);
			resoureDropDownLblOpen = false;
		}

	}
	
	public boolean hasValidateResource(){
		boolean isValid = true;
		String uploadResourceName = chooseResourceBtn.getFilename();
		try {
			RegExp reg = RegExp.compile(RESOURCE_UPLOAD_FILE_PATTERN, "gi");
			if(uploadResourceName != null && !reg.test(uploadResourceName)){
				return isValid = false;
			}
		}catch (Exception e) {
			
		}
		return isValid;
	}
	
	/**
	 * To get the upload file size from client end
	 * @return it will return the upload file size in mb
	 */
	 
	public final native String getFileNameSize() /*-{
 
	var fileSize;
      if ($wnd.$.browser.msie) 
         {
   
     	 var objFSO = new ActiveXObject("Scripting.FileSystemObject");
        var sPath =   $wnd.$("#uploadFile")[0].value;
        var objFile = objFSO.getFile(sPath);
         var iSize = objFile.size;
        fileSize = iSize/ 1048576;
    
        }
    
     
     
        else 
        {
  
       fileSize =  $wnd.$("#uploadFile")[0].files[0].size ;//size in kb
       
        fileSize = fileSize / 1048576; //size in mb 
 
         }
 
        
     
        
           return fileSize.toString();
   
                 
    
  }-*/;

}
