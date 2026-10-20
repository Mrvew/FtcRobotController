package org.firstinspires.ftc.teamcode.pedro;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {

    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("front_left_drive");
        c.frontRightName.set("front_right_drive");
        c.backLeftName.set("back_left_drive");
        c.backRightName.set("back_right_drive");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });


    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(0.5);
        c.yPodOffset.set(-2.0);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });


    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.18141543270660507);
                Controller secondaryTranslationalForward = Controller.proportional(0.06702815487545079);
                Controller primaryTranslationalLateral = Controller.proportional(0.23370434001935775);
                Controller secondaryTranslationalLateral = Controller.proportional(0.08634750894217717);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.015424311470441194));
                c.brake.set(Controller.proportionalFeedforward(0.013110664749875016));

                c.headingFeedback.set(Controller.proportional(2.2950310480116984));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.04374718007003254, 0.005018873802518303));

                c.linearBrakeCoefficients.set(Matrix.diag(0.06785506770227814, 0.05779711529606274));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0011199985745364936, 0.001057659401451393));

                c.maxAchievableForwardVelocity.set(67.22290196845763);
                c.maxAchievableStrafeVelocity.set(55.069636541650674);
                c.naturalForwardDeceleration.set(32.64033367505544);
                c.naturalStrafeDeceleration.set(54.598096717202594);
            }
    );
    public static Follower create(HardwareMap hardwareMap) {
         return new Follower(new PinpointLocalizer(hardwareMap, Constants.localizerConfig), new Mecanum(hardwareMap, Constants.drivetrainConfig),  new Foresight(Constants.foresightConfig));
    }


}