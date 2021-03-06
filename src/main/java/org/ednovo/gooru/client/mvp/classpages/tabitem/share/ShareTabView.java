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
package org.ednovo.gooru.client.mvp.classpages.tabitem.share;

import org.ednovo.gooru.client.child.ChildView;
import org.ednovo.gooru.client.mvp.dnd.IsDraggableMirage;
import org.ednovo.gooru.shared.model.content.CollectionDo;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author
 * 
 */
public class ShareTabView extends
		ChildView<ShareTabPresenter> implements
		IsShareTabView, MessageProperties {

	@UiField(provided = true)
	ShareTabViewCBundle res;
	
	@UiField
	TextBox shortenUrlTxtBox, classcodeTxtBox;
	
	@UiField Label webLinkLbl,linkToClasspage,classCodeLbl,enterCodetext;
	
	private static ShareTabViewUiBinder uiBinder = GWT
			.create(ShareTabViewUiBinder.class);

	interface ShareTabViewUiBinder extends
			UiBinder<Widget, ShareTabView> {
	}


	/**
	 * Class constructor
	 * 
	 * @param collectionDo
	 *            instance of {@link CollectionDo}
	 */
	public ShareTabView(String shareUrl, String classpageCode) {
		
		res = ShareTabViewCBundle.INSTANCE;
		ShareTabViewCBundle.INSTANCE.css().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
				
		addDomHandler(new ActionPanelHover(), MouseOverEvent.getType());
		addDomHandler(new ActionPanelOut(), MouseOutEvent.getType());
		setPresenter(new ShareTabPresenter(this));
		webLinkLbl.setText(GL0232);
		linkToClasspage.setText(GL1413);
		classCodeLbl.setText(GL0184);
		enterCodetext.setText(GL1414);
		shortenUrlTxtBox.setText(shareUrl);
		shortenUrlTxtBox.setEnabled(true);
		shortenUrlTxtBox.setReadOnly(true);
		classcodeTxtBox.setText(classpageCode.toUpperCase());
		classcodeTxtBox.setEnabled(true);
		classcodeTxtBox.setReadOnly(true);
		shortenUrlTxtBox.getElement().setId("txtShortenUrl");
		classcodeTxtBox.getElement().setId("txtClasscode");
	}
	
	/**
	 * 
	 * To show the ResourceMetaDataInfo Edit,Copy and Remove buttons
	 */
	private class ActionPanelHover implements MouseOverHandler {

		@Override
		public void onMouseOver(MouseOverEvent event) {

		}
	}

	/**
	 * 
	 * To hide the ResourceMetaDataInfo Edit,Copy and Remove buttons
	 */
	private class ActionPanelOut implements MouseOutHandler {

		@Override
		public void onMouseOut(MouseOutEvent event) {

		}
	}

	@Override
	public Widget getDragHandle() {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public IsDraggableMirage initDraggableMirage() {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public void onDragBlur() {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public String getDragId() {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public DRAG_TYPE getDragType() {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public int getDragTopCorrection() {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public int getDragLeftCorrection() {
		throw new RuntimeException("Not implemented");
	}
}
