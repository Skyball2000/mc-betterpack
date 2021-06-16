import org.json.JSONObject;
import yanwittmann.file.File;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Build {

    private final static String MODE_BUILD = "build";
    private final static String MODE_CLEAN = "clean";

    /**
     * Creates a new directory in which a publishable data pack is built
     */
    public static void main(String[] args) throws IOException {
        String mode = "build";
        for (String arg : args) {
            if (arg.equals(MODE_BUILD)) mode = MODE_BUILD;
            else if (arg.equals(MODE_CLEAN)) mode = MODE_CLEAN;
        }

        if (mode.equals(MODE_BUILD)) build();
        if (mode.equals(MODE_CLEAN)) clean();

    }

    private static void clean() {
        File buildDir = new File("build/mc_betterpack");

        // reset last build
        if (buildDir.exists())
            buildDir.deleteDirectory();
    }

    private static void build() throws IOException {

        // where to get files from
        File getDataDir = new File("data");
        File getPackMcMeta = new File("pack.mcmeta");
        File getPackPng = new File("pack.png");

        // where to build files to
        File buildDir = new File("build/mc_betterpack");
        File buildDataDir = new File(buildDir, "data");
        File buildPackMcMeta = new File(buildDir, "pack.mcmeta");
        File buildPackPng = new File(buildDir, "pack.png");

        // reset last build
        if (buildDir.exists())
            buildDir.deleteDirectory();
        buildDir.mkdirs();

        // copy data directory
        getDataDir.copyDirectory(buildDataDir);

        // copy pack metadata and icon
        getPackMcMeta.copyFile(buildPackMcMeta);
        getPackPng.copyFile(buildPackPng);

        // minify json files
        minifyJson(buildDataDir);
    }

    /**
     * Recursively minify all json files in the given directory
     */
    private static void minifyJson(File dir) {
        if (dir != null && dir.isDirectory()) {
            List<File> files = dir.getFiles().stream().map(File::new).collect(Collectors.toList());
            for (File file : files) {
                if (file.isDirectory()) minifyJson(dir);
                else if (file.getName().contains(".json")) {
                    try {
                        file.write(String.valueOf(new JSONObject(String.join("", file.readToArrayList()))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
