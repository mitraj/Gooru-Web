package org.ednovo.gooru.client.mvp.classpage.teach.edit.coursePopup;

import java.util.HashMap;
import java.util.List;

import org.ednovo.gooru.application.client.PlaceTokens;
import org.ednovo.gooru.application.client.gin.AppClientFactory;
import org.ednovo.gooru.application.shared.i18n.MessageProperties;
import org.ednovo.gooru.application.shared.model.content.ClasspageDo;
import org.ednovo.gooru.application.shared.model.folder.FolderDo;
import org.ednovo.gooru.application.shared.model.folder.FolderListDo;
import org.ednovo.gooru.client.UrlNavigationTokens;
import org.ednovo.gooru.client.mvp.gsearch.util.SuccessPopupForResource;
import org.ednovo.gooru.client.mvp.shelf.list.TreeMenuImages;
import org.ednovo.gooru.shared.util.ClientConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

public class AddCourseToClassView extends PopupViewWithUiHandlers<AddCourseToClassUiHandlers> implements IsAddCourseToClassView,ClientConstants {

	private static SearchAddResourceToCollectionViewUiBinder uiBinder = GWT
			.create(SearchAddResourceToCollectionViewUiBinder.class);

	interface SearchAddResourceToCollectionViewUiBinder extends
			UiBinder<Widget, AddCourseToClassView> {
	}
	
	@UiField HTMLPanel floderTreeContainer;
	@UiField Anchor cancelResourcePopupBtnLbl;
	@UiField ScrollPanel dropdownListContainerScrollPanel;
	@UiField Button btnAddNew,btnAddExisting;
	@UiField Label addtocollHeaderText,addingTextLbl,lblEmptyErrorMessage,lblError;
	
	String classId;
	
	String currentsearchType="class";
	
	SuccessPopupForResource successPopup=new SuccessPopupForResource();
	
	private int limit=20;
	private int totalHitCount=0;
	private int pageNum=1;
	private CourseTreeItem currectCourseSelectedTreeItem = null;
	private CourseTreeItem previousCourseSelectedTreeItem = null;
	HashMap<String,String> urlparams ;
	private static final String O1_LEVEL = "o1";
	boolean isTopMostSelected =true,isAddingInProgress=true;
	static MessageProperties i18n = GWT.create(MessageProperties.class);
	
	PopupPanel appPopUp;
	private Tree folderTreePanel = new Tree(new TreeMenuImages()){
		 @Override
		 public void onBrowserEvent(Event event) {
			    int eventType = DOM.eventGetType(event);
			    if(!(eventType==Event.ONKEYUP||eventType==Event.ONKEYPRESS||eventType==Event.ONKEYDOWN)) {
			    	super.onBrowserEvent(event);
			    }
		 }
	};
	@Inject
	public AddCourseToClassView(EventBus eventBus) {
		super(eventBus);
		appPopUp=new PopupPanel();
		appPopUp.setWidget(uiBinder.createAndBindUi(this));
		appPopUp.setGlassEnabled(true);
		appPopUp.getElement().getStyle().setZIndex(999999);
		floderTreeContainer.clear();
		floderTreeContainer.add(folderTreePanel);
		dropdownListContainerScrollPanel.addScrollHandler(new ScrollDropdownListContainer());
		dropdownListContainerScrollPanel.getElement().setId("sbDropDownListContainer");
		folderTreePanel.getElement().setId("addResourcefolderTreePanel");
		lblEmptyErrorMessage.setVisible(false);
		lblEmptyErrorMessage.getElement().getStyle().setPadding(0, Unit.PX);
		lblError.setVisible(false);
		urlparams= new HashMap<String, String>();
		addtocollHeaderText.setText(i18n.GL3428());
		addingTextLbl.setText(i18n.GL3427());
		folderTreePanel.addSelectionHandler(new SelectionHandler<TreeItem>() {
			  @Override
			  public void onSelection(SelectionEvent<TreeItem> event) {
			   isTopMostSelected = false;
				lblError.setVisible(false);
				lblError.setText(i18n.GL1134());
			   final TreeItem item = (TreeItem) event.getSelectedItem();
			    Widget folderWidget= item.getWidget();
			     if(folderWidget instanceof CourseTreeItem){
					removePreviousSelectedItem();
					currectCourseSelectedTreeItem = (CourseTreeItem)folderWidget;
					previousCourseSelectedTreeItem = currectCourseSelectedTreeItem;
					currectCourseSelectedTreeItem.addStyleName("selected");
					TreeItem parent = item.getParentItem();
					item.getTree().setSelectedItem(parent, false); 
					if (parent != null)
						parent.setSelected(false); 
					item.setState(!item.getState(), false);
			    }
			  }
		});
		this.classId = AppClientFactory.getPlaceManager().getRequestParameter(UrlNavigationTokens.CLASSPAGEID);
	}

	private void removePreviousSelectedItem() {
		if(previousCourseSelectedTreeItem != null){
			previousCourseSelectedTreeItem.removeStyleName("selected");
		}
	}

	private class ScrollDropdownListContainer implements ScrollHandler{
		@Override
		public void onScroll(ScrollEvent event) {
			if((dropdownListContainerScrollPanel.getVerticalScrollPosition() == dropdownListContainerScrollPanel.getMaximumVerticalScrollPosition())&&(totalHitCount>pageNum*limit)){
				getUiHandlers().getWorkspaceData(pageNum*limit, limit,false,currentsearchType);
				pageNum++;
			}
		}
	}
	@Override
	public Widget asWidget() {
		return appPopUp;
	}
	
	/**
	 * @return the appPopUp
	 */
	@Override
	public PopupPanel getAppPopUp() {
		return appPopUp;
	}

	@Override
	public void reset() {
	}

	@Override
	public void onLoad() {
		isAddingInProgress=true;
		currectCourseSelectedTreeItem=null;
	}

	@Override
	public void onUnload() {
		
	}

	@Override
	public void displayNoCollectionsMsg(String searchType){
		if(!COLLECTION.equalsIgnoreCase(searchType)){
			dropdownListContainerScrollPanel.setVisible(false);
			lblEmptyErrorMessage.getElement().getStyle().clearPadding();
			lblEmptyErrorMessage.setVisible(true);
			lblEmptyErrorMessage.setText("There are no collections to add this resource.");
			btnAddExisting.setVisible(false);
		}else if(COLLECTION.equalsIgnoreCase(searchType)){
			urlparams.clear();
			folderTreePanel.clear();
		}
	}
	private  void adjustTreeItemStyle(final UIObject uiObject) {
	      if (uiObject instanceof TreeItem) {
	         if (uiObject != null && uiObject.getElement() != null) {
	            Element element = uiObject.getElement();
	            element.getStyle().setPadding(0, Unit.PX);
	            element.getStyle().setMarginLeft(0, Unit.PX);
	         }
	      } else {
	         if (uiObject != null && uiObject.getElement() != null && uiObject.getElement().getParentElement() != null
	               && uiObject.getElement().getParentElement().getParentElement() != null
	               && uiObject.getElement().getParentElement().getParentElement().getStyle() != null) {
	            Element element = uiObject.getElement().getParentElement().getParentElement();
	            element.getStyle().setPadding(0, Unit.PX);
	            element.getStyle().setMarginLeft(0, Unit.PX);
	         }
	    }
	}
	private String  getTreeItemStyleName(int folderLevel){
		if(folderLevel==1){
			return "parent";
		}else if(folderLevel==2){
			return "child";
		}else{
			return "innerchild";
		}
	}
	public class CourseTreeItem extends Composite{
		private FlowPanel folderContainer=null;
		private String selectedFolderName=null;
		private String gooruOid=null,folderTitle=null;
		Label floderName=null;
		Label arrowLabel=null;
		private boolean isOpen=false;
		private boolean isApiCalled=false;
		private int folerLevel=1;
		public CourseTreeItem(){
			initWidget(folderContainer=new FlowPanel());
			folderContainer.setStyleName("foldermenuLevel");
		}
		public CourseTreeItem(String levelStyleName,String folderTitle,String gooruOid,FolderDo folderDo){
			this();
			if(folderDo.getType() != null){
				if(folderDo.getType().equalsIgnoreCase("course")){
					folderContainer.removeStyleName("foldermenuLevel");
					floderTreeContainer.getElement().setId("gShelfMainContainer");
					folderContainer.addStyleName("folderLevel");
					folderContainer.addStyleName("course");
					folderContainer.getElement().getStyle().setBackgroundColor("white");
				}
			}
			if(levelStyleName!=null){
				folderContainer.addStyleName("foldermenuLevel"+levelStyleName);
			}
			this.gooruOid=gooruOid;
			this.selectedFolderName = folderTitle;
			this.folderTitle=folderTitle;
			folderContainer.getElement().setInnerText(folderTitle);
		}
		public boolean isOpen() {
			return isOpen;
		}
		public void setOpen(boolean isOpen) {
			this.isOpen = isOpen;
		}
		public String getGooruOid(){
			return gooruOid;
		}
		public String getFolderTitle(){
			return folderTitle;
		}
		public boolean isApiCalled() {
			return isApiCalled;
		}
		public void setApiCalled(boolean isApiCalled) {
			this.isApiCalled = isApiCalled;
		}
		public int getFolerLevel() {
			return folerLevel;
		}
		public String getSelectedFolerTitle() {
			return selectedFolderName;
		}
		public void setFolerLevel(int folerLevel) {
			this.folerLevel = folerLevel;
		}
	}
	@UiHandler("cancelResourcePopupBtnLbl")
	public void cancelButtonEvent(ClickEvent event){
		hide();
		enableTopFilters();
	}
	@UiHandler("btnAddExisting")
	public void addResourceToCollection(ClickEvent event){
		if(classId != null && currectCourseSelectedTreeItem.getGooruOid() != null){
			ClasspageDo classpageDo = new ClasspageDo();
			classpageDo.setCourseGooruOid(currectCourseSelectedTreeItem.getGooruOid());
			getUiHandlers().connectCourseToClass(classId,classpageDo);
		}
	}
	@Override
	public Button getAddButton(){
		return btnAddNew;
	}
	@Override
	public void hidePopup(){
		Element element = Document.get().getElementById("fixedFilterSearchID");
		if(element!=null){
			element.removeAttribute("style");
		}
		hide();
		isAddingInProgress=true;
		enableTopFilters();
	}
	
	

	@Override
	public void setDefaultPanelVisibility(Boolean blnVal){
		btnAddNew.setVisible(!blnVal);
		pageNum=1;
	}

	@Override
	public void displaySuccessPopup(String collectionName,String selectedGooruOid,HashMap<String, String> params) {
		isAddingInProgress=true;
		hide();
		AppClientFactory.getPlaceManager().revealPlace(PlaceTokens.EDIT_CLASS,params);
	}
		
	public void enableTopFilters(){
		Element element = Document.get().getElementById("fixedFilterSearchID");
		if(element!=null){
			element.removeAttribute("style");
		}
		Window.enableScrolling(true);
	}
	

	@Override
	public void clearUrlParams() {
		urlparams.clear();
	}

	/* (non-Javadoc)
	 * @see org.ednovo.gooru.client.mvp.gsearch.addResourcePopup.IsSearchAddResourceToCollectionView#displayCourseWorkspaceData(org.ednovo.gooru.application.shared.model.folder.FolderListDo, boolean, java.lang.String)
	 */
	@Override
	public void displayCourseWorkspaceData(FolderListDo folderListDo,boolean clearShelfPanel,String searchType) {
		if(clearShelfPanel){
			folderTreePanel.clear();
		}
		if(folderListDo!=null){
			 List<FolderDo> foldersArrayList=folderListDo.getSearchResult();
			 if(foldersArrayList!=null&&foldersArrayList.size()>0){
				 for(int i=0;i<foldersArrayList.size();i++){
					 FolderDo floderDo=foldersArrayList.get(i);
					 if(!floderDo.getType().equals("collection")){
						 TreeItem folderItem=new TreeItem(new CourseTreeItem(null,floderDo.getTitle(),floderDo.getGooruOid(),floderDo));
						 folderTreePanel.addItem(folderItem);
						 adjustTreeItemStyle(folderItem);
					 }
				 }
			 }
		}
		currentsearchType=searchType;
		/*totalHitCount = folderListDo.getCount();*/
		btnAddExisting.setVisible(true);
		dropdownListContainerScrollPanel.setVisible(true);
		lblEmptyErrorMessage.setVisible(false);
		lblError.setVisible(false);
		lblEmptyErrorMessage.getElement().getStyle().setPadding(0, Unit.PX);
		
	}
	
}