package Managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class BackupManager {

    private String host;
    private String database;
    private String user;
    private String password;

    public BackupManager(String host, String database, String user, String password) {
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public void createBackup(String filePath) {
        Runtime rt = Runtime.getRuntime();
        Process p;
        ProcessBuilder pb;

        // Configure the ProcessBuilder with the pg_dump command and its parameters
        pb = new ProcessBuilder(
                "pg_dump",
                "--host", host,
                "--port", "5432",
                "--username", user,
                "--no-password",
                "--format", "custom",
                "--blobs",
                "--verbose",
                "--file", filePath, database);

        try {
            // Set the PGPASSWORD environment variable to provide the PostgreSQL password
            final Map<String, String> env = pb.environment();
            env.put("PGPASSWORD", password);

            // Start the process
            p = pb.start();

            // Read and print any error messages from the process
            final BufferedReader r = new BufferedReader(
                    new InputStreamReader(p.getErrorStream()));
            String line = r.readLine();
            while (line != null) {
                System.err.println(line);
                line = r.readLine();
            }
            r.close();

            // Wait for the process to complete and print its exit value
            p.waitFor();
            System.out.println("Backup process exit value: " + p.exitValue());

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

