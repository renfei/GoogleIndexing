English | [简体中文](./README-CN.md)

# Google Indexing
<p align="center">
<a href="https://search.maven.org/search?q=g:%22net.renfei%22%20AND%20a:%22googleindexing%22"><img src="https://img.shields.io/maven-central/v/net.renfei/googleindexing.svg?label=Maven%20Central" alt="Latest Stable Version"/></a>
<a href="https://travis-ci.org/NeilRen/GoogleIndexing"><img src="https://travis-ci.org/NeilRen/GoogleIndexing.svg?branch=master"/></a>
<a href="https://www.codacy.com/app/NeilRen/GoogleIndexing?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=NeilRen/GoogleIndexing&amp;utm_campaign=Badge_Grade"><img src="https://api.codacy.com/project/badge/Grade/1372d594a218417a83535ee0fbb8bfb2"/></a>
<a href="https://ci.appveyor.com/project/NeilRen/GoogleIndexing"><img src="https://ci.appveyor.com/api/projects/status/cmxr90l89uc68jwv/branch/master?svg=true"/></a>
</p>
The Indexing API allows any site owner to directly notify Google when pages are added or removed. This allows Google to schedule pages for a fresh crawl, which can lead to higher quality user traffic. Currently, the Indexing API can only be used to crawl pages with either JobPosting or BroadcastEvent embedded in a VideoObject. For websites with many short-lived pages like job postings or livestream videos, the Indexing API keeps content fresh in search results because it allows updates to be pushed individually.

## Installation
If you use Apache Maven to manage Java projects, you only need to add corresponding dependencies to the pom.xml files of the projects.
You only need to declare the following dependencies in the `pom.xml` file
```xml
<dependency>
    <groupId>net.renfei</groupId>
    <artifactId>googleindexing</artifactId>
    <version>1.0.1</version>
</dependency>
```

## Get started
1. Complete the prerequisites by enabling the Indexing API, creating a new service account, verifying ownership in Search Console, and getting an access token to authenticate your API call.
2. Send requests to notify Google of new, updated, or deleted web pages.
3. You may need more quota than the default. To view your current quota and request more quota, see Quota.
4. Please refer to: https://developers.google.com/search/apis/indexing-api/v3/prereqs

## Demo
```java
import com.alibaba.fastjson.JSON;
import com.google.api.services.indexing.v3.model.UrlNotificationMetadata;
import net.renfei.googleindexing.GoogleIndexing;
import net.renfei.googleindexing.entity.UrlNotification;
import net.renfei.googleindexing.entity.UrlNotificationType;

public class Demo {
    public static void main(String[] args) {
        try {
            GoogleIndexing googleIndexing = new GoogleIndexing("/Users/renfei/Google/Ren-Fei-5a8df7c2b912.json");
            UrlNotification urlNotification = new UrlNotification();
            urlNotification.setUrl("https://www.renfei.net");
            urlNotification.setType(UrlNotificationType.URL_UPDATED);
            UrlNotificationMetadata urlNotificationMetadata = googleIndexing.publish(urlNotification);
            System.out.printf(JSON.toJSONString(urlNotificationMetadata));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
```