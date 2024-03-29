package org.firstinspires.ftc.team16910.Autonomous;

import org.checkerframework.checker.units.qual.C;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class CVPipeline extends OpenCvPipeline
{
    /**
     * Enum to define the possible destination for the robot
     */
    private volatile int destination = 0;

    //magenta 1
    //yellow 2
    //cyan 3

    /**
     * Colors being used and their HUE values
     * (all values HUE values are in the HSV color scale.
     * See more: https://en.wikipedia.org/wiki/HSL_and_HSV)
     */
    // Labels for each color (what each color means)

    static final Scalar YELLOW = new Scalar(255,255,0);
    static final Scalar CYAN = new Scalar(0,255,255);
    static final Scalar MAGENTA = new Scalar(255,0,255);
    // Array with all the colors being used in RGB
    static final Scalar[] RGB_COLORS = new Scalar[]{MAGENTA, YELLOW, CYAN};

    static final int YELLOW_HUE = 30;
    static final int CYAN_HUE = 90;
    static final int MAGENTA_HUE = 150;
    // Array with all the colors' hues being used
    static final int[] COLOR_HUES = new int[]{MAGENTA_HUE, YELLOW_HUE, CYAN_HUE};
    // Minimum saturation/value for the HSV mask. (Brightness)
    static final double BRIGHTNESS_THR = 0.4;

    /**
     * Core values that define the location and size of the region
     */
    static final Point REGION_TOPLEFT_POINT = new Point(80,230);  // x,y (top left corner of the window is (0,0))
    static final int REGION_WIDTH = 25;
    static final int REGION_HEIGHT = 70;


    /**
     * Points which actually define the sample region rectangles, derived from above values
     */
    final Point REGION_POINT_A = new Point(REGION_TOPLEFT_POINT.x, REGION_TOPLEFT_POINT.y);
    final Point REGION_POINT_B = new Point(REGION_TOPLEFT_POINT.x + REGION_WIDTH, REGION_TOPLEFT_POINT.y + REGION_HEIGHT);


    /**
     * Working variables
     */
    private Mat region = new Mat(), hsvRegion = new Mat();      // the subregion for the image that we care about (the analysis will be done on here)
    private double[] sums = new double[COLOR_HUES.length]; // array of all the distances from the average color to each actual color


    /**
     * Extract the region of interest from the original image
     * Convert the RGB region of the image to the HSV color scale
     *
     * @param    input   the input image from the camera in RGB color scale
     */
    public void extractRegion(Mat input) {
        region = input.submat(new Rect(REGION_POINT_A, REGION_POINT_B));    // extract the desired subregion
        Imgproc.cvtColor(region, hsvRegion, Imgproc.COLOR_RGB2HSV);         // convert the RGB region to HSV and save it in hsvRegion
    }


    /**
     * Extract the region when the robot is initialized
     *
     * @param    input   the input image from the camera in RGB color scale
     */
    @Override
    public void init(Mat input)
    {
        extractRegion(input);
    }


    /**
     * Extract the region of interest from the original image
     * Convert the RGB region of the image to the HSV color scale
     *
     * @param  input   the input image from the camera in RGB color scale
     */
    @Override
    public Mat processFrame(Mat input)
    {
        /**
         * Extract the region of interest in HSV, and
         * compute the average hue value for the region.
         */
        extractRegion(input);

        /**
         * Compute the number of pixels that meet the brightness threshold
         * and classify pixels depending on HUE.
         * The biggest sum of ones represents the color the region is closest to.
         */
        for(int i = 0; i< COLOR_HUES.length; i++) {
            Mat tmp = new Mat();
            Core.inRange(hsvRegion, new Scalar(COLOR_HUES[i]-15, BRIGHTNESS_THR*255, BRIGHTNESS_THR*255), new Scalar(COLOR_HUES[i]+15, 255, 255), tmp);
            sums[i] = Core.countNonZero(tmp);
        }

        // Compare all sums to each other and determine the biggest.
        int idx = -1;
        for(int i = 0; i< sums.length; i++) {
            boolean bigger = true;
            for (double s : sums) {
                if (sums[i] < s) {
                    bigger = false;
                    break;
                }
            }
            if (bigger){
                idx = i;
                break;
            }
        }

        double total = 0;
        for (double s : sums) {
            total += s;
        }

        for(int i = 0; i< sums.length; i++) {
            sums[i] = (int)(sums[i]/total*1000)/1000.0;
        }

        /**
         * Update the destination depending on the color.
         */
        if (idx==0) destination = 3; //right magenta
        else if (idx==1) destination = 2; //center yellow
        else destination = 1; //left cyan


        /**
         * Draw a rectangle showing the sample region on the screen.
         * Simply a visual aid. Serves no functional purpose.
         */
        Imgproc.rectangle(
                input,              // Buffer to draw on
                REGION_POINT_A,     // First point which defines the rectangle
                REGION_POINT_B,     // Second point which defines the rectangle
                RGB_COLORS[idx],    // The color the rectangle is drawn in
                2);         // Thickness of the rectangle lines

        /**
         * Show the certainty in the Telemetry.
         */

        /**
         * Render the 'input' buffer to the viewport. But note this is not
         * simply rendering the raw camera feed, because we called functions
         * to add some annotations to this buffer earlier up.
         */
        return input;
    }
    /**
     * Get the destination/position the element indicates.
     *
     * @return      the robot's destination as indicated by the previous analysis
     */
    public int getDestination()
    {
        return destination;
    }

}