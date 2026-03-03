package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private DcMotor intake;
    private boolean active = false;

    public void init(HardwareMap hw) {
        intake = hw.get(DcMotor.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void toggle() { active = !active; }

    public void update() { intake.setPower(active ? 0.6 : 0); }

    public void setPower(double power) { intake.setPower(power); }

    public boolean status() {
        double power = intake.getPower();

        return power > 0;
    }
}