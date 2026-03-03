package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Servos {
    private Servo impulse, gate;
    private final ElapsedTime timer = new ElapsedTime();
    private boolean active = false;

    public void init(HardwareMap hw) {
        impulse = hw.get(Servo. class, "impulse");
        gate = hw.get(Servo.class, "gate");

        impulse.setPosition(0.3);
        gate.setPosition(0.25);
    }

    public void auto(double impulsePos, double gatePos) {
        impulse.setPosition(impulsePos);
        gate.setPosition(gatePos);
    }

    public void teleop() {
        impulse.setPosition(1);
        gate.setPosition(0);
        timer.reset();
        active = true;
    }

    public void update() {
        if (active && timer.seconds() >= 1) {
            impulse.setPosition(0.3);
            gate.setPosition(0.25);
            active = false;
        }
    }

    public boolean status() {
        double position = impulse.getPosition();

        return position == 1;
    }
}