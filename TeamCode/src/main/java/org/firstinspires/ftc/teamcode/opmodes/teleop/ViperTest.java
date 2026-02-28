package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Viper;

@TeleOp
public class ViperTest extends OpMode {
    Viper viper = new Viper();

    @Override
    public void init() {
        viper.init(hardwareMap);
    }

    @Override
    public void loop() {
        viper.controlViper(gamepad1.right_stick_y);

        double[] getPos = viper.getPosition();

        telemetry.addData("ViperR", getPos[0]);
        telemetry.addData("ViperL", getPos[1]);
        telemetry.update();
    }
}
