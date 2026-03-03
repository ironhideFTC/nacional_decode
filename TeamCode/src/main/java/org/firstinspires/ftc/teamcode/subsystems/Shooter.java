package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    private DcMotorEx shooter;
    private boolean active;

    public void init(HardwareMap hw) {
        shooter = hw.get(DcMotorEx.class, "shooter");
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void toggle() {
        active = !active;
    }

    public void update() { shooter.setPower(active ? 1 : 0); }

    public void setPower(double power) { shooter.setPower(power); }

    public boolean status() {
        double velocity = shooter.getVelocity();

        return velocity > 0;
    }

    public double getPower() {
        return shooter.getVelocity();
    }
}