package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.AprilTagWebcam;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

@Autonomous
public class AprilTagWebcamExample extends OpMode {
    AprilTagWebcam aprilTagWebcam = new AprilTagWebcam();

    @Override
    public void init(){
        aprilTagWebcam.init(hardwareMap, telemetry);
    }

    private List<Integer> redTags = List.of(30, 31, 32, 33, 34, 35, 36, 37);
    private List<Integer> blueTags = List.of(38, 39, 40, 41, 42, 43, 44, 45);

    @Override
    public void loop() {

        aprilTagWebcam.update();

        AprilTagDetection tagByspecificIDsRed = aprilTagWebcam.getTagByspecificIDs(redTags);
        AprilTagDetection tagByspecificIDsBlue = aprilTagWebcam.getTagByspecificIDs(blueTags);

        if (tagByspecificIDsRed != null) {
            aprilTagWebcam.displayDetectionTelemetry(tagByspecificIDsRed);
        } else {
            telemetry.addData("Status", "Tags not found");
        }

        if (tagByspecificIDsBlue != null) {
            aprilTagWebcam.displayDetectionTelemetry(tagByspecificIDsBlue);
        } else {
            telemetry.addData("Status", "Tag not found");
        }

//        AprilTagDetection tagByspecificIDRed = aprilTagWebcam.getTagByspecificID(30);
//        AprilTagDetection tagByspecificIDRed2 = aprilTagWebcam.getTagByspecificID(31);
//        AprilTagDetection tagByspecificIDRed3 = aprilTagWebcam.getTagByspecificID(32);
//        AprilTagDetection tagByspecificIDRed4 = aprilTagWebcam.getTagByspecificID(33);
//        AprilTagDetection tagByspecificIDRed5 = aprilTagWebcam.getTagByspecificID(34);
//        AprilTagDetection tagByspecificIDRed6 = aprilTagWebcam.getTagByspecificID(35);
//        AprilTagDetection tagByspecificIDRed7 = aprilTagWebcam.getTagByspecificID(36);
//        AprilTagDetection tagByspecificIDRed8 = aprilTagWebcam.getTagByspecificID(37);
//
//        if (tagByspecificIDRed != null) {
//            aprilTagWebcam.displayDetectionTelemetry(tagByspecificIDRed);
//        } else {
//            telemetry.addData("Status", "Tag not found");
//        }
//        if (tagByspecificIDRed2 != null) {
//            aprilTagWebcam.displayDetectionTelemetry(tagByspecificIDRed2);
//        } else {
//            telemetry.addData("Status", "Tag not found");
//        }
//        if (tagByspecificIDRed3 != null) {
//            aprilTagWebcam.displayDetectionTelemetry(tagByspecificIDRed3);
//        } else {
//            telemetry.addData("Status", "Tag not found");
//        }
//        if (tagByspecificIDRed4 != null) {
//            aprilTagWebcam.displayDetectionTelemetry(tagByspecificIDRed4);
//        } else {
//            telemetry.addData("Status", "Tag not found");
//        }
//        if (tagByspecificIDRed5 != null) {
//            aprilTagWebcam.displayDetectionTelemetry(tagByspecificIDRed5);
//        } else {
//            telemetry.addData("Status", "Tag not found");
//        }
//        if (tagByspecificIDRed6 != null) {
//            aprilTagWebcam.displayDetectionTelemetry(tagByspecificIDRed6);
//        } else {
//            telemetry.addData("Status", "Tag not found");
//        }

//        telemetry.update();
    }
}
