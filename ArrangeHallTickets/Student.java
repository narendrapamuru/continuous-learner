package ArrangeHallTickets;

 class Student {
	
    private String hallTicket;
    private String branch;

    public Student(String hallTicket) {
        this.hallTicket = hallTicket;
        this.branch = hallTicket.substring(0, 2); 
    }

    public String getHallTicket() { 
        return hallTicket;
    }

    public String getBranch() { 
        return branch;
    }

}
