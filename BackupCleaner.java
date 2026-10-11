import java.nio.file.*;
import java.nio.file.attribute.*;
import java.time.*;
import java.io.IOException;

public class BackupCleaner {
    public static void main(String[] args) throws IOException {
        Path folder = Paths.get("backups");
        Instant limit = Instant.now().minus(Duration.ofDays(30));

        Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path file,
                    BasicFileAttributes attr) throws IOException {
                if (attr.lastModifiedTime().toInstant().isBefore(limit)) {
                    Files.delete(file);
                    System.out.println("Deleted: " + file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
