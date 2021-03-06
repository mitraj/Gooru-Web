package org.ednovo.gooru.client.uc;



import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.play.collection.preview.PreviewPlayerPresenter;
import org.ednovo.gooru.shared.model.content.CollectionItemDo;
import org.ednovo.gooru.shared.util.MessageProperties;
import org.ednovo.gooru.shared.util.ResourceImageUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PreviewResourceView extends Composite implements HasClickHandlers,MessageProperties{

	@UiField Image resourceThumbnail;
	@UiField Label resourceTypeImage,resourceCategory,resourceSourceName,resourceIndex,resourceNumber;
	@UiField HTML resourceTitle,resourceHoverTitle;
	@UiField FlowPanel resourceImageContainer,resourceThumbContainer;
	private CollectionItemDo collectionItemDo=null;
	
	private String collectionItemId=null;
	
	private static TocResourceViewUiBinder uiBinder = GWT.create(TocResourceViewUiBinder.class);

	interface TocResourceViewUiBinder extends UiBinder<Widget, PreviewResourceView> {
	}
	
	public PreviewResourceView(){
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiConstructor
	public PreviewResourceView(CollectionItemDo collectionItemDo, int itemIndex){
		initWidget(uiBinder.createAndBindUi(this));
		this.collectionItemDo=collectionItemDo;
		setNavigationResourceTitle(collectionItemDo.getResource().getTitle());
		if(collectionItemDo.getResource().getResourceFormat()!=null){
			setResourceTypeIcon(collectionItemDo.getResource().getResourceFormat().getDisplayName());
		}
		setResourceCategory();
		setReourceSourceName();
		setResourceSequence(itemIndex+1);
		setResourcePlayLink();
	}
	
	@UiHandler("resourceThumbnail")
	public void onErrorResourceImage(ErrorEvent errorEvent){
		resourceThumbnail.setUrl("images/resource_trans.png");
		if(collectionItemDo.getResource().getResourceFormat()!=null){
		String resourceType=collectionItemDo.getResource().getResourceFormat().getDisplayName();
			resourceType=resourceType.toLowerCase();
		if(resourceType.equalsIgnoreCase("lesson")||resourceType.equalsIgnoreCase("textbook")||resourceType.equalsIgnoreCase("handout"))
		{
			resourceType=resourceType.replaceAll("lesson", "text").replaceAll("textbook", "text").replaceAll("handout", "text");
		}
		if(resourceType.equalsIgnoreCase("slide"))
		{
			resourceType=resourceType.replaceAll("slide","image");
		}
		if(resourceType.equalsIgnoreCase("exam")||resourceType.equalsIgnoreCase("website") || resourceType.equalsIgnoreCase("challenge"))
		{
			resourceType=resourceType.replaceAll("exam","webpage").replaceAll("website","webpage").replaceAll("challenge","webpage");
		}
		resourceThumbnail.setStyleName(getResourceDefaultImage(resourceType));
	}
	}
	@Override
	public void onLoad(){
		if(collectionItemDo.getResource().getResourceType().getName().equalsIgnoreCase("assessment-question")){
			resourceThumbnail.setUrl(getQuestionImage());
		}else if(collectionItemDo.getResource().getResourceType().getName().equalsIgnoreCase("video/youtube")){
			resourceThumbnail.setUrl(ResourceImageUtil.youtubeImageLink(ResourceImageUtil.getYoutubeVideoId(collectionItemDo.getResource().getUrl()),Window.Location.getProtocol()));
		}else{
			resourceThumbnail.setUrl(collectionItemDo.getResource().getThumbnails().getUrl());
		}
	}
	
	public void setResourcePlayLink(){
		Anchor resourceAnchor=new Anchor();
		resourceAnchor.setHref(getResourceLink());
		resourceAnchor.setStyleName("");
		resourceAnchor.getElement().appendChild(resourceImageContainer.getElement());
		resourceThumbContainer.insert(resourceAnchor, 0);
	}
	public String getResourceLink(){
		String collectionId=AppClientFactory.getPlaceManager().getRequestParameter("id", null);
		if(collectionItemDo.getNarration()!=null&&!collectionItemDo.getNarration().trim().equals("")){
			String resourceLink="#"+AppClientFactory.getCurrentPlaceToken()+"&id="+collectionId+"&rid="+collectionItemDo.getCollectionItemId()+"&tab=narration";
			resourceLink+=PreviewPlayerPresenter.setConceptPlayerParameters();
			return resourceLink;
		}else{
			String resourceLink="#"+AppClientFactory.getCurrentPlaceToken()+"&id="+collectionId+"&rid="+collectionItemDo.getCollectionItemId();
			resourceLink+=PreviewPlayerPresenter.setConceptPlayerParameters();
			return resourceLink;
		}
	}
	public void setResourceTypeIcon(String category){
		resourceTypeImage.addStyleName(getResourceTypeImage(category));
	}
	
	public void setNavigationResourceTitle(String title){
		resourceTitle.setHTML(getHTML(title));
		resourceHoverTitle.setHTML(getHTML(title));
	}
	public void setResourceCategory(){
		if(collectionItemDo.getResource().getResourceFormat()!=null){
			String resourceType=collectionItemDo.getResource().getResourceFormat().getDisplayName();
			resourceType=resourceType.toLowerCase();
			
			if(resourceType.equalsIgnoreCase("lesson")||resourceType.equalsIgnoreCase("textbook")||resourceType.equalsIgnoreCase("handout"))
			{
				resourceType=resourceType.replaceAll("lesson", "text").replaceAll("textbook", "text").replaceAll("handout", "text");
			}
			if(resourceType.equalsIgnoreCase("slide"))
			{
				resourceType=resourceType.replaceAll("slide","image");
			}
			if(resourceType.equalsIgnoreCase("exam")||resourceType.equalsIgnoreCase("website")|| resourceType.equalsIgnoreCase("challenge"))
			{
				resourceType=resourceType.replaceAll("exam","webpage").replaceAll("website","webpage").replaceAll("challenge","webpage");
			}
			resourceCategory.setText(resourceType);
		}
	}
	public void setReourceSourceName(){
		if(collectionItemDo.getResource().getResourceSource()!=null){
			if((!collectionItemDo.getResource().getUrl().startsWith("https://docs.google.com"))&&(!collectionItemDo.getResource().getUrl().startsWith("http://docs.google.com"))){
			resourceSourceName.setText(collectionItemDo.getResource().getResourceSource().getAttribution()!=null?collectionItemDo.getResource().getResourceSource().getAttribution():"");
			}
			}else{
			resourceSourceName.setText("");
		}
		
	}
	public void setResourceSequence(int itemIndex){
		resourceIndex.setText(itemIndex<10?"0"+itemIndex:""+itemIndex);
		resourceNumber.setText(itemIndex<10?"0"+itemIndex:""+itemIndex);
	}
	
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}
	public String getResourceTypeImage(String resourceType){
		if(resourceType.equalsIgnoreCase("Video")||resourceType.equalsIgnoreCase("Videos")){
			return PlayerBundle.INSTANCE.getPlayerStyle().videoResourceType();
		}else if(resourceType.equalsIgnoreCase("Interactive")){
			return PlayerBundle.INSTANCE.getPlayerStyle().interactiveResourceType();
		}
		else if(resourceType.equalsIgnoreCase("Website")||resourceType.equalsIgnoreCase("Exam")||resourceType.equalsIgnoreCase("Webpage")|| resourceType.equalsIgnoreCase("challenge")){
			return PlayerBundle.INSTANCE.getPlayerStyle().websiteResourceType();		
		}
		else if(resourceType.equalsIgnoreCase("Slide")||resourceType.equalsIgnoreCase("Image")){
			return PlayerBundle.INSTANCE.getPlayerStyle().imageResourceType();
		}
		else if(resourceType.equalsIgnoreCase("Textbook")||resourceType.equalsIgnoreCase("lesson")||resourceType.equalsIgnoreCase("Handout")||resourceType.equalsIgnoreCase("Text")){
			return PlayerBundle.INSTANCE.getPlayerStyle().textResourceType();
		}
		else if(resourceType.equalsIgnoreCase("Question")){
			return PlayerBundle.INSTANCE.getPlayerStyle().questionResourceType();
		}
		else if(resourceType.equalsIgnoreCase("Audio")){
			return PlayerBundle.INSTANCE.getPlayerStyle().audioResourceType();
		}else if(resourceType.equalsIgnoreCase("Other")){
			return PlayerBundle.INSTANCE.getPlayerStyle().otherResourceType();
		}else {
			return PlayerBundle.INSTANCE.getPlayerStyle().otherResourceType();
		}
	}
	
	public String getResourceDefaultImage(String resourceType){
		if(resourceType.equalsIgnoreCase("Video")||resourceType.equalsIgnoreCase("Videos")){
			return PlayerBundle.INSTANCE.getPlayerStyle().videoResourceDefault();
		}else if(resourceType.equalsIgnoreCase("Interactive")){
			return PlayerBundle.INSTANCE.getPlayerStyle().interactiveResourceDefault();
		}
		else if(resourceType.equalsIgnoreCase("Website")||resourceType.equalsIgnoreCase("Exam")||resourceType.equalsIgnoreCase("Webpage")|| resourceType.equalsIgnoreCase("challenge")){
			return PlayerBundle.INSTANCE.getPlayerStyle().websiteResourceDefault();		
		}
		else if(resourceType.equalsIgnoreCase("Slide")||resourceType.equalsIgnoreCase("Image")){
			return PlayerBundle.INSTANCE.getPlayerStyle().imageResourceDefault();
		}
		else if(resourceType.equalsIgnoreCase("Textbook")||resourceType.equalsIgnoreCase("lesson")||resourceType.equalsIgnoreCase("Handout")||resourceType.equalsIgnoreCase("Text")){
			return PlayerBundle.INSTANCE.getPlayerStyle().textResourceDefault();
		}
		else if(resourceType.equalsIgnoreCase("Question")){
			return PlayerBundle.INSTANCE.getPlayerStyle().questionResourceDefault();
		}
		else if(resourceType.equalsIgnoreCase("Audio")){
			return PlayerBundle.INSTANCE.getPlayerStyle().audioResourceDefault();
		}else if(resourceType.equalsIgnoreCase("Other")){
			return PlayerBundle.INSTANCE.getPlayerStyle().otherResourceDefault();
		}else {
			return PlayerBundle.INSTANCE.getPlayerStyle().websiteResourceDefault();
		}
	}

	public String getCollectionItemId() {
		return collectionItemId;
	}

	public void setCollectionItemId(String collectionItemId) {
		this.collectionItemId = collectionItemId;
	}
	
	private String getQuestionImage(){
		String thumbnailImage="";
		String assetName="";
		try{
			if(collectionItemDo.getResource().getAssets()!=null&&collectionItemDo.getResource().getAssets().size()>0){
				assetName=collectionItemDo.getResource().getAssets().get(0).getAsset().getName();
				thumbnailImage=collectionItemDo.getResource().getAssetURI()+collectionItemDo.getResource().getFolder()+assetName;
			}else{
				thumbnailImage=collectionItemDo.getResource().getThumbnails().getUrl();
			}
		}catch(Exception e){
			
		}
		return thumbnailImage!=null?thumbnailImage:"images/defaultRes.png";
	}
	
	private String getHTML(String html){
		html = html.replaceAll("</p>", " ").replaceAll("<p>", "").replaceAll("<br data-mce-bogus=\"1\">", "").replaceAll("<br>", "").replaceAll("</br>", "");
		HTML contentHtml=new HTML(html);
		contentHtml.setStyleName(PlayerBundle.INSTANCE.getPlayerStyle().setEllipses());
		return html;
	}
	
}
