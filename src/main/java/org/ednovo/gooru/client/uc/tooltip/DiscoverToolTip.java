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

package org.ednovo.gooru.client.uc.tooltip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ednovo.gooru.client.PlaceTokens;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.ui.HTMLEventPanel;
import org.ednovo.gooru.client.util.MixpanelUtil;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;


/**
 * @fileName : DiscoverToolTip.java
 *
 * @description : 
 *
 *
 * @version : 1.0
 *
 * @date: 27-Dec-2013
 *
 * @Author: Gooru Team
 *
 * @Reviewer: Gooru Team
 */
public class DiscoverToolTip extends PopupPanel implements MessageProperties, HasMouseOutHandlers{
	
	private static DiscoverToolTipUiBinder uiBinder = GWT
			.create(DiscoverToolTipUiBinder.class);

	interface DiscoverToolTipUiBinder extends UiBinder<Widget, DiscoverToolTip> {
	}
	
	@UiField Label lblRusdLibrary,lblGooruLibrary;

/*	@UiField HTMLEventPanel lblPartnerLibrary, partnerLibContainer;
	
	@UiField HTMLPanel dropdownImg;
*/	/**
	 * Because this class has a default constructor, it can
	 * be used as a binder template. In other words, it can be used in other
	 * *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 *   xmlns:g="urn:import:**user's package**">
	 *  <g:**UserClassName**>Hello!</g:**UserClassName>
	 * </ui:UiBinder>
	 * Note that depending on the widget that is used, it may be necessary to
	 * implement HasHTML instead of HasText.
	 */
	public DiscoverToolTip() {
		setWidget(uiBinder.createAndBindUi(this));
		lblRusdLibrary.setText(GL0515);
		lblGooruLibrary.setText(GL0516);
/*		lblPartnerLibrary.add(new Label(GL1550));
		lblPartnerLibrary.addMouseOverHandler(new OpenPartnerMenu());
		partnerLibContainer.addMouseOutHandler(new ClosePartnerMenu());
		lblRusdLibrary.addMouseOverHandler(new CloseOtherMenus());
		lblGooruLibrary.addMouseOverHandler(new CloseOtherMenus());
		partnerLibContainer.setVisible(false);
*/		
		//getPartnersData();
		
		lblRusdLibrary.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MixpanelUtil.mixpanelEvent("Community_Library_Click_RUSD");
				AppClientFactory.getPlaceManager().revealPlace(PlaceTokens.RUSD_LIBRARY);
			}
		});
		
		this.addMouseOutHandler(new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
			    hide();
			}
		});
        
        lblGooruLibrary.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				AppClientFactory.getPlaceManager().revealPlace(PlaceTokens.HOME);
			}
		});
	}
	
	@Override
	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return addDomHandler(handler, MouseOutEvent.getType());
	}

/*	private class OpenPartnerMenu implements MouseOverHandler {
		@Override
		public void onMouseOver(MouseOverEvent event) {
			lblPartnerLibrary.getElement().getStyle().setBackgroundColor("#cfe3f1");
			partnerLibContainer.setVisible(true);
		}
	}

	private class ClosePartnerMenu implements MouseOutHandler {
		@Override
		public void onMouseOut(MouseOutEvent event) {
			lblPartnerLibrary.getElement().getStyle().clearBackgroundColor();
			partnerLibContainer.setVisible(false);
		}
	}
	
	private class CloseOtherMenus implements MouseOverHandler {
		@Override
		public void onMouseOver(MouseOverEvent event) {
			lblPartnerLibrary.getElement().getStyle().clearBackgroundColor();
			partnerLibContainer.setVisible(false);
		}
	}
	
	public void setPartnersData(List partnersData) {
		for(int i = 0; i< partnersData.size();) {
			Label partnerName = new Label(partnersData.get(i).toString());
			partnerName.setStyleName("discoverTooltipLabel");
			partnerName.addClickHandler(new RedirectToPartnerPage(partnersData.get(i+1).toString()));
			i=i+2;
			partnerLibContainer.add(partnerName);
		}
	}
	
	private void getPartnersData() {
		List partnersData = new ArrayList();
		partnersData.add("Autodesk® ");
		partnersData.add("8a6b75b8-0537-492e-8970-c41ade8723a6");
		partnersData.add("Office of Naval Research (ONR)");
		partnersData.add("2e8dd71c-cef6-435d-bfd8-0afad9939b07");
		partnersData.add("Foundation for Teaching Economics (FTE)");
		partnersData.add("de182361-8379-4d82-9168-e5bd8b658cff");
		partnersData.add("New Global Citizens (NGC)");
		partnersData.add("bac737f6-4945-4990-b3d6-8c07ec09f9c8");
		partnersData.add("What So Proudly We Hail");
		partnersData.add("593eeff6-2fa2-487b-941d-67d197e10201");
		partnersData.add("SVEF's Lessonopoly");
		partnersData.add("cd46b323-83d6-44ef-acf1-cef0705623db");
		setPartnersData(partnersData);
	}

	public class RedirectToPartnerPage implements ClickHandler {
		private String folderId;
		public RedirectToPartnerPage(String folderId) {
			this.folderId = folderId;
		}

		@Override
		public void onClick(ClickEvent event) {
			hide();
			Map<String,String> params = new HashMap<String, String>();
			params.put("pid", folderId);
			//AppClientFactory.getPlaceManager().revealPlace(PlaceTokens.PARTNER_LIBRARY, params);
		}
	}
	*/
}