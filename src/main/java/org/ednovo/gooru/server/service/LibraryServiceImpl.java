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

package org.ednovo.gooru.server.service;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.ednovo.gooru.client.service.LibraryService;
import org.ednovo.gooru.server.annotation.ServiceURL;
import org.ednovo.gooru.server.request.JsonResponseRepresentation;
import org.ednovo.gooru.server.request.ServiceProcessor;
import org.ednovo.gooru.server.request.UrlToken;
import org.ednovo.gooru.server.serializer.JsonDeserializer;
import org.ednovo.gooru.shared.exception.GwtException;
import org.ednovo.gooru.shared.model.content.ThumbnailDo;
import org.ednovo.gooru.shared.model.library.ConceptDo;
import org.ednovo.gooru.shared.model.library.CourseDo;
import org.ednovo.gooru.shared.model.library.LessonDo;
import org.ednovo.gooru.shared.model.library.LibraryCollectionItemDo;
import org.ednovo.gooru.shared.model.library.LibraryResourceDo;
import org.ednovo.gooru.shared.model.library.LibraryUserDo;
import org.ednovo.gooru.shared.model.library.PartnerConceptListDo;
import org.ednovo.gooru.shared.model.library.PartnerFolderDo;
import org.ednovo.gooru.shared.model.library.PartnerFolderListDo;
import org.ednovo.gooru.shared.model.library.StandardsDo;
import org.ednovo.gooru.shared.model.library.SubjectDo;
import org.ednovo.gooru.shared.model.library.TopicDo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;

@Service("libraryService")
@ServiceURL("/libraryService")
public class LibraryServiceImpl extends BaseServiceImpl implements LibraryService {

	private static final long serialVersionUID = 1L;
	private static final String DATA = "data";
	private static final String RUSD = "rusd";
	private static final String TOTAL_LIMIT = "10";
	public static final String CURRICULUM = "curriculum";
	public static final String CURRICULUM_CODE = "curriculumCode";
	public static final String CURRICULUM_DESCRIPTION = "curriculumDesc";
	public static final String TAXONOMY_DATA_SET = "taxonomyDataSet";
	public static final String STANDARD_CODE = "code";
	public static final String STANDARD_DESCRIPTION = "description";
	private static final String SEARCH_RESULTS = "searchResult";
	private static final String COUNT = "count";
	private static final String COLLECTION_ITEMS = "collectionItems";
	
	private static final String TITLE = "title";
	private static final String GOALS = "goals";
	private static final String GOORUOID = "gooruOid";
	private static final String THUMBNAILS = "thumbnails";
	private static final String FOLDER = "folder";
	private static final String TYPE = "type";
	private static final String COLLECTION = "collection";
	private static final String LESSON = "lesson";
	private static final String CONCEPT = "concept";
	private static final String COURSE = "course";
	private static final String UNIT = "unit";
	private static final String TOPIC = "topic";
	private static final String COURSE_100_75_IMG = "../images/library/course-100x75.png";
	private static final String FEATURED = "featured";

	private static final String EXCLUDED_COURSE = "Language and Composition";
	private static final String INCLUDED_COURSE = "Teachable Moments";
	private static final String AUTODESK_GOORU_UID = "8a6b75b8-0537-492e-8970-c41ade8723a6";
	
	@Override
	public ArrayList<CourseDo> getCourses(String subjectName, String libraryName) throws GwtException {
		JsonRepresentation jsonRepresentation = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_GET_LIBRARY_COURSES, subjectName, getLoggedInSessionToken());
		url+=getLibraryName(libraryName);
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRepresentation=jsonResponseRep.getJsonRepresentation();
		return deserializeCourses(jsonRepresentation, subjectName);
	}
	
	/* (non-Javadoc)
	 * @see org.ednovo.gooru.client.service.LibraryService#getLessonsOnPagination(java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public ArrayList<LessonDo> getLessonsOnPagination(String subjectName, String topicId, int offset, int limit, String libraryName) throws GwtException {
		JsonRepresentation jsonRepresentation = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_GET_LIBRARY_TOPIC_OFFSET, subjectName, topicId, getLoggedInSessionToken(), ""+offset, ""+limit);
		url+=getLibraryName(libraryName);
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRepresentation=jsonResponseRep.getJsonRepresentation();
		return deserializeLessons(jsonRepresentation);
	}
	
	/* (non-Javadoc)
	 * @see org.ednovo.gooru.client.service.LibraryService#getLibraryFeaturedUsers()
	 */
	@Override
	public ArrayList<LibraryUserDo> getLibraryFeaturedUsers(String libraryName) throws GwtException {
		JsonRepresentation jsonRepresentation = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_GET_LIBRARY_FEATURED_USERS, getLoggedInSessionToken());
		url+=getLibraryName(libraryName);
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRepresentation=jsonResponseRep.getJsonRepresentation();
		return deserializeCollaborators(jsonRepresentation);
	}
	
	/* (non-Javadoc)
	 * @see org.ednovo.gooru.client.service.LibraryService#getSubjects()
	 */
	@Override
	public HashMap<String, SubjectDo> getSubjects(String subjectId, String libraryName) throws GwtException {
		JsonRepresentation jsonRepresentation = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_GET_LIBRARY_COURSES, subjectId, getLoggedInSessionToken());
		url+=getLibraryName(libraryName);
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRepresentation=jsonResponseRep.getJsonRepresentation();
		return deserializeSubjects(jsonRepresentation,subjectId,libraryName);
	}
	
	@Override
	public HashMap<String, StandardsDo> getSubjectsForStandards(String subjectId, String libraryName) throws GwtException {
		JsonRepresentation jsonRepresentation = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_GET_LIBRARY_COURSES, subjectId, getLoggedInSessionToken());
		url+=getLibraryName(libraryName);
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		//need to do here		
		jsonRepresentation=jsonResponseRep.getJsonRepresentation();
		return deserializeSubjectsforStandards(jsonRepresentation,subjectId);
	}

	/* (non-Javadoc)
	 * @see org.ednovo.gooru.client.service.LibraryService#getConcept(java.lang.String, boolean)
	 */
	@Override
	public ConceptDo getConcept(String gooruOid, boolean skipCollectionItems) throws GwtException {
		JsonRepresentation jsonRepresentation = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_GET_COLLECTION, gooruOid, getLoggedInSessionToken(), skipCollectionItems + "");
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRepresentation=jsonResponseRep.getJsonRepresentation();
		return deserializeConcept(jsonRepresentation);
	}
	
	/* (non-Javadoc)
	 * @see org.ednovo.gooru.client.service.LibraryService#getConceptForStandards(java.lang.String, boolean)
	 */
	@Override
	public ConceptDo getConceptForStandards(String gooruOid, String roteNodeId, boolean skipCollectionItems) throws GwtException {
		JsonRepresentation jsonRepresentation = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_GET_COLLECTIONForStandards, gooruOid, getLoggedInSessionToken(),roteNodeId, skipCollectionItems + "");
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRepresentation=jsonResponseRep.getJsonRepresentation();
		return deserializeConcept(jsonRepresentation);
	}

	/* (non-Javadoc)
	 * @see org.ednovo.gooru.client.service.LibraryService#getTopicsOnPagination(java.lang.String)
	 */
	@Override
	public ArrayList<TopicDo> getTopicsOnPagination(String subjectId, String unitId, String libraryName, int offset, String standardsId) throws GwtException {
		JsonRepresentation jsonRepresentation = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_GET_LIBRARY_UNIT_OFFSET, subjectId, unitId, getLoggedInSessionToken(), offset+"", TOTAL_LIMIT);
		url+=getLibraryName(libraryName);
		url+=getStandardId(standardsId);
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		
		jsonRepresentation=jsonResponseRep.getJsonRepresentation();
		return deserializeTopicList(jsonRepresentation);
	}
	
	/* (non-Javadoc)
	 * @see org.ednovo.gooru.client.service.LibraryService#getLibraryCollections(java.lang.String, java.lang.String)
	 */
	@Override
	public ArrayList<ConceptDo> getLibraryCollections(String courseType, String lessonId, String libraryName) throws GwtException {
		JsonRepresentation jsonRepresentation = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_GET_LIBRARY_COLLECTIONS, courseType, lessonId, getLoggedInSessionToken());
		url+=getLibraryName(libraryName);
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		
		jsonRepresentation=jsonResponseRep.getJsonRepresentation();
		return deserializeConceptDoList(jsonRepresentation);
	}
	
	/**
	 * 
	 * @function deserializeCollaborators 
	 * 
	 * @created_date : 13-Dec-2013
	 * 
	 * @description
	 * 
	 * 
	 * @parm(s) : @param jsonRep
	 * @parm(s) : @return
	 * 
	 * @return : ArrayList<LibraryUserDo>
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 */
	public ArrayList<LibraryUserDo> deserializeCollaborators(JsonRepresentation jsonRep) {
		if (jsonRep != null && jsonRep.getSize() != -1) {
			try {
				return JsonDeserializer.deserialize(jsonRep.getJsonArray().toString(), new TypeReference<ArrayList<LibraryUserDo>>() {
				});
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<LibraryUserDo>();
	}
	
	/**
	 * 
	 * @function deserializeCourses 
	 * 
	 * @created_date : 13-Dec-2013
	 * 
	 * @description
	 * 
	 * 
	 * @parm(s) : @param jsonRep
	 * @parm(s) : @param subjectName
	 * @parm(s) : @return
	 * 
	 * @return : ArrayList<CourseDo>
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 */
	public ArrayList<CourseDo> deserializeCourses(JsonRepresentation jsonRep, String subjectName) {
		if (jsonRep != null && jsonRep.getSize() != -1) {
			try {
				String jsonRep1 = jsonRep.getJsonObject().getJSONObject(subjectName).getJSONArray(DATA).toString();
				return JsonDeserializer.deserialize(jsonRep1, new TypeReference<ArrayList<CourseDo>>() {
				});
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<CourseDo>();
	}

	/**
	 * 
	 * @function deserializeLessons 
	 * 
	 * @created_date : 13-Dec-2013
	 * 
	 * @description
	 * 
	 * 
	 * @parm(s) : @param jsonRep
	 * @parm(s) : @return
	 * 
	 * @return : ArrayList<LessonDo>
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 */
	public ArrayList<LessonDo> deserializeLessons(JsonRepresentation jsonRep) {
		ArrayList<LessonDo> lessonDoList = new ArrayList<LessonDo>();
		ArrayList<ConceptDo> conceptDoList = new ArrayList<ConceptDo>();
		LessonDo lessonDo = new LessonDo();
		if (jsonRep != null && jsonRep.getSize() != -1) {
			try {
				JSONArray lessonArray = jsonRep.getJsonArray();
				for(int i=0;i<lessonArray.length();i++) {
					if(lessonArray.getJSONObject(i).getJSONArray(COLLECTION)!=null) {
						lessonDoList.add(JsonDeserializer.deserialize(lessonArray.getJSONObject(i).toString(), LessonDo.class));	
					} else {
						conceptDoList.add(JsonDeserializer.deserialize(lessonArray.getJSONObject(i).toString(), ConceptDo.class));
					}
				}
				if(conceptDoList.size()>0) {
					lessonDo.setCollection(conceptDoList);
					lessonDoList.add(lessonDo);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return lessonDoList;
	}

	/**
	 * @function deserializeSubjects 
	 * 
	 * @created_date : 13-Dec-2013
	 * 
	 * @description
	 * 
	 * 
	 * @parm(s) : @param jsonRep
	 * @parm(s) : @param subjectName
	 * @parm(s) : @return
	 * 
	 * @return : ArrayList<CourseDo>
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 */
	public HashMap<String,SubjectDo> deserializeSubjects(JsonRepresentation jsonRep, String subjectName, String libraryName) {
		HashMap<String, SubjectDo> subjectList = new HashMap<String, SubjectDo>();
		if (jsonRep != null && jsonRep.getSize() != -1) {
			try {
				String jsonRepStr = jsonRep.getJsonObject().toString();
				subjectList = JsonDeserializer.deserialize(jsonRepStr, new TypeReference<HashMap<String, SubjectDo>>() {});
				if(libraryName.equals(RUSD)&&subjectName.equals(FEATURED)) {
					subjectList = getThirdPartyPartnerData(subjectList,subjectName);
				}
				return subjectList;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return new HashMap<String,SubjectDo>();
	}
	
	private HashMap<String, SubjectDo> getThirdPartyPartnerData(HashMap<String, SubjectDo> subjectList, String subjectName) {
		ArrayList<CourseDo> data = subjectList.get(subjectName).getData();
		CourseDo courseDo = new CourseDo();
		for(int i=0;i<data.size();i++) {
			if(data.get(i).getLabel().equalsIgnoreCase(EXCLUDED_COURSE)) {
				courseDo = data.get(i);
				courseDo.setLabel(INCLUDED_COURSE);
				courseDo.setParentId(AUTODESK_GOORU_UID);
				courseDo.getThumbnails().setUrl(COURSE_100_75_IMG);
				data.remove(i);
			}
		}
		data.add(0, courseDo);
		subjectList.get(subjectName).setData(data);
		return subjectList;
	}
	
	public HashMap<String,StandardsDo> deserializeSubjectsforStandards(JsonRepresentation jsonRep, String subjectName) {
		if (jsonRep != null && jsonRep.getSize() != -1) {
			try {
				String jsonRepStr = jsonRep.getJsonObject().toString();
				JSONObject mainObj = jsonRep.getJsonObject();
				if(jsonRep.getJsonObject().getJSONObject(subjectName).getJSONArray(DATA).length()>0)
				{
					for(int z=0;z<jsonRep.getJsonObject().getJSONObject(subjectName).getJSONArray(DATA).length();z++){
					JSONArray courseArray = jsonRep.getJsonObject().getJSONObject(subjectName).getJSONArray(DATA).getJSONObject(z).getJSONArray(COURSE);
						for(int n=0; n<courseArray.length();n++)
						{
							for(int m=0; m<courseArray.getJSONObject(n).getJSONArray(UNIT).length();m++)
							{
								JSONArray topicArray = courseArray.getJSONObject(n).getJSONArray(UNIT).getJSONObject(m).getJSONArray(TOPIC);
								
								for(int l=0; l<topicArray.length();l++) {
										JSONArray lessonDoList = new JSONArray();
										JSONObject lessonList = topicArray.getJSONObject(l);
										//This code is only for TEXS
										if(jsonRep.getJsonObject().getJSONObject(subjectName).getJSONArray(DATA).getJSONObject(z).getString("label").contains("Texas")){
											JSONObject lessonObj1 = new JSONObject();
											lessonObj1=topicArray.getJSONObject(l);
											lessonDoList.put(lessonObj1);
										}
										if(!lessonList.isNull(LESSON)) {
											for(int j=0;j<topicArray.getJSONObject(l).getJSONArray(LESSON).length();j++) 
											{
												JSONObject lessonObj = new JSONObject();
												lessonObj = topicArray.getJSONObject(l).getJSONArray(LESSON).getJSONObject(j);
												lessonDoList.put(lessonObj);
												if(!lessonObj.isNull(CONCEPT)) {
													JSONArray conceptArray = topicArray.getJSONObject(l).getJSONArray(LESSON).getJSONObject(j).getJSONArray(CONCEPT);
													for(int k=0;k<lessonObj.getJSONArray(CONCEPT).length();k++) 
													{
														JSONObject conceptObj = new JSONObject();
														conceptObj = conceptArray.getJSONObject(k);
														lessonDoList.put(conceptObj);									
													}
												}
											}
										}
										mainObj.getJSONObject(subjectName).getJSONArray(DATA).getJSONObject(z).getJSONArray(COURSE).getJSONObject(n).getJSONArray(UNIT).getJSONObject(m).getJSONArray(TOPIC).getJSONObject(l).remove(LESSON);
										mainObj.getJSONObject(subjectName).getJSONArray(DATA).getJSONObject(z).getJSONArray(COURSE).getJSONObject(n).getJSONArray(UNIT).getJSONObject(m).getJSONArray(TOPIC).getJSONObject(l).put(LESSON, lessonDoList);
									}
								jsonRepStr = mainObj.toString();
								
							}
						}
					}
				}
				return JsonDeserializer.deserialize(jsonRepStr, new TypeReference<HashMap<String, StandardsDo>>() {});
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return new HashMap<String,StandardsDo>();
	}
	
	/**
	 * 
	 * @function deserializeConcept 
	 * 
	 * @created_date : 13-Dec-2013
	 * 
	 * @description
	 * 
	 * 
	 * @parm(s) : @param jsonRep
	 * @parm(s) : @return
	 * 
	 * @return : ConceptDo
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 * 
	 *
	 *
	 */
	public ConceptDo deserializeConcept(JsonRepresentation jsonRep) {
		if (jsonRep != null && jsonRep.getSize() != -1) {
			try {
				return JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), ConceptDo.class);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return new ConceptDo();
	}

	/**
	 * @function deserializeTopicList 
	 * 
	 * @created_date : 18-Dec-2013
	 * 
	 * @description
	 * 
	 * @parm(s) : @param jsonRepresentation
	 * @parm(s) : @return
	 * 
	 * @return : ArrayList<TopicDo>
	 *
	 * @throws : <Mentioned if any exceptions>
	 * 
	*/
	
	private ArrayList<TopicDo> deserializeTopicList(JsonRepresentation jsonRep) {
		ArrayList<TopicDo> topicDoList = new ArrayList<TopicDo>();
		TopicDo topicDo = new TopicDo();

		ArrayList<ConceptDo> conceptDoList = new ArrayList<ConceptDo>();
		if (jsonRep != null && jsonRep.getSize() != -1) {
			try {
				JSONArray topicArray = jsonRep.getJsonArray();
				for(int i=0;i<topicArray.length();i++) {
					ArrayList<LessonDo> lessonDoList = new ArrayList<LessonDo>();
					try {
						if(!topicArray.getJSONObject(i).isNull(COLLECTION)){
							lessonDoList.add(JsonDeserializer.deserialize(topicArray.getJSONObject(i).toString(), LessonDo.class));
						}
						if(topicArray.getJSONObject(i).getJSONArray(LESSON)!=null) {
							for(int j=0;j<topicArray.getJSONObject(i).getJSONArray(LESSON).length();j++) 
							{
								lessonDoList.add(JsonDeserializer.deserialize(topicArray.getJSONObject(i).getJSONArray(LESSON).getJSONObject(j).toString(), LessonDo.class));
								
								for(int k=0;k<topicArray.getJSONObject(i).getJSONArray(LESSON).getJSONObject(j).getJSONArray(CONCEPT).length();k++) 
								{
								lessonDoList.add(JsonDeserializer.deserialize(topicArray.getJSONObject(i).getJSONArray(LESSON).getJSONObject(j).getJSONArray(CONCEPT).getJSONObject(k).toString(), LessonDo.class));									
								}	
							}	
							topicDoList.add(JsonDeserializer.deserialize(topicArray.getJSONObject(i).toString(), TopicDo.class));
							topicDoList.get(i).setLesson(lessonDoList);
						} 
						else 
						{
							conceptDoList.add(JsonDeserializer.deserialize(topicArray.getJSONObject(i).toString(), ConceptDo.class));
						}
					} catch (JSONException e) {
						try {
							if(topicArray.getJSONObject(i).getJSONArray(COLLECTION)!=null) {
								topicDoList.add(JsonDeserializer.deserialize(topicArray.getJSONObject(i).toString(), TopicDo.class));	
							} else {
								conceptDoList.add(JsonDeserializer.deserialize(topicArray.getJSONObject(i).toString(), ConceptDo.class));
							}
						} catch(Exception ex) {
							conceptDoList.add(JsonDeserializer.deserialize(topicArray.getJSONObject(i).toString(), ConceptDo.class));
						}
					}					
				}
				if(conceptDoList.size()>0) {
					topicDo.setCollection(conceptDoList);
					topicDoList.add(topicDo);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} 
		}
		return topicDoList;
	}
	
	/**
	 * @function getLibraryName 
	 * 
	 * @created_date : 30-Dec-2013
	 * 
	 * @description
	 * 
	 * @parm(s) : @param libraryName
	 * @parm(s) : @return
	 * 
	 * @return : String
	 *
	 * @throws : <Mentioned if any exceptions>
	 *
	 */
	private String getLibraryName(String libraryName) {
		String libraryCode = "";
		if(libraryName.equalsIgnoreCase(RUSD)) {
			libraryCode = "&libraryName="+RUSD;
		}
		return libraryCode;
	}
	
	private String getStandardId(String standardId) {
		String standardIdVal = "";
		if(standardId != null) {
			standardIdVal = "&rootNodeId="+standardId;
		}
		return standardIdVal;
	}

	/**
	 * 
	 * @function deserializeConceptDoList 
	 * 
	 * @created_date : 07-Jan-2014
	 * 
	 * @description
	 * 
	 * @parm(s) : @param jsonRep
	 * @return : ArrayList<ConceptDo>
	 *
	 * @throws : JSONException
	 *
	 */
	public ArrayList<ConceptDo> deserializeConceptDoList(JsonRepresentation jsonRep) {
		if (jsonRep != null && jsonRep.getSize() != -1) {
			try {
				return JsonDeserializer.deserialize(jsonRep.getJsonArray().toString(), new TypeReference<ArrayList<ConceptDo>>() {
				});
			} catch (JSONException e) {
				
			}
		}
		return new ArrayList<ConceptDo>();
	}

	@Override
	public ArrayList<ConceptDo> getPopularCollectionsData(String courseId) throws GwtException {
		JsonRepresentation jsonRepresentation = null;
		JsonResponseRepresentation jsonResponseRep = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_GET_POPULAR_LIBRARY, courseId, getLoggedInSessionToken());
		try {
			jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		} catch (Exception e){}
		if(jsonResponseRep.getStatusCode()==200) {
			jsonRepresentation=jsonResponseRep.getJsonRepresentation();
			return deserializeConceptDoList(jsonRepresentation);
		}
		return new ArrayList<ConceptDo>();
	}

	//Partner Library APIs
	@Override
	public PartnerFolderListDo getLibraryPartnerWorkspace(String gooruUid, int limit, String sharingType, String collectionType) throws GwtException {
		JsonRepresentation jsonRep = null;
		String url = null;
		String sessionToken = getLoggedInSessionToken();
		
		if(sharingType!=null){
			sessionToken=sessionToken+"&sharing="+sharingType;
		}
		url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_PARTNER_WORKSPACE, gooruUid, sessionToken, limit+"");
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRep = jsonResponseRep.getJsonRepresentation();
		return deserializeFolderList(jsonRep);
	}
	
	@Override
	public PartnerConceptListDo getPartnerChildFolders(String gooruUid, int offset, int limit, String parentId,String sharingType, String collectionType) throws GwtException {
		JsonRepresentation jsonRep = null;
		String url = null;
		String sessionToken = getLoggedInSessionToken();
		
		if(sharingType!=null){
			sessionToken=sessionToken+"&sharing="+sharingType;
		}
		url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_GET_CHILD_FOLDER_LIST, parentId, sessionToken, offset+"", limit+"");
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRep = jsonResponseRep.getJsonRepresentation();
		return deserializePartnerFolderList(jsonRep);
	}

	@Override
	public PartnerFolderListDo getPartnerPaginationWorkspace(String parentId, String sharingType, int limit) throws GwtException {
		JsonRepresentation jsonRep = null;
		String url = null;
		String sessionToken = getLoggedInSessionToken();
		
		if(sharingType!=null){
			sessionToken=sessionToken+"&sharing="+sharingType;
		}
		url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_PARTNER_CHILD_FOLDER_LIST, parentId, sessionToken, limit+"");
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRep = jsonResponseRep.getJsonRepresentation();
		return deserializePaginatedWorkspaceFolders(jsonRep);
	}
	
	public PartnerFolderListDo deserializeFolderList(JsonRepresentation jsonRep) {
		PartnerFolderListDo searchResults = new PartnerFolderListDo();
		ArrayList<PartnerFolderDo> firstLevelFolders = new ArrayList<PartnerFolderDo>();
		Integer count;
		try {
			if (jsonRep != null && jsonRep.getSize() != -1) {
				JSONArray firstLevelArray = jsonRep.getJsonObject().getJSONArray(SEARCH_RESULTS);
				count = jsonRep.getJsonObject().getInt(COUNT);
				for(int i=0;i<firstLevelArray.length();i++) {
					PartnerFolderDo folderFirstLevelDo = JsonDeserializer.deserialize(firstLevelArray.getJSONObject(i).toString(), PartnerFolderDo.class);
					//ArrayList<PartnerFolderDo> secondLevelFolders = new ArrayList<PartnerFolderDo>();
					if(i==0) {
						JSONArray secondLevelArray = firstLevelArray.getJSONObject(i).getJSONArray(COLLECTION_ITEMS);
						folderFirstLevelDo.setFolderItems(getDeserializedPartnerList(secondLevelArray, i));
					}
					/*if(i==0) {
						folderFirstLevelDo.setFolderItems(secondLevelFolders);
					}*/
					firstLevelFolders.add(folderFirstLevelDo);
				}
				searchResults.setCount(count);
				searchResults.setSearchResult(firstLevelFolders);
			}
			return searchResults;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new PartnerFolderListDo();
	}
	
	private ArrayList<PartnerFolderDo> getDeserializedPartnerList(JSONArray secondLevelArray, int level) {
		ArrayList<PartnerFolderDo> secondLevelFolders = new ArrayList<PartnerFolderDo>();
		try {
			if(secondLevelArray.length()>0) {
				for(int j=0;j<secondLevelArray.length();j++) {
					JSONObject secondLevelJsonObject = secondLevelArray.getJSONObject(j);
					PartnerFolderDo folderTwoLevelDo = JsonDeserializer.deserialize(secondLevelJsonObject.toString(), PartnerFolderDo.class);
					JSONArray thirdLevelArray = secondLevelJsonObject.getJSONArray(COLLECTION_ITEMS);
					ArrayList<ConceptDo> thirdLevelConcepts = new ArrayList<ConceptDo>();
					ArrayList<PartnerFolderDo> thirdLevelFolders = new ArrayList<PartnerFolderDo>();
					if(thirdLevelArray.length()>0) {
						for(int k=0;k<thirdLevelArray.length();k++) {
							JSONObject thirdLevelJsonObject = thirdLevelArray.getJSONObject(k);
							if(thirdLevelJsonObject.getString(TYPE).equalsIgnoreCase(FOLDER)) {
								PartnerFolderDo folderThirdLevelDo = JsonDeserializer.deserialize(thirdLevelJsonObject.toString(), PartnerFolderDo.class);
								JSONArray fourthLevelArray = thirdLevelJsonObject.getJSONArray(COLLECTION_ITEMS);
								ArrayList<ConceptDo> fourthLevelConcepts = new ArrayList<ConceptDo>();
								if(fourthLevelArray.length()>0) {
									for(int m=0;m<fourthLevelArray.length();m++) {
										ConceptDo conceptDo = new ConceptDo();
										JSONObject fourthLevelJsonObject = fourthLevelArray.getJSONObject(m);
										if(!fourthLevelJsonObject.isNull(GOALS)) {
											conceptDo.setGoals(fourthLevelJsonObject.getString(GOALS));
										} else {
											conceptDo.setGoals("");
										}
										
										conceptDo.setTitle(fourthLevelJsonObject.getString(TITLE));
										conceptDo.setGooruOid(fourthLevelJsonObject.getString(GOORUOID));
										conceptDo.setThumbnails(JsonDeserializer.deserialize(fourthLevelJsonObject.getJSONObject(THUMBNAILS).toString(), ThumbnailDo.class));
										if(m==0) {
											JSONArray resourceArray = fourthLevelJsonObject.getJSONArray(COLLECTION_ITEMS);
											ArrayList<LibraryCollectionItemDo> collectionItems = new ArrayList<LibraryCollectionItemDo>();
											for(int l=0;l<resourceArray.length();l++) {
												LibraryCollectionItemDo libraryCollectionItemDo = new LibraryCollectionItemDo();
												libraryCollectionItemDo.setResource(JsonDeserializer.deserialize(resourceArray.getJSONObject(l).toString(), LibraryResourceDo.class));
												collectionItems.add(libraryCollectionItemDo);
											}
											conceptDo.setCollectionItems(collectionItems);
										}
										fourthLevelConcepts.add(conceptDo);
									}
								}
								folderThirdLevelDo.setCollections(fourthLevelConcepts);
								thirdLevelFolders.add(folderThirdLevelDo);
							} else {
								ConceptDo conceptDo = new ConceptDo();
								if(thirdLevelJsonObject.isNull(GOALS)) {
									
								} else {
									conceptDo.setGoals(thirdLevelJsonObject.getString(GOALS));
								}
								
								conceptDo.setTitle(thirdLevelJsonObject.getString(TITLE));
								conceptDo.setGooruOid(thirdLevelJsonObject.getString(GOORUOID));
								conceptDo.setThumbnails(JsonDeserializer.deserialize(thirdLevelJsonObject.getJSONObject(THUMBNAILS).toString(), ThumbnailDo.class));
								if(k==0) {
									JSONArray resourceArray = thirdLevelJsonObject.getJSONArray(COLLECTION_ITEMS);
									ArrayList<LibraryCollectionItemDo> collectionItems = new ArrayList<LibraryCollectionItemDo>();
									for(int l=0;l<resourceArray.length();l++) {
										LibraryCollectionItemDo libraryCollectionItemDo = new LibraryCollectionItemDo();
										libraryCollectionItemDo.setResource(JsonDeserializer.deserialize(resourceArray.getJSONObject(l).toString(), LibraryResourceDo.class));
										collectionItems.add(libraryCollectionItemDo);
									}
									conceptDo.setCollectionItems(collectionItems);
								}
								thirdLevelConcepts.add(conceptDo);
							}
						}
						if(thirdLevelArray.getJSONObject(0).getString(TYPE).equalsIgnoreCase(FOLDER)) {
							folderTwoLevelDo.setFolderItems(thirdLevelFolders);
						} else {
							folderTwoLevelDo.setCollections(thirdLevelConcepts);
						}
					}
					if(thirdLevelArray.length()>0) {
						secondLevelFolders.add(folderTwoLevelDo);
					}
				}
			}
		} catch (Exception e) {
			
		}
		return secondLevelFolders;
	}
	
	public PartnerConceptListDo deserializePartnerFolderList(JsonRepresentation jsonRep) {
		try {
			if (jsonRep != null && jsonRep.getSize() != -1) {
				return JsonDeserializer.deserialize(jsonRep.getJsonObject().toString(), new TypeReference<PartnerConceptListDo>() {});
			}
		} catch (Exception e) {}
		return new PartnerConceptListDo();
	}
	
	public PartnerFolderListDo deserializePaginatedWorkspaceFolders(JsonRepresentation jsonRep) {
		PartnerFolderListDo searchResults = new PartnerFolderListDo();
		Integer count;
		try {
			if (jsonRep != null && jsonRep.getSize() != -1) {
				JSONArray secondLevelArray = jsonRep.getJsonObject().getJSONArray(SEARCH_RESULTS);
				count = jsonRep.getJsonObject().getInt(COUNT);
				searchResults.setCount(count);
				searchResults.setSearchResult(getDeserializedPartnerList(secondLevelArray, 0));
			}
			return searchResults;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new PartnerFolderListDo();
	}

	@Override
	public ArrayList<LibraryUserDo> getPartners() throws GwtException {
		JsonRepresentation jsonRepresentation = null;
		String url = UrlGenerator.generateUrl(getRestEndPoint(), UrlToken.V2_GET_PARTNERS, getLoggedInSessionToken());
		JsonResponseRepresentation jsonResponseRep = ServiceProcessor.get(url, getRestUsername(), getRestPassword());
		jsonRepresentation=jsonResponseRep.getJsonRepresentation();
		return deserializeCollaborators(jsonRepresentation);
	}

}