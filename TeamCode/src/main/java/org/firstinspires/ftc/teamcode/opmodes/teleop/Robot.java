package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.Mecanum;
import org.firstinspires.ftc.teamcode.subsystems.Servos;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@TeleOp
public class Robot extends OpMode {
    Mecanum mecanum = new Mecanum();
    Intake intake = new Intake();
    Servos servos = new Servos();
    Shooter shooter = new Shooter();

    boolean lastRB = false;
    boolean lastLB = false;

    @Override
    public void init() {
        mecanum.init(hardwareMap);
        intake.init(hardwareMap);
        servos.init(hardwareMap);
        shooter.init(hardwareMap);
    }

    @Override
    public void loop() {
        mecanum.driveControl(
                gamepad2.left_stick_x,
                gamepad2.left_stick_y,
                -gamepad2.right_stick_x
        );

        if(gamepad2.a) {
            mecanum.resetYaw();
        }

        if(gamepad1.right_bumper && !lastRB) {
            intake.toggle();
        }
        lastRB = gamepad1.right_bumper;

        if(gamepad1.a) {
            servos.teleop();
        }

        if(gamepad1.left_bumper && !lastLB) {
            shooter.toggle();
        }
        lastLB = gamepad1.left_bumper;

        intake.update();
        servos.update();
        shooter.update();

        double[] v = mecanum.getVelocity();

        telemetry.addLine("DRIVE CONTROL / GAMEPAD 1");
        telemetry.addData("FL Velocity", v[0]);
        telemetry.addData("FR Velocity", v[1]);
        telemetry.addData("BL Velocity", v[2]);
        telemetry.addData("BR Velocity", v[3]);
        telemetry.addLine("---------------------------");
        telemetry.addLine("SUBSYTEMS CONTROL / GAMEPAD 2");
        telemetry.addData("Intake", intake.status() ? "Ativo" : "Desativado");
        telemetry.addData("Servos", servos.status() ? "Voltar" : "Empurrar");
        telemetry.addData("Shooter", shooter.status() ? "Ativo" : "Desativado");
        telemetry.addData("Shooter Power", shooter.getPower());
        telemetry.update();
    }
}