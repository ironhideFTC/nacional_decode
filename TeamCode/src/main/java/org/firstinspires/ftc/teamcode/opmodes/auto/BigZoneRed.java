package org.firstinspires.ftc.teamcode.opmodes.auto;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Servos;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@Autonomous
public class BigZoneRed extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap, new Pose2d(-50, 50, Math.toRadians(315)));
        Intake intake = new Intake();
        Servos servos = new Servos();
        Shooter shooter = new Shooter();

        intake.init(hardwareMap);
        servos.init(hardwareMap);
        shooter.init(hardwareMap);

        waitForStart();

        Actions.runBlocking(
                mecanumDrive.actionBuilder(new Pose2d(-50, 50, Math.toRadians(315)))
                        .strafeToLinearHeading(new Vector2d(0, 0), Math.toRadians(315))
                        .stopAndAdd(new intake(intake, 0.8))
                        .stopAndAdd(new shooter(shooter, 1, servos))
                        .stopAndAdd(servos(1))
                        .stopAndAdd(new stopShooter(shooter))
                        .strafeToLinearHeading(new Vector2d(-8, 0), Math.toRadians(90))
                        .strafeToLinearHeading(new Vector2d(-8, 61), Math.toRadians(90))
                        .strafeToLinearHeading(new Vector2d(0, 0), Math.toRadians(315))
                        .stopAndAdd(new shooter(shooter, 1, servos))
                        .stopAndAdd(servos(1))
                        .stopAndAdd(new stopShooter(shooter))
                        .strafeToLinearHeading(new Vector2d(15, 0), Math.toRadians(90))
                        .strafeToLinearHeading(new Vector2d(15, 61), Math.toRadians(90))
                        .strafeToLinearHeading(new Vector2d(0, 0), Math.toRadians(315))
                        .stopAndAdd(new shooter(shooter, 1, servos))
                        .stopAndAdd(servos(1))
                        .stopAndAdd(new stopShooter(shooter))
                        .strafeToLinearHeading(new Vector2d(38, 0), Math.toRadians(90))
                        .strafeToLinearHeading(new Vector2d(38, 61), Math.toRadians(90))
                        .strafeToLinearHeading(new Vector2d(0, 0), Math.toRadians(315))
                        .stopAndAdd(new shooter(shooter, 1, servos))
                        .stopAndAdd(servos(1))
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

    public Action servos(double tempo) {
        Servos servos = new Servos();

        return new SequentialAction(
                new InstantAction(() -> servos.auto(1, 1)),
                new SleepAction(tempo),
                new InstantAction(() -> servos.auto(0.3, 0.25))
        );
    }

    public static class shooter implements Action {
        Shooter shooter;
        double power;
        Servos servos;

        boolean started = false;

        public shooter(Shooter shooter, double power, Servos servos) {
            this.shooter = shooter;
            this.power = power;
            this.servos = servos;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if(!started) {
                shooter.setPower(power);
                started = true;
            }

            return false;
        }
    }

    public static class stopShooter implements Action {
        Shooter shooter;

        public stopShooter(Shooter shooter) {
            this.shooter = shooter;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            shooter.setPower(0);

            return true;
        }
    }
}