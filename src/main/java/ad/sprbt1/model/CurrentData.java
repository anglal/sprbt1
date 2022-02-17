package ad.sprbt1.model;


public class CurrentData {
    private String location;
    private int confirmedCases;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(int confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    @Override
    public String toString() {
        return "CurrentData{" +
                "location='" + location + '\'' +
                ", confirmedCases=" + confirmedCases +
                '}';
    }
}
