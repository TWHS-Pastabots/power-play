package org.firstinspires.ftc.team16911.autonomous;

import com.qualcomm.robotcore.hardware.HardwareMap;

//Horizontal Claw Utilities
public class Utilities
{

    private HardwareMap hardwareMap;
    Utilities(HardwareMap hardwaremap)
    {
        this.hardwareMap = hardwareMap;
    }
    public void openClaw()
    {

    }
    public void rotateClaw()
    {

    }
    public void liftArm(String pos)
    {
        switch(pos)
        {
            case "floor":
                hardwareMap.liftArm.
                break;
            case "low":
                break;
            case "medium":
                break;
            case "high":

        }

    }
}
