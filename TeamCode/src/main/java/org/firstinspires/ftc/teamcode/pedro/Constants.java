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
        c.xPodOffset.set(-1.0907352627731683);
        c.yPodOffset.set(0.5402917937030943);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

//    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
//        c.name.set("pinpoint");
//        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
//        c.xPodOffset.set(-0.18115451016764003);
//        c.yPodOffset.set(1.7868333350955032);
//        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
//        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
//        c.globalDistanceUnit.set(DistanceUnit.INCH);
//        c.offsetUnits.set(DistanceUnit.INCH);
//    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.18444747194232694);
                Controller secondaryTranslationalForward = Controller.proportional(0.06814841235546952);
                Controller primaryTranslationalLateral = Controller.proportional(0.28828525090086654);
                Controller secondaryTranslationalLateral = Controller.proportional(0.10651369708409567);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.01581132593245292));
                c.brake.set(Controller.proportionalFeedforward(0.013439627042584983));

                c.headingFeedback.set(Controller.proportional(3.6041759508552085));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.04970284855115989, 0.010469130338621459));

                c.linearBrakeCoefficients.set(Matrix.diag(0.1090493502918517, 0.06335460357787905));
                c.quadraticBrakeCoefficients.set(Matrix.diag(9.734756349061637E-4, 0.0017372290924266788));

                c.maxAchievableForwardVelocity.set(64.20721476967431);
                c.maxAchievableStrafeVelocity.set(55.90042061192612);
                c.naturalForwardDeceleration.set(25.75898100346627);
                c.naturalStrafeDeceleration.set(30.515500408317987);
            }
    );

    public static Follower create(HardwareMap h) {
         return new Follower(new PinpointLocalizer(hardwareMap, Constants.localizerConfig), new Mecanum(hardwareMap, Constants.drivetrainConfig),  new Foresight(Constants.foresightConfig));
    }


}