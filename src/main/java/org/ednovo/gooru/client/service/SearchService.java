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
/**
 * 
 */
package org.ednovo.gooru.client.service;

import java.util.Map;

import org.ednovo.gooru.shared.exception.GwtException;
import org.ednovo.gooru.shared.model.code.CodeDo;
import org.ednovo.gooru.shared.model.search.AutoSuggestKeywordSearchDo;
import org.ednovo.gooru.shared.model.search.CollectionItemSearchResultDo;
import org.ednovo.gooru.shared.model.search.CollectionSearchResultDo;
import org.ednovo.gooru.shared.model.search.ResourceSearchResultDo;
import org.ednovo.gooru.shared.model.search.SearchDo;
import org.ednovo.gooru.shared.model.search.SearchFilterDo;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Search Team
 * 
 */
@RemoteServiceRelativePath("gwt-service/searchService")
public interface SearchService extends BaseService {

	/**
	 * Get search filters
	 * @param type 
	 * @return serialized {@link SearchFilterDo}
	 * @throws GwtException
	 */
	SearchFilterDo getSearchFilters(String type) throws GwtException;

	/**
	 * Get resource search result 
	 * @param searchInput instance of {@link SearchDo} type of {@link ResourceSearchResultDo}
	 * @return serialized {@link ResourceSearchResultDo}
	 * @throws GwtException
	 */
	SearchDo<ResourceSearchResultDo> getResourceSearchResults(SearchDo<ResourceSearchResultDo> searchInput) throws GwtException;

	/**
	 * Get collection search result
	 * @param searchInput instance of {@link SearchDo} type of {@link CollectionSearchResultDo}
	 * @return serialized {@link CollectionSearchResultDo}
	 * @throws GwtException
	 */
	SearchDo<CollectionSearchResultDo> getCollectionSearchResults(SearchDo<CollectionSearchResultDo> searchInput) throws GwtException;

	/**
	 * Get resources which are as collection item of specific collection
	 * @param searchDo instance of {@link SearchDo} type of {@link ResourceSearchResultDo}
	 * @return serialized {@link ResourceSearchResultDo}
	 * @throws GwtException
	 */
//	SearchDo<ResourceSearchResultDo> getCollectionResources(SearchDo<ResourceSearchResultDo> searchDo) throws GwtException;
	
	
	SearchDo<CollectionItemSearchResultDo> getCollectionItems(String collectionId) throws GwtException;

	/**
	 * Get collections which holds the specific resource as collection item
	 * @param searchDo instance of {@link SearchDo} type of {@link CollectionSearchResultDo}
	 * @return serialized {@link CollectionSearchResultDo}
	 * @throws GwtException
	 */
	SearchDo<CollectionSearchResultDo> getResourceCollections(SearchDo<CollectionSearchResultDo> searchDo) throws GwtException;

	/**
	 * Get suggest query 
	 * @param searchDo instance of {@link SearchDo}
	 * @return serialized {@link SearchDo}
	 * @throws GwtException
	 */
//	SearchDo<String> getSuggestSearchQuery(SearchDo<String> searchDo) throws GwtException;

	/**
	 * Get suggest source 
	 * @param searchDo instance of {@link SearchDo}
	 * @return serialized {@link SearchDo}
	 * @throws GwtException
	 */
	SearchDo<String> getSuggestSource(SearchDo<String> searchDo) throws GwtException;

	/**
	 * Get suggest standards 
	 * @param searchDo instance of {@link SearchDo} type of {@link CodeDo}
	 * @return serialized {@link CodeDo}
	 * @throws GwtException
	 */
	SearchDo<CodeDo> getSuggestStandard(SearchDo<CodeDo> searchDo) throws GwtException;

	/**
	 * Get shorten collection url
	 * @param contentGooruOid of collection
	 * @param params set values url,type
	 * @return shrotenUrl, rawUrl
	 * @throws GwtException
	 */
	Map<String, String> getShortenShareUrl(String contentGooruOid, Map<String, String> params) throws GwtException;
	
	/**
	 * Get shorten collection url
	 * @param contentGooruOid of collection
	 * @param params set values url,type
	 * @return shrotenUrl, rawUrl
	 * @throws GwtException
	 */
	Map<String, String> getShortenShareUrlforAssign(String contentGooruOid, Map<String, String> params) throws GwtException;
	
	
	/*
	 * get Google Signin API
	 */
	String getGoogleSignin(String parms);
	
	String getCollectionPlayDirectLink(String params);
	
	/**
	 * Get Home End point url
	 * @param null
	 * @return home end point url
	 * @throws GwtException
	 */
	String getHomeEndPointUrl();
	
	SearchDo<ResourceSearchResultDo> getSuggestSearchResultForResourceNoResult(SearchDo<ResourceSearchResultDo> searchInput) throws GwtException;
	
//	SearchDo<CollectionSearchResultDo> getSuggestedSearchResultForCollectionNoResult(SearchDo<CollectionSearchResultDo> searchInput) throws GwtException;
	/**
	 * Get suggest standards 
	 * @param searchDo instance of {@link SearchDo} type of {@link CodeDo}
	 * @return serialized {@link CodeDo}
	 * @throws GwtException
	 */
	SearchDo<AutoSuggestKeywordSearchDo> getSuggestedAutokeyword(SearchDo<AutoSuggestKeywordSearchDo> searchDo) throws GwtException;

	/**
	 * 
	 * @function getGoogleSignin 
	 * 
	 * @created_date : Dec 9, 2013
	 * 
	 * @description
	 * 
	 * 
	 * @parm(s) : @param parms
	 * @parm(s) : @return
	 * @parm(s) : @throws GwtException
	 * 
	 * @return : String
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 * 
	 *
	 *
	 */
	String getGoogleSignin(String placeToken, Map<String, String> parms) throws GwtException;
	
	public SearchDo<CodeDo> getSuggestStandardByFilterCourseId(SearchDo<CodeDo> searchDo);

}
