package org.firstinspires.ftc.team16911.autonomous;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team16911.hardware.RigatoniHardware;

//Horizontal Claw Utilities
public class Utilities
{

    private RigatoniHardware hardware;
    Utilities(RigatoniHardware hardware)
    {
        this.hardware = hardware;
    }
    public void openClaw(int waitTime, Telemetry telemetry)
    {
        hardware.grabServo.setPower(0.2);
        wait(waitTime, telemetry);
        hardware.grabServo.setPower(0.0);
    }
    public void wait(int waitTime, Telemetry telemetry)
    {
        ElapsedTime time = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        time.reset();
        while (time.time() < waitTime)
        {
            telemetry.addData("Status", "Waiting");
            telemetry.addData("Wait Time", waitTime / 1000);
            telemetry.addData("Time Left", (waitTime - time.time()) / 1000);
            telemetry.update();
        }
    }
    public void rotateClaw(position)
    {
        hardware.rotServo.setPosition(position);
    }
    public void liftArm(String pos)
    {
        switch(pos)
        {
            case "floor":
                //hardwareMap.liftArm.
                break;
            case "low":
                break;
            case "medium":
                break;
            case "high":

        }

    }
}
