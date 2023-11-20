package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private final static String NEWS_NAME_LINK = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private final static URI POPULAR_NEWS_LINK = URI.create("https://hacker-news.firebaseio.com/v0/topstories.json");
    private final static int SUCCESS_CODE = 200;
    private final Pattern titlePattern = Pattern.compile("\"(title)\":\"([^\"]+)\"");

    public long[] hackerNewsTopStories() {
        HttpResponse<String> response = sendRequest(POPULAR_NEWS_LINK);

        if (Objects.requireNonNull(response).statusCode() == SUCCESS_CODE) {
            String[] topStories = response.body().replace("[", "").replace("]", "").split(",");
            return Arrays.stream(topStories).mapToLong(Long::parseLong).toArray();
        }

        return new long[0];
    }

    public String news(long id) {
        HttpResponse<String> response = sendRequest(URI.create(String.format(NEWS_NAME_LINK, id)));

        if (Objects.requireNonNull(response).statusCode() == SUCCESS_CODE) {
            Matcher matcher = titlePattern.matcher(response.body());
            if (matcher.find()) {
                return  matcher.group(2);
            }
        }

        return "";
    }

    private HttpResponse<String> sendRequest(URI uri) {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
            return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException exception) {
            return null;
        }

    }


}
