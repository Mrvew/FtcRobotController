package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

@TeleOp(name = "Sensor: color_sensor_test", group = "Sensor")
public class color_sensor_test extends LinearOpMode {

    private NormalizedColorSensor colorSensor;

    @Override
    public void runOpMode() {
        // 1. Initialization (Happens when you press INIT)
        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color");

        if (colorSensor instanceof SwitchableLight) {
            ((SwitchableLight) colorSensor).enableLight(true);
        }

        // 2. Wait for the game to start (Pauses here until you press PLAY)
        waitForStart();

        // 3. Main Loop (Runs continuously until you press STOP)
        while (opModeIsActive()) {
            NormalizedRGBA colors = colorSensor.getNormalizedColors();

            telemetry.addData("Red Channel", "%.4f", colors.red);
            telemetry.addData("Blue Channel", "%.4f", colors.blue);
            telemetry.addData("Green Channel", "%.4f", colors.green);

//            if (colors.red > colors.blue && colors.red > colors.green) {
//                telemetry.addData("Detected Element", "RED");
//            }

            telemetry.update();
            sleep(20);
        }
    }
}

