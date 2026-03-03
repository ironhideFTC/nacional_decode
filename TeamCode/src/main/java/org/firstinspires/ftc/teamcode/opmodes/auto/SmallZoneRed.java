package org.firstinspires.ftc.teamcode.opmodes.auto;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Servos;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@Autonomous
public class SmallZoneRed extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap, new Pose2d(61, 0, Math.toRadians(0)));
        Intake intake = new Intake();
        Servos servos = new Servos();
        Shooter shooter = new Shooter();

        intake.init(hardwareMap);
        servos.init(hardwareMap);
        shooter.init(hardwareMap);

        waitForStart();

        Actions.runBlocking(
                mecanumDrive.actionBuilder(new Pose2d(61, 0, Math.toRadians(0)))
                        .turn(Math.toRadians(-35))
                        .stopAndAdd(new shooter(shooter, 1, intake, 0.6, servos, 3))
                        .stopAndAdd(new intake(intake, 0.6))
                        .strafeToLinearHeading(new Vector2d(31, 0), Math.toRadians(270))
                        .strafeToLinearHeading(new Vector2d(31, 61), Math.toRadians(270))
                        .stopAndAdd(new intake(intake, 0))
                        .strafeToLinearHeading(new Vector2d(61, 0), Math.toRadians(325))
                        .stopAndAdd(new shooter(shooter, 1, intake, 0.6, servos, 3))
                        .stopAndAdd(new intake(intake, 0.6))
                        .strafeToLinearHeading(new Vector2d(8, 0), Math.toRadians(270))
                        .strafeToLinearHeading(new Vector2d(8, 61), Math.toRadians(270))
                        .stopAndAdd(new intake(intake, 0))
                        .strafeToLinearHeading(new Vector2d(61, 0), Math.toRadians(325))
                        .stopAndAdd(new shooter(shooter, 1, intake, 0.6, servos, 3))
                        .stopAndAdd(new intake(intake, 0.6))
                        .strafeToLinearHeading(new Vector2d(-15, 0), Math.toRadians(270))
                        .strafeToLinearHeading(new Vector2d(-15, 61), Math.toRadians(270))
                        .stopAndAdd(new intake(intake, 0))
                        .strafeToLinearHeading(new Vector2d(61, 0), Math.toRadians(325))
                        .stopAndAdd(new shooter(shooter, 1, intake, 0.6, servos, 3))
                        .build()
        );
    }

    public static class intake implements Action {
        private final Intake intake;
        private final double power;

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
        private final Shooter shooter;
        private final Intake intake;
        private final Servos servos;
        private final double shooterPower;
        private final double intakePower;
        private final int shots;

        private int currentShot = 0;
        private long lastSwitchTime = 0;
        private boolean servoHigh = false;
        private boolean started = false;
        private boolean finished = false;

        public shooter(Shooter shooter, double shooterPower, Intake intake, double intakePower, Servos servos, int shots) {
            this.shooter = shooter;
            this.intake = intake;
            this.servos = servos;
            this.shooterPower = shooterPower;
            this.intakePower = intakePower;
            this.shots = shots;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            long now = System.currentTimeMillis();

            if (!started) {
                shooter.setPower(shooterPower);
                intake.setPower(intakePower);
                lastSwitchTime = now;
                started = true;
            }

            if (currentShot < shots) {
                if (!servoHigh) {
                    servos.auto(1, 0);
                    servoHigh = true;
                    lastSwitchTime = now;
                } else if (now - lastSwitchTime > 300) {
                    servos.auto(0.3, 0.25);
                    servoHigh = false;
                    currentShot++;
                    lastSwitchTime = now;
                }
                return false;
            }

            if (!finished) {
                shooter.setPower(0);
                intake.setPower(0);
                finished = true;
            }

            return true;
        }
    }
}