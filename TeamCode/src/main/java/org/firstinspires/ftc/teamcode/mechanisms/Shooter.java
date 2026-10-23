package org.firstinspires.ftc.teamcode.mechanisms;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;

import com.pedropathing.ivy.CommandBuilder;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {

    private CRServo windmill = null;
    private DcMotorEx shooter = null;
    public Shooter(HardwareMap hardwareMap) {
        windmill = hardwareMap.get(CRServo.class, "windmill");
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
    }

    public void spinUp(){
        shooter.setVelocity(1520);
    }

    public void stop(){
        shooter.setPower(0);

    }

    public CommandBuilder shoot(){
        return sequential(
                waitMs(500),
                instant(() -> windmill.setPower(-1.0)),
                waitMs(750),
                instant(() -> windmill.setPower(0.0))

        );
    }

}
