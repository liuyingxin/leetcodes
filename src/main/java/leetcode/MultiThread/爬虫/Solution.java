package leetcode.MultiThread.爬虫;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

//1242. 多线程网页爬虫

/**
 * 给你一个初始地址 startUrl 和一个 HTML 解析器接口 HtmlParser，请你实现一个 多线程的网页爬虫，用于获取与 startUrl 有 相同主机名 的所有链接。
 *
 * 以 任意 顺序返回爬虫获取的路径。
 *
 * 爬虫应该遵循：
 *
 * 从 startUrl 开始
 * 调用 HtmlParser.getUrls(url) 从指定网页路径获得的所有路径。
 * 不要抓取相同的链接两次。
 * 仅浏览与 startUrl 相同主机名 的链接。
 *
 *
 * 如上图所示，主机名是 example.org 。简单起见，你可以假设所有链接都采用 http 协议，并且没有指定 端口号。举个例子，链接 http://leetcode.com/problems 和链接 http://leetcode.com/contest 属于同一个 主机名， 而 http://example.org/test 与 http://example.com/abc 并不属于同一个 主机名。
 *
 * HtmlParser 的接口定义如下：
 *
 * interface HtmlParser {
 * // Return a list of all urls from a webpage of given url.
 * // This is a blocking call, that means it will do HTTP request and return when this request is finished.
 * public List<String> getUrls(String url);
 * }
 * 注意一点，getUrls(String url) 模拟执行一个HTTP的请求。 你可以将它当做一个阻塞式的方法，直到请求结束。 getUrls(String url) 保证会在 15ms 内返回所有的路径。 单线程的方案会超过时间限制，你能用多线程方案做的更好吗？
 *
 * 对于问题所需的功能，下面提供了两个例子。为了方便自定义测试，你可以声明三个变量 urls，edges 和 startUrl。但要注意你只能在代码中访问 startUrl，并不能直接访问 urls 和 edges。
 *
 *
 *
 * 拓展问题：
 *
 * 假设我们要要抓取 10000 个节点和 10 亿个路径。并且在每个节点部署相同的的软件。软件可以发现所有的节点。我们必须尽可能减少机器之间的通讯，并确保每个节点负载均衡。你将如何设计这个网页爬虫？
 * 如果有一个节点发生故障不工作该怎么办？
 * 如何确认爬虫任务已经完成？
 *
 */
class Solution {
    private final Set<String> set = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
    private final List<String> result = Collections.synchronizedList(new ArrayList<String>());
    private String HOSTNAME = null;

    public boolean judgeHostname(String url) {
        int idx = url.indexOf('/', 7);
        String hostName = (idx != -1) ? url.substring(0, idx) : url;
        return hostName.equals(HOSTNAME);
    }

    private void initHostName(String url) {
        int idx = url.indexOf('/', 7);
        HOSTNAME = (idx != -1) ? url.substring(0, idx) : url;
    }

    public void getUrl(String startUrl, HtmlParser htmlParser) {
        result.add(startUrl);
        List<String> res = htmlParser.getUrls(startUrl);
        List<Thread> threads = new ArrayList<>();
        for (String url : res) {
            if (judgeHostname(url) && !set.contains(url)) {
                set.add(url);
                threads.add(new Thread(() -> {
                    getUrl(url, htmlParser);
                }));
            }
        }
        for (Thread thread : threads) {
            thread.start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        initHostName(startUrl);
        set.add(startUrl);
        getUrl(startUrl, htmlParser);
        return result;
    }
}
