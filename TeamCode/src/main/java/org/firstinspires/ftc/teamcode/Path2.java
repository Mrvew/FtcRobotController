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
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.pedro.BaseLinearOpMode;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.pedro.OpModeStorage;

@Autonomous(name = "Path2", group = "Autonomous")
public class Path2 extends BaseLinearOpMode {

    private CRServo intakeServoLeft = null;

    private CRServo intakeServoRight = null;
    private DcMotor intake = null;

    private Follower follower;

    private Shooter shooter;

    //this set the points
    private final PoseFactory poseFactory = PoseFactory.degrees();
    private final Pose start = poseFactory.of(58.5963, 132.8835, 90);
    private final Pose path1 = poseFactory.of(45.355, 116.4862, 180);
    private final Pose point2Start = poseFactory.of(45.355, 116.4862, 180);
    private final Pose point2 = poseFactory.of(11.0344, 116.5622, 0);

    public Command autoRoutine() {
        return sequential(
                //this is what we do once we start
                waitMs(6000),
                //this is the shooting
                shooter.shoot(),
                shooter.shoot(),
                shooter.shoot(),
                shooter.shoot(),
                shooter.shoot(),
                shooter.shoot(),
                shooter.shoot(),
                //this is the pedro part
                follow(follower, path1()),
                follow(follower, path2())
        );
    }

    @Override
    public void runOpMode() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();
        shooter = new Shooter(hardwareMap);
        //this set the motor names
        intake = hardwareMap.get(DcMotor.class, "intake_motor");
        intakeServoLeft = hardwareMap.get(CRServo.class, "intakeLeft");
        intakeServoRight = hardwareMap.get(CRServo.class, "intakeRight");


        waitForStart();
        shooter.spinUp();
        schedule(autoRoutine());

        while (opModeIsActive()) {
            follower.update();
            Scheduler.execute();

            intake.setPower(1.0);
            intakeServoLeft.setPower(1.0);
            intakeServoRight.setPower(-1.0);

            //this set what is on the screen
            telemetry.addData("x", follower.pose().x());
            telemetry.addData("y", follower.pose().y());
            telemetry.addData("heading", follower.pose().heading());

            if (follower.currentPath() != null) {
                telemetry.addData("Current path distance remaining", follower.distanceToEndpoint());
                telemetry.addData("Path number", follower.pathIndex());
            }

            telemetry.update();
        }
        OpModeStorage.autonomousEndPose = follower.pose();
        //saves our position in that file
        shooter.stop();
        //then stops the shooter
    }


    //these are all of the actual paths
    public Path path1() {
        return Paths.line(start, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.line(point2Start, point2).linear(point2Start, point2);
    }

}