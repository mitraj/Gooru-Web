package org.ednovo.gooru.client.mvp.play.resource.share;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourcePrototype;

public class ResourceShareView_ResourceShareViewUiBinderImpl_GenBundle_default_InlineClientBundleGenerator implements org.ednovo.gooru.client.mvp.play.resource.share.ResourceShareView_ResourceShareViewUiBinderImpl_GenBundle {
  private static ResourceShareView_ResourceShareViewUiBinderImpl_GenBundle_default_InlineClientBundleGenerator _instance0 = new ResourceShareView_ResourceShareViewUiBinderImpl_GenBundle_default_InlineClientBundleGenerator();
  private void playerStyleInitializer() {
    playerStyle = new org.ednovo.gooru.client.mvp.play.resource.share.ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle() {
      private boolean injected;
      public boolean ensureInjected() {
        if (!injected) {
          injected = true;
          com.google.gwt.dom.client.StyleInjector.inject(getText());
          return true;
        }
        return false;
      }
      public String getName() {
        return "playerStyle";
      }
      public String getText() {
        return com.google.gwt.i18n.client.LocaleInfo.getCurrentLocale().isRTL() ? ((".org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-narration-container {\n  width : " + ("100%")  + ";\n  margin-top : " + ("23px")  + ";\n  padding : " + ("10px")  + ";\n  background-color : " + ("#4d4d4d")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-narration-wrapper {\n  min-height : " + ("126px")  + ";\n  padding : " + ("10px")  + ";\n  width : " + ("940px")  + ";\n  position : " + ("relative")  + ";\n  margin : " + ("0"+ " " +"auto")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-maintitle {\n  float : " + ("right")  + ";\n  font-family : ") + (("Arial"+ ","+ " " +"Helvetica"+ ","+ " " +"sans-serif")  + ";\n  font-size : " + ("16px")  + ";\n  color : " + ("white")  + ";\n  font-weight : " + ("bold")  + ";\n  width : " + ("100%")  + ";\n  text-align : " + ("center")  + ";\n  padding-top : " + ("20px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-buttonscontainer {\n  margin-top : " + ("30px")  + ";\n  clear : " + ("both")  + ";\n  padding-right : " + ("76px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-button1 {\n  color : " + ("#1076bb") ) + (";\n  font-size : " + ("12px")  + ";\n  font-family : " + ("Arial"+ ","+ " " +"Helvetica"+ ","+ " " +"sans-serif")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-button2 {\n  color : " + ("#1076bb")  + ";\n  margin-right : " + ("30px")  + ";\n  font-size : " + ("12px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-button1:hover, .org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-button2:hover {\n  color : " + ("#87badd")  + ";\n  cursor : " + ("pointer")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-wrapper {\n  width : " + ("84%")  + ";\n  margin-top : " + ("15px")  + ";\n  position : " + ("relative")  + ";\n  margin-right : ") + (("auto")  + ";\n  margin-left : " + ("auto")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-titleContainer {\n  float : " + ("right")  + ";\n  padding-bottom : " + ("5px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-title {\n  color : " + ("#515151")  + ";\n  font-size : " + ("14px")  + ";\n  font-weight : " + ("bold")  + ";\n  margin-bottom : " + ("10px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-textbox {\n  border : " + ("1px"+ " " +"solid"+ " " +"#ccc")  + ";\n  color : " + ("#414141")  + ";\n  font-size : " + ("12px") ) + (";\n  height : " + ("41px")  + ";\n  resize : " + ("none")  + ";\n  width : " + ("366px")  + ";\n  padding : " + ("5px")  + ";\n  float : " + ("right")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-separator {\n  float : " + ("right")  + ";\n  height : " + ("73px")  + ";\n  width : " + ("35px")  + ";\n  margin-left : " + ("11px")  + ";\n  border-left : " + ("1px"+ " " +"solid"+ " " +"#999")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-addResource {\n  margin : ") + (("0"+ " " +"0"+ " " +"10px"+ " " +"10px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-contentCollectionEdit {\n  float : " + ("right")  + ";\n  width : " + ("97%")  + ";\n  margin-right : " + ("23px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-socialshare-title {\n  float : " + ("right")  + ";\n  font-size : " + ("13px")  + ";\n  color : " + ("#515151")  + ";\n  margin-bottom : " + ("10px")  + ";\n  margin-right : " + ("23px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-facebookButton {\n  color : " + ("white")  + ";\n  background-color : " + ("#3b5996") ) + (";\n  font-size : " + ("12px")  + ";\n  border-radius : " + ("4px")  + ";\n  padding : " + ("7px"+ " " +"35px"+ " " +"7px"+ " " +"21px")  + ";\n  border : " + ("1px"+ " " +"solid"+ " " +"#3b5996")  + ";\n  font-family : " + ("Arial"+ ","+ " " +"sans-serif")  + ";\n  display : " + ("inline-block")  + ";\n  cursor : " + ("pointer")  + ";\n  background : " + ("#3b5996")  + ";\n  background : " + ("#3b5996"+ " " +"url(images/FTE.png)"+ " " +"-26px"+ " " +"-19px"+ " " +"no-repeat")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-26px"+ " " +"-19px"+ " " +"no-repeat"+ ","+ " " +"-webkit-gradient(" + "linear"+ ","+ " " +"0"+ " " +"0"+ ","+ " " +"0"+ " " +"100%"+ ","+ " " +"from(" + "#3b5996" + ")"+ ","+ " " +"to(" + "#3b5996" + ")" + ")")  + ";\n  background : ") + (("url(images/FTE.png)"+ " " +"-26px"+ " " +"-19px"+ " " +"no-repeat"+ ","+ " " +"-webkit-linear-gradient(" + "top"+ ","+ " " +"#3b5996"+ ","+ " " +"#3b5996" + ")")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-26px"+ " " +"-19px"+ " " +"no-repeat"+ ","+ " " +"-moz-linear-gradient(" + "top"+ ","+ " " +"#3b5996"+ ","+ " " +"#3b5996" + ")")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-26px"+ " " +"-19px"+ " " +"no-repeat"+ ","+ " " +"-o-linear-gradient(" + "top"+ ","+ " " +"#3b5996"+ ","+ " " +"#3b5996" + ")")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-twitterButton {\n  color : " + ("white")  + ";\n  background-color : " + ("#00acf0")  + ";\n  font-size : " + ("12px")  + ";\n  border-radius : " + ("4px")  + ";\n  padding : " + ("7px"+ " " +"37px"+ " " +"7px"+ " " +"28px")  + ";\n  border : " + ("1px"+ " " +"solid"+ " " +"#00acf0")  + ";\n  font-family : " + ("Arial"+ ","+ " " +"sans-serif")  + ";\n  display : " + ("inline-block") ) + (";\n  cursor : " + ("pointer")  + ";\n  background : " + ("#00acf0")  + ";\n  background : " + ("#00acf0"+ " " +"url(images/FTE.png)"+ " " +"-26px"+ " " +"-43px"+ " " +"no-repeat")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-26px"+ " " +"-43px"+ " " +"no-repeat"+ ","+ " " +"-webkit-gradient(" + "linear"+ ","+ " " +"0"+ " " +"0"+ ","+ " " +"0"+ " " +"100%"+ ","+ " " +"from(" + "#00acf0" + ")"+ ","+ " " +"to(" + "#00acf0" + ")" + ")")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-26px"+ " " +"-43px"+ " " +"no-repeat"+ ","+ " " +"-webkit-linear-gradient(" + "top"+ ","+ " " +"#00acf0"+ ","+ " " +"#00acf0" + ")")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-26px"+ " " +"-43px"+ " " +"no-repeat"+ ","+ " " +"-moz-linear-gradient(" + "top"+ ","+ " " +"#00acf0"+ ","+ " " +"#00acf0" + ")")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-26px"+ " " +"-43px"+ " " +"no-repeat"+ ","+ " " +"-o-linear-gradient(" + "top"+ ","+ " " +"#00acf0"+ ","+ " " +"#1076bb" + ")")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-emailButton {\n  color : " + ("white")  + ";\n  background-color : " + ("#1076bb")  + ";\n  font-size : " + ("12px")  + ";\n  border-radius : ") + (("4px")  + ";\n  padding : " + ("7px"+ " " +"37px"+ " " +"7px"+ " " +"28px")  + ";\n  border : " + ("1px"+ " " +"solid"+ " " +"#1076bb")  + ";\n  font-family : " + ("Arial"+ ","+ " " +"sans-serif")  + ";\n  display : " + ("inline-block")  + ";\n  cursor : " + ("pointer")  + ";\n  background : " + ("#1076bb")  + ";\n  background : " + ("#1076bb"+ " " +"url(images/FTE.png)"+ " " +"-24px"+ " " +"-69px"+ " " +"no-repeat")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-24px"+ " " +"-69px"+ " " +"no-repeat"+ ","+ " " +"-webkit-gradient(" + "linear"+ ","+ " " +"0"+ " " +"0"+ ","+ " " +"0"+ " " +"100%"+ ","+ " " +"from(" + "#1076bb" + ")"+ ","+ " " +"to(" + "#1076bb" + ")" + ")")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-24px"+ " " +"-69px"+ " " +"no-repeat"+ ","+ " " +"-webkit-linear-gradient(" + "top"+ ","+ " " +"#1076bb"+ ","+ " " +"#1076bb" + ")")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-24px"+ " " +"-69px"+ " " +"no-repeat"+ ","+ " " +"-moz-linear-gradient(" + "top"+ ","+ " " +"#1076bb"+ ","+ " " +"#1076bb" + ")") ) + (";\n  background : " + ("url(images/FTE.png)"+ " " +"-24px"+ " " +"-69px"+ " " +"no-repeat"+ ","+ " " +"-o-linear-gradient(" + "top"+ ","+ " " +"#1076bb"+ ","+ " " +"#1076bb" + ")")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-resource-info-bottomImageContainer {\n  text-align : " + ("center")  + ";\n  padding-top : " + ("10px")  + ";\n  cursor : " + ("pointer")  + ";\n  padding-bottom : " + ("5px")  + ";\n  width : " + ("45px")  + ";\n  margin : " + ("0"+ " " +"auto")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-resource-info-hideImage {\n  display : " + ("inline-block")  + ";\n  background-image : " + ("url(images/hide_arrow.png)")  + ";\n  width : " + ("10px")  + ";\n  color : ") + (("#fff")  + ";\n  height : " + ("6px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-resource-info-hideText {\n  display : " + ("inline-block")  + ";\n  vertical-align : " + ("top")  + ";\n  color : " + ("#fff")  + ";\n  margin-right : " + ("4px")  + ";\n}\n")) : ((".org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-narration-container {\n  width : " + ("100%")  + ";\n  margin-top : " + ("23px")  + ";\n  padding : " + ("10px")  + ";\n  background-color : " + ("#4d4d4d")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-narration-wrapper {\n  min-height : " + ("126px")  + ";\n  padding : " + ("10px")  + ";\n  width : " + ("940px")  + ";\n  position : " + ("relative")  + ";\n  margin : " + ("0"+ " " +"auto")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-maintitle {\n  float : " + ("left")  + ";\n  font-family : ") + (("Arial"+ ","+ " " +"Helvetica"+ ","+ " " +"sans-serif")  + ";\n  font-size : " + ("16px")  + ";\n  color : " + ("white")  + ";\n  font-weight : " + ("bold")  + ";\n  width : " + ("100%")  + ";\n  text-align : " + ("center")  + ";\n  padding-top : " + ("20px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-buttonscontainer {\n  margin-top : " + ("30px")  + ";\n  clear : " + ("both")  + ";\n  padding-left : " + ("76px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-button1 {\n  color : " + ("#1076bb") ) + (";\n  font-size : " + ("12px")  + ";\n  font-family : " + ("Arial"+ ","+ " " +"Helvetica"+ ","+ " " +"sans-serif")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-button2 {\n  color : " + ("#1076bb")  + ";\n  margin-left : " + ("30px")  + ";\n  font-size : " + ("12px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-button1:hover, .org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-button2:hover {\n  color : " + ("#87badd")  + ";\n  cursor : " + ("pointer")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-wrapper {\n  width : " + ("84%")  + ";\n  margin-top : " + ("15px")  + ";\n  position : " + ("relative")  + ";\n  margin-left : ") + (("auto")  + ";\n  margin-right : " + ("auto")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-titleContainer {\n  float : " + ("left")  + ";\n  padding-bottom : " + ("5px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-title {\n  color : " + ("#515151")  + ";\n  font-size : " + ("14px")  + ";\n  font-weight : " + ("bold")  + ";\n  margin-bottom : " + ("10px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-textbox {\n  border : " + ("1px"+ " " +"solid"+ " " +"#ccc")  + ";\n  color : " + ("#414141")  + ";\n  font-size : " + ("12px") ) + (";\n  height : " + ("41px")  + ";\n  resize : " + ("none")  + ";\n  width : " + ("366px")  + ";\n  padding : " + ("5px")  + ";\n  float : " + ("left")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-separator {\n  float : " + ("left")  + ";\n  height : " + ("73px")  + ";\n  width : " + ("35px")  + ";\n  margin-right : " + ("11px")  + ";\n  border-right : " + ("1px"+ " " +"solid"+ " " +"#999")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-addResource {\n  margin : ") + (("0"+ " " +"10px"+ " " +"10px"+ " " +"0")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-contentCollectionEdit {\n  float : " + ("left")  + ";\n  width : " + ("97%")  + ";\n  margin-left : " + ("23px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-socialshare-title {\n  float : " + ("left")  + ";\n  font-size : " + ("13px")  + ";\n  color : " + ("#515151")  + ";\n  margin-bottom : " + ("10px")  + ";\n  margin-left : " + ("23px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-facebookButton {\n  color : " + ("white")  + ";\n  background-color : " + ("#3b5996") ) + (";\n  font-size : " + ("12px")  + ";\n  border-radius : " + ("4px")  + ";\n  padding : " + ("7px"+ " " +"21px"+ " " +"7px"+ " " +"35px")  + ";\n  border : " + ("1px"+ " " +"solid"+ " " +"#3b5996")  + ";\n  font-family : " + ("Arial"+ ","+ " " +"sans-serif")  + ";\n  display : " + ("inline-block")  + ";\n  cursor : " + ("pointer")  + ";\n  background : " + ("#3b5996")  + ";\n  background : " + ("#3b5996"+ " " +"url(images/FTE.png)"+ " " +"-26px"+ " " +"-19px"+ " " +"no-repeat")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-26px"+ " " +"-19px"+ " " +"no-repeat"+ ","+ " " +"-webkit-gradient(" + "linear"+ ","+ " " +"0"+ " " +"0"+ ","+ " " +"0"+ " " +"100%"+ ","+ " " +"from(" + "#3b5996" + ")"+ ","+ " " +"to(" + "#3b5996" + ")" + ")")  + ";\n  background : ") + (("url(images/FTE.png)"+ " " +"-26px"+ " " +"-19px"+ " " +"no-repeat"+ ","+ " " +"-webkit-linear-gradient(" + "top"+ ","+ " " +"#3b5996"+ ","+ " " +"#3b5996" + ")")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-26px"+ " " +"-19px"+ " " +"no-repeat"+ ","+ " " +"-moz-linear-gradient(" + "top"+ ","+ " " +"#3b5996"+ ","+ " " +"#3b5996" + ")")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-26px"+ " " +"-19px"+ " " +"no-repeat"+ ","+ " " +"-o-linear-gradient(" + "top"+ ","+ " " +"#3b5996"+ ","+ " " +"#3b5996" + ")")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-twitterButton {\n  color : " + ("white")  + ";\n  background-color : " + ("#00acf0")  + ";\n  font-size : " + ("12px")  + ";\n  border-radius : " + ("4px")  + ";\n  padding : " + ("7px"+ " " +"28px"+ " " +"7px"+ " " +"37px")  + ";\n  border : " + ("1px"+ " " +"solid"+ " " +"#00acf0")  + ";\n  font-family : " + ("Arial"+ ","+ " " +"sans-serif")  + ";\n  display : " + ("inline-block") ) + (";\n  cursor : " + ("pointer")  + ";\n  background : " + ("#00acf0")  + ";\n  background : " + ("#00acf0"+ " " +"url(images/FTE.png)"+ " " +"-26px"+ " " +"-43px"+ " " +"no-repeat")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-26px"+ " " +"-43px"+ " " +"no-repeat"+ ","+ " " +"-webkit-gradient(" + "linear"+ ","+ " " +"0"+ " " +"0"+ ","+ " " +"0"+ " " +"100%"+ ","+ " " +"from(" + "#00acf0" + ")"+ ","+ " " +"to(" + "#00acf0" + ")" + ")")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-26px"+ " " +"-43px"+ " " +"no-repeat"+ ","+ " " +"-webkit-linear-gradient(" + "top"+ ","+ " " +"#00acf0"+ ","+ " " +"#00acf0" + ")")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-26px"+ " " +"-43px"+ " " +"no-repeat"+ ","+ " " +"-moz-linear-gradient(" + "top"+ ","+ " " +"#00acf0"+ ","+ " " +"#00acf0" + ")")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-26px"+ " " +"-43px"+ " " +"no-repeat"+ ","+ " " +"-o-linear-gradient(" + "top"+ ","+ " " +"#00acf0"+ ","+ " " +"#1076bb" + ")")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-emailButton {\n  color : " + ("white")  + ";\n  background-color : " + ("#1076bb")  + ";\n  font-size : " + ("12px")  + ";\n  border-radius : ") + (("4px")  + ";\n  padding : " + ("7px"+ " " +"28px"+ " " +"7px"+ " " +"37px")  + ";\n  border : " + ("1px"+ " " +"solid"+ " " +"#1076bb")  + ";\n  font-family : " + ("Arial"+ ","+ " " +"sans-serif")  + ";\n  display : " + ("inline-block")  + ";\n  cursor : " + ("pointer")  + ";\n  background : " + ("#1076bb")  + ";\n  background : " + ("#1076bb"+ " " +"url(images/FTE.png)"+ " " +"-24px"+ " " +"-69px"+ " " +"no-repeat")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-24px"+ " " +"-69px"+ " " +"no-repeat"+ ","+ " " +"-webkit-gradient(" + "linear"+ ","+ " " +"0"+ " " +"0"+ ","+ " " +"0"+ " " +"100%"+ ","+ " " +"from(" + "#1076bb" + ")"+ ","+ " " +"to(" + "#1076bb" + ")" + ")")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-24px"+ " " +"-69px"+ " " +"no-repeat"+ ","+ " " +"-webkit-linear-gradient(" + "top"+ ","+ " " +"#1076bb"+ ","+ " " +"#1076bb" + ")")  + ";\n  background : " + ("url(images/FTE.png)"+ " " +"-24px"+ " " +"-69px"+ " " +"no-repeat"+ ","+ " " +"-moz-linear-gradient(" + "top"+ ","+ " " +"#1076bb"+ ","+ " " +"#1076bb" + ")") ) + (";\n  background : " + ("url(images/FTE.png)"+ " " +"-24px"+ " " +"-69px"+ " " +"no-repeat"+ ","+ " " +"-o-linear-gradient(" + "top"+ ","+ " " +"#1076bb"+ ","+ " " +"#1076bb" + ")")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-resource-info-bottomImageContainer {\n  text-align : " + ("center")  + ";\n  padding-top : " + ("10px")  + ";\n  cursor : " + ("pointer")  + ";\n  padding-bottom : " + ("5px")  + ";\n  width : " + ("45px")  + ";\n  margin : " + ("0"+ " " +"auto")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-resource-info-hideImage {\n  display : " + ("inline-block")  + ";\n  background-image : " + ("url(images/hide_arrow.png)")  + ";\n  width : " + ("10px")  + ";\n  color : ") + (("#fff")  + ";\n  height : " + ("6px")  + ";\n}\n.org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-resource-info-hideText {\n  display : " + ("inline-block")  + ";\n  vertical-align : " + ("top")  + ";\n  color : " + ("#fff")  + ";\n  margin-left : " + ("4px")  + ";\n}\n"));
      }
      public java.lang.String addResource(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-addResource";
      }
      public java.lang.String collectionNarrationContainer(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-narration-container";
      }
      public java.lang.String collectionNarrationWrapper(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-narration-wrapper";
      }
      public java.lang.String collectionShareButton1(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-button1";
      }
      public java.lang.String collectionShareButton2(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-button2";
      }
      public java.lang.String collectionShareButtonscontainer(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-buttonscontainer";
      }
      public java.lang.String collectionShareMaintitle(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-maintitle";
      }
      public java.lang.String collectionShareSeparator(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-separator";
      }
      public java.lang.String collectionShareTextbox(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-textbox";
      }
      public java.lang.String collectionShareTitle(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-title";
      }
      public java.lang.String collectionShareTitleContainer(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-titleContainer";
      }
      public java.lang.String collectionShareWrapper(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-share-wrapper";
      }
      public java.lang.String collectionSocialshareTitle(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-collection-socialshare-title";
      }
      public java.lang.String contentCollectionEdit(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-contentCollectionEdit";
      }
      public java.lang.String emailButton(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-emailButton";
      }
      public java.lang.String facebookButton(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-facebookButton";
      }
      public java.lang.String resourceInfoBottomImageContainer(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-resource-info-bottomImageContainer";
      }
      public java.lang.String resourceInfoHideImage(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-resource-info-hideImage";
      }
      public java.lang.String resourceInfoHideText(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-resource-info-hideText";
      }
      public java.lang.String twitterButton(){
        return "org-ednovo-gooru-client-mvp-play-resource-share-ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle-twitterButton";
      }
    }
    ;
  }
  private static class playerStyleInitializer {
    static {
      _instance0.playerStyleInitializer();
    }
    static org.ednovo.gooru.client.mvp.play.resource.share.ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle get() {
      return playerStyle;
    }
  }
  public org.ednovo.gooru.client.mvp.play.resource.share.ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle playerStyle() {
    return playerStyleInitializer.get();
  }
  private static java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype> resourceMap;
  private static org.ednovo.gooru.client.mvp.play.resource.share.ResourceShareView_ResourceShareViewUiBinderImpl_GenCss_playerStyle playerStyle;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      playerStyle(), 
    };
  }
  public ResourcePrototype getResource(String name) {
    if (GWT.isScript()) {
      return getResourceNative(name);
    } else {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype>();
        resourceMap.put("playerStyle", playerStyle());
      }
      return resourceMap.get(name);
    }
  }
  private native ResourcePrototype getResourceNative(String name) /*-{
    switch (name) {
      case 'playerStyle': return this.@org.ednovo.gooru.client.mvp.play.resource.share.ResourceShareView_ResourceShareViewUiBinderImpl_GenBundle::playerStyle()();
    }
    return null;
  }-*/;
}
