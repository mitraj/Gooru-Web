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
package org.ednovo.gooru.client.service;

import java.util.ArrayList;

import java.util.Map;

import org.ednovo.gooru.client.SimpleAsyncCallback;
import org.ednovo.gooru.shared.model.content.ClasspageItemDo;
import org.ednovo.gooru.shared.model.content.CollectionDo;
import org.ednovo.gooru.shared.model.content.CollectionItemDo;
import org.ednovo.gooru.shared.model.content.CollectionItemsList;
import org.ednovo.gooru.shared.model.content.ContentReportDo;
import org.ednovo.gooru.shared.model.content.ReactionDo;
import org.ednovo.gooru.shared.model.content.ResoruceCollectionDo;
import org.ednovo.gooru.shared.model.player.CommentsDo;
import org.ednovo.gooru.shared.model.player.CommentsListDo;
import org.ednovo.gooru.shared.model.player.FeaturedContentDo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PlayerAppServiceAsync extends BaseServiceAsync {

	
	public void getSimpleCollectionDetils(String simpleCollectionId,String resourceId,String tabView,String rootNodeId,AsyncCallback<CollectionDo> callback);
	
	public void getSimpleCollectionDetils(String apikey,String simpleCollectionId,String resourceId,String tabView,String rootNodeId,AsyncCallback<CollectionDo> callback);
	
	public void getResourceCollectionsList(String resourceGooruOid,String pageNum,String pageSize,AsyncCallback<ResoruceCollectionDo> callback);
	
	public void getResourceCollectionItem(String apiKey,String resourceId,String tabView,AsyncCallback<CollectionItemDo> callback);
	
	public void getShortenShareUrl(String contentGooruOid,  AsyncCallback<Map<String, String>> callback);
	
	public void updateViewCount(String gooruid,String viewCount,String resourceType,AsyncCallback<String> callback);
	
	public void startActivityPlayerLog(String activityEventId,String activityParentEventId,String eventName,String gooruOid,String resourceGooruOid,String context,String userAgent,AsyncCallback<String> callback);
	
	public void stopActivityPlayerLog(String activityEventId,String activityParentEventId,String eventName,String gooruOid,String resourceGooruOid,String context,String userAgent,AsyncCallback<String> callback);
	
	public void createSessionTracker(String collectionGooruOid,AsyncCallback<String> callback);
	
	public void updateSessionInCollection(String sessionTrackerId,AsyncCallback<String> callback);
	
	public void createSessionItemInCollection(String sessionTrackerId,String collectionItemId, String resourceGooruOid,AsyncCallback<String> callback);
	
	public void createSessionItemAttemptTry(String sessionTrackerId,String sessionItemTrackerId, Integer answerId, String attemptResult,AsyncCallback<String> callback);
	
	public void createSessionItemAttemptTryForOe(String sessionTrackerId,String sessionItemTrackerId,String answerId, String attemptStatus,String attemptAnswerResult,AsyncCallback<String> callback);
	
	public void sendEmail(String fromEmail,String toEmail,String copyEmail,String subject,String message,AsyncCallback<String> callback);
	
	public void getUserProfileVisibility(String gooruUid,AsyncCallback<Boolean> callback);
	
	public void copyCollection(String collectionId,String collectionTile,AsyncCallback<String> callback);
	
	public void copyCollectionItem(String collectionItemId,String collectionId,AsyncCallback<String> callback);
	
	public void getWorkspaceCollections(String userId,String offset,String limit,AsyncCallback<ArrayList<CollectionItemsList>> callback);
	
	public void updateContentThumbsRating(String resourceGooruOid,int userThumbsRataing,AsyncCallback<String> callback);
	
	public void getContentReport(String associatedGooruOid,String gooruUid,AsyncCallback<ArrayList<ContentReportDo>> callback);
	
	public void createContentReport(String associatedGooruOid,String freeText,ArrayList<String> contentReportList,String deleteContentReportGooruOids,AsyncCallback<ContentReportDo> callback);
	
	public void deleteContentReport(String associatedGooruOid,AsyncCallback<Void> callback);
	
	public void createCommentForCollection(String gooruCollectionId, String userCommentsEntered,AsyncCallback<CommentsDo> callback);
	
	public void getCollectionCommentsList(String gooruOid, String offset, String pageLimit,AsyncCallback<CommentsListDo> callback);
	
	public void deleteCollectionCommentbyCommentUid(String commentUid,AsyncCallback<Void> callback);
	
	public void updateCollectionCommentbyCommentUid(String commentUid, String commentsUpdatedByUser, AsyncCallback<CommentsDo> callback);

	public void createReaction(String resourceId, String reactionText, String gooruReactionId, String collectionId, String createStudyPlayerReaction, AsyncCallback<ReactionDo> callback); 

	public void getResourceReaction(String gooruOid, String gooruUid,AsyncCallback<ArrayList<ReactionDo>> callback); 
	
	public void generatePdf(String innerHtml,String completedDateTime,AsyncCallback<String> callback);
	
	void sendEmailWithPdf(String toAddress, String fromAddress, String cfm, String subject, String message, String Url, String FileName, AsyncCallback<String> callback);

	public void getFeaturedContent(AsyncCallback<ArrayList<FeaturedContentDo>> callback);

	public void deleteReaction(String gooruReactionId,AsyncCallback<Void> callback);

	
	
}
