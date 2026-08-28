package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.AprilTagWebcam;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@Autonomous
public class AprilTagWebcamExample extends OpMode {
    AprilTagWebcam aprilTagWebcam = new AprilTagWebcam();

    @Override
    public void init(){
        aprilTagWebcam.init(hardwareMap, telemetry);
    }

    @Override
    public void loop() {

        aprilTagWebcam.update();

        AprilTagDetection tagByspecificID = aprilTagWebcam.getTagByspecificID(21);
        AprilTagDetection tagByspecificID2 = aprilTagWebcam.getTagByspecificID(22);
        AprilTagDetection tagByspecificID3 = aprilTagWebcam.getTagByspecificID(23);
        if (tagByspecificID != null) {
            aprilTagWebcam.displayDetectionTelemetry(tagByspecificID);
        } else {
            telemetry.addData("Status", "Tag not found");
        }
        if (tagByspecificID2 != null) {
            aprilTagWebcam.displayDetectionTelemetry(tagByspecificID2);
        } else {
            telemetry.addData("Status", "Tag not found");
        }
        if (tagByspecificID3 != null) {
            aprilTagWebcam.displayDetectionTelemetry(tagByspecificID3);
        } else {
            telemetry.addData("Status", "Tag not found");
        }

        telemetry.update();
    }
}
