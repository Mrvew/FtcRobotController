package org.firstinspires.ftc.teamcode;


import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.pedro.OpModeStorage;

@TeleOp(name = "Pedro TeleOp")
public class PedroTeleOp extends OpMode {


    private CRServo windmill = null;
    private CRServo intakeServoLeft = null;

    private CRServo intakeServoRight = null;
    private DcMotor intake = null;

    private Follower follower;

    private Shooter shooter;


    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        shooter = new Shooter(hardwareMap);
        //this set the names
        windmill = hardwareMap.get(CRServo.class, "windmill");
        intake = hardwareMap.get(DcMotor.class, "intake_motor");
        intakeServoLeft = hardwareMap.get(CRServo.class, "intakeLeft");
        intakeServoRight = hardwareMap.get(CRServo.class, "intakeRight");
    }

    @Override
    public void loop() {
        //this loop is what happens when we start
        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                -gamepad1.right_stick_x,
                follower.pose().heading()
        );
        //this set power to the intakes
        intake.setPower(1.0);
        intakeServoLeft.setPower(1.0);
        intakeServoRight.setPower(-1.0);

        windmill.setPower(gamepad1.square ? -1.0 : 0.0);
        intake.setPower(gamepad1.triangle ? -1.0 : 1.0);

        follower.manual(powers);
        follower.update();
        Pose robotPose = follower.pose();
        // returns a Pose object

        //this adds the data on the screen
        telemetry.addData("Robot X", robotPose.x());
        telemetry.addData("Robot Y", robotPose.y());
        telemetry.addData("Robot Heading", Math.toDegrees(robotPose.heading()));
    }

    @Override
    public void start() {
        //this tells it what to do once the start butten is pressed
        follower.setPose(new Pose(0, 0, 270));
        follower.update();
        shooter.spinUp();
    }

    //this stop the shooter
    @Override
    public void stop() {
        shooter.stop();
    }
}