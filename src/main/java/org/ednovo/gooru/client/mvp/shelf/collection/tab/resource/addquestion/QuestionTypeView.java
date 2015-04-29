package org.ednovo.gooru.client.mvp.shelf.collection.tab.resource.addquestion;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ednovo.gooru.client.SimpleAsyncCallback;
import org.ednovo.gooru.client.effects.FadeInAndOut;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.gin.BaseViewWithHandlers;
import org.ednovo.gooru.client.mvp.search.CenturySkills.AddCenturyPresenter;
import org.ednovo.gooru.client.mvp.shelf.collection.tab.resource.add.AddHintsView;
import org.ednovo.gooru.client.mvp.shelf.collection.tab.resource.add.AddHotSpotQuestionAnswerChoice;
import org.ednovo.gooru.client.mvp.shelf.collection.tab.resource.add.AddQuestionImg;
import org.ednovo.gooru.client.uc.AppMultiWordSuggestOracle;
import org.ednovo.gooru.client.uc.AppSuggestBox;
import org.ednovo.gooru.client.uc.CloseLabel;
import org.ednovo.gooru.client.uc.CloseLabelCentury;
import org.ednovo.gooru.client.uc.ConfirmationPopupVc;
import org.ednovo.gooru.client.uc.DownToolTipWidgetUc;
import org.ednovo.gooru.client.uc.RemoveToolTipUc;
import org.ednovo.gooru.client.uc.StandardsPreferenceOrganizeToolTip;
import org.ednovo.gooru.client.uc.tooltip.BrowseStandardsTooltip;
import org.ednovo.gooru.client.uc.tooltip.ToolTip;
import org.ednovo.gooru.client.ui.HTMLEventPanel;
import org.ednovo.gooru.client.ui.TinyMCE;
import org.ednovo.gooru.shared.i18n.MessageProperties;
import org.ednovo.gooru.shared.model.code.CodeDo;
import org.ednovo.gooru.shared.model.content.CollectionItemDo;
import org.ednovo.gooru.shared.model.content.StandardFo;
import org.ednovo.gooru.shared.model.content.checkboxSelectedDo;
import org.ednovo.gooru.shared.model.search.SearchDo;
import org.ednovo.gooru.shared.model.user.ProfileDo;
import org.ednovo.gooru.shared.util.StringUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.Widget;

public class QuestionTypeView extends BaseViewWithHandlers<QuestionTypeUiHandlers>
implements IsQuestionTypeView,SelectionHandler<SuggestOracle.Suggestion> {

	@UiField QuestionTypeCBundle res;

	private static QuestionTypeViewUiBinder uiBinder = GWT	.create(QuestionTypeViewUiBinder.class);

	private static MessageProperties i18n = GWT.create(MessageProperties.class);

	interface QuestionTypeViewUiBinder extends UiBinder<Widget, QuestionTypeView> {
	}

	@UiField Label questionTypeHeader,questionTypeText,charLimitLbl,questionNameErrorLbl,setUpAdvancedLbl,advancedLbl,
	explanationLabel,charLimitExplanation,explainationErrorLbl,errorMessageForExplanation,errorMessageForHintsCheck,
	depthOfKnowledgeHeader,depthOfKnowledgeTitle,standardsDefaultText,standardMaxMsg,centuryDefaultText;
	@UiField Anchor addQuestionImg,addExplanationAnc,addHintsAnc,addDepthOfKnowledgeAnc,addStandardsAnc,addCenturyAnc;
	@UiField HTMLPanel questionText,addQuestImgContainer,questionHotSpotAnswerChoiceContainer,advancedContainer,hintsContainer,
	errorContainer;
	@UiField TinyMCE questionNameTextArea,explainationTextArea;
	@UiField FlowPanel explanationContainer,depthOfKnowledgeContainer,standardContainer,standardsPanel,centuryContainer,
	centuryPanel;
	@UiField HTMLEventPanel eHearderIconExplanation,eHearderIconDepthOfKnowledge,eHearderIconStandards,eHearderIconCentury;
	@UiField Image depthOfKnoweldgeToolTip;
	@UiField CheckBox chkLevelRecall,chkLevelSkillConcept,chkLevelStrategicThinking,chkLevelExtendedThinking;
	@UiField Button browseStandards,browseCentury;
	@UiField(provided = true)
	AppSuggestBox standardSgstBox,centurySgstBox;

	private static final String MESSAGE_HEADER = i18n.GL0748();
	private static final String MESSAGE_CONTENT = i18n.GL0891();
	String[] anserChoiceNumArray=new String[]{"1","2","3","4","5"};

	private DeleteConfirmationPopupVc deleteConfirmationPopup;
	private CollectionItemDo collectionItemDo=null;
	RemoveToolTipUc removeToolTip=null; 
	ToolTip toolTip=null;
	private boolean isBrowseTooltip =false;
	BrowseStandardsTooltip browseStandardsTooltip;
	private boolean isBrowseStandardsToolTip = false;
	private static final String USER_META_ACTIVE_FLAG = "0";
	List<String> standardPreflist;
	private Map<String, String> standardCodesMap = new HashMap<String, String>();
	private Map<String, String> centuryCodesMap = new HashMap<String, String>();
	ArrayList<CodeDo> standardsDo=new ArrayList<CodeDo>();
	Set<CodeDo> deletedStandardsDo=new HashSet<CodeDo>();
	private AppMultiWordSuggestOracle standardSuggestOracle;
	private AppMultiWordSuggestOracle centurySuggestOracle;
	private SearchDo<CodeDo> standardSearchDo = new SearchDo<CodeDo>();
	private SearchDo<StandardFo> centurySearchDo = new SearchDo<StandardFo>();
	final StandardsPreferenceOrganizeToolTip standardsPreferenceOrganizeToolTip=new StandardsPreferenceOrganizeToolTip();
	String courseCode="";
	private static final String FLT_CODE_ID = "id";
	boolean isEditResource=false;

	PopupPanel centuryPopup=new PopupPanel();
	Map<Long, String> centurySelectedValues = new HashMap<Long, String>();
	AddCenturyPresenter centuryPresenterWidget=AppClientFactory.getInjector().getAddCenturyPresenterWidget();

	ArrayList<checkboxSelectedDo> depthOfKnowledges= new ArrayList<checkboxSelectedDo>();
	private HandlerRegistration handlerRegistration=null;

	public QuestionTypeView() {
		initializeAutoSuggestedBox();
		setWidget(uiBinder.createAndBindUi(this));
		setHeaderAndBodyText("EQ");
		questionTypeHeader.getElement().setId("lblQuestionTypeHeader");
		questionTypeText.getElement().setId("lblQuestionTypeText");
		questionText.getElement().setId("pnlQuestionText");
		questionText.getElement().setInnerHTML(" "+i18n.GL0863());
		questionText.getElement().setAttribute("alt", i18n.GL0863());
		questionText.getElement().setAttribute("title", i18n.GL0863());
		questionNameTextArea.getElement().setId("tinyMCEQuestionNameTextArea");
		questionNameTextArea.getElement().setAttribute("maxlength", "500");
		questionNameTextArea.markAsBlankPanel.setVisible(false);
		questionNameErrorLbl.getElement().setId("errlblQuestionNameErrorLbl");
		addQuestionImg.setText(i18n.GL0860());
		addQuestionImg.getElement().setAttribute("alt", i18n.GL0860());
		addQuestionImg.getElement().setAttribute("title", i18n.GL0860());
		addQuestionImg.getElement().setId("lnkAddQuestionImg");
		addQuestImgContainer.getElement().setId("pnlAddQuestImgContainer");
		questionHotSpotAnswerChoiceContainer.getElement().setId("pnlQuestionHotSpotAnswerChoiceContainer");
		setTextForTheFields();
		setHotSpotAnswerFields();

		advancedContainer.getElement().setId("pnladvancedContainer");
		addHintsAnc.setText(i18n.GL3210() +i18n.GL_SPL_OPEN_SMALL_BRACKET()+5+i18n.GL3207()+i18n.GL_SPL_CLOSE_SMALL_BRACKET());
		addHintsAnc.getElement().setAttribute("alt", i18n.GL3210());
		addHintsAnc.getElement().setAttribute("title", i18n.GL3210());
		addExplanationAnc.setText( i18n.GL3208());
		addExplanationAnc.getElement().setAttribute("alt",  i18n.GL3208());
		addExplanationAnc.getElement().setAttribute("title", i18n.GL3208());
		addDepthOfKnowledgeAnc.setText(i18n.GL3209());
		addDepthOfKnowledgeAnc.getElement().setAttribute("alt", i18n.GL3209());
		addDepthOfKnowledgeAnc.getElement().setAttribute("title", i18n.GL3209());
		addStandardsAnc.setText(i18n.GL0575());
		addStandardsAnc.getElement().setAttribute("alt", i18n.GL0575());
		addStandardsAnc.getElement().setAttribute("title", i18n.GL0575());
		addCenturyAnc.setText(i18n.GL3199());
		addCenturyAnc.getElement().setAttribute("alt", i18n.GL3199());
		addCenturyAnc.getElement().setAttribute("title", i18n.GL3199());
		advancedLbl.setText(i18n.GL3096());
		advancedLbl.getElement().setAttribute("alt", i18n.GL3096());
		advancedLbl.getElement().setAttribute("title", i18n.GL3096());
		setUpAdvancedLbl.setText(i18n.GL3211());
		setUpAdvancedLbl.getElement().setAttribute("alt", i18n.GL3211());
		setUpAdvancedLbl.getElement().setAttribute("title", i18n.GL3211());


		/**
		 * Explanation
		 */
		explanationContainer.getElement().setId("fpnlExplanationContainer");
		explainationErrorLbl.getElement().setId("errlblExplainationErrorLbl");
		eHearderIconExplanation.getElement().setId("eHearderIconExplanation");
		eHearderIconExplanation.addClickHandler(new MinimizePanelsClickHandler());

		explanationContainer.setVisible(false);
		addExplanationAnc.addStyleName("advancedOptionsTabs");
		addExplanationAnc.removeStyleName("advancedOptionsTabActive");
		explanationContainer.getElement().setId("fpnlExplanationContainer");

		explanationLabel.setText(" "+i18n.GL0867());
		explanationLabel.getElement().setId("lblExplanationLabel");
		explanationLabel.getElement().setAttribute("alt", i18n.GL0867());
		explanationLabel.getElement().setAttribute("title", i18n.GL0867());
		explanationLabel.getElement().getStyle().setDisplay(Display.INLINE);
		explainationErrorLbl.getElement().setId("errlblExplainationErrorLbl");
		/**
		 * hints
		 */
		hintsContainer.getElement().setId("pnlHintsContainer");
		errorMessageForHintsCheck.getElement().setId("errlblErrorMessageForHintsCheck");

		/**
		 * Depth of Knowledge
		 */
		depthOfKnowledgeContainer.setVisible(false);
		addDepthOfKnowledgeAnc.addStyleName("advancedOptionsTabs");
		addDepthOfKnowledgeAnc.removeStyleName("advancedOptionsTabActive");

		eHearderIconDepthOfKnowledge.getElement().setId("eHearderIconDepthOfKnowledge");
		eHearderIconDepthOfKnowledge.addClickHandler(new MinimizePanelsClickHandler());

		depthOfKnowledgeHeader.getElement().setId("lblDepthOfKnowledgeHeader");
		depthOfKnoweldgeToolTip.getElement().setId("imgDepthOfKnoweldgeToolTip");
		depthOfKnowledgeTitle.getElement().setId("lblDepthOfKnowledgeTitle");


		depthOfKnowledgeHeader.setText(i18n.GL1693());
		depthOfKnowledgeHeader.getElement().setAttribute("alt", i18n.GL1693());
		depthOfKnowledgeHeader.getElement().setAttribute("title", i18n.GL1693());

		chkLevelRecall.getElement().setId("chkChkLevelRecall");
		chkLevelSkillConcept.getElement().setId("chkChkLevelSkillConcept");
		chkLevelStrategicThinking.getElement().setId("chkChkLevelStrategicThinking");
		chkLevelExtendedThinking.getElement().setId("chkChkLevelExtendedThinking");

		chkLevelRecall.setText(i18n.GL1645());
		chkLevelRecall.getElement().setAttribute("alt", i18n.GL1645());
		chkLevelRecall.getElement().setAttribute("title", i18n.GL1645());
		chkLevelSkillConcept.setText(i18n.GL1646());
		chkLevelSkillConcept.getElement().setAttribute("alt", i18n.GL1646());
		chkLevelSkillConcept.getElement().setAttribute("title", i18n.GL1646());
		chkLevelStrategicThinking.setText(i18n.GL1647());
		chkLevelStrategicThinking.getElement().setAttribute("alt", i18n.GL1647());
		chkLevelStrategicThinking.getElement().setAttribute("title", i18n.GL1647());
		chkLevelExtendedThinking.setText(i18n.GL1648());
		chkLevelExtendedThinking.getElement().setAttribute("alt", i18n.GL1648());
		chkLevelExtendedThinking.getElement().setAttribute("title", i18n.GL1648());
		depthOfKnoweldgeToolTip.setUrl("images/mos/questionmark.png");
		depthOfKnoweldgeToolTip.setTitle("Question Mark");
		depthOfKnoweldgeToolTip.getElement().setAttribute("alt", "Question Mark");
		depthOfKnoweldgeToolTip.getElement().setAttribute("title", "Question Mark");

		/**
		 * Standards
		 */
		standardContainer.getElement().setId("fpnlStandardContainer");
		standardContainer.setVisible(false);
		addStandardsAnc.addStyleName("advancedOptionsTabs");
		addStandardsAnc.removeStyleName("advancedOptionsTabActive");
		eHearderIconStandards.getElement().setId("eHearderIconStandards");
		eHearderIconStandards.addClickHandler(new MinimizePanelsClickHandler());
		standardsDefaultText.getElement().setId("lblStandardsDefaultText");
		standardSgstBox.getElement().setId("appSuggestBoxStandardSgstBox");
		standardSgstBox.getElement().setAttribute("style", "box-sizing:content-box;width:85%;height:19px");

		standardsDefaultText.setText(i18n.GL1682());
		standardsDefaultText.getElement().setAttribute("alt", i18n.GL1682());
		standardsDefaultText.getElement().setAttribute("title", i18n.GL1682());
		browseStandards.addClickHandler(new onBrowseStandardsClick());
		errorContainer.setVisible(false);
		errorContainer.add(standardsPreferenceOrganizeToolTip);
		standardMaxMsg.getElement().setId("lblStandardMaxMsg");
		standardsPanel.getElement().setId("fpnlStandardsPanel");

		/**
		 * century
		 */
		centuryContainer.setVisible(false);
		addCenturyAnc.addStyleName("advancedOptionsTabs");
		addCenturyAnc.removeStyleName("advancedOptionsTabActive");
		eHearderIconCentury.getElement().setId("eHearderIconCentury");
		eHearderIconCentury.addClickHandler(new MinimizePanelsClickHandler());
		centuryDefaultText.setText(i18n.GL3199());
		centuryDefaultText.getElement().setAttribute("alt", i18n.GL3199());
		centuryDefaultText.getElement().setAttribute("title", i18n.GL3199());
		centurySgstBox.getElement().setAttribute("style", "box-sizing:content-box;width:85%;height:19px");

		setCenturyData();
	}

	public void setTextForTheFields(){
		String value = StringUtil.generateMessage(i18n.GL2103(), "500");
		charLimitLbl.setText(value);
		StringUtil.setAttributes(charLimitLbl.getElement(), "charLimitLbl", value, value);

		charLimitExplanation.setText(value);
		StringUtil.setAttributes(charLimitExplanation.getElement(), "charLimitExplanation", value, value);

		addClickEventsForCheckBox();

		AppClientFactory.getInjector().getUserService().getUserProfileV2Details(AppClientFactory.getGooruUid(),USER_META_ACTIVE_FLAG,new SimpleAsyncCallback<ProfileDo>() {

			@Override
			public void onSuccess(ProfileDo profileObj) {
				if(profileObj.getUser().getMeta().getTaxonomyPreference().getCodeId()!=null){
					if(profileObj.getUser().getMeta().getTaxonomyPreference().getCodeId().size()==0){
						//standardContainer.setVisible(true);
						isBrowseTooltip = true;
						DisableStandars();
					}else
					{
						//standardContainer.setVisible(true);
						isBrowseTooltip = false;
						enableStandards();
						standardPreflist=new ArrayList<String>();
						for (String code : profileObj.getUser().getMeta().getTaxonomyPreference().getCode()) {
							standardPreflist.add(code);
							standardPreflist.add(code.substring(0, 2));
						}

					}
				}else{
					//standardContainer.setVisible(true);
					isBrowseTooltip = true;
					DisableStandars();
				}
			}

		});
	}


	public void setHeaderAndBodyText(String tabType){
		if(tabType.equals("EQ")){
			questionTypeHeader.setText(i18n.GL3226());
			questionTypeHeader.getElement().setAttribute("alt", i18n.GL3226());
			questionTypeHeader.getElement().setAttribute("title", i18n.GL3226());
			questionTypeText.setText(i18n.GL0350());
			questionTypeText.getElement().setAttribute("alt", i18n.GL0350());
			questionTypeText.getElement().setAttribute("title", i18n.GL0350());
		}
	}


	@UiHandler("addQuestionImg")
	public void clickOnAddQuestImg(ClickEvent event){

		if(addQuestImgContainer.getElement().hasChildNodes()){
			addQuestImgContainer.setVisible(true);
			addQuestionImg.setVisible(false);
			setImageContainer();

		}else{
			uploadQuestionImage();
		}


	}

	public void setImageContainer(){

		if(addQuestImgContainer.getElement().hasChildNodes()){
			addQuestionImg.removeStyleName("ancTab");
			addQuestionImg.addStyleName("ancTabActive");
		}else{
			addQuestionImg.addStyleName("ancTab");
			addQuestionImg.removeStyleName("ancTabActive");
		}
	}

	public void uploadQuestionImage(){
		getUiHandlers().questionImageUpload();
	}


	@Override
	public void getRevealType() {
		System.out.println("");
	}

	@Override
	public void setImageUrl(String fileName,String fileNameWithoutRepository,boolean isQuestionImage,boolean isUserOwnResourceImage){
		double randNumber = Math.random();

		if(isQuestionImage){
			AddQuestionImg addQuestionImage = new AddQuestionImg();
			addQuestionImage.setQuestionImage(fileName+"?"+randNumber);
			addQuestionImage.setFileName(fileNameWithoutRepository);
			addQuestImgContainer.clear();
			addQuestionImg.getElement().getStyle().setDisplay(Display.NONE);
			addQuestImgContainer.add(addQuestionImage);
			setImageHandler();


			addQuestionImage.getChangeImgLbl().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if(getQuestionEditMode()){
						getUiHandlers().questionImageUpload(collectionItemDo.getCollectionItemId());
					}else{
						getUiHandlers().questionImageUpload();
					}
				}
			});
			addQuestionImage.getRemoveImgLbl().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					boolean isQuestionEditMode=getQuestionEditMode();
					if(isQuestionEditMode){
						deleteConfirmationPopup=new DeleteConfirmationPopupVc(MESSAGE_HEADER,MESSAGE_CONTENT);
					}else{
						addQuestImgContainer.clear();
						addQuestionImg.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
					}
				}
			});
		}
		else{
		}
	}


	public void setImageHandler(){
		if(addQuestImgContainer.getElement().hasChildNodes()){
			AddQuestionImg addQuestionImage=(AddQuestionImg)addQuestImgContainer.getWidget(0);
			addQuestionImage.geteHearderIconImage().addClickHandler(new MinimizePanelsClickHandler());
		}
	}

	private class MinimizePanelsClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			if(event.getRelativeElement().getId().equalsIgnoreCase("eHearderIconImage")){
				addQuestImgContainer.setVisible(false);
				addQuestionImg.setVisible(true);
				setImageContainer();
			}else if(event.getRelativeElement().getId().equalsIgnoreCase("eHearderIconExplanation")){
				explanationContainer.setVisible(false);
				addExplanationAnc.setVisible(true);
				addExplanationAnc.setText(i18n.GL3208());
				setExplanationContainer();
			}else if(event.getRelativeElement().getId().equalsIgnoreCase("eHearderIconHint")){
				hintsContainer.setVisible(false);
				int widgetsCount=hintsContainer.getWidgetCount();
				for(int i=0;i<widgetsCount;){
					AddHintsView addHintsView =(AddHintsView) hintsContainer.getWidget(i);
					if(addHintsView.hintTextBox.getText().equalsIgnoreCase("")){
						addHintsView.removeFromParent();
						widgetsCount=hintsContainer.getWidgetCount();
						i--;
					}else{
						addHintsView.showHintsMessage(i+1);
						addHintsAnc.removeStyleName("advancedOptionsTabs");
						addHintsAnc.addStyleName("advancedOptionsTabActive");
					}

					i++;
				}
				int count=hintsContainer.getWidgetCount();
				addHintsAnc.setText(i18n.GL3210()+i18n.GL_SPL_OPEN_SMALL_BRACKET()+(5-count)+i18n.GL3207()+i18n.GL_SPL_CLOSE_SMALL_BRACKET());

			}else if(event.getRelativeElement().getId().equalsIgnoreCase("eHearderIconDepthOfKnowledge")){
				depthOfKnowledgeContainer.setVisible(false);
				addDepthOfKnowledgeAnc.setVisible(true);
				addDepthOfKnowledgeAnc.setText(i18n.GL3209());
				setDepthOfKnowledgeContainer();
			}else if(event.getRelativeElement().getId().equalsIgnoreCase("eHearderIconStandards")){
				standardContainer.setVisible(false);
				addStandardsAnc.setVisible(true);
				addStandardsAnc.setText(i18n.GL0575());
				setStandardsContainer();
			}else if(event.getRelativeElement().getId().equalsIgnoreCase("eHearderIconCentury")){
				centuryContainer.setVisible(false);
				addCenturyAnc.setVisible(true);
				addCenturyAnc.setText(i18n.GL3199());
				setCenturyContainer();
			}
		}
	}

	public boolean getQuestionEditMode(){
		return collectionItemDo!=null?true:false;
	}
	private class DeleteConfirmationPopupVc extends ConfirmationPopupVc{
		public DeleteConfirmationPopupVc(String messageHeader,String messageContent){
			super(messageHeader, messageContent);
			setPopupZindex(9999);
			setGlassZindex(9998);
			setScrollDisable();
		}
		@Override
		public void onDelete(ClickEvent clickEvent) {
			setImageContainer();
			getUiHandlers().removeQuestionImage(collectionItemDo.getCollectionItemId());
		}
		public void hide() {
			super.hide();

		}
	}

	public void setHotSpotAnswerFields(){
		questionHotSpotAnswerChoiceContainer.clear();
		int widgetCount=questionHotSpotAnswerChoiceContainer.getWidgetCount();
		final AddHotSpotQuestionAnswerChoice addQuestionAnswer=new AddHotSpotQuestionAnswerChoice();
		/*addQuestionAnswer.imageRDButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					int widgetCount=questionHotSpotAnswerChoiceContainer.getWidgetCount();
					for(int i=0;i<widgetCount;i++){
						if(i==0){
						}else{
							questionHotSpotAnswerChoiceContainer.remove(i);
						widgetCount=questionHotSpotAnswerChoiceContainer.getWidgetCount();
						i--;
						}
					}
					addQuestionAnswer.imageRDButtonClick();
				}
			});

			addQuestionAnswer.textRDButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					addQuestionAnswer.textRDButtonClick();
				}
			});

			addQuestionAnswer.singleRDButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					addQuestionAnswer.singleRDButtonClick();
				}
			});
			addQuestionAnswer.multiRDButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					addQuestionAnswer.multiRDButtonClick();
				}
			});

				addQuestionAnswer.setHeadLabelFields(true);
				addQuestionAnswer.imageRDButton.setValue(true);
		 */

		questionHotSpotAnswerChoiceContainer.add(addQuestionAnswer);
	}

	public void setExplanationContainer(){
		if(explainationTextArea.getText().isEmpty() || explainationTextArea.getText().trim().length()==0){
			addExplanationAnc.addStyleName("advancedOptionsTabs");
			addExplanationAnc.removeStyleName("advancedOptionsTabActive");
		}else{
			addExplanationAnc.removeStyleName("advancedOptionsTabs");
			addExplanationAnc.addStyleName("advancedOptionsTabActive");
		}

	}

	public void setHintsContainer(){
		if(hintsContainer.getWidgetCount()>0){
			addHintsAnc.removeStyleName("advancedOptionsTabs");
			addHintsAnc.addStyleName("advancedOptionsTabActive");
		}else{
			addHintsAnc.addStyleName("advancedOptionsTabs");
			addHintsAnc.removeStyleName("advancedOptionsTabActive");
		}
	}

	public void setDepthOfKnowledgeContainer(){

		boolean isSelected=false;
		if(chkLevelRecall.isChecked() || chkLevelSkillConcept.isChecked() || chkLevelStrategicThinking.isChecked() || chkLevelExtendedThinking.isChecked()){
			isSelected=true;
		}

		if(isSelected){
			addDepthOfKnowledgeAnc.removeStyleName("advancedOptionsTabs");
			addDepthOfKnowledgeAnc.addStyleName("advancedOptionsTabActive");
		}else{
			addDepthOfKnowledgeAnc.addStyleName("advancedOptionsTabs");
			addDepthOfKnowledgeAnc.removeStyleName("advancedOptionsTabActive");
		}

	}


	@UiHandler("addExplanationAnc")
	public void clickOnExplanationAnc(ClickEvent event){
		addExplanationAnc.setVisible(false);
		addExplanationAnc.addStyleName("advancedOptionsTab");
		addExplanationAnc.removeStyleName("advancedOptionsTabActive");
		explanationContainer.setVisible(true);
	}

	public void resetToHints(){
		int widgetsCount=hintsContainer.getWidgetCount();
		for(int i=0;i<widgetsCount;){
			AddHintsView addHintsView =(AddHintsView) hintsContainer.getWidget(i);
			//addHintsView.hintTextBox.setText("");
			if(i<0){			
				if(i==0){
					addHintsView.hintsTextLblVal.setText("");
				}else{
					addHintsView.hintsTextLblVal.setText("");
				}
				i++;
			}else{
				addHintsView.removeFromParent();
				widgetsCount=hintsContainer.getWidgetCount();
			}

		}
	}

	private void addHintsTextArea(final AddHintsView addHints){

		hintsContainer.add(addHints); 
		addHints.hintDelLbl.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) 
			{    
				addHints.removeFromParent();
				// removeToolTip.hide();
				refreshHintNumber();
				// isAddBtnClicked=true;
			}
		});

		addHints.addMouseOverHandler(new MouseOverHandler() {

			@Override
			public void onMouseOver(MouseOverEvent event) {
				addHints.hintDelLbl.getElement().getStyle().setDisplay(Display.BLOCK);

			}
		});
		addHints.addMouseOutHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {
				addHints.hintDelLbl.getElement().getStyle().setDisplay(Display.NONE);
			}
		});
		showRemoveToolTip(addHints.hintDelLbl);

		if(hintsContainer.getWidgetCount()>=5){
			addHintsAnc.getElement().getStyle().setDisplay(Display.NONE);
		}

		addHints.eHearderIconHint.addClickHandler(new MinimizePanelsClickHandler());

	}



	protected void refreshHintNumber() 
	{
		int hintWidgetsCount=hintsContainer.getWidgetCount();
		for(int i=0 ; i<hintWidgetsCount;i++)
		{
			Widget childWidget=hintsContainer.getWidget(i);
			AddHintsView addHints=(AddHintsView)childWidget;
			addHints.hintNumLbl.setText(""+(i+1));
			if(i==0){
				addHints.eHearderIconHint.setVisible(true);
				addHints.hintsTextLblVal.setText(i18n.GL0859());
			}

		}
		if(hintsContainer.getWidgetCount()<5){
			addHintsAnc.addStyleName("advancedOptionsTabs");
			addHintsAnc.removeStyleName("advancedOptionsTabActive");
			addHintsAnc.setText(i18n.GL3210()+i18n.GL_SPL_OPEN_SMALL_BRACKET()+(5-hintWidgetsCount)+i18n.GL3207()+i18n.GL_SPL_CLOSE_SMALL_BRACKET());
			addHintsAnc.getElement().getStyle().setDisplay(Display.BLOCK);
		}

	}

	public void showRemoveToolTip(final Label deleteButton){
		deleteButton.addMouseOverHandler(new MouseOverHandler() {

			@Override
			public void onMouseOver(MouseOverEvent event) {
				removeToolTip=new RemoveToolTipUc();	                
				int left=event.getRelativeElement().getAbsoluteLeft()-16;
				int top=event.getRelativeElement().getAbsoluteTop()+27;
				removeToolTip.setPopupPosition(left, top);
				removeToolTip.getElement().getStyle().setZIndex(999);
				removeToolTip.show();
			}
		});
		deleteButton.addMouseOutHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {
				removeToolTip.hide();
			}
		});
	}

	@UiHandler("addHintsAnc")
	public void clickOnHintsLabel(ClickEvent event){
		Window.enableScrolling(false);
		hintsContainer.setVisible(true);
		int widgetCount=hintsContainer.getWidgetCount();
		addHintsAnc.addStyleName("advancedOptionsTabs");
		addHintsAnc.removeStyleName("advancedOptionsTabActive");
		addHintsAnc.setText(i18n.GL3210()+i18n.GL_SPL_OPEN_SMALL_BRACKET()+(4-widgetCount)+i18n.GL3207()+i18n.GL_SPL_CLOSE_SMALL_BRACKET());
		final AddHintsView addHints = new AddHintsView(widgetCount+1);
		addHintsTextArea(addHints);
	}

	public void addClickEventsForCheckBox(){
		chkLevelRecall.addClickHandler(new AddCheckBoxClickHandler());
		chkLevelSkillConcept.addClickHandler(new AddCheckBoxClickHandler());
		chkLevelStrategicThinking.addClickHandler(new AddCheckBoxClickHandler());
		chkLevelExtendedThinking.addClickHandler(new AddCheckBoxClickHandler());
		depthOfKnoweldgeToolTip.addMouseOverHandler(new MouseOverHandler() {

			@Override
			public void onMouseOver(MouseOverEvent event) {
				toolTip = new ToolTip(i18n.GL1734());
				toolTip.getLblLink().setVisible(false);
				toolTip.getElement().getStyle().setBackgroundColor("transparent");
				toolTip.getElement().getStyle().setPosition(Position.ABSOLUTE);
				toolTip.setPopupPosition(depthOfKnoweldgeToolTip.getAbsoluteLeft()-(50+22), depthOfKnoweldgeToolTip.getAbsoluteTop()+22);
				toolTip.getElement().getStyle().setZIndex(1111);
				toolTip.show();
			}
		});
		depthOfKnoweldgeToolTip.addMouseOutHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {

				EventTarget target = event.getRelatedTarget();
				if (Element.is(target)) {
					if (!toolTip.getElement().isOrHasChild(Element.as(target))){
						toolTip.hide();
					}
				}
			}
		});
	}
	public class AddCheckBoxClickHandler implements ClickHandler  {

		@Override
		public void onClick(ClickEvent event) {
			CheckBox checkBox = (CheckBox) event.getSource();
			boolean checked = checkBox.getValue();		

			depthOfKnowledges.clear();

			if(chkLevelRecall.isChecked())
			{
				checkboxSelectedDo depthObj=new checkboxSelectedDo();
				depthObj.setSelected(true);
				depthObj.setValue(chkLevelRecall.getText());
				depthOfKnowledges.add(depthObj); 
			}
			else
			{
				checkboxSelectedDo depthObj=new checkboxSelectedDo();
				depthObj.setSelected(false);
				depthObj.setValue(chkLevelRecall.getText());
				depthOfKnowledges.add(depthObj);  
			}

			if(chkLevelSkillConcept.isChecked())
			{
				checkboxSelectedDo depthObj=new checkboxSelectedDo();
				depthObj.setSelected(true);
				depthObj.setValue(chkLevelSkillConcept.getText());
				depthOfKnowledges.add(depthObj); 
			}
			else
			{
				checkboxSelectedDo depthObj=new checkboxSelectedDo();
				depthObj.setSelected(false);
				depthObj.setValue(chkLevelSkillConcept.getText());
				depthOfKnowledges.add(depthObj);  
			}

			if(chkLevelStrategicThinking.isChecked())
			{
				checkboxSelectedDo depthObj=new checkboxSelectedDo();
				depthObj.setSelected(true);
				depthObj.setValue(chkLevelStrategicThinking.getText());
				depthOfKnowledges.add(depthObj); 
			}
			else
			{
				checkboxSelectedDo depthObj=new checkboxSelectedDo();
				depthObj.setSelected(false);
				depthObj.setValue(chkLevelStrategicThinking.getText());
				depthOfKnowledges.add(depthObj);  
			}

			if(chkLevelExtendedThinking.isChecked())
			{
				checkboxSelectedDo depthObj=new checkboxSelectedDo();
				depthObj.setSelected(true);
				depthObj.setValue(chkLevelExtendedThinking.getText());
				depthOfKnowledges.add(depthObj); 
			}
			else
			{
				checkboxSelectedDo depthObj=new checkboxSelectedDo();
				depthObj.setSelected(false);
				depthObj.setValue(chkLevelExtendedThinking.getText());
				depthOfKnowledges.add(depthObj);  
			}


		}
	}
	@UiHandler("addDepthOfKnowledgeAnc")
	public void clickOnaddDepthOfKnowledgeAnc(ClickEvent event){
		addDepthOfKnowledgeAnc.setVisible(false);
		addDepthOfKnowledgeAnc.addStyleName("advancedOptionsTabs");
		addDepthOfKnowledgeAnc.removeStyleName("advancedOptionsTabActive");
		depthOfKnowledgeContainer.setVisible(true);
	}


	public void setStandardsContainer(){
		if(standardsPanel.getWidgetCount()>0){
			addStandardsAnc.removeStyleName("advancedOptionsTabs");
			addStandardsAnc.addStyleName("advancedOptionsTabActive");
		}else{
			addStandardsAnc.addStyleName("advancedOptionsTabs");
			addStandardsAnc.removeStyleName("advancedOptionsTabActive");
		}
	}


	public void initializeAutoSuggestedBox(){
		standardSuggestOracle = new AppMultiWordSuggestOracle(true);
		centurySuggestOracle= new AppMultiWordSuggestOracle(true);
		standardSearchDo.setPageSize(10);
		standardSgstBox = new AppSuggestBox(standardSuggestOracle) {
			@Override
			public void keyAction(String text,KeyUpEvent event) {
				text=text.toUpperCase();
				//standardsPreferenceOrganizeToolTip.hide();
				errorContainer.setVisible(false);
				standardSearchDo.setSearchResults(null);
				boolean standardsPrefDisplayPopup = false;
				standardSgstBox.hideSuggestionList();
				if(!courseCode.isEmpty()) {
					Map<String,String> filters = new HashMap<String, String>();
					filters.put(FLT_CODE_ID,courseCode);
					standardSearchDo.setFilters(filters);
				}
				standardSearchDo.setQuery(text);
				if (text != null && text.trim().length() > 0) {
					errorContainer.setVisible(false);
					if(standardPreflist!=null){
						for(int count=0; count<standardPreflist.size();count++) {
							if(text.contains(standardPreflist.get(count))) {
								standardsPrefDisplayPopup = true;
								break;
							} else {
								standardsPrefDisplayPopup = false;
							}
						}						
					}
					if(standardsPrefDisplayPopup){
						errorContainer.setVisible(false);
						AppClientFactory.getInjector().getSearchService().getSuggestStandardByFilterCourseId(standardSearchDo, new SimpleAsyncCallback<SearchDo<CodeDo>>() {
							@Override
							public void onSuccess(SearchDo<CodeDo> result) {
								setStandardSuggestions(result);

							}
						});

						standardSgstBox.showSuggestionList();
					}
					else{
						errorContainer.setVisible(true);
						standardSgstBox.hideSuggestionList();
						standardSuggestOracle.clear();
					}
				}
			}
			@Override
			public HandlerRegistration addClickHandler(ClickHandler handler) {
				return null;
			}
		};
		standardSgstBox.addSelectionHandler(this);
		BlurHandler blurHandler=new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				if(standardsPreferenceOrganizeToolTip.isShowing()){
					errorContainer.setVisible(false);
				}
			}
		};
		standardSgstBox.addDomHandler(blurHandler, BlurEvent.getType());
		centurySgstBox=new AppSuggestBox(centurySuggestOracle) {

			@Override
			public HandlerRegistration addClickHandler(ClickHandler handler) {
				return null;
			}

			@Override
			public void keyAction(String text, KeyUpEvent event) {
				text=text.toUpperCase();
				centurySearchDo.setSearchResults(null);
				centurySearchDo.setQuery(text);
				if (text != null && text.trim().length() > 0) {
					AppClientFactory.getInjector().getSearchService().getSuggestCenturyByQuery(centurySearchDo, new AsyncCallback<SearchDo<StandardFo>>() {

						@Override
						public void onSuccess(SearchDo<StandardFo> result) {
							setCenturySuggestions(result);

						}

						@Override
						public void onFailure(Throwable caught) {

						}							
					});
					centurySgstBox.showSuggestionList();
				}
			}
		};
		centurySgstBox.getElement().getStyle().setFontSize(12, Unit.PX);
		centurySgstBox.getTextBox().getElement().setAttribute("placeholder", i18n.GL3122_1());

		BlurHandler blurHandlerCentury=new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				if(standardsPreferenceOrganizeToolTip.isShowing()){
					errorContainer.setVisible(false);
				}
			}
		};

		centurySgstBox.addDomHandler(blurHandlerCentury, BlurEvent.getType());
		centurySgstBox.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {

			@Override
			public void onSelection(SelectionEvent<Suggestion> event) {
				addCentury(centurySgstBox.getValue(), getCodeIdByCodeCentury(centurySgstBox.getValue(), centurySearchDo.getSearchResults()));
				centurySgstBox.setText("");
				centurySuggestOracle.clear();
			}
		});
	}

	@Override
	public void onSelection(SelectionEvent<Suggestion> event) {
		addStandard(standardSgstBox.getValue(), getCodeIdByCode(standardSgstBox.getValue(), standardSearchDo.getSearchResults()));
		standardSgstBox.setText("");
		standardSuggestOracle.clear();
	}
	private static String getCodeIdByCode(String code, List<CodeDo> codes) {
		if (codes != null) {
			for (CodeDo codeDo : codes) {
				if (code.equals(codeDo.getCode())) {
					return codeDo.getCodeId() + "";
				}
			}
		}
		return null;
	}
	private static String getCodeIdByCodeCentury(String code, List<StandardFo> codes) {
		if (codes != null) {
			for (StandardFo codeDo : codes) {
				if (code.equals(codeDo.getLabel())) {
					return codeDo.getCodeId() + "";
				}
			}
		}
		return null;
	}
	/**
	 * Adding new standard for the collection , will check it has more than
	 * fifteen standards
	 * 
	 * @param standard
	 *            which to be added for the collection
	 */
	public void addStandard(String standard, String id) {
		if (standardsPanel.getWidgetCount() <5) {
			if (standard != null && !standard.isEmpty()) {
				CodeDo codeObj=new CodeDo();
				codeObj.setCodeId(Integer.parseInt(id));
				codeObj.setCode(standard);
				standardsDo.add(codeObj);
				standardsPanel.add(createStandardLabel(standard, id, standardCodesMap.get(id)));
			}
		} else {
			standardMaxShow();
			standardSgstBox.setText("");
		}
	}
	public void addCentury(String centuryTag, String id) {
		if (centuryTag != null && !centuryTag.isEmpty()) {
			String codeIdVal = getCodeIdByCodeCentury(centurySgstBox.getValue(), centurySearchDo.getSearchResults());				
			CodeDo codeObjStandard=new CodeDo();
			codeObjStandard.setCodeId(Integer.parseInt(codeIdVal));
			codeObjStandard.setCode(centurySgstBox.getValue());
			standardsDo.add(codeObjStandard);

			centurySelectedValues.put(Long.parseLong(codeIdVal),centurySgstBox.getValue());
			centuryPanel.add(create21CenturyLabel(centuryTag, id, centuryCodesMap.get(id)));
		}
	}
	/**
	 * new label is created for the standard which needs to be added
	 * 
	 * @param standardCode
	 *            update standard code
	 * @return instance of {@link DownToolTipWidgetUc}
	 */
	public DownToolTipWidgetUc createStandardLabel(final String standardCode, final String id, String description) {
		CloseLabel closeLabel = new CloseLabel(standardCode) {

			@Override
			public void onCloseLabelClick(ClickEvent event) {
				for(final CodeDo codeObj:standardsDo){
					if(isEditResource){
						if(codeObj.getCodeId()==Integer.parseInt(id)){
							AppClientFactory.getInjector().getResourceService().deleteTaxonomyResource(collectionItemDo.getResource().getGooruOid(), codeObj.getCodeId(), new SimpleAsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									CodeDo deletedObj=new CodeDo();
									deletedObj.setCodeId(codeObj.getCodeId());
									deletedStandardsDo.add(deletedObj);
									standardsDo.remove(codeObj);
								}
							});
						}
					}else
					{
						if(codeObj.getCodeId()==Integer.parseInt(id)){
							standardsDo.remove(codeObj);
						}
					}
				}
				this.getParent().removeFromParent();
			}
		};
		return new DownToolTipWidgetUc(closeLabel, description);
	}
	public void standardMaxShow() {
		standardSgstBox.addStyleName("standardTxtBox");
		standardMaxMsg.setStyleName("standardMax");
		standardsPanel.addStyleName("floatLeftNeeded");
		new FadeInAndOut(standardMaxMsg.getElement(), 5000, 5000);
	}
	public void setStandardSuggestions(SearchDo<CodeDo> standardSearchDo) {
		standardSuggestOracle.clear();
		this.standardSearchDo = standardSearchDo;
		if (this.standardSearchDo.getSearchResults() != null) {
			List<String> sources = getAddedStandards(standardsPanel);
			for (CodeDo code : standardSearchDo.getSearchResults()) {
				if (!sources.contains(code.getCode())) {
					standardSuggestOracle.add(code.getCode());
				}
				standardCodesMap.put(code.getCodeId() + "", code.getLabel());
			}
		}
		standardSgstBox.showSuggestionList();
	}
	public void setCenturySuggestions(SearchDo<StandardFo> centurySearchDo) {
		centurySuggestOracle.clear();
		this.centurySearchDo = centurySearchDo;
		if (this.centurySearchDo.getSearchResults() != null) {
			List<String> sources = getAddedCentury(centuryPanel);
			for (StandardFo code : centurySearchDo.getSearchResults()) {
				if (!sources.contains(code.getLabel())) {
					centurySuggestOracle.add(code.getLabel());
				}
				centuryCodesMap.put(code.getCodeId() + "", code.getLabel());
			}
		}
		centurySgstBox.showSuggestionList();
	}
	/**
	 * get the standards are added for collection
	 * 
	 * @param flowPanel
	 *            having all added standards label
	 * @return standards text in list which are added for the collection
	 */
	private List<String> getAddedStandards(FlowPanel flowPanel) {
		List<String> suggestions = new ArrayList<String>();
		for (Widget widget : flowPanel) {
			if (widget instanceof DownToolTipWidgetUc) {
				suggestions.add(((CloseLabel) ((DownToolTipWidgetUc) widget).getWidget()).getSourceText());
			}
		}
		return suggestions;
	}
	/**
	 * get the standards are added for collection
	 * 
	 * @param flowPanel
	 *            having all added standards label
	 * @return standards text in list which are added for the collection
	 */
	private List<String> getAddedCentury(FlowPanel flowPanel) {
		List<String> suggestions = new ArrayList<String>();
		for (Widget widget : flowPanel) {
			if (widget instanceof DownToolTipWidgetUc) {
				suggestions.add(((CloseLabelCentury) ((DownToolTipWidgetUc) widget).getWidget()).getSourceText());
			}
		}
		return suggestions;
	}

	private class onBrowseStandardsClick implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			callBrowseStandards();
		}
	}

	public void callBrowseStandards() {
		getUiHandlers().browseStandardsInfo(true, false);
	}

	@Override
	public void OnBrowseStandardsClickEvent(Button addStandardsBtn) {
		if(handlerRegistration!=null){
			handlerRegistration.removeHandler();
		}
		handlerRegistration=addStandardsBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				getUiHandlers().addUpdatedBrowseStandards();
			}
		});
	}

	@Override
	public void setUpdatedStandardsCode(String standardsCodeVal,int id,String desc) {
		if (standardsPanel.getWidgetCount() <5) {
			if (standardsCodeVal != null && !standardsCodeVal.isEmpty()) {
				CodeDo codeObj=new CodeDo();
				codeObj.setCodeId(id);
				codeObj.setCode(standardsCodeVal);
				standardsDo.add(codeObj);
				standardsPanel.add(createStandardLabel(standardsCodeVal, Integer.toString(id), desc));
			}
		} else {
			standardMaxShow();
		}
		closeStandardsPopup();
	}

	public void closeStandardsPopup(){
		getUiHandlers().closeStandardsPopup();
	}
	public void DisableStandars(){
		browseStandardsTooltip=new BrowseStandardsTooltip("To see all standards, please edit your standards preference in","settings");
		browseStandards.getElement().getStyle().setColor("#999");
		browseStandards.getElement().addClassName("disabled");
		browseStandards.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				if(isBrowseTooltip == true){
					browseStandardsTooltip.show();
					browseStandardsTooltip.setPopupPosition(browseStandards.getAbsoluteLeft()+3, browseStandards.getAbsoluteTop()+33);
					browseStandardsTooltip.getElement().getStyle().setZIndex(999999);
					isBrowseStandardsToolTip= true;
				}
			}
		});

		Event.addNativePreviewHandler(new NativePreviewHandler() {
			public void onPreviewNativeEvent(NativePreviewEvent event) {
				hideBrowseStandardsPopup(event);
			}
		});
	}

	public void hideBrowseStandardsPopup(NativePreviewEvent event){
		try{
			if(event.getTypeInt()==Event.ONMOUSEOVER){
				Event nativeEvent = Event.as(event.getNativeEvent());
				boolean target=eventTargetsPopup(nativeEvent);
				if(!target)
				{
					if(isBrowseStandardsToolTip){
						browseStandardsTooltip.hide();
					}
				}
			}
		}catch(Exception ex){}
	}
	private boolean eventTargetsPopup(NativeEvent event) {
		EventTarget target = event.getEventTarget();
		if (Element.is(target)) {
			try{
				return browseStandardsTooltip.getElement().isOrHasChild(Element.as(target));
			}catch(Exception ex){}
		}
		return false;
	}

	public void enableStandards(){
		browseStandards.getElement().getStyle().clearColor();
		browseStandards.getElement().removeClassName("disabled");
	}

	@UiHandler("addStandardsAnc")
	public void clickOnaddStandardsLabel(ClickEvent event){
		addStandardsAnc.setVisible(false);
		addStandardsAnc.addStyleName("advancedOptionsTabs");
		addStandardsAnc.removeStyleName("advancedOptionsTabActive");
		standardContainer.setVisible(true);
	}

	public void setCenturyContainer(){

		if(centuryPanel.getWidgetCount()>0){
			addCenturyAnc.removeStyleName("advancedOptionsTabs");
			addCenturyAnc.addStyleName("advancedOptionsTabActive");
		}else{
			addCenturyAnc.addStyleName("advancedOptionsTabs");
			addCenturyAnc.removeStyleName("advancedOptionsTabActive");
		}
	}

	/**
	 * new label is created for the 21 century which needs to be added
	 * 
	 * @param standardCode
	 *            update standard code
	 * @return instance of {@link DownToolTipWidgetUc}
	 */
	public DownToolTipWidgetUc create21CenturyLabel(final String centuryCode, final String id, String description) {
		CloseLabelCentury closeLabel = new CloseLabelCentury(centuryCode) {
			@Override
			public void onCloseLabelClick(ClickEvent event) {	
				for(final CodeDo codeObj:standardsDo){
					if(id.equalsIgnoreCase(String.valueOf(codeObj.getCodeId()))){
						AppClientFactory.getInjector().getResourceService().deleteTaxonomyResource(collectionItemDo.getResource().getGooruOid(), codeObj.getCodeId(), new SimpleAsyncCallback<Void>() {
							@Override
							public void onSuccess(Void result) {
								CodeDo deletedObj=new CodeDo();
								deletedObj.setCodeId(codeObj.getCodeId());
								deletedStandardsDo.add(deletedObj);
								standardsDo.remove(codeObj);	
								centurySelectedValues.remove(Long.parseLong(id));
							}
						});
						this.getParent().removeFromParent();
						return;
					}
				}
			}
		};
		return new DownToolTipWidgetUc(closeLabel, description);
	}

	/**
	 * This will handle the click event on the browser century
	 * @param e
	 */
	@UiHandler("browseCentury")
	public void onClickOfBrowseCentury(ClickEvent e){
		centuryPopup.clear();
		centuryPresenterWidget.setAddResourceData(centurySelectedValues);
		centuryPopup.add(centuryPresenterWidget.getWidget());
		centuryPopup.show();
		centuryPopup.center();
		centuryPopup.getElement().getStyle().setZIndex(999999);
	}

	public void setCenturyData(){
		//This will hide the popup when clicked on the cancel button
		centuryPresenterWidget.getCancelBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				hideCenturyPopup();
			}
		});
		//This will hide the popup when clicked on close button
		centuryPresenterWidget.getCloseBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				hideCenturyPopup();
			}
		});
		centuryPresenterWidget.getAddButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				centurySelectedValues.clear();
				centurySelectedValues.putAll(centuryPresenterWidget.getSelectedValues());
				centuryPanel.clear();
				if(centurySelectedValues!=null && centurySelectedValues.size()>0){
					for (Map.Entry<Long, String> entry : centurySelectedValues.entrySet()){
						CodeDo codeObjStandard=new CodeDo();
						codeObjStandard.setCodeId(Integer.parseInt(entry.getKey()+""));
						codeObjStandard.setCode(entry.getValue());
						standardsDo.add(codeObjStandard);
						centuryPanel.add(create21CenturyLabel(entry.getValue(),entry.getKey()+"",""));
					}
				}
				hideCenturyPopup();
			}
		});
	}
	/**
	 * This method will hide the century popup
	 */
	public void hideCenturyPopup(){
		centuryPopup.hide();
	}
	  @UiHandler("addCenturyAnc")
	    public void clickOnaddCenturyLabel(ClickEvent event){
	    	addCenturyAnc.setVisible(false);
	    	addCenturyAnc.addStyleName("advancedOptionsTabs");
	    	addCenturyAnc.removeStyleName("advancedOptionsTabActive");
	    	centuryContainer.setVisible(true);
	    }
	    
}