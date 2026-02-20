package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Impulse {
    private Servo impulse;

    public void init(HardwareMap hw) {
        impulse = hw.get(Servo. class, "impulse");
    }

    public void setPosition(boolean alternate) {
        if (alternate) {
            impulse.setPosition(1);
        } else {
            impulse.setPosition(0);
        }
    }

    public double getPosition() {
        return impulse.getPosition();
    }
}