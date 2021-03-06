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
package org.ednovo.gooru.server.deserializer;

import java.util.ArrayList;
import java.util.List;

import org.ednovo.gooru.shared.model.code.CodeDo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.springframework.stereotype.Component;

/**
 * @author Search Team
 *
 */
@Component
public class AutoCompleteDeSerializer extends DeSerializer {

	private static final String ATTRIBUTION = "attribution";
	private static final String SEARCH_RESULTS = "searchResults";
	private static final String CODE = "code";
	private static final String LABEL = "label";
	private static final String CODE_ID = "codeId";

	/**
	 * Deserialize json object into search query as List
	 * @param jsonRep instance of {@link JsonRepresentation}
	 * @return resultQuery
	 */
	/*public List<String> deserializeSearchQuery(JsonRepresentation jsonRep) {
		List<String> resultQuery = new ArrayList<String>();
		try {
			JSONArray searchQueryJsonArray = jsonRep.getJsonArray();
			for (int query = 0; query < searchQueryJsonArray.length(); query++) {
				resultQuery.add((String) searchQueryJsonArray.get(query));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return resultQuery;
	}*/

	/**
	 * Deserialize json object to List of Standards
	 * @param jsonRep instance of {@link JsonRepresentation}
	 * @return list of standards
	 */
	public List<CodeDo> deserializeStandards(JsonRepresentation jsonRep) {
		List<CodeDo> standards = new ArrayList<CodeDo>();
		try {
			JSONObject standardJsonObject = jsonRep.getJsonObject();
			JSONArray searchResults = standardJsonObject.getJSONArray(SEARCH_RESULTS);
			for (int i = 0; i < searchResults.length(); i++) {
				JSONObject code = searchResults.getJSONObject(i);
				CodeDo codeDo = new CodeDo();
				codeDo.setCodeId(code.getInt(CODE_ID));
				codeDo.setCode(code.getString(CODE));
				codeDo.setLabel(code.getString(LABEL));
				standards.add(codeDo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return standards;
	}

	/**
	 * Deserialize json object to list of resource source
	 * @param jsonRep instance of {@link JsonRepresentation}
	 * @return  list of source
	 */
	public List<String> deserializeSource(JsonRepresentation jsonRep) {
		List<String> sources = new ArrayList<String>();
		try {
			if (jsonRep.getSize() > 0) {
				JSONArray soruceJsonArray = jsonRep.getJsonArray();
				for (int sourceCount = 0; sourceCount < soruceJsonArray.length(); sourceCount++) {
					JSONObject source = soruceJsonArray.getJSONObject(sourceCount);
					sources.add(source.getString(ATTRIBUTION));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return sources;
	}
}
