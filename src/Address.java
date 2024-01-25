import javax.swing.*;

public class Address {

    protected String streetName;
    protected String streetNO;
    protected String buildingNO;
    protected String cityName;
    protected String containter_addressInfo[];

    Address() {
        containter_addressInfo = new String[]{"City name", "Street", "Street number", "Building number", ""};
    }

    protected void setInput(String addressEntered[]) {
        cityName = addressEntered[0];
        streetName = addressEntered[1];
        streetNO = addressEntered[2];
        buildingNO = addressEntered[3];
    }

    public String toString() {

        String tmpCityName = cityName != null ? "City - " + cityName + ", " : "";
        String tmpStreetName = streetName != null ? "Street - " + streetName + ", " : "";
        String tmpStreetNumber = streetNO != null ? "Street number - " + streetNO + ", " : "";
        String tmpBuildingNumber = buildingNO != null ? "Building number - " + buildingNO + "." : "";

        return tmpCityName + tmpStreetName + tmpStreetNumber + tmpBuildingNumber;
    }

    public void getMessage(){
        String message = "Your " + this.getClass().getName() + " address is not set.";
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}

class Job extends Address {

    private String job;

    Job() {
        containter_addressInfo[containter_addressInfo.length - 1] = "Job name";
    }

    @Override
    public void setInput(String[] addressEntered) {
        super.setInput(addressEntered);
        job = addressEntered[4];
    }

    @Override
    public String toString() {
        String tmpJob = job != null ? "Profession name: " + job + ", " : "";
        return tmpJob + super.toString();
    }
}

class Home extends Address {

    private String homeName;

    Home() {
        containter_addressInfo[containter_addressInfo.length - 1] = "House name";
    }

    @Override
    public void setInput(String[] addressEntered) {
        super.setInput(addressEntered);
        homeName = addressEntered[4];
    }

    @Override
    public String toString() {
        String tmpHomeName = homeName != null ? "Home is called '" + homeName + "' and is in: " : "";
        return tmpHomeName + super.toString();
    }
}

class School extends Address {

    private String schoolName;

    School() {
        containter_addressInfo[containter_addressInfo.length - 1] = "School name";
    }

    @Override
    public void setInput(String[] addressEntered) {
        super.setInput(addressEntered);
        schoolName = addressEntered[4];
    }

    @Override
    public String toString() {
        String tmpSchoolName = schoolName != null ? "School '" + schoolName + "' is in: " : "";
        return tmpSchoolName + super.toString();
    }
}

