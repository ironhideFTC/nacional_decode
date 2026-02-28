package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Viper {
    private DcMotor viperR, viperL;

    public void init(HardwareMap hw) {
        viperR = hw.get(DcMotor.class, "viperR");
        viperL= hw.get(DcMotor.class, "viperL");

        viperR.setDirection(DcMotorSimple.Direction.FORWARD);
        viperL.setDirection(DcMotorSimple.Direction.REVERSE);

        viperR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        viperL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        viperR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        viperR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        viperL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void controlViper(double control) {
        viperR.setPower(control);
        viperL.setPower(control);
    }

    public void setViper(int position) {
        viperR.setTargetPosition(position);
        viperL.setTargetPosition(position);
    }

    public double[] getPosition() {
        return new double[] {
                viperR.getCurrentPosition(),
                viperL.getCurrentPosition()
        };
    }

    public boolean status() {
        double[] position = this.getPosition();

        return position[0] > 0;
    }
}