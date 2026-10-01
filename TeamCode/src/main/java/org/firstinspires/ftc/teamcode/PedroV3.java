package org.firstinspires.ftc.teamcode;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name = "Pedro Pathing Test")
public class PedroV3 extends OpMode {
    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();
    // our poses
    private final Pose startPose = p.of(24, 24, 0);
    private final Pose park = p.of(48, 48, 90);

    @Override
    public void init() {
        Scheduler.reset();


        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();

        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", follower.pose().heading());
        telemetry.addData("Is Busy", follower.isBusy());
        telemetry.update();
    }

    @Override
    public void start() {
        schedule(follow(follower, park()));

    }

    private Path park() {
        return line(startPose, park).linear(startPose, park);
    }


}
