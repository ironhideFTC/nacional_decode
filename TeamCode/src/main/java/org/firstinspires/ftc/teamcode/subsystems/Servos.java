package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Servos {
    private Servo impulse, gate;
    private boolean active = false;

    public void init(HardwareMap hw) {
        impulse = hw.get(Servo. class, "impulse");
        gate = hw.get(Servo.class, "gate");

        impulse.setPosition(0.3);
        gate.setPosition(0.25);
    }

    public void toggle() {
        active = !active;
    }

    public void update() {
        impulse.setPosition(active ? 1 : 0.3);
        gate.setPosition(active ? 0 : 0.25);
    }

    public void auto() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            wait(1);
            impulse.setPosition(0.3);
            gate.setPosition(0.25);
            wait(1);
            impulse.setPosition(1);
            gate.setPosition(0);
        }
    }

    public boolean status() {
        double position = impulse.getPosition();

        return position == 1;
    }
}