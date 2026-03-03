package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-50, 50, Math.toRadians(315)))
                .strafeToLinearHeading(new Vector2d(0, 12), Math.toRadians(315))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-25, 12), Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(-61, 12), Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(0, 12), Math.toRadians(315))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-25, -12), Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(-61, -12), Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(0, 12), Math.toRadians(315))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-25, -36), Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(-61, -36), Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(0, 12), Math.toRadians(315))
                .waitSeconds(2)

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}