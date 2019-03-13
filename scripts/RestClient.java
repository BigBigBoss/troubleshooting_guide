import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class RestClient {

    private final static Logger LOGGER = Logger.getLogger(RestClient.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Start with parameters:" + Arrays.toString(args));

        var simpleRequestPath = simpleRequestPath(args);
        var patternRequestPath = patternRequestPath(args);
        var iterates = iterates(args);
        var isUserModifiable = isUserModifiable(args);

        var request = isUserModifiable ? readFile(patternRequestPath)
            : readFile(simpleRequestPath);

        var httpClient = HttpClient.newBuilder().build();
        try {
            for (int i = 0; i < iterates; i++) {
                sendRequest(httpClient,
                    String.format(request, UUID.randomUUID().toString(), UUID.randomUUID().toString()));
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Request didn't send!", e);
        }
        LOGGER.info("Complete!!!");
    }

    private static String readFile(String s) {
        String fileContent = "";
        try {
            fileContent = Files.readString(Paths.get(s));
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Cannot read file", e);
        }
        return fileContent;
    }

    private static String simpleRequestPath(String[] args) {
        return args.length > 0 ? String.valueOf(args[0]) : "";
    }

    private static String patternRequestPath(String[] args) {
        return args.length > 1 ? String.valueOf(args[1]) : "";
    }

    private static int iterates(String[] args) {
        return args.length > 2 ? Integer.valueOf(args[2]) : 1;
    }

    private static boolean isUserModifiable(String[] args) {
        return args.length > 3 && Boolean.valueOf(args[3]);
    }

    private static void sendRequest(HttpClient httpClient, String json)
        throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9090/storyEvaluator/story"))
            .POST(BodyPublishers.ofString(json))
            .header("Content-Type", "application/json")
            .build();
        long start = System.nanoTime();
        var response = httpClient.send(request, BodyHandlers.ofString());
        var finish = System.nanoTime();
        var durationInMs = TimeUnit.MILLISECONDS.convert(finish - start, TimeUnit.NANOSECONDS);

        System.out.println(durationInMs + "ms. Response: " + response.body());
    }
}