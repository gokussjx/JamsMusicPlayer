/*
 * Copyright (C) 2014 Saravan Pantham
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jams.music.player.GMusicHelpers;

import org.json.JSONObject;

public class WebClientSongsSchema implements IJsonObject<WebClientSongsSchema> {
    private int mTotalTracks;
    private boolean mSubjectToCuration;
    private String mName;
    private int mTotalDiscs;
    private String mTitleNorm;
    private String mAlbumNorm;
    private int mTrack;
    private String mAlbumArtUrl;
    private String mUrl;
    private long mCreationDate;
    private String mAlbumArtistNorm;
    private String mArtistNorm;
    private long mLastPlayed;
    private String mMatchedId;
    private int mType;
    private int mDisc;
    private String mGenre;
    private int mBeatsPerMinute;
    private String mAlbum;
    private String mId;
    private String mComposer;
    private String mTitle;
    private String mAlbumArtist;
    private int mYear;
    private String mArtist;
    private long mDurationMillis;
    private boolean mIsDeleted;
    private int mPlayCount;
    private String mRating;
    private String mComment;
    private String mPlaylistEntryId;

    public String getAlbum() {
        return mAlbum;
    }

    public void setAlbum(String album) {
        mAlbum = album;
    }

    public String getAlbumArtist() {
        return mAlbumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        mAlbumArtist = albumArtist;
    }

    public String getAlbumArtistNorm() {
        return mAlbumArtistNorm;
    }

    public void setAlbumArtistNorm(String albumArtistNorm) {
        mAlbumArtistNorm = albumArtistNorm;
    }

    public String getAlbumArtUrl() {
        return mAlbumArtUrl;
    }

    public void setAlbumArtUrl(String albumArtUrl) {
        mAlbumArtUrl = albumArtUrl;
    }

    public String getAlbumNorm() {
        return mAlbumNorm;
    }

    public void setAlbumNorm(String albumNorm) {
        mAlbumNorm = albumNorm;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getArtistNorm() {
        return mArtistNorm;
    }

    public void setArtistNorm(String artistNorm) {
        mArtistNorm = artistNorm;
    }

    public int getBeatsPerMinute() {
        return mBeatsPerMinute;
    }

    public void setBeatsPerMinute(int beatsPerMinute) {
        mBeatsPerMinute = beatsPerMinute;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public String getComposer() {
        return mComposer;
    }

    public void setComposer(String composer) {
        mComposer = composer;
    }

    public float getCreationDate() {
        return mCreationDate;
    }

    public void setCreationDate(long creationDate) {
        mCreationDate = creationDate;
    }

    public int getDisc() {
        return mDisc;
    }

    public void setDisc(int disc) {
        mDisc = disc;
    }

    public long getDurationMillis() {
        return mDurationMillis;
    }

    public void setDurationMillis(long durationMillis) {
        mDurationMillis = durationMillis;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public double getLastPlayed() {
        return mLastPlayed;
    }

    public void setLastPlayed(long lastPlayed) {
        mLastPlayed = lastPlayed;
    }

    public String getMatchedId() {
        return mMatchedId;
    }

    public void setMatchedId(String matchedId) {
        mMatchedId = matchedId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPlayCount() {
        return mPlayCount;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitleNorm() {
        return mTitleNorm;
    }

    public void setTitleNorm(String titleNorm) {
        mTitleNorm = titleNorm;
    }

    public int getTotalDiscs() {
        return mTotalDiscs;
    }

    public void setTotalDiscs(int totalDiscs) {
        mTotalDiscs = totalDiscs;
    }

    public int getTotalTracks() {
        return mTotalTracks;
    }

    public void setTotalTracks(int totalTracks) {
        mTotalTracks = totalTracks;
    }

    public int getTrack() {
        return mTrack;
    }

    public void setTrack(int track) {
        mTrack = track;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        mYear = year;
    }

    public String getPlaylistEntryId() {
        return mPlaylistEntryId;
    }

    public boolean isDeleted() {
        return mIsDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        mIsDeleted = isDeleted;
    }

    public boolean isSubjectToCuration() {
        return mSubjectToCuration;
    }

    public void setSubjectToCuration(boolean subjectToCuration) {
        mSubjectToCuration = subjectToCuration;
    }

    public void setPlaycount(int playcount) {
        mPlayCount = playcount;
    }

    public void setEntryId(String playlistEntryId) {
        mPlaylistEntryId = playlistEntryId;
    }

    @Override
    public WebClientSongsSchema fromJsonObject(JSONObject jsonObject) {

        if (jsonObject != null) {
            mTotalTracks = jsonObject.optInt("totalTracks");
            mSubjectToCuration = jsonObject.optBoolean("subjectToCuration");
            mName = jsonObject.optString("name", null);
            mTotalDiscs = jsonObject.optInt("totalDiscs");
            mTitleNorm = jsonObject.optString("titleNorm", null);
            mAlbumNorm = jsonObject.optString("albumNorm", null);
            mTrack = jsonObject.optInt("track");
            mAlbumArtUrl = jsonObject.optString("albumArtUrl", null);
            mUrl = jsonObject.optString("url", null);
            mCreationDate = jsonObject.optLong("creationDate");
            mAlbumArtistNorm = jsonObject.optString("albumArtistNorm", null);
            mArtistNorm = jsonObject.optString("artistNorm", null);
            mLastPlayed = jsonObject.optLong("lastPlayed");
            mMatchedId = jsonObject.optString("matchedId", null);
            mType = jsonObject.optInt("type");
            mDisc = jsonObject.optInt("disc");
            mGenre = jsonObject.optString("genre", null);
            mBeatsPerMinute = jsonObject.optInt("beatsPerMinute");
            mAlbum = jsonObject.optString("album", null);
            mId = jsonObject.optString("id", null);
            mComposer = jsonObject.optString("composer", null);
            mTitle = jsonObject.optString("title", null);
            mAlbumArtist = jsonObject.optString("albumArtist", null);
            mYear = jsonObject.optInt("year");
            mArtist = jsonObject.optString("artist", null);
            mDurationMillis = jsonObject.optLong("durationMillis");
            mIsDeleted = jsonObject.optBoolean("deleted");
            mPlayCount = jsonObject.optInt("playCount");
            mRating = jsonObject.optString("rating", null);
            mComment = jsonObject.optString("comment", null);
            mPlaylistEntryId = jsonObject.optString("playlistEntryId");
        }

        //This method returns itself to support chaining.
        return this;
    }

}
