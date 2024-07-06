package ArrangeHallTickets;

public class ArrangeSeats {
    public static void main(String[] args) {
        String filePath = "src/ArrangeHallTickets/students.txt";
        int columns = 3;
        ArrangeSeatsService seatingArrangement = new ArrangeSeatsService(filePath);
        seatingArrangement.arrangeSeating(columns);
    }
}