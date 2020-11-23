package leetcode.MultiThread.爬虫;

import java.util.List;

interface HtmlParser {
    public List<String> getUrls(String url);
}