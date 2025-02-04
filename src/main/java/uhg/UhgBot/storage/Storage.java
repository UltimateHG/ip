package uhg.UhgBot.storage;

import java.io.*;
import java.nio.file.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uhg.UhgBot.task.*;

public class Storage {
    private final String filePath;
    private static final DateTimeFormatter FILE_DATE_FORMAT = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Creates a new Storage object. This object is used to read and write tasks to a file. Accepts a file path string as input.
     * 
     * @param filePath The path to the file to read and write tasks to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the constructor. If the file does not exist, an empty list is returned.
     * 
     * @return A list of tasks loaded from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public List<Task> load() throws IOException {
        createDataDirectory();
        List<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filePath);
        
        if (!Files.exists(path)) {
            return tasks;
        }

        return Files.lines(path)
            .map(this::parseTaskFromFile)
            .filter(task -> task != null)
            .collect(Collectors.toList());
    }

    /**
     * Saves the list of tasks to the file specified in the constructor. If the file does not exist, it will be created.
     * 
     * @param tasks The list of tasks to save to the file.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void save(List<Task> tasks) throws IOException {
        createDataDirectory();
        List<String> lines = tasks.stream()
            .map(this::formatTaskForFile)
            .collect(Collectors.toList());
        Files.write(Paths.get(filePath), lines);
    }

    /**
     * Deletes the file specified in the constructor.
     * 
     * @throws IOException If an error occurs while deleting the file.
     */
    private void createDataDirectory() throws IOException {
        Path directory = Paths.get(filePath).getParent();
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
    }

    /**
     * Parses a task from a line in the file.
     * 
     * @param line The line to parse the task from.
     * @return The task parsed from the line, or null if the line is invalid.
     */
    private Task parseTaskFromFile(String line) {
        try {
            String[] parts = line.split(",");
            Task task = null;
            
            switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            }
            
            if (task != null && parts[1].equals("1")) {
                task.markAsDone();
            }
            
            return task;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Formats a task to a string that can be written to the file.
     * 
     * @param task The task to format.
     * @return The formatted string.
     */
    private String formatTaskForFile(Task task) {
        if (task instanceof Todo) {
            return String.format("T,%d,%s", 
                task.isDone() ? 1 : 0, task.getDescription());
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return String.format("D,%d,%s,%s", 
                task.isDone() ? 1 : 0, task.getDescription(),
                d.getBy().format(FILE_DATE_FORMAT));
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return String.format("E,%d,%s,%s,%s",
                task.isDone() ? 1 : 0, task.getDescription(),
                e.getStart().format(FILE_DATE_FORMAT),
                e.getEnd().format(FILE_DATE_FORMAT));
        }
        return "";
    }
}