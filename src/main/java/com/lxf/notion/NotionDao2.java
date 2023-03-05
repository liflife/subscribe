//package com.lxf.notion;
//
//import com.sun.org.apache.regexp.internal.RE;
//import okhttp3.*;
//import tw.yukina.notion.sdk.builder.TextBuilder;
//import tw.yukina.notion.sdk.client.api.ApiClient;
//import tw.yukina.notion.sdk.client.api.ApiClientFactory;
//import tw.yukina.notion.sdk.endpoint.database.QueryDatabase;
//import tw.yukina.notion.sdk.model.ObjectType;
//import tw.yukina.notion.sdk.model.TextColor;
//import tw.yukina.notion.sdk.model.block.*;
//import tw.yukina.notion.sdk.model.block.heading.HeadingBlockHelper;
//import tw.yukina.notion.sdk.model.block.heading.HeadingOneBlock;
//import tw.yukina.notion.sdk.model.block.heading.HeadingTwoBlock;
//import tw.yukina.notion.sdk.model.common.IconEmoji;
//import tw.yukina.notion.sdk.model.common.PropertyType;
//import tw.yukina.notion.sdk.model.common.parent.DatabaseParent;
//import tw.yukina.notion.sdk.model.common.parent.PageParent;
//import tw.yukina.notion.sdk.model.common.parent.Parent;
//import tw.yukina.notion.sdk.model.common.parent.ParentType;
//import tw.yukina.notion.sdk.model.common.rich.RichText;
//import tw.yukina.notion.sdk.model.database.Database;
//import tw.yukina.notion.sdk.model.endpoint.block.RequestAppendChildrenBlockList;
//import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;
//import tw.yukina.notion.sdk.model.endpoint.database.query.ResponsePageList;
//import tw.yukina.notion.sdk.model.endpoint.page.RequestCreatePage;
//import tw.yukina.notion.sdk.model.helper.BlockHelper;
//import tw.yukina.notion.sdk.model.page.Page;
//import tw.yukina.notion.sdk.model.page.property.PageProperty;
//import tw.yukina.notion.sdk.model.page.property.TitleProperty;
//
//import java.io.IOException;
//import java.nio.file.Path;
//import java.util.*;
//
//public class NotionDao2 {
////        String bearerToken="secret_2TYp6lKrLelmd7aTnzB8QyBH8YXhiH7ziKW8RTgQpH3";
//
//    static String bearerToken="secret_r9DpMOKUJ4UDdtdaliifxq7EZhOLt6yY1CCQbHo5Roo";
////    static String databaseId="28794e0ad59540f7b8bbc9532b8af39a";
//    static String databaseId="10b8ccba178749a79d455092e2bddadb";
//    static  String pageId="10b8ccba178749a79d455092e2bddadb";
//
//    public static void createPage() throws IOException {
//
//
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\n    \"parent\": {\n        \"database_id\": \"{{DATABASE_ID}}\"\n    },\n    \"properties\": {\n        \"Type\": {\n            \"select\": {\n                \"id\": \"f96d0d0a-5564-4a20-ab15-5f040d49759e\",\n                \"name\": \"Article\",\n                \"color\": \"default\"\n            }\n        },\n        \"Score /5\": {\n            \"select\": {\n                \"id\": \"5c944de7-3f4b-4567-b3a1-fa2c71c540b6\",\n                \"name\": \"⭐️⭐️⭐️⭐️⭐️\",\n                \"color\": \"default\"\n            }\n        },\n        \"Name\": {\n            \"title\": [\n                {\n                    \"text\": {\n                        \"content\": \"New Media Article\"\n                    }\n                }\n            ]\n        },\n        \"Status\": {\n            \"select\": {\n                \"id\": \"8c4a056e-6709-4dd1-ba58-d34d9480855a\",\n                \"name\": \"Ready to Start\",\n                \"color\": \"yellow\"\n            }\n        },\n        \"Publisher\": {\n            \"select\": {\n                \"id\": \"01f82d08-aa1f-4884-a4e0-3bc32f909ec4\",\n                \"name\": \"The Atlantic\",\n                \"color\": \"red\"\n            }\n        },\n        \"Publishing/Release Date\": {\n            \"date\": {\n                \"start\": \"2020-12-08T12:00:00Z\",\n                \"end\": null\n            }\n        },\n        \"Link\": {\n            \"url\": \"https://www.nytimes.com/2018/10/21/opinion/who-will-teach-silicon-valley-to-be-ethical.html\"\n        },\n        \"Summary\": {\n            \"text\": [\n                {\n                    \"type\": \"text\",\n                    \"text\": {\n                        \"content\": \"Some think chief ethics officers could help technology companies navigate political and social questions.\",\n                        \"link\": null\n                    },\n                    \"annotations\": {\n                        \"bold\": false,\n                        \"italic\": false,\n                        \"strikethrough\": false,\n                        \"underline\": false,\n                        \"code\": false,\n                        \"color\": \"default\"\n                    },\n                    \"plain_text\": \"Some think chief ethics officers could help technology companies navigate political and social questions.\",\n                    \"href\": null\n                }\n            ]\n        },\n        \"Read\": {\n            \"checkbox\": false\n        }\n    }\n}");
//        Request request = new Request.Builder()
//                .url("https://api.notion.com/v1/pages/")
//                .method("POST", body)
//                .addHeader("Authorization", "Bearer secret_r9DpMOKUJ4UDdtdaliifxq7EZhOLt6yY1CCQbHo5Roo")
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Notion-Version", "2021-05-13")
//                .build();
//        Response response = client.newCall(request).execute();
//        System.out.println(response.toString());
//    }
//
//    public static void main(String[] args) throws IOException {
////        String bearerToken="secret_2TYp6lKrLelmd7aTnzB8QyBH8YXhiH7ziKW8RTgQpH3";
////        createPage();
//        ApiClientFactory apiClientFactory = new ApiClientFactory();
//        apiClientFactory.setToken(bearerToken);
//        apiClientFactory.applyDefaultSetting();
//        ApiClient apiClient = apiClientFactory.build();
////        Page page = apiClient.RetrievePage(pageId);
//
////        RequestCreatePage requestCreatePage = new RequestCreatePage();
////        Map<String, PageProperty> pagePropertyMap=new HashMap<>();
////        TitleProperty titleProperty = new TitleProperty();
////
////
////        pagePropertyMap.put("title",titleProperty);
////        requestCreatePage.setProperties(pagePropertyMap);
////        apiClient.CreatePage(requestCreatePage);
//
//
////        Map<String, PageProperty> propertyMap = page.getPropertyMap();
////        propertyMap.forEach((key,value)->{
////            String id = value.getId();
////            System.out.println("key="+key);
////            System.out.println("value="+value.toString());
////            System.out.println("id="+id);
////        });
//        ResponseBlockList responseBlockList = apiClient.retrieveBlockChildren(pageId);
//
//        List<Block> blocks = responseBlockList.getBlocks();
//        for (Block block : blocks) {
//            System.out.println(block.toString());
//        }
////
//        RequestAppendChildrenBlockList requestAppendChildrenBlockList = new RequestAppendChildrenBlockList();
//        List<Block> appendBlocks = new ArrayList<>();
//
////        for (Block block : blocks) {
////            ChildPageBlock blockTmp=(ChildPageBlock)block;
////            blockTmp.getChildPage().setTitle("test-1");
////            appendBlocks.add(block);
////        }
////
//        for (int i = 0; i < 5; i++) {
//            TextBuilder textBuilder = new TextBuilder();
//            textBuilder.setContent("test-" + i).setBold();
////            HeadingOneBlock defaultHeadingOne = HeadingBlockHelper.createDefaultHeadingOne(Arrays.asList(textBuilder.build()));
//            ParagraphBlock of1 = ParagraphBlock.of(Arrays.asList(textBuilder.build()));
////            ChildPageBlock of = ChildPageBlock.of("test-" + i);
////            ChildPageBlock childPageBlock = BlockHelper.createDefaultChildPageBlock("test-" + i);
//            appendBlocks.add(of1);
//        }
////
////
//        requestAppendChildrenBlockList.setBlocks(appendBlocks);
//
//        RequestCreatePage requestCreatePage = new RequestCreatePage();
//        PageParent of = PageParent.of(pageId);
//        DatabaseParent of1 = DatabaseParent.of(pageId);
//        of1.setParentType(ParentType.PAGE);
//        requestCreatePage.setParent(of1);
//        requestCreatePage.setChildren(appendBlocks);
//        apiClient.CreatePage(requestCreatePage);
////        apiClient.appendBlockChildren(pageId, requestAppendChildrenBlockList);
//
////        RequestCreatePage requestCreatePage = new RequestCreatePage();
////        PageParent of = PageParent.of(page.getId());
////        requestCreatePage.setParent(of);
////        requestCreatePage.setProperties(propertyMap);
////        System.out.println(requestCreatePage.toString());
////        apiClient.CreatePage(requestCreatePage);
//
//    }
//}
