package org.ednovo.gooru.client.mvp.search;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourcePrototype;

public class SearchResultWrapperCBundle_default_InlineClientBundleGenerator implements org.ednovo.gooru.client.mvp.search.SearchResultWrapperCBundle {
  private static SearchResultWrapperCBundle_default_InlineClientBundleGenerator _instance0 = new SearchResultWrapperCBundle_default_InlineClientBundleGenerator();
  private void cssInitializer() {
    css = new org.ednovo.gooru.client.mvp.search.SearchResultWrapperCBundle.SearchResultWrapperCss() {
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
        return "css";
      }
      public String getText() {
        return com.google.gwt.i18n.client.LocaleInfo.getCurrentLocale().isRTL() ? ((".org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-searchPanel {\n  background : " + ("none"+ " " +"repeat"+ " " +"scroll"+ " " +"0"+ " " +"0"+ " " +"#fff")  + ";\n  border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -webkit-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -moz-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -ms-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -khtml-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -o-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -moz-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -webkit-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -o-box-shadow : ") + (("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -khtml-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -ms-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  cursor : " + ("pointer")  + ";\n  margin-top : " + ("22px")  + ";\n  margin-right : " + ("10px")  + ";\n  float : " + ("right")  + ";\n  cursor : " + ("move")  + ";\n  width : " + ("559px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-collectionPPPPanel {\n  background : " + ("none"+ " " +"repeat"+ " " +"scroll"+ " " +"0"+ " " +"0"+ " " +"#fff")  + ";\n  border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px") ) + (";\n  -webkit-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -moz-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -ms-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -khtml-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -o-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -moz-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -webkit-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -o-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -khtml-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -ms-box-shadow : ") + (("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  margin-bottom : " + ("20px")  + ";\n  float : " + ("right")  + ";\n  width : " + ("700px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-added {\n  color : " + ("#4e9746")  + " !important;\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-searchResultWrapper {\n  width : " + ("557px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-contentPanel {\n  width : " + ("456px")  + ";\n  float : " + ("right")  + ";\n  padding : " + ("10px"+ " " +"10px"+ " " +"0")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-statusLbl {\n  color : " + ("#999")  + ";\n  font-style : " + ("italic") ) + (";\n  margin : " + ("10px"+ " " +"0"+ " " +"0"+ " " +"0")  + ";\n  float : " + ("left")  + ";\n  width : " + ("74px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-hiddenPanel {\n  border : " + ("1px"+ " " +"solid"+ " " +"#fff")  + ";\n  border-bottom-right-radius : " + ("5px")  + ";\n  border-bottom-left-radius : " + ("5px")  + ";\n  clear : " + ("both")  + ";\n  float : " + ("right")  + ";\n  overflow : " + ("hidden")  + ";\n  width : " + ("578px")  + ";\n  height : ") + (("20px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-share, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-moreInfo, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-embed {\n  color : " + ("#333")  + ";\n  cursor : " + ("pointer")  + ";\n  float : " + ("right")  + ";\n  font-size : " + ("12px")  + ";\n  padding : " + ("6px")  + ";\n  text-align : " + ("center")  + ";\n  width : " + ("81px")  + ";\n  box-shadow : " + ("none")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-moreInfoActive, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-shareActive, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-embedActive {\n  -moz-box-shadow : " + ("inset"+ " " +"0"+ " " +"5px"+ " " +"16px"+ " " +"0"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -webkit-box-shadow : " + ("inset"+ " " +"0"+ " " +"5px"+ " " +"16px"+ " " +"0"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")") ) + (";\n  box-shadow : " + ("inset"+ " " +"0"+ " " +"5px"+ " " +"16px"+ " " +"0"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  background : " + ("#efefef")  + ";\n  background : " + ("-moz-linear-gradient(" + "top"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"0"+ ","+ " " +"rgba(" + "247"+ ","+ " " +"247"+ ","+ " " +"247"+ ","+ " " +"1" + ")"+ " " +"32%"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"100%" + ")")  + ";\n  background : " + ("-webkit-gradient(" + "linear"+ ","+ " " +"left"+ " " +"top"+ ","+ " " +"left"+ " " +"bottom"+ ","+ " " +"color-stop(" + "0"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")" + ")"+ ","+ " " +"color-stop(" + "32%"+ ","+ " " +"rgba(" + "247"+ ","+ " " +"247"+ ","+ " " +"247"+ ","+ " " +"1" + ")" + ")"+ ","+ " " +"color-stop(" + "100%"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")" + ")" + ")")  + ";\n  background : " + ("-webkit-linear-gradient(" + "top"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"0"+ ","+ " " +"rgba(" + "247"+ ","+ " " +"247"+ ","+ " " +"247"+ ","+ " " +"1" + ")"+ " " +"32%"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"100%" + ")")  + ";\n  background : " + ("-o-linear-gradient(" + "top"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"0"+ ","+ " " +"rgba(" + "247"+ ","+ " " +"247"+ ","+ " " +"247"+ ","+ " " +"1" + ")"+ " " +"32%"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"100%" + ")")  + ";\n  background : " + ("-ms-linear-gradient(" + "top"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"0"+ ","+ " " +"rgba(" + "247"+ ","+ " " +"247"+ ","+ " " +"247"+ ","+ " " +"1" + ")"+ " " +"32%"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"100%" + ")")  + ";\n  background : " + ("linear-gradient(" + "to"+ " " +"bottom"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"0"+ ","+ " " +"rgba(" + "247"+ ","+ " " +"247"+ ","+ " " +"247"+ ","+ " " +"1" + ")"+ " " +"32%"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"100%" + ")")  + ";\n  filter : " + ("progid")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-share:HOVER, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-moreInfo:HOVER, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-shareActive:HOVER, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-moreInfoActive:HOVER, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-embed:HOVER {\n  background : " + ("#efefef")  + ";\n  cursor : ") + (("pointer")  + ";\n  box-shadow : " + ("none")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-disclosurePanel {\n  color : " + ("#999")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-disclosureHeader {\n  background : " + ("-moz-linear-gradient(" + "center"+ " " +"top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")"+ " " +"repeat"+ " " +"scroll"+ " " +"0"+ " " +"0"+ " " +"transparent")  + ";\n  background : " + ("-webkit-gradient(" + "linear"+ ","+ " " +"0"+ " " +"0"+ ","+ " " +"0"+ " " +"100%"+ ","+ " " +"from(" + "#fdfdfd" + ")"+ ","+ " " +"to(" + "#ededed" + ")" + ")")  + ";\n  background : " + ("-webkit-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  background : " + ("-moz-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  background : " + ("-ms-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  background : " + ("-o-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  border : " + ("1px"+ " " +"solid"+ " " +"#ddd")  + ";\n  width : " + ("559px") ) + (";\n  margin-right : " + ("-1px")  + ";\n  display : " + ("block")  + ";\n  float : " + ("right")  + ";\n  height : " + ("27px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-collectionPPPDisclosureHeader {\n  background : " + ("-moz-linear-gradient(" + "center"+ " " +"top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")"+ " " +"repeat"+ " " +"scroll"+ " " +"0"+ " " +"0"+ " " +"transparent")  + ";\n  background : " + ("-webkit-gradient(" + "linear"+ ","+ " " +"0"+ " " +"0"+ ","+ " " +"0"+ " " +"100%"+ ","+ " " +"from(" + "#fdfdfd" + ")"+ ","+ " " +"to(" + "#ededed" + ")" + ")")  + ";\n  background : " + ("-webkit-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  background : " + ("-moz-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  background : " + ("-ms-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  background : " + ("-o-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  border : ") + (("1px"+ " " +"solid"+ " " +"#ddd")  + ";\n  width : " + ("700px")  + ";\n  margin-right : " + ("-1px")  + ";\n  display : " + ("block")  + ";\n  float : " + ("right")  + ";\n  height : " + ("27px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-disclosureMainHeader {\n  height : " + ("29px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-disclosureContentPanel {\n  float : " + ("right")  + ";\n  padding-top : " + ("5px")  + ";\n  width : " + ("100%")  + ";\n  margin : " + ("0"+ " " +"auto") ) + (";\n  padding : " + ("25px"+ " " +"0"+ " " +"40px"+ " " +"0")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-blueLink {\n  color : " + ("#0f76bb")  + ";\n  cursor : " + ("pointer")  + ";\n  float : " + ("right")  + ";\n  font-size : " + ("10px")  + ";\n  margin-top : " + ("5px")  + ";\n  padding-right : " + ("4px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-blueLinkPad {\n  color : " + ("#0f76bb")  + ";\n  cursor : " + ("pointer")  + ";\n  float : " + ("right")  + ";\n  font-size : ") + (("10px")  + ";\n  margin-top : " + ("5px")  + ";\n  padding-right : " + ("4px")  + ";\n  color : " + ("#0f76bb")  + ";\n  cursor : " + ("pointer")  + ";\n  float : " + ("right")  + ";\n  font-size : " + ("10px")  + ";\n  margin-top : " + ("1px")  + ";\n  padding-right : " + ("4px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-moreMetaLbl {\n  color : " + ("#999")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-tagText {\n  color : " + ("#1076ba") ) + (";\n}\n")) : ((".org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-searchPanel {\n  background : " + ("none"+ " " +"repeat"+ " " +"scroll"+ " " +"0"+ " " +"0"+ " " +"#fff")  + ";\n  border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -webkit-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -moz-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -ms-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -khtml-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -o-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -moz-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -webkit-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -o-box-shadow : ") + (("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -khtml-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -ms-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  cursor : " + ("pointer")  + ";\n  margin-top : " + ("22px")  + ";\n  margin-left : " + ("10px")  + ";\n  float : " + ("left")  + ";\n  cursor : " + ("move")  + ";\n  width : " + ("559px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-collectionPPPPanel {\n  background : " + ("none"+ " " +"repeat"+ " " +"scroll"+ " " +"0"+ " " +"0"+ " " +"#fff")  + ";\n  border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px") ) + (";\n  -webkit-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -moz-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -ms-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -khtml-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -o-border-radius : " + ("5px"+ " " +"5px"+ " " +"5px"+ " " +"5px")  + ";\n  -moz-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -webkit-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -o-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -khtml-box-shadow : " + ("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -ms-box-shadow : ") + (("0"+ " " +"0"+ " " +"10px"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  margin-bottom : " + ("20px")  + ";\n  float : " + ("left")  + ";\n  width : " + ("700px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-added {\n  color : " + ("#4e9746")  + " !important;\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-searchResultWrapper {\n  width : " + ("557px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-contentPanel {\n  width : " + ("456px")  + ";\n  float : " + ("left")  + ";\n  padding : " + ("10px"+ " " +"10px"+ " " +"0")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-statusLbl {\n  color : " + ("#999")  + ";\n  font-style : " + ("italic") ) + (";\n  margin : " + ("10px"+ " " +"0"+ " " +"0"+ " " +"0")  + ";\n  float : " + ("right")  + ";\n  width : " + ("74px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-hiddenPanel {\n  border : " + ("1px"+ " " +"solid"+ " " +"#fff")  + ";\n  border-bottom-left-radius : " + ("5px")  + ";\n  border-bottom-right-radius : " + ("5px")  + ";\n  clear : " + ("both")  + ";\n  float : " + ("left")  + ";\n  overflow : " + ("hidden")  + ";\n  width : " + ("578px")  + ";\n  height : ") + (("20px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-share, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-moreInfo, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-embed {\n  color : " + ("#333")  + ";\n  cursor : " + ("pointer")  + ";\n  float : " + ("left")  + ";\n  font-size : " + ("12px")  + ";\n  padding : " + ("6px")  + ";\n  text-align : " + ("center")  + ";\n  width : " + ("81px")  + ";\n  box-shadow : " + ("none")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-moreInfoActive, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-shareActive, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-embedActive {\n  -moz-box-shadow : " + ("inset"+ " " +"0"+ " " +"5px"+ " " +"16px"+ " " +"0"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  -webkit-box-shadow : " + ("inset"+ " " +"0"+ " " +"5px"+ " " +"16px"+ " " +"0"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")") ) + (";\n  box-shadow : " + ("inset"+ " " +"0"+ " " +"5px"+ " " +"16px"+ " " +"0"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";\n  background : " + ("#efefef")  + ";\n  background : " + ("-moz-linear-gradient(" + "top"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"0"+ ","+ " " +"rgba(" + "247"+ ","+ " " +"247"+ ","+ " " +"247"+ ","+ " " +"1" + ")"+ " " +"32%"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"100%" + ")")  + ";\n  background : " + ("-webkit-gradient(" + "linear"+ ","+ " " +"left"+ " " +"top"+ ","+ " " +"left"+ " " +"bottom"+ ","+ " " +"color-stop(" + "0"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")" + ")"+ ","+ " " +"color-stop(" + "32%"+ ","+ " " +"rgba(" + "247"+ ","+ " " +"247"+ ","+ " " +"247"+ ","+ " " +"1" + ")" + ")"+ ","+ " " +"color-stop(" + "100%"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")" + ")" + ")")  + ";\n  background : " + ("-webkit-linear-gradient(" + "top"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"0"+ ","+ " " +"rgba(" + "247"+ ","+ " " +"247"+ ","+ " " +"247"+ ","+ " " +"1" + ")"+ " " +"32%"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"100%" + ")")  + ";\n  background : " + ("-o-linear-gradient(" + "top"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"0"+ ","+ " " +"rgba(" + "247"+ ","+ " " +"247"+ ","+ " " +"247"+ ","+ " " +"1" + ")"+ " " +"32%"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"100%" + ")")  + ";\n  background : " + ("-ms-linear-gradient(" + "top"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"0"+ ","+ " " +"rgba(" + "247"+ ","+ " " +"247"+ ","+ " " +"247"+ ","+ " " +"1" + ")"+ " " +"32%"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"100%" + ")")  + ";\n  background : " + ("linear-gradient(" + "to"+ " " +"bottom"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"0"+ ","+ " " +"rgba(" + "247"+ ","+ " " +"247"+ ","+ " " +"247"+ ","+ " " +"1" + ")"+ " " +"32%"+ ","+ " " +"rgba(" + "239"+ ","+ " " +"239"+ ","+ " " +"239"+ ","+ " " +"1" + ")"+ " " +"100%" + ")")  + ";\n  filter : " + ("progid")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-share:HOVER, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-moreInfo:HOVER, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-shareActive:HOVER, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-moreInfoActive:HOVER, .org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-embed:HOVER {\n  background : " + ("#efefef")  + ";\n  cursor : ") + (("pointer")  + ";\n  box-shadow : " + ("none")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-disclosurePanel {\n  color : " + ("#999")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-disclosureHeader {\n  background : " + ("-moz-linear-gradient(" + "center"+ " " +"top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")"+ " " +"repeat"+ " " +"scroll"+ " " +"0"+ " " +"0"+ " " +"transparent")  + ";\n  background : " + ("-webkit-gradient(" + "linear"+ ","+ " " +"0"+ " " +"0"+ ","+ " " +"0"+ " " +"100%"+ ","+ " " +"from(" + "#fdfdfd" + ")"+ ","+ " " +"to(" + "#ededed" + ")" + ")")  + ";\n  background : " + ("-webkit-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  background : " + ("-moz-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  background : " + ("-ms-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  background : " + ("-o-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  border : " + ("1px"+ " " +"solid"+ " " +"#ddd")  + ";\n  width : " + ("559px") ) + (";\n  margin-left : " + ("-1px")  + ";\n  display : " + ("block")  + ";\n  float : " + ("left")  + ";\n  height : " + ("27px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-collectionPPPDisclosureHeader {\n  background : " + ("-moz-linear-gradient(" + "center"+ " " +"top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")"+ " " +"repeat"+ " " +"scroll"+ " " +"0"+ " " +"0"+ " " +"transparent")  + ";\n  background : " + ("-webkit-gradient(" + "linear"+ ","+ " " +"0"+ " " +"0"+ ","+ " " +"0"+ " " +"100%"+ ","+ " " +"from(" + "#fdfdfd" + ")"+ ","+ " " +"to(" + "#ededed" + ")" + ")")  + ";\n  background : " + ("-webkit-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  background : " + ("-moz-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  background : " + ("-ms-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  background : " + ("-o-linear-gradient(" + "top"+ ","+ " " +"#fdfdfd"+ ","+ " " +"#ededed" + ")")  + ";\n  border : ") + (("1px"+ " " +"solid"+ " " +"#ddd")  + ";\n  width : " + ("700px")  + ";\n  margin-left : " + ("-1px")  + ";\n  display : " + ("block")  + ";\n  float : " + ("left")  + ";\n  height : " + ("27px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-disclosureMainHeader {\n  height : " + ("29px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-disclosureContentPanel {\n  float : " + ("left")  + ";\n  padding-top : " + ("5px")  + ";\n  width : " + ("100%")  + ";\n  margin : " + ("0"+ " " +"auto") ) + (";\n  padding : " + ("25px"+ " " +"0"+ " " +"40px"+ " " +"0")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-blueLink {\n  color : " + ("#0f76bb")  + ";\n  cursor : " + ("pointer")  + ";\n  float : " + ("left")  + ";\n  font-size : " + ("10px")  + ";\n  margin-top : " + ("5px")  + ";\n  padding-left : " + ("4px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-blueLinkPad {\n  color : " + ("#0f76bb")  + ";\n  cursor : " + ("pointer")  + ";\n  float : " + ("left")  + ";\n  font-size : ") + (("10px")  + ";\n  margin-top : " + ("5px")  + ";\n  padding-left : " + ("4px")  + ";\n  color : " + ("#0f76bb")  + ";\n  cursor : " + ("pointer")  + ";\n  float : " + ("left")  + ";\n  font-size : " + ("10px")  + ";\n  margin-top : " + ("1px")  + ";\n  padding-left : " + ("4px")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-moreMetaLbl {\n  color : " + ("#999")  + ";\n}\n.org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-tagText {\n  color : " + ("#1076ba") ) + (";\n}\n"));
      }
      public java.lang.String added(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-added";
      }
      public java.lang.String blueLink(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-blueLink";
      }
      public java.lang.String blueLinkPad(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-blueLinkPad";
      }
      public java.lang.String collectionPPPDisclosureHeader(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-collectionPPPDisclosureHeader";
      }
      public java.lang.String collectionPPPPanel(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-collectionPPPPanel";
      }
      public java.lang.String contentPanel(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-contentPanel";
      }
      public java.lang.String disclosureContentPanel(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-disclosureContentPanel";
      }
      public java.lang.String disclosureHeader(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-disclosureHeader";
      }
      public java.lang.String disclosureMainHeader(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-disclosureMainHeader";
      }
      public java.lang.String disclosurePanel(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-disclosurePanel";
      }
      public java.lang.String embed(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-embed";
      }
      public java.lang.String embedActive(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-embedActive";
      }
      public java.lang.String hiddenPanel(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-hiddenPanel";
      }
      public java.lang.String moreInfo(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-moreInfo";
      }
      public java.lang.String moreInfoActive(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-moreInfoActive";
      }
      public java.lang.String moreMetaLbl(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-moreMetaLbl";
      }
      public java.lang.String searchPanel(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-searchPanel";
      }
      public java.lang.String searchResultWrapper(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-searchResultWrapper";
      }
      public java.lang.String share(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-share";
      }
      public java.lang.String shareActive(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-shareActive";
      }
      public java.lang.String statusLbl(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-statusLbl";
      }
      public java.lang.String tagText(){
        return "org-ednovo-gooru-client-mvp-search-SearchResultWrapperCBundle-SearchResultWrapperCss-tagText";
      }
    }
    ;
  }
  private static class cssInitializer {
    static {
      _instance0.cssInitializer();
    }
    static org.ednovo.gooru.client.mvp.search.SearchResultWrapperCBundle.SearchResultWrapperCss get() {
      return css;
    }
  }
  public org.ednovo.gooru.client.mvp.search.SearchResultWrapperCBundle.SearchResultWrapperCss css() {
    return cssInitializer.get();
  }
  private static java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype> resourceMap;
  private static org.ednovo.gooru.client.mvp.search.SearchResultWrapperCBundle.SearchResultWrapperCss css;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      css(), 
    };
  }
  public ResourcePrototype getResource(String name) {
    if (GWT.isScript()) {
      return getResourceNative(name);
    } else {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype>();
        resourceMap.put("css", css());
      }
      return resourceMap.get(name);
    }
  }
  private native ResourcePrototype getResourceNative(String name) /*-{
    switch (name) {
      case 'css': return this.@org.ednovo.gooru.client.mvp.search.SearchResultWrapperCBundle::css()();
    }
    return null;
  }-*/;
}
