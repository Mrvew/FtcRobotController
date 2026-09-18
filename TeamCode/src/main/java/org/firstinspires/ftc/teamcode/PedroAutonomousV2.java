//package org.firstinspires.ftc.teamcode;
//
//import static org.firstinspires.ftc.teamcode.pedroPathing.Constants.createFollower;
//import static org.firstinspires.ftc.teamcode.pedroPathing.Constants.followerConstants;
//
//import android.graphics.Point;
//
//import com.pedropathing.follower.Follower;
//import com.pedropathing.geometry.BezierLine;
//import com.pedropathing.geometry.Pose;
//import com.pedropathing.paths.PathChain;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//
//@Autonomous(name = "Pedro Pathing Test")
//public class PedroAutonomous extends OpMode {
//    private Follower follower;
//    private PathChain myPath;
//
//    // Define field coordinates (inches) and heading (radians)
//    private final Pose startPose = new Pose(0, 0, Math.toRadians(0));
//    private final Pose targetPose = new Pose(24, 24, Math.toRadians(90));
//
//    @Override
//    public void init() {
//        // 1. Initialize the follower and assign the starting pose
//        follower = createFollower(hardwareMap);
//        follower.setStartingPose(startPose);
//
//        // 2. Build the path or PathChain
//        myPath = follower.pathBuilder()
//                .addPath(new BezierLine(startPose, targetPose))
//                .setLinearHeadingInterpolation(startPose.getHeading(), targetPose.getHeading())
//                .build();
//    }
//
//    @Override
//    public void start() {
//
//        follower.followPath(myPath, true);
//    }
//
//    @Override
//    public void loop() {
//        // 4. CRITICAL: update() must run every loop cycle to calculate motor powers
//        follower.update();
//
//        // Optional telemetry
//        telemetry.addData("X", follower.getPose().getX());
//        telemetry.addData("Y", follower.getPose().getY());
//        telemetry.addData("Heading", follower.getPose().getHeading());
//        telemetry.addData("Is Busy", follower.isBusy());
//        telemetry.update();
//    }
//}
