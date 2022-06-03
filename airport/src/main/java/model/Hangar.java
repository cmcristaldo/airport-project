package model;

import java.util.ArrayList;

public class Hangar {

    private final int jobType = 3;
    private int leadId;
    private ArrayList<Integer> employees;

    public Hangar(int leadId, ArrayList<Integer> employees) {
        this.leadId = leadId;
        this.employees = employees;
    }

    public int getJobType() {
        return jobType;
    }

    public int getLeadId() {
        return leadId;
    }

    public void setLeadId(int leadId) {
        this.leadId = leadId;
    }

    public ArrayList<Integer> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Integer> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Hangar{" +
                "jobType=" + jobType +
                ", leadId=" + leadId +
                ", employees=" + employees +
                '}';
    }
}
