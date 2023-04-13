package spoof;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/9/20 16:51
 * @since jdk1.8
 */
public class Test {
    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_WINDOWS);
        robot.keyPress(KeyEvent.VK_R);
        robot.keyRelease(KeyEvent.VK_WINDOWS);
        robot.keyRelease(KeyEvent.VK_R);
//        robot.keyPress(KeyEvent.VK_BACK_SPACE);
//        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
//        robot.keyPress(KeyEvent.VK_C);
//        robot.keyRelease(KeyEvent.VK_C);
//        robot.keyPress(KeyEvent.VK_M);
//        robot.keyRelease(KeyEvent.VK_M);
//        robot.keyPress(KeyEvent.VK_D);
//
//        robot.keyRelease(KeyEvent.VK_D);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
//        robot.keyPress(KeyEvent.VK_ENTER);

    }
}
