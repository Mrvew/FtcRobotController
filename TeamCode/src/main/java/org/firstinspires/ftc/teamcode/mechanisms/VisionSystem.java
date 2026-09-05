package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;


public class VisionSystem {

    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;


    public VisionSystem(HardwareMap hardwareMap) {
        // Create the AprilTag processor.
        aprilTag = new AprilTagProcessor.Builder()
                .setCameraPose(
                    new Position(DistanceUnit.INCH, Constants.cameraXOffset, Constants.cameraYOffset, Constants.cameraZOffset, 0),
                    new YawPitchRollAngles(AngleUnit.DEGREES, Constants.cameraYaw, Constants.cameraPitch, Constants.cameraRoll, 0)
                )
                .build();

        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessor(aprilTag)
                .build();
    }

//add the location to the perdro pathing
    public void updatePedroPose(Follower follower) {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        
        for (AprilTagDetection detection : currentDetections) {
            // Check if the detection is valid and has metadata (is in the library)
            if (detection.metadata != null && detection.robotPose != null) {
                
                // Get the robot's pose in the FTC field coordinate system.
                // FTC Field Coordinates: +X is towards Red Wall, +Y is towards Audience.
                double ftcX = detection.robotPose.getPosition().x;
                double ftcY = detection.robotPose.getPosition().y;
                double ftcHeading = detection.robotPose.getOrientation().getYaw(AngleUnit.RADIANS);

                // Nudge the Pedro Pathing follower to the new pose.
                // NOTE: Depending on your specific field setup and how you built your paths,
                // you may need to adjust the X, Y, or Heading mapping here.
                follower.setPose(new Pose(ftcX, ftcY, ftcHeading));
                
                // Usually, one good detection is enough to fix the error.
                break;
            }
        }
    }
//this will close the vision system to save resources
    public void stop() {
        if (visionPortal != null) {
            visionPortal.close();
        }
    }
}
