package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
@TeleOp(name = "LimeLightTest", group = "Sensor")
public class AprilTagLimeLightTest extends OpMode {
    private Limelight3A limeLight;
    private IMU imu;

    @Override
    public void init(){
        limeLight = hardwareMap.get(Limelight3A.class, "limelight");
        limeLight.pipelineSwitch(5);
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));
    }

    @Override
    public void start() {
        limeLight.start();
    }

    @Override
    public void loop() {
        YawPitchRollAngles orienation = imu.getRobotYawPitchRollAngles();
        limeLight.updateRobotOrientation(orienation.getYaw());
        LLResult llResult = limeLight.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            Pose3D botpose = llResult.getBotpose();
            telemetry.addData("Target x", llResult.getTx());
            telemetry.addData("Target y", llResult.getTy());
            telemetry.addData("Target Area",llResult.getTa());
            telemetry.addData("botpose",botpose.toString());
            telemetry.addData("Yaw", botpose.getOrientation().getYaw());
        }
    }
}