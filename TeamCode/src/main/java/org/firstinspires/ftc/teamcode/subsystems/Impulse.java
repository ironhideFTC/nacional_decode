package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Impulse {
    private Servo impulse;
    private boolean active = false;

    public void init(HardwareMap hw) {
        impulse = hw.get(Servo. class, "impulse");
        impulse.setPosition(0);
    }

    public void toggle() {
        active = !active;
    }

    public void update() { impulse.setPosition(active ? 1 : 0); }

    public void auto() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            wait(1);
            impulse.setPosition(1);
            wait(1);
            impulse.setPosition(0);
        }
    }
}