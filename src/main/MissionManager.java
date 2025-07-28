package main;

import Mission.Mission1.Mission1;
import Mission.Mission2.Mission2;
import Mission.Mission3.Mission3;
import Mission.Mission4.Mission4;
import Mission.Mission5.Mission5;
import Mission.Mission6.Mission6;

import java.util.ArrayList;
import java.util.List;

public class MissionManager {
    GamePanel gp;
    List<MissionTrigger> triggers = new ArrayList<>();

    public MissionManager(GamePanel gp) {
        this.gp = gp;
        triggers.add(new MissionTrigger("Mission1", 47 * gp.tileSize, 86 * gp.tileSize));
        triggers.add(new MissionTrigger("Mission2", 39 * gp.tileSize, 89 * gp.tileSize));
        triggers.add(new MissionTrigger("Mission3", 40 * gp.tileSize, 52 * gp.tileSize));
        triggers.add(new MissionTrigger("Mission4", 48 * gp.tileSize, 77 * gp.tileSize));
        triggers.add(new MissionTrigger("Mission5", 46 * gp.tileSize, 31 * gp.tileSize));
        triggers.add(new MissionTrigger("Mission6", 61 * gp.tileSize, 48 * gp.tileSize));

    }

    public void checkTrigger(int playerX, int playerY) {
        int triggerSize = gp.tileSize / 2;

        for (MissionTrigger trigger : triggers) {
            if (!trigger.triggered &&
                    Math.abs(playerX - trigger.worldX) < triggerSize &&
                    Math.abs(playerY - trigger.worldY) < triggerSize) {

                trigger.triggered = true;
                launchMission(trigger.missionId);
            }
        }
    }

    public void launchMission(String id) {
        switch (id) {
            case "Mission1":
                new Mission1(gp);
                break;
            case "Mission2":
                new Mission2(gp);
                break;
            case "Mission3":
                gp.mission3 = new Mission3(gp);
                break;
            case "Mission4":
                new Mission4(gp);
                break;
            case "Mission5":
                gp.mission5 = new Mission.Mission5.Mission5(gp);
                break;
            case "Mission6":
                new Mission.Mission6.Mission6(gp);
                break;
        }
    }
}
