package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Impulse;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@TeleOp
public class Robot extends OpMode {
    MecanumDrive mecanumDrive = new MecanumDrive();
    Intake intake = new Intake();
    Impulse impulse = new Impulse();
    Shooter shooter = new Shooter();

    @Override
    public void init() {
        mecanumDrive.init(hardwareMap);
        intake.init(hardwareMap);
        impulse.init(hardwareMap);
        shooter.init(hardwareMap);
    }

    @Override
    public void loop() {
        mecanumDrive.driveControl(
                gamepad1.left_stick_x,
                gamepad1.left_stick_y,
                gamepad1.right_stick_x
        );
        mecanumDrive.driveIMU(hardwareMap);
        intake.setPower(gamepad2.right_bumper);
        impulse.setPosition(gamepad2.a);

        telemetry.addData("Impulse position", impulse.getPosition());
    }
}
