package mao.elasticsearch_implement_highlight;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Project name(项目名称)：elasticsearch_Implement_highlight
 * Package(包名): mao.elasticsearch_implement_highlight
 * Class(类名): ElasticSearchTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/6/1
 * Time(创建时间)： 13:55
 * Version(版本): 1.0
 * Description(描述)： 测试高亮
 */


@SpringBootTest
public class ElasticSearchTest
{
    private static RestHighLevelClient client;

    /**
     * Before all.
     */
    @BeforeAll
    static void beforeAll()
    {
        client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9200, "http")));
    }

    /**
     * After all.
     *
     * @throws IOException the io exception
     */
    @AfterAll
    static void afterAll() throws IOException
    {
        client.close();
    }

    /**
     * 测试高亮
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *   "query": {
     *     "multi_match":
     *     {
     *       "query": "java",
     *       "fields": ["name","description"]
     *     }
     *   },
     *   "highlight":
     *   {
     *     "fields":
     *     {
     *       "description":
     *       {
     *         "pre_tags": "<div class=\"s\">",
     *         "post_tags": "</div>",
     *         "require_field_match": "false"
     *       },
     *       "name":
     *       {
     *         "pre_tags": "<div class=\"s\">",
     *         "post_tags": "</div>",
     *         "require_field_match": "false"
     *       }
     *     }
     *   }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * {
     *   "took" : 1,
     *   "timed_out" : false,
     *   "_shards" : {
     *     "total" : 1,
     *     "successful" : 1,
     *     "skipped" : 0,
     *     "failed" : 0
     *   },
     *   "hits" : {
     *     "total" : {
     *       "value" : 4,
     *       "relation" : "eq"
     *     },
     *     "max_score" : 0.52048135,
     *     "hits" : [
     *       {
     *         "_index" : "book",
     *         "_id" : "2",
     *         "_score" : 0.52048135,
     *         "_source" : {
     *           "name" : "java编程思想",
     *           "description" : "java语言是世界第一编程语言，在软件开发领域使用人数最多。",
     *           "studymodel" : "201001",
     *           "price" : 68.6,
     *           "timestamp" : "2019-08-25 19:11:35",
     *           "pic" : "group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg",
     *           "tags" : [
     *             "java",
     *             "dev"
     *           ]
     *         },
     *         "highlight" : {
     *           "name" : [
     *             """<div class="s">java</div>编程思想"""
     *           ],
     *           "description" : [
     *             """<div class="s">java</div>语言是世界第一编程语言，在软件开发领域使用人数最多。"""
     *           ]
     *         }
     *       },
     *       {
     *         "_index" : "book",
     *         "_id" : "5",
     *         "_score" : 0.52048135,
     *         "_source" : {
     *           "name" : "java编程思想",
     *           "description" : "java语言是世界第一编程语言，在软件开发领域使用人数最多。",
     *           "studymodel" : "201001",
     *           "price" : 68.6,
     *           "timestamp" : "2022-5-25 19:11:35",
     *           "pic" : "group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg",
     *           "tags" : [
     *             "bootstrap",
     *             "dev"
     *           ]
     *         },
     *         "highlight" : {
     *           "name" : [
     *             """<div class="s">java</div>编程思想"""
     *           ],
     *           "description" : [
     *             """<div class="s">java</div>语言是世界第一编程语言，在软件开发领域使用人数最多。"""
     *           ]
     *         }
     *       },
     *       {
     *         "_index" : "book",
     *         "_id" : "6",
     *         "_score" : 0.52048135,
     *         "_source" : {
     *           "name" : "java编程思想",
     *           "description" : "java语言是世界第一编程语言，在软件开发领域使用人数最多。",
     *           "studymodel" : "201001",
     *           "price" : 68.6,
     *           "timestamp" : "2022-5-25 19:11:35",
     *           "pic" : "group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg",
     *           "tags" : [
     *             "bootstrap",
     *             "dev"
     *           ]
     *         },
     *         "highlight" : {
     *           "name" : [
     *             """<div class="s">java</div>编程思想"""
     *           ],
     *           "description" : [
     *             """<div class="s">java</div>语言是世界第一编程语言，在软件开发领域使用人数最多。"""
     *           ]
     *         }
     *       },
     *       {
     *         "_index" : "book",
     *         "_id" : "3",
     *         "_score" : 0.4745544,
     *         "_source" : {
     *           "name" : "spring开发基础",
     *           "description" : "spring 在java领域非常流行，java程序员都在用。",
     *           "studymodel" : "201001",
     *           "price" : 78.6,
     *           "timestamp" : "2019-08-24 19:21:35",
     *           "pic" : "group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg",
     *           "tags" : [
     *             "spring",
     *             "java"
     *           ]
     *         },
     *         "highlight" : {
     *           "description" : [
     *             """spring 在<div class="s">java</div>领域非常流行，<div class="s">java</div>程序员都在用。"""
     *           ]
     *         }
     *       }
     *     ]
     *   }
     * }
     *
     * </pre>
     * <p>
     * 程序结果：
     * <pre>
     *
     * 总数：4
     * 最大分数：0.52048135
     * 数据：
     * ----price：68.6
     * ----studymodel：201001
     * ----name：java编程思想
     * ----description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ----pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ----timestamp：2019-08-25 19:11:35
     * ----tags：[java, dev]
     * ------高亮：
     * ------name：<div class=\"s\">java</div>编程思想
     * ------description：<div class=\"s\">java</div>语言是世界第一编程语言，在软件开发领域使用人数最多。
     * -------------------------------------
     * ----price：68.6
     * ----studymodel：201001
     * ----name：java编程思想
     * ----description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ----pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ----timestamp：2022-5-25 19:11:35
     * ----tags：[bootstrap, dev]
     * ------高亮：
     * ------name：<div class=\"s\">java</div>编程思想
     * ------description：<div class=\"s\">java</div>语言是世界第一编程语言，在软件开发领域使用人数最多。
     * -------------------------------------
     * ----price：68.6
     * ----studymodel：201001
     * ----name：java编程思想
     * ----description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ----pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ----timestamp：2022-5-25 19:11:35
     * ----tags：[bootstrap, dev]
     * ------高亮：
     * ------name：<div class=\"s\">java</div>编程思想
     * ------description：<div class=\"s\">java</div>语言是世界第一编程语言，在软件开发领域使用人数最多。
     * -------------------------------------
     * ----price：78.6
     * ----studymodel：201001
     * ----name：spring开发基础
     * ----description：spring 在java领域非常流行，java程序员都在用。
     * ----pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ----timestamp：2019-08-24 19:21:35
     * ----tags：[spring, java]
     * ------高亮：
     * ------description：spring 在<div class=\"s\">java</div>领域非常流行，<div class=\"s\">java</div>程序员都在用。
     * -------------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void highlight() throws Exception
    {
        //构建请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("java", "name", "description"));
        //高亮
        /*HighlightBuilder highlightBuilder = new HighlightBuilder();
        List<HighlightBuilder.Field> fields = highlightBuilder.fields();
        fields.add(new HighlightBuilder.Field("name")
                .requireFieldMatch(false)
                .preTags("<div class=\\\"s\\\">")
                .postTags("</div>"));
        fields.add(new HighlightBuilder.Field("description")
                .requireFieldMatch(false)
                .preTags("<div class=\\\"s\\\">")
                .postTags("</div>"));
        searchSourceBuilder.highlighter(highlightBuilder);*/

        //方法二
        searchSourceBuilder.highlighter(new HighlightBuilder()
                .field("name")
                .requireFieldMatch(false)
                .preTags("<div class=\\\"s\\\">")
                .postTags("</div>")
                .field("description")
                .requireFieldMatch(false)
                .preTags("<div class=\\\"s\\\">")
                .postTags("</div>"));

        //放入到请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long total = hits.getTotalHits().value;
        //最大分数
        float maxScore = hits.getMaxScore();
        //数据
        SearchHit[] hitsHits = hits.getHits();
        //打印
        System.out.println("总数：" + total);
        System.out.println("最大分数：" + maxScore);
        System.out.println("数据：");
        for (SearchHit hitsHit : hitsHits)
        {
            //数据
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();
            for (String key : sourceAsMap.keySet())
            {
                Object value = sourceAsMap.get(key);
                System.out.println("----" + key + "：" + value);
            }
            System.out.println("------高亮：");
            Map<String, HighlightField> highlightFields = hitsHit.getHighlightFields();
            for (String key : highlightFields.keySet())
            {
                HighlightField highlightField = highlightFields.get(key);
                String name = highlightField.getName();
                String field = highlightField.getFragments()[0].string();
                System.out.println("------" + name + "：" + field);
            }
            System.out.println("-------------------------------------");

        }

    }
}
