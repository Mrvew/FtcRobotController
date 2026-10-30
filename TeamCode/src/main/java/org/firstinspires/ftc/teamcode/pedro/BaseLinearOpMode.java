package org.firstinspires.ftc.teamcode.pedro;

import static com.pedropathing.ivy.Scheduler.schedule;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.CommandBuilder;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mechanisms.Shooter;

public abstract class BaseLinearOpMode extends LinearOpMode {

    private CRServo intakeServoLeft = null;

    private CRServo intakeServoRight = null;
    private DcMotor intake = null;

    protected Follower follower;

    protected Shooter shooter;
    @Override
    public void runOpMode() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose());
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
            telemetry.addData("heading", Math.toDegrees(follower.pose().heading()));

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

    protected abstract Pose startPose();

    protected abstract Command autoRoutine();

}
