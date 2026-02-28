package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Servos;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@TeleOp
public class Robot extends OpMode {
    MecanumDrive mecanumDrive = new MecanumDrive();
    //Viper viper = new Viper();
    Intake intake = new Intake();
    Servos impulse = new Servos();
    Shooter shooter = new Shooter();

    boolean lastX = false;
    boolean lastRB = false;
    boolean lastLB = false;
    boolean lastA = false;

    @Override
    public void init() {
        mecanumDrive.init(hardwareMap);
        //viper.init(hardwareMap);
        intake.init(hardwareMap);
        impulse.init(hardwareMap);
        shooter.init(hardwareMap);
    }

    @Override
    public void loop() {
        mecanumDrive.driveControl(
                gamepad1.left_stick_x,
                gamepad1.left_stick_y,
                -gamepad1.right_stick_x
        );

        if(gamepad1.a) {
            mecanumDrive.resetYaw();
        }

        if(gamepad1.x && !lastX) {
            mecanumDrive.toggle();
        }
        lastX = gamepad1.x;

        /*
        if(gamepad1.y) {
            viper.setViper(80);
        }
         */

        if(gamepad2.right_bumper && !lastRB) {
            intake.toggle();
        }
        lastRB = gamepad2.right_bumper;

        if(gamepad2.left_bumper && !lastLB) {
            shooter.toggle();
        }
        lastLB = gamepad2.left_bumper;

        if(gamepad2.a && !lastA) {
            impulse.toggle();
        }
        lastA = gamepad2.a;

        intake.update();
        shooter.update();
        impulse.update();

        double[] v = mecanumDrive.getVelocity();

        telemetry.addLine("DRIVE CONTROL / GAMEPAD 1");
        telemetry.addData("FL Velocity", v[0]);
        telemetry.addData("FR Velocity", v[1]);
        telemetry.addData("BL Velocity", v[2]);
        telemetry.addData("BR Velocity", v[3]);
        //telemetry.addData("Viper", viper.status() ? "Ativo" : "Desativado");
        telemetry.addLine("---------------------------");
        telemetry.addLine("SUBSYTEMS CONTROL / GAMEPAD 2");
        telemetry.addData("Intake", intake.status() ? "Ativo" : "Desativado");
        telemetry.addData("Servos", impulse.status() ? "Voltar" : "Empurrar");
        telemetry.addData("Shooter", shooter.status() ? "Ativo" : "Desativado");
        telemetry.update();
    }
}