简体中文 | [English](./README.md)

# Google Indexing
<p align="center">
    <a href="https://search.maven.org/search?q=g:%22net.renfei%22%20AND%20a:%22googleindexing%22"><img src="https://img.shields.io/maven-central/v/net.renfei/googleindexing.svg?label=Maven%20Central" alt="Latest Stable Version"/></a>
<a href="https://travis-ci.org/NeilRen/GoogleIndexing"><img src="https://travis-ci.org/NeilRen/GoogleIndexing.svg?branch=master"/></a>
<a href="https://www.codacy.com/app/NeilRen/GoogleIndexing?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=NeilRen/GoogleIndexing&amp;utm_campaign=Badge_Grade"><img src="https://api.codacy.com/project/badge/Grade/1372d594a218417a83535ee0fbb8bfb2"/></a>
<a href="https://ci.appveyor.com/project/NeilRen/GoogleIndexing"><img src="https://ci.appveyor.com/api/projects/status/cmxr90l89uc68jwv/branch/master?svg=true"/></a>
</p>
借助 Indexing API，任何网站所有者都可在添加或移除网页时直接告知 Google。这样一来，Google 就能及时整理网页并安排新的抓取，从而带来更优质的用户流量。目前，Indexing API 只能用于抓取包含 JobPosting 或 BroadcastEvent（嵌套于 VideoObject）的网页。对于包含很多短效网页（如招聘信息或直播视频）的网站，Indexing API 会通过为不同的内容分别推送更新，使搜索结果中的内容保持最新状态。

## 安装依赖
如果您使用Apache Maven来管理Java项目，只需在项目的`pom.xml`文件加入相应的依赖项即可。您只需在`pom.xml`中声明以下依赖：
```xml
<dependency>
    <groupId>net.renfei</groupId>
    <artifactId>googleindexing</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 开始使用 
1.启用 Indexing API，创建新服务帐号，在 Search Console 中验证所有权并获取用于验证 API 调用的访问令牌，从而满足前提条件。  
2.发送请求，通知 Google 有新增、更新或删除的网页。  
3.您可能需要比默认配额更多的配额。要查看当前配额和请求更多配额，请参阅配额。 

## 案例
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

## 中国大陆网络
您需要注意：在中国大陆的网络中无法访问谷歌的API接口，您需要让程序运行在可以访问谷歌的网络环境中。