package com.abbaqus.vivekathreya_reddit.model;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "favTable")
public class PopularModel {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id ;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "subReddit")
    private String subReddit;
    @ColumnInfo(name = "likes")
    private String likes;
    @ColumnInfo(name = "noOfComments")
    private Integer noOfComments;
    @ColumnInfo(name = "domain")
    private String domain;
    @ColumnInfo(name = "postSince")
    private String postSince;
    @ColumnInfo(name = "imageUrl")
    private String imageUrl;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "url")
    private String url;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubReddit() {
        return subReddit;
    }

    public void setSubReddit(String subReddit) {
        this.subReddit = subReddit;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public Integer getNoOfComments() {
        return noOfComments;
    }

    public void setNoOfComments(Integer noOfComments) {
        this.noOfComments = noOfComments;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPostSince() {
        return postSince;
    }

    public void setPostSince(String postSince) {
        this.postSince = postSince;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "PopularModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subReddit='" + subReddit + '\'' +
                ", likes='" + likes + '\'' +
                ", noOfComments=" + noOfComments +
                ", domain='" + domain + '\'' +
                ", postSince='" + postSince + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
