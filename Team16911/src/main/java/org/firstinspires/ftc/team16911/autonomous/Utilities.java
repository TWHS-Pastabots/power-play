package org.firstinspires.ftc.team16911.autonomous;

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
    public void openClaw()
    {

    }
    public void rotateClaw()
    {
        //hardware.
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
