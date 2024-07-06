package ArrangeHallTickets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class ArrangeSeatsService {

	private Map<String, List<Student>> studentsByBranch;

    public ArrangeSeatsService(String filePath) {
        this.studentsByBranch = readStudentsFromFile(filePath); 
    }

    private Map<String, List<Student>> readStudentsFromFile(String filePath) {
        Map<String, List<Student>> map = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                Student student = new Student(line.trim()); 
                map.computeIfAbsent(student.getBranch(), k -> new ArrayList<>()).add(student); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
    
    public void arrangeSeating(int columns) {
        List<Student> arrangedStudents = new ArrayList<>();
        Set<String> branches = studentsByBranch.keySet();

        while (!studentsByBranch.isEmpty()) {
            for (String branch : new ArrayList<>(branches)) {
                List<Student> list = studentsByBranch.get(branch);
                if (list != null && !list.isEmpty()) {
                    arrangedStudents.add(list.remove(0));
                    if (list.isEmpty()) {
                        studentsByBranch.remove(branch);
                    }
                }
            }
        }

        printSeating(arrangedStudents, columns);
    }
    
    private void printSeating(List<Student> arrangedStudents, int columns) {
        int totalStudents = arrangedStudents.size();
        int rows = (int) Math.ceil((double) totalStudents / columns);
        
        String lastBranch = "";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int index = i * columns + j;
                if (index < totalStudents) {
                    Student currentStudent = arrangedStudents.get(index);
                    String currentBranch = currentStudent.getBranch();

                    if (!currentBranch.equals(lastBranch)) {
                        System.out.print(currentStudent.getHallTicket() + " ");
                        lastBranch = currentBranch;
                    } else {
                        System.out.print("... ");
                    }
                }
            }
            System.out.println();
            lastBranch = "";
        }
    }

 
}
