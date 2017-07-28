package cmu.curantis.outputbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CircleInfo {
    private long circleId;
    private String circleName;
    public long getCircleId() {
        return circleId;
    }
    public void setCircleId(long circleId) {
        this.circleId = circleId;
    }
    public String getCircleName() {
        return circleName;
    }
    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

}
