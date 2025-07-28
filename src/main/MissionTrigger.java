package main;

public class MissionTrigger {
    public int worldX, worldY;
    public String missionId;
    public boolean triggered = false;
    public MissionTrigger(String missionName, int worldX, int worldY) {
        this.missionId = missionName;
        this.worldX = worldX;
        this.worldY = worldY;
    }
}
