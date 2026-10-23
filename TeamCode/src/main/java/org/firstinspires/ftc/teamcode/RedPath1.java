package org.firstinspires.ftc.teamcode;


import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.CommandBuilder;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.*;
import static com.pedropathing.ivy.groups.Groups.sequential;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.pedro.BaseLinearOpMode;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.pedro.OpModeStorage;

@Autonomous(name = "RedPath1", group = "Autonomous")
public class RedPath1 extends BaseLinearOpMode {

    private CRServo intakeServoLeft = null;

    private CRServo intakeServoRight = null;
    private DcMotor intake = null;

    private Follower follower;

    private Shooter shooter;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(54.9817, 8.3251, -90);
    private final Pose path1 = poseFactory.of(55.1701, 26.456, 180);
    private final Pose point2Start = poseFactory.of(55.1701, 26.456, 180);
    private final Pose point2 = poseFactory.of(36.3087, 8.1867, 180);
    private final Pose point3 = poseFactory.of(10.9963, 8.1867, 180);
    private final Pose point4Start = poseFactory.of(10.9963, 8.1867, 180);
    private final Pose point4 = poseFactory.of(36.5853, 117.3202, 0);
    private final Pose point5Start = poseFactory.of(36.5853, 117.3202, 0);
    private final Pose point5 = poseFactory.of(58.8546, 117.0435, -90);

    // Autonomous routine
//    public Command autoRoutine() {
//        return sequential(
//                follow(follower, path1()),
//                follow(follower, path2()),
//                follow(follower, path3()),
//                follow(follower, path4()),
//                follow(follower, path5())
//        );
//    }



    public Command autoRoutine() {
        return sequential(
                waitMs(1000),
                shooter.shoot(),
                shooter.shoot(),
                shooter.shoot(),
                shooter.shoot(),
                shooter.shoot(),
                shooter.shoot()

                //follow(follower, path1())
        );
    }

    @Override
    public void runOpMode() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();
        shooter = new Shooter(hardwareMap);
        intake = hardwareMap.get(DcMotor.class, "intake_motor");
        intakeServoLeft = hardwareMap.get(CRServo.class, "intakeLeft");
        intakeServoRight = hardwareMap.get(CRServo.class, "intakeRight");



        waitForStart();
        shooter.spinUp();
        schedule(autoRoutine());

        while (opModeIsActive()) {
            follower.update();
            Scheduler.execute();

                    telemetry.addData("x", follower.pose().x());
            telemetry.addData("y", follower.pose().y());
            telemetry.addData("heading", follower.pose().heading());

            if (follower.currentPath() != null) {
                telemetry.addData("Current path distance remaining", follower.distanceToEndpoint());
                telemetry.addData("Path number", follower.pathIndex());
            }

            telemetry.update();
        }
        OpModeStorage.autonomousEndPose = follower.pose(); //saves your position in that file
        shooter.stop();
    }


    public Path path1() {
        return Paths.line(start, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.line(point2Start, point2).linear(point2Start, point2);
    }

    public Path path3() {
        return Paths.line(point2, point3).tangent();
    }

    public Path path4() {
        return Paths.line(point4Start, point4).linear(point4Start, point4);
    }

    public Path path5() {
        return Paths.line(point5Start, point5).linear(point5Start, point5);
    }
}
