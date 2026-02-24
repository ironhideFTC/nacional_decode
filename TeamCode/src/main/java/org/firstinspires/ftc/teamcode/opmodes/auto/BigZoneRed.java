package org.firstinspires.ftc.teamcode.opmodes.auto;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Impulse;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

public class BigZoneRed extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap, new Pose2d(50, 50, Math.toRadians(215)));
        Intake intake = new Intake();
        Impulse impulse = new Impulse();
        Shooter shooter = new Shooter();

        intake.init(hardwareMap);
        impulse.init(hardwareMap);
        shooter.init(hardwareMap);

        waitForStart();

        Actions.runBlocking(
                mecanumDrive.actionBuilder(new Pose2d(50, 50, Math.toRadians(215)))
                        .strafeToLinearHeading(new Vector2d(0, 12), Math.toRadians(215))
                        .stopAndAdd(new intake(intake, 0.8))
                        .stopAndAdd(new shooter(shooter, 3500, impulse))
                        .stopAndAdd(new stopShooter(shooter))
                        .strafeToLinearHeading(new Vector2d(25, 12), Math.toRadians(0))
                        .strafeToLinearHeading(new Vector2d(61, 12), Math.toRadians(0))
                        .strafeToLinearHeading(new Vector2d(0, 12), Math.toRadians(215))
                        .stopAndAdd(new shooter(shooter, 3500, impulse))
                        .stopAndAdd(new stopShooter(shooter))
                        .strafeToLinearHeading(new Vector2d(25, -12), Math.toRadians(0))
                        .strafeToLinearHeading(new Vector2d(61, -12), Math.toRadians(0))
                        .strafeToLinearHeading(new Vector2d(0, 12), Math.toRadians(215))
                        .stopAndAdd(new shooter(shooter, 3500, impulse))
                        .stopAndAdd(new stopShooter(shooter))
                        .strafeToLinearHeading(new Vector2d(25, -36), Math.toRadians(0))
                        .strafeToLinearHeading(new Vector2d(61, -36), Math.toRadians(0))
                        .strafeToLinearHeading(new Vector2d(0, 12), Math.toRadians(215))
                        .stopAndAdd(new shooter(shooter, 3500, impulse))
                        .stopAndAdd(new stopShooter(shooter))
                        .build()
        );
    }

    public static class intake implements Action {
        Intake intake;
        double power;

        public intake(Intake intake, double power) {
            this.intake = intake;
            this.power = power;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            intake.setPower(power);

            return true;
        }
    }

    public static class shooter implements Action {
        Shooter shooter;
        double velocity;
        Impulse impulse;

        public shooter(Shooter shooter, double velocity, Impulse impulse) {
            this.shooter = shooter;
            this.velocity = velocity;
            this.impulse = impulse;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            shooter.setVelocity(velocity);
            try {
                impulse.auto();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return true;
        }
    }

    public static class stopShooter implements Action {
        Shooter shooter;

        public stopShooter(Shooter shooter) {
            this.shooter = shooter;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            shooter.setVelocity(0);

            return true;
        }
    }
}