package Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Log {
    Log(){}
    private static boolean LOG = false;
    private static List<String> showTags = new ArrayList<>();

    public static void setLOG(boolean val) {
        LOG = val;
    }

    public static void setShowTags(String[] tags) {
        if (!showTags.isEmpty()) showTags = new ArrayList<>();
        showTags.addAll(Arrays.asList(tags));
    }

    public static void addTag(String tag) {
        showTags.add(tag);
    }

    public static void removeTag(String tag) {
        showTags.remove(tag);
    }

    public static void d(String tag, String event) {
        if (LOG && showTags.contains(tag)) {
            System.out.println(tag + ": " + event);
        }
    }
}

