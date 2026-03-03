package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Mecanum {
    private DcMotorEx backRight, backLeft, frontRight, frontLeft;
    private IMU imu;

    public void init(HardwareMap hw) {
        backRight = hw.get(DcMotorEx.class, "backRight");
        backLeft = hw.get(DcMotorEx.class, "backLeft");
        frontRight = hw.get(DcMotorEx.class, "frontRight");
        frontLeft = hw.get(DcMotorEx.class, "frontLeft");

        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        imu = hw.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
        ));
        imu.initialize(parameters);
    }

    public void driveControl(double xAxis, double yAxis, double rotate) {
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double rotX = xAxis * Math.cos(botHeading) - yAxis * Math.sin(botHeading);
        double rotY = xAxis * Math.sin(botHeading) + yAxis * Math.cos(botHeading);

        rotX = rotX * 1.1;

        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rotate), 1);
        double frontLeftPower = (rotY + rotX + rotate) / denominator;
        double backLeftPower = (rotY - rotX + rotate) / denominator;
        double frontRightPower = (rotY - rotX - rotate) / denominator;
        double backRightPower = (rotY + rotX - rotate) / denominator;

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);
    }

    public void resetYaw() {
        imu.resetYaw();
    }

    public double[] getVelocity() {
        return new double[] {
                frontLeft.getVelocity(),
                frontRight.getVelocity(),
                backLeft.getVelocity(),
                backRight.getVelocity()
        };
    }
}