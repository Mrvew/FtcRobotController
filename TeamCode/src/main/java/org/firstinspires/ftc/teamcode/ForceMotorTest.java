package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Force Motor Test", group = "Test")
public class ForceMotorTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotor motor = hardwareMap.get(DcMotor.class, "front_right_drive");

        telemetry.addData("Status", "Initialized successfully");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            motor.setPower(1.0); // Force full power
            telemetry.addData("Status", "Sending 100% Power to Port 3");
            telemetry.update();
        }
        motor.setPower(0);
    }
}